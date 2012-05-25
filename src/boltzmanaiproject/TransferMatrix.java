
package boltzmanaiproject;

/**
 * Generates the transfer matrix.
 *
 * @author Kaleb Kircher
 */
public class TransferMatrix
{
    /**
     * Generates The transfer matrix based on the new weights.
     *
     * @param newWS the new weights.
     * @return An array containing the transfer matrix.
     */
    public double[][] generateNewMatrix(double[][][] newWS)
    {
        // array to store the transfer function
        double[][] trans = new double[newWS.length][newWS.length];
        double sum = 0;

        for (int i = 0; i < newWS.length; i++)
        {
            // find how many bits it takes to represent the number of states
            for (int j = 0; j < Math.ceil(Math.log(newWS.length) / Math.log(2)); j++)
            {
                // take j and and it with each bit in the state number to 
                // check what digit it is transfering on 0 to 1
                if (((i & (1 << j)) == 0))
                {
                    trans[i][i + (1 << j)] = newWS[i][newWS[i].length - 1 - j][2];
                }
                // take j and and it with each bit in the state number to
                // check what digit it is transfering on 1 to 0 
                else
                {
                    trans[i][i - (1 << j)] = newWS[i][newWS[i].length - 1 - j][3];
                }
            }
            sum = 0;

            // fill in the diagnols (the states go to themselves)
            for (int j = 0; j < newWS.length; j++)
            {
                sum += trans[i][j];
            }
            trans[i][i] = 1 - sum;
        }

        System.out.println();
        System.out.println("Transfer Matrix ");
        for (int i = 0; i < trans.length; i++)
        {
            for (int j = 0; j < trans[0].length; j++)
            {
                System.out.print(" " + trans[i][j]);
            }
            System.out.println();
        }
        return trans;
    }
}
