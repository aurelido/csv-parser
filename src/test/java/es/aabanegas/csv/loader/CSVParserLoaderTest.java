package es.aabanegas.csv.loader;

import static org.junit.Assert.assertEquals;

import es.aabanegas.csv.loader.CSVParserLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.mockito.exceptions.base.MockitoAssertionError;

/**
 * Testea la factoria de variables de configuracion.
 * 
 * @author Aurelio Aragones
 */
public class CSVParserLoaderTest {
    

    public CSVParserLoaderTest() {
    }


    /**
     * Test of getAllDefaultSorted method, of class CSVParserLoader.
     * TC: 	sort.field=0
     * 		sort.type=string
     * 		sort.desc=false
     */
    @Test
    public void testGetRecordsByFirstField() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("HSBC Canada,5601-2345-3446-5678,Nov-2017\n")
	    	.append("Royal Bank of  Canada,4519-4532-4524-2456,Oct-2017\n")
	    	.append("American Express,3786-7334-8965-345,Dec-2018\n");

        InputStream input = new ByteArrayInputStream(sb.toString().getBytes());
        List<String[]> unit;
		try {
			unit = CSVParserLoader.getAllDefaultSorted(input, 0, "string", false);
			
		} catch (IOException e) {
			throw new MockitoAssertionError("Data no loaded");
		}

