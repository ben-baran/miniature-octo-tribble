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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PathFinder extends JPanel implements KeyListener, MouseListener, MouseMotionListener, ActionListener
{
	private int numX = 50, numY = 50;
	private int startX = 2, startY = 2;
	private int endX = 49, endY = 49;
	
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	private int boxX = SIZE_X / numX, boxY = SIZE_Y / numY;
	private boolean buildPhase = true;
	private Node[][] map = new Node[numX][numY];
	private boolean done = false;
	private boolean erase = false;
	private int hoverX = -1, hoverY = -1;
	
	private int curX = startX, curY = startY;
	
	private Timer time = new Timer(1, this);
	
	public PathFinder()
	{
		setSize(SIZE_X, SIZE_Y);
		setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		setFocusable(true);
		setVisible(true);
		
		addKeyListener(this);
		addMouseMotionListener(this);
		
		for(int i = 0; i < numX; i++)
		{
			for(int j = 0; j < numY; j++)
			{
				map[i][j] = new Node(i, j);
			}
		}
	}
	
	public PathFinder(File f)
	{
		setSize(SIZE_X, SIZE_Y);
		setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		setFocusable(true);
		setVisible(true);
		
		addKeyListener(this);
		addMouseMotionListener(this);
		
		Scanner scan = null;
		try
		{
			scan = new Scanner(f);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		numX = Integer.parseInt(scan.nextLine());
		numY = Integer.parseInt(scan.nextLine());
		map = new Node[numX][numY];
		boxX = SIZE_X / numX;
		boxY = SIZE_Y / numY;
		curX = startX;
		curY = startY;
		for(int i = 0; i < numY; i++)
		{
			String curLine = scan.nextLine();
			for(int j = 0; j < numX; j++)
			{
				map[j][i] = new Node(j, i);
				if(curLine.charAt(j) == 's')
				{
					startX = j;
					startY = i;
				}
				else if(curLine.charAt(j) == 'e')
				{
					endX = j;
					endY = i;
				}
				else if(curLine.charAt(j) == 'w') map[j][i].type = NodeType.WALL;
				else map[j][i].type = NodeType.NONE;
			}
		}
	}
	
	public void reset(File f)
	{
		buildPhase = true;
		done = false;
		erase = false;
		
		Scanner scan = null;
		try
		{
			scan = new Scanner(f);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		numX = Integer.parseInt(scan.nextLine());
		numY = Integer.parseInt(scan.nextLine());
		map = new Node[numX][numY];
		boxX = SIZE_X / numX;
		boxY = SIZE_Y / numY;
		for(int i = 0; i < numY; i++)
		{
			String curLine = scan.nextLine();
			for(int j = 0; j < numX; j++)
			{
				map[j][i] = new Node(j, i);
				if(curLine.charAt(j) == 's')
				{
					startX = j;
					startY = i;
				}
				else if(curLine.charAt(j) == 'e')
				{
					endX = j;
					endY = i;
				}
				else if(curLine.charAt(j) == 'w') map[j][i].type = NodeType.WALL;
				else map[j][i].type = NodeType.NONE;
			}
		}
		curX = startX;
		curY = startY;
		repaint();
	}
	
	public void putMapInFile(File f)
	{
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()));
		}
		catch (IOException e)
		{
			System.out.println("Could not open bufferedwriter to file.");
			e.printStackTrace();
		}
		
		try
		{
			bw.write(numX + "\n" + numY + "\n");
			for(int i = 0; i < numY; i++)
			{
				for(int j = 0; j < numX; j++)
				{
					if(j == startX && i == startY) bw.write("s");
					else if(j == endX && i == endY) bw.write("e");
					else if(map[j][i].type == NodeType.WALL) bw.write("w");
					else bw.write(".");
				}
				bw.write("\n");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			bw.close();
		}
		catch (IOException e)
		{
			System.out.println("Could not close bufferwriter stream.");
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SIZE_X, SIZE_Y);
		
		for(int i = 0; i < numX; i++)
		{
			for(int j = 0; j < numY; j++)
			{
				if(map[i][j].type == NodeType.WALL)
				{
					g.setColor(Color.BLACK);
					g.fillRect(i * boxX, j * boxY, boxX, boxY);
				}
				else if(map[i][j].type == NodeType.OPEN)
				{
					g.setColor(Color.ORANGE);
					g.fillRect(i * boxX, j * boxY, boxX, boxY);
				}
				else if(map[i][j].type == NodeType.CLOSED)
				{
					g.setColor(Color.CYAN);
					g.fillRect(i * boxX, j * boxY, boxX, boxY);
				}
			}
		}
		
		if(buildPhase)
		{
			if(!erase)
			{
				g.setColor(Color.BLACK);
			}
			else
			{
				g.setColor(Color.WHITE);
			}
			g.fillRect(hoverX * boxX, hoverY * boxY, boxX, boxY);
		}
		
		g.setColor(Color.RED);
		Node curNode = map[curX][curY];
		while(curNode != null)
		{
			g.fillRect(curNode.x * boxX, curNode.y * boxY, boxX, boxY);
			curNode = curNode.getParent();
		}
		
		g.setColor(Color.BLUE);
		g.fillRect(startX * boxX, startY * boxY, boxX, boxY);
		
		g.setColor(Color.GREEN);
		g.fillRect(endX * boxX, endY * boxY, boxX, boxY);
	}

	public void mouseDragged(MouseEvent e)
	{
		if(buildPhase && e.getX() <= SIZE_X && e.getX() >= 0 && e.getY() <= SIZE_Y && e.getY() >= 0)
		{
			if(!erase) map[e.getX() / boxX][e.getY() / boxY].type = NodeType.WALL;
			else map[e.getX() / boxX][e.getY() / boxY].type = NodeType.NONE;
			repaint();
		}
	}
	
	public void mouseMoved(MouseEvent e)
	{
		if(buildPhase)
		{
			hoverX = e.getX() / boxX;
			hoverY = e.getY() / boxY;
			repaint();
		}
	}
	
	public void mousePressed(MouseEvent e){}
	
	public void mouseReleased(MouseEvent e){}
	
	public void calculateNextPath()
	{
		if(curX > 0)
		{
			if(map[curX - 1][curY].type == NodeType.NONE)
			{				
				map[curX - 1][curY].type = NodeType.OPEN;
				map[curX - 1][curY].setParent(map[curX][curY], 10);
			}
			else if(map[curX - 1][curY].type == NodeType.OPEN)
			{
				if(map[curX][curY].getGCost() + 10 < map[curX - 1][curY].getGCost()) map[curX - 1][curY].setParent(map[curX][curY], 10);
			}
			if(curY > 0 && !(map[curX - 1][curY].type == NodeType.WALL || map[curX][curY - 1].type == NodeType.WALL))
			{
				if(map[curX - 1][curY - 1].type == NodeType.NONE)
				{
					map[curX - 1][curY - 1].type = NodeType.OPEN;
					map[curX - 1][curY - 1].setParent(map[curX][curY], 14);
				}
				else if(map[curX - 1][curY - 1].type == NodeType.OPEN)
				{
					if(map[curX][curY].getGCost() + 14 < map[curX - 1][curY - 1].getGCost()) map[curX - 1][curY - 1].setParent(map[curX][curY], 14);
				}
			}
			if(curY < numY - 1 && !(map[curX - 1][curY].type == NodeType.WALL || map[curX][curY + 1].type == NodeType.WALL))
			{
				if(map[curX - 1][curY + 1].type == NodeType.NONE)
				{
					map[curX - 1][curY + 1].type = NodeType.OPEN;
					map[curX - 1][curY + 1].setParent(map[curX][curY], 14);
				}
				else if(map[curX - 1][curY + 1].type == NodeType.OPEN)
				{
					if(map[curX][curY].getGCost() + 14 < map[curX - 1][curY + 1].getGCost()) map[curX - 1][curY + 1].setParent(map[curX][curY], 14);
				}
			}
		}
		if(curX < numX - 1)
		{			
			if(map[curX + 1][curY].type == NodeType.NONE)
			{				
				map[curX + 1][curY].type = NodeType.OPEN;
				map[curX + 1][curY].setParent(map[curX][curY], 10);
			}
			else if(map[curX + 1][curY].type == NodeType.OPEN)
			{
				if(map[curX][curY].getGCost() + 10 < map[curX + 1][curY].getGCost()) map[curX + 1][curY].setParent(map[curX][curY], 10);
			}
			if(curY > 0 && !(map[curX + 1][curY].type == NodeType.WALL || map[curX][curY - 1].type == NodeType.WALL))
			{
				if(map[curX + 1][curY - 1].type == NodeType.NONE)
				{
					map[curX + 1][curY - 1].type = NodeType.OPEN;
					map[curX + 1][curY - 1].setParent(map[curX][curY], 14);
				}
				else if(map[curX + 1][curY - 1].type == NodeType.OPEN)
				{
					if(map[curX][curY].getGCost() + 14 < map[curX + 1][curY - 1].getGCost()) map[curX + 1][curY - 1].setParent(map[curX][curY], 14);
				}
			}
			if(curY < numY - 1 && !(map[curX + 1][curY].type == NodeType.WALL || map[curX][curY + 1].type == NodeType.WALL))
			{
				if(map[curX + 1][curY + 1].type == NodeType.NONE)
				{
					map[curX + 1][curY + 1].type = NodeType.OPEN;
					map[curX + 1][curY + 1].setParent(map[curX][curY], 14);
				}
				else if(map[curX + 1][curY + 1].type == NodeType.OPEN)
				{
					if(map[curX][curY].getGCost() + 14 < map[curX + 1][curY + 1].getGCost()) map[curX + 1][curY + 1].setParent(map[curX][curY], 14);
				}
			}
		}
		if(curY > 0)
		{
			if(map[curX][curY - 1].type == NodeType.NONE)
			{
				map[curX][curY - 1].type = NodeType.OPEN;
				map[curX][curY - 1].setParent(map[curX][curY], 10);
			}
			else if(map[curX][curY - 1].type == NodeType.OPEN)
			{
				if(map[curX][curY].getGCost() + 10 < map[curX][curY - 1].getGCost()) map[curX][curY - 1].setParent(map[curX][curY], 10);
			}
		}
		if(curY < numY - 1)
		{
			if(map[curX][curY + 1].type == NodeType.NONE)
			{
				map[curX][curY + 1].type = NodeType.OPEN;
				map[curX][curY + 1].setParent(map[curX][curY], 10);
			}
			else if(map[curX][curY + 1].type == NodeType.OPEN)
			{
				if(map[curX][curY].getGCost() + 10 < map[curX][curY + 1].getGCost()) map[curX][curY + 1].setParent(map[curX][curY], 10);
			}
		}
		
		map[curX][curY].type = NodeType.CLOSED;
		int lowestFCost = Integer.MAX_VALUE;
		for(int i = 0; i < numX && !done; i++)
		{
			for(int j = 0; j < numY && !done; j++)
			{
				if(map[i][j].type == NodeType.OPEN)
				{
					if(map[i][j].getGCost() + (Math.abs(endX - i) + Math.abs(endY - j)) * 10 < lowestFCost)
					{
						curX = i;
						curY = j;
						lowestFCost = map[i][j].getGCost() + (Math.abs(endX - i) + Math.abs(endY - j)) * 10;
						if(curX == endX && curY == endY)
						{
							time.stop();
							done = true;
							repaint();
						}
					}
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(buildPhase)
			{				
				buildPhase = false; 
				time.setRepeats(true);
				time.start();
			}
			else
			{
				buildPhase = true;
				time.stop();
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_Q && buildPhase)
		{
			erase ^= true;
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		calculateNextPath();
		repaint();
	}
	
	//Unimplemented methods
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
}