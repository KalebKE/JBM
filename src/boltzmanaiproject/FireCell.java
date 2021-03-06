/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boltzmanaiproject;

import java.util.Random;

/**
 *
 * @author Kaleb
 */
public class FireCell
{
    /**
     * Fires the cells, or doesn't fier the cell, based on the activation probabilities.
     * @param activations the probabilities that each node will fire
     * @return the results of the fired nodes
     */
    public int[][] fireCells(double[][] activations)
    {
        // Array to store the results of the probabilistic
        // activations. These are the new states of the boltzman machine.
        int[][] states = new int[activations.length][];

        for (int i = 0; i < activations.length; i++)
        {
            states[i] = new int[activations[i].length];
            for (int j = 0; j < activations[i].length; j++)
            {
                // Get a random double between 0 and 1
                Random r = new Random();

                // If the random double is less than or equal to the
                // activation, the node fires
                if (r.nextDouble() <= activations[i][j])
                {
                    states[i][j] = 1;
                }
                // Otherwise the node does not fire
                else
                {
                    states[i][j] = 0;
                }
            }
        }

        //printMatrix(states);

        return states;
    }

    private void printMatrix(int[][] matrix)
    {
        System.out.println();
        System.out.println("The new State Matrix: ");

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
