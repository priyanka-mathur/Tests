import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        scan.nextLine();
        int[] arr = new int[size];
        for(int i =0; i<size; i++) {
            arr[i] = scan.nextInt();
        }

        sort(arr);
        System.out.println("Sorted array");
        for(int i =0; i<size; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void sort(int[] A) {
        if(A.length < 2) {
            return;
        }
        int size = A.length;
        int mid = size/2;
        int[] L = new int[mid];
        int[] R = new int[size-mid];
        for(int i = 0; i < mid; i++) {
            L[i] = A[i];
        }
        for(int i = mid; i<A.length; i++) {
            R[i-mid] = A[i];
        }

        sort(L);
        sort(R);
        merge(L, R, A);
    }

    static void merge(int[] L, int[] R, int[] A) {
        int Lsize = L.length;
        int Rsize = R.length;
        int i = 0, j = 0, k = 0;

        while(i<Lsize && j<Rsize) {
            if(L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
            k++;
        }
        while(i<Lsize) {
            A[k] = L[i];
            i++;
            k++;
        }
        while(j<Rsize) {
            A[k] = R[j];
            j++;
            k++;
        }
    }
}
