package me.isburning.bristol.hci;

public class Tag {

	private String mTagId;
	
	public Tag(String value) {
		mTagId = value;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Tag && ((Tag)o).mTagId == mTagId;
	}

	public String getID() {
		return mTagId;
	}

}
