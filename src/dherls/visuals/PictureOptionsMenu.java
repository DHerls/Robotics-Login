package dherls.visuals;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import dherls.login.Member;

public class PictureOptionsMenu extends JPopupMenu implements ActionListener{
	private JMenuItem switchState;
   	private JMenuItem editMember = new JMenuItem("Edit User");
   	private JMenuItem deleteMember= new JMenuItem("Delete Member");
   	private Member member;
   	private MemberPictureButton button;
    
   	
   	public PictureOptionsMenu(Member m, MemberPictureButton b){
    	button = b;
   		member = m;
    	switchState = new JMenuItem(m.getIsLoggedIn() ? "Log Out" : "Log In");
    	add(switchState);
    	add(editMember);
    	add(deleteMember);
    	switchState.addActionListener(this);
    	editMember.addActionListener(this);
    	deleteMember.addActionListener(this);
    }
    
    public void changeState(){
    	removeAll();
    	switchState = new JMenuItem(member.getIsLoggedIn() ? "Log Out" : "Log In");
    	add(switchState);
    	add(editMember);
    	add(deleteMember);
    	switchState.addActionListener(this);
    	editMember.addActionListener(this);
    	deleteMember.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(switchState)){
			button.doClick();
			
		} else if(e.getSource().equals(deleteMember)){
			new RemoveMemberFrame(member);
		} else {
			new EditMemberFrame(member);
		}
	}
}
