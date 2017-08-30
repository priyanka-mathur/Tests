import java.util.Scanner;

public class TurnIntoPermutation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();

        int bitVector = updateCharacterFrequency(s.toLowerCase());
        System.out.println("Can be turned into palindrome: " + maxOneBitSet(bitVector));
    }

    static int getCharNumber(char c) {
        int a_val = (int) 'a';
        int z_val = (int) 'z';
        int c_val = (int) c;
        if(c_val > a_val && c_val <= z_val) {
            return c_val - a_val;
        }
        return -1;
    }

    static int updateCharacterFrequency(String s) {
        int bitVector = 0, index;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            index = getCharNumber(c);
            if(index!=-1) {
                int mask = 1 << index;
                if ((mask & bitVector) == 0) { // the bit at index is not set
                    bitVector = bitVector | mask;
                } else { // the bit at index is set
                    bitVector = bitVector & ~mask;
                }
            }
        }
        return bitVector;
    }

    static boolean maxOneBitSet(int bitVector) {
        if(((bitVector-1)&bitVector) == 0) {
         return true;
        }
        return false;
    }

}
