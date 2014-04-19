package org.rev377.min.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.rev377.min.api.methods.GroundItems;
import org.rev377.min.api.wrappers.GroundItem;

public class DGroundItems extends AbstractDebugger {

	@Override
	public void paint(Graphics g) {
		
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void toggle() {
		GroundItem[] items = GroundItems.getNearest();
		if(items == null || items.length == 0) {
			return;
		}
		
		for (int i = items.length - 1; i >= 0; i--) {
			System.out.println(
					" ID: " + items[i].getId() + 
					" Location: "+ items[i].getLocation() + 
					" Distance: "+ items[i].distanceTo());
		}
	}

}
