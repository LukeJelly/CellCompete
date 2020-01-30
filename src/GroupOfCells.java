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
 * group of cells, then I just traverse through the list to solve the problem.
 * 
 * //TODO: Below.
 * I think it mite actually be easier to solve this with a Deque and a Queue but
 * I will try that at a latter time. 
 * 
 * Change Log:
 * Version 1.0 Working methods with a recursive solution for compete
 * Version 2.0 Working methods with a recursive and iterative solution for compete.
 */

/**
 * A simple class to represent a group of cells and its neighbors. This class
 * was created to solve the CellCompete problem posted on Geeksforgeeks.
 * 
 * @author Luke Kelly
 * @version 2.0
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
        if (arr == null) {
            throw new IllegalArgumentException("Can not create a GroupOfCells from a null array");
        }
        if (arr.length <= 3) {
            throw new IllegalArgumentException("Must have at least three values in the array");
        }
        fillCellGroup(arr);
    }

    /**
     * Private constructor to create a new GroupOfCells from a given farLeftCell.
     * @param farLeftCell the begging, or far left cell, of your GroupOfCells
     */
    private GroupOfCells(Cell farLeftCell){
        this.farLeftCell = farLeftCell;
    }

    @Override
    /**
     * Returns a string representation of this GroupOfCells.
     */
    public String toString() {
        StringBuffer output = new StringBuffer();
        Cell p = farLeftCell;
        while (p != null) {
            output.append(p.value + ",");
            p = p.right;
        }
        // Remove the trailing ,
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }

    /**
     * A recursive solution to the Cell Compete Problem, advances the current group
     * of cells by the given amount of days.
     * 
     * @param days how many days to advance the current Group Of Cells
     * @throws IllegalArgumentException if days < 0
     */
    public void competeR(int days) {
        checkMinDays(days);
        /*
         * Disclaimer: This algorithm is very similar to the iterative solution.
         * Algorithm comments:
         * 1. Create a clone of current Group of Cells
         * 2. Create pointer to current farLeftCell in the current Group Of Cells
         *    then create a pointer to the new cloned Group of Cells
         * 3. While there the pointer to the current Group of Cells is not null
         *      i. Check if the left and right cells for the current cell are the same
         *          a. If they true set this cell to INACTIVE
         *          b. Else set this cell to ACTIVE
         *      ii. Increment both pointers to the next cell in the Group of Cells
         * 4. Set the current Group Of Cells to now have the same values as the
         *    temp group of cells. 
         * 5. Recursively call this method again but decrement the numbers of days
         */
        if (days != 0) {
            //1.
            GroupOfCells tempGroupOfCells = this.clone();
            //2.
            Cell dontChangePointer = farLeftCell;
            Cell changePointer = tempGroupOfCells.farLeftCell;
            //3(In the method) and 4.
            this.farLeftCell = iterateOneDay(dontChangePointer, changePointer);
            //5.
            competeR((--days));
        }
    }

    /**
     * A iterative solution to the Cell Compete Problem, advances the current group
     * of cells by the given amount of days.
     * 
     * @param days how many days to advance the current Group of Cells.
     * @throws IllegalArgumentException if days < 0
     */
    public void competeI(int days){
        checkMinDays(days);
        /*
         * Disclaimer: This algorithm is very similar to the recursive solution.
         * Algorithm comments:
         * 1. Create a clone of current Group of Cells
         * 2. Create pointer to current farLeftCell in the current Group Of Cells
         *    then create a pointer to the new cloned Group of Cells
         * 3. While there the pointer to the current Group of Cells is not null
         *      i. Check if the left and right cells for the current cell are the same
         *          a. If they true set this cell to INACTIVE
         *          b. Else set this cell to ACTIVE
         *      ii. Increment both pointers to the next cell in the Group of Cells
         * 4. Set the current Group Of Cells to now have the same values as the
         *    temp group of cells. 
         * 5. Loop through all the values from 0 to the number of days given. 
         * 
         * NOTE: So the method iterateOneDay would have the same algorithm marks
         * for both algorithms I made step five the loop, technically it happens 
         * first. 
         * 
         */
        
        //5.
        for(int daysCounter = 0; daysCounter < days; daysCounter++){
            //1.
            GroupOfCells tempGroupOfCells = this.clone();
            //2.
            Cell dontChangePointer = this.farLeftCell;
            Cell changePointer = tempGroupOfCells.farLeftCell;
            //3(In the method) and 4
            this.farLeftCell = iterateOneDay(dontChangePointer, changePointer);
        }
    }

    /**
     * Checks to make sure the amount of days is greater than the min.
     * @param days the amount of days that was requested to simulate. 
     */
    private void checkMinDays(int days){
        //Check if negative days.
        int MIN_DAYS = 0;
        if(days < MIN_DAYS ){
            throw new IllegalArgumentException("Can not advance group of cells by a negative amount");
        }
    }

    /**
     * Iterates the current Group of Cells by one day.
     * @param dontChangePointer the pointer pointing to the original Group of Cells
     * @param changePointer the pointer that can change with out affecting the original
     * @return a pointer to the farLeftCell of a new group of cells after they have
     * been iterated through one day.
     */
    private Cell iterateOneDay(Cell dontChangePointer, Cell changePointer){
        Cell returnedCell = changePointer;
        while(dontChangePointer != null){
            //3 i.
            if(dontChangePointer.LandRAreSame()){
                //3 i a.
                changePointer.value = INACTIVE;
            }else{
                //3 i b.
                changePointer.value = ACTIVE;
            }
            //3 ii.
            dontChangePointer = dontChangePointer.right;
            changePointer = changePointer.right;
        }
        //4.
        return returnedCell;
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
     * Clones the current Group Of Cells
     * @return a new instance of the current Group Of Cells
     */
    public GroupOfCells clone(){
        Cell p = farLeftCell;
        Cell newGPC = p.clone();
        p = p.right;
        Cell lastCell = newGPC;
        Cell currentCell;
        while(p != null){
            currentCell = p.clone();
            currentCell.left = lastCell;
            lastCell.right = currentCell;
            lastCell = currentCell;
            p = p.right;
        }
        return new GroupOfCells(newGPC);
    }

    /**
     * Used by the constructor to create a Group Of Cells from a given array of 
     * cells.Preconditions of the constructor must be checked before calling this 
     * method.
     * @param arr the array that represents Group of Cells. 
     */
    private void fillCellGroup(byte[] arr) {
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

    /**
     * Makes a single cell given the value grabbed from the array at the index 
     * given.
     * 
     * @return a Cell with a value at the given index of the array
     * @throws IllegalArgumentException if the value is not a 0 or 1
     */
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

        /**
         * Clones the current cell
         * @return a new instance of this cell.
         */
        public Cell clone(){
            return new Cell(this.value, this.left, this.right);
        }

        /**
         * Checks if the left and right cells of this cell are the same, 
         * either both inactive or both active.
         * @return returns true if they are both the same.
         */
        public boolean LandRAreSame() {
            /* 
             * If the left or right cell is null that means it is always INACTIVE
             * According to the prompt
             */
            byte leftValue = (left == null) ? INACTIVE : left.value;
            byte rightValue = (right == null) ? INACTIVE : right.value;

            return (leftValue == rightValue);
        }
    }
}