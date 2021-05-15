package gui;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import nongui.BookingRequest;
import nongui.BookingRequestPC;
import nongui.BookingValidator;


public class Main {
	// static BlockingQueue<BookingRequest> bookingRequestQueue = new LinkedBlockingDeque<BookingRequest>();
	// static BlockingQueue<BookingRequest> activeBookingsQueue = new LinkedBlockingDeque<>();
	
	public static void main(String[] args) throws InterruptedException {
		StartWindow sr = new StartWindow();
		sr.setVisible(true);
		sr.setResizable(false);
		
		/*
		Thread t = new Thread(new Runnable() {
			public void run() {
				BookingRequest br1 = new BookingRequest(155, 3, "skulledmage@gmail.com");
				try {
					producer(br1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				BookingRequest br1 = new BookingRequest(156, 3, "skulledmage@gmail.com");
				try {
					producer(br1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		*/
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					BookingRequestPC.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t.start();
		
	}

}
