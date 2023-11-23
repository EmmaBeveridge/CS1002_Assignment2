import java.util.regex.Pattern;

public class CellNumeric extends Cell {


    public CellNumeric(String value){
        super(value);
    }


    @Override
    protected boolean isCellValueValid(){
        return Pattern.matches("^[1-9]$", this.getValue());
    }
    
}
