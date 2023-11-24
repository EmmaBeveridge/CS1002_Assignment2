/**
 * Specialisation of Line super class for a textual line
 */
public class LineTextual extends Line {

    // #region CLASS CONSTANTS
    private static final int ASCII_OFFSET = 65; // Offset/shift that must be applied to map ASCII codes for uppercase
                                                // letters to values in range 0(A)-24(Y)
    // #endregion

    /**
     * Convert the bit field index to the cell value for textual type
     * 
     * @param index Index to convert
     * @return Value represented by index
     */
    @Override
    protected String convertFieldIndexToValue(int index) {
        // Index 0 in field => ASCII 65 = "A", index 1 => ASCII 66 = "B" etc.
        int ASCII = index + ASCII_OFFSET;
        return Character.toString((char) ASCII);
    }

    /**
     * Convert the cell value to its bit field index for textual type
     * 
     * @param value Cell value to convert
     * @return Index of flag for value in bit field
     */
    @Override
    protected int convertValueToFieldIndex(String value) {
        // Value "A" = ASCII 65 => index 0 in field, value "B" = ASCII 66 => index 1 etc.
        int ASCII = (int) value.charAt(0);
        return ASCII - ASCII_OFFSET;
    }

}
