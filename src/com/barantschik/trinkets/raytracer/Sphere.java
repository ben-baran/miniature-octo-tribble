package com.barantschik.trinkets.raytracer;

public class Sphere implements Renderable
{
	private static final double DEFAULT_SHININESS = 50;
	private double shininess = 50;
	
	double[] pos;
	double radius;
	private float[] diffuse;
	private float[] specular;
	
	public Sphere(double[] pos, double radius, float[] color)
	{
		this(pos, radius, color, color);
	}
	
	public Sphere(double[] pos, double radius, float[] diffuse, float[] specular)
	{
		this(pos, radius, diffuse, specular, DEFAULT_SHININESS);
	}
	
	public Sphere(double[] pos, double radius, float[] diffuse, float[] specular, double shininess)
	{
		this.pos = pos;
		this.radius = radius;
		this.diffuse = diffuse;
		this.specular = specular;
		this.shininess = shininess;
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

	
	public double getShininess()
	{
		return shininess;
	}

	public float[] getDiffuse()
	{
		return diffuse;
	}

	public float[] getSpecular()
	{
		return specular;
	}
}