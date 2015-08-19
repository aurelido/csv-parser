package es.aabanegas.csv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FieldTypeComparator implements Comparator<String[]> {
	private final static Logger logger = Logger
			.getLogger(FieldTypeComparator.class.getName());
	
	private final SimpleDateFormat sdf;
	private final String typeSortField;
	private final int posSortField;

	private final boolean desc;
	
	public FieldTypeComparator() {
		super();
		this.sdf = new SimpleDateFormat("MMM-yyyy", Locale.ENGLISH);
		this.typeSortField = Constants.STRING_TYPE;
		this.posSortField = 0;
		this.desc = true;
	}
	
	public FieldTypeComparator(String dateFormat, int posField, String typeField, boolean desc) {
		super();
		this.sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
		this.typeSortField = typeField;
		this.posSortField = posField;
		this.desc = desc;
	}

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

}
