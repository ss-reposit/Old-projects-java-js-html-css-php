
public class Missile extends Sprite{
	private int MISSILE_SPEED = -3;
	private final String PROJECTILE_IMAGE_FILE = new String("C:\\path\\missile.png");
    public Missile(int x, int y) {
        super(x, y);

        initMissile();
    }
    
    private void initMissile() {
        
        loadImage(PROJECTILE_IMAGE_FILE);  
        getImageDimensions();
    }
    
    public void setMissileSpeed(int x) {
    	MISSILE_SPEED = x;
    }
    public void move() {
        
        y += MISSILE_SPEED;
        
        if (y < (0 - image.getHeight(null))) {
            visible = false;
        }
        if (y > Simulation.P_HEIGHT) {
            visible = false;
        }
    }
}
