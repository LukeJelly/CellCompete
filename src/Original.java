import java.util.*;

public class Original{
    public static void main(String[] args) {
        System.out.println("NonRecursive");
        double startTime = System.nanoTime();
        testNonRecursive();
        double endTime = System.nanoTime();
        double timeElapsed = endTime - startTime;
        System.out.println("Time Elapsed(NonRecursive) in nanoseconds: " + timeElapsed);
        System.out.println("Time Elapsed(NonRecursive) in miliseconds: " + (timeElapsed/1000000));

        System.out.println("Recursive");
        double startTimeRecursive = System.nanoTime();
        testRecursive(); 
        double endTimeRecursive = System.nanoTime();
        double timeElapsedRecursive = endTimeRecursive - startTimeRecursive;
        System.out.println("Time Elapsed(Recursive) in nanoseconds: " + timeElapsedRecursive);
        System.out.println("Time Elapsed(Recursive) in miliseconds: " + (timeElapsedRecursive/1000000));
        
    }

    private static void testNonRecursive(){
        int[] one = {1,0,0,0,0,1,0,0};
        int[] two = {1,1,1,0,1,1,1,1};
        cellCompeteNonRecrusive(one, 1);
        cellCompeteNonRecrusive(two, 2);
        // cellCompeteNonRecrusive(two, 10000);
        System.out.println(Arrays.toString(one));
        System.out.println(Arrays.toString(two));
    }

    private static void testRecursive(){
        int[] one = {1,0,0,0,0,1,0,0};
        int[] two = {1,1,1,0,1,1,1,1};
        cellCompete(one, 1);
        cellCompete(two, 2);
        // cellCompete(two, 10000);
        System.out.println(Arrays.toString(one));
        System.out.println(Arrays.toString(two));
    }

    public static void cellCompete(int[] arr, int days){
        int[] arrChanged = cellCompeteHelper(arr, days);
        for(int c = 0; c < arr.length; c++){
            arr[c] = arrChanged[c];
        }
    }


    public static int[] cellCompeteHelper(int[] arr, int days){
        if(days == 0){
            return arr;
        }
        int[] arrChanged = new int[8];
        for(int i = 0; i < arr.length; i++){
            if(i == 0){//Check first instance
                if(arr[i+1] == 0){
                    arrChanged[i] = 0;
                }else{
                    arrChanged[i] = 1;
                }
            }else if(i==arr.length-1){//Check last instance
                if(arr[i-1]==0){
                    arrChanged[i] = 0;
                }else{
                    arrChanged[i] = 1;
                }
            }else{//Check all the middle cases
                if(arr[i-1]==arr[i+1]){
                    arrChanged[i] = 0; 
                }else{
                    arrChanged[i] = 1;
                }
            }
        }
        return cellCompeteHelper(arrChanged, days-1);
    }

    public static void cellCompeteNonRecrusive(int[] arr, int days){
        int[] arrChanged = new int[8];
        for(int outer = 0; outer < days; outer++){
            for(int i = 0; i < arr.length; i++){
                if(i == 0){//Check first instance
                    if(arr[i+1] == 0){
                        arrChanged[i] = 0;
                    }else{
                        arrChanged[i] = 1;
                    }
                }else if(i==arr.length-1){//Check last instance
                    if(arr[i-1]==0){
                        arrChanged[i] = 0;
                    }else{
                        arrChanged[i] = 1;
                    }
                }else{//Check all the middle cases
                    if(arr[i-1]==arr[i+1]){
                        arrChanged[i] = 0; 
                    }else{
                        arrChanged[i] = 1;
                    }
                }
            }
            for(int c = 0; c < arr.length; c++){
                arr[c] = arrChanged[c];
            }
        }
    }
}