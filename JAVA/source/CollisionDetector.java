import java.awt.Rectangle;

public class CollisionDetector {
	private Rectangle r1, r2;
	private Player player;
	private Bot bot;
	public CollisionDetector(Player p, Bot b) {
		player = p;
		bot = b;
	}
	public void checkCollision() {
		r1 = player.hitBox();
		r2 = bot.hitBox();
		if(r1.intersects(r2)) hit(3); 
		for(Missile ms : player.getMissiles()) {
			Rectangle r3 = ms.hitBox();
			
			if(r3.intersects(r2)) {
				hit(2);
				ms.setVisible(false);
			}
		}
		for(Missile ms : bot.getMissiles()) {
			Rectangle r3 = ms.hitBox();
			
			if(r3.intersects(r1)) {
				hit(1);
				ms.setVisible(false);
			}
		}
	}
	
	private void hit(int x) {
		if(x ==1) player.health-=10;
		else if(x==2) bot.health-=10;
		else {
			bot.health = 0;
			player.health = 0;
			Animation.state = Animation.STATE.END;
		}
		if(player.health == 0 ||bot.health == 0)Animation.state = Animation.STATE.END;
	}
}
