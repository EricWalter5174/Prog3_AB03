package com.company;

public class Main {

    public static void main(String[] args) {
    	int counter = 0;
		Ringbuffer<Integer> buffer = new Ringbuffer<>(3);
		BlackBox bb = new BlackBox();
		buffer.toggleFixedCapacity();
			while(true){
				buffer.push(bb.getData());
				counter++;
				if(counter%9 == 0){
					buffer.pop();
				}
			}
    }
}
