/* File: GUIInitialiser.java
 * Author:			ID
 * Linus Go			300345571
 * Chris Rabe		300334207
 * 
 * Date				Author				Changes
 * 13 Sep 16		Linus Go			created GUIInitialiser.java
 * 13 Sep 16		Linus Go			added initialiseGNodes method.
 * 13 Sep 16		Chris Rabe			implemented intialiseGNodes method
 * 13 Sep 16		Chris Rabe			implemented initialiseGTiles method
 */
package missing.helper;

import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile;
import missing.ui.assets.GWTile;
import missing.game.world.World;
import missing.ui.assets.GWNode;

public class GUIInitialiser {

	public static GWTile[][] initialiseGTiles(WorldNode node, int tileSize) {
		WorldTile[][] tiles = node.getWorldTiles();
		GWTile[][] temp = new GWTile[tiles.length][tiles[0].length];
		int size = tileSize / temp.length;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				temp[i][j] = new GWTile(tiles[i][j], size);
			}
		}
		return temp;
	}

	public static GWNode[][] initialiseGNodes(World world, int nodeSize) {
		WorldNode[][] nodes = world.getWorldNodes();
		GWNode[][] temp = new GWNode[nodes.length][nodes[0].length];
		int size = nodeSize / temp.length;
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				temp[i][j] = new GWNode(nodes[i][j], size);
			}
		}
		return temp;
	}
}