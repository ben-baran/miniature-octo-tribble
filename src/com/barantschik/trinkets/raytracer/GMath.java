package com.barantschik.trinkets.raytracer;

import java.util.Arrays;

public abstract class GMath
{
	public static double[] normalize(double[] vec)
	{
		vec = Arrays.copyOf(vec, vec.length);
		double magnitude = getMag(vec);
		for(int i = 0; i < vec.length; i++) vec[i] /= magnitude;
		return vec;
	}

	public static double getMag(double[] vec)
	{
		double magnitude = 0;
		for(double d : vec) magnitude += d * d;
		magnitude = Math.sqrt(magnitude);
		return magnitude;
	}

	public static double[] subtract(double[] a, double[] b)
	{
		b = Arrays.copyOf(b,  b.length);
		for(int i = 0; i < b.length; i++)
		{
			b[i] -= a[i];
		}
		return b;
	}

	public static double det(double a, double b, double c, double d)
	{
		return a * d - b * c;
	}

	public static double[] cross(double[] a, double[] b)
	{
		double x = det(a[1], a[2], b[1], b[2]);
		double y = -det(a[0], a[2], b[0], b[2]);
		double z = det(a[0], a[1], b[0], b[1]);

		return new double[]{x, y, z};
	}

	public static double dot(double[] a, double[] b)
	{
		double total = 0;
		for(int i = 0; i < a.length; i++)
		{
			total += a[i] * b[i];
		}
		return total;
	}

	public static double[] mult(double[] vals, double scal)
	{
		vals = Arrays.copyOf(vals,  vals.length);
		for(int i = 0; i < vals.length; i++) vals[i] *= scal;
		return vals;
	}

	public static double[] negative(double[] a)
	{
		a = Arrays.copyOf(a, a.length);
		for(int i = 0; i < a.length; i++) a[i] = -a[i];
		return a;
	}

	public static double[] add(double[]... vals)
	{
		vals = Arrays.copyOf(vals, vals.length);
		double[] total = new double[vals[0].length];
		for(double[] dA : vals)
		{
			for(int i = 0; i < dA.length; i++)
			{
				total[i] += dA[i];
			}
		}
		return total;
	}

	public static double[] solveQuad(double a, double b, double c)
	{
		double discriminant = b * b - 4 * a * c;
		if(discriminant < 0)
		{
			return new double[]{Double.NaN};
		}
		else
		{
			double sqrtDiscriminant = Math.sqrt(discriminant);
			double firstAnswer = (-b + sqrtDiscriminant) / (2 * a);
			double secondAnswer = (-b - sqrtDiscriminant) / (2 * a);
			return new double[]{firstAnswer, secondAnswer};
		}
	}

	public static double[] findAverage(double[] a, double[] b)
	{
		return mult(add(a, b), 0.5);
	}

	public static double[] getHalfAngle(double[] view, double[] light, double[] normal)
	{
		view = normalize(view);
		light = normalize(light);
		double[] average = findAverage(view, light);
		if(average[0] == 0 && average[1] == 0 && average[2] == 0) return normalize(normal);
		else return normalize(average);
	}
}
