package org.rev377.min.api.methods;

import java.util.ArrayList;

import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.rev377.min.Loader;
import org.rev377.min.api.wrappers.Item;
import org.rev377.min.api.wrappers.Npc;
import org.rev377.min.api.wrappers.SceneObject;

/**
 * 
 * @author Everel
 * 
 */
public class Bank {

	public static final int BANK_PARENT_ID_INTERFACE = 433;
	public static final int BANK_CHILD_ID_INTERFACE = 5;

	public static final int ITEM_INTERFACE = 5382;
	public static final int BUTTON_DEPOSIT_ALL = 5386;

	public static final int INV_PARENT_ID = 69;
	public static final int INV_CHILD_ID = 1;

	public static final int[] BANKERS = new int[] { 44, 45, 494, 495, 498, 499,
			909, 958, 1036, 2271, 2354, 2355, 3824, 5488, 5901, 4456, 4457,
			4458, 4459, 5912, 5913, 6362, 6532, 6533, 6534, 6535, 7605, 8948,
			9710, 14367 };
	public static final int[] BANKS = new int[] { 782, 2213, 2995, 5276, 6084,
			10517, 11402, 11758, 12759, 14367, 19230, 20325, 24914, 25808,
			26972, 29085, 52589, 34752, 35647, 36786, 2012, 2015, 2019, 693,
			4483, 12308, 20607, 21301, 27663, 42192 };

	public static int[] getBankItemIDs() {
		return Loader.getClient().getCache()[BANK_PARENT_ID_INTERFACE][BANK_CHILD_ID_INTERFACE].getItems();
	}

	public static int[] getBankStacks() {
		return Loader.getClient().getCache()[BANK_PARENT_ID_INTERFACE][BANK_CHILD_ID_INTERFACE].getStackSizes();
	}
	/**
	 * Gets nearest banker
	 * 
	 * @return nearest banker
	 */
	public static Npc getBanker() {
		return Npcs.getNearest(new Filter<Npc>() {

			@Override
			public boolean accept(Npc f) {
				for (int id : BANKERS) {
					if (id == f.getDef().getId()) {
						return true;
					}
				}
				return false;
			}

		})[0];
	}

	/**
	 * Gets nearest bank booths
	 * 
	 * @return bank booths
	 */
	public static SceneObject[] getNearestBanks() {
		return SceneObjects.getNearest(BANKS);
	}

	/**
	 * Gets nearest bank booths
	 * 
	 * @return bank booth
	 */
	public static SceneObject getBank() {
		SceneObject[] banks = getNearestBanks();
		if (banks != null && banks[0] != null) {
			return banks[0];
		}
		return null;
	}

	/**
	 * Opens bank using banker or bank booth
	 * 
	 * @return <b>true</b> if successfully interacted
	 */
	public static boolean open() {

		if (isOpen()) {
			return false;
		}
		SceneObject bank = getBank();
		Npc banker = getBanker();

		if (bank != null) {
			bank.interact(1);
			return true;
		} else if (banker != null) {
			banker.interact(1);
			return true;
		}

		return false;
	}

	/**
	 * Deposits all items
	 */
	public static void depositAll() {
		Menu.sendAction(518, 3105, 28, 28377098);
	}

	/**
	 * Withdraws items at bank based on given parameters
	 * 
	 * @param id
	 * @param amount
	 */
	public static void withdraw(int id, int amount, int sleep) {

		if (!isOpen()) {
			return;
		}

		Item b = getItem(id);

		if (b == null) {
			return;
		}

		if (amount == 1) {
			b.transform(6);
		} else if (amount == 10) {
			b.transform(5);
		} else if (amount == 100) {
			b.transform(4);
		} else if (amount == 0) {
			b.transform(3);
		} else {
			b.transform(2);
			Time.sleep(1500 + sleep);
			Keyboard.getInstance().sendKeys("" + amount);
		}
	}

	/**
	 * Gets bank item with given id
	 * 
	 * @param id
	 * @return bank item
	 */
	public static Item getItem(int id) {

		if (!isOpen()) {
			return null;
		}

		for (Item i : Bank.getBankItems()) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Counts the amount of items with given id in bank
	 * 
	 * @param id
	 * @return count
	 */
	public static int getCount(int id) {
		if (!isOpen()) {
			return 0;
		}
		return getItem(id).getStackSize();
	}

	/**
	 * Opens the bank
	 * 
	 * @param bank
	 *            booth
	 */
	public static void open(SceneObject bank) {

		if (isOpen()) {
			return;
		}

		if (bank.getLocation().distanceTo() > 8) {
			bank.getLocation().walkTo();
			return;
		}
		bank.interact(1);
	}

	/**
	 * Closes the bank interface
	 */
	public static void close() {
		if (!isOpen()) {
			return;
		}
		// [index: 1, action1: -1, action2: -1, action3: 5384, id: 200]
		Menu.sendAction(200, -1, -1, 5384);
	}

	/**
	 * Deposits all items except the given ids
	 * 
	 * @param exceptions
	 *            the item indexes that will be ignored.
	 */
	public static void depositAllExcept(int... exceptions) {
		if (Bank.isOpen()) {
			final ArrayList<Integer> ignored = new ArrayList<Integer>();
			for (int i : exceptions) {
				ignored.add(i);
			}
			for (Item i : Inventory.getItems()) {
				if (!ignored.contains(i.getId())) {
					while (Bank.isOpen() && Inventory.getCount(i.getId()) > 0) {
						i.transform(3, 7471105);
						ignored.add(i.getId());
						Time.sleep(50);
					}
				}
			}
		}
	}

	/**
	 * Gets all bank items in bank
	 * 
	 * @return bank items
	 */
	public static Item[] getBankItems() {
		if (!isOpen()) {
			return null;
		}
		ArrayList<Item> items = new ArrayList<Item>();
		int[] ids = getBankItemIDs();
		int[] stacks = getBankStacks();
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] > 0) {
				items.add(new Item(ids[i], stacks[i], i));
			}
		}
		return (Item[]) items.toArray(new Item[items.size()]);
	}

	/**
	 * Counts total amount of items in bank
	 * 
	 * @return total amount of items
	 */
	public static int getBankCount() {
		if (!isOpen()) {
			return 0;
		}
		return getBankItemIDs().length;
	}

	/**
	 * Determines if bank is open
	 * 
	 * @return <b>true</b> if bank is open
	 */
	public static boolean isOpen() {
		return Loader.getClient().getOpenInterfaceId() == BANK_PARENT_ID_INTERFACE;
	}

}
