package org.rev377.min.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.rev377.min.api.methods.Npcs;
import org.rev377.min.api.wrappers.Npc;

public class DNpcs extends AbstractDebugger {

	@Override
	public void paint(Graphics g) {
		
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void toggle() {
		for(Npc n : Npcs.getNearest()) {
			System.out.println("ID: " + n.getDef().getId() + " Distance: " + n.distanceTo() + " Location: " + n.getLocation().toString());
		}
		
		Npc[] npc = Npcs.getNearest();
		if(npc == null || npc.length == 0) 
			return;

		for (int i = npc.length - 1; i >= 0; i--) {
			System.out.println(
					" ID: " + npc[i].getDef().getId() + 
					" Index: " + npc[i].getIndex() + 
					" Location: "+ npc[i].getLocation() + 
					" Distance: "+ npc[i].distanceTo());
		}
		
	}

}
