package es.aabanegas.csv.util;

/**
 * Constants used in all app. All new configuration variables have to be included here.
 * recuperarlas del properties.
 * 
 * <p>For instance:
 *  {@code String var = ConfigurationFactory.getString(Constants.CONFIG_CONST);}
 * </p>
 * @see es.aabanegas.csv.config.ConfigurationFactory
 * 
 * @author Aurelio Aragones
 */
public class Constants {
    
    /**
     * Input folder.
     */
    public static  String INPUT_CSV_PATH = "files.csv.path";
    
    /**
     * patter to filter files in input folder
     */
    public static String pattern = "*.{csv}";
    
    /**
     * Position of field to sort 
     */
    public static final String SORT_BY = "sort.field";
    
    /**
     * Records sorted by desc order
     */
    public static final String SORT_DESC = "sort.desc";
    
    /**
     *  Type of sorter field
     */
    public static final String SORT_TYPE_FIELD = "sort.field.type";
    
    /**
     *  When it is necessary format of sorter field
     */
	public static final String SORT_FORMAT_FIELD = "sort.field.format";   
	
    /**
     * Possible types of sorter field
     */
    public static final String DATE_TYPE = "date";
    public static final String NUMBER_TYPE = "number";
    public static final String STRING_TYPE = "string"; 

}
