package dherls.visuals;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import dherls.login.Main;
import dherls.login.Member;


public class IDLoginPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField idBox = new JTextField(10);
	private JButton logInButton = new JButton("Log In");
	private GridBagConstraints c = new GridBagConstraints();
	
	
	public IDLoginPanel(){
		setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(124,110));
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		addContent();
	}
	
	private void addContent() {
		c.insets = new Insets(10,5,10,5);
		add(new JLabel("Member ID:"));
		c.gridy = 1;
		add(idBox,c);
		c.gridy = 2;
		add(logInButton,c);
		
		idBox.setHorizontalAlignment(JTextField.CENTER);
		logInButton.addActionListener(this);
		idBox.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try{
			Member m =Main.getTeam().getMember(Integer.parseInt(idBox.getText().trim()));
			if (m!=null){
				if (!m.getIsLoggedIn()){
					Main.getTeam().logIn(m);
					reset();
				} else if (m.getIsLoggedIn()){
				new LogOutFrame(m);
				} 
			}else {
				new AddMemberFrame(Integer.parseInt(idBox.getText().trim()));
			}
		}catch (NumberFormatException e){
			new MessageFrame("The ID must be a number.");
		}
		
	}

	
	public void reset(){
		idBox.setText("");
		idBox.requestFocus();
		
	}
	
	public void retry(){
		idBox.requestFocus();
		idBox.selectAll();
	}

}
