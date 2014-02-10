package dherls.visuals;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import dherls.login.Main;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private GridBagConstraints c = new GridBagConstraints();
	private IDLoginPanel loginPanel = new IDLoginPanel();
	private JScrollPane loggedInPanel = new MemberDisplayContainer().getDisplay(Main.getTeam().getLoggedIn());
	
	public MainFrame(){
		setLayout(new GridBagLayout());
		setTitle("Log-In");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addContent();
		
		setSize(500,500);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	private void addContent() {
		c.gridwidth =1;
		c.anchor = GridBagConstraints.EAST;
		add(loginPanel,c);
		c.gridx = 2;
		add(new ExtraButtonsPanel());
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		add(loggedInPanel,c);
	}
	
	public IDLoginPanel getLoginPanel(){
		return loginPanel;
	}
	
	public void checkLoggedIn(){
		remove(loggedInPanel);
		repaint();
		loggedInPanel = new MemberDisplayContainer().getDisplay(Main.getTeam().getLoggedIn());
		add(loggedInPanel,c);
		revalidate();
		repaint();
		
		
	}

}
