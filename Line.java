import java.util.ArrayList;

public abstract class Line { 

    //need to store validity dynamically as lines processed as columns should only be checked if all rows valid 
    private boolean isValid;
    public boolean getIsValid(){return isValid;}    
    
    //ArrayList information source Java SE Documentation: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
    private ArrayList<String> duplicateValues;
    public String getFirstDuplicate(){return this.duplicateValues.get(0);}
    
    //Bit Field information source Wikipedia, Bit Field: https://en.wikipedia.org/wiki/Bit_field
    private int bitField;

    public Line(){
        this.isValid=true;
        this.duplicateValues=new ArrayList<String>();
    }



    /**
     * Adds values to line bit field at corresponding index using AddValue() until duplicate encountered.
     * @param values
     */
    public void addCells(Cell[] cells){
        for (Cell cell : cells) {
            this.addCell(cell);
            if (!this.isValid){return;}
        }
       
    }

    /**
     * Adds single value to line bit field at corresponding index unless value is a duplicate in line or line is already found to be invalid
     * @param value Value to add to line as String
     */
    public void addCell(Cell cell){
        //add duplicate values to duplicateValues
        // set isValid to false if it is a duplicate

        if (!this.isValid){return;}
        String value = cell.getValue();
        int valueAsBits = 1 << this.convertValueToFieldIndex(value);
        if (this.hasValuesInCommon(valueAsBits)){
            this.isValid = false;
            //this.duplicateValues.add(this.getFirstValueInCommon(valueAsBits));
            this.duplicateValues.add(value);
        }
        else{
            this.mergeBitFields(valueAsBits);
        }
        
    }

    /**
     * Determines if the line contains values also contained in otherLine using bit field and otherLine bit field as mask
     * @param otherLine Line to check for duplicate values between
     * @return true if duplicate values present, otherwise false
     */
    public boolean hasValuesInCommon(Line otherLine){
        return this.hasValuesInCommon(otherLine.bitField);
    }

    private boolean hasValuesInCommon(int otherLineMask){
        return (this.bitField & otherLineMask)!=0;
    }

    /**
     * Returns the leftmost duplicate value between the lines
     * @param otherLine Line to find duplicate value between
     * @return String of leftmost duplicate
     */
    /*
     public String getFirstValueInCommon(Line otherLine){
        return this.getFirstValueInCommon(otherLine.bitField);
    }

    private String getFirstValueInCommon(int otherLineMask){
        int commonIndices=this.bitField & otherLineMask;
        //leftmost duplicate value is the value represented by index of most significant bit that is 1 in commonIndices bit string = largest integer power of 2 in bit string 
        int firstDuplicateIndex = (int)(Math.log(commonIndices)/Math.log(2));
        return this.convertFieldIndexToValue(firstDuplicateIndex);
    }
*/
    
    /**
     * Adds all values in mergeLine to line bit field
     * @param mergeLine Line to merge bit fields with
     */
    public void mergeBitFields(Line mergeLine){
        this.mergeBitFields(mergeLine.bitField);
    }

    private void mergeBitFields(int mergeField){
        this.bitField=this.bitField|mergeField;
    }


    protected abstract String convertFieldIndexToValue(int index);
    protected abstract int convertValueToFieldIndex(String value);

} 


   
    

