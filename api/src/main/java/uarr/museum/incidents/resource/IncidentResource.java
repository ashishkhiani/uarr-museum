package uarr.museum.incidents.resource;

import static uarr.museum.incidents.cache.SpreadsheetUtil.incidentCache;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.micrometer.core.instrument.util.StringUtils;
import uarr.museum.incidents.model.Incident;

@RestController
public class IncidentResource {

	/*
	 * Returns the list of incidents based on condition
	 */
	@CrossOrigin
	@GetMapping("/uarr/museum/incidents")
	public ArrayList<Incident> getConditionalIncidents(
			@RequestParam(required = false) String bounds,
			@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate,
			@RequestParam(required = false) String system,
			@RequestParam(required = false) String incidentLevel,
			@RequestParam(required = false) String province
			) throws JsonProcessingException{

		// cleanup request parameters
		fromDate = removeSpecialCharactersAndSpaces(fromDate);
		toDate = removeSpecialCharactersAndSpaces(toDate);
		ArrayList<String> systemList = getFilteredParams(system);
		incidentLevel = removeSpecialCharactersAndSpaces(incidentLevel);
		ArrayList<String> provinceList = getFilteredParams(province);
		
		// get all incidents
		ArrayList<Incident> candidateIncidents = getIncidentList();

		// filter incidents by bounds if given
		if(!StringUtils.isBlank(bounds))
			filterByBounds(bounds, candidateIncidents);

		// filter incidents by dateFrom to dateTo
		if(!StringUtils.isBlank(fromDate) || !StringUtils.isBlank(toDate))
			filterByDate(fromDate, toDate, candidateIncidents);

		// filter incidents by systems
		if(systemList.size()>0)
			filterBySystem(systemList, candidateIncidents);

		// filter incidents by incident level
		if(!StringUtils.isBlank(incidentLevel))
			filterByIncidentLevel(incidentLevel, candidateIncidents);
		
		// filter by province
		if(provinceList.size()>0)
			filterByProvince(provinceList, candidateIncidents);

		return candidateIncidents;
	}

	/*
	 * Returns the list of incidents based on condition
	 */
	@CrossOrigin
	@GetMapping("/uarr/museum/incidents/{id}")
	public Incident getIncidentById(
			@PathVariable("id") String id){
		
		if(!StringUtils.isBlank(id)) {
			Iterator<Incident> itr = incidentCache.iterator(); 
			while (itr.hasNext()){ 
				Incident incident = itr.next();
				if(incident.getId().equals(id))
					return incident;
			}
		}
		
		return null;
	}
		
	/*
	 * filter params with multiple values
	 */
	private ArrayList<String> getFilteredParams(String input){
		
		ArrayList<String> filteredParam = new ArrayList<String>();
		if(input != null) {
			if(input.contains(",")) {
				String[] split = input.split(",");
				for(String val : split) {
					if(!StringUtils.isBlank(val)) {
						String output = removeSpecialCharactersAndSpaces(val);
						filteredParam.add(output);
					}
				}
				
			} else {
				if(!StringUtils.isBlank(input)) {
					String output = removeSpecialCharactersAndSpaces(input);
					filteredParam.add(output);
				}
			}
		}
		
		return filteredParam;
		
	}
	
	/*
	 * Returns the list of all incidents
	 */
	private ArrayList<Incident> getIncidentList(){
		ArrayList<Incident> candidateIncidents = new ArrayList<Incident>();
		candidateIncidents.addAll(incidentCache);
		return candidateIncidents;
	}

	/*
	 * Returns the list of all incidents filtered by incident level
	 */
	private void filterByIncidentLevel(String incidentLevel, ArrayList<Incident> candidateList){

		Iterator<Incident> itr = candidateList.iterator(); 
		while (itr.hasNext()){ 
			Incident incident = itr.next();
			if(!compareVal(incidentLevel, incident.getIncidentLevel()))
				itr.remove(); 
		}

	}

