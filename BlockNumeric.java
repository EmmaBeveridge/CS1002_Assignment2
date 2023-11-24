import java.util.Scanner;

/**
 * Specialisation of Block super class for a numeric block
 */
public class BlockNumeric extends Block {

    // #region Class Constants
    private static final int NUMERIC_BLOCK_SIZE = 3;
    // #endregion

    /**
     * Child constructor for new numeric block. Creates block with size 3
     * 
     * @param reader Reference to active Scanner object to read user input
     */
    public BlockNumeric(Scanner reader) {
        super(NUMERIC_BLOCK_SIZE, reader); // Calls constructor in parent Block class, specifying size of block as 3
    }

    /**
     * Create new Line for numeric block
     * 
     * @return LineNumeric for numeric block
     */
    @Override
    protected LineNumeric getNewValueLine() {
        return new LineNumeric();
    }

    /**
     * Create new Cell for numeric block with value
     * 
     * @param cellValue Value to be stored in NumericCell
     * @return CellNumeric for numeric block with value
     */
    @Override
    protected CellNumeric getNewCell(String cellValue) {
        return new CellNumeric(cellValue);

    }

}
