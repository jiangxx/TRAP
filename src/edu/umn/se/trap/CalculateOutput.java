package edu.umn.se.trap;

import java.util.Map;
import java.util.List;
import edu.umn.se.trap.TravelFormProcessorIntf;
import edu.umn.se.trap.db.PerDiemDB;
import edu.umn.se.trap.db.PerDiemDB.RATE_FIELDS;
import edu.umn.se.trap.db.CurrencyDB;
import edu.umn.se.trap.db.CurrencyDB.CURRENCY_FIELDS;
import edu.umn.se.trap.db.KeyNotFoundException;

public class CalculateOutput 
{
	private TravelFormProcessorIntf intf;
	private Map<String, String> formData;
	private PerDiemDB perDiem;
	private CurrencyDB currency;
	private List<Double> perDiemInfo;
	private List<Double> currencyInfo;
	
	public void calculateOutput(Integer formId) throws Exception
	{
		formData = intf.getSavedFormData(formId);
		
		
	}
	
	

}
