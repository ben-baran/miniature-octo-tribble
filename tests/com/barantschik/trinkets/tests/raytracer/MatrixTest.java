package com.barantschik.trinkets.tests.raytracer;

import org.junit.Test;

import com.barantschik.trinkets.raytracer.M4x4;

import static org.junit.Assert.*;

public class MatrixTest
{
	@Test
	public void testMinors4x4()
	{
		double[][] test1Values = {{1, 2, 3, 4},
								  {5, 6, 7, 8},
								  {9, 10, 11, 12},
								  {13, 14, 15, 16}};
		M4x4 test1 = new M4x4(test1Values);
		double[][] test1SolutionValues = {{6, 7, 8},
										  {10, 11, 12},
										  {14, 15, 16}};
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				System.out.print(test1.getMinor(0, 0).values[i][j] + " ");
			}
			System.out.println();
		}
		assertTrue("The 4 by 4 matrix should return the correct minor", GMathTest.acceptable(test1SolutionValues, test1.getMinor(0, 0).values));
	}
}
