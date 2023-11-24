import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Abstract class to model Sudoku block. Inherited by BlockNumeic and
 * BlockTextual.
 */
public abstract class Block {

    // #region FIELDS
    protected int size; // Blocks are square
    protected Line[] rows; // Array of rows in block
    protected Line[] columns; // Array of columns in block
    protected Cell[][] cells; // 2D array of cells in block
    protected Scanner reader; // Active Scanner
    private boolean structureValid; // Flag indicating if block has valid structure
    private boolean formatValid; // Flag indicating if block has valid format
    private String errorDescription; // Error message to output to user

    // #endregion

    /**
     * Super constructor for abstract Block class. Called explicitly by derived
     * classes to initialise fields
     * 
     * @param size   Size of square block, i.e. size = number of elements per row =
     *               number of elements per column
     * @param reader Reference to active Scanner object to read user input
     */
    public Block(int size, Scanner reader) {
        this.reader = reader; // Assigns Scanner object passed as parameter to object field. Easier to share
                              // reference to single Scanner object between objects than to close current
                              // Scanner and then instantiate new Scanner object.
        this.size = size;
        this.formatValid = true; // Assumes valid format until error is encountered
        this.structureValid = true; // Assumes valid structure until error is encountered
        this.cells = new Cell[size][size]; // Initialises 2D cells array to size of block
        this.rows = new Line[size]; // Initialises rows array to size of block
        this.columns = new Line[size]; // Initialises columns array to size of block

        // Overwrite default null elements in rows and columns arrays to new instances
        // of Line, representing currently blank lines in the block
        for (int i = 0; i < this.size; i++) {
            this.rows[i] = getNewValueLine();
            this.columns[i] = getNewValueLine();
        }

    }

    /**
     * Called from main to initialise constructed Block object. Uses CheckFormat and
     * CheckStructure methods to populate and validate block with user-input
     */
    public void initialise() {
        System.out.println("Enter cell values:");
        this.checkFormat();
        if (!this.formatValid) {
            System.out.println(this.errorDescription);
        } else {
            this.checkStructure();
            if (!this.structureValid) {
                System.out.println(this.errorDescription);
            } else {
                System.out.println("Valid format, valid structure.");
            }
        }
    }

    /**
     * Called from Initialise in Block super class. Reads user input of block
     * element values.
     * Validates block format by checking:
     * -Number of cells input for row
     * -Input value of cells
     * -Number of rows input
     * Records results of checks to boolean flag formatValid, writes error output to
     * errorDescription String
     */
    private void checkFormat() {

        // Iterate over each row of cells (outer level arrays in 2D cells array)
        for (Cell[] row : this.cells) {
            if (this.reader.hasNextLine()) { // Check there is input before attempting to read (required for stacscheck
                                             // tests/ input read from file)

                String rowInput = this.reader.nextLine();

                // Regex information source Java SE Documentation:
                // https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
                String[] rowValues = rowInput.replaceAll("\\s+", "").split(",", -1); // Parses single line String of row
                                                                                     // input values into individual
                                                                                     // cell values. Whitespace removed
                                                                                     // from input by matching with
                                                                                     // regex and replacing with empty
                                                                                     // string. String then split into
                                                                                     // elements using comma(",") as
                                                                                     // delimeter. Empty/whitespace
                                                                                     // entries separated by commas are
                                                                                     // still treated as cell value
                                                                                     // entries, even if trailing - row
                                                                                     // containing empty entries will
                                                                                     // have length including empty
                                                                                     // entries but empty values will
                                                                                     // not be considered valid.

                if (rowValues.length != size) { // If row is not of correct length:
                    this.formatValid = false;
                    this.errorDescription = "Invalid format: number of cells in row.";
                    return; // Do not check for further format errors
                }

                // Check cell values valid and create new cells
                for (int j = 0; j < row.length; j++) {
                    Cell newCell = this.getNewCell(rowValues[j]); // Add new cell with entered value - value potentially
                                                                  // invalid
                    if (!newCell.isCellValueValid()) { // If entered value is invalid
                        this.formatValid = false;
                        this.errorDescription = "Invalid format: value: " + newCell.getValue() + ".";
                        return; // Do not check for further format errors
                    } else {
                        row[j] = newCell; // Assign cell to row in cells array at correct position
                    }
                }

            } else { // Missing expected row
                formatValid = false;
                this.errorDescription = "Invalid format: number of rows.";
                return; // Do not check for further format errors
            }

        }

    }

    /**
     * Called from Initialise in Block super class if format of block valid.
     * Validates block structure by checking:
     * - No repeated values within a row
     * - No repeated values within a column
     * - No repeated values within whole block
     */

    private void checkStructure() {

        Line blockLine = this.getNewValueLine(); // Create Line object to store running account of block values

        // Construct and check rows for repeats, construct columns
        for (int i = 0; i < this.size; i++) { // Iterating for each row
            this.rows[i].addCells(this.cells[i]); // Adding all cells in row's values to row's bit field
            if (!this.rows[i].getIsValid()) { // If row contains repeated values
                this.structureValid = false;
                this.errorDescription = "Valid format, invalid structure: " + this.rows[i].getFirstDuplicate()
                        + " repeated in row " + (i + 1) + ".\n"; // In the case of multiple duplicate values, only the
                                                                 // first is displayed
                return; // Do not check for further structure errors
            }

            // For each row, add cell values to respective columns
            for (int j = 0; j < this.cells[i].length; j++) {
                this.columns[j].addCell(this.cells[i][j]); // Adding single cell value to column's bit field
            }

            blockLine.addCells(this.cells[i]); // Adding all cells in row's values to block's bit field

        }

        // Check columns for repeats
        for (int i = 0; i < this.columns.length; i++) { // Iterating for each column
            if (!this.columns[i].getIsValid()) { // If column contains repeated values
                this.structureValid = false;
                this.errorDescription = "Valid format, invalid structure: " + this.columns[i].getFirstDuplicate()
                        + " repeated in column " + (i + 1) + ".\n"; // In the case of multiple duplicate values, only
                                                                    // the first is displayed
                return; // Do not check for further structure errors
            }
        }

        if (!blockLine.getIsValid()) { // If block contains repeated values
            this.structureValid = false;
            this.errorDescription = "Valid format, invalid structure: " + blockLine.getFirstDuplicate()
                    + " repeated in block.\n"; // In the case of multiple duplicate values, only the first is displayed
            return; // Do not check for further structure errors
        }

    }

    /**
     * Create new Line object of appropriate subtype for block type
     * 
     * @return Line for block type
     */
    protected abstract Line getNewValueLine();

    /**
     * Create new Cell object of appropriate subtype for block type with value
     * 
     * @param cellValue Value to be stored in Cell
     * @return Cell for block type with value
     */
    protected abstract Cell getNewCell(String cellValue);

}
