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

public class LogOutFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton logOutButton = new JButton("Log Out");
	private JButton cancelButton = new JButton("Cancel");
	private GridBagConstraints c = new GridBagConstraints();
	private Member m;
	
	public LogOutFrame(Member m){
		this.m = m;
		setSize(300,150);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());
		setTitle("Log Out?");
		String s = "<html> Member " + m.getName() + "(" + m.getId() + ") is already logged in. Would you like to log them out? </html>";
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
		add(logOutButton,c);
		c.gridx = 1;
		add(cancelButton,c);
		
		logOutButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(logOutButton)){
			Main.getTeam().logOut(m);
			Main.getFrame().getLoginPanel().reset();
			this.dispose();
		} else {
			Main.getFrame().getLoginPanel().retry();
			this.dispose();
		}
		
	}

}
