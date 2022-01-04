package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Model.Dealer;
import Model.Game;
import Model.Player;
import Controller.MyCardListener;

public class Session extends JPanel {
	private PlayerPanel player1;
	private PlayerPanel player2;
	private PlayerPanel player3;
	private TablePanel table;	
	
	private Game game;
	
	public Session(Game newGame, UNOCard firstCard){
		//setPreferredSize(new Dimension(960,720));
		setPreferredSize(new Dimension(1200,740));
		setBackground(new Color(46, 187, 166));
		setLayout(new BorderLayout());
		
		game = newGame;
		
		setPlayers();
		table = new TablePanel(firstCard);
		
		player1.setOpaque(false);
		player2.setOpaque(false);
		
		if(game.getPlayers().length==3) {
			player3.setOpaque(false);
		}
		
		add(player1,BorderLayout.NORTH);
		add(player2, BorderLayout.SOUTH);
		
		if(game.getPlayers().length==3) {
			add(player3,BorderLayout.WEST);
			add(table, BorderLayout.EAST);
		}
		else
		{
			add(table, BorderLayout.CENTER);
		}
	}
	
	private void setPlayers() {
		
		player1 = new PlayerPanel(game.getPlayers()[0]);
		player2 = new PlayerPanel(game.getPlayers()[1]);
		if(game.getPlayers().length==3) {
			player3 = new PlayerPanel(game.getPlayers()[2]);	
		}
	}
	
	public void refreshPanel(){
		player1.setCards();
		player2.setCards();
		
		if(game.getPlayers().length==3) {
			player3.setCards();
		}
		table.revalidate();		
		revalidate();
	}
	
	public void updatePanel(UNOCard playedCard){
		table.setPlayedCard(playedCard);
		refreshPanel();
	}	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
