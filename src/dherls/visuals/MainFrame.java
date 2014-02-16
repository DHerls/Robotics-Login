package dherls.visuals;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	private InfoPanel infoPanel = new InfoPanel();
	
	public MainFrame(){
		setLayout(new GridBagLayout());
		setTitle("Log-In");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//addTextContent();
		c.gridy = 0;
		c.gridwidth = 2;
		add(infoPanel,c);
		c.gridy = 1;
		c.gridwidth = 1;
		add(new ExtraButtonsPanel(),c);
		c.gridx = 1;
		add(loginPanel,c);
		addPictureContent();
		//new AddMemberFrame(12121);
		try {
			BufferedImage img = ImageIO.read(getClass().getResource("/dherls/resources/icon.png"));
			setIconImage(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(810,840);
		setLocationRelativeTo(null);
		infoPanel.setText("This is a test of the info panel");
	}

	private void addPictureContent() {
		JPanel pictures = new JPanel();
		pictures.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		for (Member m:Main.getTeam().getMembers()){
			pictures.add(new MemberPicturePanel(m),c);
			
			c.gridx+=2;
			System.out.println(c.gridx);
			if (c.gridx == 8){
				c.gridx = 0;
				c.gridy ++;
				c.gridy++;
			}
		}
		
		scroll = new JScrollPane(pictures);
		scroll.setPreferredSize(new Dimension(700,700));
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		c.gridx =0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(0,0,0,0);
		add(scroll,c);
	}

	@SuppressWarnings("unused")
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
		scroll = null;
		addPictureContent();
		scroll.getVerticalScrollBar().setValue(position);
		repaint();
		revalidate();
		
	}
	
	public InfoPanel getInfoPanel(){
		return infoPanel;
	}

	public void visualize() {
		updateMembers();
		setVisible(true);
	}

}
