public class ZeroMatrix {
    public static void main(String[] args) {
        int M= 4, N = 5;
        int matrix[][] = {  {1,     2,      3,      4,      5},
                            {6,     0,      8,      0,      10},
                            {11,    12,     13,     14,     15},
                            {16,    0,     18,     0,     20}};

        setZeros(matrix);
        printMatrix(matrix);
    }

    static void setZeros(int[][] matrix) {
        int numOfRows = matrix.length,
                numOfCols = matrix[0].length;
        boolean row[] = new boolean[numOfRows],
                column[] = new boolean[numOfCols];

        for(int i =0; i<numOfRows; i++) {
            for(int j =0; j<numOfCols; j++) {
                if(matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        for(int i =0; i<row.length; i++) {
            if(row[i]) nullifyRow(matrix, i);
        }

        for(int j =0; j<column.length; j++) {
            if(column[j]) nullifyColumn(matrix, j);
        }
    }

    static void nullifyRow(int[][] matrix, int row) {
        for(int j=0;j<matrix[0].length;j++) {
            matrix[row][j] =0;
        }
    }

    static void nullifyColumn(int[][] matrix, int col) {
        for(int i=0; i<matrix.length; i++) {
            matrix[i][col] =0;
        }
    }

    static void printMatrix(int m[][]) {
        for(int i = 0; i<m.length; i++) {
            for (int j = 0; j<m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

}
