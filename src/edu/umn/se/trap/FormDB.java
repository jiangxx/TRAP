//FormDB.java
/**
 */
package edu.umn.se.trap;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import edu.umn.se.trap.TravelFormProcessorIntf.FORM_STATUS;

/**
 * Build the FormDB that store the form 
 * @author group17
 */
public class FormDB 
{
	public static Integer genfid = 0;
	public static String currentUser;
	
	//HashMap<key:formId, value: TravelFormMetadata>
	//private Map<Integer, TravelFormMetadata> fidDescription = new HashMap<Integer, TravelFormMetadata>();
	
	//HashMap<key:userId, value: Map<formId, description>>
	public static Map<String, Map<Integer, TravelFormMetadata>> uidDescription = new HashMap<String, Map<Integer, TravelFormMetadata>>();                                             //////
	
	// HashMap<key:formId, value: formMap>
	//private Map<Integer, Map<String, String>> fidMap = new HashMap<Integer, Map<String, String>>();
	
	// HashMap<Key: username, value: fidMap>
	public static Map<String, Map<Integer, Map<String, String>>> uidMap = new HashMap<String, Map<Integer, Map<String, String>>>();   /////
	
	/**
	 * Constructor. Sets up the object.
	 */
	public FormDB() 
	{

	}

	public static void clearsavedform() throws java.lang.Exception 
	{
		uidDescription.clear();
		uidMap.clear();
	}

	public static Map<String, String> getsavedformdata(Integer formId) 
	{				
		Map<String, String> formData = new HashMap<String, String>();
		Map<Integer, Map<String, String>> fidMap = new HashMap<Integer, Map<String, String>>();
		fidMap.putAll(uidMap.get(currentUser));
		
		boolean i = false;
	    Iterator<Entry<Integer, Map<String, String>>> iter = fidMap.entrySet().iterator();
	    while (iter.hasNext()) 
		{
			Map.Entry<Integer, Map<String, String>> entry = (Map.Entry<Integer, Map<String, String>>) iter.next();
			Object key = entry.getKey();
			i = key.equals(formId);
		}
		if(i)
		{
			formData.putAll(fidMap.get(formId));
			return formData;
		}
		else
		{
			throw exception;    //no form is found in this formId
		}

	}

	public static Map<String, String> getcompletedform(Integer formId) 
	{
		Map<String, String> completeForm = new HashMap<String, String>();
		Map<Integer, Map<String, String>> fidMap = new HashMap<Integer, Map<String, String>>();
		fidMap.putAll(uidMap.get(currentUser));
		
		boolean i = false;
	    Iterator<Entry<Integer, Map<String, String>>> iter = fidMap.entrySet().iterator();
	    while (iter.hasNext()) 
		{
			Map.Entry<Integer, Map<String, String>> entry = (Map.Entry<Integer, Map<String, String>>) iter.next();
			Object key = entry.getKey();
			i = key.equals(formId);
		}
	    if(i)
		{
	    	completeForm.putAll(fidMap.get(formId));
	    	return completeForm;
		}
	    else
	    {
	    	throw exception;   //no form is found in this formId
	    }
	}

