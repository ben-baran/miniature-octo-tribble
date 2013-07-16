package com.barantschik.trinkets.conway;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GameWindow extends JFrame
{
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	private Conway conway = new Conway();
	
	public GameWindow()
	{
		setSize(SIZE_X, SIZE_Y);
		setLocationRelativeTo(null);
		setTitle("Conway's Game of Life");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().add(conway);
		pack();
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				conway.getTimer().stop();
			}
		});
	}
}
