package com.barantschik.trinkets.fractal;

import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class FractalWindow extends JFrame
{
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	
	public FractalWindow()
	{
		setSize(SIZE_X, SIZE_Y);
		setLocationRelativeTo(null);
		setTitle("Fractal Generator");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().add(new Fractal());
		pack();
		
		setVisible(true);
	}
}
