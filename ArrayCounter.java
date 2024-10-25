public class ArrayCounter {
    public static int[] populateNewArray(int[] inputArray) {
        int[] resultArray = new int[4]; // Array to store the counts

        for (int value : inputArray) {
            if (value >= 0 && value <= 10) {
                resultArray[0]++;
            } else if (value >= 11 && value <= 20) {
                resultArray[1]++;
            } else if (value >= 21 && value <= 30) {
                resultArray[2]++;
            } else {
                resultArray[3]++;
            }
        }

        return resultArray;
    }

    public static void main(String[] args) {
        int[] inputArray = {3, 7, 15, 22, 29, 31, 10, 20, 21, 50, 0};
        int[] resultArray = populateNewArray(inputArray);

        System.out.println("Counts: ");
        for (int i = 0; i < resultArray.length; i++) {
            System.out.println("Slot " + i + ": " + resultArray[i]);
        }
    }
}
