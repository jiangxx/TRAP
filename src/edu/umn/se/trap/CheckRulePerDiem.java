package edu.umn.se.trap;

import java.util.Map;
import java.util.List;
import edu.umn.se.trap.TravelFormProcessorIntf;
import edu.umn.se.trap.db.PerDiemDB;
import edu.umn.se.trap.db.PerDiemDB.RATE_FIELDS;
import edu.umn.se.trap.db.CurrencyDB;

public class CheckRulePerDiem 
{
	private TravelFormProcessorIntf intf;
	private Map<String, String> formData;
	private PerDiemDB perDiem;
	private CurrencyDB currency;
	private List<Double> perDiemInfo;
	private Double currencyInfo;
	private int i;
	
	public void checkIncidentalPerDiem(Integer formId) throws Exception 
	{
		formData = intf.getSavedFormData(formId);
		//Gets Domestic Incidental Per Diem
		for(i=0;i<Integer.parseInt(formData.get("NUM_DAYS"));i++)
			if(formData.get("DAY"+i+"_INCIDENTAL_COUNTRY") == "united states")
			{
				if(formData.get("DAY"+i+"_INCIDENTAL_STATE") != null)
				{
					if(formData.get("DAY"+i+"_INCIDENTAL_CITY") != null)
					{
						perDiemInfo = perDiem.getDomesticPerDiem(formData.get("DAY"+i+"_INCIDENTAL_CITY"), formData.get("DAY"+i+"_INCIDENTAL_STATE"));
					}
				}
				else
				{
					perDiemInfo = perDiem.getDomesticPerDiem(formData.get("DAY"+i+"_INCIDENTAL_STATE"));
				}
				
				if(Double.parseDouble(formData.get("DAY"+i+"_INCIDENTAL_AMOUNT")) > perDiemInfo.get(RATE_FIELDS.INCIDENTAL_CEILING.ordinal()))
				{
					throw new Exception("Incidental Amount of day "+ formData.get("DAY"+i+"_DATE") +" is more than the ceiling.\n " +
							"Please input less than $"+formData.get("DAY"+i+"_INCIDENTAL_AMOUNT")+".\n");
				}
			}
			
			//Gets International Incidental Per Diem
			
			else
			{
				if(formData.get("DAY"+i+"_INCIDENTAL_CITY") != null)
				{
					perDiemInfo = perDiem.getInternationalPerDiem(formData.get("DAY"+i+"_INCIDENTAL_CITY"), formData.get("DAY"+i+"_INCIDENTAL_COUNTRY"));
					currencyInfo = currency.getConversion(formData.get("DAY"+i+"_INCIDENTAL_CURRENCY"), "DAY"+i+"_DATE");
				}
				else
				{
					perDiemInfo = perDiem.getInternationalPerDiem(formData.get("DAY"+i+"_INCIDENTAL_COUNTRY"));
				}
				
				if(Double.parseDouble(formData.get("DAY"+i+"_INCIDENTAL_AMOUNT")) * currencyInfo > perDiemInfo.get(RATE_FIELDS.INCIDENTAL_CEILING.ordinal()))
				{
					throw new Exception("Incidental Amount of day "+ formData.get("DAY"+i+"_DATE") +" is more than the ceiling.\n " +
							"Please input less than $"+formData.get("DAY"+i+"_INCIDENTAL_AMOUNT")+".\n");
				}
			}
	}
		
	public void checkLodgingPerDiem(Integer formId) throws Exception
	{
		formData = intf.getSavedFormData(formId);
		//Gets Domestic Lodging Per Diem
		if(formData.get("DAY"+i+"_LODGING_COUNTRY") == "united states")
		{
			if(formData.get("DAY"+i+"_LODGING_STATE") != null)
			{
				if(formData.get("DAY"+i+"_LODGING_CITY") != null)
				{
					perDiemInfo = perDiem.getDomesticPerDiem(formData.get("DAY"+i+"_LODGING_CITY"), formData.get("DAY"+i+"_LODGING_STATE"));
				}
				else
				{
					perDiemInfo = perDiem.getDomesticPerDiem(formData.get("DAY"+i+"_LODGING_STATE"));
				}
			}
			if(Double.parseDouble(formData.get("DAY"+i+"_LODGING_AMOUNT")) > perDiemInfo.get(RATE_FIELDS.LODGING_CEILING.ordinal()))
			{
				throw new Exception("Lodging Amount of day "+ formData.get("DAY"+i+"_DATE") +" is more than the ceiling.\n " +
						"Please input less than $"+formData.get("DAY"+i+"_LODGING_AMOUNT")+".\n");
			}
		//Gets International Lodging Per Diem
		}
		else
		{
			if(formData.get("DAY"+i+"_LODGING_CITY") != null)
			{
				perDiemInfo = perDiem.getInternationalPerDiem(formData.get("DAY"+i+"_LODGING_CITY"), formData.get("DAY"+i+"_LODGING_COUNTRY"));
				currencyInfo = currency.getConversion(formData.get("DAY"+i+"_LODGING_CURRENCY"), "DAY"+i+"_DATE");
			}
			else
			{
				perDiemInfo = perDiem.getInternationalPerDiem(formData.get("DAY"+i+"_LODGING_COUNTRY"));
			}
			if(Double.parseDouble(formData.get("DAY"+i+"_LODGING_AMOUNT")) * currencyInfo > perDiemInfo.get(RATE_FIELDS.LODGING_CEILING.ordinal()))
			{
				throw new Exception("Lodging Amount of day "+ formData.get("DAY"+i+"_DATE") +" is more than the ceiling.\n " +
						"Please input less than $"+formData.get("DAY"+i+"_LODGING_AMOUNT")+".\n");
			}
		}
	}
}
