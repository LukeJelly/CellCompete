import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;


/**
 * Test class for CellCompete.
 * 
 * @author Luke Kelly
 * @version 1.0
 */
public class TestCellCompete {
    byte[] original = {1,0,0,0,0,1,0,0};

    @Test(expected = IllegalArgumentException.class)
    public void checkEmptyArray(){
        new GroupOfCells(new byte[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNullArray(){
        new GroupOfCells(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayToSmall(){
        byte[] badArr = {1,0,0};
        new GroupOfCells(badArr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompeteNegativeDays(){
        GroupOfCells test = new GroupOfCells(original);
        test.competeR(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonBinaryDigitInArray(){
        byte[] badArr = {1,0,3,0,0,1};
        new GroupOfCells(badArr);
    }

    @Test
    public void testToString(){
        GroupOfCells test = new GroupOfCells(original);
        String actual = test.toString();
        String expected = "1,0,0,0,0,1,0,0";
        assertEquals(expected, actual);
    }

    
    @Test
    public void testToString2(){
        byte[] diffArray = {1,0,0,0,0,1,0,1};
        GroupOfCells test = new GroupOfCells(diffArray);
        String actual = test.toString();
        String expected = "1,0,0,0,0,1,0,1";
        assertEquals(expected, actual);
    }

    @Test
    public void testGetArrayOfCells(){
        GroupOfCells test = new GroupOfCells(original);
        byte[] actual = test.getArrayOfCells();
        if(!Arrays.equals(original, actual)){
            fail("The two arrays were not the same");
        }
    }

    @Test
    public void testCaseOneDayRecursiveSolution(){
        int days = 1;
        byte[] expected = {0,1,0,0,1,0,1,0};
        runTestRecursiveSolution(days, expected, this.original);
    }

    @Test
    public void testCaseTwoDaysRecursiveSolution(){
        byte[] originalDiffPermutation = {1,1,1,0,1,1,1,1};
        int days = 2;
        byte[] expected = {0,0,0,0,0,1,1,0};
        runTestRecursiveSolution(days, expected, originalDiffPermutation);
    }

    @Test
    public void testCaseOneDayIterativeSolution(){
        int days = 1;
        byte[] expected = {0,1,0,0,1,0,1,0}; 
        runTestIterativeSolution(days, expected, this.original);
    }

    @Test
    public void testCaseTwoDaysIterativeSolution(){
        byte[] originalDiffPermutation = {1,1,1,0,1,1,1,1};
        int days = 2;
        byte[] expected = {0,0,0,0,0,1,1,0};
        runTestIterativeSolution(days, expected, originalDiffPermutation);
    }

    private void runTestRecursiveSolution(int days, byte[] expected, byte[] source){
        GroupOfCells testedGroup = new GroupOfCells(source);
        byte[] actual = runCompeteRecursiveSolution(days, testedGroup);
        checkArraysEqual(expected, actual);

    }

    private void runTestIterativeSolution(int days, byte[] expected, byte[] source){
        GroupOfCells testGroup = new GroupOfCells(source);
        byte[] actual = runCompeteIterativeSolution(days, testGroup);
        checkArraysEqual(expected, actual);
    }
    
    private byte[] runCompeteRecursiveSolution(int days, GroupOfCells GPC){
        GPC.competeR(days);
        return GPC.getArrayOfCells();
    }

    private byte[] runCompeteIterativeSolution(int days, GroupOfCells GPC){
        GPC.competeI(days);
        return GPC.getArrayOfCells();
    }

    private void checkArraysEqual(byte[] expected, byte[] actual){
        if(!Arrays.equals(expected, actual)){
            fail("The expected array did not equal the actual array");
        }
    }


}