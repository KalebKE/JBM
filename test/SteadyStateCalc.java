
/**
 * A class that contains methods to calculates the steady state vectors from a transition matrix.
 *
 * @author Kaleb Kircher
 */
public class SteadyStateCalc
{
    /**
     * A method that calculates the steady state vectors from a transition matrix.
     * It will calculate a matrix of any size.
     *
     * @param a The transition matrix.
     */
    public double[][] multiply(double[][] a)
    {
        double diff;
        double epsilon = 0.0000000000001;
        int count, i, j, k, l;
        int test = 0;

        double[][] product = new double[a.length][a[0].length];
        double[][] olda = new double[a.length][a[0].length];

        for (i = 0; i < a.length; i++)
        {
            for (j = 0; j < a.length; j++)
            {
                olda[i][j] = a[i][j];
            }
        }

        for (count = 1; count <= 1000; count++)
        {
            for (i = 0; i < a.length; i++)
            {
                for (j = 0; j < a.length; j++)
                {
                    product[i][j] = 0;
                }
            }

            for (i = 0; i < a.length; i++)
            {
                for (j = 0; j < a.length; j++)
                {
                    for (k = 0; k < a.length; k++)
                    {
                        product[i][j] = product[i][j] + a[i][k] * olda[k][j];
                    }
                }
            }

            for (i = 0; i < a.length; i++)
            {
                for (j = 0; j < a.length; j++)
                {
                    diff = Math.abs(product[i][j] - olda[i][j]);
                    if (diff > epsilon)
                    {
                        test = 1;
                        for (k = 0; k < a.length; k++)
                        {
                            for (l = 0; l < a.length; l++)
                            {
                                olda[k][l] = product[k][l];
                            }
                        }
                        break;
                    }
                }
                if (test < 1)
                {
                    System.out.println("\n The number of iterations is: ");
                    System.out.println("\n");
                    System.out.println("\n"
                            + count);
                    break;
                }
                test = 0;
            }
        }

        return product;
    }

    /**
     * Method to run the matrix calculator.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        SteadyStateCalc calc = new SteadyStateCalc();

        // *MATRIX TO BE CALCULATED*
        // *change this matrix*
        double[][] matrix =
        {
            {
                0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
            },
            {
                0.4, 0.0, 0.13, 0.1, 0.17, 0.2, 0.0, 0.0
            },
            {
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0
            },
            {
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0
            },
            {
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0
            },
            {
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0
            },
            {
                0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
            },
            {
                0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
            }
        };

        double[][] product = calc.multiply(matrix);

        // print the steady state vector
        int m = matrix.length;

        System.out.print("\n");
        System.out.print(" \n The product is: ");
        System.out.print("\n");
        for (int i = 0; i < m; i++)
        {
            System.out.print("\n");
            for (int j = 0; j < m; j++)
            {
                System.out.print(product[i][j]);
            }

        }
    }
}
