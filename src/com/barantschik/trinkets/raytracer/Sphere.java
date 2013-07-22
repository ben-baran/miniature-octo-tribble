package com.barantschik.trinkets.raytracer;

public class Sphere implements Renderable
{
	double[] pos;
	double radius;
	private float[] color;
	
	private double specularity = 50;
	
	public Sphere(double[] pos, double radius, float[] color)
	{
		this.pos = pos;
		this.radius = radius;
		this.color = color;
	}

	public double giveIntersection(Ray r)
	{
		double posdif[] = GMath.subtract(pos, r.pos);

		double a = 1;
		double b = 2 * GMath.dot(r.dir, posdif);
		double c = GMath.dot(posdif, posdif) - radius * radius;

		double[] solution = GMath.solveQuad(a, b, c);
		if(Double.isNaN(solution[0]) || (solution[0] < 0 && solution[1] < 0))
		{
			return Double.NaN;
		}
		else if(solution[0] < 0 || solution[1] < 0)
		{
			return Math.max(solution[0],  solution[1]);
		}
		else
		{
			return Math.min(solution[0],  solution[1]);
		}
	}

	public double[] getNormal(double[] point)
	{
		return GMath.normalize(GMath.subtract(pos, point));
	}

	public float[] getColor()
	{
		return color;
	}

	public double getSpecularity()
	{
		return specularity;
	}
	
	public void move(double x, double y, double z)
	{
		pos[0] += x;
		pos[1] += y;
		pos[2] += z;
	}

	public void transform(M4x4 transform)
	{
		//not implemented yet
	}
}