import javax.swing.JFrame;

public class PreferencesChooser extends JFrame
{
	private final int SIZE_X = 400, SIZE_Y = 800;
	
	public PreferencesChooser()
	{
		setSize(SIZE_X, SIZE_Y);
		setTitle("Preferences");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
