package edu.umn.se.trap;

import java.util.List;
import java.util.Map;
import edu.umn.se.*;
import edu.umn.se.trap.db.GrantDB;
import edu.umn.se.trap.db.GrantDB.GRANT_FIELDS;
import edu.umn.se.trap.db.KeyNotFoundException;
import edu.umn.se.trap.db.UserGrantDB;

public class CheckRuleDODFund {
	private TravelFormProcessorIntf intf;
	private UserGrantDB userGrantDB;
	private GrantDB grantDB;
	private Map<String, String> formData;
	private List<String> userGrantInfo;
	private List<Object> grantInfo;
	private int i=0;
	public void checkRuleDODFund(int formId) throws KeyNotFoundException {
		formData = intf.getSavedFormData(formId);
		for(i=0;i<formData.get(NUM_GRANTS);i++) {
			userGrantInfo = userGrantDB.getUserGrantInfo(formData.get(GRANT1_ACCOUNT));
			if(userGrantInfo.contains(formData.get(USER_NAME)==false){
				throw new KeyNotFoundException("User " + formData.get(USER_NAME) +" is not supported by" +
						" account number "+formData.get(GRANT1_ACCOUNT) + ".\n");
			}
			grantInfo = grantDB.getGrantInfo(formData.get(GRANT1_ACCOUNT));
			if(grantInfo.get(GRANT_FIELDS.ACCOUNT_TYPE.ordinal())=="DOD"){
				
			}
		}
		
	}
}
