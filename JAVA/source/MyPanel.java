import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements Runnable, KeyListener{ // Runnable is an interface
	private Thread t;
	private final int REPAINT_DELAY = 17;
	private boolean keepPainting = true;
	private Animation anim;
	public MyPanel() {
		addKeyListener(this);
		addMouseListener(new MouseInput());
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		t = new Thread(this);
		anim = new Animation(this);
		initPanel();
	}
	
	private void initPanel() {
		setPanelCustomizables();
		t.start();
	}

	@Override
	public void run() {
		long beforeTime, latency, sleepTime;
		beforeTime = System.currentTimeMillis(); // for first call of run
		while (keepPainting) { // run Method is called only once
			repaint();
			latency = System.currentTimeMillis() - beforeTime;
			sleepTime = REPAINT_DELAY - latency; //accounts for Latency
			if(sleepTime < 0) {
				sleepTime = 2;
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				System.out.print("Error " + e);
			}
			beforeTime = System.currentTimeMillis();
		}
	}

	private void AnimationSetState() {
		keepPainting = !keepPainting;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		anim.step(g2D);
	}

	private void setPanelCustomizables() {
		if (Simulation.P_COLOR.equals("BLACK")) {
			setBackground(Color.BLACK);
		} else if (Simulation.P_COLOR.equals("WHITE")) {
			setBackground(Color.WHITE);
		} else if (Simulation.P_COLOR.equals("LIGHT_GRAY")) {
			setBackground(Color.LIGHT_GRAY);
		} else {
			setBackground(Color.RED);
		}
		setPreferredSize(new Dimension(Simulation.P_WIDTH, Simulation.P_HEIGHT)); // class variables
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		this.anim.player.keyPressed(e);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		this.anim.player.keyReleased(e);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}