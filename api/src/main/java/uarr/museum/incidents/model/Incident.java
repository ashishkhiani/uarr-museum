package uarr.museum.incidents.model;

import java.util.ArrayList;

public class Incident {

	private String id;
	private String incidentLevel;
	private String title;
	private String date;
	private String province;
	private String region;
	private String location;
	private String address;
	private Double latitude;
	private Double longitude;
	private String system;
	private String summary;
	private String notes;
	private String incidentResponse;
	private String policies;
	private ArrayList<String> additionalLinks;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIncidentLevel() {
		return incidentLevel;
	}
	public void setIncidentLevel(String incidentLevel) {
		this.incidentLevel = incidentLevel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getIncidentResponse() {
		return incidentResponse;
	}
	public void setIncidentResponse(String incidentResponse) {
		this.incidentResponse = incidentResponse;
	}
	public String getPolicies() {
		return policies;
	}
	public void setPolicies(String policies) {
		this.policies = policies;
	}
	public ArrayList<String> getAdditionalLinks() {
		return additionalLinks;
	}
	public void setAdditionalLinks(ArrayList<String> additionalLinks) {
		this.additionalLinks = additionalLinks;
	}	
	
}
