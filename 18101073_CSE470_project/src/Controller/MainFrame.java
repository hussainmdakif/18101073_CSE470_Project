
package Controller;


import javax.swing.JFrame;
import Model.GameConstants;
import Controller.Server;
import View.Session;


public class MainFrame extends JFrame implements GameConstants {
	
	private Session mainPanel;
	private Server server;
	
	public MainFrame(){	
		server = new Server();
		CARDLISTENER.setServer(server);
		BUTTONLISTENER.setServer(server);
		
		mainPanel = server.getSession();
		add(mainPanel);
	}
}