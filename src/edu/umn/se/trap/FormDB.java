//FormDB.java
/**
 */
package edu.umn.se.trap;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Build the FormDB that store the form
 * 
 * @author group17
 */

public class FormDB {

	private Map<String, String> formMap = new HashMap<String, String>();
	// HashMap<key:formId, value: formMap>
	private HashMap<String, Map<String, String>> fidMap = new HashMap<String, Map<String, String>>();
	// HashMap<Key: username, value: fidMap>
	private HashMap<String, HashMap<String, Map<String, String>>> uidMap = new HashMap<String, HashMap<String, Map<String, String>>>();
	/**
	 * Constructor. Sets up the object.
	 */
	public FormDB() 
	{

	}

	public void clearsavedform() throws java.lang.Exception 
	{

	}

	public Map<String, String> getsavedformdata(Integer formId) 
	{

		return formdata;
	}

	public Map<String, String> getcompletedform(Integer formId) 
	{

		return completeform;
	}

	public Map<Integer, TravelFormMetadata> getsavedform() {

		return formlist;
	}

	public Integer saveformdata(Map<String, String> formData, String description) {

		return formId;
	}

	public Integer overwriteformdata(Map<String, String> formData, Interger id) {

		return formId;
	}

	public Integer savecompletedform(Map<String, String> formData) {

		return formId;
	}

	// ---inner class----

	/*
	 * private class FormDB_FIELDS { fomrId, description, username, formdata, };
	 */

}
