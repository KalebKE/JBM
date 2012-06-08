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
    public double[][] fireCells(double[][] activations)
    {
        // Array to store the results of the probabilistic
        // activations. These are the new states of the boltzman machine.
        double[][] states = new double[activations.length][];

        for (int i = 0; i < activations.length; i++)
        {
            states[i] = new double[activations[i].length];
            for (int j = 0; j < activations[i].length; j++)
            {
                // Get a random double between 0 and 1
                Random r = new Random();

                // If the random double is less than or equal to the
                // activation, the node fires
                if(r.nextDouble() <= activations[i][j])
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

        return states;
    }
}
