/*
 * The Prompt:
 * Given a binary array of size n where n > 3. A true (or 1) value in the array 
 * means active and false (or 0) means inactive. Given a number k, the task is 
 * to find count of active and inactive cells after k days. After every day, 
 * status of i’th cell becomes active if left and right cells are not same and 
 * inactive if left and right cell are same (both 0 or both 1).
 * Since there are no cells before leftmost and after rightmost cells, the value
 * cells before leftmost and after rightmost cells is always considered as 0 (or inactive).
 */

/**
 * A simple class to represent a group of cells and its neighbors.
 * 
 * @author Luke Kelly
 * @version 0.1
 */
public class GroupOfCells {
    private Cell farLeftCell;
    private int size;

    public GroupOfCells(byte[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Can not create a GroupOfCells from a null array");
        }
        if (arr.length < 3) {
            throw new IllegalArgumentException("Must have atleast three values in the array");
        }
        fillCellGroup(arr);
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();
        Cell p = farLeftCell;
        while (p != null) {
            output.append(p.value + ",");
            p = p.right;
        }
        // Remove trailing ,
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }


    public void competeR(int days){
        if(days != 0){
            
        }
    }

    public byte[] getArrayOfCells(){
        byte[] outputArr = new byte[this.size];
        Cell p = farLeftCell;
        for (int i = 0; i < size; i++) {
            outputArr[i] = p.value;
            p = p.right;
        }
        return outputArr;
    }

    private void fillCellGroup(byte[] arr) {
        Cell lastUsedCell;
        Cell currentCell;
        int sizeOfArr = arr.length;
        int i = 0;
        lastUsedCell = makeOneCell(i, arr);
        i++;
        farLeftCell = lastUsedCell;
        for (; i < sizeOfArr; i++) {
            currentCell = makeOneCell(i, arr);
            lastUsedCell.right = currentCell;
            lastUsedCell = currentCell;
        }
        this.size = sizeOfArr;
    }

    private Cell makeOneCell(int index, byte[] arr) {
        byte value = arr[index];
        if (value < 0 || value > 1) {
            throw new IllegalArgumentException("All values in the array must be 0s or 1s");
        }
        return new Cell(value, null);
    }

    class Cell {
        byte value;
        Cell left;
        Cell right;

        public Cell(byte value, Cell left) {
            this.value = value;
            this.left = left;
        }

        public void mutate() {
            value ^= 1;
        }

        public boolean areLandRtheSame() {
            byte leftValue = (left == null)? 0 : left.value;
            byte rightValue = (right == null)? 0 : right.value;
            
            return(leftValue == rightValue);
        }
    }
}