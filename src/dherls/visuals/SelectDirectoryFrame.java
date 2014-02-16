package dherls.visuals;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import dherls.login.FileHandler;
import dherls.login.Main;

public class SelectDirectoryFrame extends JFrame implements ActionListener, WindowListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField fileLocationBox = new JTextField(20);
	private JButton browseButton = new JButton("Browse"); 
	private JButton okButton = new JButton("OK");
	private GridBagConstraints c = new GridBagConstraints();
	
	public SelectDirectoryFrame(){
		addWindowListener(this);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addContent();
		setSize(400,120);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addContent() {
		c.insets = new Insets(5,5,5,5);
		c.gridwidth = 2;
		add(new JLabel("Please select a folder to store user data:"),c);
		c.gridwidth = 1;
		c.gridy =1;
		add(fileLocationBox,c);
		c.gridx =1;
		add(browseButton,c);
		c.gridx = 2;
		add(okButton,c);
		
		fileLocationBox.setText(FileHandler.getDefaultDir().getAbsolutePath());
		
		browseButton.addActionListener(this);
		okButton.addActionListener(this);
	}
	
	public File browseFile() {
		JFileChooser fc = new JFileChooser();
		FileHandler.getDefaultDir().mkdirs();

		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		File f = new File(fileLocationBox.getText());
		fc.setCurrentDirectory(f.exists() && f.isDirectory() ? f : FileHandler.getDefaultDir());		
		
		int returnVal = fc.showOpenDialog(null);
		

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		} else {
			return null;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(browseButton)){
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			}
			File f = browseFile();
			if (f!=null){
				fileLocationBox.setText(f.getAbsolutePath());
			}else {
				
			}
		} else if (e.getSource().equals(okButton)){
			File f = new File(fileLocationBox.getText().trim());
			if ((f.exists() && f.isDirectory())|| f.getAbsolutePath().equals(FileHandler.getDefaultDir().getAbsolutePath())){
				FileHandler.setDir(f);
				System.out.println("Default directory set at " + f.getAbsolutePath());
				this.dispose();
				Main.execute();
			} else {
				new MessageFrame("That directory doesn't exist");
			}
		}
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		this.dispose();
		FileHandler.getDefaultDir().delete();		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
