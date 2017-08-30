import java.util.Scanner;

public class OneAway {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();

        scanner.close();

        System.out.println("One Away: " + oneEditAway(s1.toLowerCase(), s2.toLowerCase()));
    }

    static boolean oneEditAway(String s1, String s2) {
        if(oneInsertionAway(s1, s2) || oneInsertionAway(s2, s1) || oneReplacementAway(s1, s2)) {
            return true;
        }
        return false;
    }

    static boolean oneInsertionAway(String s1, String s2) {
        if(s1.length()-s2.length() != -1) {
            return false;
        }
        int charFrequency[] = new int[26], val, count =0;

        for(int i=0; i<s2.length(); i++) {
            if(i<s1.length()) {
                val = s1.charAt(i) - (int)'a';
                charFrequency[val]++;
            }
            val = s2.charAt(i) - (int)'a';
            charFrequency[val]--;
        }
        for(int i =0; i<26; i++) {
            if(charFrequency[i] != -1 && charFrequency[i] != 0) {
                return false;
            }
            else if(charFrequency[i] == -1) {
                count++;
                if(count>1) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean oneReplacementAway(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }
        int charFrequency[] = new int[26], val, minusOne = 0, plusOne = 0;
        for(int i=0; i<s2.length(); i++) {
            val = s1.charAt(i) - (int)'a';
            charFrequency[val]++;
            val = s2.charAt(i) - (int)'a';
            charFrequency[val]--;
        }

        for(int i=0; i<26; i++) {
            if(Math.abs(charFrequency[i]) != 1 && charFrequency[i] != 0) {
                return false;
            }
            else if(charFrequency[i] == -1) {
                minusOne++;
                if(minusOne>1) {
                    return false;
                }
            } else if(charFrequency[i] == 1) {
                plusOne++;
                if(plusOne>1) {
                    return false;
                }
            }
        }
        if(plusOne == minusOne) {
            return true;
        }
        return false;
    }

}
