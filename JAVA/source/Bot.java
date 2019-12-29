import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Bot extends Sprite{
	private int dx, dy;
	private final String BOT_IMAGE_FILE = new String("C:\\path\\botship4461.png");
	private List<Missile> missiles;
	public Bot(int x, int y) {
		super(x,y);
		
		initBot();
	}
	private void initBot() {
		missiles = new ArrayList<Missile>();
		loadImage(BOT_IMAGE_FILE);
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
	public void fire(int direction) {
		if(direction ==1) missiles.add(new Missile(x + width/2 - 9, y - 14));
		else missiles.add(new Missile(x + width/2 - 9, y + 14));
	}
	public List<Missile> getMissiles(){
		return missiles;
	}
	// make better keyBinder that updates the state of a key using conditional
	// statements.
	public void keyPressed(int key) {

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
	}

	public void keyReleased(int key) {

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
