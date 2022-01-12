package TestCode;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import View.UNOCard;
import Model.ActionCard;
import Model.NumberCard;
import Model.WildCard;

import Model.Player;

public class PlayerTest {
	Player p;
	ActionCard ac;
	NumberCard nc;
	WildCard wc;

	@Before
	public void setUp() throws Exception {
		p=new Player();
		ac=Mockito.mock(ActionCard.class);
		wc=Mockito.mock(WildCard.class);
		nc=Mockito.mock(NumberCard.class);
	}

	@Test
	public void getNameTest() {
		assertEquals(null,p.getName());
		assertNotEquals("Akif", p.getName());
		p.setName("Akif");
		assertEquals("Akif",p.getName());
		assertNotEquals("ami", p.getName());
		
	}
	
	@Test
	public void getAllCardsTest() {
		LinkedList<UNOCard> list=new LinkedList<UNOCard>();
		assertEquals(list, p.getAllCards());
		p.obtainCard(ac);
		list.add(ac);
		assertEquals(list, p.getAllCards());
		p.obtainCard(wc);
		list.add(wc);
		assertEquals(list, p.getAllCards());
		p.obtainCard(nc);
		list.add(nc);
		assertEquals(list, p.getAllCards());
	}
	
	@Test
	public void getTotalCardsTest() {
	
	
		assertEquals(0, p.getTotalCards());
		p.obtainCard(ac);
		assertEquals(1, p.getTotalCards());
		p.obtainCard(wc);
		assertEquals(2,p.getTotalCards());
		p.obtainCard(nc);
		assertEquals(3, p.getTotalCards());
	}
	@Test
	public void hasCardTest() {
		p.obtainCard(ac);
		assertEquals(false, p.hasCard(wc));
		assertEquals(true, p.hasCard(ac));
	}

	@Test
	public void totalPlayedCardsTest() {
		assertEquals(0,p.totalPlayedCards());
		p.obtainCard(ac);
		p.removeCard(ac);
		assertEquals(1,p.totalPlayedCards());
	}
	
	
	@Test
	public void isMyTurnTest() {
		assertNotEquals(true,p.isMyTurn());
		assertEquals(false,p.isMyTurn());
		p.toggleTurn();
		assertEquals(true,p.isMyTurn());
	}
	@Test
	public void hasCardsTest() {
		assertEquals(false,p.hasCards());
		p.obtainCard(ac);
		assertNotEquals(false,p.hasCards());
		assertEquals(true,p.hasCards());
	}
	@Test
	public void getSaidUNO(){
		assertEquals(false,p.getSaidUNO());
		p.saysUNO();
		assertEquals(true,p.getSaidUNO());
		p.setSaidUNOFalse();
		assertEquals(false,p.getSaidUNO());
	}

}