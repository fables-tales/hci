package me.isburning.bristol.hci;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.openqa.selenium.firefox.FirefoxDriver;

import me.isburning.loggy.Log;

import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;
import com.phidgets.event.TagGainEvent;
import com.phidgets.event.TagGainListener;
import com.phidgets.event.TagLossEvent;
import com.phidgets.event.TagLossListener;

public class RFIDProvider {
	private Tag mTag;
	private RFIDPhidget mPhidget;
	private FirefoxDriver mFd;

	public RFIDProvider(RFIDPhidget phidget) throws PhidgetException {
		mPhidget = phidget;
		mPhidget.addTagGainListener(new TagGainListener() {

			@Override
			public void tagGained(TagGainEvent arg0) {
				RFIDProvider.this.findTag(arg0);
			}
		});

		mPhidget.addTagLossListener(new TagLossListener() {

			@Override
			public void tagLost(TagLossEvent arg0) {
				RFIDProvider.this.loseTag(arg0);
			}
		});
		Log.spam("status", "opening phidget");
		mPhidget.openAny();
		Log.spam("status", "waiting for attachment");
		mPhidget.waitForAttachment(1000);
		Log.spam("status", "got attachment");
	}

	private synchronized void loseTag(TagLossEvent arg0) {
		Log.debug("tag loss", "tag null?: " + (mTag != null));
		Log.debug("tag loss", "tag id: " + (mTag.getID()));
		Log.debug("tag loss", "tag lost id: " + (arg0.getValue()));

		if (mTag != null && mTag.getID().equals(arg0.getValue())) {
			String id = mTag.getID();
			mTag = null;
			Log.wtf("tag current", "current tag is:" + mTag);
			for (int i = 0; i < RFIDTest.tags.length; i++) {
				if (RFIDTest.tags[i].equals(id)) {
					try {
						/*Log.debug("tag loss", "killing firefox");
						mFd.quit();
						Log.debug("tag loss", "killing firefox succeeded");*/
					} catch (Throwable t) {

					}

				}

			}

		}

	}

	private synchronized void findTag(TagGainEvent arg0) {
		Log.debug("tag find", "found id:" + arg0.getValue());
		if (mTag != null)
			Log.debug("tag find", "current-tag" + mTag.getID());

		if (mTag == null) {
			mTag = new Tag(arg0.getValue());
			Log.wtf("tag current", "current tag is:" + mTag.getID());
			for (int i = 0; i < RFIDTest.tags.length; i++) {
				if (RFIDTest.tags[i].equals(mTag.getID())) {
					if (mFd == null) mFd = new FirefoxDriver();
					Log.spam("broser", "loading: " + RFIDTest.urls[i]);
					mFd.navigate().to(RFIDTest.urls[i]);
					
				}
			}
		}
		
	}

	public Tag getTag() {
		return mTag;
	}
}
