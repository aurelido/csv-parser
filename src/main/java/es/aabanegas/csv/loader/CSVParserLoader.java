package es.aabanegas.csv.loader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

import es.aabanegas.csv.util.Constants;
import es.aabanegas.csv.util.FieldTypeComparator;
import es.aabanegas.csv.util.UtilsCSV;
import es.aabanegas.utils.config.ConfigurationFactory;

/**
 * Class to start CSV parser in standalone mode 
 * @author Aurelio Aragones
 */
public class CSVParserLoader {
	private final static Logger logger = Logger
			.getLogger(CSVParserLoader.class.getName());
	
	/**
	 * Configuration variables. Defaults values are defined here
	 */
	private final static String strPath = ConfigurationFactory.getString(Constants.INPUT_CSV_PATH);
	private final static int posSortField = ConfigurationFactory.getInteger(Constants.SORT_BY, 0);
	private final static String typeSortField = ConfigurationFactory.getString(Constants.SORT_TYPE_FIELD, "string");	
	private final static boolean desc = ConfigurationFactory.getBoolean(Constants.SORT_DESC);
	

	/**
	 * Get all sorted record from a InputStream. Format and type could be configured by 
	 * @see /src/main/java/csvParserConfig.properties
	 * @param inputStream Data in CSV format
	 * @param posSortField Position of sorter field
	 * @param typeSortField Type of sorter field [date|number|string]. Customizable sort methods by type
	 * @param desc Indicates if it is descend sorter.
	 * @return Sorter list with record
	 * @throws IOException
	 */
	public static List<String[]> getAllDefaultSorted(InputStream inputStream,
			final int posSortField, final String typeSortField, final boolean desc) throws IOException {
		Reader csvFile;
		List<String[]> records;
		CSVReader<String[]> genericReader;
		
		csvFile = new InputStreamReader(inputStream);
		genericReader = CSVReaderBuilder.newDefaultReader(csvFile);
		records = genericReader.readAll();
		
		
		FieldTypeComparator ccardsComparator = new FieldTypeComparator("MMM-yyyy", posSortField, typeSortField, desc);		
		Collections.sort(records, ccardsComparator);
		
		return records;
	}
	
	/**
	 * Method to run stand-alone version
	 * @param args
	 */
	public static void main(String[] args) {
		List<Path> files;
		InputStream inputStream;
		List<String[]> records;
		
		try {
			files = UtilsCSV.filesCSVInPath(strPath);

			for (Path path : files) {
				inputStream = Files.newInputStream(path);
				records = getAllDefaultSorted(inputStream, posSortField, typeSortField, desc);

				
				logger.log(Level.FINEST, "Congratulations! " + records.size() + "credits cards loaded.");
				for (String[] record: records) {
					logger.log(Level.INFO, Arrays.toString(record));
				}
				
			}
		} catch (IOException | InvalidPathException ioe) {
			logger.log(Level.SEVERE, "Problem I/O. Review config variable ["+Constants.INPUT_CSV_PATH+"] in " + ConfigurationFactory.propertiesFilePath, ioe);
		}
	}

}
