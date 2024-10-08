package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

  private String driver;
  private String baseURL;

  public Config() {
    Properties properties = new Properties();
    String configFileName = "config/mysql.properties";

    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName)) {
      if (inputStream != null) {
        properties.load(inputStream);
        this.driver = properties.getProperty("driver");
        this.baseURL = properties.getProperty("baseURL");
      } else {
        throw new RuntimeException("Archivo de configuración no encontrado: " + configFileName);
      }
    } catch (IOException e) {
      throw new RuntimeException("Error al leer el archivo de configuración", e);
    }
  }

  public String getDriver() {
    return driver;
  }

  public String getBaseURL() {
    return baseURL;
  }

}
