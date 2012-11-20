//FormDB.java
/**
 */
package edu.umn.se.trap;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import edu.umn.se.trap.TravelFormProcessor.FORM_STATUS;

/**
 * Build the FormDB that store forms created by all the users
 * @author group17
 */
public class FormDB 
{
	public static String currentUser;
	
	//HashMap<key:formId, value: TravelFormMetadata>
	//private Map<Integer, TravelFormMetadata> fidDescription = new HashMap<Integer, TravelFormMetadata>();
	
	//HashMap<key:userId, value: Map<formId, description>>
	public static Map<String, Map<Integer, TravelFormMetadata>> uidDescription = new HashMap<String, Map<Integer, TravelFormMetadata>>();            
	
	// HashMap<key:formId, value: formMap>
	//private Map<Integer, Map<String, String>> fidMap = new HashMap<Integer, Map<String, String>>();
	
	// HashMap<Key: username, value: fidMap>
	public static Map<String, Map<Integer, Map<String, String>>> uidMap = new HashMap<String, Map<Integer, Map<String, String>>>(); 
	
	/**
	 * Constructor. Sets up the object.
	 */
	public FormDB() 
	{
//		TravelFormProcessor processor = new TravelFormProcessor();
//		
//		//Initialize the userId
//		currentUser = processor.getUser();
	}
	
	/**
	 * Clear the whole formDB
	 * @throws TRAPException 
	 */
	public void clearSavedForm() throws TRAPException{
		uidDescription.clear();
		uidMap.clear();

		if (!uidDescription.isEmpty()) {
			throw new TRAPException("uidDescription is not empty!");
		}
		if (!uidMap.isEmpty()) {
			throw new TRAPException("uidMap is not empty!");
		}
	}

	/**
	 * Return the saved form data for a given formId
	 *  
	 * @param formId the ID of a form
	 * @return the saved form data
	 * @throws TRAPException 
	 */
	public Map<String, String> getSavedFormdata(Integer formId) throws TRAPException{
		Map<Integer, TravelFormMetadata> tmpDescription = getSavedFormList();
		
		if(tmpDescription.get(formId).status.equals(FORM_STATUS.DRAFT))
		{
			return getFormData(formId);
		}else
			return null;
	}
	
	/**
	 * Return the completed form data for a given formId
	 * 
	 * @param formId the ID of a form
	 * @return the completed form data.
	 * @throws TRAPException 
	 */
	public Map<String, String> getCompletedForm(Integer formId) throws TRAPException{
		Map<Integer, TravelFormMetadata> tmpDescription = getSavedFormList();
		
		if(tmpDescription.get(formId).status.equals(FORM_STATUS.SUBMITTED)){
			return getFormData(formId);
		}else
			return null;
	}

	/**
	 * Return a list of saved form IDs
	 * 
	 * @return a Map<Integer, TravelFormMetadata> data type holds formId as well as TravelFormMetadata
	 * @throws TRAPException 
	 */
	public Map<Integer, TravelFormMetadata> getSavedFormList() throws TRAPException{		
		// Check whether or not the formDB is empty
		if (uidDescription.isEmpty() || uidMap.isEmpty()) {
			throw new TRAPException("getSavedFormList(): uidDescription or uidMap is empty!");
		}

		return uidDescription.get(currentUser);	
	}

	/**
	 * Save a new draft form in the formDB
	 * 
	 * @param formData the data in the form
	 * @param description the description of the form
	 * @return Integer the ID of the form
	 * @throws TRAPException 
	 */
	public Integer saveFormData(Map<String, String> formData, String description) throws TRAPException{
		//Generate the ID of the form
		Integer formId = 0;
		
		if(formData.isEmpty() || description.isEmpty()){
			throw new TRAPException("saveFormData(): invliad form data!!");
		}
	
		//Save the draft in the fidDescription
		Map<Integer, TravelFormMetadata> tmpDescription = new HashMap<Integer, TravelFormMetadata>();
		//Save the draft in the fidMap
		Map<Integer, Map<String, String>> fidMap = new HashMap<Integer, Map<String, String>>();
		
        //Check whether or not the current user is exist
		if(uidDescription.containsKey(currentUser)){
			formId = getLatestFormId() +1;					
			tmpDescription = uidDescription.get(currentUser);
			fidMap = uidMap.get(currentUser);	
		}
		else{
			formId = 1;
		}		
		//Save the draft in the uidDescription	
		//uidDescription: Map<username, Map<formId, TravelFormMetadata>>
		TravelFormMetadata tmpMeta = new TravelFormMetadata();
		tmpMeta.description = description;
		tmpMeta.status = FORM_STATUS.DRAFT;
		tmpDescription.put(formId, tmpMeta);	
		
		uidDescription.put(currentUser, tmpDescription);
		
		//Save the draft in the uidMap
		fidMap.put(formId, formData);
		//uidMap: Map<username, Map<formId, formData>>
		uidMap.put(currentUser, fidMap);	
				
		return formId;
	}

