package uarr.museum.incidents.cache;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import io.micrometer.core.instrument.util.StringUtils;
import uarr.museum.incidents.model.Incident;

public class SpreadsheetUtil {

	public static ArrayList<Incident> incidentCache = new ArrayList<Incident>();

	/*
	 * Loads the spreadsheet into the cache at runtime
	 */
	public static void loadSpreadsheet(String pathToFile) {

		try {
			Reader csvData = new FileReader(pathToFile);
		    Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(csvData);
		    boolean isFirstRecord = true;
		    
		    for (CSVRecord record : records) {
		    	
		    	// skip the header
		    	if(isFirstRecord) {
		    		isFirstRecord = false;
		    		continue;
		    	}		    	
		    	
		    	// create an incident
		    	Incident incident = createIncident(record);
		    	
		    	// cache incident
		    	if(incident!=null)
		    		cacheIncident(incident);
		    	
		    }
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Incident createIncident(CSVRecord csvRecord) {
				
		try {
			Incident incident = new Incident();
			incident.setId(csvRecord.get(0));
			incident.setIncidentLevel(csvRecord.get(1));
			incident.setTitle(csvRecord.get(2));
			incident.setDate(parseDate(csvRecord.get(3),csvRecord.get(4),csvRecord.get(5)));
			incident.setProvince(csvRecord.get(6));
			incident.setRegion(csvRecord.get(7));
			incident.setLocation(csvRecord.get(8));
			incident.setAddress(csvRecord.get(9));
			incident.setLatitude(Double.parseDouble(csvRecord.get(10)));
			incident.setLongitude(Double.parseDouble(csvRecord.get(11)));
			incident.setSystem(csvRecord.get(12));
			incident.setSummary(csvRecord.get(13));
			incident.setNotes(csvRecord.get(14));
			incident.setIncidentResponse(csvRecord.get(15));
			incident.setPolicies(csvRecord.get(16));
			incident.setAdditionalLinks(parseLinks(csvRecord.get(17)));
			
			return incident;
		}catch(Exception e) {
			return null;
		}
	}
	
	private static String parseDate(String month, String day, String year) {
		
		if(StringUtils.isBlank(day)) {
			day = "01";
		} else if(day.trim().length()==1) {
			day = "0"+day.trim();
		}
		
		if(StringUtils.isBlank(month)) {
			month = "01";
		} else if(month.trim().length()==1) {
			month = "0"+month.trim();
		}
		
		if(StringUtils.isBlank(year)) {
			year = "2020";
		}
		
		return month+day+year;
	}
	
	private static void cacheIncident(Incident incident) {
		incidentCache.add(incident);
	}
	
	private static ArrayList<String> parseLinks(String linkList){
		
		ArrayList<String>  additionalLinks = new ArrayList<String>();
		
		if(!StringUtils.isBlank(linkList)) {
			String[] splitList = linkList.trim().split(" ");
			
			for(String link : splitList) {
				if(!StringUtils.isBlank(link))
					additionalLinks.add(link.trim());
			}
		}
		
		return additionalLinks;
	}

}
