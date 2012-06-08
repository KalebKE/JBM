/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boltzmanaiproject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kaleb
 */
public class CalculateActivationTest
{
    public CalculateActivationTest()
    {
        super();
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        System.out.println("* CalculateActivationTest: @BeforeClass method");
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
        System.out.println("* CalculateActivationTest: @AfterClass method");
    }

    @Before
    public void setUp()
    {
        System.out.println("* CalculateActivationTest: @Before method");
    }

    @After
    public void tearDown()
    {
        System.out.println("* CalculateActivationTest: @After method");
    }

    /**
     * Test of conversionMatrix method, of class CalculateActivation.
     */
    @Test
    public void testConversionMatrix()
    {
        System.out.println("* CalculateActivationTest: testConversionMatrix()");
        
        // The Weighted Sums that we will find the activations for.
        double[][] ws =
        {
            {
                0.1, 0.5, -0.4, 0, 0.1, 0.5, -0.4, 0
            },
            {
                0.2, 0.7, 0.2, 0.7, -0.3, 0.2, -0.3, 0.2
            },
            {
                -0.7, -0.7, -0.2, -0.2, -0.3, -0.3, 0.2, 0.2
            }
        };

        // The temperature for the expected result is 0.5
        double temperature = 0.5;

        CalculateActivation instance = new CalculateActivation();
       
        // The expected activations for a temperature of 0.5
        double[][] expResult =
        {
            {
                0.549833997312478, 0.7310585786300049, 0.31002551887238755, 0.5, 0.549833997312478, 0.7310585786300049, 0.31002551887238755, 0.5
            },
            {
                0.598687660112452, 0.8021838885585817, 0.598687660112452, 0.8021838885585817, 0.35434369377420455, 0.598687660112452, 0.35434369377420455, 0.598687660112452
            },
            {
                0.19781611144141825, 0.19781611144141825, 0.401312339887548, 0.401312339887548, 0.35434369377420455, 0.35434369377420455, 0.598687660112452, 0.598687660112452
            }
        };

        double[][] result = instance.conversionMatrix(ws, temperature);

        // Ensure that the result is equal to the expected result
        boolean equal = true;
        for (int i = 0; i < result.length; i++)
        {
            for (int j = 0; j < result[i].length; j++)
            {
                // if any value is not equal to the expected value, fail
                if (result[i][j] != expResult[i][j])
                {
                    equal = false;
                }
            }
        }

        assertTrue(equal);
    }
}
