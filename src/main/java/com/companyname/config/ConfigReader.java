package com.companyname.config;

import java.io.FileInputStream;
import java.util.Properties;

import com.companyname.constants.FrameworkConstants;
import com.companyname.exceptions.FrameworkException;

public final class ConfigReader {
	
	private ConfigReader() {}
	
	private static final Properties PROP =new Properties();
	
	static {
		try(FileInputStream fis =new FileInputStream(FrameworkConstants.getConfigFilePath())) {
			PROP.load(fis);
		} catch (Exception e) {
			throw new FrameworkException("Failed to load config file: ",e);
		}
	}
	
	public static String get(String key) {
        return PROP.getProperty(key);
    }
	

}
