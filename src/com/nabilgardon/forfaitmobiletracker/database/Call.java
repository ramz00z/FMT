package com.nabilgardon.forfaitmobiletracker.database;

public class Call 
{
	private long id;
	private long callLength;
	private long dateCall;
	private String callNum;
	private String callerId;
	private long forfaitID;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getCallLength() {
		return callLength;
	}
	
	public void setCallLength(long callLength) {
		this.callLength = callLength;
	}
	
	public long getDateCall() {
		return dateCall;
	}
	
	public void setDateCall(long dateCall) {
		this.dateCall = dateCall;
	}
	
	public String getCallNum() {
		return callNum;
	}
	
	public void setCallNum(String callNum) {
		this.callNum = callNum;
	}
	
	public String getCallerId() {
		return callerId;
	}
	
	public void setCallerId(String callerId) {
		this.callerId = callerId;
	}
	
	public long getForfaitID() {
		return forfaitID;
	}
	
	public void setForfaitID(long forfaitID) {
		this.forfaitID = forfaitID;
	}

	@Override
	public String toString()
	{
		return callNum+ " de "+callLength+ " secondes";
	}
}
