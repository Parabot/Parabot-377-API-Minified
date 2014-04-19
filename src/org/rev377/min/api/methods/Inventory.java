package org.rev377.min.api.methods;

import java.util.ArrayList;

import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.rev377.min.Loader;
import org.rev377.min.api.wrappers.Item;
import org.rev377.min.api.wrappers.Interface;

/**
 * 
 * @author Everel
 * 
 */
public class Inventory {
	public static final int PARENT_INDEX = 69;
	public static final int CHILD_INDEX = 1;
	
	public static Item[] getItems() {
		return getItems(new Filter<Item>() {

			@Override
			public boolean accept(Item i) {
				return true;
			}
			
		});
	}

	public static int[] getInventoryItems() {
		org.rev377.min.accessors.Interface inv = Loader.getClient().getCache()[PARENT_INDEX][CHILD_INDEX];

		if (inv == null)
			return null;

		return inv.getItems();
	}

	public static int[] getInventoryStacks() {
		org.rev377.min.accessors.Interface inv = Loader.getClient().getCache()[PARENT_INDEX][CHILD_INDEX];

		if (inv == null)
			return null;

		return inv.getStackSizes();
	}

	/**
	 * Clears the inventory
	 */
	public static void clear() {
		for (Item item : Inventory.getItems()) {
			item.drop();
			Time.sleep(60, 80);
		}
	}

	/**
	 * Gets the amount of items in inventory, excludes the stack sizes
	 * 
	 * @return amount of items
	 */
	public static final int getCount() {
		return getCount(false);
	}

	/**
	 * Gets the amount of items with given ids in inventory, excludes the stack
	 * sizes
	 * 
	 * @param ids
	 * @return amount of items
	 */
	public static final int getCount(int... ids) {
		return getCount(false, ids);
	}

	/**
	 * Gets the amount of items in inventory
	 * 
	 * @param includeStack
	 *            - true for including stack sizes to the counting
	 * @return amount of items
	 */
	public static final int getCount(final boolean includeStack) {
		final Interface inventory = getInterface();
		if (inventory == null) {
			return -1;
		}
		int count = 0;
		final int[] items = inventory.getItems();
		final int[] stackSizes = includeStack ? inventory.getStackSizes()
				: null;
		for (int i = 0; i < items.length; i++) {
			if (items[i] > 0) {
				count += includeStack ? stackSizes[i] : 1;
			}
		}
		return count;
	}

	private static Interface getInterface() {
		org.rev377.min.accessors.Interface inventoryInterface = Loader.getClient().getCache()[PARENT_INDEX][CHILD_INDEX];
		Interface invInterface = new Interface(inventoryInterface);

		return invInterface;
	}

	/**
	 * Gets the amount of items with given ids in inventory
	 * 
	 * @param includeStack
	 *            - true for including stack sizes to the counting
	 * @param ids
	 * @return amount of items
	 */
	public static final int getCount(final boolean includeStack, int... ids) {
		final Interface inventory = getInterface();
		if (inventory == null) {
			return -1;
		}
		int count = 0;
		final int[] items = inventory.getItems();
		final int[] stackSizes = includeStack ? inventory.getStackSizes()
				: null;
		for (int i = 0; i < items.length; i++) {
			final int itemId = items[i];
			if (itemId > 0) {
				for (final int id : ids) {
					if (id == itemId) {
						count += includeStack ? stackSizes[i] : 1;
						break;
					}
				}
			}
		}
		return count;
	}

	/**
	 * Gets all items with given ids
	 * 
	 * @param ids
	 * @return items
	 */
	public static final Item[] getItems(final int... ids) {
		return getItems(new Filter<Item>() {

			@Override
			public boolean accept(Item e) {
				for (int id : ids) {
					if (e.getId() == id) {
						return true;
					}
				}
				return false;
			}

		});
	}

	/**
	 * Gets all items accepted by filter
	 * 
	 * @param filter
	 * @return items
	 */
	public static final Item[] getItems(final Filter<Item> filter) {
		final Interface inventory = getInterface();
		if (inventory == null) {
			return null;
		}
		final int[] items = inventory.getItems();
		final int[] stackSizes = inventory.getStackSizes();
		final ArrayList<Item> invItems = new ArrayList<Item>(28);
		for (int i = 0; i < items.length; i++) {
			final int itemId = items[i];
			if (itemId < 1) {
				continue;
			}
			final int stackSize = stackSizes[i];
			final Item item = new Item(itemId, stackSize, i);
			if (filter != null && filter.accept(item)) {
				invItems.add(item);
			}
		}
		return invItems.toArray(new Item[invItems.size()]);
	}

	/**
	 * Determines if inventory is full
	 * 
	 * @return <b>true</b> if inventory is full, otherwise <b>false</b>
	 */
	public static final boolean isFull() {
		return Inventory.getCount() == 28;
	}

	/**
	 * Determines if inventory is empty
	 * 
	 * @return <b>true</b> if inventory is empty, otherwise <b>false</b>
	 */
	public static final boolean isEmpty() {
		return Inventory.getCount() == 0;
	}

}
