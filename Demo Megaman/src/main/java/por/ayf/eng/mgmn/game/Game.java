package por.ayf.eng.mgmn.game;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

import por.ayf.eng.mgmn.game.audio.Audio;
import por.ayf.eng.mgmn.game.entd.Character;
import por.ayf.eng.mgmn.game.entd.Projectile;
import por.ayf.eng.mgmn.game.map.Map;
import por.ayf.eng.mgmn.game.phys.Collision;
import por.ayf.eng.mgmn.game.render.Animation;
import por.ayf.eng.mgmn.view.ViewMainWindow;

/**
 *  Canvas where the game happens.
 * 
 *  @author: Ángel Yagüe Flor
 *  @version: 1.0 - Stable.
 *  @version: 1.1 - Refactor the project.
 */

public class Game extends Canvas {
	private static final long serialVersionUID = 1L;			

	private int xPositionInitial; 								
	private int yPositionInitial; 								
	
	private BufferedImage bufferedImageGame; 								
	private Graphics2D graphicGame;							
	private BufferedImage bufferedImageScreen; 						
	private Graphics2D graphicScreen;							
	
	private Map map; 											
	private Character megaman;									
	private Character bass;										
	private BufferedImage contentMegaman; 						
	private BufferedImage contentBass; 						
	private BufferedImage bufferedImageWin;									
	private BufferedImage bufferedImageLose;									
	private BufferedImage bufferedImageLife; 								
	
	private Clip clipSound; 										
	private Clip clipMusic;										
	
	private Timer timerPaint;								
	private Timer timerSpeed;								
	private Timer timerEnd;										
	
	private Timer timerAnimationMovementMegaman;				
	private Timer timerAnimationJumpMegaman;					
	private Timer timerJumpMegaman;							
	private Timer timerSlideMegaman;						
	private Timer timerShootMegaman;							
	private Timer timerInvincibleMegaman;						
	private Timer timerDamageMegaman;							
	
	private Timer timerActionBass;								
	private Timer timerShootBass;							
	private Timer timerAnimationJumpBass;						
	private Timer timerJumpBass;								
	private Timer timerJumpShootBass;						
	private Timer timerInvincibleBass;							
	
	private boolean aPressed = false;						
	private boolean sPressed = false;						
	private boolean actionBass = false;						
	private boolean ended = false;							
	private boolean gameOver = false;						
	private boolean end = false;							
	
	private int blinkM = 0;									
	private int blinkB = 0;									
	
	public Game() {
		this.setBounds(0, 0, ViewMainWindow.WIDTH, ViewMainWindow.HIGH);
		
		this.map = new Map(150, 0);
		loadMap("Intro_stage.png");
		loadElements();
		
		this.xPositionInitial = map.getXPositionCamera() + 50;
		this.yPositionInitial = map.getYPositionCamera() + 353;
		
		this.megaman = new Character(this.xPositionInitial, this.yPositionInitial, 1, "Megaman");
		
		this.xPositionInitial = map.getXPositionCamera() + 600;
		this.yPositionInitial = map.getYPositionCamera() + 329;
		
		this.bass = new Character(this.xPositionInitial, this.yPositionInitial, 0, "Bass");
		
		clipMusic = Audio.playBackgroundMusic(clipMusic, "BassTheme.wav", 380000, -1);
		

		setTimers();
		
		this.timerPaint.start();
		this.timerActionBass.start();
	}
	
	/** 
	 *  Method that paint the game.
	 *  
	 *  @param graphics for paint the game.
	 */
	
	public void paint(Graphics graphics) {
		update(graphics);
	}
	
	/**
	 *  Method will call to paint for avoid the blink on the screen.
	 *  
	 *  @param graphics for update the screen.
	 */
	
	public void update(Graphics graphics) {
		createBuffers();
		drawScreen(graphics);
		
		if(!this.timerSpeed.isRunning() && ended == false) {
			this.timerSpeed.start();
		}
	}
	
	/**
	 *  Method will draw the screen of the game.
	 * 
	 *  @param graphics for paint the screen.
	 */
	
