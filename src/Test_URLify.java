import java.util.Scanner;

public class Test_URLify {

    public static void main(String[] args) {
        char s[] = "Mr John Smith is       ".toCharArray();
        int trueLength = 16;
        System.out.println("URL-ified String: " + URLify(s, trueLength));
    }

    static String URLify(char[] s, int trueLength) {
        char s_copy[] = new char[s.length];
        int j =0;
        for(int i=0; i < trueLength; i++) {
            if(s[i] == ' ') {
                s_copy[++j] = '%';
                s_copy[++j] = '2';
                s_copy[++j] = '0';
            } else {
                s_copy[++j] = s[i];
            }

        }
        return new String(s_copy);
    }
}
