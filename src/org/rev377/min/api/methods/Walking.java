package org.rev377.min.api.methods;

import org.rev377.min.Loader;
import org.rev377.min.api.wrappers.Tile;
import org.rev377.min.api.wrappers.TilePath;

/**
 * 
 * @author Everel
 *
 */
public class Walking {
	
	public static void walkTo(Tile from, Tile to) {
		// Loader.getClient().walkTo(0, 0, 0, 0, from.getRegionY(), 0, 0, to.getRegionY(), from.getRegionX(), true, to.getRegionX());
		Loader.getClient().walkTo(true, false, from.getRegionY(), to.getRegionY(), 0, 0, 1, 0, from.getRegionX(), 0, 0, to.getRegionX());
	}
	
	/**
	 * 
	 * @param tilePath
	 * @return <b>true</b> if destination reached, otherwise <b>false</b>
	 */
	public static boolean walkDown(TilePath tilePath) {
		if(tilePath.hasReached()) {
			return true;
		}
		tilePath.traverse();
		return false;
	}

}

