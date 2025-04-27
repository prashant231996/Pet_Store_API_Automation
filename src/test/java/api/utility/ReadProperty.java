package api.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty {
	
	static Properties prop;
	static ReadProperty readProperty=null;
	
	private ReadProperty() throws IOException
	{
		File file=new File("./src/test/resources/config.properties");
		FileInputStream fis=new FileInputStream(file);
		prop=new Properties();
		prop.load(fis);
	}
	
	public static String getPropertDetails(String key) throws IOException
	{
		if(readProperty==null)
		   readProperty=new ReadProperty();
		return prop.getProperty(key); 
	}

}
