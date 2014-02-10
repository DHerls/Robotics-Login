package dherls.visuals;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dherls.login.Main;
import dherls.login.Member;

public class MemberDisplayPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField nameBox = new JTextField(15);
	private JTextField idBox = new JTextField(10);
	private JTextField stateBox = new JTextField (5);
	private JButton stateButton = new JButton("S");
	private JButton removeButton = new JButton("\u2716");
	private GridBagConstraints c = new GridBagConstraints();
	private Member m;
	
	public MemberDisplayPanel(Member m){
		this.m = m;
		setLayout(new GridBagLayout());
		add(nameBox,c);
		c.gridx = 1;
		add(idBox,c);
		c.gridx = 2;
		add(stateBox,c);
		c.gridx = 3;
		add(stateButton,c);
		c.gridx = 4;
		add(removeButton,c);
		
		stateButton.setMargin(new Insets(-2,5,-2,5));
		removeButton.setMargin(new Insets(-2,3,-2,3));
		stateButton.setToolTipText("Switch State");
		removeButton.setToolTipText("Delete Member");
		stateButton.addActionListener(this);
		removeButton.addActionListener(this);
		
		nameBox.setEnabled(false);
		idBox.setEnabled(false);
		stateBox.setEnabled(false);
		
		nameBox.setHorizontalAlignment(JTextField.CENTER);
		idBox.setHorizontalAlignment(JTextField.CENTER);
		stateBox.setHorizontalAlignment(JTextField.CENTER);
		
		nameBox.setDisabledTextColor(Color.BLACK);
		idBox.setDisabledTextColor(Color.BLACK);
		stateBox.setDisabledTextColor(Color.BLACK);
		
		nameBox.setBorder(BorderFactory.createLineBorder(Color.black));
		idBox.setBorder(BorderFactory.createLineBorder(Color.black));
		stateBox.setBorder(BorderFactory.createLineBorder(Color.black));
		
		nameBox.setText(m.getName());
		idBox.setText(String.valueOf(m.getId()));
		stateBox.setText(m.getIsLoggedIn() ? "IN" : "OUT");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(stateButton)){
			Main.getTeam().toggleState(m);
			stateBox.setText(m.getIsLoggedIn() ? "IN" : "OUT");
		} else {
			new RemoveMemberFrame(m);
		}
		
	}

}
