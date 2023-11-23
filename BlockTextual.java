import java.util.Scanner;
/**
 * Specialisation of Block super class for a textual block
 */
public class BlockTextual extends Block {
//#region Class Constants
private static final int TEXTUAL_BLOCK_SIZE=5;
//#endregion

     /**
     * Child constructor for new textual block. Creates block with size 3
     * @param reader Reference to active Scanner object to read user input
     */
    public BlockTextual(Scanner reader){
        super(TEXTUAL_BLOCK_SIZE, reader);
    }


    /**
     * Create new Line for textual block
     * @return LineTextual for textual block
     */
    @Override
    protected LineTextual getNewValueLine() {
        return new LineTextual();    
    }



     /**
     * Create new Cell for textual block with value
     * @param cellValue Value to be stored in TextualCell
     * @return CellTextual for textual block with value
     */
    @Override
    protected CellTextual getNewCell(String cellValue) {
        return new CellTextual(cellValue);
    }

    
    
}
