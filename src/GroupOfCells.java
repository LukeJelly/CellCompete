/*
 * The Prompt:
 * Given a binary array of size n where n > 3. A true (or 1) value in the array 
 * means active and false (or 0) means inactive. Given a number k, the task is 
 * to find count of active and inactive cells after k days. After every day, 
 * status of i’th cell becomes active if left and right cells are not same and 
 * inactive if left and right cell are same (both 0 or both 1).
 * Since there are no cells before leftmost and after rightmost cells, the value
 * cells before leftmost and after rightmost cells is always considered as 0 (or inactive).
 * 
 * Source:
 * https://www.geeksforgeeks.org/active-inactive-cells-k-days/
 * 
 * 
 * Solution:
 * This uses a personal implementation of doubly linked list to represent the 
 * group of cells. I traverse through the linked list to compare each cell to 
 * it's neighbor and change that cells value if required.
 * 
 * //TODO:
 * I think it mite actually be easier to solve this with a Deque and a Queue but
 * I will try that at a latter time. 
 */

/**
 * A simple class to represent a group of cells and its neighbors. This class
 * was created to solve the CellCompete problem posted on Geeksforgeeks.
 * 
 * @author Luke Kelly
 * @version 2.0 2/3/2020
 */
public class GroupOfCells {
    private Cell farLeftCell;
    private int size;
    private final byte ACTIVE = 1;
    private final byte INACTIVE = 0;

    /**
     * Creates a new GroupOfCells from the given array, preconditions:
     * <ul>
     * <li>1. The array must be comprised of 0s and 1s<\li>
     * <li>2. Size of array must be greater than 3<\li>
     * <li>3. Array can not be null<\li>
     * </ul>
     * @param arr the array to be converted into a GroupOfCells
     * @throws IllegalArgumentException if arr == null, n <= 3, or a value other
     *                                  than 0s and 1s exist in array.
     */
    public GroupOfCells(byte[] arr) {
        constructorValidation(arr);
        createGroupOfCellsFromArray(arr);
    }

    private void constructorValidation(byte[] arr){
        if (arr == null) {
            throw new IllegalArgumentException("Can not create a GroupOfCells from a null array");
        }
        if (arr.length <= 3) {
            throw new IllegalArgumentException("Must have at least three values in the array");
        }
    }

    @Override
    /**
     * Returns a string representation of this GroupOfCells.
     * 
     * @return a string representation of this GroupOfCells.
     */
    public String toString() {
        StringBuffer output = new StringBuffer();
        Cell p = farLeftCell;
        while (p.right != null) {
            output.append(p.value + ",");
            p = p.right;
        }
        output.append(p.value);
        return output.toString();
    }

    /**
     * Advances the current group of cells by the given amount of days.
     * 
     * @param days how many days to advance the current Group of Cells.
     * @throws IllegalArgumentException if days < 0
     */
    public void compete(int days){
        checkDaysGreaterThanMinPossible(days);
        for(int daysCounter = 0; daysCounter < days; daysCounter++){
            iterateGroupOfCellsOneDay();
        }
    }

    private void checkDaysGreaterThanMinPossible(int days){
        int MIN_DAYS = 0;
        if(days < MIN_DAYS ){
            throw new IllegalArgumentException("Can not advance group of cells by a negative amount");
        }
    }

    private void iterateGroupOfCellsOneDay(){
        Cell currentCellPointer = this.farLeftCell;
        byte leftPreviousValue = 0;
        // While there the pointer to the current Group of Cells is not null
        while(currentCellPointer != null){
            byte currentCellValue = currentCellPointer.value;
            mutateACell(leftPreviousValue, currentCellPointer);
            leftPreviousValue = currentCellValue;
            currentCellPointer = currentCellPointer.right;
        }
    } 

    /**
     * Get an array that represents the state of the the current Group of Cells
     * @return an array that represents the state of the current Group of Cells
     */
    public byte[] getArrayOfCells() {
        byte[] outputArr = new byte[this.size];
        Cell p = farLeftCell;
        for (int i = 0; i < size; i++) {
            outputArr[i] = p.value;
            p = p.right;
        }
        return outputArr;
    }
    /**
     * Checks if the given pointer's left and right Cells are the same then changes
     * the secondary pointers values. 
     */
    private void mutateACell(byte leftPreviousValue, Cell currentCellPointer){  
            byte rightValue = findRightCellsValue(currentCellPointer);
            if(leftPreviousValue == rightValue){
                currentCellPointer.value = INACTIVE;
            }else{
                currentCellPointer.value = ACTIVE;
            }
    }

    private byte findRightCellsValue(Cell cell){
        byte rightValue = (cell.right == null) ? INACTIVE : cell.right.value; 
        return rightValue;
    }

    private void createGroupOfCellsFromArray(byte[] arr) {
        Cell lastUsedCell;
        Cell currentCell;
        int sizeOfArr = arr.length;
        int i = 0;
        lastUsedCell = makeOneCell(i, arr, null);
        i++;
        //Set farLeftCell to the first cell created.
        this.farLeftCell = lastUsedCell;
        //Loop through the rest and create the rest of the cells.
        for (; i < sizeOfArr; i++) {
            currentCell = makeOneCell(i, arr, lastUsedCell);
            lastUsedCell.right = currentCell;
            lastUsedCell = currentCell;
        }
        this.size = sizeOfArr;
    }

    
    private Cell makeOneCell(int index, byte[] arr, Cell leftCell) {
        byte value = arr[index];
        return new Cell(value, leftCell);
    }

    /**
     * An cell it represents the value of the cell and knows the value of it's 
     * neighboring cells.
     */
    class Cell {
        byte value;
        Cell left;
        Cell right;

        /**
         * Create a cell given a value and a left pointer.
         * @param value the value of the cell
         * @param left the cell to the left of this cell.
         * @throws IllegalArgumentException if the value is not a 0 or 1
         */
        public Cell(byte value, Cell left) {
           this(value, left, null);
        }

        /**
         * Create a cell given a value and a left pointer.
         * @param value the value of the cell
         * @param left the cell to he left of this cell.
         * @param right the cell to the right of this cell
         * @throws IllegalArgumentException if the value is not a 0 or 1
         */
        public Cell(byte value, Cell left, Cell right){
            if (value < 0 || value > 1) {
                throw new IllegalArgumentException("All values in the array must be 0s or 1s");
            }
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}