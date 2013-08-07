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

import com.barantschik.trinkets.Container;

public class PathWindow extends Container implements ActionListener
{
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	private PathFinder pf = new PathFinder();
	private JFileChooser fc;
	private PreferencesChooser pc;
	
	public PathWindow()
	{
		setTitle("A* Algorithm");
		
		addMenu("File", new String[]{"New Window", "Save File", "Save as Image", "Load File"});
		addMenu("Edit", new String[]{});
		addMenu("Analysis", new String[]{});
		addMenu("Help", new String[]{"Preferences"});
		
		getContentPane().add(pf);
		pack();
		
		setLocationRelativeTo(null);
		
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