	private void drawScreen(Graphics graphics) {
		drawMap(this.graphicGame);
		drawCharacters(this.graphicGame);
		drawProjectiles(this.graphicGame);
		drawLifes(this.graphicGame);
		
		if(this.end == true) {
			drawEnd(this.graphicGame);
		}

		drawSubmap(this.graphicScreen);
		graphics.drawImage(this.bufferedImageScreen, 0, 0, null);
	}
	
	/**
	 *  Method that draw the characters of the game.
	 * 
	 *  @param graphics for paint on the game.
	 */
	
	private void drawCharacters(Graphics2D graphics) {
		// MEGAMAN:
		int width = this.megaman.getCurrentImage().getWidth();
		int height = this.megaman.getCurrentImage().getHeight();
		
		BufferedImage currentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D characterGraphic = (Graphics2D) currentImage.getGraphics();
		
		// If I'm invincible, blink.
		if(this.megaman.getInvincible() == true) {
			if(this.blinkM == 0) {
				this.blinkM++;
				characterGraphic.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
			}
			else {
				this.blinkM--;
			}
		}
		
		// If I look to the left, rotate 180 degrees and do a mirror.
		if(this.megaman.getPosition() == 0) {
			characterGraphic.rotate(Math.toRadians(180.0), width/2.0, height/2.0);
			characterGraphic.drawImage(this.megaman.getCurrentImage(), 0, 0, width, height, 0, height, width, 0, null);
		}
		else {	// If I look to the right, paint it normal.
			characterGraphic.drawImage(this.megaman.getCurrentImage(), 0, 0, null);
		}	
		
		graphics.drawImage(currentImage, this.megaman.getPos_X(), this.megaman.getYPosition(), null);
		
		// BASS:
		width = this.bass.getCurrentImage().getWidth();
		height = this.bass.getCurrentImage().getHeight();
			
		currentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		characterGraphic = (Graphics2D) currentImage.getGraphics();
			
		// If I'm invincible, blink.
		if(this.bass.getInvincible() == true) {
			if(this.blinkB == 0) {
				this.blinkB++;
				characterGraphic.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
			}
			else {
				this.blinkB--;
			}
		}
		
		// If I look to the left, rotate 180 degrees and do a mirror.
		if(this.bass.getPosition() == 0) {
			characterGraphic.rotate(Math.toRadians(180.0), width/2.0, height/2.0);
			characterGraphic.drawImage(this.bass.getCurrentImage(), 0, 0, width, height, 0, height, width, 0, null);
		}
		else {	// If I look to the right, paint it normal.
			characterGraphic.drawImage(this.bass.getCurrentImage(), 0, 0, null);
		}	
		
		graphics.drawImage(currentImage, this.bass.getPos_X(), this.bass.getYPosition(), null);
	}
	
	/**
	 *  Method that draw the projectiles of the game.
	 * 
	 *  @param graphics for paint the game.
	 */
	
	private void drawProjectiles(Graphics2D graphics) {
		for(int i = 0; i < this.megaman.getProjectiles().size(); i++) {
			Projectile aux = this.megaman.getProjectiles().get(i);
			
			graphics.drawImage(aux.getCurrentImage(), aux.getPos_X(), aux.getYPosition(), null);
		}

		for(int i = 0; i < this.bass.getProjectiles().size(); i++) {
			Projectile aux = this.bass.getProjectiles().get(i);
			
			graphics.drawImage(aux.getCurrentImage(), aux.getPos_X(), aux.getYPosition(), null);
		}
	}
	
	/**  
	 *  Method will draw the map on the image of the game.
	 *  
	 *  @param graphics for paint the game.
	 */
	
	private void drawMap(Graphics2D graphics) {
		graphics.drawImage(this.map.getMap(), 0, 0, null);
	}
	
	/**
	 *  Method will draw the life of the characters.
	 *  
	 *  @param graphics for paint the game.
	 */
	
