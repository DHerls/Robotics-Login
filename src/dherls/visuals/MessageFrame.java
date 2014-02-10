package dherls.visuals;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MessageFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton okButton = new JButton("OK");
	private GridBagConstraints c = new GridBagConstraints();
	
	public MessageFrame(String s){
		setTitle("Message");
		setLayout(new GridBagLayout());
		setSize(300,100);
		setLocationRelativeTo(null);
		add(new JLabel(s),c);
		c.gridy = 1;
		add(okButton,c);
		okButton.addActionListener(this);
		setResizable(false);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
	}

}
