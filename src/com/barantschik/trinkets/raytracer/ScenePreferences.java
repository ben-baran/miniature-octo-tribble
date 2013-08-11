package com.barantschik.trinkets.raytracer;

public class ScenePreferences
{
	private int sizeX, sizeY;
	
	private Sampler aaProvider;
	
	private float ambient;
	private double[] defaultAttenuation;
	
	public ScenePreferences(int sizeX, int sizeY)
	{
		this(sizeX, sizeY, new CenterSampler());
	}
	
	public ScenePreferences(int sizeX, int sizeY, Sampler aaProvider)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.aaProvider = aaProvider;
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
	
	public float getAmbient()
	{
		return ambient;
	}

	public void setAmbient(float ambient)
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
}