package com.ics.part3.fileIO;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.ics.part3.study.Reading;

public class ReadXML {
	private static ArrayList<Reading> readXML(String path) {
        ArrayList<Reading> readings = null;
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            ReadingsXMLHandler handler = new ReadingsXMLHandler();
            saxParser.parse(new File(path), handler);
            //Get Readings list
            readings = handler.getReadings();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readings;
}
}
