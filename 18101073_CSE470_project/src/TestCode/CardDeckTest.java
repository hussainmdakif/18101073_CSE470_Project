package TestCode;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Model.CardDeck;

public class CardDeckTest {

	CardDeck c;

	@Before
	public void setUp() throws Exception {
		c=new CardDeck();
	}

	@Test
	public void getCardTest() {
		assertEquals(108, c.getCards().size());
	}

}
