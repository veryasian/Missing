/* File: MenuFactory.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * Casey Huang    	300316284
 * 
 * Date				Author			Modification
 * 24 Sep 16		Edward Kelly	created class
 * 6 Oct 16			Casey Huang		Added another createLabel method for game menu label
 * 									- change of font size and spacing
 */
package missing.ui.menustyle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import missing.datastorage.assetloader.GameAssets;
import missing.ui.controller.VControl;

/**
 * Used for creating components of the menu so a set style can be followed
 *
 */
public class MenuFactory {
	public static final Color TEXT_COLOUR = Color.BLACK;
	public static final Color TEXT_COLOUR_HIGHLIGHTED = new Color(122, 169, 12);
	public static final int TEXT_FIELD_HEIGHT = 30;

	private MenuFactory() {
		
	}

	public static JLabel createHeading(String text) {
		JLabel label = new JLabel(text);
		label.setName(text);
		Font f = GameAssets.getFont(170f);
		label.setForeground(TEXT_COLOUR);
		label.setFont(f);
		return label;
	}
	
	public static JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setName(text);
		Font f = GameAssets.getFont(30f);
		label.setBorder(new EmptyBorder(0,0,25,0));
		label.setForeground(TEXT_COLOUR);
		label.setFont(f);
		return label;
	}
	
	public static JLabel createLabel2(String text) {
		JLabel label = new JLabel(text);
		label.setName(text);
		Font f = GameAssets.getFont(60f);
		label.setBorder(new EmptyBorder(50,0,55,0));
		label.setForeground(TEXT_COLOUR);
		label.setFont(f);
		return label;
	}

	public static TextField createTextField(int width) {
		TextField field = new TextField();
		field.setPreferredSize(new Dimension(width, TEXT_FIELD_HEIGHT));
		Font f = GameAssets.getFont(30f);
		field.setForeground(TEXT_COLOUR);
		field.setFont(f);
		return field;
	}

	public static JButton createButton(String text) {
		JButton btn = new JButton();
		Font f = GameAssets.getFont(30f);
		btn.setFont(f);
		btn.setName(text);
		btn.setText(text);
		btn.setForeground(TEXT_COLOUR);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.addMouseListener(new MouseAdapter() {
			
		    public void mouseEntered(MouseEvent evt) {
		    	btn.setForeground(TEXT_COLOUR_HIGHLIGHTED);
		    }

		    public void mouseExited(MouseEvent evt) {
		    	btn.setForeground(TEXT_COLOUR);
		    }
		    
		    public void mouseReleased(MouseEvent e){
		    	btn.setForeground(TEXT_COLOUR);
		    }
		});
		return btn;
	}
}
