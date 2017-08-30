import java.util.Scanner;

public class RotateMatrix {
    public static void main(String[] args) {
        int N = 5;
        int matrix[][] = {  {1,     2,      3,      4,      5},
                            {6,     7,      8,      9,      10},
                            {11,    12,     13,     14,     15},
                            {16,    17,     18,     19,     20},
                            {21,    22,     23,     24,     25}};
        int layers = N/2, layerEnd = N-1;
        for(int i=0; i<layers; i++) {
/*            0 0     0 1      0 2      0 3       0 4
             |
            4 0     3 0      2 0      1 0       0 0
             |
            4 4     4 3      4 2      4 1       4 0
             |
            0 4     1 4      2 4      3 4       4 4 */

            for(int k = i; k<layerEnd; k++) {

                int temp = matrix[i][k]; //save top

                matrix[i][k] = matrix[layerEnd-k+i][i];  //top = left

                matrix[layerEnd-k+i][i] = matrix[layerEnd][layerEnd-k+i];//left = bottom

                matrix[layerEnd][layerEnd-k+i] = matrix[k][layerEnd]; //bottom = right

                matrix[k][layerEnd] = temp; //right = top
            }
            layerEnd--;
        }

        printMatrix(matrix);

    }

    static void printMatrix(int m[][]) {
        for(int i = 0; i<m.length; i++) {
            for (int j = 0; j<m.length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }


}
