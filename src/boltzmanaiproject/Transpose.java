/*
Transpose -- an class within the Machine Artificial Vision Network(Machine Artificial Vision Network)
Copyright (C) 2012, Kaleb Kircher.

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package boltzmanaiproject;

/**
 * A class containing methods to tranpose two dimensional collections.
 * @author Kaleb
 */
public class Transpose
{
    /**
     * Tranpose a double array.
     * @param array the array to be transposed.
     * @return the transposed array.
     */
    public static double[][] tranposeMatrix(double[][] array)
    {

        int r = array.length;
        int c = array[0].length;
        double[][] t = new double[c][r];
        for (int i = 0; i < r; ++i)
        {
            for (int j = 0; j < c; ++j)
            {
                t[j][i] = array[i][j];
            }
        }

        return t;
    }
}
