package com.barantschik.trinkets.raytracer.rendering;


public class ScenePreferences
{
	public final static float[] DEFAULT_AMBIENT = GMath.empty();
	public final static double[] DEFAULT_DEFAULT_ATTENUATION = {1, 0, 0};
	
	public final static double DEFAULT_FLOAT_ADJUST = 0.000001;
	public final static int DEFAULT_NUM_RECURSIVE = 5;
	public final static double DEFAULT_RECURSIVE_THRESHOLD = 0.001;
	
	private int sizeX, sizeY;
	
	private Sampler aaProvider;
	
	private float[] ambient;
	private double[] defaultAttenuation;
	
	private double floatAdjust;
	private int numRecursive;
	private double recursiveThreshold;
	
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
		recursiveThreshold = DEFAULT_RECURSIVE_THRESHOLD;
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

	public double getRecursiveThreshold()
	{
		return recursiveThreshold;
	}

	public void setRecursiveThreshold(double recursiveThreshold)
	{
		this.recursiveThreshold = recursiveThreshold;
	}
}