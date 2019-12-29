import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class BotController implements ActionListener {
	private Bot bcBot;
	private Player player;

	private final int VK_UP = 0x26;
	private final int VK_DOWN = 0x28;
	private final int VK_LEFT = 0x25;
	private final int VK_RIGHT = 0x27;
	private final int VK_SPACE = 0x20;

	private final int PROX_RADIUS = (Simulation.P_HEIGHT + Simulation.P_WIDTH) / 4;
	private final int BOT_DELAY = 1000;

	public String state;
	private String inputStage;

	private Timer t;

	private boolean randomMove, align, shoot;
	private int randomKey, alignKey;
	
	private int[] shootArray = new int[2];
	private int shootArrayI = 0;
	private int shot = 0;

	public BotController(Bot b, Player p) {
		bcBot = b;
		player = p;
		state = "";
		t = new Timer(BOT_DELAY, this);
		t.start();
	}

	public void moveBot() {
		bcBot.move();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (randomMove) {
			randomMove();
		} else {
			bcBot.keyReleased(randomKey);
			randomKey = 0;
		}
		if (align) {
			align();
		} else {
			bcBot.keyReleased(alignKey);
			alignKey = 0;
		}
		if(shoot) {
			shoot();
		}
		System.out.println(state + inputStage);
		System.out.println(bcBot.getMissiles());
		setBotState(inputStage);
	}

	private void setBotState(String input) {
		if (state.equals("")) {
			state = "checkProx";
		} else if (state.equals("checkProx") && input.equals("outProx")) {
		} else if (state.equals("checkProx") && input.equals("inProx")) {
			state = "align";
		} else if (state.equals("align") && input.equals("notAligned")) {
		} else if (state.equals("align") && input.equals("aligned")) {
			state = "shoot";
		} else if (state.equals("shoot") && input.equals("winning")) {
		} else if (state.equals("shoot") && input.equals("loosing")) {
			state = "run";
		} 
		else if(state.equals("run")) {
			randomMove();
			state = "checkProx";
			inputStage = "outProx";
		}// state = run change later to hide/wait
		else if(state.equals("end")) { t.stop();}
		else { //handles when inputstage = outprox in other stages
			state = "checkProx";
		}
		stateAction(state);
	}

	private void stateAction(String state) {
		if (state.equals("checkProx")) {
			inputStage = checkProx();
			if (inputStage.equals("outProx")) {
				randomMove = true;
			} else {
				randomMove = false;
			}
		} else if (state.equals("align")) {
			inputStage = checkAlign();
			if (inputStage.equals("notAligned")) {
				align = true;
			} else {
				align = false;
			}
		}
		else if(state.equals("shoot")) {
			//if()shootArrayI++; update shot and shootArray based on each other
			//shoot first iterate shot and then iterate shootarray when shot != shootArray
			shootArray[shootArrayI] = player.health;
			shoot = true;
			if (inputStage.equals("loosing")) shoot = false;
		}
	}
	

	private String checkProx() {
		int deltaBotToPlayer = (int) Math
				.sqrt(Math.pow(player.getX() - bcBot.getX(), 2) + Math.pow(player.getY() - bcBot.getY(), 2));
		if (deltaBotToPlayer > PROX_RADIUS) {
			return "outProx";
		} else {
			return "inProx";
		}
	}
	
	private String checkAlign() {
		if(Math.abs(player.getY() - bcBot.getY()) > PROX_RADIUS) {
			return "outProx";
		}
		if(outOfBounds()) { //so that not stuck outOfBounds
			return "outProx"; //checkProx outProx and then sets to inprox again, redundent
		}
		if(player.getX() + (0.1 * player.getX()) > bcBot.getX()//if 0.x smaller then r creates bump
			&& player.getX() - (0.1 * player.getX()) < bcBot.getX()) {
			return "aligned";
		}
		else {
			return "notAligned";
		}
	}
	
	private String checkShoot() {
		if(shootArray[0] > shootArray[1]) {
			return "winning";
		}
		else {
			return "loosing";
		}
	}
	private void shoot() {
		if(player.getY() > bcBot.getY()) {
			bcBot.fire(2);
			for(Missile missile : bcBot.getMissiles()) {
				missile.setMissileSpeed(3);
			}
		}
		else {
			bcBot.fire(1);
			for(Missile missile : bcBot.getMissiles()) {
				missile.setMissileSpeed(-3);
			}
		}
		shootArray[1] = player.health;
		inputStage = checkShoot();
	}
	private void align() {
		//starts bumpin back and forth when bcBot goes out of range in DELAY
		//since this is being called at an increased delay
		//bot can go past align pos making it realign again.
		//happens frequency at higher delays
		double r = Math.random() * 0.1;
		boolean b = (Math.random() * 2 > 1) ? true : false;
		int alignPos;
		if (b) {
			alignPos = (int) (player.getX() + (r*player.getX()));
		} else {
			alignPos = (int) (player.getX() - (r*player.getX()));
		}
		bcBot.keyReleased(alignKey);
		
		if(alignPos > bcBot.getX()) {
			alignKey = VK_RIGHT;
		}
		else if(alignPos < bcBot.getX()) {
			alignKey = VK_LEFT;
		}
		bcBot.keyPressed(alignKey);
	}

	private void randomMove() {
		bcBot.keyReleased(randomKey);

		int x = (int) (Math.random() * 4);
		switch (x) {
		case 0:
			randomKey = VK_LEFT;
			break;
		case 1:
			randomKey = VK_UP;
			break;
		case 2:
			randomKey = VK_RIGHT;
			break;
		case 3:
			randomKey = VK_DOWN;
			break;
		default:
			System.out.println("No Random Move Generation");
		}

		bcBot.keyPressed(randomKey);
	}
	
	private boolean outOfBounds() {
		if(bcBot.getX() > Simulation.P_WIDTH || bcBot.getX() < 0
											|| bcBot.getY() > Simulation.P_HEIGHT
											|| bcBot.getY() < 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
