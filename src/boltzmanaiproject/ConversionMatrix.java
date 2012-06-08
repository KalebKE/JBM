package boltzmanaiproject;

/**
 * Converts the activations via a conversion table.
 *
 * @author Kaleb Kircher
 */
public class ConversionMatrix
{
    /**
     * Finds the activations and looks up their conversion based on temperature.
     *
     * @param ws a 2-d array containing the weights
     * @param temperature a integer for the temperature
     * @return a 2-d array containing the new weights
     */
    public double[][][] conversionMatrix(double[][] ws, int temperature)
    {
        // a table containing the conversions
        double[][] conversionTable =
        {
            {
                0.0000454, 0.017986, 0.119203, 0.208609, 0.268941
            },
            {
                0.000123, 0.026597, 0.141851, 0.231475, 0.28905
            },
            {
                0.000335, 0.039166, 0.167982, 0.256038, 0.310026
            },
            {
                0.000911, 0.057324, 0.197816, 0.282249, 0.331812
            },
            {
                0.002473, 0.083173, 0.231475, 0.310026, 0.354344
            },
            {
                0.006693, 0.119203, 0.268941, 0.339244, 0.377541
            },
            {
                0.017986, 0.167982, 0.310026, 0.36974, 0.401312
            },
            {
                0.047462, 0.231475, 0.354344, 0.401312, 0.425557
            },
            {
                0.119203, 0.310026, 0.401312, 0.433726, 450166
            },
            {
                0.268941, 0.401312, 0.450166, 0.466716, 0.475021
            },
            {
                0.5, 0.5, 0.5, 0.5, 0.5
            },
            {
                0.731059, 0.598688, 0.549834, 0.533284, 0.524979
            },
            {
                0.880797, 0.689974, 0.598688, 0.566274, 0.549834
            },
            {
                0.952574, 0.768525, 0.6456565, 0.598688, 0.574443
            },
            {
                0.982014, 0.832018, 0.68974, 0.63026, 0.598688
            },
            {
                .993307, 0.880797, 0.731059, 0.660756, 0.622459
            },
            {
                0.997527, 0.916827, 0.768525, 0.689974, 0.645656
            },
            {
                0.999089, 0.942676, 0.802184, 0.717751, 0.668188
            },
            {
                0.999665, 0.960834, 0.832018, 0.743962, 0.689974
            },
            {
                0.999877, 0.973403, 0.858149, 0.768525, 0.71095
            },
            {
                0.999955, 0.982014, 0.880797, 0.791391, 0.731059
            }
        };

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
                newWS[i][j][0] = conversionTable[(int) (10 * activations[j][i]) + 10][temperature];
                // probability that the cell state goes to 0 => 1 -Pij.
                newWS[i][j][1] = 1 - newWS[i][j][0];
                // Pij divided by the number of cell
                // connections
                newWS[i][j][2] = newWS[i][j][0] / 3;
                // 1 - Pij divided by the number of cell
                // connections
                newWS[i][j][3] = newWS[i][j][1] / 3;
            }
        }

        System.out.println();
        System.out.println("The new WS Matrix: ");

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
