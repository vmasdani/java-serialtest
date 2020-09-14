package com.ioseries.statictest;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.ioseries.statictest.model.SerialData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class StatictestApplication {
	public static void main(String[] args) {
		SpringApplication.run(StatictestApplication.class, args);
		runSerial();
	}

	public static void runSerial() {
		SerialPort comPorts[] = SerialPort.getCommPorts();
		SerialPort comPort = null;

		for (SerialPort port : comPorts) {
			System.out.println(port.getDescriptivePortName());
			comPort = port;
		}

		if (comPort != null) {
			comPort.openPort();

			SerialPort finalComPort = comPort;

			comPort.addDataListener(new SerialPortDataListener() {
				@Override
				public int getListeningEvents() {
					return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
				}


				@Override
				public void serialEvent(SerialPortEvent event) {
//					if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
//						return;
//
//					System.out.println("Read bytes!");
//					System.out.println(event.getReceivedData());
//					System.out.println(event.getEventType());

					byte[] newData = new byte[finalComPort.bytesAvailable()];
					int numRead = finalComPort.readBytes(newData, newData.length);
					 
					for (byte data : newData) {
						System.out.println((char)data);
					}
				}
			});

			comPort.writeBytes("Helloworld!\n".getBytes(), "Helloworld!\n".length());

//			comPort.closePort();
		}
	}

	@RestController
	class Test {
		@Autowired
		ApplicationContext ctx;

		@GetMapping("/through-main")
		SerialData throughMain() {
			SerialData serialData = ctx.getBean("serialData", SerialData.class);
			return serialData;
		}
	}
}
