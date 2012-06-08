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
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of fireCells method, of class FireCell.
     */
    @Test
    public void testFireCells()
    {
        System.out.println("fireCells");
        double[][] activations = null;
        FireCell instance = new FireCell();
        double[][] expResult = null;
        double[][] result = instance.fireCells(activations);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
