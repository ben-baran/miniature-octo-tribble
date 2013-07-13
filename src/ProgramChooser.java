import javax.swing.JFrame;

public class ProgramChooser extends JFrame
{
	
	private final int SIZE_X = 400, SIZE_Y = 800;
	
	public static void main(String[] args)
	{
		new ProgramChooser();
	}
	
	public ProgramChooser()
	{
		setSize(SIZE_X, SIZE_Y);
		setTitle("Trinkets");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
