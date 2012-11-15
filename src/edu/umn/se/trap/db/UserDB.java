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
 * Mimics the X500 database.
 * 
 * @author desilva
 *
 */
public class UserDB
{
	/**
     * This type enumerates the fields of the {@link ArrayList} with the user 
     * information.
     */
    public static enum USER_FIELDS
    {
        USER_NAME,                 /* X500 user name */
        FULL_NAME,                 /* Full name of user */
        EMAIL,           			/* E-mail address of user */
        EMPLOYEE_ID,              /* ID number */
        CITIZENSHIP,				/*Citizenship of the user*/
        VISA_STATUS,				/* US visa status if not a US citizenship */
        PAID_BY_UNIVERSITY			/* Are they paid by the university? */
    };
    
    Map<String, List<String>> userInfo = new HashMap<String, List<String>>();
    
    /**
     * Constructor.  Sets up the object.
     */
    public UserDB()
    {
        ArrayList<String> user = new ArrayList<String>();
        user.add("linc001");
        user.add("Lincoln, Abraham");
        user.add("linc001@umn.edu");
        user.add("1849304");
        user.add("United States");
        user.add(null);
        user.add("Yes");
        
        this.userInfo.put(user.get(USER_FIELDS.USER_NAME.ordinal()), user);
    }
    
    
    /**
     * Gets the user's information as a list of strings.
     * @param userName  the X500 user id of the person to be retrieved from the
     *      system.
     * @return  a list containing the user's infomation.  This contains the
     *      X500 id, user's real name (in Last, First (MI.) form), the user's 
     *      e-mail address, and the user's employee id number.  A null or empty
     *      list returned from this method should be treated as an invalid user 
     *      name.
     * @throws KeyNotFoundException  if the specified user name could not be 
     *      found in the database.
     */
    public List<String> getUserInfo(String userName) throws KeyNotFoundException
    {
        List<String> userInfo = this.userInfo.get(userName.toLowerCase());
        if(userInfo == null)
        {
            throw new KeyNotFoundException("Could not find user, " + userName +
                    ", in user DB.");
        }
        return userInfo;
    }
}
