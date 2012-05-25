
package boltzmanaiproject;

/**
 * A program which takes the weights and thresholds for a Boltzman
 * Net and generates the transion matrix and the steady state vector.
 *
 * @author Kaleb Kircher
 */
public class Main
{
    static int size = 8;
    static double epsilon = 0.0000000000001;
    static double[][] product = new double[size][size];
    static int temperature = 2;

    /**
     * Initializes the input arrays and calls the methods that produce
     * the matrix outputs.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        GenerateMatrix gm = new GenerateMatrix();
        ConversionMatrix cm = new ConversionMatrix();
        TransferMatrix tm = new TransferMatrix();
        MultiplyMatrix mm = new MultiplyMatrix();

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

        // arrays for calculations
        double[][] ws = gm.generateMatrix(w, outcomes, threshold);
        double[][][] newWS = cm.conversionMatrix(ws, temperature);
        double[][] trans = tm.generateNewMatrix(newWS);
        double[][] product = mm.multiply(trans);
        
        // print the steady state vector
        int m = size;
        for (int i = 0; i < m; i++)
        {
            System.out.println("\n");
            for (int j = 0; j < m; j++)
            {
                System.out.print(product[i][j]);
            }
        }
    }
}

