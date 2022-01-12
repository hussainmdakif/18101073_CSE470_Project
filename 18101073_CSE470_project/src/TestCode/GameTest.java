package TestCode;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.*;
import java.util.regex.Pattern;
import org.junit.Assert;
import Controller.*;
import Model.*;
import View.*;
//import junit.framework.TestCase;

public class GameTest {

	Game game;
	
	UNOCard uc;
	ActionCard ac;
	NumberCard nc;
	WildCard wc;
	
	Color RED = new Color(192,80,77);
	Color BLUE = new Color(31,73,125);
	Color GREEN = new Color(0,153,0);
	Color YELLOW = new Color(255,204,0);
	Color BLACK = new Color(0,0,0);
	
	
	@Before
	public void setUp() throws Exception {
		game=new Game(1);
		
		ac=Mockito.mock(ActionCard.class);
		wc=Mockito.mock(WildCard.class);
		nc=Mockito.mock(NumberCard.class);
	}

	@Test
	public void getPlayersTest() {
		Player[] p=game.getPlayers();
	    assertEquals("PC",p[0].getName());
	    assertEquals(true,Pattern.matches(".*", p[1].getName()));

		
	}
	
	@Test
	public void getDirectionTest() {
		game.setDirection(true);
		assertTrue(game.getDirection());
	}
	
	@Test
	public void getCardTest() {
		UNOCard uc=game.getCard();
		int type=uc.getType();
		try {
			assertEquals(1,type);
		}catch(AssertionError e) {
			try{
				assertEquals(2,type);
			}catch(AssertionError ee) {
				assertEquals(3,type);
			}
		}
	}
	
	@Test
	public void isOverTest() {
		assertEquals(false, game.isOver());
		
	}
	
	@Test
	public void remainingCardsTest() {
		assertEquals(92, game.remainingCards());
	}
	@Test
	public void isPCsTurnTest() {
		assertEquals(false,game.isPCsTurn());
}
	@Test
	public void playedCardSizeTest() {
		int[] nr=game.playedCardsSize();
		int[] m= {0,0,0};
		assertArrayEquals(m, nr);
	}

}
