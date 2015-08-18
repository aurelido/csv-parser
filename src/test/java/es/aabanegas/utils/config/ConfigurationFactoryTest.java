package es.aabanegas.utils.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;

/**
 * Testea la factoria de variables de configuracion.
 * 
 * @author Aurelio Aragones
 */
public class ConfigurationFactoryTest {
    
    public ConfigurationFactoryTest() {
    }
    

    /**
     * Test of getProperties method, of class ConfigurationFactory.
     */
    @Test
    public void testGetProperties() {
        System.out.println("getProperties");
        Properties result = ConfigurationFactory.getProperties();
        assertNotNull(result);
        //fail("No ha recuperado el fichero properties de configuracion. Comprobar la ruta " + ConfigurationFactory.propertiesFilePath );
    }

    /**
     * Test of getString method, of class ConfigurationFactory.
     */
    @Test
    public void testGetStringDefault() {
    	System.out.println("Testing default String value");
        String configKey = "key";
        String def = "defaultValue";
        String expResult = "defaultValue";
        String result = ConfigurationFactory.getString(configKey, def);
        assertEquals(expResult, result);
        //fail("No ha recuperado el valor de configuracion tipo cadena");
    }

    /**
     * Test of getString method, of class ConfigurationFactory.
     */
    @Test
    public void testGetString() {
    	System.out.println("Testing config String value");
        String configKey = "sort.type";
        String result = ConfigurationFactory.getString(configKey);
        assertNotNull(result);
        //fail("No ha recuperado el valor de configuracion tipo cadena");
    }


    /**
     * Test of getBoolean method, of class ConfigurationFactory.
     */
    @Test
    public void testGetBoolean() {
    	System.out.println("Testing config boolean value");
        String configKey = "sort.desc";
        Boolean result = ConfigurationFactory.getBoolean(configKey);
        assertTrue(result);
        //fail("No ha recuperado el valor de configuracion tipo boolean");
    }

    /**
     * Test of getBoolean method, of class ConfigurationFactory.
     */
    @Test
    public void testGetBooleanDefault() {
        System.out.println("Testing default boolean value");
        String configKey = "true";
        Boolean def = true;
        Boolean result = ConfigurationFactory.getBoolean(configKey, def);
        assertTrue(result);
        //fail("No ha recuperado el valor de configuracion tipo boolean");
    }
    
}