	private void drawLifes(Graphics2D graphics) {
		graphics.drawImage(this.contentMegaman, this.map.getXPositionCamera() + 20, this.map.getYPositionCamera() + 20, null);
		for(int i = 0; i < this.megaman.getLifes(); i++) {
			graphics.drawImage(this.bufferedImageLife, this.map.getXPositionCamera() + 27, this.map.getYPositionCamera() + 214 - (i * 8), null);
		}
		
		graphics.drawImage(this.contentBass, this.map.getXPositionCamera() + ViewMainWindow.WIDTH - 70, this.map.getYPositionCamera() + 20, null);
		for(int i = 0; i < this.bass.getLifes(); i++) {
			graphics.drawImage(this.bufferedImageLife, this.map.getXPositionCamera() + ViewMainWindow.WIDTH - 63, this.map.getYPositionCamera() + 214 - (i * 8), null);
		}
	}
	
	/**
	 *  Method will draw the text of the end of the game.
	 * 
	 *  @param graphics for paint the game.
	 */
	
	private void drawEnd(Graphics2D graphics) {
		// If there is a Game Over, draw that I lose, else that I win.
		if(this.gameOver == true) {
			graphics.drawImage(this.bufferedImageLose, this.map.getXPositionCamera() + 120, this.map.getYPositionCamera() + 100, null);
		}
		else {
			graphics.drawImage(this.bufferedImageWin, this.map.getXPositionCamera() + 120, this.map.getYPositionCamera() + 100, null);
		}
	}
	
	/**
	 *  Method will cut on the image of the game for after put in the screen.
	 * 
	 *  @param graphics for paint the game.
	 */
	
	private void drawSubmap(Graphics2D graphics) {
		this.map.setSubmap(this.bufferedImageGame.getSubimage(this.map.getXPositionCamera(), this.map.getYPositionCamera(), ViewMainWindow.WIDTH, ViewMainWindow.HIGH));
		graphics.drawImage(this.map.getSubmap(), 0, 0, null);
	}

	/**
	 *  Method that do Megaman move.
	 * 
	 *  @param direction to move Megaman.
	 */
		
	public void moveMegaman(int direction) {
		// If the movement animation is not the current one, put it.
		if(!this.timerAnimationMovementMegaman.isRunning()) {
			if(this.megaman.getInAir() == false) {
				this.timerAnimationMovementMegaman.start();
			}
		}
		
		// If I was sliding, then stop it.
		if(this.timerSlideMegaman.isRunning()) {
			this.megaman.setSliding(false);
			this.timerSlideMegaman.stop();
		}
		
		switch(direction) {
			case 0:	// LEFT		
				// In case I'm looking to the left and I'm not ended the slide, we don't move.
				if(this.megaman.getPosition() == 0 && this.megaman.getSliding() == true) {
					return;
				}
				
				this.megaman.setXSpeed(-6);
				this.megaman.setPosition(0);
				break;
			case 1: // RIGHT
				// In case I'm looking to the right and I'm not ended the slide, we don't move.
				if(this.megaman.getPosition() == 1 && this.megaman.getSliding() == true) {
					return;
				}
				
				this.megaman.setXSpeed(6);
				this.megaman.setPosition(1);
				break;
		}
	}
	
	/**
	 *  Method that do Megaman jump.
	 */
	
	public void jumpMegaman() {
		// If I'm not jumping, then start the jump.
		if(!this.timerAnimationJumpMegaman.isRunning() && !this.timerJumpMegaman.isRunning()) {
			this.timerAnimationJumpMegaman.start();
			this.timerJumpMegaman.start();
		}
		
		// If I was moving, then stop the movement.
		if(this.timerAnimationMovementMegaman.isRunning()) {
			this.timerAnimationMovementMegaman.stop();
		}
		
		// If I was sliding, then stop the slide.
		if(this.timerSlideMegaman.isRunning()) {
			this.megaman.setXSpeed(0);
			this.megaman.setSliding(false);
			this.timerSlideMegaman.stop();
		}
		
		this.megaman.setYSpeed(-12);		// Initial force of jump.
		this.aPressed = true;					
		this.megaman.setInAir(true);		
	}
	
	/**
	 *  Method that do Megaman slide by the ground.
	 */
	
