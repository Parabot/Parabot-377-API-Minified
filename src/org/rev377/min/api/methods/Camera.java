package org.rev377.min.api.methods;

import java.util.Random;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;

public class Camera {
	
	public static void moveRandomly() {
		char dir = new char[] { '%', '\'' }[new Random().nextInt(2)];
		Keyboard.getInstance().pressKey(dir);
		Time.sleep(500, 1000);
		Keyboard.getInstance().releaseKey(dir);
	}

}
