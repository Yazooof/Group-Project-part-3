package com.ics.part3.study;

public class Reading {

    private readingType reading_type;
    private String reading_id;
    private String reading_value;
    private String reading_date;
	private String site_id;

	public enum readingType{
		HUMIDITY, PARTICULATE, TEMP, BAR_PRESS;
	}

    public Reading() {
    	this.site_id = null;
        this.reading_type = null;
        this.reading_id = null;
        this.reading_value = null;
        this.reading_date = null;

    }

    /**
     * Simple constructor with all fields considered
     */
    public Reading(String site_id, readingType reading_type,
    		String reading_id, String reading_value, String reading_date) {

    	this.site_id = site_id;
        this.reading_type = reading_type;
        this.reading_id = reading_id;
        this.reading_value = reading_value;
        this.reading_date = reading_date;
    }

    /**
     * Return reading data
     */
    public String toString() {
        String ret = "";
        ret += String.format("site ID = %s\n", site_id);
        ret += String.format("type = %s\n", readingTypeToString(reading_type));
        ret += String.format("reading ID = %s\n", reading_id);
        ret += String.format("value = %s\n", reading_value);
        ret += String.format("date = %s\n", reading_date);

        return ret;
    }

    public String readingTypeToString(readingType readingType) {
		switch (readingType) {
		case TEMP:
			return "Temp";
		case BAR_PRESS:
			return "Bar_Press";
		case PARTICULATE:
			return "Particulate";
		case HUMIDITY:
			return "Humidity";

		default:
			return null;
				}

    }


    public readingType getReading_type() {
        return reading_type;
    }


    public void setReading_type(readingType reading_type) {
        this.reading_type = reading_type;
    }


    public String getReading_id() {
        return reading_id;
    }


    public void setReading_id(String reading_id) {
        this.reading_id = reading_id;
    }


    public String getReading_value() {
        return reading_value;
    }

    public void setReading_value(String reading_value) {
        this.reading_value = reading_value;
    }
    public String getReading_date() {
        return reading_date;
    }


    public void setReading_date(String reading_date) {
        this.reading_date = reading_date;
    }

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}



}