	public void slideMegaman() {
		// If I was moving, stop it.
		if(this.timerAnimationMovementMegaman.isRunning()) {
			this.timerAnimationMovementMegaman.stop();
		}
		
		// If I wasn't sliding, start.
		if(!this.timerSlideMegaman.isRunning()) {
			this.timerSlideMegaman.start();
		}
		
		this.aPressed = true;														
		this.megaman.setSliding(true);												
		this.megaman.setCurrentImage(this.megaman.getImages().get("Deslizar_1"));
		
		// According to the direction slide me to one side or other.
		switch(this.megaman.getPosition()) {
			case 0:		// LEFT	
				this.megaman.setXSpeed(-12);
				break;
			case 1:		// RIGHT
				this.megaman.setXSpeed(12);
				break;
		}	

		Audio.playSound(clipSound, "Slide.wav");
	}
	
	/** 
	 *  Method that do Megaman shoot.
	 */
	
	public void shootMegaman() {
		// If there are 3 or more projectiles, then don't shoot.
		if(this.megaman.getProjectiles().size() >= 3) {
			return;
		}
		
		// If I was shooting, stop it.
		if(this.timerShootMegaman.isRunning()) {
			this.timerShootMegaman.stop();
		}
		
		// If I was sliding, stop.
		if(this.timerSlideMegaman.isRunning()) {
			this.megaman.setXSpeed(0);
			this.megaman.setSliding(false);
			this.timerSlideMegaman.stop();
		}
		
		// We create the projectile according to where is looking.
		Projectile projectile = null;
		
		if(this.megaman.getPosition() == 0) {
			projectile = new Projectile(this.megaman.getPos_X() - 12, this.megaman.getYPosition() + 40, this.megaman.getPosition(), "Disparo_Megaman.png");
		}
		else if(this.megaman.getPosition() == 1) {
			projectile = new Projectile(this.megaman.getPos_X() + this.megaman.getCurrentImage().getWidth(), this.megaman.getYPosition() + 40, this.megaman.getPosition(), "Disparo_Megaman.png");
		}
		
		this.megaman.getProjectiles().add(projectile);
		
		// If I'm in the ground, I'm not moving or sliding, then put static the image of shoot.
		if(this.megaman.getInAir() == false && this.megaman.getSliding() == false && !timerAnimationMovementMegaman.isRunning()) {
			this.megaman.setCurrentImage(this.megaman.getImages().get("Disparo_1"));
		}
		
		this.sPressed = true;				
		this.megaman.setShooting(true);		
		this.timerShootMegaman.start();		
		
		Audio.playSound(clipSound, "Shot.wav");
	}
	
	/**  
	 *  Method will execute when Megaman stops to move.
	 */
	
	public void stopMegaman() {
		// If Megaman was moving, stop it.
		if(this.timerAnimationMovementMegaman.isRunning()) {
			this.timerAnimationMovementMegaman.stop();
		}
		
		// If I'm shooting, put static the image of the shoot, else the standing one.
		if(this.megaman.getShooting() == true) {
			this.megaman.setCurrentImage(this.megaman.getImages().get("Disparo_1"));	
		}
		else {
			this.megaman.setCurrentImage(this.megaman.getImages().get("Estatico_1"));	
		}
		
		if(this.megaman.getSliding() == true) {
			this.megaman.setSliding(false);
		}
		
		// We stop the X's axis.
		this.megaman.setXSpeed(0);
	}
	
	/** 
	 *  Method will execute for Megaman stops to move in the air.
	 */
	
	public void airStopMegaman() {
		// Only stop the movement of the X's axis.
		this.megaman.setXSpeed(0);
	}
	
	/** 
	 *  Method will execute when Megaman touch the ground.
	 */
	
	public void landingMegaman() {
		// If the jump animation is activated, stop it. 
		if(timerAnimationJumpMegaman.isRunning()) {
			timerAnimationJumpMegaman.stop();
		}
		
		// If the jump is activated, stop it.	
		if(this.timerJumpMegaman.isRunning()) {
			this.timerJumpMegaman.stop();
		}
		
		// If the X's axis are different to 0, then activate the movement animation.
		if(this.megaman.getXSpeed() != 0) {
			this.timerAnimationMovementMegaman.start();
		}
		
		this.megaman.setCurrentImage(this.megaman.getImages().get("Estatico_1")); 
		this.megaman.setYSpeed(0);													
		this.megaman.setInAir(false);												
		this.megaman.setFalling(false);																										
		
		Audio.playSound(clipSound, "Landing.wav");
	}

