/*
 * Autor: JUAN JOSE BAILON CALDERON
 * Codigo: 2028696
 * e-mail: juan.bailon@correounivalle.edu.co
 */
package ArcaDeNoe;


import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;


// TODO: Auto-generated Javadoc
/**
 * The Class GameControl. this class controls the game logic
 */
public class GameControl {
	
	private ArrayList<Card> cardsInPlay, cards;
	private int score, level;
	private int[] cardInitialDimension = {0,0};
	
	
	/**
	 * Instantiates a new game control.
	 */
	public GameControl() {
		score=0;
		level=1;
		cards = new ArrayList<Card>();
		cardsInPlay = new ArrayList<Card>();
		
		fillCardsList();
		determineCardsInPlay();
		setCardsInPlay();
		
		cards.get(0);
		cardInitialDimension[0] = Card.getWidthCard();
		
		cardInitialDimension[1] = Card.getHeightCard();
		
	}
	
	/**
	 * Fill cards list. fill the cards ArrayList whit six cards
	 */
	public void fillCardsList() {
		
		for(int i=0; i<6; i++) {
			String img = "src/images/"+ i +".jpg";
			cards.add( new Card(img, i) );
		}		
	}
	
	
	/**
	 * Determine cards in play. determines how many cards should be in play according with the level's value
	 *
	 * @return the int
	 */
	public int determineCardsInPlay() {
		int aux=0;
		
		for(int i=1; i<=level; i++) {
			if(level>=5) {
				aux=12;
				break;
			}
			else if(level==i) {
				aux = (level+1)*2;
				break;
			}
		}
		
		return aux;

	}
	
	
	/**
	 * Sets the cards in play. inserts the corresponding number of cards in the cardsInPlay ArrayList,
	 * 						   it picks the cards randomly 
	 */
	public void setCardsInPlay() {
				
		int aux = determineCardsInPlay();
		Random random = new Random();
		ArrayList<Card> tempList = new ArrayList<>();
		
		
		for(int i=0; i<(aux/2); i++) {
			
			int index = random.nextInt( cards.size() );
			Card temp = cards.get(index);
			
			tempList.add(temp);
			cards.remove(temp);
		}
		
		int num = tempList.size();
		
		for(int i=0; i<num; i++) {
			ImageIcon img = tempList.get(i).getImage();
			int id = tempList.get(i).getId();
			
			tempList.add( new Card(img, id) );
		}
			
		num = tempList.size();
		for(int i=0; i<num; i++) {
			
			int index = random.nextInt( tempList.size() );
			Card temp = tempList.get(index);
			
			cardsInPlay.add(temp);
			tempList.remove(temp);
	
		}
		
	}

	
	/**
	 * Gets the cards in play.
	 *
	 * @return the cards in play
	 */
	public ArrayList<Card> getCardsInPlay() {
		return cardsInPlay;
	}
	
	
	/**
	 * Equal cards. returns true if the two cards are equal, otherwise false 
	 *
	 * @param cardA the card A
	 * @param cardB the card B
	 * @return true, if successful
	 */
	public boolean equalCards( Card cardA, Card cardB ) {
		boolean flag = cardA.equalTo(cardB);
		
		return flag;
	}
	
	
	/**
	 * Restart. resets everything to the default/starting values
	 */
	public void restart() {
		
		score=0;
		level=1;
		cards.clear();
		cardsInPlay.clear();
		
		fillCardsList();
		determineCardsInPlay();
		setCardsInPlay();
		resizeCardsInPlay( cardInitialDimension[0], cardInitialDimension[1] );
	}
	
	
	/**
	 * Next level. sets everything to correspond according to the new level
	 */
	public void nextLevel() {
			
		level++;
		cards.clear();
		cardsInPlay.clear();
		
		fillCardsList();
		determineCardsInPlay();
		setCardsInPlay();
		
		setNewCardDimensionForLevelIncrease();
		
	}

	
	/**
	 * Resize cards in play. resize the cards in play according to the given parameters (cards that are in the cardsInPlay ArrayList)
	 *
	 * @param width the width
	 * @param height the height
	 */
	public void resizeCardsInPlay(int width, int height) {
		
		for( Card i : cardsInPlay ) {
			i.resizeCard(width, height);
		}
		
	}
	
	
	/**
	 * Sets the new card dimension for level increase. resizes the cards in play for the corresponding level 
	 */
	public void setNewCardDimensionForLevelIncrease() {
		
		int newWidth, newHeight, width, height, aux;
		
		width = cardInitialDimension[0];
		height = cardInitialDimension[1];	
		
		aux = (level>5)? 5-1 : level-1;
		
		newWidth = (int) Math.round( width - ( width*0.03125 )*aux );
		newHeight =  (int) Math.round( height - ( height*0.030986 )*aux );  
		
		System.out.println("width: " + width + " ,  height: " + height);
		System.out.println("new width: " + newWidth + " ,  new height: " + newHeight);
		
		resizeCardsInPlay(newWidth, newHeight);		
		
	}
	
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
	
	
	

}// END OF GAMECONTROL CLASS
