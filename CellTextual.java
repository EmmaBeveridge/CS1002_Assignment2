import java.util.regex.Pattern;

public class CellTextual extends Cell{

    public CellTextual(String value){
        super(value);
    }

    @Override
    protected boolean isCellValueValid(){
        return Pattern.matches("^[A-Y]$", this.getValue());
    }
    
}
