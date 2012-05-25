/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boltzmanaiproject;

/**
 *
 * @author Kaleb
 */
public class tester
{
    public static void main(String[] args)
    {
        double[][] w =
        {
            {
                0.0, -0.5, 0.2
            },
            {
                -0.5, 0.0, 0.6
            },
            {
                0.2, 0.6, 0.0
            }
        };
        double[][] outcomes =
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

        double[][] ws = new double[3][8];

        double[][] t =
        {
            {
                .3, -.1, .3
            }
        };

        int x = w.length;
        System.out.println("Matrix 1 : ");
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < x; j++)
            {
                System.out.print(" " + w[i][j]);
            }
            System.out.println();
        }

        int y = outcomes.length;
        int z = outcomes[0].length;
        System.out.println("Matrix 2 : ");
        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < z; j++)
            {
                System.out.print(" " + outcomes[i][j]);
            }
            System.out.println();
        }
        // w rows
        for (int i = 0; i < x; i++)
        {   // outcomes colums
            for (int j = 0; j < z; j++)
            {
                // w colums
                for (int k = 0; k < w[0].length; k++)
                {
                    ws[i][j] += w[i][k] * outcomes[k][j];
                }
            }
        }


        System.out.println("Multiply of both matrix : ");


        for (int i = 0; i < ws.length; i++)
        {
            for (int j = 0; j < ws[0].length; j++)
            {
                System.out.print(" " + ws[i][j]);
            }
            System.out.println();
        }

        System.out.println("2.Subtraction\n");
        
        double diff[][] = new double[3][8];
        int c = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                diff[i][j] = ws[i][j] - t[0][c];
                c++;
                if(c == 3)
                {
                    c = 0;
                }
                System.out.print("\t" + diff[i][j]);
            }
            System.out.println("\t");
        }


    }
}
