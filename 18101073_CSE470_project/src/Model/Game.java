package Model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

import Model.*;
import Model.GameConstants;
import View.UNOCard;

public class Game implements GameConstants {

	private Player[] players;
	private boolean isOver;
	private int GAMEMODE;

	private boolean direction=true;
	private PC pc;

	private Dealer dealer;
	private Stack<UNOCard> cardStack;
	
	
	public Game(int mode){
		
		GAMEMODE = mode;

		//Create Players
		String name="";
		String name2="";
		String name3="";
		
		//Create players
		//***********
		if (GAMEMODE== vsPC) {
		//	name=JOptionPane.showInputDialog("PC") ;
			name2 = JOptionPane.showInputDialog("Name of Player 2");
			
		}
		else if (GAMEMODE==thirdplayer) {
			
			name=JOptionPane.showInputDialog("Name of Player 1") ;
			name2 = JOptionPane.showInputDialog("Name of Player 2");
			name3=JOptionPane.showInputDialog("Name of Player 3");
		}
		else if (GAMEMODE==MANUAL) {
			name=JOptionPane.showInputDialog("Name of Player 1") ;
			name2 = JOptionPane.showInputDialog("Name of Player 2");
		}
		
		
		if(GAMEMODE==vsPC)
			pc = new PC();
		
		Player player1= new Player("Default");
		Player player2= new Player("Default");
		Player player3= new Player("Default");
		if (GAMEMODE== vsPC) {
			player1 =pc;
			player2 = new Player(name2);	
			players = new Player[]{player1, player2};
			
		}
		else if (GAMEMODE==thirdplayer) {
			
			player1 = new Player(name);	
			player2 = new Player(name2);	
			player3 = new Player(name3);
			players = new Player[]{player1, player2,player3};
		}
		else if(GAMEMODE==MANUAL) {
			player1 = new Player(name);	
			player2 = new Player(name2);
			players = new Player[]{player1, player2};
		}

		if (GAMEMODE== vsPC) {
			player2.toggleTurn();
		}
		else {
			player1.toggleTurn();
		}


		//Create Dealer
		dealer = new Dealer();
		cardStack = dealer.shuffle();
		dealer.spreadOut(players);

		isOver = false;
	}
	
	public void setDirection(boolean x) {
		direction=x;
	}
	public boolean getDirection() {
		return direction;
	}

	public Player[] getPlayers() {
		return players;
	}

	public UNOCard getCard() {
		return dealer.getCard();
	}
	
	public void removePlayedCard(UNOCard playedCard) {

		for (Player p : players) {
			if (p.hasCard(playedCard)){
				p.removeCard(playedCard);
				
				if (p.getTotalCards() == 1 && !p.getSaidUNO()) {
					infoPanel.setError(p.getName() + " Forgot to say UNO");
					p.obtainCard(getCard());
					p.obtainCard(getCard());
				}else if(p.getTotalCards()>2){
					p.setSaidUNOFalse();
				}
			}			
		}
	}
	
	//give player a card
	public void drawCard(UNOCard topCard) {

		boolean canPlay = false;

		for (Player p : players) {
			if (p.isMyTurn()) {
				UNOCard newCard = getCard();
				p.obtainCard(newCard);
				canPlay = canPlay(topCard, newCard);
				break;
			}
		}

		if (!canPlay)
			switchTurn();
	}

	public void switchTurn() {
		
		if(direction) {
		if(players.length ==2) {
			for (Player p : players) {
				p.toggleTurn();
			}
			
			
				
			
			
		}
		else {
			if(players[0].isMyTurn()) {
				players[0].toggleTurn();
				players[1].toggleTurn();
			}
			else if(players[1].isMyTurn()) {
				players[1].toggleTurn();
				players[2].toggleTurn();
			}
			else {
				players[2].toggleTurn();
				players[0].toggleTurn();
			}
		}
		whoseTurn();
	}else {
		
		if(players.length ==2) {
			for (Player p : players) {
				p.toggleTurn();
			}
			
			
				
			
			
		}
		else {
			if(players[0].isMyTurn()) {
				players[0].toggleTurn();
				players[2].toggleTurn();
			}
			else if(players[1].isMyTurn()) {
				players[1].toggleTurn();
				players[0].toggleTurn();
			}
			else {
				players[2].toggleTurn();
				players[1].toggleTurn();
			}
		}
		whoseTurn();
		
		
	}
		
	}
	
