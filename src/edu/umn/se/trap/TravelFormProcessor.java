package edu.umn.se.trap;

import java.util.HashMap;
import java.util.Map;

public class TravelFormProcessor implements TravelFormProcessorIntf{
   
private static FormDB db = new FormDB();		
		
	/**
     * An enumeration representing the form's status.
     */
    public static enum FORM_STATUS 
    {   
        DRAFT, /* The form has not passed the submission checks.*/       
        SUBMITTED /*The form has passed all checked required for submission of the form.*/
    };
    
    /**
     * Sets the user id (X500) of the user working on a form.
     * @param userId  the X500 id of the user working on a travel reimbursement
     *      form.
     * @throws Exception  if the user id is invalid.  SEE NOTE IN CLASS HEADER.
     */
	public void setUser(String userId) throws TRAPException {
		FormDB.currentUser = userId;
	}

    /**
     * Gets the user id of the user currently working on forms.
     * @return  the X500 user id of the user currently working on travel 
     *      reimbursement forms.
     */
	public String getUser(){
		return FormDB.currentUser;				
	}	

    /**
     * Gets a list of currently saved form data for the set user.
     * @return  a map containing the integer identifier of for each form in the
     *      system belonging to the current user as the key and the travel 
     *      form's metadata.
     * @see {@link TravelFormMetadata} for more information.
     */
	public Map<Integer, TravelFormMetadata> getSavedForms() throws TRAPException{
		Map<Integer, TravelFormMetadata> formlist = new HashMap<Integer, TravelFormMetadata>();		
		formlist.putAll(db.getSavedFormList()) ;		
		return formlist;
	}

    /**
     * Clears the entire collection of saved form data from the database in 
     * which they were stored.  This includes submitted and completed forms 
     * regardless of which user is set.
     * @throws Exception  if the collection of saved forms could not be cleared.
     */
	public void clearSavedForms() throws TRAPException{
		db.clearSavedForm();
	}

    /**
     * Gets the saved form data for a given saved form.
     * @param formId  the identifier of the saved form data to get.
     * @return  the saved form data.
     * @throws Exception  if the form data could not be retrieved.  SEE NOTE IN 
     *      CLASS HEADER.
     */
	public Map<String, String> getSavedFormData(Integer formId)throws TRAPException{
		Map<String, String> formData = new HashMap<String, String>();
		formData.putAll(db.getSavedFormdata(formId));
		return formData;
	}

	/**
     * Gets a completed form.
     * @param formId  the id of a submitted (checked) form.
     * @return  the completed form as a mapping of string keys to string values.
     * @throws Exception  if the specified form has not been completed (passed 
     *      submission).  SEE NOTE IN CLASS HEADER.
     */
	public Map<String, String> getCompletedForm(Integer formId) throws TRAPException
	{
		Map<String, String> completeForm = new HashMap<String, String>();
		completeForm.putAll(db.getCompletedForm(formId));
		return completeForm;
	}
	
	/**
     * Saves a new set of form data for later.  This does not check the form 
     *      data, only that no invalid keys have been submitted.
     * @param formData  the data for the form
     * @param description  a description of the form data to save.
     * @return  the form data's identifier.
     * @throws Exception  if the form data could not be saved or the map 
     *      contained invalid keys.  SEE NOTE IN CLASS HEADER.
     */
	public Integer saveFormData(Map<String, String> formData, String description)throws TRAPException
	{
		Integer FormId;
		FormId = db.saveFormData(formData, description);
		return FormId;
	}

	/**
     * Saves a form into a specific form id, overwriting any information 
     *      already saved under that id.  This does not check the form 
     *      data, only that no invalid keys have been submitted.
     * @param formData  the form data to save
     * @param id  the identifier to save the form data under.  This must be in 
     *      the list of saved forms with an existing description.
     * @return  the form data's identifier
     * @throws Exception  if the form data could not be saved or the map 
     *      contained invalid keys.  SEE NOTE IN CLASS HEADER.
     */
	public Integer saveFormData(Map<String, String> formData, Integer id) throws TRAPException
	{
		Integer FormId;
		FormId = db.overwriteFormData(formData, id);
		return FormId;
	}
		
	/**
     * Save the completed form data and check the form data for formatting 
     * errors and process the data in terms of the business rules.
     * @param formId  the id of the saved form data to submit.
     * @throws Exception  if there is an error in the data formatting or if the 
     *      data violates one of the business rules.  SEE NOTE IN CLASS HEADER.
     */
	public void submitFormData(Integer formId) throws TRAPException
	{
		
		
	}

}
