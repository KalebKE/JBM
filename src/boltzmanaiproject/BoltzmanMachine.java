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
    GenerateWeightedSum gm = new GenerateWeightedSum();
    CalculateActivation ca = new CalculateActivation();
    FireCell fc = new FireCell();
    GenerateTransitionMatrix tm = new GenerateTransitionMatrix();
    MarkovSteadyState ss = new MarkovSteadyState();
    double[][] statistics = new double[outcomes.length][outcomes[0].length];
    int iterations = 500;
    double temperature = 100;

    public void execute()
    {
        for (int i = 1; i <= iterations; i++)
        {
            // arrays for calculations
            double[][] ws = gm.generateMatrix(w, outcomes, threshold);
            double[][] activation = ca.conversionMatrix(ws, temperature / i);
            int[][] state = fc.fireCells(activation);
           
            addMatrix(statistics, state);
        }

        double[][] results = transferProbabilities(statistics);

        printMatrix(results);

       double[][] transitionMatrix = tm.generateNewMatrix(results);
       double[][] steadyState = ss.multiply(transitionMatrix);
       printMatrix(steadyState);
    }

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
        System.out.println();
        System.out.println("The boltzman transition probabilities Matrix: ");

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
        bm.execute();
    }
}