	/*
	 * Returns the list of all incidents filtered by system
	 */
	private void filterBySystem(ArrayList<String> systemList, ArrayList<Incident> candidateList){

		Iterator<Incident> itr = candidateList.iterator(); 
		while (itr.hasNext()){ 
			Incident incident = itr.next();
			
			Iterator<String> inputItr = systemList.iterator();
			boolean isFound = false;
			while(inputItr.hasNext()) {
				String system = inputItr.next();
				if(compareVal(system, incident.getSystem())) {
					isFound = true;
				}
			}
			
			if(!isFound) {
				itr.remove();
			}
			
			
		}

	}
	
	/*
	 * Returns the list of all incidents filtered by province
	 */
	private void filterByProvince(ArrayList<String> provinceList, ArrayList<Incident> candidateList){

		Iterator<Incident> itr = candidateList.iterator(); 
		while (itr.hasNext()){ 
			Incident incident = itr.next();
			
			Iterator<String> inputItr = provinceList.iterator();
			boolean isFound = false;
			while(inputItr.hasNext()) {
				String province = inputItr.next();
				if(compareVal(province, incident.getProvince())) {
					isFound = true;
				}
			}
			
			if(!isFound) {
				itr.remove();
			}
			
			
		}

	}
	
	/*
	 * Returns the list of all incidents filtered by date (from - to)
	 */
	private void filterByDate(String fromDateVal, String toDateVal, ArrayList<Incident> candidateList){

		try {
			// date format MM-dd-yyyy
			SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
			
			Date fromDate = StringUtils.isBlank(fromDateVal) ? null : format.parse(createDateString(fromDateVal)); 
			Date toDate = StringUtils.isBlank(toDateVal) ? null : format.parse(createDateString(toDateVal)); 
			
			Iterator<Incident> itr = candidateList.iterator(); 
			while (itr.hasNext()){ 
				
				Incident incident = itr.next();
				Date incidentDate = format.parse(createDateString(incident.getDate()));
				
				// remove dates that are earlier than fromDate
				if(fromDate!=null && incidentDate.compareTo(fromDate) < 0)
					itr.remove();
				
				// remove dates that are after than toDate
				if(toDate!=null && incidentDate.compareTo(toDate) > 0)
					itr.remove();
			}
		} catch(ParseException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * filter by bounds
	 */
	private void filterByBounds(String bounds, ArrayList<Incident> candidateList) {
		
		try {
			
			Iterator<Incident> itr = candidateList.iterator(); 
			while (itr.hasNext()){ 
				Incident incident = itr.next();
				if(!isExistInBound(bounds, incident))
					itr.remove(); 
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * identify if a location lies in a bound
	 */
	private boolean isExistInBound(String bounds, Incident incident) {
		
		String[] split = bounds.trim().split(",");
		double southWestLatitude = Double.parseDouble(split[0].trim());
		double southWestLongitude = Double.parseDouble(split[1].trim());
		double northEastLatitude = Double.parseDouble(split[2].trim());
		double northEastLongitude = Double.parseDouble(split[3].trim());
		
		double latitude = incident.getLatitude();
		double longitude = incident.getLongitude();
		
		if(latitude <= northEastLatitude 
				&& latitude >= southWestLatitude
				&& longitude <= northEastLongitude
				&& longitude >= southWestLongitude) {
			
			return true;
			
		}
		
		return false;
	}

	/*
	 * case-insensitive and space-insensitive comparison
	 */
	private boolean compareVal(String value1, String value2) {

		if(StringUtils.isBlank(value1) || StringUtils.isBlank(value2))
			return false;

		value1 = removeSpecialCharactersAndSpaces(value1);
		value2 = removeSpecialCharactersAndSpaces(value2);

		return value1.equalsIgnoreCase(value2);

	}
	
	/*
	 * remove special characters and spaces
	 */
	private String removeSpecialCharactersAndSpaces(String input) {
		if(!StringUtils.isBlank(input))
			input = input.replaceAll("[^a-zA-Z0-9]", "");
		return input;
	}
	
	/*
	 * create string in format MM-dd-yyyy from MMddyyyy
	 */
	private String createDateString(String input) {
		StringBuilder date = new StringBuilder(input);
		date.insert(2, '-');
		date.insert(5, '-');
		return date.toString();
	}

}
