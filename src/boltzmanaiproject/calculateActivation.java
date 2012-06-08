package boltzmanaiproject;

/**
 * Converts the activations via a conversion table.
 *
 * @author Kaleb Kircher
 */
public class CalculateActivation
{
    /**
     * Finds the activations and looks up their conversion based on temperature.
     *
     * @param ws a 2-d array containing the weighted sums minus the thresholds.
     * @param temperature a integer for the temperature to begin simulated
     * annealing at.
     * @return a 2-d array containing the new weights
     */
    public double[][] conversionMatrix(double[][] ws, double temperature)
    {
        // three dimensional array to assign the weights
        double[][] activations = new double[ws.length][ws[0].length];

        // 2-d array to to hold the activations
        double[][] weightedStates = new double[ws.length][ws[0].length];

        // transfer the weights array to the activation array
        for (int i = 0; i < ws.length; i++)
        {
            for (int j = 0; j < ws[i].length; j++)
            {
                weightedStates[i][j] = ws[i][j];
            }
        }

        // look up the converstions for the activations and assign them to the new weights array
        for (int i = 0; i < ws.length; i++)
        {
            for (int j = 0; j < ws[i].length; j++)
            {
                // probability that the cell state goes to 1 => Pij = 1/(1 + e^-Aij/T)
                // where Ai is equal to the i,jth element from the result
                // of the (W*S) - T (T = cell threshold) calculations. The T that Aij is
                // divided by is Temperature.
                activations[i][j] = 1 / (1 + (Math.exp((-weightedStates[i][j]) / temperature)));
            }
        }

        printMatrix(activations);

        return activations;
    }

    private void printMatrix(double[][] matrix)
    {
        System.out.println();
        System.out.println("The new WS Matrix: ");

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
