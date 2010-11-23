package me.isburning.bristol.hci;

import java.io.IOException;

import javax.swing.SwingUtilities;

import me.isburning.loggy.Log;
import me.isburning.loggy.LogLevel;

import com.phidgets.Phidget;
import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.OutputChangeEvent;
import com.phidgets.event.OutputChangeListener;
import com.phidgets.event.TagGainEvent;
import com.phidgets.event.TagGainListener;
import com.phidgets.event.TagLossEvent;
import com.phidgets.event.TagLossListener;

public class RFIDTest {
	public static String[] tags = new String[]{"01068de1cd","01068dee5d","010775960d"};
	public static String[] urls = new String[]{"http://google.com", "http://xkcd.com", "http://news.bbc.co.uk"};
	
	public static void main(String[] args) throws PhidgetException, IOException {
		//setup logging
		Log.setLogLevel(LogLevel.SPAM);
		Log.wtf("test", "running");
		Log.info("phidgets", Phidget.getLibraryVersion());
		RFIDPhidget phidg = new RFIDPhidget();
		final RFIDProvider prov = new RFIDProvider(phidg);
		phidg.setLEDOn(true);
		phidg.setAntennaOn(true);
		Log.debug("provider", "provider setup");
		System.in.read();
	}
}
