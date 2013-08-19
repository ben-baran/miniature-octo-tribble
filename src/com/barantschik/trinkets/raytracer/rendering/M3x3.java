package com.barantschik.trinkets.raytracer.rendering;

public class M3x3
{
	public double[][] values;
	
	public M3x3(double[][] values)
	{
		this.values = values;
	}
	
	public double[] getMinor(int x, int y)
	{
		if(x < 0 || x > 2 || y < 0 || y > 2)
		{
			return null;
		}
		else
		{
			double[] solution = new double[4];
			for(int i = 0, numfound = 0; i < 3 && numfound < 4; i++)
			{
				for(int j = 0; j < 3 && numfound < 4; j++)
				{
					if(i != x && j != y)
					{
						solution[numfound] = values[i][j];
						numfound++;
					}
				}
			}
			return solution;
		}
	}
}