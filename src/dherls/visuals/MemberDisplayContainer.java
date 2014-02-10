package dherls.visuals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import dherls.login.Member;

public class MemberDisplayContainer {
	
	public JScrollPane getDisplay(ArrayList<Member> members){
		JPanel panel= new JPanel();
		JScrollPane scroll = new JScrollPane(setUpPanel(panel ,members));
		scroll.setPreferredSize(new Dimension(400,300));
		scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Logged In Members", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		return scroll;
	}

	private JPanel setUpPanel(JPanel panel, ArrayList<Member> members) {
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(new GridBagLayout());
		c.gridwidth = 1;
		for (Member m: members){
			panel.add(new MemberDisplayPanel(m),c);
			c.gridy++;
			c.gridy++;
		}
		
		return panel;
	}
}
