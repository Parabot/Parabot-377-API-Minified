package org.rev377.min.callback;

import org.rev377.min.Loader;
import org.rev377.min.accessors.Client;
import org.rev377.min.api.wrappers.Player;
import org.rev377.min.debug.DActions;

import org.rev377.min.api.methods.Players;

/**
 * 
 * @author Everel
 *
 */
public class MenuAction {
	
	public static void intercept(int index, int arg) {
		if(DActions.debugActions()) {
			Client client = Loader.getClient();
			int action1 = client.getMenuAction1()[index];
			int action2 = client.getMenuAction2()[index];
			int action3 = client.getMenuAction3()[index];
			int actionId = client.getMenuActionId()[index];
			System.out.println(String.format("[index: %d, action1: %d, action2: %d, action3: %d, id: %d]", index, action1, action2, action3, actionId));
		}
	}
	
	// intercepts walking
	public static void intercept(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10) {
		Player p = Players.getMyPlayer();
		System.out.println(p.getLocation().getRegionX() + ", " + p.getLocation().getRegionY());
		System.out.println(paramBoolean1 + ", " + paramBoolean2 + ", " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + ", " + paramInt4 + ", " + paramInt5 + ", " + paramInt6 + ", " + paramInt7 + ", " + paramInt8 + ", " + paramInt9 + ", " + paramInt10);
	}

}
