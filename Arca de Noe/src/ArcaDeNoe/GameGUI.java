/*
 * Autor: JUAN JOSE BAILON CALDERON
 * Codigo: 2028696
 * e-mail: juan.bailon@correounivalle.edu.co
 */
package ArcaDeNoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;



// TODO: Auto-generated Javadoc
/**
 * The Class GameGUI. this class control all the GUI related stuff with the game
 */
public class GameGUI extends JFrame {
	
	private JButton exit, start;
	private JLabel scoreLabel;
	private JTextField scoreValue;
	private JPanel zoneCards, zoneButtons;
	private Escucha escucha;
	private GameControl gameControl;
	private int[][] grids = { {2,2}, {2,3}, {2,4}, {3,4}, {3,4} }; //{rows, cols}
	private ArrayList<Card> clickedCards;
	private Timer timer, deadMessageTimer;
	private boolean firstAttempt;
	
	/**
	 * Instantiates a new game GUI.
	 */
	public GameGUI() {
		initGUI();
		
		this.setTitle("Arca de Noe");
		pack();
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void initGUI() {
		
		//set up container - layout
		this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS) );		
		this.getContentPane().setBackground(Color.black);
		
		//create Escucha, timer, GameControl, clickedCards, etc.
		escucha = new Escucha();
		timer = new Timer(1000, escucha);
		deadMessageTimer = new Timer(900, escucha);
		gameControl = new GameControl();
		clickedCards = new ArrayList<>();
		firstAttempt = true;
		
		//set up GUI components
		
		// *** zoneCards ***
		
		zoneCards = new JPanel( new GridBagLayout() );
		zoneCards.setPreferredSize(new Dimension(550, 550));
		zoneCards.setBorder(new TitledBorder("zoneCards"));
		zoneCards.setBackground(Color.BLACK);
		this.add(zoneCards);
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		paintCardsInPlay();
		
		
		// *** zoneButtons ***
		
		zoneButtons = new JPanel( new GridBagLayout() );		
		zoneButtons.setBackground(Color.BLACK);
		this.add(zoneButtons);
	
		// ** start button **
		start = new JButton("Restart");		
		start.addActionListener(escucha);
		
		constraints.gridx=1;
		constraints.gridy=0;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.insets = new Insets(9, 9, 9, 9);
		zoneButtons.add(start, constraints);

		
		// ** scoreLabel **
		scoreLabel = new JLabel("Score: ");
		scoreLabel.setForeground(Color.WHITE);
		
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.insets = new Insets(9, 9, 9, 6);
		zoneButtons.add(scoreLabel, constraints);
		
		// ** scoreValue **
		scoreValue = new JTextField();
		scoreValue.setEditable(false);
		scoreValue.setText( String.valueOf( gameControl.getScore() ) );
		
