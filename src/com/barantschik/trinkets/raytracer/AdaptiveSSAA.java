package com.barantschik.trinkets.raytracer;

public class AdaptiveSSAA extends UniformSampler implements AAProvider
{
	public static final double DEFAULT_EPSILON = 0.2485;
	
	private int curNumIterations = 1;
	private int squareIterations = 1;
	private double epsilon;
	
	public AdaptiveSSAA()
	{
		this(1);
	}
	
	public AdaptiveSSAA(int numAA)
	{
		this(numAA, DEFAULT_EPSILON);
	}
	
	public AdaptiveSSAA(int numAA, double epsilon)
	{
		super(numAA);
		this.epsilon = epsilon;
	}

	public AAData createPixelData(float[][] colors)
	{
		float[] r = new float[colors.length], g = new float[colors.length], b = new float[colors.length];
		for(int i = 0; i < colors.length; i++)
		{
			r[i] = colors[i][0];
			g[i] = colors[i][1];
			b[i] = colors[i][2];
		}
		
		double averageStandardDeviation = GMath.average(GMath.standardDeviation(r), GMath.standardDeviation(g), GMath.standardDeviation(b));
		if(averageStandardDeviation < epsilon)
		{
			float averageR = 0, averageG = 0, averageB = 0;
			for(int i = 0; i < colors.length; i++)
			{
				averageR += colors[i][0];
				averageG += colors[i][1];
				averageB += colors[i][2];
			}
			averageR /= colors.length;
			averageG /= colors.length;
			averageB /= colors.length;
			
			curNumIterations = 1;
			squareIterations = 1;
			
			numAAInverse = 1.0 / numAA;
			numAAMap = numAA * numAA;
			
			return new AAData(true, new float[]{averageR, averageG, averageB});
		}
		System.out.println(averageStandardDeviation + ": " + (curNumIterations + 1));
		curNumIterations++;
		squareIterations = curNumIterations * curNumIterations;
		
		numAAInverse = 1.0 / (numAA * curNumIterations);
		numAAMap = numAA * numAA * squareIterations;
		
		return new AAData(false, null);
	}
}