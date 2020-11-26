package por.ayf.eng.mgmn.game.entd;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 *  Class that define a character of the game.
 * 
 *  @author: Ángel Yagüe Flor.
 *  @version: 1.0 - Stable.
 *  @version: 1.1 - Refactor the project.
 */

public class Character extends Element {
	private HashMap<String, BufferedImage> images;		
	private String characterName;							
	private ArrayList<Projectile> projectiles;				
	private int lifes;										
	private boolean inAir = false;						
	private boolean falling = false;						
	private boolean sliding = false;						
	private boolean shooting = false;						
	private boolean invincible = false;						
	private boolean damage = false;							
	
	public Character(int xPosition, int yPosition, int position, String characterName) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.position = position;
		this.characterName = characterName;
		this.images = new HashMap<String, BufferedImage>();
		this.projectiles = new ArrayList<Projectile>();
		this.lifes = 23;
		
		loadImages();
		this.currentImage = this.images.get("Estatico_1");
	}
	
	/**
	 *  Method that return the name of the character.
	 * 
	 *  @return Name of the character.
	 */
	
	public String getCharacterName() {
		return characterName;
	}
	
	/**
	 *  Method that return the images of the character.
	 * 
	 *  @return Images of the character.
	 */
	
	public HashMap<String, BufferedImage> getImages() {
		return images;
	}
	
	/**
	 *  Method that load the images of the character.
	 */
	
	private void loadImages() {
		try {
			this.images.put("Estatico_1", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Estatico_1.png")));
			this.images.put("Disparo_1", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Disparo_1.png")));
			this.images.put("Salto_1", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Salto_1.png")));
			this.images.put("Salto_2", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Salto_2.png")));
			this.images.put("Salto_3", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Salto_3.png")));
			this.images.put("Salto_4", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Salto_4.png")));
			this.images.put("DSalto_1", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DSalto_1.png")));
			this.images.put("DSalto_2", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DSalto_2.png")));
			this.images.put("DSalto_3", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DSalto_3.png")));
			this.images.put("DSalto_4", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DSalto_4.png")));
			this.images.put("Lose", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Lose.png")));
			this.images.put("Win", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Win.png")));

			if(this.characterName.equals("Megaman")) {
				this.images.put("Movimiento_1", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Movimiento_1.png")));
				this.images.put("Movimiento_2", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Movimiento_2.png")));
				this.images.put("Movimiento_3", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Movimiento_3.png")));
				this.images.put("Movimiento_4", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Movimiento_4.png")));
				this.images.put("Movimiento_5", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Movimiento_5.png")));
				this.images.put("Movimiento_6", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Movimiento_6.png")));
				this.images.put("Movimiento_7", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Movimiento_7.png")));
				this.images.put("Movimiento_8", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Movimiento_8.png")));
				this.images.put("Movimiento_9", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Movimiento_9.png")));
				this.images.put("Movimiento_10", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Movimiento_10.png")));
				this.images.put("Deslizar_1", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Deslizar_1.png")));
				this.images.put("DMovimiento_1", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DMovimiento_1.png")));
				this.images.put("DMovimiento_2", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DMovimiento_2.png")));
				this.images.put("DMovimiento_3", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DMovimiento_3.png")));
				this.images.put("DMovimiento_4", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DMovimiento_4.png")));
				this.images.put("DMovimiento_5", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DMovimiento_5.png")));
				this.images.put("DMovimiento_6", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DMovimiento_6.png")));
				this.images.put("DMovimiento_7", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DMovimiento_7.png")));
				this.images.put("DMovimiento_8", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DMovimiento_8.png")));
				this.images.put("DMovimiento_9", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DMovimiento_9.png")));
				this.images.put("DMovimiento_10", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_DMovimiento_10.png")));
				this.images.put("Damage", ImageIO.read(new File("src/main/resources/images/textures/characters/" + this.characterName + "_Damage.png")));
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  Method that return the projectiles of the character.
	 * 
	 *  @return The projectiles of the character.
	 */
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	/**
	 *  Method that return the lifes of the character.
	 * 
	 *  @return The lifes of the character.
	 */
	
	public int getLifes() {
		return lifes;
	}

	/**
	 *  Method that set the current lifes of the character.
	 * 
	 *  @param Current lifes of the character.
	 */
	
	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
	
	/** 
	 *  Method that return if the character is in the air or not.
	 * 
	 *  @return true if is in the air; false if not.
	 */
	
	public boolean getInAir() {
		return inAir;
	}

	/**
	 *  Method that set if the character is in the air.
	 * 
	 *  @param inAir: Boolean that indicate if is in the air or not.
	 */
	
	public void setInAir(boolean inAir) {
		this.inAir = inAir;
	}

	/**
	 *  Method that indicate if the character is falling or not.
	 * 
	 *  @return true if is falling; false if not.
	 */
	
	public boolean getFalling() {
		return falling;
	}

	/**
	 *  Method that set if the character is falling.
	 *  
	 *  @param falling: Boolean that indicate if is falling or not.
	 */
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	/**
	 *  Method that set if is sliding the character or not.
	 * 
	 *  @param sliding: Boolean that indicate if is sliding or not.
	 */
	
	public void setSliding(boolean sliding) {
		this.sliding = sliding;
	}

	/**
	 *  Method that indicate if the character has finished to slide or not.
	 * 
	 *  @return true if is sliding; false if not.
	 */
	
	public boolean getSliding() {
		return sliding;
	}
	
	/**
	 *  Method that indicate if the character is shooting or not.
	 * 
	 *  @return true if is shooting; false if not.
	 */
	
	public boolean getShooting() {
		return shooting;
	}

	/**
	 *  Method that set if is shooting the character or not.
	 * 
	 *  @param shooting: Boolean that indicate if is shooting or not.
	 */
	
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	/**
	 *  Method that return if the character is invincible or not.
	 * 
	 *  @return true if is invincible; false if not.
	 */
	
	public boolean getInvincible() {
		return invincible;
	}

	/**
	 *  Method that set or quit the invincibility.
	 * 
	 *  @param invincible: Boolean that indicate if is invincible or not.
	 */
	
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	/**
	 *  Method that return if the character is hurted or not.
	 * 
	 *  @return true if is hurted; false if not.
	 */
	
	public boolean getDamage() {
		return damage;
	}

	/**
	 *  Method that set or quit the damage of a character.
	 * 
	 *  @param damage: Boolean that indicate if is hurted or not.
	 */
	
	public void setDamage(boolean damage) {
		this.damage = damage;
	}
}
