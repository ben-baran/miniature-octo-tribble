package com.barantschik.trinkets.conway;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.barantschik.trinkets.pathfinder.PathWindow;

public class GameWindow extends JFrame implements ActionListener
{
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	private Conway conway = new Conway();
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newWindow, loadFile;
	private JFileChooser fc;
	
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
		loadFile = new JMenuItem("Load File");
		loadFile.addActionListener(this);
		
		fileMenu.add(newWindow);
		fileMenu.add(loadFile);
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
		else if(e.getActionCommand().equals("Load File"))
		{
			fc = new JFileChooser("./res/Conway/docs");
			int accepted = fc.showOpenDialog(GameWindow.this);
			if(accepted == JFileChooser.APPROVE_OPTION)
			{
				File f = fc.getSelectedFile();
				getContentPane().remove(conway);
				conway = new Conway(f);
				getContentPane().add(conway);
				pack();
			}
		}
	}
}
