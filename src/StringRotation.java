import java.util.Scanner;

public class StringRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        scanner.close();
        System.out.println("Strings are rotation: " + isRotation(s1, s2));
    }

    static boolean isRotation(String s1, String s2) {

        if(s1.length()!=s2.length() || s1.isEmpty()) {
            return false;
        }
        String s= s1+s1;
        if(s.contains(s2)) {
            return true;
        }
        return false;
    }
}
