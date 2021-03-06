/*	File: PileFrame.java
 * 	Author:
 *  Casey Huang		300316284 - based off the BagCanvasTest.
 * 	Linus Go		300345571
 * 
 * 	Date			Author				changes
 *  6th Oct 2016	Linus Go			created Pile Frame.
 */
package missing.ui.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import missing.datastorage.assetloader.GameAssets;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Container;
import missing.game.items.nonmovable.NonMovable;
import missing.game.items.nonmovable.Pocket;
import missing.game.items.nonmovable.Tree;
import missing.game.world.nodes.WorldTile.Pile;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.ui.canvas.ContainerCanvas;
import missing.ui.canvas.HandPanel;
import missing.ui.canvas.PileCanvas;
import javax.swing.JButton;

/**
 * This class should be called if you want to create a window that will render the contents of a Pile object.
 *
 */
@SuppressWarnings("serial")
public class ContainerFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnTransferToPlayer;
	private BufferedImage backgroundImage = GameAssets.getWindowBackgroundImage();
	/**
	 * Create a window that renders the Piles to be drawn.
	 * @param Pile - pile to be rendered.
	 */
	public ContainerFrame(Container container) {
		super("Pile of Items");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		ContainerCanvas panel = new ContainerCanvas(container);
		contentPane.add(panel, BorderLayout.CENTER);
		btnTransferToPlayer = new JButton("Transfer Selection to Player");
		contentPane.add(btnTransferToPlayer, BorderLayout.SOUTH);
		addActionListeners();
	}
	
	private void addActionListeners(){
		btnTransferToPlayer.addActionListener(e->{
			//TODO: need to define logic to transfer the selected items to a player.
		});
	}
	
	
	
	
}
