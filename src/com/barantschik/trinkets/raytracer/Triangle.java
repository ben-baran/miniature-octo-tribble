package com.barantschik.trinkets.raytracer;

public class Triangle implements Renderable
{
	private double specularity = 50;
	
	double[] v1, v2, v3;
	private float[] color;
	private double[] normal;
	
	public Triangle(double[] v1, double[] v2, double[] v3, float[] color)
	{
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		
		this.color = color;
		
		normal = GMath.normalize(GMath.cross(GMath.subtract(v1, v3), GMath.subtract(v1, v2)));
		System.out.println(normal[0] + ", " + normal[1] + ", " + normal[2]);
	}
	
	public double giveIntersection(Ray r)
	{
		double dirDotN = GMath.dot(r.dir, normal);
		if(dirDotN != 0)
		{
			double t = GMath.dot(normal, GMath.subtract(r.pos, v1)) / dirDotN;
			if(t > 0)
			{
				
			}
		}
		return Double.NaN;
	}

	public double[] getNormal(double[] point)
	{
		return normal;
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
		double[] container = {x, y, z};
		v1 = GMath.add(container, v1);
		v2 = GMath.add(container, v2);
		v3 = GMath.add(container, v3);
	}

	public void transform(M4x4 transform)
	{
		//not implemented yet
	}

}