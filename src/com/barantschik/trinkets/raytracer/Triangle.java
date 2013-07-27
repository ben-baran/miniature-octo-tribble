package com.barantschik.trinkets.raytracer;

import java.util.Arrays;

public class Triangle implements Renderable
{
	private static final double DEFAULT_SHININESS = 50;
	private double shininess;
	
	double[] v1, v2, v3;
	double[] bMinA, cMinA;
	
	private float[] diffuse;
	private float[] specular;
	private float[] ambient;
	private float[] emmissive;
	
	private double[] normal;
	private double[] transformedNormal;
	
	private boolean transformed = false;
	private M4x4 transformMatrix = M4x4.identity();
	private M4x4 inverseTransformMatrix = M4x4.identity();
	
	public Triangle(double[] v1, double[] v2, double[] v3, float[] color)
	{
		this(v1, v2, v3, color, color);
	}
	
	public Triangle(double[] v1, double[] v2, double[] v3, float[] diffuse, float[] specular)
	{
		this(v1, v2, v3, diffuse, specular, DEFAULT_SHININESS);
	}
	
	public Triangle(double[] v1, double[] v2, double[] v3, float[] diffuse, float[] specular, double shininess)
	{
		this(v1, v2, v3, diffuse, specular, shininess, new float[]{0, 0, 0});
	}
	
	public Triangle(double[] v1, double[] v2, double[] v3, float[] diffuse, float[] specular, double shininess, float[] ambient)
	{
		this(v1, v2, v3, diffuse, specular, shininess, ambient, new float[]{0, 0, 0});
	}
	
	public Triangle(double[] v1, double[] v2, double[] v3, float[] diffuse, float[] specular, double shininess, float[] ambient, float[] emmissive)
	{
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		
		bMinA = GMath.subtract(v1, v2);
		cMinA = GMath.subtract(v1, v3);
		
		this.diffuse = diffuse;
		this.specular = specular;
		
		normal = GMath.normalize(GMath.cross(GMath.subtract(v1, v3), GMath.subtract(v1, v2)));
		transformedNormal = Arrays.copyOf(normal, normal.length);
		
		this.shininess = shininess;
		
		this.ambient = ambient;
		this.emmissive = emmissive;
	}
	
	public double giveIntersection(Ray r)
	{
		if(!transformed)
		{
			double dirDotN = GMath.dot(r.dir, normal);
			if(dirDotN != 0)
			{
				double t = GMath.dot(normal, GMath.subtract(r.pos, v1)) / dirDotN;
				if(t > 0)
				{
					M3x3 inverseMatrix = GMath.findInverseMatrix(GMath.constructMatrix(bMinA, cMinA, GMath.negative(r.dir)));
					double[] solution = GMath.mult(inverseMatrix, GMath.subtract(v1, r.pos));
					
					if(solution[0] >= 0 && solution[1] >= 0 && solution[0] + solution[1] <= 1)
					{
						return t;
					}
				}
			}
			return Double.NaN;
		}
		else
		{
			return Double.NaN;
		}
	}

	public double[] getNormal(double[] point)
	{
		return transformedNormal;
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
		if(!transformed) transformed = true;
		
		transformMatrix = GMath.mult(transform, transformMatrix);
		inverseTransformMatrix = GMath.findInverseMatrix(transformMatrix);

		transformedNormal = GMath.mult(inverseTransformMatrix, GMath.createHomogenousDir(normal));
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