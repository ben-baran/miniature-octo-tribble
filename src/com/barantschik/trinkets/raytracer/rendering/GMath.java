package com.barantschik.trinkets.raytracer.rendering;

import java.util.Arrays;


public abstract class GMath
{
	public static double[] normalize(double[] vec)
	{
		vec = Arrays.copyOf(vec, vec.length);
		double magnitude = getMag(vec);
		if(magnitude == 0)
		{
			return null;
		}
		else
		{			
			for(int i = 0; i < vec.length; i++) vec[i] /= magnitude;
			return vec;
		}
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

	public static double det(M3x3 matrix)
	{
		double a = matrix.values[0][0] * det(matrix.getMinor(0, 0));
		double b = -matrix.values[1][0] * det(matrix.getMinor(1, 0));
		double c = matrix.values[2][0] * det(matrix.getMinor(2, 0));
		return a + b + c;
	}
	
	public static double det(M4x4 matrix)
	{
		double a = matrix.values[0][0] * det(matrix.getMinor(0, 0));
		double b = -matrix.values[1][0] * det(matrix.getMinor(1, 0));
		double c = matrix.values[2][0] * det(matrix.getMinor(2, 0));
		double d = -matrix.values[3][0] * det(matrix.getMinor(3, 0));
		return a + b + c + d;
	}
	
	public static double det(double a, double b, double c, double d)
	{
		return a * d - b * c;
	}
	
	public static double det(double[] matrix)
	{
		return matrix[0] * matrix[3] - matrix[1] * matrix[2];
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

	public static float dot(float[] a, float[] b)
	{
		float total = 0;
		for(int i = 0; i < a.length; i++)
		{
			total += a[i] * b[i];
		}
		return total;
	}

	public static double[] mult(double[] a, double[] b)
	{
		double[] product = new double[a.length];
		for(int i = 0; i < a.length; i++)
		{
			product[i] += a[i] * b[i];
		}
		return product;
	}
	
	public static float[] mult(float[] a, float[] b)
	{
		float[] product = new float[a.length];
		for(int i = 0; i < a.length; i++)
		{
			product[i] += a[i] * b[i];
		}
		return product;
	}
	
	public static double[] mult(double[] vals, double scal)
	{
		vals = Arrays.copyOf(vals, vals.length);
		for(int i = 0; i < vals.length; i++) vals[i] *= scal;
		return vals;
	}
	
	public static float[] mult(float[] vals, float scal)
	{
		vals = Arrays.copyOf(vals, vals.length);
		for(int i = 0; i < vals.length; i++) vals[i] *= scal;
		return vals;
	}

	public static M3x3 mult(M3x3 matrix, double scal)
	{
		matrix = new M3x3(matrix.values);
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				matrix.values[i][j] = matrix.values[i][j] * scal;
			}
		}
		return matrix;
	}
	
