package dherls.visuals;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import dherls.login.Main;

public class AddMemberFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField nameBox = new JTextField(15);
	private JTextField idBox = new JTextField(10);
	private JButton addMemberButton = new JButton("Add Member");
	private JButton cancelButton = new JButton ("Cancel");
	private JCheckBox logInBox = new JCheckBox("Log in new member after creation?");
	private GridBagConstraints c = new GridBagConstraints();
	
	public AddMemberFrame(int id){
		setSize(300,250);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
		setTitle("Add Member");
		addContent(id);
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		setResizable(false);
		setVisible(true);
	}
	
	private void addContent(int id) {
		idBox.setText(String.valueOf(id));
		idBox.setHorizontalAlignment(JTextField.CENTER);
		nameBox.setHorizontalAlignment(JTextField.CENTER);
		
		
		c.gridwidth = 2;
		add(new JLabel("The ID " + String.valueOf(id) + " is not on the members list."),c);
		c.gridy = 1;
		add(new JLabel( "Would you like to add a member?"),c);
		
		c.insets = new Insets(5,5,5,5);
		c.gridy = 2;
		add(new JLabel("Name:"),c);
		c.gridy = 3;
		add(nameBox,c);
		c.gridy = 4;
		add(new JLabel("ID"),c);
		c.gridy = 5;
		add(idBox,c);
		
		c.gridy = 6;
		add(logInBox,c);
		logInBox.setSelected(true);
		
		c.gridwidth = 1;
		c.gridy = 7;
		add(addMemberButton,c);
		c.gridx = 1;
		add(cancelButton,c);
		
		nameBox.addActionListener(this);
		addMemberButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addMemberButton) || e.getSource().equals(nameBox)){
			if (idBox.getText().trim().equals("") || nameBox.getText().trim().equals("")){
				new MessageFrame("One or more fields have been left blank.");
			} else {
				try {
					if (Main.getTeam().addMember(nameBox.getText(), Integer.parseInt(idBox.getText().trim()), logInBox.isSelected())){
						Main.getFrame().getLoginPanel().reset();
						setVisible(false);
					}
				} catch (NumberFormatException e1) {
					new MessageFrame("The ID must be a number.");
				}
			}
		} else {
			setVisible(false);
			Main.getFrame().getLoginPanel().retry();
		}
	}

}
