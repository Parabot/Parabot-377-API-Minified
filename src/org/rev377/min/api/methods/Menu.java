package org.rev377.min.api.methods;

import java.util.HashMap;

import org.parabot.core.Context;
import org.rev377.min.Loader;
import org.rev377.min.accessors.Client;
import org.rev377.min.api.wrappers.Character;
import org.rev377.min.api.wrappers.GroundItem;
import org.rev377.min.api.wrappers.Item;
import org.rev377.min.api.wrappers.SceneObject;

/**
 * 
 * @author Everel
 * 
 */
public class Menu {
	public static final int ACTION_CLICK_BUTTON = 518;
	public static final int ACTION_DROP_ITEM = 891;
	public static final int ACTION_TAKE_ITEM = 684;

	private static HashMap<String, String> constants;

	/**
	 * Interacts with a sceneobject
	 * 
	 * @param object
	 * @param actionIndex
	 */
	public static void interact(SceneObject object, int actionIndex) {
		int actionId = 35;
		switch (actionIndex) {
		case 1:
			actionId = 389;
			break;
		case 2:
			actionId = 888;
			break;
		case 3:
			actionId = 892;
			break;
		case 4:
			actionId = 1280;
			break;
		}
		sendAction(actionId, object.getHash(), 0, object.getId());
	}

	/**
	 * Interacts with a character
	 * 
	 * @param character
	 * @param actionIndex
	 */
	public static void interact(Character character, int actionIndex) {
		int actionId = 318;
		switch (actionIndex) {
		case 1:
			actionId = 921;
			break;
		case 2:
			actionId = 118;
			break;
		case 3:
			actionId = 553;
			break;
		case 4:
			actionId = 432;
			break;
		}
		sendAction(actionId, character.getIndex(), 0, 0);
	}

	public static void interactItem(Item item, int actionIndex) {
		int actionId = 0;
		switch (actionIndex) {
		case 1:
			actionId = 399; // Wield, Equip, Wear
			break;
		case 2:
			actionId = 961; // Eat, Drink
			break;
		case 3:
			actionId = 52; // Use
			break;
		case 4:
			actionId = 891; // Drop
			break;
		case 5:
			actionId = 324; // Bind
			break;
		case 6:
			actionId = 1094; // Examine
			break;
		case 7:
			actionId = 227; // Empty
			break;
		}
		sendAction(actionId, (int) item.getId() - 1, item.getSlot(), 7471105);
	}

	/**
	 * Interacts with an item when it has the following menu Transform-1
	 * Transform-5 Transform-10 etc..
	 * 
	 * @param item
	 * @param actionIndex
	 * @param interfaceParentId
	 */
	public static void transformItem(Item item, int actionIndex,
			int interfaceParentId) {
		int actionId = 1094;
		switch (actionIndex) {
		case 1:
			actionId = 1094;
			break;
		case 2:
			actionId = 891;
			break;
		case 3:
			actionId = 52;
			break;
		case 4:
			actionId = 961;
			break;
		}
		sendAction(actionId, (int) item.getId() - 1, item.getSlot(), 7471105);
	}

	/**
	 * Interacts with an item when it has the following menu Transform-1
	 * Transform-5 Transform-10 etc..
	 * 
	 * @param item
	 * @param actionIndex
	 * @param interfaceParentId
	 */
	public static void transformBankItem(Item item, int actionIndex) {
		int actionId = 9;
		switch (actionIndex) {
		case 1:
			actionId = 225;
			break;
		case 2:
			actionId = 444;
			break;
		case 3:
			actionId = 564;
			break;
		case 4:
			actionId = 894;
			break;
		}
		sendAction(actionId, (int) item.getId() - 1, item.getSlot(), 28377093);
	}

	/**
	 * Takes grounditem from the ground
	 * 
	 * @param item
	 */
	public static void take(GroundItem item) {
		sendAction(ACTION_TAKE_ITEM, item.getId(), item.getX(), item.getY());
	}

	/**
	 * Interacts with a ground item
	 * 
	 * @param item
	 * @param action
	 */
	public static void interact(GroundItem item, int action) {
		int actionId = 684;
		switch (action) {
		case 1:
			actionId = 1564;
			break;
		case 3:
			actionId = 684;
			break;
		}
		sendAction(actionId, item.getId(), item.getX(), item.getY());
	}

	/**
	 * Drops an item
	 * 
	 * @param item
	 */
	public static void drop(Item item) {
		sendAction(ACTION_DROP_ITEM, (int) item.getId() - 1, item.getSlot(),
				Inventory.PARENT_INDEX);
	}

	/**
	 * Clicks a button
	 * 
	 * @param id
	 */
	public static void clickButton(int id) {
		sendAction(ACTION_CLICK_BUTTON, 0, 0, id);
	}

	/**
	 * Sends an action to the client
	 * 
	 * @param action
	 * @param cmd1
	 * @param cmd2
	 * @param cmd3
	 */
	public static void sendAction(int action, int cmd1, int cmd2, int cmd3) {
		sendAction(action, cmd1, cmd2, cmd3, 0);
	}

	/**
	 * Sends an action to the client
	 * 
	 * @param action
	 * @param cmd1
	 * @param cmd2
	 * @param cmd3
	 * @param cmd4
	 */
	public static void sendAction(int action, int cmd1, int cmd2, int cmd3,
			int cmd4) {
		if (constants == null) {
			constants = Context.getInstance().getHookParser().getConstants();
		}

		int index = 3;
		Client client = Loader.getClient();

		client.getMenuAction1()[index] = cmd1;
		client.getMenuAction2()[index] = cmd2;
		client.getMenuAction3()[index] = cmd3;
		client.getMenuActionId()[index] = action;

		client.doAction(index, 8);
	}

}
