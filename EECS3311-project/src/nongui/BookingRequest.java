package nongui;

public class BookingRequest {
	
	private int spaceNum;
	private String customerEmail;
	private int numHours;
	private String bookingID;
	public BookingRequest(int spaceNum, int numHours, String customerEmail, String bookingID) {
		this.spaceNum = spaceNum;
		this.numHours = numHours;
		this.customerEmail = customerEmail;
		this.bookingID = bookingID;
	}
	
	public int getSpaceNum() {
		return this.spaceNum;
	}
	
	public int getnumHours() {
		return this.numHours;
	}
	
	public String getCustomerEmail() {
		return this.customerEmail;
	}
	
	public String getBookingID() {
		return this.bookingID;
	}
	
	public void setBookingID(String id) {
		this.bookingID = id;
	}
}
