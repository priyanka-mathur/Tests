import java.util.Scanner;

public class Compress {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        scanner.close();
        System.out.println("Compressed string: " + compressString(s1));
    }

    static String compressString(String s) {
        int countConsecutive=1;
        char prevChar=s.charAt(0), currChar;
        StringBuilder compressedString = new StringBuilder();
        for(int i=1; i<s.length(); i++) {
            currChar = s.charAt(i);
            if(currChar == prevChar) {
                countConsecutive++;
            } else {
                compressedString.append(Character.toString(prevChar) + Integer.toString(countConsecutive));
                countConsecutive=1;
            }
            prevChar = currChar;
        }
        compressedString.append(Character.toString(prevChar) + Integer.toString(countConsecutive));
        if(s.length() < compressedString.length()) {
            return s;
        }
        return compressedString.toString();
    }
}
