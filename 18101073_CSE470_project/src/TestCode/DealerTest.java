package TestCode;

import   static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import Model.*;
import View.UNOCard;

import org.mockito.BDDMockito.Then;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

public class DealerTest {
	Dealer d;
	

	@Before
	public void setUp() throws Exception {
	
		d=new Dealer();

	}

	@Test
	public void shuffleTest() {
		
		assertEquals(108,d.shuffle().size());
	}
	
	@Test
	public void getCardTest() {
	
		d.shuffle();
		UNOCard c=d.getCard();
		
		assertNotNull(c);
	}
	

}
