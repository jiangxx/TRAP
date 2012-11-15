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
 * Mimics the currency conversion database.
 * 
 * @author ggay
 *
 */
public class CurrencyDB
{
    /**
     * This type enumerates the fields of the {@link ArrayList} with the conversion 
     * information.
     */
    public static enum CURRENCY_FIELDS
    {
        CURRENCY,               /* Currency to be converted */
        DATE,               	/* Date of conversion */
    };
    
    Map<List<String>, Double> currencyInfo = new HashMap<List<String>, Double>();
    
    /**
     * Constructor.  Sets up the object.
     */
    public CurrencyDB()
    {
        ArrayList<String> currency = new ArrayList<String>();
        currency.add("eur"); 			/* Currency to be converted */
        currency.add("20121009");		/* Date */
        Double value=1.5;				/* USD value of 1 unit of that currency 
         									(i.e. 1 EURO = 1.5 USD)*/
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121010");
        value=1.50;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121011");
        value=1.51;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121012");
        value=1.60;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121013");
        value=1.58;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121014");
        value=1.56;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121015");
        value=1.50;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121016");
        value=1.70;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121017");
        value=1.69;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121018");
        value=1.68;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121019");
        value=1.68;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121020");
        value=1.69;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121021");
        value=1.75;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121022");
        value=1.70;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121023");
        value=1.69;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121024");
        value=1.50;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121025");
        value=1.50;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121026");
        value=1.51;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121027");
        value=1.51;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121028");
        value=1.50;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121029");
        value=1.55;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121030");
        value=1.50;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121031");
        value=1.60;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121101");
        value=2.00;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121102");
        value=2.01;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121103");
        value=2.00;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121104");
        value=1.98;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121105");
        value=1.97;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121106");
        value=1.95;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121107");
        value=1.80;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121108");
        value=1.70;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121109");
        value=1.71;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121110");
        value=1.69;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121111");
        value=1.69;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121112");
        value=1.71;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121113");
        value=1.69;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121114");
        value=1.65;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121115");
        value=1.66;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121116");
        value=1.75;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121117");
        value=1.75;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121118");
        value=2.00;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121119");
        value=2.10;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121120");
        value=2.09;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121121");
        value=2.10;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121122");
        value=2.08;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121123");
        value=2.05;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121124");
        value=2.00;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121125");
        value=1.98;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121126");
        value=1.99;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121127");
        value=1.89;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121128");
        value=1.80;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121129");
        value=1.81;
        this.currencyInfo.put(currency, value);
        currency.set(CURRENCY_FIELDS.DATE.ordinal(), "20121130");
        value=1.80;
        this.currencyInfo.put(currency, value);
    }
    
    /**
     * Gets the USD value of a currency given a date.
     * @param currency  the currency to be converted.
     * @param date the date of the transaction
     * @return  The USD value for one unit of the specified currency 
     * 			on the given date
     * @throws KeyNotFoundException  if the specified currency or date could not be 
     *      found in the database.
     */
    public Double getConversion(String currency, String date) throws KeyNotFoundException
    {
    	ArrayList<String> conversion = new ArrayList<String>();
    	conversion.add(currency.toLowerCase());
    	conversion.add(date);
    	Double value = this.currencyInfo.get(conversion);
        if(value == null)
        {
            throw new KeyNotFoundException("Could not find either currency, " + 
                    currency +
                    " or date " + date + " in currency DB.");
        }
        return value;
    }
}
