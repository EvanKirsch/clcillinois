import java.util.Arrays;

public class MaximumFinder {

    public static void main(String[] args) {
        int[] myArray = {10, 3, 4, 20, 6, 8};
        System.out.println("My Array: " + Arrays.toString(myArray));
        int myFoundMax = findMaximum(myArray);
        System.out.println("Found Max: " + myFoundMax);
    }

    public static int findMaximum(int[] myArray) {
        int myMax = Integer.MIN_VALUE;
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i] > myMax) {
                System.out.println("I found: " + myArray[i] + " to be greater than " + myMax + ". The new max: " + myArray[i]);
                myMax = myArray[i];
            } else {
                System.out.println("I found: " + myArray[i] + " to be less than " + myMax + ". The max will remain: " + myMax);
            }
        }
        return myMax;
    }

}