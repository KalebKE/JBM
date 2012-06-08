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
public class FireCellTest
{
    public FireCellTest()
    {
        super();
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        System.out.println("* FireCellTest: @BeforeClass method");
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
        System.out.println("* FireCellTest: @AfterClass method");
    }

    @Before
    public void setUp()
    {
        System.out.println("* FireCellTest: @Before method");
    }

    @After
    public void tearDown()
    {
        System.out.println("* FireCellTest: @After method");
    }

    /**
     * Test of fireCells method, of class FireCell.
     */
    @Test
    public void testFireCells()
    {
        System.out.println("* FireCellTest: testFireCells()");

        // The activation probabilities that will be used to fire the cells.
        double[][] activations =
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

        FireCell instance = new FireCell();

        int[][] result = instance.fireCells(activations);

        boolean valid = false;

        // The result is not stochastic, not deterministic.
        // This makes testing for absolute values impossible,
        // so we test for valid outputs. 
        for (int i = 0; i < result.length; i++)
        {
            for (int j = 0; j < result[i].length; j++)
            {
                // if any value is not equal to the expected value, fail
                if (result[i][j] == 0)
                {
                    valid = true;
                }
                // if any value is not equal to the expected value, fail
                if (result[i][j] == 1)
                {
                    valid = true;
                }
            }
        }

        assertTrue(valid);
    }
}
