import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;

public class Animation {
	public static enum STATE {
		MENU, GAME, END
	};
	private Menu menu;
	private EndGame endGame;
	
	public static STATE state;
	public Player player;
	public Bot bot;
	public BotController bc;
	private JPanel imageObserverPanel;
	private CollisionDetector cD;

	public Animation(JPanel p) {
		state = STATE.MENU;
		menu = new Menu();
		endGame = new EndGame();
			player = new Player(0, 0);
			bot = new Bot(Simulation.P_WIDTH, Simulation.P_HEIGHT);
			bc = new BotController(bot, player);
			cD = new CollisionDetector(player, bot);
			imageObserverPanel = p;
	}

	public void step(Graphics2D g2d) {
		if (state == STATE.GAME) {
			player.move();
			bc.moveBot();
			updateMissiles();
			cD.checkCollision();
		}
		drawSprite(g2d);
	}

	private void drawSprite(Graphics2D g2d) {
		if (state == STATE.GAME) {
			g2d.drawImage(player.getImage(), player.getX(), player.getY(), imageObserverPanel);
			g2d.drawImage(bot.getImage(), bot.getX(), bot.getY(), imageObserverPanel);

			for (Missile missile : player.getMissiles()) {
				g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), imageObserverPanel);
			}

			for (Missile missile : bot.getMissiles()) {
				g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), imageObserverPanel);
			}

			g2d.setColor(Color.WHITE);
			g2d.fillRect(5, 5, 100, 20);

			g2d.setColor(Color.GREEN);
			g2d.fillRect(5, 5, player.health, 20);

			g2d.setColor(Color.WHITE);
			g2d.fillRect(Simulation.P_WIDTH - 100, 5, 100, 20);

			g2d.setColor(Color.RED);
			g2d.fillRect(Simulation.P_WIDTH - 100, 5, bot.health, 20);
		} 
		else if(state == STATE.MENU) {
			menu.render(g2d);
		}
		else {
			endGame.render(g2d);
		}
	}

	private void updateMissiles() {
		List<Missile> toRemove = new ArrayList<Missile>();
		for (Missile missile : player.getMissiles()) {
			if (missile.isVisible()) {
				missile.move();
			} else {
				toRemove.add(missile); // cannot just remove creates concurrentMod error
			}
		}
		for (Missile missile : bot.getMissiles()) {
			if (missile.isVisible()) {
				missile.move();
			} else {
				toRemove.add(missile); // cannot just remove creates concurrentMod error
			}
		}
		player.getMissiles().removeAll(toRemove);
		bot.getMissiles().removeAll(toRemove);
	}
}
