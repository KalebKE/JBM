package boltzmanaiproject;

/**
 * Generates the transfer matrix.
 *
 * @author Kaleb Kircher
 */
public class GenerateTransitionMatrix
{
    /**
     * Generates The transfer matrix based on the new weights.
     *
     * @param transferProbabilites
     * @return An array containing the transfer matrix.
     */
    public double[][] generateNewMatrix(double[][] transferProbabilites)
    {
        // Probabilities for transfering from 0 to 1
        // We need to transpose this matrix
        double[][] probTransfer1to0 = Transpose.tranposeMatrix(transferProbabilites);
        // Probabilities for transfering from 1 to 0
        double[][] probTransfer0to1 = new double[probTransfer1to0.length][probTransfer1to0[0].length];

        //System.out.println();
        //System.out.println("Boltzman Activation scaled by number of cells: " + probTransfer1to0[0].length);

        // The probabilities from transfering from 1 to 0 are 1 minus the
        // probability of going from 0 to 1.
        // We also need to scale the probability by the number of connections
        // so we can use it with our Makrov system.
        for (int i = 0; i < probTransfer1to0.length; i++)
        {
            for (int j = 0; j < probTransfer1to0[i].length; j++)
            {
                // 1 - probability of going from 1 to 0
                probTransfer0to1[i][j] = 1 - probTransfer1to0[i][j];
                // Now scale by dividing by the number of connections
                probTransfer0to1[i][j] = probTransfer0to1[i][j] / probTransfer0to1[i].length;
            }
        }

        // We also need to scale the probability by the number of connections
        // so we can use it with our Makrov system.
        for (int i = 0; i < probTransfer1to0.length; i++)
        {
            for (int j = 0; j < probTransfer1to0[i].length; j++)
            {
                // Now scale by dividing by the number of connections
                probTransfer1to0[i][j] = probTransfer1to0[i][j] / probTransfer1to0[i].length;
            }
        }

        //printMatrix(probTransfer1to0);

        // array to store the transfer function
        double[][] trans = new double[probTransfer1to0.length][probTransfer1to0.length];
        double sum = 0;

        // Algorithm to place the transition probabilities into a Markov
        // System. The tranfer probability from each state is placed into
        // a Markov System that is then iterated by itself many times
        // to find the steady state of the system.
        for (int i = 0; i < probTransfer1to0.length; i++)
        {
            // find how many bits it takes to represent the number of states
            // For example, three bits will get you 8 binary states, 2^3
            for (int j = 0; j < Math.ceil(Math.log(probTransfer1to0.length) / Math.log(2)); j++)
            {
                // take j and AND it with each bit in the state number to
                // check what digit it is transfering on 0 to 1
                if (((i & (1 << j)) == 0))
                {
                    trans[i][i + (1 << j)] = probTransfer1to0[i][probTransfer1to0[i].length - 1 - j];
                }
                // take j and AND it with each bit in the state number to
                // check what digit it is transfering on 1 to 0 
                else
                {
                    trans[i][i - (1 << j)] = ((probTransfer0to1[i][probTransfer1to0[i].length - 1 - j]));
                }
            }
            sum = 0;

            // fill in the diagnols (the states go to themselves)
            // the value for the diagnols is 1 minus the sum of the probabilities
            // of one state tranfering to another state
            for (int j = 0; j < probTransfer1to0.length; j++)
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
}
