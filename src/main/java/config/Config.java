package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

  private String driver;
  private String baseURL;
  private String user1;
  private String user2;
  private String password1;
  private String password2;
  private String host1;
  private String host2;
  private String db1;
  private String db2;

  public Config(String configFileName) {
    Properties properties = new Properties();

    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName)) {
      if (inputStream != null) {
        properties.load(inputStream);
        this.driver = properties.getProperty("driver");
        this.baseURL = properties.getProperty("baseURL");
        this.user1 = properties.getProperty("user1");
        this.user2 = properties.getProperty("user2");
        this.password1 = properties.getProperty("password1");
        this.password2 = properties.getProperty("password2");
        this.host1 = properties.getProperty("host1");
        this.host2 = properties.getProperty("host2");
        this.db1 = properties.getProperty("db1");
        this.db2 = properties.getProperty("db2");
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

  public String getUser1() {
    return user1;
  }

  public String getUser2() {
    return user2;
  }

  public String getPassword1() {
    return password1;
  }

  public String getPassword2() {
    return password2;
  }

  public String getHost1() {
    return host1;
  }

  public String getHost2() {
    return host2;
  }

  public String getDb1() {
    return db1;
  }

  public String getDb2() {
    return db2;
  }


}
