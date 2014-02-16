package dherls.visuals;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import dherls.login.LogBook;

public class ExtraButtonsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton logOutButton = new JButton("Log Out All");
	private JButton viewLogsButton = new JButton("View Log");
	private JButton addMemberButton = new JButton("Add Member");
	private GridBagConstraints c = new GridBagConstraints();
	
	public ExtraButtonsPanel(){
		setLayout(new GridBagLayout());
		addContent();
	}
	
	private void addContent(){
		c.insets = new Insets(5,5,5,5);
		add(logOutButton,c);
		c.gridx++;
		c.gridx++;
		add(viewLogsButton,c);
		c.gridx++;
		add(addMemberButton,c);
		
		logOutButton.addActionListener(this);
		viewLogsButton.addActionListener(this);
		addMemberButton.addActionListener(this);

		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(logOutButton)){
			new LogOutFrame();
			
		} else if (e.getSource().equals(viewLogsButton)){
			LogBook.openTemp();
		} else {
			new AddMemberFrame();
		}
	}

}