	public static M4x4 mult(M4x4 matrix, double scal)
	{
		matrix = new M4x4(matrix.values);
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				matrix.values[i][j] = matrix.values[i][j] * scal;
			}
		}
		return matrix;
	}
	
	public static double[] mult(M3x3 matrix, double[] vector)
	{
		double[] values = new double[3];
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				values[i] += vector[j] * matrix.values[i][j];
			}
		}
		return values;
	}

	public static double[] mult(M4x4 matrix, double[] vector)
	{
		double[] solution = new double[4];
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				solution[i] += matrix.values[i][j] * vector[j];
			}
		}
		return solution;
	}
	
	public static M4x4 mult(M4x4 a, M4x4 b)
	{
		M4x4 solution = new M4x4();
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				for(int k = 0; k < 4; k++)
				{
					solution.values[i][j] += a.values[i][k] * b.values[k][j];
				}
			}
		}
		return solution;
	}
	
	public static double[] negative(double[] a)
	{
		a = Arrays.copyOf(a, a.length);
		for(int i = 0; i < a.length; i++) a[i] = -a[i];
		return a;
	}

	public static float[] add(float[]... vals)
	{
		vals = Arrays.copyOf(vals, vals.length);
		float[] total = new float[vals[0].length];
		for(float[] fA : vals)
		{
			for(int i = 0; i < fA.length; i++)
			{
				total[i] += fA[i];
			}
		}
		return total;
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

	public static M3x3 constructMatrix(double[] a, double[] b, double[] c)
	{
		double[][] matrix = new double[3][3];
		for(int i = 0; i < 3; i++)
		{
			matrix[i][0] = a[i];
			matrix[i][1] = b[i];
			matrix[i][2] = c[i];
		}
		return new M3x3(matrix);
	}

	public static M3x3 findInverseMatrix(M3x3 original)
	{
		double determinant = det(original);
		if(determinant != 0)
		{
			return mult(getCofactors(findTranspose(original)), (1 / determinant));
		}
		return null;
	}
	
	public static M4x4 findInverseMatrix(M4x4 original)
	{
		double determinant = det(original);
		if(determinant != 0)
		{
			return mult(getCofactors(findTranspose(original)), (1 / determinant));
		}
		return null;
	}
	
	public static M3x3 getCofactors(M3x3 original)
	{
		double[][] values = new double[3][3];
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				values[i][j] = det(original.getMinor(i, j));
				if((i + j) % 2 == 1) values[i][j] = -values[i][j];
			}
		}
		return new M3x3(values);
	}
	
	public static M4x4 getCofactors(M4x4 original)
	{
		double[][] values = new double[4][4];
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				values[i][j] = det(original.getMinor(i, j));
				if((i + j) % 2 == 1) values[i][j] = -values[i][j];
			}
		}
		return new M4x4(values);
	}
	
	public static M3x3 findTranspose(M3x3 original)
	{
		double[][] values = new double[3][3];
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				values[i][j] = original.values[j][i];
			}
		}
		return new M3x3(values);
	}
	
	public static M4x4 findTranspose(M4x4 original)
	{
		double[][] values = new double[4][4];
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				values[i][j] = original.values[j][i];
			}
		}
		return new M4x4(values);
	}

	public static double[] createHomogenousPos(double[] original)
	{
		double[] solution = new double[4];
		for(int i = 0; i < 3; i++)
		{
			solution[i] = original[i];
		}
		solution[3] = 1;
		return solution;
	}
	
	public static double[] createHomogenousDir(double[] original)
	{
		double[] solution = new double[4];
		for(int i = 0; i < 3; i++)
		{
			solution[i] = original[i];
		}
		return solution;
	}

	public static double[] dehomogenize(double[] original)
	{
		double[] solution = new double[3];
		for(int i = 0; i < 3; i++)
		{
			solution[i] = original[i];
		}
		return solution;
	}
	
	public static double findT(double[] vector, Ray r)
	{
		double[] difference = subtract(createHomogenousPos(r.pos), vector);
		
		for(int i = 0; i < 3; i++)
		{
			if(r.dir[i] != 0) return difference[i] / r.dir[i];
		}
		
		return Double.NaN;
	}

	public static double[] reflectRay(double[] d, double[] n)
	{
		double[] solution = subtract(mult(mult(n, dot(d, n)), 2), d);
		if(solution != null) return solution;
		return new double[]{0, 1, 0};
	}

	public static float[] capColor(float[] color)
	{
		return new float[]{Math.min(color[0], 1), Math.min(color[1], 1), Math.min(color[2], 1)};
	}

	public static double average(double... values)
	{		
		double sum = 0;
		for(double d : values) sum += d;
		
		return sum / values.length;
	}
	
	public static float average(float... values)
	{		
		float sum = 0;
		for(float f : values) sum += f;
		
		return sum / values.length;
	}

	public static float standardDeviation(float... fs)
	{
		float average = average(fs);
		float sumSquareDifferences = 0;
		
		for(int i = 0; i < fs.length; i++)
		{
			float difference = fs[i] - average;
			sumSquareDifferences += difference * difference;
		}
		
		sumSquareDifferences /= fs.length;
		return (float) Math.sqrt(sumSquareDifferences);
	}
	
	public static double standardDeviation(double... fs)
	{
		double average = average(fs);
		double sumSquareDifferences = 0;
		
		for(int i = 0; i < fs.length; i++)
		{
			double difference = fs[i] - average;
			sumSquareDifferences += difference * difference;
		}
		
		sumSquareDifferences /= fs.length;
		return Math.sqrt(sumSquareDifferences);
	}

	public static Ray refract(double[] point, double[] normal, Ray r, float indexOfRefraction)
	{
		float ratio = r.lastIndex / indexOfRefraction;
		double cosI = GMath.dot(normal, r.dir);
		double sinThetaTSquared = ratio * ratio * (1 - cosI * cosI);
		double[] dir = GMath.add(GMath.mult(r.dir, ratio), GMath.mult(normal, ratio * cosI - Math.sqrt(1 - sinThetaTSquared)));
		
		Ray refracted = new Ray(point, dir);
		refracted.lastIndex = indexOfRefraction;
		return refracted;
	}

	public static float[] empty()
	{
		return new float[]{0, 0, 0};
	}
	
	public static float[] full()
	{
		return new float[]{1, 1, 1};
	}
}   