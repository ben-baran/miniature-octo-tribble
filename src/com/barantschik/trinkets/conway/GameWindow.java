package com.barantschik.trinkets.conway;

import javax.swing.JFrame;

public class GameWindow extends JFrame
{
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	
	public GameWindow()
	{
		setSize(SIZE_X, SIZE_Y);
		setLocationRelativeTo(null);
		setTitle("Fractal Generator");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().add(new Conway());
		pack();
		
		setVisible(true);
	}
}
