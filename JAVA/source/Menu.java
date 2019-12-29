import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Simulation.P_WIDTH/2 - 50, 150, 100, 50);
	
	public void render(Graphics2D g2d) {
		Font fnt0 = new Font("arial", Font.BOLD, 40);
		FontMetrics met = g2d.getFontMetrics(fnt0);
		g2d.setFont(fnt0);
		g2d.setColor(Color.WHITE);
		g2d.drawString("GAME MENU", (Simulation.P_WIDTH - met.stringWidth("GAME MENU"))/2 , 100);
		
		
		g2d.draw(playButton);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g2d.setFont(fnt1);
		g2d.drawString("Play", playButton.x + 20, playButton.y + 30);
	}
}
