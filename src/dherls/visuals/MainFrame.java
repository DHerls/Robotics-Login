package dherls.visuals;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private GridBagConstraints c = new GridBagConstraints();
	private IDLoginPanel loginPanel = new IDLoginPanel();
	
	public MainFrame(){
		setLayout(new GridBagLayout());
		setTitle("Log-In");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addContent();
		
		setSize(loginPanel.getPreferredSize().width+50, loginPanel.getPreferredSize().height+50);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	private void addContent() {
		add(loginPanel,c);
	}
	
	public IDLoginPanel getLoginPanel(){
		return loginPanel;
	}

}
