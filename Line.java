import java.util.ArrayList;

/**
 * Abstract class to model Sudoku line. Inherited by LineNumeic and LineTextual.
 */
public abstract class Line {

    // #region FIELDS
    private boolean isValid;// Flag to store validity, updated as lines processed. Columns and blocks should
                            // only be checked if all rows valid however use of flag means we do not need to
                            // process separately.

    // ArrayList information source Java SE Documentation:
    // https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
    private ArrayList<String> duplicateValues; // Array list used to store first duplicate value encountered - not
                                               // necessary to use ArrayList but helps in scalability if in in future
                                               // versions all duplicate elements should be reported

    // Bit Field information source Wikipedia, Bit Field:
    // https://en.wikipedia.org/wiki/Bit_field
    private int bitField; // Bit field with flags representing valid values: 1 indicates value for index
                          // is present in line, 0 indicates value for index is not present in line
    // #endregion

    public boolean getIsValid() {
        return isValid;
    }

    /**
     * Accessor for first element of duplicateValues
     * 
     * @return First duplicate value in Line
     */
    public String getFirstDuplicate() {
        return this.duplicateValues.get(0);
    }

    /**
     * Super constructor for abstract Line class. Called implicitly by derived
     * classes to initialise fields
     */
    public Line() {
        this.isValid = true; // Assumes line is valid until found otherwise
        this.duplicateValues = new ArrayList<String>();
    }

    /**
     * Adds values to line bit field at corresponding index using addCell() until
     * duplicate encountered.
     * 
     * @param values
     */
    public void addCells(Cell[] cells) {
        for (Cell cell : cells) {
            this.addCell(cell);
            if (!this.isValid) {
                return; // Do not continue to add value to invalid line
            }
        }

    }

    /**
     * Adds single value to line bit field at corresponding index unless value is a
     * duplicate in line or line is already found to be invalid
     * 
     * @param value Value to add to line as String
     */
    public void addCell(Cell cell) {
        if (!this.isValid) {
            return; // Do not continue to add value to invalid line
        }
        String value = cell.getValue();
        // Bitwise Operations information source The Java Tutorials:
        // https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html
        int valueAsBits = 1 << this.convertValueToFieldIndex(value); // Creates bit mask to indicate presence of value
        if (this.hasValuesInCommon(valueAsBits)) { // value present in line
            this.isValid = false;
            this.duplicateValues.add(value);
        } else { // value unique to line
            this.mergeBitFields(valueAsBits); // Add value to line
        }

    }

    /**
     * Determines if the line contains values also contained in otherLine using bit
     * field and otherLine bit field as mask
     * 
     * @param otherLine Line to check for duplicate values between
     * @return true if duplicate values present, otherwise false
     */
    public boolean hasValuesInCommon(Line otherLine) {
        return this.hasValuesInCommon(otherLine.bitField);
    }

    /**
     * Determines if the line contains values also contained in otherLine using bit
     * field and otherLine bit field as mask
     * 
     * @param otherLineMask Line mask to check for duplicate values between
     * @return true if duplicate values present, otherwise false
     */
    private boolean hasValuesInCommon(int otherLineMask) {
        // Bitwise Operations information source The Java Tutorials:
        // https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html
        return (this.bitField & otherLineMask) != 0; // AND operation result will always be 0 unless bits at same index
                                                     // are both 1 i.e. value present in both line's bit field and
                                                     // otherLine's mask
    }

    /**
     * Adds all values in mergeLine to line bit field
     * 
     * @param mergeLine Line to merge bit fields with
     */
    public void mergeBitFields(Line mergeLine) {
        this.mergeBitFields(mergeLine.bitField);
    }

    /**
     * Adds all values in mergeMask to line bit field
     * 
     * @param mergeMask Bit field to merge with
     */
    private void mergeBitFields(int mergeMask) {
        // Bitwise Operations information source The Java Tutorials:
        // https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html
        this.bitField = this.bitField | mergeMask; // OR operation will produce result with 1 at all indexes where 1 is
                                                   // present in either field i.e. adds all values present in the merge
                                                   // field to the line's field
    }

    /**
     * Convert the bit field index to the cell value for block type
     * 
     * @param index Index to convert
     * @return Value represented by index
     */
    protected abstract String convertFieldIndexToValue(int index);

    /**
     * Convert the cell value to its bit field index for block type
     * 
     * @param value Cell value to convert
     * @return Index of flag for value in bit field
     */
    protected abstract int convertValueToFieldIndex(String value);

}
