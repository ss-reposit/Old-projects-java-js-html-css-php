
import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class Player extends Sprite {
	private int dx, dy;
	private final String PLAYER_IMAGE_FILE = new String("C:\\path\\spaceship5050.png");
	private List<Missile> missiles;
	public Player(int x, int y) {
		super(x, y);// explicit call to super constructer Sprite
		
		initPlayer();
	}
	private void initPlayer() {
		missiles = new ArrayList<Missile>();
		loadImage(PLAYER_IMAGE_FILE);
		getImageDimensions();
		health = 100;
	}
	public void move() {
		x += dx;
		y += dy;
		if(x > Simulation.P_WIDTH ) {
			x = -width;
		}
		else if(x < -width){
			x = Simulation.P_WIDTH ;
		}
		
		if(y > Simulation.P_HEIGHT ) {
			y = -height;
		}
		else if(y < -height){
			y = Simulation.P_HEIGHT ;
		}
	}
	
	private void fire() {
		missiles.add(new Missile(x + width/2 - 9, y - 14));
	}
	public List<Missile> getMissiles(){
		return missiles;
	}
	// make better keyBinder that updates the state of a key using conditional
	// statements.
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -2;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 2;
		}

		if (key == KeyEvent.VK_UP) {
			dy = -2;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 2;
		}
		
		if(key == KeyEvent.VK_SPACE) {
			fire();
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}
}
