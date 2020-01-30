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
    public void testGetArrayOfCells(){
        GroupOfCells test = new GroupOfCells(original);
        byte[] actual = test.getArrayOfCells();
        if(!Arrays.equals(original, actual)){
            fail("The two arrays were not the same");
        }
    }

    @Test
    public void testCaseOneDay(){
        int days = 1;
        byte[] expected = {0,1,0,0,1,0,1,0};
        runTest(days, expected, this.original);
    }

    @Test
    public void testCaseTwoDays(){
        byte[] originalDiffPermutation = {1,1,1,0,1,1,1,1};
        int days = 2;
        byte[] expected = {0,0,0,0,0,1,1,0};
        runTest(days, expected, originalDiffPermutation);
    }

    private void runTest(int days, byte[] expected, byte[] source){
        GroupOfCells testedGroup = new GroupOfCells(source);
        byte[] actual = runCompeteR(days, testedGroup);
        if(!Arrays.equals(expected, actual)){
            fail("The expected array did not equal the actual array");
        }
    }
    
    private byte[] runCompeteR(int days, GroupOfCells GPC){
        GPC.competeR(days);
        return GPC.getArrayOfCells();
    }
}