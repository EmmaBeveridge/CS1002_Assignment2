public abstract class Cell {
    private String value;

    public String getValue(){return this.value;}

    public Cell(String value){
        this.value = value;
    }
    
    protected abstract boolean isCellValueValid();

}
