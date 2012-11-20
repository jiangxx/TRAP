package edu.umn.se.trap;

public class TRAPException extends Exception{

	/**
	 * 
	 * @author group17
	 */
	private static final long serialVersionUID = 1L;
	
	public TRAPException(String message){
		super(message);
	}
	
	public TRAPException(String message, Throwable t){
		super(message, t);
	}

}
