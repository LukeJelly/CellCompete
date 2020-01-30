import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;


/**
 * Test class for CellCompete.
 * 
 * @author Luke Kelly
 * @version 0.1
 */
public class TestCellCompete {
    @Test(expected = IllegalArgumentException.class)
    public void checkEmptyArray(){
        new GroupOfCells(new byte[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNulltyArray(){
        new GroupOfCells(null);
    }

    @Test
    public void testToString(){
        byte[] original = {1,1,1,0,1,1,1,1};
        GroupOfCells test = new GroupOfCells(original);
        String actual = test.toString();
        String expected = "1,1,1,0,1,1,1,1";
        assertEquals(expected, actual);
    }

    @Test
    public void testGetArrayOfCells(){
        byte[] expected = {1,1,1,0,1,1,1,1};
        GroupOfCells test = new GroupOfCells(expected);
        byte[] actual = test.getArrayOfCells();
        if(!Arrays.equals(expected, actual)){
            fail("The two arrays were not the same");
        }
    }

    @Test
    public void testCaseOneDay(){
        byte[] original = {1,0,0,0,0,1,0,0};
        int days = 1;
        byte[] expected = {0,1,0,0,1,0,1,0};
        runTest(days, expected, original);
    }

    @Test
    public void testCaseTwoDays(){
        byte[] original = {1,1,1,0,1,1,1,1};
        int days = 2;
        byte[] expected = {0,0,0,0,0,1,1,0};
        runTest(days, expected, original);
    }

    private void runTest(int days, byte[] expected, byte[] original){
        GroupOfCells testedGroup = new GroupOfCells(original);
        byte[] actual = runCompeteR(days, testedGroup);
        assertEquals(expected, actual);
    }
    
    private byte[] runCompeteR(int days, GroupOfCells GPC){
        GPC.competeR(days);
        return GPC.getArrayOfCells();
    }
}