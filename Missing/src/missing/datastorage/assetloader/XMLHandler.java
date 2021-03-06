/*	File: XMLHandler.java
 * 	Author:
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				Changes
 * 	17 Sep 16		Chris Rabe			created XMLHandler.java
 * 	17 Sep 16		Chris Rabe			can now parse rocks
 * 	17 Sep 16		Chris Rabe			now uses the filename field to parse
 * 	29 Sep 16		Chris Rabe			added parsing for nonmovable and movable
 * 	3 Oct 16		Chris Rabe			moved all loading logic to XMLLoader
 * 	5 Oct 16		Chris Rabe			implemented saving methods
 */

package missing.datastorage.assetloader;

import java.io.File;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import missing.game.Game;
import missing.game.items.Item;
import missing.helper.GameException;

/**
 * This class is responsible for reading and writing XML files.
 */
public class XMLHandler {
	/**The filename */
	public static String filename;
	
	/** 
	 *Returns a list of items from a file.
	 * @return list of items.  
	 */
	public static List<Item> getItemsFromFile() throws GameException {
		if (filename == null) {
			throw new GameException("No file loaded!");
		}
		return XMLImporter.getItemsFromFile(filename);
	}
	/**
	 * Saves the current state of the game, using a specified <code>Game</code> object and <code>filename</code>
	 * filename.
	 * @param game
	 * @param filename
	 * @throws GameException
	 */
	public static void saveGame(Game game, String filename) throws GameException {
		if (filename == null) {
			throw new GameException("Filename must be specified");
		}
		// convert the game to an XML document
		Document doc = XMLExporter.toDocument(game);
		// write the document to a file
		String docName = extractName(filename);
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			File file = new File(docName);
			StreamResult res = new StreamResult(file);
			transformer.transform(source, res);
			System.out.println(String.format("saved to %s", file.getAbsolutePath()));
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes any file extensions which the user may have put on the filename.
	 * 
	 * @param filename
	 * @return
	 */
	private static String extractName(String filename) {
		String[] name = filename.split("[.]");
		String fName = String.format("%s.xml", name[0]);
		return fName;
	}
}
