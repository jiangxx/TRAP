package edu.umn.se.trap;

import java.util.Map;
import edu.umn.se.trap.TravelFormProcessorIntf;

public class CalculateOutput 
{
	private TravelFormProcessorIntf intf;
	private Map<String, String> formData;
	private double dayTotal;
	private double transportationTotal;
	private double otherTotal;
	private double totalReimbursement;
	private int i;
	private Object day;
	private Object transportation;
	private Object other;
	
	public void calculateTotal(Integer formId) throws Exception
	{
		formData = intf.getSavedFormData(formId);
		dayTotal = 0; transportationTotal = 0; otherTotal = 0; totalReimbursement = 0;
		
		for(i=0;i<Integer.parseInt(formData.get("NUM_DAYS"));i++)
		{
			day = "DAY" + i + "_TOTAL";
			dayTotal += Double.parseDouble(formData.get(day));
			day = null;
		}
		for(i=0;i<Integer.parseInt(formData.get("NUM_TRANSPORTATION"));i++)
		{
			transportation = "TRANSPORTATION" + i + "_TOTAL";
			transportationTotal += Double.parseDouble(formData.get(transportation));
			transportation = null;
		}
		for(i=0;i<Integer.parseInt(formData.get("NUM_OTHER_EXPENSES"));i++)
		{
			other = "OTHER" + i + "_TOTAL";
			otherTotal += Double.parseDouble(formData.get(other));
			other = null;
		}
		totalReimbursement = dayTotal + transportationTotal + otherTotal;
		formData.put("TOTAL_REIMBURSEMENT", Double.toString(totalReimbursement));	
	}
}