	public static Map<Integer, TravelFormMetadata> getsavedform() 
	{
		Map<Integer, TravelFormMetadata> formlist = new HashMap<Integer, TravelFormMetadata>();
		formlist.putAll(uidDescription.get(currentUser));
		return formlist;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static Integer saveformdata(Map<String, String> formData, String description) 
	{
		Integer formId = genfid;				
		// Creat Map of detailed form data
	    Map<String, String> formMap = new HashMap<String, String>();
		formMap.putAll(formData);
	
        //check if the user already exist in the database. 
	    boolean c = false;
	    Iterator<Entry<String, Map<Integer, TravelFormMetadata>>> iter = uidDescription.entrySet().iterator();
	    while (iter.hasNext()) 
		{
			Map.Entry<String, Map<Integer, TravelFormMetadata>> entry = (Map.Entry<String, Map<Integer, TravelFormMetadata>>) iter.next();
			Object key = entry.getKey();
			c = key.equals(currentUser);
		}
		
		if (c)   //current user already have records
		{
			Map<Integer, TravelFormMetadata> fidDescription = new HashMap<Integer, TravelFormMetadata>();
			fidDescription.putAll(uidDescription.get(currentUser));	
			TravelFormMetadata Metadata =  new TravelFormMetadata();	
			Metadata.description = new String(description);
			Metadata.status = FORM_STATUS.DRAFT;
			fidDescription.put(formId, Metadata);
			uidDescription.put(currentUser, fidDescription);
			
			Map<Integer, Map<String, String>> fidMap = new HashMap<Integer, Map<String, String>>();
			fidMap.putAll(uidMap.get(currentUser));
			fidMap.put(formId, formMap);			
			uidMap.put(currentUser, fidMap);
		}
		else  //no forms about current user, create.
		{
			Map<Integer, TravelFormMetadata> fidDescription = new HashMap<Integer, TravelFormMetadata>();
			TravelFormMetadata Metadata =  new TravelFormMetadata();	
			Metadata.description = new String(description);
			Metadata.status = FORM_STATUS.DRAFT;
			fidDescription.put(formId, Metadata);
			uidDescription.put(currentUser, fidDescription);
			// HashMap<key:formId, value: formMap>
			Map<Integer, Map<String, String>> fidMap = new HashMap<Integer, Map<String, String>>();
			fidMap.put(formId, formMap);			
			uidMap.put(currentUser, fidMap);
		}	  
		genfid++;
		return formId;
	}

	public static Integer overwriteformdata(Map<String, String> formData, Integer id) 
	{
		boolean c = false;
	    Iterator<Entry<String, Map<Integer, TravelFormMetadata>>> iter = uidDescription.entrySet().iterator();
	    while (iter.hasNext()) 
		{
			Map.Entry<String, Map<Integer, TravelFormMetadata>> entry = (Map.Entry<String, Map<Integer, TravelFormMetadata>>) iter.next();
			Object key = entry.getKey();
			c = key.equals(currentUser);
		}
		if (c)
		{
			throw exception;    //no form is found in this formId
		}
		
		Integer formId = id;
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.putAll(formData);
		
		Map<Integer, Map<String, String>> fidMap = new HashMap<Integer, Map<String, String>>();
		fidMap.putAll(uidMap.get(currentUser));
		fidMap.put(formId, formMap);			
		uidMap.put(currentUser, fidMap);
				
		return formId;
	}
		
	public static Integer savecompletedform(Map<String, String> formData) 
	{
		Integer formId = genfid;				
	    Map<String, String> formMap = new HashMap<String, String>();
		formMap.putAll(formData);	
        //check if the user already exist in the database. 
	    boolean c = false;
	    Iterator<Entry<String, Map<Integer, TravelFormMetadata>>> iter = uidDescription.entrySet().iterator();
	    while (iter.hasNext()) 
		{
			Map.Entry<String, Map<Integer, TravelFormMetadata>> entry = (Map.Entry<String, Map<Integer, TravelFormMetadata>>) iter.next();
			Object key = entry.getKey();
			c = key.equals(currentUser);
		}
	    
	    if (c)   //current user already have records
		{
	    	Map<Integer, TravelFormMetadata> fidDescription = new HashMap<Integer, TravelFormMetadata>();
			fidDescription.putAll(uidDescription.get(currentUser));	
			TravelFormMetadata Metadata =  new TravelFormMetadata();	
			Metadata.description = new String("completed");
			Metadata.status = FORM_STATUS.SUBMITTED;
			fidDescription.put(formId, Metadata);
			uidDescription.put(currentUser, fidDescription);
			
			Map<Integer, Map<String, String>> fidMap = new HashMap<Integer, Map<String, String>>();
			fidMap.putAll(uidMap.get(currentUser));
			fidMap.put(formId, formMap);			
			uidMap.put(currentUser, fidMap);
		}
		else  //no forms about cueent user, creat.
		{
			Map<Integer, TravelFormMetadata> fidDescription = new HashMap<Integer, TravelFormMetadata>();
			TravelFormMetadata Metadata =  new TravelFormMetadata();	
			Metadata.description = new String("complete");
			Metadata.status = FORM_STATUS.SUBMITTED;
			fidDescription.put(formId, Metadata);
			uidDescription.put(currentUser, fidDescription);
			
			// HashMap<key:formId, value: formMap>
			Map<Integer, Map<String, String>> fidMap = new HashMap<Integer, Map<String, String>>();
			fidMap.put(formId, formMap);			
			uidMap.put(currentUser, fidMap);
		}	    
		genfid++;
		return formId;
	}

	/*
	 * private class FormDB_FIELDS { fomrId, description, username, formdata, };
	 */

}
