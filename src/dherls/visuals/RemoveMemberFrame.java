package dherls.visuals;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import dherls.login.Main;
import dherls.login.Member;

public class RemoveMemberFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton removeButton = new JButton("Remove Member");
	private JButton cancelButton = new JButton("Cancel");
	private GridBagConstraints c = new GridBagConstraints();
	private Member m;
	
	public RemoveMemberFrame(Member m){
		this.m = m;
		setSize(300,150);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
		setTitle("Log Out?");
		String s = "<html> Are you sure you want to delete " + m.getName() + "(" + m.getId() + ")? </html>";
		addContent(s);
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		setVisible(true);
		
	}
	
	
	private void addContent(String s) {
		c.insets = new Insets(10,10,10,10);
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		JLabel j = new JLabel(s);
		j.setHorizontalTextPosition(SwingConstants.CENTER);
		add(j,c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1;
		c.gridwidth = 1;
		add(removeButton,c);
		c.gridx = 1;
		add(cancelButton,c);
		
		removeButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(removeButton)){
			Main.getTeam().removeMember(m);
			setVisible(false);
		} else {
			setVisible(false);
		}
		
	}

}

