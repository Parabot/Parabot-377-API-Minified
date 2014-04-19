package org.rev377.min.debug;

import java.awt.Graphics;

import org.parabot.core.paint.AbstractDebugger;
import org.rev377.min.api.methods.Bank;
import org.rev377.min.api.wrappers.Item;

public class DBank extends AbstractDebugger {

	@Override
	public void paint(Graphics g) {

	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void toggle() {
		if (!Bank.isOpen())
			return;

		Item[] items = Bank.getBankItems();
		for (int i = Bank.getBankItems().length - 1; i >= 0; i--) {
			System.out.println("ID: " + items[i].getId() + " Stack: "
					+ items[i].getStackSize() + " Slot: " + items[i].getSlot());
		}
	}

}
