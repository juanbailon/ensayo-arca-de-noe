/*
 * Autor: JUAN JOSE BAILON CALDERON  
 * CODIGO: 2028696
 * e-mail: juan.bailon@correounivalle.edu.co
 */
package ArcaDeNoe;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class Card. represent a playing card, with a back image and a front image 
 */
public class Card extends JButton {
	
	private int id;
	private static int heightCard=142, widthCard=96;
	private ImageIcon image, cardBack;
	
	
	/**
	 * Instantiates a new card.
	 *
	 * @param image the image that belongs to the front of the card
	 * @param id the id of the card
	 */
	public Card( ImageIcon image, int id ) {
		
		this.image = image;
		this.id = id;
		this.cardBack = new ImageIcon("src/images/respaldoCarta.jpg");
		this.setIcon(cardBack);
		this.setSelectedIcon(image);
		this.setDisabledIcon(cardBack);
		this.setDisabledSelectedIcon(image);
		
		this.setPreferredSize( new Dimension( widthCard, heightCard ) );
		
	}
	
	/**
	 * Instantiates a new card.
	 *
	 * @param imagePath the image path to the image that belongs to the front of the card
	 * @param id the id of the card
	 */
	public Card( String imagePath, int id ) {
		
		this.image = new ImageIcon( imagePath );
		this.id = id;
		this.cardBack = new ImageIcon("src/images/respaldoCarta.jpg");
		this.setIcon(cardBack);
		
		
		this.setSelectedIcon(this.image);
		this.setDisabledIcon(cardBack);
		this.setDisabledSelectedIcon(this.image);
		
		this.setPreferredSize( new Dimension( widthCard, heightCard ) );
		
	}
	
	/**
	 * Instantiates a new card.
	 *
	 * @param image the image that belongs to the front of the card
	 * @param id the id of the card
	 * @param cardBack the card back is the image that belongs to the back of the card
	 */
	public Card( ImageIcon image, int id, ImageIcon cardBack ) {
		
		this.image = image;
		this.id = id;
		this.cardBack = cardBack;
		this.setIcon(cardBack);
		this.setSelectedIcon(image);
		this.setDisabledIcon(cardBack);
		this.setDisabledSelectedIcon(image);
		
		this.setPreferredSize( new Dimension( widthCard, heightCard ) );
	}
	
	
	/**
	 * Equal to. returns true if the two cards are the same, otherwise false
	 *
	 * @param otherCard the other card
	 * @return true, if successful
	 */
	public boolean equalTo( Card otherCard ) {
		
		if( this.id == otherCard.getId() ) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	/**
	 * Resize card. resize the card and it's images acording to the given parameters
	 *
	 * @param newWidth the new width
	 * @param newHeight the new height
	 */
	public void resizeCard(int newWidth, int newHeight) {
		
		heightCard = newHeight;
		widthCard = newWidth;
		
		Image tempImage = this.image.getImage().getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_DEFAULT);
		this.image.setImage(tempImage);
		
		tempImage = this.cardBack.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
		this.cardBack.setImage(tempImage);
		
		
		this.setPreferredSize( new Dimension( newWidth, newHeight ) );
	}
	
	

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the height card.
	 *
	 * @return the height card
	 */
	public static int getHeightCard() {
		return heightCard;
	}

	/**
	 * Sets the height card.
	 *
	 * @param heightCard the new height card
	 */
	public static void setHeightCard(int heightCard) {
		Card.heightCard = heightCard;
	}

	/**
	 * Gets the width card.
	 *
	 * @return the width card
	 */
	public static int getWidthCard() {
		return widthCard;
	}

	/**
	 * Sets the width card.
	 *
	 * @param widthCard the new width card
	 */
	public static void setWidthCard(int widthCard) {
		Card.widthCard = widthCard;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	
	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public ImageIcon getImage() {
		return image;
	}

	/**
	 * Gets the card back.
	 *
	 * @return the card back
	 */
	public ImageIcon getCardBack() {
		return cardBack;
	}

	/**
	 * Sets the card back.
	 *
	 * @param cardBack the new card back
	 */
	public void setCardBack(ImageIcon cardBack) {
		this.cardBack = cardBack;
	}

	
	
}//END Card class
