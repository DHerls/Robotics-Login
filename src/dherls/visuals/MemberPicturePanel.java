package dherls.visuals;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dherls.login.Main;
import dherls.login.Member;

public class MemberPicturePanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MemberPictureButton button;
	private Member member;
	private GridBagConstraints c = new GridBagConstraints();
	private PictureOptionsMenu menu;
	
	public MemberPicturePanel(Member m){
		member = m;
		button = new MemberPictureButton(m);
		setLayout(new GridBagLayout());
		addContent();
	}
	
	private void addContent(){
		c.insets = new Insets(5,5,5,5);
		add(button,c);
		button.addActionListener(this);
		menu = new PictureOptionsMenu(member,button);
		button.setComponentPopupMenu(menu);
		c.insets = new Insets(0,0,0,0);
		c.gridy = 1;
		add(new JLabel(member.getName()),c);
		c.gridy = 2;
		add(new JLabel( "(" + member.getId() + ")"),c);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (button.isSelected()){
			Main.getTeam().logIn(member);
		} else {
			Main.getTeam().logOut(member);
		}
		menu.changeState();
	}
}
