package com.barantschik.trinkets.raytracer.rendering;

public class M4x4
{
	public double[][] values;
	
	public M4x4()
	{
		this(new double[4][4]);
	}
	
	public M4x4(double[][] values)
	{
		this.values = values;
	}
	
	public M3x3 getMinor(int x, int y)
	{
		if(x < 0 || x > 3 || y < 0 || y > 3)
		{
			return null;
		}
		else
		{
			double[][] solution = new double[3][3];
			for(int i = 0, numCol = 0; i < 4 && numCol < 3; i++)
			{
				for(int j = 0, numRow = 0; j < 4 && numRow < 3; j++)
				{
					if(j != y)
					{
						if(i != x)
						{
							solution[numCol][numRow] = values[i][j];
						}
						numRow++;
					}
				}
				if(i != x) numCol++;
			}
			return new M3x3(solution);
		}
	}
	
	public static M4x4 identity()
	{
		double[][] identity = {{1, 0, 0, 0},
							   {0, 1, 0, 0},
							   {0, 0, 1, 0},
							   {0, 0, 0, 1}};
		return new M4x4(identity);
	}
}