package por.ayf.eng.mgmn.game.phys;

import java.util.ArrayList;

import por.ayf.eng.mgmn.game.entd.Character;
import por.ayf.eng.mgmn.game.entd.Projectile;
import por.ayf.eng.mgmn.game.map.Map;

/**
 *  Class that define the collisions of the game.
 * 
 *  @author: Ángel Yagüe Flor.
 *  @version: 1.0 - Stable.
 *  @version: 1.1 - Refactor the project.
 */

public class Collision {
	
	/**
	 *  Method that indicate if there is a collision in the scene or not.
	 * 
	 *  @param Character will analyze if there is collision.
	 *  @param Map where the collision happens.
	 *  @return If there is collision or not.
	 */
	
	public static boolean sceneCollision(Character character, Map map) {
		// LEFT:
		if(character.getPos_X() < map.getXPositionCamera() && character.getPosition() == 0) {
			character.setXPosition(map.getXPositionCamera());
			return true;
		}
		
		// RIGHT:
		if(character.getPos_X() > map.getXPositionCamera() + map.getSubmap().getWidth() - character.getCurrentImage().getWidth() && character.getPosition() == 1) {
			character.setXPosition(map.getXPositionCamera() + (map.getSubmap().getWidth() - character.getImages().get("Estatico_1").getWidth())); // Minus the borders too.
			return true;
		}
		
		return false;
	}
	
	/** 
	 *  Method that indicate if there is collision of the projectiles with the scene or not.
	 * 
	 *  @param Projectile will analyze if there is collision.
	 *  @param Map where the collision happens.
	 *  @return If there is collision or not.
	 */
	
	public static boolean projectileToSceneCollision(Projectile projectile, Map map) {
		// LEFT
		if(projectile.getPos_X() < map.getXPositionCamera() && projectile.getPosition() == 0) {
			return true;
		}
		
		// RIGHT
		if(projectile.getPos_X() > map.getXPositionCamera() + map.getSubmap().getWidth() - projectile.getCurrentImage().getWidth() && projectile.getPosition() == 1) {
			return true;
		}
	
		return false;
	}
	
	/**
	 *  Method that indicate if there is collision to the ground or not.
	 *
	 *   @param Character will analyze if there is collision with the ground.
	 *   @param Map where the collision happens.
	 *   @return If there is collision or not.
	 */
	
	public static boolean groundCollision(Character character, Map map) {
		if(character.getCharacterName().equals("Megaman")) {
			if(character.getYPosition() > 353) {
				character.setYPosition(353);
				return true;
			}
		} 
		else if(character.getCharacterName().equals("Bass")){
			if(character.getYPosition() > 329) {
				character.setYPosition(329);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 *  Method for check the collision between enemies projectiles and the character.
	 * 
	 *  @param Character that analyze.
	 *  @param Projectiles that interact in the collision.
	 *  @return True if there is collision, false if not.
	 */
	
	public static boolean characterToProjectileCollision(Character character, ArrayList<Projectile> projectiles) {
		boolean collision = false;
		
		// If Megaman is sliding, ignore the collisions.
		if(character.getSliding() == true) {
			return false;
		}
		
		for(int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).getPos_X() + projectiles.get(i).getCurrentImage().getWidth() >= character.getPos_X() + 30 &&
			   projectiles.get(i).getPos_X() <= character.getPos_X() + character.getCurrentImage().getWidth() - 30 &&
			   projectiles.get(i).getYPosition() + projectiles.get(i).getCurrentImage().getHeight() >= character.getYPosition() &&
			   projectiles.get(i).getYPosition() <= character.getYPosition() + character.getCurrentImage().getHeight()) {
				
				projectiles.remove(i);
				collision = true;
			}
		}
		
		return collision;
	}
}
