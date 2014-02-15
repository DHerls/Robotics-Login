package dherls.visuals;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dherls.login.Main;
import dherls.login.Member;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private GridBagConstraints c = new GridBagConstraints();
	private IDLoginPanel loginPanel = new IDLoginPanel();
	private JScrollPane loggedInPanel = new MemberDisplayContainer().getDisplay(Main.getTeam().getLoggedIn());
	private JScrollPane scroll;
	
	public MainFrame(){
		setLayout(new GridBagLayout());
		setTitle("Log-In");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//addTextContent();
		c.gridy = 0;
		add(new ExtraButtonsPanel(),c);
		addPictureContent();
		//new AddMemberFrame(12121);
		
		setSize(800,800);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	private void addPictureContent() {
		JPanel pictures = new JPanel();
		pictures.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		for (Member m:Main.getTeam().getMembers()){
			pictures.add(new MemberPicturePanel(m),c);
			
			c.gridx++;
			if (c.gridx == 4){
				c.gridx = 0;
				c.gridy ++;
				c.gridy++;
			}
		}
		
		scroll = new JScrollPane(pictures);
		scroll.setPreferredSize(new Dimension(700,700));
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		c.gridx =0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(0,0,0,0);
		add(scroll,c);
	}

	private void addTextContent() {
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

	public void updateMembers() {
		int position = scroll.getVerticalScrollBar().getValue();
		remove(scroll);
		addPictureContent();
		scroll.getVerticalScrollBar().setValue(position);
		repaint();
		revalidate();
		
	}

}
