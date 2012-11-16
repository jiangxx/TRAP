package edu.umn.se.trap;

import java.util.List;
import java.util.Map;
import edu.umn.se.*;
import edu.umn.se.trap.db.GrantDB;
import edu.umn.se.trap.db.UserGrantDB;

public class CheckRuleDODFund {
	private TravelFormProcessorIntf intf;
	private UserGrantDB userGrantDB;
	private GrantDB grantDB;
	private Map<String, String> formData;
	private List<String> userGrantInfo;
	private List<Object> grantInfo;
	public void checkRuleDODFund(int formId) throws Exception {
		formData = intf.getSavedFormData(formId);
		userGrantInfo = userGrantDB.getUserGrantInfo(formData.get(GRANT1_ACCOUNT));
		grantInfo = grantDB.getGrantInfo(formData.get(GRANT1_ACCOUNT));
	}
}
