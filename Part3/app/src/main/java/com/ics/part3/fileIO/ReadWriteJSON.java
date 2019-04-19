package com.ics.part3.fileIO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.ics.part3.study.Reading;

public class ReadWriteJSON {
	/**
	 * Auxiliary function for writing readings to a JSON file
	 */
	private static void writeJSON(ArrayList<Reading> readings, String path) {
		// JSONArray that will hold all readings
		JSONArray combined = new JSONArray();
		
		// Add all readings to a JSONObject, and then to the array
		for(Reading r : readings) {
			JSONObject curr = new JSONObject(r);
			combined.put(curr);
		}
		
		// Add array to a final JSONObject
		JSONObject top = new JSONObject();
		top.put("site_readings", combined);
		
		// Finally, write to file
		try(FileWriter writer = new FileWriter(path)) {
			top.write(writer);
		} catch (IOException e) {
			System.out.println("Could not open file for IO!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Auxiliary function for opening and reading JSON file
	 */
	private static ArrayList<Reading> readJSON(String path) {
		JSONObject in = null;
		// Create reader, which can be used to construct a JSON parser
		try(FileReader reader = new FileReader(path)) {
			JSONTokener tok = new JSONTokener(reader);
			in = new JSONObject(tok);
		} catch (Exception e) {
			System.out.println("Could not open file for IO! ");
			return null;
		}
		
		// Check for badly formed input JSON
		if(in.isNull("site_readings")) {
			System.out.println("JSON file has no site_readings field!");
			return null;
		}
			
		// If file has been read, and contains readings, begin splitting into readings
		ArrayList<Reading> ret = new ArrayList<Reading>();
		JSONArray json_readings = in.getJSONArray("site_readings");
		
		for(int i = 0; i != json_readings.length(); ++i)
		{
			JSONObject json_curr = json_readings.getJSONObject(i);
			
			// Confirm that reading has the required fields
			if(!json_curr.has("reading_type") ||
				!json_curr.has("site_id") ||
				!json_curr.has("reading_id") ||
				!json_curr.has("reading_value") ||
				!json_curr.has("reading_date")) {
				System.out.println("Skipping badly formed reading: missing field");
				continue;
			}
			
			// Confirm supported reading type
			String type = json_curr.getString("reading_type");
			if(!type.equals("humidity") && !type.equals("temp") && !type.equals("bar_press") && !type.equals("particulate")) {
				System.out.println("Skipping badly formed reading: reading type is not supported");
				continue;
			}
			
			// Construct reading based on the JSON fields
			Reading curr = new Reading(type,
					json_curr.getString("site_id"),
					json_curr.getString("reading_id"),
					json_curr.getString("reading_value"),
					json_curr.getString("reading_date"));

			ret.add(curr);
		}
		
		return ret;
	}
        
        /**
	 * Auxiliary function for opening and reading JSON file
	 */
}
