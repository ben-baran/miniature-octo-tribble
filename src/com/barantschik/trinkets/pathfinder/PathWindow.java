package com.barantschik.trinkets.pathfinder;

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

public class PathWindow extends JFrame implements ActionListener
{
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	private JMenuBar menuBar;
	private JMenu fileMenu, editMenu, analysisMenu, helpMenu;
	private JMenuItem newWindow, save, saveAsImage, load, preferences;
	private PathFinder pf = new PathFinder();
	private JFileChooser fc;
	private PreferencesChooser pc;
	
	public PathWindow()
	{
		
		setSize(SIZE_X, SIZE_Y);
		setLocationRelativeTo(null);
		setTitle("A* Algorithm");
		
		getContentPane().add(pf);
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		analysisMenu = new JMenu("Analysis");
		helpMenu = new JMenu("Help");
		newWindow = new JMenuItem("New Window");
		
		save = new JMenuItem("Save File");
		saveAsImage = new JMenuItem("Save as Image");
		load = new JMenuItem("Load File");
		newWindow.addActionListener(this);
		save.addActionListener(this);
		saveAsImage.addActionListener(this);
		load.addActionListener(this);
		fileMenu.add(newWindow);
		fileMenu.add(save);
		fileMenu.add(saveAsImage);
		fileMenu.add(load);
		
		preferences = new JMenuItem("Preferences");
		preferences.addActionListener(this);
		helpMenu.add(preferences);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(analysisMenu);
		menuBar.add(helpMenu);
		setJMenuBar(menuBar);
		pack();
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				pf.getTimer().stop();
			}
		});
	}
	
	public PathWindow(File f)
	{
		super();
		pf = new PathFinder(f);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("New Window")) new PathWindow();
		else if(e.getActionCommand().equals("Load File"))
		{
			fc = new JFileChooser("./res/PathFinder/docs");
			int accepted = fc.showOpenDialog(PathWindow.this);
			if(accepted == JFileChooser.APPROVE_OPTION)
			{
				File f = fc.getSelectedFile();
				pf.reset(f);
			}
		}
		else if(e.getActionCommand().equals("Save File"))
		{
			fc = new JFileChooser("./res/PathFinder/docs");
			int accepted = fc.showSaveDialog(PathWindow.this);
			if(accepted == JFileChooser.APPROVE_OPTION)
			{
				File f = fc.getSelectedFile();
				try
				{
					if(!f.exists()) f.createNewFile();
				}
				catch(Exception err)
				{
					err.printStackTrace();
				}
				pf.putMapInFile(f);
			}
		}
		else if(e.getActionCommand().equals("Save as Image"))
		{
			fc = new JFileChooser("./res/PathFinder/images/usr");
			int accepted = fc.showSaveDialog(PathWindow.this);
			if(accepted == JFileChooser.APPROVE_OPTION)
			{
				File f = fc.getSelectedFile();
				pf.saveAsImage(f);
			}
		}
		else if(e.getActionCommand().equals("Preferences"))
		{
			pc = new PreferencesChooser();
			
		}
	}
}