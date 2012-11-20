package edu.umn.se.trap;

import java.util.Map;
import java.util.List;
import edu.umn.se.trap.TravelFormProcessorIntf;
import edu.umn.se.trap.db.KeyNotFoundException;
import edu.umn.se.trap.db.GrantDB;
import edu.umn.se.trap.db.GrantDB.GRANT_FIELDS;

public class CheckRuleTotalAmount 
{
	private TravelFormProcessorIntf intf;
	private Map<String, String> formData;
	private List<Object> grantInfo;
	private GrantDB grantDB;
	
	public void checkRuleTotalAmount(Integer formId) throws Exception
	{
		int i = 0;
		formData = intf.getSavedFormData(formId);
		for(i=0;i<Integer.parseInt(formData.get("NUM_GRANTS"));i++) 
		{
			grantInfo = grantDB.getGrantInfo(formData.get("GRANT_ACCOUNT"));
			if(grantInfo.contains(formData.get("USER_NAME")== null))
			{
				throw new KeyNotFoundException("User " + formData.get("USER_NAME") +" is not supported by" +
						" account number "+formData.get("GRANT_ACCOUNT") + ".\n");
			}
		//	if(Double.parseDouble(grantInfo.get(GRANT_FIELDS.ACCOUNT_BALANCE.ordinal())) > Double.parseDouble(formData.get("GRANT_AMOUNT_TO_CHARGE")))
			//	throw new Exception("Total reimbursement is more than the grant amount.\n");
		}
	}
}


