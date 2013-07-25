package com.barantschik.trinkets.raytracer;

public class ScenePreferences
{
	private int sizeX, sizeY;
	private AntialiasingType aaType;
	private int antialiasingMagnitude;
	
	public ScenePreferences(int sizeX, int sizeY)
	{
		this(sizeX, sizeY, AntialiasingType.NONE);
	}
	
	public ScenePreferences(int sizeX, int sizeY, AntialiasingType aaType)
	{
		this(sizeX, sizeY, aaType, 1);
	}
	
	public ScenePreferences(int sizeX, int sizeY, AntialiasingType aaType, int antialiasingMagnitude)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.aaType = aaType;
		this.antialiasingMagnitude = antialiasingMagnitude;
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
	
	public AntialiasingType getAAType()
	{
		return aaType;
	}
	
	public void setAAType(AntialiasingType aaType)
	{
		this.aaType = aaType;
	}

	
	public int getAntialiasingMagnitude()
	{
		return antialiasingMagnitude;
	}

	
	public void setAntialiasingMagnitude(int antialiasingMagnitude)
	{
		this.antialiasingMagnitude = antialiasingMagnitude;
	}
}