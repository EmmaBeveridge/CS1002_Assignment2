public class LineTextual extends Line {

    private static final int ASCII_OFFSET = 65;


    @Override
    protected String convertFieldIndexToValue(int index) {
        //Index 0 => ASCII 65 = "A", index 1 => ASCII 66 = "B" etc.
        int ASCII = index + ASCII_OFFSET;
        return Character.toString((char) ASCII);
    }


    @Override
    protected int convertValueToFieldIndex(String value) {
       //Value "A" = ASCII 65 => index 0, value "B" = ASCII 66 => index 1 etc.
       int ASCII = (int)value.charAt(0);
       return ASCII-ASCII_OFFSET;
    }

    
    
}
