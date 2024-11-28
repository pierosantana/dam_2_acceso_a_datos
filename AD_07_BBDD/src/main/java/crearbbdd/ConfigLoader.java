package crearbbdd;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

	private Properties properties;

	public ConfigLoader() {

		properties = new Properties();

		try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				System.out.println("Archivo de configuración no encontrado");
				return;
			}
			properties.load(input);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}