	/** 
	 *  Method will execute when Megaman is damaged.
	 */
	
	private void damageMegaman() {
		// If I'm in the jump animation, stop it.
		if(this.timerAnimationJumpMegaman.isRunning()) {
			this.timerAnimationJumpMegaman.stop();
		}
		
		// If I'm in the movement animation, stop it.
		if(this.timerAnimationMovementMegaman.isRunning()) {
			this.timerAnimationMovementMegaman.stop();
		}
		
		this.megaman.setLifes(megaman.getLifes() - 1); 								// Minus one life. 
		this.megaman.setInvincible(true);											// Make me invencibility.
		this.timerInvincibleMegaman.start();										
		this.megaman.setCurrentImage(this.megaman.getImages().get("Damage"));		
		this.megaman.setDamage(true);												
		this.timerDamageMegaman.start();												
		this.megaman.setXSpeed(0);																									
		
		if(this.megaman.getInAir() == true) {
			this.megaman.setFalling(true);
		}
		
		Audio.playSound(clipSound, "Damage.wav");
	}

	/**
	 *  Method that do Bass shoot.
	 */
	
	public void shootBass() {
		// If there are 3 or more projectiles, then don't shoot.
		if(this.bass.getProjectiles().size() >= 3) {
			return;
		}
		
		// Create the projectile according to where is looking.
		Projectile projectile = null;
		
		if(this.bass.getPosition() == 0) {
			projectile = new Projectile(this.bass.getPos_X() - 35, this.bass.getYPosition() + 40, this.bass.getPosition(), "Disparo_Bass.png");
		}
		else if(this.bass.getPosition() == 1) {
			projectile = new Projectile(this.bass.getPos_X() + this.bass.getCurrentImage().getWidth(), this.bass.getYPosition() + 40, this.bass.getPosition(), "Disparo_Bass.png");
		}
		
		this.bass.getProjectiles().add(projectile);
		
		// If I'm in the ground, I'm not moving or sliding, then put  the static image of the shoot.
		if(this.bass.getInAir() == false) {
			this.bass.setCurrentImage(this.bass.getImages().get("Disparo_1"));
		}
		
		this.bass.setShooting(true);		
		this.timerShootBass.start();		
		
		Audio.playSound(clipSound, "Shot_Bass.wav");
	}
	
	/**
	 *  Method that do Bass jump.
	 */
	
	public void jumpBass() {
		// If I wasn't jumping, then start the jump.
		if(!this.timerAnimationJumpBass.isRunning() && !this.timerJumpBass.isRunning()) {
			this.timerAnimationJumpBass.start();
			this.timerJumpBass.start();
		}
		
		this.bass.setYSpeed(-12);				// Initial force of the jump.	
		this.bass.setInAir(true);					
		this.timerJumpShootBass.start();		
	}
	
	/**
	 *  Method will execute when Bass touch the ground
	 */
	
	public void landingBass() {
		// If the jump animation is activated, stop it.
		if(timerAnimationJumpBass.isRunning()) {
			timerAnimationJumpBass.stop();
		}
		
		// If the jump is active, stop it.
		if(this.timerJumpBass.isRunning()) {
			this.timerJumpBass.stop();
		}
		
		this.bass.setCurrentImage(this.bass.getImages().get("Estatico_1")); 		
		this.bass.setYSpeed(0);														
		this.bass.setInAir(false);													
		this.bass.setFalling(false);																										
		this.actionBass = false;													
		
		Audio.playSound(clipSound, "Landing_Bass.wav");
	}
	
	/**
	 *  Method will execute when Bass is damaged.
	 */
	
	private void damageBass() {
		this.bass.setLifes(bass.getLifes() - 1);	// Minus one life.
		this.bass.setInvincible(true);				// Make him invencibility.
		this.timerInvincibleBass.start();			
		
		Audio.playSound(clipSound, "Damage_Bass.wav");
	}
	
	/**
	 *  Method that define the timers of the game.
	 */
	
