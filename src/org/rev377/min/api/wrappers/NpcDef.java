package org.rev377.min.api.wrappers;

/**
 * 
 * @author Everel
 *
 */
public class NpcDef {
	private org.rev377.min.accessors.NpcDef accessor;
	
	public NpcDef(org.rev377.min.accessors.NpcDef accessor) {
		this.accessor = accessor;
	}
	
	/**
	 * Gets id of this item
	 * @return id of this item
	 */
	public int getId() {
		if(accessor == null || accessor.getId() == -1)
			return 0;
		
		return accessor.getId();
	}

}

