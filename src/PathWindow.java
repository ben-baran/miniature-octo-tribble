import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PathWindow extends JFrame implements ActionListener
{
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newWindow, save, load;
	private PathFinder pf = new PathFinder();
	private JFileChooser fc;
	
	public static void main(String[] args)
	{
		new PathWindow();
	}
	public PathWindow()
	{
		
		setSize(SIZE_X, SIZE_Y);
		setLocationRelativeTo(null);
		setTitle("A* Algorithm");
		
		getContentPane().add(pf);
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		newWindow = new JMenuItem("New Window");
		save = new JMenuItem("Save File");
		load = new JMenuItem("Load File");
		newWindow.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		fileMenu.add(newWindow);
		fileMenu.add(save);
		fileMenu.add(load);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		pack();
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public PathWindow(File f)
	{
		pf = new PathFinder(f);
		
		setSize(SIZE_X, SIZE_Y);
		setLocationRelativeTo(null);
		setTitle("A* Algorithm");
		
		getContentPane().add(pf);
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		newWindow = new JMenuItem("New Window");
		save = new JMenuItem("Save File");
		load = new JMenuItem("Load File");
		newWindow.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		fileMenu.add(newWindow);
		fileMenu.add(save);
		fileMenu.add(load);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		pack();
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("New Window")) new PathWindow();
		else if(e.getActionCommand().equals("Load File"))
		{
			fc = new JFileChooser("./docs");
			int accepted = fc.showOpenDialog(PathWindow.this);
			if(accepted == JFileChooser.APPROVE_OPTION)
			{
				File f = fc.getSelectedFile();
				pf.reset(f);
			}
		}
		else if(e.getActionCommand().equals("Save File"))
		{
			fc = new JFileChooser("./docs");
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
	}
}