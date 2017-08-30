import java.util.Scanner;

public class Unique_Characters {
    public static void main(String[] args) {
        String s = readString();

        System.out.println("String has unique characters: " + isUniqueChars(s));
    }

    static String readString() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    static boolean isUniqueChars(String s) {
        if(s.length() > 128) {
            return false;
        }
        boolean chars[] = new boolean[128];
        for(int i =0; i<s.length(); i++) {
            int val = s.charAt(i);
            if(chars[val]) {
                return false;
            }
            chars[val] = true;
        }
        return true;
    }
}
