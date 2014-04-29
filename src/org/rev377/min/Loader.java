package org.rev377.min;

import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JMenuBar;

import org.parabot.core.Context;
import org.parabot.core.asm.ASMClassLoader;
import org.parabot.core.asm.adapters.AddInterfaceAdapter;
import org.parabot.core.asm.hooks.HookFile;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.servers.ServerManifest;
import org.parabot.environment.servers.ServerProvider;
import org.parabot.environment.servers.Type;
import org.rev377.min.accessors.Client;
import org.rev377.min.script.ScriptEngine;
import org.rev377.min.ui.BotMenu;

/**
 * 
 * @author Everel
 * 
 */
@ServerManifest(author = "Everel & SingleCore", name = "Battlescape", type = Type.INJECTION, version = 0.2)
public class Loader extends ServerProvider {

	private Applet applet;

	@Override
	public Applet fetchApplet() {
		try {
			final Context context = Context.getInstance();
			final ASMClassLoader classLoader = context.getASMClassLoader();
			final Class<?> clientClass = classLoader.loadClass("client");
			Object instance = clientClass.newInstance();
			applet = (Applet) instance;
			applet.setStub(new Stub());
			return applet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public URL getJar() {
		try {
			return new URL("http://bot.parabot.org/servers/battlescape.jar");
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	public static Client getClient() {
		return (Client) Context.getInstance().getClient();
	}

	@Override
	public void addMenuItems(JMenuBar bar) {
		new BotMenu(bar);
	}

	@Override
	public void injectHooks() {
		AddInterfaceAdapter.setAccessorPackage("org/rev377/min/accessors/");
		// default injection is done by bot, it basically parses the hooks file
		super.injectHooks();
	}

	@Override
	public void initScript(Script script) {
		ScriptEngine.getInstance().setScript(script);
		ScriptEngine.getInstance().init();
	}

	@Override
	public HookFile getHookFile() {

		try {
			return new HookFile(new URL("http://bot.parabot.org/hooks/bs/bs_hooks.xml"), HookFile.TYPE_XML);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void unloadScript(Script script) {
		ScriptEngine.getInstance().unload();
	}

}
