package org.rev377.min;

import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

public class Stub implements AppletStub {

	@SuppressWarnings("rawtypes")
	private Hashtable collection = new Hashtable();

	@SuppressWarnings("unchecked")
	public Stub() {
		collection.put("worldid", "1");
		collection.put("portoff", "0");
		collection.put("lowmem", "0");
		collection.put("free", "0");
		collection.put("version", "474");
	}

	public String getParameter(String paramString) {
		return (String) collection.get(paramString);
	}

	public URL getDocumentBase() {
		return getCodeBase();
	}

	public URL getCodeBase() {
		try {
			return new URL("http://www.battle-scape.com");
		} catch (MalformedURLException localMalformedURLException) {
		}
		throw new RuntimeException();
	}

	public boolean isActive() {
		return true;
	}

	@Override
	public AppletContext getAppletContext() {
		return null;
	}

	@Override
	public void appletResize(int arg0, int arg1) {

	}

}
