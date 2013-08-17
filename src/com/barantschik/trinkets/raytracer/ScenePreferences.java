package com.barantschik.trinkets.raytracer;

public class ScenePreferences
{
	public final static float[] DEFAULT_AMBIENT = {0, 0, 0};
	public final static double[] DEFAULT_DEFAULT_ATTENUATION = {1, 0, 0};
	
	public final static double DEFAULT_FLOAT_ADJUST = 0.000001;
	public final static int DEFAULT_NUM_RECURSIVE = 5;
	
	private int sizeX, sizeY;
	
	private Sampler aaProvider;
	
	private float[] ambient;
	private double[] defaultAttenuation;
	
	private double floatAdjust;
	private int numRecursive;
	
	public ScenePreferences(int sizeX, int sizeY)
	{
		this(sizeX, sizeY, new CenterSampler());
	}
	
	public ScenePreferences(int sizeX, int sizeY, Sampler aaProvider)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.aaProvider = aaProvider;
		
		ambient = DEFAULT_AMBIENT;
		defaultAttenuation = DEFAULT_DEFAULT_ATTENUATION;
		
		floatAdjust = DEFAULT_FLOAT_ADJUST;
		numRecursive = DEFAULT_NUM_RECURSIVE;
	}
	
	public int getSizeX()
	{
		return sizeX;
	}
	
	public void setSizeX(int sizeX)
	{
		this.sizeX = sizeX;
	}
	
	public int getSizeY()
	{
		return sizeY;
	}
	
	public void setSizeY(int sizeY)
	{
		this.sizeY = sizeY;
	}
	
	public Sampler getAAProvider()
	{
		return aaProvider;
	}
	
	public float[] getAmbient()
	{
		return ambient;
	}

	public void setAmbient(float[] ambient)
	{
		this.ambient = ambient;
	}

	public double[] getDefaultAttenuation()
	{
		return defaultAttenuation;
	}

	public void setDefaultAttenuation(double[] defaultAttenuation)
	{
		this.defaultAttenuation = defaultAttenuation;
	}

	
	public double getFloatAdjust()
	{
		return floatAdjust;
	}
	

	public void setFloatAdjust(double floatAdjust)
	{
		this.floatAdjust = floatAdjust;
	}

	public int getNumRecursive()
	{
		return numRecursive;
	}

	public void setNumRecursive(int numRecursive)
	{
		this.numRecursive = numRecursive;
	}
}