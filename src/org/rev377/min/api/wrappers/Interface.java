package org.rev377.min.api.wrappers;


public class Interface {
	private org.rev377.min.accessors.Interface accessor;

	public Interface(org.rev377.min.accessors.Interface accessor) {
		this.accessor = accessor;
	}

	public Interface(org.rev377.min.accessors.Interface[] parentInterfaces, int child) {
		this.accessor = parentInterfaces[child];
	}
	
	public int[] getItems() {
		return getAccessor().getItems();
	}

	public int[] getStackSizes() {
		return getAccessor().getStackSizes();
	}
	
	public org.rev377.min.accessors.Interface getAccessor() {
		return accessor;
	}

}
