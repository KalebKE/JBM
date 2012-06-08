package boltzmanaiproject;

/**
 * Find the steady state vector of the transition matrix.
 *
 * @author Kaleb
 */
public class MultiplyMatrix
{
    /**
     * A method that calculates the steady state vectors from a transition matrix.
     *
     * @param a The transition matrix.
     * @return
     */
    public double[][] multiply(double[][] a)
    {
        int test = 0;
        double diff;
        int count, i, j, k, l;

        double[][] product = new double[Main.size][Main.size];
        double[][] olda = new double[Main.size][Main.size];

        for (i = 0; i < Main.size; i++)
        {
            for (j = 0; j < Main.size; j++)
            {
                olda[i][j] = a[i][j];
            }
        }

        for (count = 1; count <= 1000; count++)
        {
            for (i = 0; i < Main.size; i++)
            {
                for (j = 0; j < Main.size; j++)
                {
                    product[i][j] = 0;
                }
            }

            for (i = 0; i < Main.size; i++)
            {
                for (j = 0; j < Main.size; j++)
                {
                    for (k = 0; k < Main.size; k++)
                    {
                        product[i][j] = product[i][j] + a[i][k] * olda[k][j];
                    }
                }
            }

            for (i = 0; i < Main.size; i++)
            {
                for (j = 0; j < Main.size; j++)
                {
                    diff = Math.abs(product[i][j] - olda[i][j]);
                    if (diff > Main.epsilon)
                    {
                        test = 1;
                        for (k = 0; k < Main.size; k++)
                        {
                            for (l = 0; l < Main.size; l++)
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
                    System.out.println("\n" +
                            count);
                    break;
                }
                test = 0;
            }
        }

        return product;
    }
}
