package edu.umn.se.trap;

import java.util.HashMap;
import java.util.Map;

public class TravelFormProcessor 
{
	public void setUser(String userId) throws Exception
	{		
		FormDB.currentUser = new String(userId);
	}

	public String getUser()
	{
		String user = new String(FormDB.currentUser);
		return user;				
	}	

	public Map<Integer, TravelFormMetadata> getSavedForms() throws Exception
	{
		Map<Integer, TravelFormMetadata> formlist = new HashMap<Integer, TravelFormMetadata>();
		formlist.putAll(FormDB.getsavedform()) ;		
		return formlist;
	}

	public void clearSavedForms() throws Exception
	{
		FormDB.clearsavedform();
	}

	public Map<String, String> getSavedFormData(Integer formId)throws Exception
	{
		Map<String, String> formData = new HashMap<String, String>();
		formData.putAll(FormDB.getsavedformdata(formId));
		return formData;
	}

	public Map<String, String> getCompletedForm(Integer formId) throws Exception
	{
		Map<String, String> completeForm = new HashMap<String, String>();
		completeForm.putAll(FormDB.getcompletedform(formId));
		return completeForm;
	}
	
	public Integer saveFormData(Map<String, String> formData, String description)throws Exception
	{
		Integer FormId;
		FormId = FormDB.saveformdata(formData, description);
		return FormId;
	}

	public Integer saveFormData(Map<String, String> formData, Integer id) throws Exception
	{
		Integer FormId;
		FormId = FormDB.overwriteformdata(formData, id);
		return FormId;
	}
	
	public void submitFormData(Integer formId) throws Exception
	{
		
		
	}


}
