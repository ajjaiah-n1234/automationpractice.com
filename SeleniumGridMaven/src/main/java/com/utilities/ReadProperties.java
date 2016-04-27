/** 
 *This class will read the properties file which contains the data 
 *
 *@author - Sampath & Praveen
 *@since - 07/12/15
 */
package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.customexception.CustomException;

//This class will read properties file
public class ReadProperties {

	CustomException objException = new CustomException();
	Properties prop = new Properties();
	InputStream input = null;

	public Properties getRepository(String propertiesFile) throws IOException {
		try {
			if (System.getProperty("user.dir").contains("src")) {
				input = new FileInputStream(new File(
						System.getProperty("user.dir") + "/main/java/com/utilities/"
								+ propertiesFile));
			} else {
				System.out.println(System.getProperty("user.dir") + "/src/main/java/com/utilities/");
				
				input = new FileInputStream(new File(
						System.getProperty("user.dir") + "/src/main/java/com/utilities/"
								+ propertiesFile));
			}

			// load a properties file
			prop.load(input);

		} catch (Exception e) {
			objException.exceptionHandling(e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					objException.exceptionHandling(e);
				}
			}
		}
		return prop;
	}
}
