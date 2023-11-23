public class LineNumeric extends Line {

    @Override
    protected String convertFieldIndexToValue(int index) {
        //Index 0 in mask => value "1", index 1 => "2" etc.
        return Integer.toString(index+1);
    }

    @Override
    protected int convertValueToFieldIndex(String value) {
        //Value "1" => index 0 in mask, "2" => index 1 etc.
        return Integer.parseInt(value)-1;
    }
   


}
