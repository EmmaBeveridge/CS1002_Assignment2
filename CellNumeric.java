import java.util.regex.Pattern;

/**
 * Specialisation of Cell super class for a numeric cell
 */
public class CellNumeric extends Cell {

    /**
     * Child constructor for new numeric cell. Creates cell with specified value
     * 
     * @param value Value to be contained within numeric cell
     */
    public CellNumeric(String value) {
        super(value); // Calls constructor in parent Cell class passing value as argument
    }

    /**
     * Check if cell value is valid for numeric block type. Numeric cells valid if
     * value is integer from 1-9 inclusive
     * 
     * @return Boolean true if value is valid, false otherwise
     */
    @Override
    protected boolean isCellValueValid() {
        // Regex information source Java SE Documentation:
        // https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
        return Pattern.matches("^[1-9]$", this.getValue());
    }

}
