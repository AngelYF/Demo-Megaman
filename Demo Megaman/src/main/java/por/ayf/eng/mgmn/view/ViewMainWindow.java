package por.ayf.eng.mgmn.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import por.ayf.eng.mgmn.game.Game;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

/**
 *  JFrame of the game.
 *  
 *  @author: Ángel Yagüe Flor.
 *  @version: 1.0 - Stable.
 *  @version: 1.1 - Refactor the project.
 */

public class ViewMainWindow extends JFrame {
	private static final long serialVersionUID = 1L;						
	
	public final static int WIDTH = 768; 									
	public final static int HIGH = 665;										
	public final static int BORDER = 6;									
	
	private JPanel contentPane; 											
	private Game game;												
	
	private HashSet<Integer> keys;	// Variable where I save the keys have been pressed, for can analyze the actions when there are some keys pressed at the same time.
	
	public ViewMainWindow() {
		initComponents();
	}
	
	private void initComponents() {
		this.setTitle("Demo de Megaman");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icons/Icon.png")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(0, 0, WIDTH, HIGH);
		this.setLayout(null); 
		this.setLocationRelativeTo(null);

		this.keys = new HashSet<Integer>();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);

		this.addKeyListener(new KeyAdapter() {
	        public void keyPressed(KeyEvent evt) {	 
	        	// If the game hasn't finished, then you have the control.
	        	if(game.getEnded() == false) {
		        	keys.add(evt.getKeyCode());
		        	
		        	// The action happens	    
		        	if(game.getMegaman().getDamage() == false) {
			        	if(keys.contains(KeyEvent.VK_A) && keys.contains(KeyEvent.VK_LEFT) && keys.contains(KeyEvent.VK_S)) { // LEFT JUMP WHILE IS SHOOTING
		        			if(game.getMegaman().getInAir() == false && game.getAPressed() == false) {
		        				game.jumpMegaman();
		        			}
		        			
		        			game.moveMegaman(0);
		        			
		        			if(game.getSPressed() == false) {
		        				game.shootMegaman();
		        			}	
		        		} else if(keys.contains(KeyEvent.VK_A) && keys.contains(KeyEvent.VK_RIGHT) && keys.contains(KeyEvent.VK_S)) {	// RIGHT JUMP WHILE IS SHOOTING
		        			if(game.getMegaman().getInAir() == false && game.getAPressed() == false) {
		        				game.jumpMegaman();
		        			}
		        			
		        			game.moveMegaman(1);
		        			
		        			if(game.getSPressed() == false) {
		        				game.shootMegaman();
		        			}	
		        		} else if(keys.contains(KeyEvent.VK_A) && keys.contains(KeyEvent.VK_DOWN)) {	// SLIDE
		        			if(game.getMegaman().getInAir() == false && game.getAPressed() == false && game.getMegaman().getSliding() == false) {
		        				game.slideMegaman();
		        			}
		        		} else if(keys.contains(KeyEvent.VK_A) && keys.contains(KeyEvent.VK_LEFT)) {	// LEFT JUMP
		        			if(game.getMegaman().getInAir() == false && game.getAPressed() == false) {
		        				game.jumpMegaman();
		        			}
		        			
		        			game.moveMegaman(0);
		        		} else if(keys.contains(KeyEvent.VK_A) && keys.contains(KeyEvent.VK_RIGHT)) {	// RIGHT JUMP
		        			if(game.getMegaman().getInAir() == false && game.getAPressed() == false) {
		        				game.jumpMegaman();
		        			}
		        			
		        			game.moveMegaman(1);
		        		} else if(keys.contains(KeyEvent.VK_A) && keys.contains(KeyEvent.VK_S)) {	// JUMP WHILE IS SHOOTING
		        			if(game.getMegaman().getInAir() == false && game.getAPressed() == false) {
		        				game.jumpMegaman();
		        			}
		        			
		        			if(game.getSPressed() == false) {
		        				game.shootMegaman();
		        			}	
		        		} else if(keys.contains(KeyEvent.VK_RIGHT) && keys.contains(KeyEvent.VK_S)) {	// RIGHT MOVE WHILE IS SHOOTING
		        			game.moveMegaman(1);
		        			
		        			if(game.getSPressed() == false) {
		        				game.shootMegaman();
		        			}	
		        		} else if(keys.contains(KeyEvent.VK_LEFT) && keys.contains(KeyEvent.VK_S)) { // LEFT MOVE WHILE IS SHOOTING
		        			game.moveMegaman(0);
		        			
		        			if(game.getSPressed() == false) {
		        				game.shootMegaman();
		        			}	
		        		} else if(keys.contains(KeyEvent.VK_LEFT)) { // LEFT MOVE
		        			game.moveMegaman(0);
		        		} else if(keys.contains(KeyEvent.VK_RIGHT)) { // RIGHT MOVE
		        			game.moveMegaman(1);
		        		} else if(keys.contains(KeyEvent.VK_A)) {	// JUMP
		        			if(game.getMegaman().getInAir() == false && game.getAPressed() == false) {
		        				game.jumpMegaman();
		        			}
		        		} else if(keys.contains(KeyEvent.VK_S)) {	// SHOOT
		        			if(game.getSPressed() == false) {
		        				game.shootMegaman();
		        			}
		        		}
		        	}
	        	}
	        	
	        }
	        
	        public void keyReleased(KeyEvent evt) {
	        	keys.remove(evt.getKeyCode()); 		
	        	
	        	if(keys.size() == 0) { 				
		        	if(evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT) { // STOP
		        		if(game.getMegaman().getInAir() == false) {
		        			game.stopMegaman();
		        		} else {
		        			game.airStopMegaman();
		        		}
		        	} else if(evt.getKeyCode() == KeyEvent.VK_A) { // JUMP
		        		if(game.getMegaman().getInAir()) {
		        			game.getMegaman().setFalling(true);
		        		}
		        		
	        			if(game.getAPressed()) {
	        				game.setAPressed(false);
	        			}
	        		} else if(evt.getKeyCode() == KeyEvent.VK_S) { // SHOOT
	        			if(game.getSPressed()) {
	        				game.setSPressed(false);
	        			}
	        		}
	        	} else {
	        		if(evt.getKeyCode() == KeyEvent.VK_A) {	// JUMP
	        			if(game.getMegaman().getInAir()) {
	        				game.getMegaman().setFalling(true);
	        			}
	        			
	        			if(game.getAPressed()) {
	        				game.setAPressed(false);
	        			}
	        		} else if(evt.getKeyCode() == KeyEvent.VK_S) { // SHOOT
	        			if(game.getSPressed()) {
	        				game.setSPressed(false);
	        			}
	        		} else if(evt.getKeyCode() == KeyEvent.VK_LEFT && keys.contains(KeyEvent.VK_RIGHT)) {	// SI HE SOLTADO LA IZQUIERDA, PERO PERMANCE LA DERECHA
	        			game.moveMegaman(1);
	        		} else if(evt.getKeyCode() == KeyEvent.VK_RIGHT && keys.contains(KeyEvent.VK_LEFT)) {	// SI HE SOLTADO LA DERECHA, PERO PERMANCE LA IZQUIERDA
	        			game.moveMegaman(0);
	        		} else if(evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT) { // STOP
	        			if(game.getMegaman().getInAir() == false && game.getMegaman().getSliding() == false) {
		        			game.stopMegaman();
		        		} else {
		        			if(game.getMegaman().getInAir()) {
		        				game.airStopMegaman();
		        			}
		        		}
	        		}
	        	}
	        }
	    });
		
		this.game = new Game();
		contentPane.add(game);
		
		this.game.setFocusable(false); 	// Necessary because if I click in the screen, don't lose the focus.
		this.setVisible(true);
	}
}
