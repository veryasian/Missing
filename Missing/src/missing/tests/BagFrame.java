/*	File: BagFrame.java
 * 	Author:
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				changes
 * 	27 Sep 16		Casey Huang			created copy of BagFrame class for testing purposes.
 */
package missing.tests;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import missing.game.items.Item;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Bush;
import missing.ui.canvas.BagCanvas;

public class BagFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bag bag = new Bag();
					Movable wood = new Wood(new Point(0, 0), new Point(0,0));
					Movable wood2 = new Wood(new Point(0, 0), new Point(0,0));
					Movable food = new Food(new Point(0, 0), new Point(0,0), FoodType.APPLE);
					Movable food2 = new Food(new Point(0, 0), new Point(0,0), FoodType.APPLE);
					Movable food3 = new Food(new Point(0, 0), new Point(0,0), FoodType.APPLE);
					bag.addItem(wood);
					bag.addItem(wood2);
					bag.addItem(food);
					bag.addItem(food2);
					bag.addItem(food3);
					BagFrame frame = new BagFrame(bag);
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
	public BagFrame(Bag bag) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(442, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		BagCanvas panel = new BagCanvas(bag);
		contentPane.add(panel, BorderLayout.CENTER);
	}

}