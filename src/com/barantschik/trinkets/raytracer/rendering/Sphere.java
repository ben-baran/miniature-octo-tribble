package com.barantschik.trinkets.raytracer.rendering;


public class Sphere implements Renderable
{
	private static final double DEFAULT_SHININESS = 100;
	private double shininess;
	
	private static final float DEFAULT_REFLECTIVITY = 1;
	private float reflectivity;
	
	private static final float DEFAULT_TRANSMISSION = 0;
	private float transmission;
	
	private static final float DEFAULT_INDEX_OF_REFRACTION = 1;
	private float indexOfRefraction;
	
	double[] pos;
	double radius;
	private float[] diffuse;
	private float[] specular;
	private float[] ambient;
	private float[] emmissive;
	
	private boolean transformed = false;
	private M4x4 transformMatrix = M4x4.identity();
	private M4x4 inverseTransformMatrix = M4x4.identity();
	
	public Sphere(double[] pos, double radius)
	{
		this(pos, radius, GMath.empty());
	}
	
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
		this(pos, radius, diffuse, specular, shininess, ambient, emmissive, DEFAULT_REFLECTIVITY);
	}
	
	public Sphere(double[] pos, double radius, float[] diffuse, float[] specular, double shininess, float[] ambient, float[] emmissive, float reflectivity)
	{
		this(pos, radius, diffuse, specular, shininess, ambient, emmissive, DEFAULT_REFLECTIVITY, DEFAULT_TRANSMISSION);
	}
	
	public Sphere(double[] pos, double radius, float[] diffuse, float[] specular, double shininess, float[] ambient, float[] emmissive, float reflectivity, float transmission)
	{
		this(pos, radius, diffuse, specular, shininess, ambient, emmissive, DEFAULT_REFLECTIVITY, DEFAULT_TRANSMISSION, DEFAULT_INDEX_OF_REFRACTION);
	}
	
	public Sphere(double[] pos, double radius, float[] diffuse, float[] specular, double shininess, float[] ambient, float[] emmissive, float reflectivity, float transmission, float indexOfRefraction)
	{
		this.pos = pos;
		this.radius = radius;
		this.diffuse = diffuse;
		this.specular = specular;
		this.shininess = shininess;
		this.reflectivity = reflectivity;
		this.transmission = transmission;
		this.indexOfRefraction = indexOfRefraction;
		this.ambient = ambient;
		this.emmissive = emmissive;
	}
	
	public void setMaterial(Material m)
	{
		diffuse = m.diffuse;
		specular = m.specular;
		
		shininess = m.shininess;
		
		reflectivity = m.reflectivity;
		transmission = m.transmission;
		indexOfRefraction = m.indexOfRefraction;
		
		ambient = m.ambient;
		emmissive = m.emmissive;
	}
	
	public IntersectionData giveIntersection(Ray r)
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
				return new IntersectionData(Double.NaN, null);
			}
			else if(solution[0] < 0 || solution[1] < 0)
			{
				return new IntersectionData(Math.max(solution[0],  solution[1]), this);
			}
			else
			{
				return new IntersectionData(Math.min(solution[0], solution[1]), this);
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
				return new IntersectionData(Double.NaN, null);
			}
			else if(solution[0] < 0 || solution[1] < 0)
			{
				finalAnswer = Math.max(solution[0],  solution[1]);
			}
			else
			{
				finalAnswer = Math.min(solution[0],  solution[1]);
			}
			
			return new IntersectionData(finalAnswer, this);
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
	}

	public double getShininess()
	{
		return shininess;
	}

	public float getReflectivity()
	{
		return reflectivity;
	}
	
	public float[] getDiffuse(IntersectionData interData)
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

	public float getTransmission()
	{
		return transmission;
	}

	public float getIndexOfRefraction()
	{
		return indexOfRefraction;
	}
}