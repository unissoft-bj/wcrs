package net.wyun.wcrs.wechat.jssdk;

public class JSTicket {
	
	public JSTicket(String ticket, long timeStamp) {
		super();
		this.ticket = ticket;
		this.timeStamp = timeStamp;
	}
	
	String ticket;
	long timeStamp;
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