	//Draw cards x times
	public void drawPlus(int times) {
		/*for (Player p : players) {
			if (!p.isMyTurn()) {
				for (int i = 1; i <= times; i++)
					p.obtainCard(getCard());
			}
		}*/
		
		if(direction) {
			
		if(players.length==3) {	
		if(players[0].isMyTurn()) {
			for (int i = 1; i <= times; i++)
				players[1].obtainCard(getCard());
		}
		else if(players[1].isMyTurn()) {
			for (int i = 1; i <= times; i++)
				players[2].obtainCard(getCard());
		}
		else {
			for (int i = 1; i <= times; i++)
				players[0].obtainCard(getCard());
		}
		}
		else {
			if(players[0].isMyTurn()) {
				for (int i = 1; i <= times; i++)
					players[1].obtainCard(getCard());
			}
			else if(players[1].isMyTurn()) {
				for (int i = 1; i <= times; i++)
					players[0].obtainCard(getCard());
			}
			
		}
		}
		else {
			if(players.length==3) {
			if(players[0].isMyTurn()) {
				for (int i = 1; i <= times; i++)
					players[2].obtainCard(getCard());
			}
			else if(players[1].isMyTurn()) {
				for (int i = 1; i <= times; i++)
					players[0].obtainCard(getCard());
			}
			else {
				for (int i = 1; i <= times; i++)
					players[1].obtainCard(getCard());
			}
			
		}else {
			
			if(players[0].isMyTurn()) {
				for (int i = 1; i <= times; i++)
					players[1].obtainCard(getCard());
			}
			else if(players[1].isMyTurn()) {
				for (int i = 1; i <= times; i++)
					players[0].obtainCard(getCard());
			}
			
			
		}
		}
	}
	
	//response whose turn it is
	public void whoseTurn() {

		for (Player p : players) {
			if (p.isMyTurn()){
				infoPanel.updateText(p.getName() + "'s Turn");
				System.out.println(p.getName() + "'s Turn");
			}
		}
		infoPanel.setDetail(playedCardsSize(), remainingCards());
		infoPanel.repaint();
	}
	
	//return if the game is over
	public boolean isOver() {
		
		if(cardStack.isEmpty()){
			isOver= true;
			return isOver;
		}
		
		for (Player p : players) {
			if (!p.hasCards()) {
				isOver = true;
				break;
			}
		}
		
		return isOver;
	}

	public int remainingCards() {
		return cardStack.size();
	}

	public int[] playedCardsSize() {
		int[] nr = new int[3];
		int i = 0;
		for (Player p : players) {
			nr[i] = p.totalPlayedCards();
			i++;
		}
		return nr;
	}

	//Check if this card can be played
	private boolean canPlay(UNOCard topCard, UNOCard newCard) {

		// Color or value matches
		if (topCard.getColor().equals(newCard.getColor())
				|| topCard.getValue().equals(newCard.getValue()))
			return true;
		// if chosen wild card color matches
		else if (topCard.getType() == WILD)
			return ((WildCard) topCard).getWildColor().equals(newCard.getColor());

		// suppose the new card is a wild card
		else if (newCard.getType() == WILD)
			return true;

		// else
		return false;
	}

	//Check whether the player said or forgot to say UNO
	public void checkUNO() {
		for (Player p : players) {
			if (p.isMyTurn()) {
				if (p.getTotalCards() == 1 && !p.getSaidUNO()) {
					infoPanel.setError(p.getName() + " Forgot to say UNO");
					p.obtainCard(getCard());
					p.obtainCard(getCard());
				}
			}
		}		
	}

	public void setSaidUNO() {
		for (Player p : players) {
			if (p.isMyTurn()) {
				if (p.getTotalCards() == 2) {
					p.saysUNO();
					infoPanel.setError(p.getName() + " said UNO");
				}
			}
		}
	}
	
	public boolean isPCsTurn(){
		if(pc.isMyTurn()){
			return true;
		}
		return false;
	}

	//if it's PC's turn, play it for pc
	public void playPC(UNOCard topCard) {		
		
		if (pc.isMyTurn()) {
			boolean done = pc.play(topCard);
			
			if(!done)
				drawCard(topCard);
		}
	}
}
