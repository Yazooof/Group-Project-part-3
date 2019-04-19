package com.ics.part3.study;

import java.util.*;
import com.ics.part3.study.*;

public class ListOfStudies {
    private ArrayList<Study> studyList = new ArrayList<>();

    //Add single study to study list.
    public String addStudy(String studyID, String studyName) {
        String message = null;
        if((studyID == null) || (studyName == null)) {
            message = "Invalid study entry\n";
        }else {
            Study newStudy = new Study(studyID, studyName);
            Boolean found = false;
            if(studyList.isEmpty()) {

            }else {
                for (Study study : studyList) {
                    if(Integer.parseInt(study.getStudyID()) == Integer.parseInt(studyID)) {
                        message = "Study already added\n";
                        found = true;
                        break;
                    }
                }
            }

            if(found == false) {
                studyList.add(newStudy);
                message = "Study added";

            }
        }
		return message;
    }

    //Remove single study from study list. returns boolean true if removed and false if not.
    public boolean removeStudy(String studyID) {
    	for (Study study : studyList) {
			if(study.getStudyID() == studyID) {
				study.setStudyID(null);
				study.setStudyName(null);
				return true;
			}else {
				return false;
			}
		}
		return false;
    }

    //Add a single site to a given study then return message
    public String addSite(String studyID, String siteID) {

    	String message = null;

		Site newSite = new Site(siteID);

		boolean found = false;

		for (Study study : studyList) {
			if(Integer.parseInt(study.getStudyID()) == Integer.parseInt(studyID)) {
				for (Site site : study.getSites()) {
					if(Integer.parseInt(site.getId()) == Integer.parseInt(siteID)) {

						message = "Site already exists\n";
						found = true;
					}
				}

				if(!found) {
					message = "New site added\n";
					study.addSite(newSite);
				}
			}
		}
		return message;

    }

    //Removes a site from a given study and returns true if site was removed.
    public boolean removeSite(String studyID, String siteID) {
    	boolean flag = false;
    	for (Study study : studyList) {
			if(study.getStudyID() == studyID) {
				for (Site site : study.getSites()) {
					if(site.getId() == siteID) {
						site.setId(null);
						site.setSiteStatus(null);
						site.clearReadings();
						flag = true;
					}
				}
			}
		}
		return flag;
    }

    //Adds single reading to the given site and study then returns message.
    public String addReading(String studyID, String siteID, String readingID,
                             String readingDate, Reading.readingType readingType, String readingValue) {
        String message = null;


        Reading reading = new Reading();

        reading.setSite_id(siteID);
        reading.setReading_date(readingDate);
        reading.setReading_id(readingID);
        reading.setReading_type(readingType);
        reading.setReading_value(readingValue);

        for (Study study : studyList) {
            if(Integer.parseInt(study.getStudyID()) == Integer.parseInt(studyID)) {
                for (Site site : study.getSites()) {
                    if(Integer.parseInt(site.getId()) == Integer.parseInt(siteID)) {
                        if(site.getSiteStatus() == Site.siteStatus.ACTIVE_SITE) {
                            site.addReading(reading);
                           message = "Reading Added\n";
                        }else {
                          message = "Site not collecting. Start site collection to add reading.\n";
                        }
                    }
                }
            }
        }
        return message;
    }

    //Remove single reading from given study and site and return true if removed
    public boolean removeReading(String studyID, String siteID, String readingID) {
    	boolean flag = false;
    	for (Study study : studyList) {
			if(study.getStudyID() == studyID) {
				for (Site site : study.getSites()) {
					if(site.getId() == siteID) {
						for (Reading reading : site.getReadings()) {
							if(reading.getReading_id() == readingID) {
								reading.setReading_date(null);
								reading.setReading_id(null);
								reading.setReading_type(null);
								reading.setSite_id(null);
								reading.setReading_value(null);
								flag = true;
							}
						}
					}
				}
			}
		}
		return flag;
    }

    //Get all readings stored in the application. returns array list of readings.
    public ArrayList<String> getAllReadings(){
    	ArrayList<String> arr = new ArrayList<>();
    	for (Study study : studyList) {
			for (Site site : study.getSites()) {
				for (Reading reading : site.getReadings()) {
					if(!site.getReadings().isEmpty()) {
						arr.add(reading.toString());
					}
				}
			}
    	}
		return arr;
    }

    public void setSiteStatus(String studyID, String siteID, Site.siteStatus siteStatus) {

    	for (Study study : studyList) {
			if(study.getStudyID() == studyID) {
				for (Site site : study.getSites()) {
					if(site.getId() == siteID) {
						site.setSiteStatus(siteStatus);
						}
					}
				}
			}
		}

    //Returns list of active site that are able to have readings added to them.
    public ArrayList<Site> getActiveSites(String studyID) {
    	ArrayList<Site> activeSites = null;
		for (Study study : studyList) {
			if(study.getStudyID() == studyID) {
				for (Site site : study.getSites()) {
					if(site.getSiteStatus() == Site.siteStatus.ACTIVE_SITE) {
						activeSites.add(site);
					}
				}
			}
		}
		return activeSites;
    }

    //Returns list of all study ids in the app
    public ArrayList<String> getAllStudyIDs(){
    	ArrayList<String> studyIDs = null;
    	for (Study study : studyList) {
			studyIDs.add(study.getStudyID());
		}
		return studyIDs;
    }

    //Returns list of site ids for a given study
    public ArrayList<String> getAllSiteIDs(String studyID){
    	ArrayList<String> siteIDs = null;
    	for (Study study : studyList) {
			if(study.getStudyID() == studyID) {
				for (Site site : study.getSites()) {
					siteIDs.add(site.getId());
				}
			}
		}
		return siteIDs;
    }







}