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
		Loader.getClient().walkTo(true, false, to.getRegionY(),
				from.getRegionY(), 0, 0, 0, 0, to.getRegionX(), 0, 0,
				from.getRegionX());
	}

	/**
	 * 
	 * @param tilePath
	 * @return <b>true</b> if destination reached, otherwise <b>false</b>
	 */
	public static boolean walkDown(TilePath tilePath) {
		if (tilePath.hasReached()) {
			return true;
		}
		tilePath.traverse();
		return false;
	}

	public static Tile getNearestTileTo(Tile tile) {
		Tile loc = Players.getMyPlayer().getLocation();
		for (int i = 0; i < 1000; ++i) {
			if (tile.distanceTo() < 16 && tile.isWalkable()) {
				return tile;
			}
			tile = new Tile((loc.getX() + tile.getX()) / 2,
					(loc.getY() + tile.getY()) / 2);
		}
		return null;
	}

}
