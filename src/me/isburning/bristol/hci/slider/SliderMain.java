package me.isburning.bristol.hci.slider;

import org.openqa.selenium.firefox.FirefoxDriver;

import me.isburning.loggy.Log;
import me.isburning.loggy.LogLevel;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.Phidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.InputChangeEvent;
import com.phidgets.event.InputChangeListener;

public class SliderMain {

	public static final int MAX_SLIDE = 4096;

	public static final String[] urls = new String[] { "http://google.com", "http://xkcd.com",
			"http://news.bbc.co.uk", "http://arstechnica.com" };

	public static void main(String args[]) throws PhidgetException {
		Log.setLogLevel(LogLevel.SPAM);
		final InterfaceKitPhidget phidg = new InterfaceKitPhidget();
		
		phidg.openAny();
		phidg.waitForAttachment(1000);
		Log.debug("number of sensors", "" + phidg.getSensorCount());
		Log.debug("interface", "got phidget");

		FirefoxDriver fd = null;
		String currUrl = null;
		while (true) {
			int i = 7;
			Log.debug("sensor " + i, "" + phidg.getSensorRawValue(i));
			int val = phidg.getSensorRawValue(i);
			int partition = (val * urls.length) / MAX_SLIDE;
			String newUrl = urls[partition];
			if (fd == null)
				fd = new FirefoxDriver();
			if (currUrl == null || !currUrl.equals(newUrl)) {
				Log.debug("url loading", newUrl);
				fd.navigate().to(newUrl);
				currUrl = newUrl;
			}
			
		}

	}
	
}
