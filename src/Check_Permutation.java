import java.util.Scanner;

public class Check_Permutation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        scanner.close();
        System.out.println("Strings are permutation: " + isPermutation(s1, s2));
    }

    static boolean isPermutation(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }

        int chars1[] = new int[128];

        for(int i = 0; i<s1.length(); i++) {
            int val = (int) s1.charAt(i);
            chars1[val]++;
        }

        for(int i = 0; i<s2.length(); i++) {
            int val = (int) s2.charAt(i);
            chars1[val]--;
            if(chars1[val] < 0) {
                return false;
            }
        }
        return true;

    }
}
