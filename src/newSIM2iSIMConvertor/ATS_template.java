package newSIM2iSIMConvertor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.w3c.dom.Element;

public class ATS_template extends NewSIM2iSIMConvertor {
	
	public Element eElement;
	public String reformattedMessage;
		
	public String SOURCE;
	public String TEXT;
	public String CONDITIONS;
		
	public ATS_template(String myReformattedMessage, Element myElement) {

		reformattedMessage = myReformattedMessage;
		eElement = myElement;
		setSOURCE();
		setTEXT();
		setCONDITIONS();
	}
	
	public String getSOURCE() {
		return SOURCE;
	}
	public void setSOURCE(String sOURCE) {
		SOURCE = sOURCE;
	}
	public void setSOURCE() {
		
		String iSIMSource = eElement.getAttribute("sender");			       
		if ( extInt.hashTable.containsKey( iSIMSource ) ) {
			SOURCE = (String) extInt.hashTable.get(iSIMSource);
		}else {
			System.out.println("DEBUG::: setSOURCE ::: sender not found in extInt.hashTable");
			SOURCE = "    ";
		}	
		
	}
	public String getTEXT() {
		return TEXT;
	}
	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}
	public void setTEXT() {
		//TEXT = thisNodeContent.replaceAll("\\s+", " ").trim() ;
		TEXT = reformattedMessage.trim()  ;
	}	
	public String getCONDITIONS() {
		return CONDITIONS;
	}
	public void setCONDITIONS(String cONDITIONS) {
		CONDITIONS = cONDITIONS;
	}
	public void setCONDITIONS() {
		
		LocalDateTime timeOffset = LocalDateTime.of(2018, 1, 1, 0, 0);
		String givenTimeOffset = eElement.getAttribute("time");
		LocalDateTime timeAdjusted;
		timeAdjusted = timeOffset.plusHours( Long.parseLong( givenTimeOffset.substring(0, 2) ) );
		timeAdjusted = timeAdjusted.plusMinutes( Long.parseLong( givenTimeOffset.substring(3, 5) ) );
	       
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("m");	       
		String time2set = dtf.format( timeAdjusted );
		CONDITIONS = "[CLOCK]=[INITIAL_SESSION_TIME]+" + time2set;
		
	}
	
	public String toString() {
		String stringifyObj = "";
		
		stringifyObj += "-SOURCE:>" + this.getSOURCE() + "$\n";
		stringifyObj += "-TEXT:>" + this.getTEXT() + "$\n";
		stringifyObj += "-CONDITIONS:>" + this.getCONDITIONS() + "$\n";
		
		
		return stringifyObj;
	}


}
