/*	File: PileCanvasTest.java
 * 	Author:
 *  Casey Huang		300316284 - based off the BagCanvasTest.
 * 	Linus Go		300345571
 * 
 * 	Date			Author				changes
 *  6th Oct 2016	Linus Go			created Pile Canvas Test.
 */
package missing.tests;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.NonMovable;
import missing.game.items.nonmovable.Pocket;
import missing.game.items.nonmovable.Tree;
import missing.game.world.nodes.WorldTile.Pile;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.ui.canvas.HandCanvas;
import missing.ui.canvas.PileCanvas;

/**
 * This class tests the pile functionality and ensures that it draws it correctly.
 * @author linus
 *
 */
@SuppressWarnings("serial")
public class PileCanvasTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Run the test.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pile testPile = generatePile();
					PileCanvasTest frame = new PileCanvasTest(testPile);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PileCanvasTest(Pile pile) {
		super("Pile of Items");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setSize(442, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		PileCanvas panel = new PileCanvas(pile);
		contentPane.add(panel, BorderLayout.CENTER);
		JButton btnTransferToPlayer = new JButton("Transfer Selection to Player");
		contentPane.add(btnTransferToPlayer, BorderLayout.SOUTH);

	}

	public static Pile generatePile(){
		List<TileObject> pileItems = new ArrayList<>();
		//test that we can put in movable items.
		Movable wood = new Wood(new Point(0, 0), new Point(0,0));
		Movable food = new Food(new Point(0, 0), new Point(0,0), FoodType.APPLE);
		Movable axe  = new Tool(new Point(0, 0), new Point(0,0), Tool.ToolType.AXE, 0);
		Movable shovel = new Tool(new Point(0, 0), new Point(0,0), Tool.ToolType.SHOVEL, 0);
		pileItems.add(wood);
		pileItems.add(food);
		pileItems.add(axe);
		pileItems.add(shovel);
		
		//test that we CANNOT add NonMovable Items - it should throw an exception when trying to render it.
		//NonMovable tree = new Tree(new Point(0,0), new Point(0,0));
		//pileItems.add(tree);
	
		return new Pile(new Point(0,0), new Point(0,0), pileItems);

	}

	
}
