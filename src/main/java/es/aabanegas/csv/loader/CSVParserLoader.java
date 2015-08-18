package es.aabanegas.csv.loader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

import es.aabanegas.csv.util.Constants;
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
		
		
		Comparator<String[]> ccardsComparator = new Comparator<String[]>() {
			private final SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy", Locale.ENGLISH);
			
			@Override
			public int compare(String[] cc1, String[] cc2) {
				String[] values1 = cc1[0].split(",");
				String[] values2 = cc2[0].split(",");
				int res = 0;
				
				// add specific compare method for each type
				switch (typeSortField) {
					case Constants.DATE_TYPE:
						try {
							res = dateCompareTo(values1, values2);
						} catch (ParseException e) {
							logger.log(Level.SEVERE, "Error date format: "+values1+", "+values2);
						}
						break;
					case Constants.STRING_TYPE:
						// we could customize string sort method
						res = genericCompareTo(values1, values2);
						break;
					default:
						res = genericCompareTo(values1, values2);
						break;
					}
				return res;
			}
			
			private int genericCompareTo(String[] values1, String[] values2) {
				return (!desc)?values1[posSortField].toString().compareTo(values2[posSortField].toString())
						: values2[posSortField].toString().compareTo(values1[posSortField].toString());
			}
			
			private int dateCompareTo(String[] values1, String[] values2) throws ParseException {		
				Date date1 = sdf.parse(values1[posSortField]);
				Date date2 = sdf.parse(values2[posSortField]);
				
				return (!desc)? date1.compareTo(date2): date2.compareTo(date1);
			}
		};
		
		Collections.sort(records, ccardsComparator);
		return records;
	}

}
