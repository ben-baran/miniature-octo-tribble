package com.barantschik.trinkets;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ProgramChooser extends JFrame implements ActionListener
{
	
	private final int SIZE_X = 400, SIZE_Y = 800;
	
	public static void main(String[] args)
	{
		new ProgramChooser();
	}
	
	public ProgramChooser()
	{
//		setSize(SIZE_X, SIZE_Y);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JButton pathfinder = new JButton("Open PathFinder");
		pathfinder.addActionListener(this);
		pathfinder.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(pathfinder);
		
		JButton fractal = new JButton("Open Fractal Generator");
		fractal.addActionListener(this);
		fractal.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(fractal);
		
		JButton conway = new JButton("Open Conway's Game of Life");
		conway.addActionListener(this);
		conway.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(conway);
		
		JButton raytracer = new JButton("Open Raytracer");
		raytracer.addActionListener(this);
		raytracer.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(raytracer);
		
		pack();
		
		setTitle("Trinkets");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Open PathFinder"))
		{
			new com.barantschik.trinkets.pathfinder.PathWindow();
		}
		else if(e.getActionCommand().equals("Open Fractal Generator"))
		{
			new com.barantschik.trinkets.fractal.FractalWindow();
		}
		else if(e.getActionCommand().equals("Open Conway's Game of Life"))
		{
			new com.barantschik.trinkets.conway.GameWindow();
		}
		else if(e.getActionCommand().equals("Open Raytracer"))
		{
			new com.barantschik.trinkets.raytracer.Raytracer();
		}
	}
}