		/* Sorted by field 0 */
		assertEquals("American Express,3786-7334-8965-345,Dec-2018", unit.get(0)[0].toString());
        assertEquals("HSBC Canada,5601-2345-3446-5678,Nov-2017", unit.get(1)[0].toString());
        assertEquals("Royal Bank of  Canada,4519-4532-4524-2456,Oct-2017", unit.get(2)[0].toString());
        
    	
        //fail("No ha recuperado el fichero properties de configuracion. Comprobar la ruta " + ConfigurationFactory.propertiesFilePath );
    }
    
    /**
     * Test of getAllDefaultSorted method, of class CSVParserLoader.
     * TC: 	sort.field=0
     * 		sort.type=string
     * 		sort.desc=true
     */
    @Test
    public void testGetRecordsByFirstFieldDesc() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("HSBC Canada,5601-2345-3446-5678,Nov-2017\n")
	    	.append("Royal Bank of  Canada,4519-4532-4524-2456,Oct-2017\n")
	    	.append("American Express,3786-7334-8965-345,Dec-2018\n");

        InputStream input = new ByteArrayInputStream(sb.toString().getBytes());
        List<String[]> unit;
		try {
			unit = CSVParserLoader.getAllDefaultSorted(input, 0, "string", true);
			
		} catch (IOException e) {
			throw new MockitoAssertionError("Data no loaded");
		}

		/* Sorted desc by field 0 */
        assertEquals("Royal Bank of  Canada,4519-4532-4524-2456,Oct-2017", unit.get(0)[0].toString());
        assertEquals("HSBC Canada,5601-2345-3446-5678,Nov-2017", unit.get(1)[0].toString());
		assertEquals("American Express,3786-7334-8965-345,Dec-2018", unit.get(2)[0].toString());
        
    	
        //fail("No ha recuperado el fichero properties de configuracion. Comprobar la ruta " + ConfigurationFactory.propertiesFilePath );
    }
    
    /**
     * Test of getAllDefaultSorted method, of class CSVParserLoader.
     * TC: 	sort.field=1
     * 		sort.type=string
     * 		sort.desc=false
     */
    @Test
    public void testGetRecordsSecondField() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("HSBC Canada,5601-2345-3446-5678,Nov-2017\n")
	    	.append("Royal Bank of  Canada,4519-4532-4524-2456,Oct-2017\n")
	    	.append("American Express,3786-7334-8965-345,Dec-2018\n");

        InputStream input = new ByteArrayInputStream(sb.toString().getBytes());
        List<String[]> unit;
		try {
			unit = CSVParserLoader.getAllDefaultSorted(input, 1, "string", false);
			
		} catch (IOException e) {
			throw new MockitoAssertionError("Data no loaded");
		}

		/* Sorted by field 1 */
		assertEquals("American Express,3786-7334-8965-345,Dec-2018", unit.get(0)[0].toString());
		assertEquals("Royal Bank of  Canada,4519-4532-4524-2456,Oct-2017", unit.get(1)[0].toString());
		assertEquals("HSBC Canada,5601-2345-3446-5678,Nov-2017", unit.get(2)[0].toString());
		
        
        
    	
        //fail("No ha recuperado el fichero properties de configuracion. Comprobar la ruta " + ConfigurationFactory.propertiesFilePath );
    }
    
    /**
     * Test of getAllDefaultSorted method, of class CSVParserLoader.
     * TC: 	sort.field=1
     * 		sort.type=string
     * 		sort.desc=true
     */
    @Test
    public void testGetRecordsSecondFieldDesc() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("HSBC Canada,5601-2345-3446-5678,Nov-2017\n")
	    	.append("Royal Bank of  Canada,4519-4532-4524-2456,Oct-2017\n")
	    	.append("American Express,3786-7334-8965-345,Dec-2018\n");

        InputStream input = new ByteArrayInputStream(sb.toString().getBytes());
        List<String[]> unit;
		try {
			unit = CSVParserLoader.getAllDefaultSorted(input, 1, "string", true);
			
		} catch (IOException e) {
			throw new MockitoAssertionError("Data no loaded");
		}

		/* Sorted desc by field 1 */
		assertEquals("HSBC Canada,5601-2345-3446-5678,Nov-2017", unit.get(0)[0].toString());
		assertEquals("Royal Bank of  Canada,4519-4532-4524-2456,Oct-2017", unit.get(1)[0].toString());
		assertEquals("American Express,3786-7334-8965-345,Dec-2018", unit.get(2)[0].toString());
        //fail("No ha recuperado el fichero properties de configuracion. Comprobar la ruta " + ConfigurationFactory.propertiesFilePath );
    }
    
    /**
     * Test of getAllDefaultSorted method, of class CSVParserLoader.
     */
    @Test
    public void testGetRecordsDate() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("HSBC Canada,5601-2345-3446-5678,Nov-2017\n")
	    	.append("Royal Bank of  Canada,4519-4532-4524-2456,Oct-2017\n")
	    	.append("American Express,3786-7334-8965-345,Dec-2018\n");

        InputStream input = new ByteArrayInputStream(sb.toString().getBytes());
        List<String[]> unit;
		try {
			unit = CSVParserLoader.getAllDefaultSorted(input, 2, "date", false);
			
		} catch (IOException e) {
			throw new MockitoAssertionError("Data no loaded");
		}

		/* Sorted by field 2 */
		assertEquals("Royal Bank of  Canada,4519-4532-4524-2456,Oct-2017", unit.get(0)[0].toString());
		assertEquals("HSBC Canada,5601-2345-3446-5678,Nov-2017", unit.get(1)[0].toString());
		assertEquals("American Express,3786-7334-8965-345,Dec-2018", unit.get(2)[0].toString());
		    	
        //fail("No ha recuperado el fichero properties de configuracion. Comprobar la ruta " + ConfigurationFactory.propertiesFilePath );
    }
    
    /**
     * Test of getAllDefaultSorted method, of class CSVParserLoader.
     */
    @Test
    public void testGetRecordsDateDesc() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("HSBC Canada,5601-2345-3446-5678,Nov-2017\n")
	    	.append("Royal Bank of  Canada,4519-4532-4524-2456,Oct-2017\n")
	    	.append("American Express,3786-7334-8965-345,Dec-2018\n");

        InputStream input = new ByteArrayInputStream(sb.toString().getBytes());
        List<String[]> unit;
		try {
			unit = CSVParserLoader.getAllDefaultSorted(input, 2, "date", true);
			
		} catch (IOException e) {
			throw new MockitoAssertionError("Data no loaded");
		}

		/* Sorted desc by field 2 */
		assertEquals("American Express,3786-7334-8965-345,Dec-2018", unit.get(0)[0].toString());
		assertEquals("HSBC Canada,5601-2345-3446-5678,Nov-2017", unit.get(1)[0].toString());
		assertEquals("Royal Bank of  Canada,4519-4532-4524-2456,Oct-2017", unit.get(2)[0].toString());
    	
        //fail("No ha recuperado el fichero properties de configuracion. Comprobar la ruta " + ConfigurationFactory.propertiesFilePath );
    }

}