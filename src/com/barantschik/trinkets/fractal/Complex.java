package com.barantschik.trinkets.fractal;

public class Complex
{
	private double real = 0, imaginary = 0;
	private boolean outsideRange = false;

	public Complex(double real, double imaginary)
	{
		this.setR(real);
		this.setI(imaginary);
	}

	public double getR()
	{
		return real;
	}

	private void setR(double real)
	{
		this.real = real;
	}

	public double getI()
	{
		return imaginary;
	}

	private void setI(double imaginary)
	{
		this.imaginary = imaginary;
	}

	public void add(Complex other)
	{
		this.setR(this.getR() + other.getR());
		this.setI(this.getI() + other.getI());
	}

	public void square()
	{
		double oldR = this.getR();
		this.setR(this.getR() * this.getR() - this.getI() * this.getI());
		this.setI(2 * oldR * this.getI());
	}

	public void toTheN(int n)
	{
		Complex rightSide = this;
		for(int i = 0; i < n - 1; i++)
		{
			rightSide = multZ(this, rightSide);
		}
		this.setR(rightSide.getR());
		this.setI(rightSide.getI());
	}


	private static Complex multZ(Complex a, Complex b)
	{
		return new Complex(a.getR() * b.getR() - a.getI() * b.getI(), a.getR() * b.getI() + a.getI() * b.getR());
	}

	public void subtract(Complex z)
	{
		this.setR(this.getR() - z.getR());
		this.setI(this.getI() - z.getI());
	}

	public void subtract(double val)
	{
		this.setR(this.getR() - val);
		this.setI(this.getI() - val);
	}

	public void mult(double val)
	{
		this.setR(this.getR() * val);
		this.setR(this.getI() * val);
	}

	public void mult(Complex z)
	{
		double oldR = this.getR();
		this.setR(this.getR() * z.getR() - this.getI() * z.getI());
		this.setI(oldR * z.getI() + this.getI() * z.getR());
	}

	public void naturalLog()
	{
		double oldR = this.getR();
		this.setR(this.magnitude());
		double ratio = this.getI() / oldR;
		boolean switchSign = oldR < 0;
		double angle = 0;
		if(!switchSign)
		{
			angle = Math.atan(ratio);
		}
		else
		{
			angle = Math.PI + Math.atan(ratio);
		}
		this.setI(angle);
	}

	public void divideBy(Complex z)
	{
		Complex conjugateDen = new Complex(z.getR(), -z.getI());
		Complex newC = Complex.multZ(conjugateDen, this);
		double newDenom = z.getR() * z.getR() + z.getI() * z.getI();
		this.setR(newC.getR() / newDenom);
		this.setI(newC.getI() / newDenom);
	}

	public void raiseETo()
	{
		double coeff = Math.exp(this.getR());
		this.setR(coeff * Math.cos(this.getI()));
		this.setI(coeff * Math.sin(this.getI()));
	}

	public void sineOf()
	{
		double oldR = this.getR();
		this.setR(Math.sin(this.getR()) * Math.cosh(this.getI()));
		this.setI(Math.sinh(this.getI()) * Math.cos(oldR));
	}

	public double magnitude()
	{
		return Math.sqrt(this.getR() * this.getR() + this.getI() * this.getI());
	}

	public void outsideRange()
	{
		outsideRange = true;
	}

	public boolean isOutsideRange()
	{
		return outsideRange;
	}
}