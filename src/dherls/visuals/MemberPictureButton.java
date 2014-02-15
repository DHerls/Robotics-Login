package dherls.visuals;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import dherls.login.Member;

public class MemberPictureButton extends JToggleButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Member member;
	private BufferedImage image;
	private BufferedImage inBorder;
	private BufferedImage outBorder;
	private BufferedImage combinedIn;
	private BufferedImage combinedOut;
	
	public MemberPictureButton(Member m) {
		member = m;
		try {
			image = getScaledImage(member.getImage(),150,250);
			inBorder = ImageIO.read(getClass().getResource("/dherls/resources/Inborder.png"));
			outBorder = ImageIO.read(getClass().getResource("/dherls/resources/Outborder.png"));
		} catch (IOException e) {
		}
		combinedIn = getCombinedImage(image, inBorder);
		combinedOut = getCombinedImage(image, outBorder);
		setBorder(null);
		setIcon(new ImageIcon(combinedOut));
		setSelectedIcon(new ImageIcon(combinedIn));
		if(m.getIsLoggedIn()){
			setSelected(true);
		}
		
	}
	
	public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
	    int imageWidth  = image.getWidth();
	    int imageHeight = image.getHeight();

	    double scaleX = (double)width/imageWidth;
	    double scaleY = (double)height/imageHeight;
	    AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
	    AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

	    return bilinearScaleOp.filter(image,new BufferedImage(width, height, image.getType()));
	}
	
	public static BufferedImage getCombinedImage(BufferedImage i1, BufferedImage i2) {
        if (i1.getHeight() != i2.getHeight()
                || i1.getWidth() != i2.getWidth()) {
            throw new IllegalArgumentException("Images are not the same size!");
        }
        BufferedImage bi = new BufferedImage(i1.getWidth(), i1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.getGraphics();
        g.drawImage(i1,0,0,null);
        g.drawImage(i2,0,0,null);
        g.dispose();

        return bi;
    }
	
	
}