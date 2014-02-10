package dherls.visuals;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import dherls.login.Main;

public class ExtraButtonsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton logOutButton = new JButton("Log Out All");
	private JButton viewLogsButton = new JButton("View Log");
	private JButton viewMembersButton = new JButton("View Member List");
	private GridBagConstraints c = new GridBagConstraints();
	
	public ExtraButtonsPanel(){
		setLayout(new GridBagLayout());
		addContent();
	}
	
	private void addContent(){
		c.insets = new Insets(5,5,5,5);
		add(logOutButton,c);
		c.gridy++;
		c.gridy++;
		add(viewLogsButton,c);
		c.gridy++;
		add(viewMembersButton,c);
		
		logOutButton.addActionListener(this);
		viewLogsButton.addActionListener(this);
		viewMembersButton.addActionListener(this);

		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(logOutButton)){
			Main.getTeam().logOutAll();
		} else if (e.getSource().equals(viewLogsButton)){
			//TODO view Logs button.
		} else {
			//TODO view members button.
		}
	}

}
