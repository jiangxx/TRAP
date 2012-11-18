package edu.umn.se.trap;

import java.util.Map;
import java.util.List;
import edu.umn.se.trap.TravelFormProcessorIntf;
import edu.umn.se.trap.db.PerDiemDB;
import edu.umn.se.trap.db.PerDiemDB.RATE_FIELDS;
import edu.umn.se.trap.db.CurrencyDB;
import edu.umn.se.trap.db.CurrencyDB.CURRENCY_FIELDS;
import edu.umn.se.trap.db.KeyNotFoundException;

public class CheckRulePerDiem 
{
	private TravelFormProcessorIntf intf;
	private Map<String, String> formData;
	private PerDiemDB perDiem;
	private CurrencyDB currency;
	private List<Double> perDiemInfo;
	private List<Double> currencyInfo;
	
	public void checkIncidentalPerDiem(Integer formId) throws Exception 
	{
		formData = intf.getSavedFormData(formId);
		//Gets Domestic Incidental Per Diem
		if(formData.get(DAY_INCIDENTAL_COUNTRY) == "united states")
		{
			if(formData.get(DAY_INCIDENTAL_STATE) != null)
			{
				if(formData.get(DAY_INCIDENTAL_CITY) != null)
				{
					perDiemInfo = perDiem.getDomesticPerDiem(formData.get(DAY_INCIDENTAL_CITY), formData.get(DAY_INCIDENTAL_STATE));
				}
			}
			else
			{
				perDiemInfo = perDiem.getDomesticPerDiem(formData.get(DAY_INCIDENTAL_STATE));
			}
			
			if(formData.get(DAY_INCIDENTAL_AMOUNT) > perDiemInfo.get(RATE_FIELDS.INCIDENTAL_CEILING.ordinal()))
			{
				throw new Exception(formData.get(DAY) +" Amount of incidental is more than the ceiling.\n");
			}
		}
		
		//Gets International Incidental Per Diem
		
		else
		{
			if(formData.get(DAY_INCIDENTAL_CITY) != null)
			{
				perDiemInfo = perDiem.getInternationalPerDiem(formData.get(DAY_INCIDENTAL_CITY), formData.get(DAY_INCIDENTAL_COUNTRY));
				currencyInfo = currency.getConversion(formData.get(DAY_INCIDENTAL_CURRENCY), date);
			}
			else
			{
				perDiemInfo = perDiem.getInternationalPerDiem(formData.get(DAY_INCIDENTAL_COUNTRY));
			}
			
			if(formData.get(DAY_INCIDENTAL_AMOUNT) * currencyInfo.get(CURRENCY_FIELDS.CURRENCY.ordinal()) > perDiemInfo.get(RATE_FIELDS.INCIDENTAL_CEILING.ordinal()))
			{
				throw new Exception(formData.get(DAY) +" Amount of incidental is more than the ceiling.\n");
			}
		}
	}
		
	public void checkLodgingPerDiem(Integer formId)
	{
		formData = intf.getSavedFormData(formId);
		//Gets Domestic Lodging Per Diem
		if(formData.get(DAY_LODGING_COUNTRY) == "united states")
		{
			if(formData.get(DAY_LODGING_STATE) != null)
			{
				if(formData.get(DAY_LODGING_CITY) != null)
				{
					perDiemInfo = perDiem.getDomesticPerDiem(formData.get(DAY_LODGING_CITY), formData.get(DAY_LODGING_STATE));
				}
				else
				{
					perDiemInfo = perDiem.getDomesticPerDiem(formData.get(DAY_LODGING_STATE));
				}
			}
			if(formData.get(DAY_LODGING_AMOUNT) > perDiemInfo.get(RATE_FIELDS.LODGING_CEILING.ordinal()))
			{
				throw new Exception(formData.get(DAY) +" Amount of lodging is more than the ceiling.\n");
			}
		//Gets International Lodging Per Diem
		}
		else
		{
			if(formData.get(DAY_LODGING_CITY) != null)
			{
				perDiemInfo = perDiem.getInternationalPerDiem(formData.get(DAY_LODGING_CITY), formData.get(DAY_LODGING_COUNTRY));
				currencyInfo = currency.getConversion(formData.get(DAY_LODGING_CURRENCY), date);
			}
			else
			{
				perDiemInfo = perDiem.getInternationalPerDiem(formData.get(DAY_LODGING_COUNTRY));
			}
			if(formData.get(DAY_LODGING_AMOUNT) * currencyInfo.get(CURRENCY_FIELDS.CURRENCY.ordinal()) > perDiemInfo.get(RATE_FIELDS.LODGING_CEILING.ordinal()))
			{
				throw new Exception(formData.get(DAY) +" Amount of lodging is more than the ceiling.\n");
			}
		}
	}
}
