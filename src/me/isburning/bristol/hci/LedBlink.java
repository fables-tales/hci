package me.isburning.bristol.hci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.isburning.loggy.Log;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;

public class LedBlink {

	private static ExecutorService mPool = Executors.newCachedThreadPool();

	public static void blinkLed(final int id, final InterfaceKitPhidget phidg) {
		mPool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					for (int i = 0; i < 3; i++) {
						phidg.setOutputState(id, true);
						Thread.sleep(500);
						phidg.setOutputState(id, false);
						Thread.sleep(500);
					}
				} catch (PhidgetException e) {
					Log.spam("blinkLed", "led crashed");
				} catch (InterruptedException ie) {
					Log.spam("blinkLed", "led crashed");
				}
			}
		});
	}
}