	/**
	 * Overwrite an existing form
	 * 
	 * @param formData the data in the form
	 * @param formId the ID of a form
	 * @return Integer the ID of the form
	 * @throws TRAPException 
	 */
	public Integer overwriteFormData(Map<String, String> formData, Integer formId) throws TRAPException{
		//Check whether or not the formDB is empty
		if(checkEmpty()){
			throw new TRAPException("overwriteFormData(): the formDB is empty!!");
		}
		
		//Check whether or not the formId is valid
		Map<Integer, TravelFormMetadata> formList = uidDescription.get(currentUser);
		if(!formList.containsKey(formId)){
			throw new TRAPException("overwriteFormData(): the formId is not exist in the uidDescription!!");
		}
		
		//Check the input values
		if(formData.isEmpty() || formId ==0){
			throw new TRAPException("overwriteFormData(): invliad form data or formId!!");
		}
		
		//Since HashMap overwrites duplicate keys, only modify the uidMap
		Map<Integer, Map<String, String>> fidMap = uidMap.get(currentUser);
		fidMap.put(formId, formData);
		uidMap.put(currentUser, fidMap);
		
		return formId;		
	}
		
	/**
	 * Save a form that passed all the submission checks
	 * 
	 * @param formData the data in the form
	 * @param formId the ID of the form
	 * @return Integer the ID of the form
	 * @throws TRAPException 
	 */
	public Integer saveCompletedFormData(Map<String, String> formData, Integer formId) throws TRAPException{
		//Check whether or not the formDB is empty
		if(checkEmpty()){
			throw new TRAPException("saveCompletedFormData(): the formDB is empty!!");
		}
		
		//Check whether or not the formId is valid
		Map<Integer, TravelFormMetadata> formList = uidDescription.get(currentUser);
		if(!formList.containsKey(formId)){
			throw new TRAPException("saveCompletedFormData(): the formId is not exist in the uidDescription!!");
		}
		
		//Check the input values
		if(formData.isEmpty() || formId ==0){
			throw new TRAPException("saveCompletedFormData(): invliad form data or formId!!");
		}
		
		//Since HashMap overwrites duplicate keys, only modify the uidMap
		Map<Integer, Map<String, String>> fidMap = uidMap.get(currentUser);
		fidMap.put(formId, formData);
		uidMap.put(currentUser, fidMap);
		
		Map<Integer, TravelFormMetadata> tmpDescription = uidDescription.get(currentUser);
		//uidDescription: Map<username, Map<formId, TravelFormMetadata>>
		TravelFormMetadata tmpMeta = new TravelFormMetadata();
		tmpMeta.status = FORM_STATUS.SUBMITTED;
		tmpDescription.put(formId, tmpMeta);	
		
		uidDescription.put(currentUser, tmpDescription);
		
		return formId;		
	}
	
	//-----Private APIs-----
	
	/**
	 * Return the latest formId based on the currentUser
	 * 
	 * @return the latest formId
	 */
	private static Integer getLatestFormId(){
		Map<Integer, TravelFormMetadata> fidDescription = uidDescription.get(currentUser);
		Iterator<Integer> allFids = fidDescription.keySet().iterator();
		Integer maxFid = 0;
		
		while(allFids.hasNext()){
			if(allFids.next() > maxFid){
				maxFid = allFids.next();
			}
		}		
		return maxFid;
	}
	
	/**
	 * Return a form based on the formId.
	 * 
	 * @param formId the ID of a form
	 * @return all the data in a form saved in a Map<String, String> data type
	 * @throws TRAPException 
	 */
	private Map<String, String> getFormData(Integer formId) throws TRAPException{
		//Check whether or not the formDB is empty
		if(checkEmpty()){
			throw new TRAPException("getFormData(): the formDB is empty!!");
		}
		
		//Check whether or not the formId is valid
		Map<Integer, TravelFormMetadata> formList = uidDescription.get(currentUser);
		if(!formList.containsKey(formId)){
			throw new TRAPException("getFormData(): the formId is not exist in the uidDescription!!");
		}
		
		//fidMap holds all the forms saved by the current user
		HashMap<Integer, Map<String, String>> fidMap = new HashMap<Integer, Map<String, String>>();		
		fidMap.putAll(uidMap.get(currentUser));
		
		if(!fidMap.containsKey(formId)){
			throw new TRAPException("getFormData(): the formId is not exist in the uidMap!!");
		}else
			return fidMap.get(formId);
	}
	
	/**
	 * Check whether or the formDB is empty
	 * 
	 * @return true if the formDB is empty; otherwise the formDB is not empty
	 */
	private boolean checkEmpty(){
		if(uidDescription.isEmpty() || uidMap.isEmpty()){
			System.out.println("Is the uidMap empty!" + uidMap.isEmpty());
			System.out.println("Is the uidDescription empty!" + uidDescription.isEmpty());
			return true;
		}
		else{
			return false;
		}
	}
}
