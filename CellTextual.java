import java.util.regex.Pattern;

/**
 * Specialisation of Cell super class for a textual cell
 */
public class CellTextual extends Cell {

    /**
     * Child constructor for new textual cell. Creates cell with specified value
     * 
     * @param value Value to be contained within textual cell
     */
    public CellTextual(String value) {
        super(value); // Calls constructor in parent Cell class passing value as argument
    }

    /**
     * Check if cell value is valid for textual block type. Textual cells valid if
     * value is an uppercase letter from A-Y inclusive
     * 
     * @return Boolean true if value is valid, false otherwise
     */
    @Override
    protected boolean isCellValueValid() {
        // Regex information source Java SE Documentation:
        // https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
        return Pattern.matches("^[A-Y]$", this.getValue());
    }

}
