package nongui;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class BookingRequestPC {
	static BlockingQueue<BookingRequest> bookingRequestQueue = new LinkedBlockingDeque<>();
	
	public static void addToQueue(BookingRequest br) throws InterruptedException {
		bookingRequestQueue.put(br);
		System.out.println(br.getSpaceNum());
	}
	
	public static void producer(BookingRequest br) throws InterruptedException {
		while (true) {
			bookingRequestQueue.put(br);
		}
	}
	
	public static void consumer() throws InterruptedException {
		while (true) {
			Thread.sleep(1000);
			BookingRequest br = bookingRequestQueue.take();
			
			if (!BookingValidator.isParkingSpaceOccupied(br.getSpaceNum())) {
				// System.out.println("Booked space # " + br.getSpaceNum() + ", id: " + BookingValidator.bookSpace(br.getSpaceNum(), br.getCustomerEmail(), br.getnumHours()));
				BookingValidator.bookSpace(br.getSpaceNum(), br.getCustomerEmail(), br.getnumHours(), br.getBookingID());
			}
		}
	}
	
}
