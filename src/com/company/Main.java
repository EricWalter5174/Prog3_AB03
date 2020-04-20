package com.company;
/*
* Autor: Eric Walter
* Prog3 AB03
* Hochschule Osnabrueck
* */
public class Main {

	public static void main(String[] args) {
		//buffer.toggleFixedCapacity();
		try {
			var blackbox = new Ringbuffer<FlightInfo>(3);
			//blackbox.toggleFixedCapacity();
			//blackbox.setFifo();
			blackbox.push(FlightInfo.Random());
			blackbox.push(FlightInfo.Random());
			System.out.println(blackbox.pop());
			System.out.println(blackbox.pop());

			blackbox.push(FlightInfo.Random());
			System.out.println(blackbox.pop());

			blackbox.push(FlightInfo.Random());
			System.out.println(blackbox.pop());
			blackbox.push(FlightInfo.Random());
			blackbox.push(FlightInfo.Random());
			blackbox.push(FlightInfo.Random());
			blackbox.push(FlightInfo.Random());
			System.out.println(blackbox.pop());
			System.out.println(blackbox.pop());
			System.out.println(blackbox.pop());

			blackbox.push(FlightInfo.Random());
		}
		catch (IllegalStateException exception) {
			System.out.println("Error");
		}
	}
}