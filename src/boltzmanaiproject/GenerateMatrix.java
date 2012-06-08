package boltzmanaiproject;

/**
 * A class that produces the activations matrix.
 *
 * @author Kaleb Kircher
 */
public class GenerateMatrix
{
    private double[][] ws;

    /**
     * Multiplies the weights by the outputs and then subtracts the thresholds.
     *
     * @param w a 2-d array for the weights
     * @param outcomes a 2-d array for the outcomes
     * @param threshold a 2-d array for the threshold
     * @return a 2-d array containing the activations
     */
    public double[][] generateMatrix(double[][] w, int[][] outcomes, double[][] threshold)
    {

        // print the weight matrix
        System.out.println("Weight Matrix: ");
        printMatrix(w);

        // loop to print second matrix
        System.out.println("Outcomes Matrix: ");
        printMatrix(outcomes);

        // Multiply the weight matrix by the possible states (outcomes matrix).
        this.ws = multiplyMatrix(w, outcomes);

        // print the new WS matrix
        System.out.println("W*S Matrix: ");
        printMatrix(ws);

        // Subtract the Threshold from W*S to create (W*S) - T
        subtractMatrix(ws,threshold);
  
        System.out.println();
        System.out.println("The new ((W*S) - T) Matrix is: ");

        // print the new WS-T matrix
        printMatrix(ws);

        return ws;
    }

    /**
     * Multiply two matrices.
     * @param a the first matrix
     * @param b the second matrix
     * @return the result of the multiplication.
     */
    public double[][] multiplyMatrix(double[][] a, int[][] b)
    {
        double[][] result = new double[a[0].length][b[0].length];

        // multiply the weights by the outcomes
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < b[0].length; j++)
            {
                for (int k = 0; k < a[0].length; k++)
                {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    /**
     * Subtract matrix b from matrix a.
     * @param a the matrix to be subtracted from
     * @param b the matrix that will be substracted by
     * @return the result of the subtracted matrix.
     */
    public double[][] subtractMatrix(double[][] a, double[][] b)
    {
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a[0].length; j++)
            {
                a[i][j] = a[i][j] - b[i][0];
            }
        }
        return a;
    }

    private void printMatrix(double[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
    }

    private void printMatrix(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
    }
}
