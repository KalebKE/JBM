package boltzmanaiproject.markov;

/**
 * Converts the activations via a conversion table.
 *
 * @author Kaleb Kircher
 */
public class MarkovConversionMatrix
{
    /**
     * Finds the activations and looks up their conversion based on temperature.
     *
     * @param ws a 2-d array containing the weights
     * @param temperature a integer for the temperature
     * @return a 2-d array containing the new weights
     */
    public double[][][] conversionMatrix(double[][] ws, double temperature)
    {
       
        // three dimensional array to assign the weights
        double[][][] newWS = new double[ws[0].length][ws.length][4];

        // 2-d array to to hold the activations
        double[][] activations = new double[ws.length][ws[0].length];

        // transfer the weights array to the activation array
        for (int i = 0; i < ws.length; i++)
        {
            for (int j = 0; j < ws[0].length; j++)
            {
                activations[i][j] = (double) (ws[i][j]);
            }
        }

        // look up the converstions for the activations and assign them to the new weights array
        for (int i = 0; i < ws[0].length; i++)
        {
            for (int j = 0; j < ws.length; j++)
            {
                // probability that the cell state goes to 1 => Pij = 1/(1 + e^-Aij/T)
                // where Ai is equal to the i,jth element from the result
                // of the (W*S) - T (T = cell threshold) calculations. The T that Aij is
                // divided by is Temperature.
                newWS[i][j][0] = 1 / (1 + (Math.exp((-activations[j][i]) / temperature)));
                // probability that the cell state goes to 0 => 1 -Pij.
                newWS[i][j][1] = 1 - newWS[i][j][0];
                // Pij divided by the number of cell
                // connections
                newWS[i][j][2] = newWS[i][j][0] / newWS[0].length;
                // 1 - Pij divided by the number of cell
                // connections
                newWS[i][j][3] = newWS[i][j][1] / newWS[0].length;
            }
        }

        System.out.println();
        System.out.println("The new WS Matrixs: ");

        // print the new WS matrix
        for (int i = 0; i < newWS.length; i++)
        {
            System.out.println(" Matrix #" + i);
            for (int j = 0; j < newWS[0].length; j++)
            {
                for (int k = 0; k < newWS[0][0].length; k++)
                {
                    System.out.print(" " + newWS[i][j][k]);
                }
                System.out.println();
            }
        }


        return newWS;
    }
}