	private void setTimers() {
		// TIMER OF REPAINT:
		this.timerPaint = new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent event){
				repaint();
		    }
		});
		
		// TIMER FINAL:
		this.timerEnd = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent event){
				// If is active the speed timer, stop it.
				if(timerSpeed.isRunning()) {
					timerSpeed.stop();
				}
				
				// If there is Game Over show the defeat, else the victory.
				if(gameOver == true) {
					bass.setYPosition(bass.getYPosition() - 9);
					bass.setCurrentImage(bass.getImages().get("Win"));
					megaman.setYPosition(megaman.getYPosition() + 6);
					megaman.setCurrentImage(megaman.getImages().get("Lose"));
					Audio.playSound(clipSound, "Lose.wav");
				}
				else {
					bass.setCurrentImage(bass.getImages().get("Lose"));
					megaman.setCurrentImage(megaman.getImages().get("Win"));
					Audio.playSound(clipSound, "Win.wav");
				}
				
				end = true;			// Mark the end as true for can see the final text.
				timerEnd.stop();	// For this timer for it doesn't loop the sound.
		    }	
		});
		
		// TIMER OF MOVEMENT ANIMATION OF MEGAMAN:
		this.timerAnimationMovementMegaman = new Timer(90, new ActionListener() {
			public void actionPerformed(ActionEvent event){
				Animation.movementAnimation(megaman, megaman.getShooting());
		    }
		});
		
		this.timerAnimationMovementMegaman.setInitialDelay(0);
		
		// TIMER OF JUMP ANIMATION OF MEGAMAN:
		this.timerAnimationJumpMegaman = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent event){
				Animation.jumpAnimation(megaman, megaman.getShooting());
		    }
		});
		
		this.timerAnimationJumpMegaman.setInitialDelay(0);
		
		// TIMER OF JUMP ANIMATION OF BASS:
		this.timerAnimationJumpBass = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent event){
				Animation.jumpAnimation(bass, bass.getShooting());
		    }
		});
		
		this.timerAnimationJumpBass.setInitialDelay(0);
		
		// TIMER OF MOVEMENT
		this.timerSpeed = new Timer(15, new ActionListener() {
			public void actionPerformed(ActionEvent event){
				// MOVEMENT OF THE X'S AXIS:
				megaman.setXPosition(megaman.getPos_X() + megaman.getXSpeed());
				
				// If collide by the sides.
				if(Collision.sceneCollision(megaman, map)) {
					if(megaman.getInAir() == false) {
						stopMegaman();
					}	
				}
				
				// MOVEMENT OF THE Y'S AXIS:
				megaman.setYPosition(megaman.getYPosition() + megaman.getYSpeed());
				
				// If is by collide with the ground, change the image.
				if(megaman.getFalling() == true && megaman.getYPosition() >= 330 && megaman.getYPosition() <= 353) {
					if(megaman.getShooting() == true) {
						megaman.setCurrentImage(megaman.getImages().get("DSalto_1"));
					}
					else {
						megaman.setCurrentImage(megaman.getImages().get("Salto_1"));
					}
				}
				
				// If collide with the ground.
				if(Collision.groundCollision(megaman, map)) {
					landingMegaman();
				}
			
				// According to where is Megaman, Bass will look to one side or other.	
				if(megaman.getPos_X() > bass.getPos_X()) {
					bass.setPosition(1);
				}
				else {
					bass.setPosition(0);
				}
				
				// MOVEMENT OF THE Y'S AXIS:
				bass.setYPosition(bass.getYPosition() + bass.getYSpeed());
				
				// If is by collide with the ground, change the image.
				if(bass.getFalling() == true && bass.getYPosition() >= 306 && bass.getYPosition() <= 329) {
					if(bass.getShooting() == true) {
						bass.setCurrentImage(bass.getImages().get("DSalto_1"));
					}
					else {
						bass.setCurrentImage(bass.getImages().get("Salto_1"));
					}
				}
				
				// If he collides with the ground.
				if(Collision.groundCollision(bass, map)) {
					landingBass();
				}
				
				// PROJECTILES MOVEMENT:
				for(int i = 0; i < megaman.getProjectiles().size(); i++) {
					Projectile projectile = megaman.getProjectiles().get(i);
					
					if(Collision.projectileToSceneCollision(projectile, map)) {
						megaman.getProjectiles().remove(i);
						continue;
					}
					else {
						projectile.setXPosition(projectile.getPos_X() + projectile.getXSpeed());
						projectile.setYPosition(projectile.getYPosition() + projectile.getYSpeed());
					}
				}
				
				for(int i = 0; i < bass.getProjectiles().size(); i++) {
					Projectile projectile = bass.getProjectiles().get(i);
					
					if(Collision.projectileToSceneCollision(projectile, map)) {
						bass.getProjectiles().remove(i);
						continue;
					}
					else {
						projectile.setXPosition(projectile.getPos_X() + projectile.getXSpeed());
						projectile.setYPosition(projectile.getYPosition() + projectile.getYSpeed());
					}
				}
				
				// PROJECTILES COLLISION:
				if(Collision.characterToProjectileCollision(megaman, bass.getProjectiles())) {
					if(megaman.getInvincible() == false && ended == false) {
						damageMegaman();
					}
				}
				
				if(Collision.characterToProjectileCollision(bass, megaman.getProjectiles())) {
					if(bass.getInvincible() == false && ended == false) {
						damageBass();
					}
				}
				
				// GAME OVER:
				if(megaman.getLifes() == 0) {
					gameOver = true;
					ended = true;
				}
				else if(bass.getLifes() == 0) {
					ended = true;
				}
				
				if(ended == true) {
					timerActionBass.stop();
					timerAnimationMovementMegaman.stop();
					clipMusic.stop(); 
					timerEnd.start();
				}
		    }
		});
		
		this.timerSpeed.setInitialDelay(0);
		
		// JUMP TIMER OF MEGAMAN:
		this.timerJumpMegaman = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent event){
				// If is by collide with the ground, change the image.				
				if(megaman.getYPosition() <= 170) {
					megaman.setFalling(true);
				}
				
				// According to I fall or up, I modify staged the speed of how move for give more sensation of reality.
				if(megaman.getFalling() == true) {
					if(megaman.getYSpeed() < 16) {
						megaman.setYSpeed(megaman.getYSpeed() + 3);
					}
					else {
						megaman.setYSpeed(16);
					}
				}
				else {
					megaman.setYSpeed(-12);
				}
		    }
		});
		
		// JUMP TIMER OF BASS:
		this.timerJumpBass = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent event){
				// If is by collide with the ground, change the image.				
				if(bass.getYPosition() <= 170) {
					bass.setFalling(true);
				}
				
				// According to I fall or up, I modify staged the speed of how move for give more sensation of reality.
				if(bass.getFalling() == true) {
					if(bass.getYSpeed() < 16) {
						bass.setYSpeed(bass.getYSpeed() + 3);
					}
					else {
						bass.setYSpeed(16);
					}
				}
				else {
					bass.setYSpeed(-12);
				}
		    }
		});
		
		// SLIDE TIMER OF MEGAMAN:
		this.timerSlideMegaman = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				megaman.setCurrentImage(megaman.getImages().get("Estatico_1"));
				megaman.setXSpeed(0);
				megaman.setSliding(false);
				timerSlideMegaman.stop();
			}
		});
		
		// SHOOT TIMER OF MEGAMAN:
		this.timerShootMegaman = new Timer(350, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(megaman.getInAir() == false && megaman.getSliding() == false && !timerAnimationMovementMegaman.isRunning()) {
					megaman.setCurrentImage(megaman.getImages().get("Estatico_1"));
				}
				else if(megaman.getCurrentImage() == megaman.getImages().get("DSalto_4")) {
					megaman.setCurrentImage(megaman.getImages().get("Salto_4"));
				}
				
				megaman.setShooting(false);
				timerShootMegaman.stop();
			}
		});	
		
		// DAMAGE TIMER OF MEGAMAN:
		this.timerDamageMegaman = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(megaman.getInAir() == false) {
					megaman.setCurrentImage(megaman.getImages().get("Estatico_1"));
				}
				else {
					megaman.setCurrentImage(megaman.getImages().get("Salto_4"));
				}
				
				megaman.setDamage(false);
				timerDamageMegaman.stop();
			}
		});
		
		// INVENCIBILITY TIMER OF MEGAMAN:
		this.timerInvincibleMegaman = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				megaman.setInvincible(false);
				timerInvincibleMegaman.stop();
			}
		});
		
		// SHOOT TIMER OF BASS:
		this.timerShootBass = new Timer(700, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(bass.getInAir() == false) {
					bass.setCurrentImage(bass.getImages().get("Estatico_1"));
				}
				else if(bass.getCurrentImage() == bass.getImages().get("DSalto_4")) {
					bass.setCurrentImage(bass.getImages().get("Salto_4"));
				}
				
				bass.setShooting(false);
				actionBass = false;
				timerShootBass.stop();
			}
		});	
		
		// SHOOT JUMP TIMER OF BASS:
		this.timerJumpShootBass = new Timer(450, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				shootBass();
				timerJumpShootBass.stop();
			}
		});	
		
		// INVENCIBILITY TIMER OF BASS:
		this.timerInvincibleBass = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				bass.setInvincible(false);
				timerInvincibleBass.stop();
			}
		});
		
		// ACTION TIMER OF BASS:
		this.timerActionBass = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(actionBass == false) {
					actionBass = true;
					
					if(megaman.getInAir() == false) {
						shootBass();
					}
					else {
						jumpBass();
					}	
				}
			}
		});
	}

	/**
	 *  Method that load the map of the game.
	 * 
	 * @param Map name.
	 */
	
	private void loadMap(String name) {
		try {
			this.map.setMap(ImageIO.read(getClass().getResource("/images/textures/map/" + name)));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 *  Method that load elements of the game.
	 */
	
	private void loadElements() {
		try {
			this.contentMegaman = ImageIO.read(getClass().getResource("/images/textures/miscellaneous/Contenedor.png"));
			this.contentBass = ImageIO.read(getClass().getResource("/images/textures/miscellaneous/Contenedor_Bass.png"));
			this.bufferedImageWin = ImageIO.read(getClass().getResource("/images/textures/miscellaneous/Win.png"));
			this.bufferedImageLose = ImageIO.read(getClass().getResource("/images/textures/miscellaneous/Lose.png"));
			this.bufferedImageLife = ImageIO.read(getClass().getResource("/images/textures/miscellaneous/Vida.png"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 *  Method that create the paint buffers.
	 */
	
	private void createBuffers() {
		if(this.bufferedImageScreen == null) {
			this.bufferedImageScreen = (BufferedImage) createImage(ViewMainWindow.WIDTH, ViewMainWindow.HIGH);
			this.graphicScreen = (Graphics2D) this.bufferedImageScreen.getGraphics();
		}
		
		if(this.bufferedImageGame == null) {
			this.bufferedImageGame = (BufferedImage) createImage(this.map.getMap().getWidth(), this.map.getMap().getHeight());
			this.graphicGame = (Graphics2D) this.bufferedImageGame.getGraphics();
		}
	}

	/**
	 *  Method that return if the key "A" is pressed.
	 * 
	 *  @return true if is pressed, false if not.
	 */
	
	public boolean getAPressed() {
		return aPressed;
	}

	/**
	 *  Method that set if the key "A" is pressed or not.
	 * 
	 *  @param aPressed: Boolean that indicate if the key "A" is pressed or not.
	 */
	
	public void setAPressed(boolean aPressed) {
		this.aPressed = aPressed;
	}
	
	/**
	 *  Method that return if the key "S" is pressed.
	 * 
	 *  @return true if is pressed, false if not.
	 */
	
	public boolean getSPressed() {
		return sPressed;
	}

	/**
	 *  Method that set if the key "S" is pressed or not.
	 * 
	 *  @param sPressed: Boolean that indicate if the key "S" is pressed or not.
	 */
	
	public void setSPressed(boolean sPressed) {
		this.sPressed = sPressed;
	}
	
	/**
	 *  Method that return Megaman.
	 * 
	 *  @return megaman.
	 */
	
	public Character getMegaman() {
		return megaman;
	}

	/**
	 *  Method for know if the game is ended or not.
	 * 
	 *  @return true if the game has finished, false if not.
	 */
	
	public boolean getEnded() {
		return ended;
	}
}
