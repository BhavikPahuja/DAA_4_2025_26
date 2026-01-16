public class RecurranceRelation {

    private static int i = 0;
    private static int d = 0;

    static void complexRec(int n, int dpth) throws InterruptedException {

        i++;
        d = Math.max(d, dpth);

        if (n <= 2) {
            return;
        }

        Thread.sleep(100);

        int p = n;
        while (p > 0) {
            int[] temp = new int[n];
            for (int i = 0; i < n; i++) {
                temp[i] = i ^ p;
            }
            p >>= 1;
        }

        int[] small = new int[n];
        for (int i = 0; i < n; i++) {
            small[i] = i * i;
        }

        reverseArray(small);

        complexRec(n / 2, dpth + 1);
        complexRec(n / 2, dpth + 1);
        complexRec(n / 2, dpth + 1);

    }

    private static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        complexRec(16, 1);
        long endTime = System.currentTimeMillis();
        System.out.println("Number of operations: " + i);
        System.out.println("Depth of the tree: " + d);
        System.out.println("Execution time is: " + (endTime - startTime));
    }

}

// Depth = log(n)
// T(n) = 3T(n/2) + nLog(n)
// final complexity = O(n^(log(base: 2)3))