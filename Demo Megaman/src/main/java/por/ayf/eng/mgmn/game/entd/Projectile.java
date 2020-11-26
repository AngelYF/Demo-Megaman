package por.ayf.eng.mgmn.game.entd;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *  Class that define a projectile of the game.
 * 
 *  @author: Ángel Yagüe Flor.
 *  @version: 1.0 - Stable.
 *  @version: 1.1 - Refactor the project.
 */

public class Projectile extends Element {
	
	public Projectile(int xPosition, int yPosition, int position, String url) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.position = position;
		
		try {
			this.currentImage = ImageIO.read(new File("src/main/resources/images/textures/objects/" + url));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		if(this.position == 0) {
			this.xSpeed = -12;
		}
		else if(this.position == 1) {
			this.xSpeed = 12;
		}
	}
}
