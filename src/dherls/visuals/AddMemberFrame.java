package dherls.visuals;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import dherls.login.FileHandler;
import dherls.login.Main;

public class AddMemberFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField nameBox = new JTextField(15);
	private JTextField idBox = new JTextField(10);
	private JButton addMemberButton = new JButton("Add Member");
	private JButton cancelButton = new JButton ("Cancel");
	private JCheckBox logInBox = new JCheckBox("Log in new member after creation?");
	private JTextField imagePathBox = new JTextField(10);
	private JButton browseButton = new JButton("Browse");
	private File picture = null;
	private GridBagConstraints c = new GridBagConstraints();
	
	public AddMemberFrame(int id){
		setSize(300,350);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
		setTitle("Add Member");
		addContent(id);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		setResizable(false);
		setVisible(true);
	}
	
	public AddMemberFrame(){
		setSize(300,350);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
		setTitle("Add Member");
		addContent();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
		setResizable(false);
		setVisible(true);
	}
	
	private void addContent() {
		idBox.setHorizontalAlignment(JTextField.CENTER);
		nameBox.setHorizontalAlignment(JTextField.CENTER);
		imagePathBox.setEnabled(false);
		imagePathBox.setDisabledTextColor(Color.black);
		
		
		c.gridwidth = 2;
		c.gridy = 1;
		
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
		add(new JLabel("Select a custom image"),c);
		c.gridwidth = 1;
		c.gridy = 7;
		add(imagePathBox,c);
		c.gridx = 1;
		add(browseButton,c);
		
		c.gridx = 0;
		c.gridwidth = 2;
		
		c.gridy = 8;
		add(logInBox,c);
		logInBox.setSelected(true);
		
		c.gridwidth = 1;
		c.gridy = 9;
		add(addMemberButton,c);
		c.gridx = 1;
		add(cancelButton,c);
		
		nameBox.addActionListener(this);
		addMemberButton.addActionListener(this);
		cancelButton.addActionListener(this);
		browseButton.addActionListener(this);
		
	}

	private void addContent(int id) {
		idBox.setText(String.valueOf(id));
		idBox.setHorizontalAlignment(JTextField.CENTER);
		nameBox.setHorizontalAlignment(JTextField.CENTER);
		imagePathBox.setEnabled(false);
		imagePathBox.setDisabledTextColor(Color.black);
		
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
		add(new JLabel("Select a custom image"),c);
		c.gridwidth = 1;
		c.gridy = 7;
		add(imagePathBox,c);
		c.gridx = 1;
		add(browseButton,c);
		
		c.gridx = 0;
		c.gridwidth = 2;
		
		c.gridy = 8;
		add(logInBox,c);
		logInBox.setSelected(true);
		
		c.gridwidth = 1;
		c.gridy = 9;
		add(addMemberButton,c);
		c.gridx = 1;
		add(cancelButton,c);
		
		addMemberButton.addActionListener(this);
		cancelButton.addActionListener(this);
		browseButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addMemberButton)){
			if (idBox.getText().trim().equals("") || nameBox.getText().trim().equals("")){
				new MessageFrame("One or more fields have been left blank.");
			} else {
				try {
					if (Main.getTeam().addMember(nameBox.getText(), Integer.parseInt(idBox.getText().trim()), logInBox.isSelected())){
						
						try {
							FileHandler.copyPic(picture,  Integer.parseInt(idBox.getText().trim()));
							Main.getTeam().getLatestMember().loadPicture();
							Main.getFrame().updateMembers();
						} catch (IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
						
						//Main.getFrame().getLoginPanel().reset();
						this.dispose();
					}
				} catch (NumberFormatException e1) {
					new MessageFrame("The ID must be a number.");
				}
			}
		} else if (e.getSource().equals(browseButton)) {
			picture = FileHandler.getMemberPic();
			if (picture!=null){
				imagePathBox.setText(picture.getAbsolutePath());
			} else {
				imagePathBox.setText("");
			}
		}else {
			this.dispose();
			//Main.getFrame().getLoginPanel().retry();
		}
	}

}
