/**
 * Abstract class to model Sudoku cell. Inherited by CellNumeic and CellTextual.
 */
public abstract class Cell {
    // #region FIELDS
    private String value; // Value contained within cell. Stored as String for textual and numeric cells.
    // #endregion

    public String getValue() {
        return this.value;
    }

    /**
     * Super constructor for abstract Cell class. Called explicitly by derived
     * classes to initialise fields
     * 
     * @param value Value contained within cell
     */
    public Cell(String value) {
        this.value = value;
    }

    /**
     * Check if cell value is valid for block type
     * 
     * @return Boolean true if value is valid, false otherwise
     */
    protected abstract boolean isCellValueValid();

}
