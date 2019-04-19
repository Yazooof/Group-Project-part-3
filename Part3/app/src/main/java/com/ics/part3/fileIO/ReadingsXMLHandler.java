package com.ics.part3.fileIO;


import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ics.part3.study.Reading;

public class ReadingsXMLHandler extends DefaultHandler {

    // List to hold Reading objects
    private ArrayList<Reading> readings = null;
    // A temporal Reading to fill in
    private Reading reading = null;
    // A String Builder to hold the values inside the tags
    private StringBuilder sb = null;
    
    /* Empty constructor */
    public ReadingsXMLHandler() {
        readings = new ArrayList<>();
    }

    // getter method for employee list
    public ArrayList<Reading> getReadings() {
        return readings;
    }

    boolean site_id = false;
    boolean reading_value = false;
    boolean reading_date = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("Reading")) {
            // create a new Reading and put it in the Array List
            String type = attributes.getValue("type");
            String id = attributes.getValue("id");
            // initialize Employee object and set id attribute
            reading = new Reading();
            reading.setReading_type(type);
            reading.setReading_id(id);
        } else if (qName.equalsIgnoreCase("value")) {
            // set boolean values for fields, will be used in setting Reading variables
            reading_value = true;
        } else if (qName.equalsIgnoreCase("site")) {
            site_id = true;
        } 
        // create the data container
        sb = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (reading_value) {
            // age element, set Employee age
            reading.setReading_value(sb.toString());
            reading_value = false;
        } else if (site_id) {
            reading.setSite_id(sb.toString());
            site_id = false;
        } 

        if (qName.equalsIgnoreCase("Reading")) {
            // Confirm that reading has the required fields
            // I'm not enabling the date because none of the 
            // readings in the xml example has a date
            // if you want to test for date too, remove the comments
            if(reading.getReading_type() != null && 
                    reading.getSite_id() != null &&
                    reading.getReading_id() != null &&
                    reading.getReading_value() != null  /*&& reading.getReading_date() != 0*/) {
                // Confirm supported reading type
		String type = reading.getReading_type();
                // I'm ignoring the case, because all the types in the xml example start with 
                // uppercase
                // I also added temperature and pressure, since those are the names
                // available in the XML example
		if(type.equalsIgnoreCase("humidity") ||
                   type.equalsIgnoreCase("temperature") || 
                   type.equalsIgnoreCase("temp") ||
                   type.equalsIgnoreCase("bar_press") ||
                   type.equalsIgnoreCase("pressure") || 
                   type.equalsIgnoreCase("particulate")) {
                    // add Reading object to list
                    readings.add(reading);
		} else {
                    System.out.println("Skipping badly formed reading: reading type is not supported " + type);
                }   
            } else {
                System.out.println("Skipping badly formed reading: missing field");
            }
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        sb.append(new String(ch, start, length));
    }
}
