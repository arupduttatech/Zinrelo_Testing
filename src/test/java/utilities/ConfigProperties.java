package utilities;

import java.util.Properties;
 
public class ConfigProperties
{
	Properties properties;
	
	public String getProperty(String keyName) {
		
		String keyValue = this.properties.getProperty(keyName);
		return keyValue;
	}

	public ConfigProperties() {
		
		properties = new java.util.Properties();
		
		try {
						properties.load(this.getClass().getResourceAsStream("/zinrelo.properties"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}