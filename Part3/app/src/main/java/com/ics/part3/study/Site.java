package com.ics.part3.study;

import java.util.*;


public class Site {
	private String id;
	private ArrayList<Reading> readings;
	private siteStatus status;

	public enum siteStatus{
		ACTIVE_SITE, SITE_COLLECTION_DISABLED, SITE_INVALID, COMPLETED_STUDY;
	}

	public void setSiteStatus(siteStatus s) {
		status = s;
	}

	public siteStatus getSiteStatus() {
		return status;
	}

	/**
	 * Basic constructor, by default it has no readings, and no collection has started
	 */
	public Site(String id) {
		this.id = id;
		this.readings = new ArrayList<>();
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the readings
	 */
	public ArrayList<Reading> getReadings() {
		return readings;
	}

	public void clearReadings() {
		this.readings = null;
	}

	/**
	 * @param reading the reading to add
	 */
	public void addReading(Reading reading) {
		this.readings.add(reading);
	}



}
