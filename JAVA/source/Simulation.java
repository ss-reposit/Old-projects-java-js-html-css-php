import javax.swing.JFrame;

public class Simulation extends JFrame {
	public static int P_WIDTH; //need to be static, easier then using instances (i.e. using ex)
	public static int P_HEIGHT;
	public static String P_COLOR = new String();
	private Config xConfig;
	private MyPanel panel;
	public Simulation() {
		initConfig();
	}

	private void initConfig() {

		 xConfig = new Config();
		P_WIDTH = xConfig.getPanelX();
		P_HEIGHT = xConfig.getPanelY();
		P_COLOR = xConfig.getPanelColor();
		panel = new MyPanel();
		initUI();
	}

	private void initUI() {

		add(panel);
		setResizable(false);
		pack();
		
		setTitle("Simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // centers
	}
	public static void main(String[] args) {

		Simulation ex = new Simulation();
		ex.setVisible(true);
	}
}