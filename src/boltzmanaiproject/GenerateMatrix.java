package boltzmanaiproject;

/**
 * A class that produces the activations matrix.
 *
 * @author Kaleb Kircher
 */
public class GenerateMatrix
{
    private double[][] ws = new double[3][8];

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

        // get the length of the weight matrix
        int x = w.length;

        // loop that prints the weight matrix
        System.out.println("Matrix 1 : ");
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < x; j++)
            {
                System.out.print(" " + w[i][j]);
            }
            System.out.println();
        }

        // grabs the length of the rows for the outcomes
        int y = outcomes.length;

        // grabs the length of the colums for the outcomes
        int z = outcomes[0].length;

        // loop to print second matrix
        System.out.println("Matrix 2 : ");
        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < z; j++)
            {
                System.out.print(" " + outcomes[i][j]);
            }
            System.out.println();
        }

        // multiply the weights by the outcomes
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < z; j++)
            {
                for (int k = 0; k < w[0].length; k++)
                {

                    ws[i][j] += w[i][k] * outcomes[k][j];
                }
            }
        }

        System.out.println("Multiply of both matrix : ");

        // print the new WS matrix
        for (int i = 0; i < ws.length; i++)
        {
            for (int j = 0; j < ws[0].length; j++)
            {
                System.out.print(" " + ws[i][j]);
            }
            System.out.println();
        }

        for (int i = 0; i < ws.length; i++)
        {
            for (int j = 0; j < ws[0].length; j++)
            {
                ws[i][j] = ws[i][j] - threshold[i][0];
            }
        }

        System.out.println();
        System.out.println("The new WS Matrix is: ");

        // print the new WS matrix
        for (int i = 0; i < ws.length; i++)
        {
            for (int j = 0; j < ws[0].length; j++)
            {
                System.out.print(" " + ws[i][j]);
            }
            System.out.println();
        }

        return ws;
    }
}
