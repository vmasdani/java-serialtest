package com.ioseries.statictest.controller;

import com.fazecast.jSerialComm.SerialPort;
import com.ioseries.statictest.model.SerialData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
public class MainController {
    @Autowired
    ApplicationContext ctx;

    @GetMapping("/serial")
    SerialData get() {
        SerialData serialData = ctx.getBean("serialData", SerialData.class);

        return serialData;
    }

    @GetMapping("/ports")
    Iterable<String> getPorts() {
        SerialPort[] ports = SerialPort.getCommPorts();
        ArrayList<String> portsArray = new ArrayList();

        for (SerialPort port : ports) {
            portsArray.add(String.format("%s: %s", port.getSystemPortName(), port.getDescriptivePortName()));
        }

        return portsArray;
    }

    @GetMapping("/serial/put")
    SerialData put() {
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        SerialData serialData = ctx.getBean("serialData", SerialData.class);
        serialData.setData("Hello world. This is a data from serial.");

        return serialData;
    }
}