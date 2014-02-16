package dherls.visuals;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel info = new JLabel();
	
	public InfoPanel(){
		add(info);
		setPreferredSize(new Dimension(790,30));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public void setText(String s){
		info.setText(s);
		repaint();
		revalidate();
	}
	
}
