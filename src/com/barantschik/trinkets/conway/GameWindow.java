package com.barantschik.trinkets.conway;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameWindow extends JFrame implements ActionListener
{
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	private Conway conway = new Conway();
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newWindow;
	
	public GameWindow()
	{
		setSize(SIZE_X, SIZE_Y);
		setLocationRelativeTo(null);
		setTitle("Conway's Game of Life");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().add(conway);
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		newWindow = new JMenuItem("New Window");
		newWindow.addActionListener(this);
		fileMenu.add(newWindow);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
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

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("New Window"))
		{
			new GameWindow();
		}
	}
}