		constraints.gridx=1;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.insets = new Insets(9, 9, 9, 6);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		zoneButtons.add(scoreValue, constraints);
		
		
		// ** exit button **
		exit = new JButton("Exit");
		exit.addActionListener(escucha);
		
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		zoneButtons.add(exit, constraints);
		
	}
	
	
	
	/**
	 * Paint cards in play. insets/paints the components of the cardsInPlay(ArrayList) into the zoneCards(JPanel),
	 * 						according to the value of the level 
	 */
	public void paintCardsInPlay() {
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		if(gameControl.getLevel() >= 5) {
			
			int contador=0, rows=grids[ grids.length - 1 ][0], cols=grids[ grids.length - 1 ][1];
			
			for(int i=0; i<rows; i++) {		
				for(int j=0; j<cols; j++) {
					
					if(contador >= (gameControl.getLevel() + 1)*2 ) break;
					
					Card temp = gameControl.getCardsInPlay().get(contador);
					
					temp.addMouseListener(escucha);
					System.out.println( gameControl.getCardsInPlay().get(j).getId() );
					constraints.gridx=j;
					constraints.gridy=i;
					constraints.gridwidth=1;
					constraints.gridheight=1;
					constraints.insets = new Insets(18, 18, 18, 18);
					zoneCards.add(temp, constraints);
					
					contador++;
				}			
			}
			
		}
		else {
			
			int contador=0, rows=grids[ gameControl.getLevel() - 1 ][0], cols=grids[ gameControl.getLevel() - 1 ][1];
			
			for(int i=0; i<rows; i++) {		
				for(int j=0; j<cols; j++) {
					
					if(contador >= (gameControl.getLevel() + 1)*2 ) break;
					
					System.out.println( "contador: " + contador );
					Card temp = gameControl.getCardsInPlay().get(contador);

					temp.addMouseListener(escucha);
					System.out.println( gameControl.getCardsInPlay().get(j).getId() );
					constraints.gridx=j;
					constraints.gridy=i;
					constraints.gridwidth=1;
					constraints.gridheight=1;
					constraints.insets = new Insets(18, 18, 18, 18);
					zoneCards.add(temp, constraints);
					
					contador++;
				}			
			}
			
		}
		
	}
	
	
	/**
	 * Checks if is dead. checks if the score is less or equal to 0, if it does then shows a message( an image)
	 */
	public void isDead() {
		
		if( gameControl.getScore() <= 0 ) {
			
			diseableCardsInPlay();
			timer.stop();
			
			deadMessageTimer.start();
			
			System.out.println(" ######  You're DEAD  ####### ");
		}
	}
	
	
	/**
	 * Next level.
	 */
	public void nextLevel() {
		
		gameControl.nextLevel();
		paintCardsInPlay();		
	}
	
	/**
	 * Restart. set everything to it's default/starting values
	 */
	public void restart() {
		
		System.out.println("You pressed RESTART");
		timer.stop();
		
		zoneCards.removeAll();  
		zoneCards.revalidate();
		zoneCards.repaint();
		/*
		for( Card i : gameControl.getCardsInPlay() ) {
			i.setIcon(null);
			i.setVisible(false);
		}*/
		
		gameControl.restart();
		scoreValue.setText( String.valueOf( gameControl.getScore() ) );
		
		firstAttempt=true;
		clickedCards.clear();
		
		paintCardsInPlay();
		enableCardsInPlay();
	
	}
	
	
	/**
	 * Diseable cards in play.
	 */
	public void diseableCardsInPlay() {
		
		for( Card i : gameControl.getCardsInPlay() ) {
			i.setEnabled(false);
		}
		System.out.println("cards in play are DISEABLE");
		
	}
	
	/**
	 * Enable cards in play.
	 */
	public void enableCardsInPlay() {
		
		for( Card i : gameControl.getCardsInPlay() ) {
			i.setEnabled(true);			
		}
		System.out.println("cards in play are ENEABLE");
		
	}
	
	
	
	private class Escucha extends MouseAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			
			if(eventAction.getSource() == exit ) {
				System.exit(0);
			}
			else if(eventAction.getSource() == start ) {
				
				restart();
								
			}
			else if( eventAction.getSource() == deadMessageTimer ) {
				
				deadMessageTimer.stop();
				
				zoneCards.removeAll();  
				zoneCards.revalidate();
				zoneCards.repaint();
				
				zoneCards.setBackground(Color.BLACK);
				
				JLabel deadImageLabel = new JLabel( new ImageIcon("src/images/shipSinking2.jpg") );
				
				GridBagConstraints constraints = new GridBagConstraints();
				
				constraints.gridx=0;
				constraints.gridy=0;
				constraints.gridwidth=1;
				constraints.gridheight=1;			
				zoneCards.add(deadImageLabel, constraints);
				
			}
			else if(eventAction.getSource() == timer) {
				
				clickedCards.get(0).setSelected(false);
				clickedCards.get(1).setSelected(false);
				timer.stop();
				
				
				boolean flag = gameControl.equalCards( clickedCards.get(0) , clickedCards.get(1) );
				
				if(flag) {
					clickedCards.get(0).setIcon(null);
					clickedCards.get(0).setVisible(false);
					clickedCards.get(1).setIcon(null);
					clickedCards.get(1).setVisible(false);
					
					gameControl.getCardsInPlay().remove( clickedCards.get(0) );
					gameControl.getCardsInPlay().remove( clickedCards.get(1) );
				}
				
				
				if( gameControl.getCardsInPlay().size() == 0 ) {
	
					firstAttempt = true;
					nextLevel();
				}
				
				clickedCards.clear();
				enableCardsInPlay();
				
			}
			
		} // END actionPreformed method
		
		
		@Override
		public void mouseClicked(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			
			Card cardClick = (Card) eventMouse.getSource();
			
			if( cardClick.isEnabled() ) {
				
				cardClick.setSelected(true);
				cardClick.setEnabled(false);
				clickedCards.add( cardClick );
				
				System.out.println("you clicked a card");
				
				
				if( clickedCards.size() == 2 ) {
					
					diseableCardsInPlay();
					boolean flag = gameControl.equalCards( clickedCards.get(0) , clickedCards.get(1) );								
					
					if( flag ) {
						
						timer.start();			
						int newScore = gameControl.getScore();					
						gameControl.setScore(newScore + 1);
						scoreValue.setText( String.valueOf( gameControl.getScore() ) );
						
						System.out.println("Plus 1 point, you found a maching pair of cards");
					}
					else {
						timer.start();
						
						if( firstAttempt == false ) {
							
							int newScore = gameControl.getScore();					
							gameControl.setScore(newScore - 1);
							
							isDead();
							
							scoreValue.setText( String.valueOf( gameControl.getScore() ) );
							
							System.out.println("Minus 1 point, you found a  NOT maching pair of cards");
						}
						
						firstAttempt = false;	
						
					}
					
					
				}
				
			}
			
			
		}// END mouseclicked method
		
		
	}// END Class Escucha
		
		

	
	

}// END GameGUI class
