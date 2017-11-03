/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventa001;

/**
 *
 * @author Omar
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
 
/**
 *
 * @author v3rgu1
 */
public class Configuration {
 
    Properties properties = null;
 
    /** Configuration file name */
    public final static String CONFIG_FILE_NAME = "properties.config";
 
    /** Data base server */
    public final static String DATABASE_SERVER = "dataBaseServer";
 
    /** Data base name */
    public final static String DATABASE_CATALOG = "dataBaseCatalog";
 
    /** Data base user */
    public final static String DATABASE_USER = "dataBaseUser";
 
    /** Data base password */
    public final static String DATABASE_PSWD = "dataBasePassword";
 
    private Configuration() {
        this.properties = new Properties();
        try {
            properties.load(Configuration.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//Configuration
 
    /**
     * Implementando Singleton
     *
     * @return
     */
    public static Configuration getInstance() {
        return ConfigurationHolder.INSTANCE;
    }
 
    private static class ConfigurationHolder {
 
        private static final Configuration INSTANCE = new Configuration();
    }
 
    /**
     * Retorna la propiedad de configuración solicitada
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }//getProperty
}