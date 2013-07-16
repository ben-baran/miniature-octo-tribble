package com.barantschik.trinkets.conway;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Conway extends JPanel implements MouseListener, MouseMotionListener, KeyListener, ActionListener
{
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	private final int NUM_X = 100, NUM_Y = 100;
	private final int BLOCK_X = SIZE_X / NUM_X, BLOCK_Y = SIZE_Y / NUM_Y;
	private boolean running = false;
	private boolean[][] map = new boolean[NUM_X][NUM_Y];
	private boolean[][] premap = map;
	
	private Timer time = new Timer(0, this);
	
	public Conway()
	{
		setSize(SIZE_X, SIZE_Y);
		setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		setFocusable(true);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		
		time.setRepeats(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		update();
		repaint();
	}
	
	public void update()
	{
//		premap = map;
		for(int i = 0; i < premap.length; i++)
		{
			for(int j = 0; j < premap[0].length; j++)
			{
				int numAlive = getNumNeighbors(i, j);
				if(premap[i][j] == true)
				{
					if(!(numAlive == 2 || numAlive == 3)) premap[i][j] = false;
				}
				else if(numAlive == 3) premap[i][j] = true;
			}
		}
		map = premap;
	}
	
	public int getNumNeighbors(int x, int y)
	{
		int num = 0;
		
		for(int i = -1; i < 2; i++)
		{
			for(int j = -1; j < 2; j++)
			{
				if(!(i == 0 && j == 0))
				{
					if(alive(x + i, y + j)) num++;
				}
			}
		}
		
		return num;
	}
	
	public boolean alive(int x, int y)
	{
		if(x != -1 && x != NUM_X && y != -1 && y != NUM_Y) return map[x][y];
		return false;
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SIZE_X, SIZE_Y);
		g.setColor(Color.BLACK);
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[0].length; j++)
			{
				if(map[i][j] == true) g.fillRect(i * BLOCK_X, j * BLOCK_Y, BLOCK_X, BLOCK_Y);
			}
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			running ^= true;
			if(!running) time.stop();
			else time.start();
		}
	}
	public void mouseClicked(MouseEvent e)
	{
		if(!running)
		{			
			int selectedX = e.getX() / BLOCK_X, selectedY = e.getY() / BLOCK_Y;
			map[selectedX][selectedY] ^= true;
			repaint();
		}
	}
	
	public void mouseDragged(MouseEvent e)
	{
		if(!running)
		{			
			int selectedX = e.getX() / BLOCK_X, selectedY = e.getY() / BLOCK_Y;
			map[selectedX][selectedY] = true;
			repaint();
		}
	}
	
	public Timer getTimer()
	{
		return time;
	}
	
	//Unimplemented methods
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
}