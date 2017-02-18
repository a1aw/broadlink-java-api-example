/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2017 Anthony Law
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package com.github.mob41.blapi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {
	
	private static final Logger log = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception{
		log.info("Discovering devices...");
		BLDevice[] devs = BLDevice.discoverDevices(5000);
		log.info("Done");
		
		if (devs == null || devs.length == 0){
			log.info("No devices found. / devs is null");
			return;
		}
		
		log.info("Number of devices: " + devs.length);
		
		for (int i = 0; i < devs.length; i++){
			log.info("Device " + i + ": " + devs[i].getHost() + " / " + devs[i].getMac() + " / " + Integer.toHexString(devs[i].getDeviceType()));
		}
		
		log.info("Getting the first device");
		BLDevice dev = devs[0];
		
		log.info("Authenticating...");
		dev.auth();
		log.info("Done");
		
		if (dev instanceof RM2Device){
			log.info("The device is a RM device");
			RM2Device rm = (RM2Device) dev;
			log.info("Entering learning mode...");
			rm.enterLearning();
			log.info("Sent.");
		} else {
			log.warn("The device is not a RM device. Aborting RM staff");
		}
		
		dev.close();
	}

}
