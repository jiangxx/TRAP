/**
 * Copyright (c) 2012, Ian De Silva, Gregory Gay
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 * - Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice, 
 *   this list of conditions and the following disclaimer in the documentation 
 *   and/or other materials provided with the distribution.
 * - Neither the name of the University of Minnesota nor the names of its 
 *   contributors may be used to endorse or promote products derived from this 
 *   software without specific prior written permission.
 *   
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package edu.umn.se.trap.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Mimics the database of meal/incidental per diems.
 * 
 * @author ggay
 *
 */
public class PerDiemDB
{
	/**
     * This type enumerates the fields of the {@link ArrayList} with the  
     * per diem information.
     */
    public static enum RATE_FIELDS
    {
        BREAKFAST_RATE,        /* Breakfast rate in USD */
        LUNCH_RATE,            /* Lunch rate in USD */
        DINNER_RATE,           /* Dinner rate in USD */
        INCIDENTAL_CEILING,       /* Incidental ceiling in USD */
        LODGING_CEILING;		/* Lodging ceiling in USD */
    };
    
    Map<List<String>, List<Double>> perDiemInfo = new HashMap<List<String>, List<Double>>();
    
    /**
     * Constructor.  Sets up the object.
     */
    public PerDiemDB()
    {
    	/*Example 1: Domestic */
    	
    	ArrayList<String> location = new ArrayList<String>();
        ArrayList<Double> rates = new ArrayList<Double>();
        location.add("minneapolis"); 	/* City. */
        location.add("minnesota"); 		/* State. Only applicable for domestic travel. */
        location.add("united states"); 	/* Country */
        rates.add(7.0); 				/* Breakfast rate in USD */
        rates.add(12.0); 				/* Lunch rate in USD */
        rates.add(26.0); 				/* Dinner rate in USD */
        rates.add(5.0); 				/* Incidental ceiling in USD */
        rates.add(150.0);				/* Lodging ceiling in USD */
        
        this.perDiemInfo.put(location,rates);
        
        /*Example 2: International, specific city */
        
        location = new ArrayList<String>();
        rates = new ArrayList<Double>();
        location.add("zurich"); 		/* City */ 
        location.add(null); 			/* State. Only applicable for domestic */
        location.add("switzerland"); 	/* Country */
        rates.add(12.0); 				/* Breakfast rate in USD */
        rates.add(25.0); 				/* Lunch rate in USD */
        rates.add(50.0); 				/* Dinner rate in USD */
        rates.add(20.0); 				/* Incidental ceiling in USD */
        rates.add(225.0);				/* Lodging ceiling in USD */
        
        this.perDiemInfo.put(location,rates);
        
        /*Example 3: International, country rate */
        
        location = new ArrayList<String>();
        rates = new ArrayList<Double>();
        location.add(null); 			/* City */
        location.add(null); 			/* State. Only applicable for domestic travel */
        location.add("switzerland"); 	/* Country */
        rates.add(10.0); 				/* Breakfast rate in USD */
        rates.add(20.0); 				/* Lunch rate in USD */
        rates.add(40.0); 				/* Dinner rate in USD */
        rates.add(20.0);    			/* Incidental ceiling in USD */
        rates.add(250.0);				/* Hotel ceiling in USD */
        
        this.perDiemInfo.put(location,rates);
    }
    
    
    /**
     * Gets domestic per diem rates as a list of strings.
     * @param city the name of the city where a meal was purchased.
     * @param state the state where the meal was purchased.
     * @return  a list containing the per diem rates, broken down by type.  
     * 		This contains breakfast, lunch, dinner, and incidentals - 
     *		all in USD. A null or empty list returned from this method 
     *		should be treated as an invalid city/state pairing.
     * @throws KeyNotFoundException  if the specified city/state pairing 
     * 		could not be found in the database.
     */
    public List<Double> getDomesticPerDiem(String city, String state) throws KeyNotFoundException
    {
    	ArrayList<String> location = new ArrayList<String>();
    	location.add(city.toLowerCase());
    	location.add(state.toLowerCase());
    	location.add("united states");
        List<Double> rateInfo = this.perDiemInfo.get(location);
        if(rateInfo == null)
        {
            throw new KeyNotFoundException("Could not find city, " + city +
                    ", in the database.");
        }
        return rateInfo;
    }
    
    /**
     * Gets domestic per diem rates as a list of strings.
     * @param state the state where the meal was purchased.
     * @return  a list containing the per diem rates, broken down by type.  
     * 		This contains breakfast, lunch, dinner, incidentals, and lodging - 
     *		all in USD. A null or empty list returned from this method 
     *		should be treated as an invalid state input.  The 
     *      {@link RATE_FIELDS} enum is provided to help you index the list.
     * @throws KeyNotFoundException  if the specified state could not be found 
     * 		in the database.
     */
    public List<Double> getDomesticPerDiem(String state) throws KeyNotFoundException
    {
    	ArrayList<String> location = new ArrayList<String>();
    	location.add(null);
    	location.add(state.toLowerCase());
    	location.add("united states");
        List<Double> rateInfo = this.perDiemInfo.get(location);
        if(rateInfo == null)
        {
            throw new KeyNotFoundException("Could not find state, " + state +
                    ", in the database.");
        }
        return rateInfo;
    }
    
    /**
     * Gets international per diem rates as a list of strings.
     * @param city the name of the city where a meal was purchased.
     * @param country the country where the meal was purchased.
     * @return  a list containing the per diem rates, broken down by type.  
     * 		This contains breakfast, lunch, dinner, incidentals, and lodging - 
     *		all in USD. A null or empty list returned from this method 
     *		should be treated as an invalid city/country pairing.  The 
     *      {@link RATE_FIELDS} enum is provided to help you index the list.
     * @throws KeyNotFoundException  if the specified city/country pairing 
     * 		could not be found in the database.
     */
    public List<Double> getInternationalPerDiem(String city, String country) 
            throws KeyNotFoundException
    {
    	ArrayList<String> location = new ArrayList<String>();
    	location.add(city.toLowerCase());
    	location.add(null);
    	location.add(country.toLowerCase());
        List<Double> rateInfo = this.perDiemInfo.get(location);
        if(rateInfo == null)
        {
            throw new KeyNotFoundException("Could not find city, " + city +
                    ", in the database.");
        }
        return rateInfo;
    }
    
    /**
     * Gets international per diem rates as a list of strings.
     * @param country the country where the meal was purchased.
     * @return  a list containing the per diem rates, broken down by type.  
     * 		This contains breakfast, lunch, dinner, and incidentals - 
     *		all in USD. A null or empty list returned from this method 
     *		should be treated as an invalid country input.  The 
     *      {@link RATE_FIELDS} enum is provided to help you index the list.
     * @throws KeyNotFoundException  if the specified country could not be found 
     * 		in the database.
     */
    public List<Double> getInternationalPerDiem(String country) 
            throws KeyNotFoundException
    {
    	ArrayList<String> location = new ArrayList<String>();
    	location.add(null);
    	location.add(null);
    	location.add(country.toLowerCase());
        List<Double> rateInfo = this.perDiemInfo.get(location);
        if(rateInfo == null)
        {
            throw new KeyNotFoundException("Could not find country, " + country +
                    ", in the database.");
        }
        return rateInfo;
    }
}
