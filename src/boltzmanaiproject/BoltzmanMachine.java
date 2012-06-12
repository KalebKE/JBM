/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boltzmanaiproject;

import boltzmanaiproject.markov.MarkovSteadyState;

/**
 *
 * @author Kaleb
 */
public class BoltzmanMachine
{
    // array for weights
    // ***Change the weights here***
    double[][] w =
    {
        {
            0.0, -0.5, 0.4
        },
        {
            -0.5, 0.0, 0.5
        },
        {
            0.4, 0.5, 0.0
        }
    };
    
    // array of outcomes
    int[][] outcomes =
    {
        {
            0, 0, 0, 0, 1, 1, 1, 1
        },
        {
            0, 0, 1, 1, 0, 0, 1, 1
        },
        {
            0, 1, 0, 1, 0, 1, 0, 1
        }
    };
    // array for thesholds
    double[][] threshold =
    {
        {
            -0.1
        },
        {
            -0.2
        },
        {
            0.7
        }
    };

    // This is the equalibrium/ steady state for this system
    // when the vectors are all un-clamped and the system
    // is allowed to run to equalibrium
    double[] equalibrium =
    {
        0.1389298344501983, 0.08914129869459861, 0.16917291152350944, 0.11310280419150183, 0.1333312177004315, 0.08484338317744242, 0.16196094281763623, 0.10951760744468109
    };

    GenerateWeightedSum gm = new GenerateWeightedSum();
    CalculateActivation ca = new CalculateActivation();
    FireCell fc = new FireCell();
    GenerateTransitionMatrix tm = new GenerateTransitionMatrix();
    MarkovSteadyState ss = new MarkovSteadyState();
    int iterations = 500;
    double temperature = 100;

    public void execute()
    {
        double[][] statistics = new double[outcomes.length][outcomes[0].length];

        for (int i = 1; i <= iterations; i++)
        {
            // arrays for calculations
            double[][] ws = gm.generateMatrix(w, outcomes, threshold);
            double[][] activation = ca.conversionMatrix(ws, temperature / i);
            int[][] state = fc.fireCells(activation);

            // If outcomes equal the state, the system is running
            // totally un-clamped and will eventually converge to
            // the steady state.
            // If this is commented out, the system inputs will be clamped
            // to the outcomes.
            outcomes = state;

            // Un-commenting this code will clamp the outcomes to be the outputs
            //state = outcomes;

            // Add the results of the simulation to an array that keeps
            // track of the probability of each state occuring.
            addMatrix(statistics, state);
        }

        double[][] results = transferProbabilities(statistics);

        System.out.println();
        System.out.println("The boltzman transition probabilities matrix: ");
        printMatrix(results);

        double[][] transitionMatrix = tm.generateNewMatrix(results);
        double[][] steadyState = ss.multiply(transitionMatrix);

        System.out.println();
        System.out.println("The boltzman steady state matrix: ");
        printMatrix(steadyState);

        // This method is used to update the weights of the system
        // for training.
        // Comment this method out to find the equalibrium state
        // of a system.
        asymmetricDivergence(steadyState);
    }

    /**
     * Calculate the new weights of the system.
     * @param steadyState
     */
    public void asymmetricDivergence(double[][] steadyState)
    {
        // Array to store the values for the asymmetric divergence
        // These values will be summed into a value known a G which will
        // then be used to calculate the new weights
        double[] asymmetricDivergence = new double[equalibrium.length];

        // Calculate the asymmetric divergence
        for (int i = 0; i < asymmetricDivergence.length; i++)
        {
            asymmetricDivergence[i] = steadyState[0][i] * (Math.log(steadyState[0][i] / equalibrium[i]));
        }

        double G = 0;

        for (int i = 0; i < asymmetricDivergence.length; i++)
        {
            G += asymmetricDivergence[i];
        }

        double learningRate = 0.0001;

        // Calculate the new weights
        for (int i = 0; i < w.length; i++)
        {
            for (int j = 0; j < w[i].length; j++)
            {
                if (w[i][j] != 0)
                {
                    w[i][j] = (-learningRate) * (G / w[i][j]);
                }

            }
        }

        System.out.println();
        System.out.println("The new weight matrix: ");
        printMatrix(w);
    }

    /**
     * Add a matrix to another matrix.
     * @param a matrix to be added to.
     * @param b matrix to be added.
     * @return the result of the matrix addition.
     */
    public double[][] addMatrix(double[][] a, int[][] b)
    {
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a[i].length; j++)
            {
                a[i][j] = a[i][j] + b[i][j];
            }
        }

        return a;
    }

    /**
     * Calculate the transfer probabilities of the System.
     * @param matrix
     * @return
     */
    public double[][] transferProbabilities(double[][] matrix)
    {
        double[][] probabilities = new double[matrix.length][];

        for (int i = 0; i < matrix.length; i++)
        {
            probabilities[i] = new double[matrix[i].length];

            for (int j = 0; j < matrix[i].length; j++)
            {
                probabilities[i][j] = matrix[i][j] / iterations;
            }
        }

        return probabilities;
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
        System.out.println();
        System.out.println("The Clamped State Matrix: ");

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        BoltzmanMachine bm = new BoltzmanMachine();

        // The loop is only needed when
        // the weights are being calculated.
        // To find the equalibrium state of a system,
        // set the number of iterations to 1.
        for (int i = 0; i < 100; i++)
        {
            System.out.println("Iteration: " + i);

            bm.execute();
        }
    }
}
