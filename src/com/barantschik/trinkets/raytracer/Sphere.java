package com.barantschik.trinkets.raytracer;

public class Sphere implements Renderable
{
	private static final double DEFAULT_SHININESS = 50;
	private double shininess = 50;
	
	double[] pos;
	double radius;
	private float[] diffuse;
	private float[] specular;
	private float[] ambient;
	private float[] emmissive;
	
	private boolean transformed = false;
	private M4x4 transformMatrix = M4x4.identity();
	private M4x4 inverseTransformMatrix = M4x4.identity();
	private M4x4 inverseTransposedTransformMatrix = M4x4.identity();
	
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
		this(pos, radius, diffuse, specular, shininess, new float[]{0, 0, 0});
	}

	public Sphere(double[] pos, double radius, float[] diffuse, float[] specular, double shininess, float[] ambient)
	{
		this(pos, radius, diffuse, specular, shininess, ambient, new float[]{0, 0, 0});
	}
	
	public Sphere(double[] pos, double radius, float[] diffuse, float[] specular, double shininess, float[] ambient, float[] emmissive)
	{
		this.pos = pos;
		this.radius = radius;
		this.diffuse = diffuse;
		this.specular = specular;
		this.shininess = shininess;
		this.ambient = ambient;
		this.emmissive = emmissive;
	}
	
	public double giveIntersection(Ray r)
	{
		if(!transformed)
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
		else
		{
			double[] transformedPos = GMath.mult(inverseTransformMatrix, GMath.createHomogenousPos(r.pos));
			double[] transformedDir = GMath.mult(inverseTransformMatrix, GMath.createHomogenousDir(r.dir));
			Ray rT = new Ray(transformedPos, transformedDir);
			
			double posdif[] = GMath.subtract(GMath.createHomogenousPos(pos), rT.pos);

			double a = 1;
			double b = 2 * GMath.dot(rT.dir, posdif);
			double c = GMath.dot(posdif, posdif) - radius * radius;

			double[] solution = GMath.solveQuad(a, b, c);
			double finalAnswer = Double.NaN;
			
			if(Double.isNaN(solution[0]) || (solution[0] < 0 && solution[1] < 0))
			{
				return Double.NaN;
			}
			else if(solution[0] < 0 || solution[1] < 0)
			{
				finalAnswer = Math.max(solution[0],  solution[1]);
			}
			else
			{
				finalAnswer = Math.min(solution[0],  solution[1]);
			}
			
			return finalAnswer;
		}
	}

	public double[] getNormal(double[] point)
	{
		if(!transformed)
		{			
			return GMath.normalize(GMath.subtract(pos, point));
		}
		else
		{
			return GMath.normalize(GMath.subtract(pos, GMath.dehomogenize(GMath.mult(inverseTransformMatrix, GMath.createHomogenousPos(point)))));
//			return GMath.normalize(GMath.subtract(pos, GMath.dehomogenize(GMath.mult(inverseTransposedTransformMatrix, GMath.createHomogenousDir(point)))));
//			return GMath.dehomogenize(GMath.mult(inverseTransposedTransformMatrix, GMath.createHomogenousDir(GMath.normalize(GMath.subtract(pos, GMath.dehomogenize(GMath.mult(transformMatrix, GMath.createHomogenousPos(point))))))));
		}
	}
	
	public void move(double x, double y, double z)
	{
		pos[0] += x;
		pos[1] += y;
		pos[2] += z;
	}

	public void transform(M4x4 transform)
	{
		if(!transformed) transformed = true;
		
		transformMatrix = GMath.mult(transform, transformMatrix);
		inverseTransformMatrix = GMath.findInverseMatrix(transformMatrix);
		inverseTransposedTransformMatrix = GMath.findTranspose(inverseTransformMatrix);
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

	public float[] getAmbient()
	{
		return ambient;
	}
	
	public float[] getEmissive()
	{
		return emmissive;
	}

}