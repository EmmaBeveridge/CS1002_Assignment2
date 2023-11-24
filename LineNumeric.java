/**
 * Specialisation of Line super class for a numeric line
 */
public class LineNumeric extends Line {

    /**
     * Convert the bit field index to the cell value for numeric type
     * 
     * @param index Index to convert
     * @return Value represented by index
     */
    @Override
    protected String convertFieldIndexToValue(int index) {
        // Index 0 in field => value "1", index 1 => "2" etc.
        return Integer.toString(index + 1);
    }

    /**
     * Convert the cell value to its bit field index for numeric type
     * 
     * @param value Cell value to convert
     * @return Index of flag for value in bit field
     */
    @Override
    protected int convertValueToFieldIndex(String value) {
        // Value "1" => index 0 in field, "2" => index 1 etc.
        return Integer.parseInt(value) - 1;
    }

}
