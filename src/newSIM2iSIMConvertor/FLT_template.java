package newSIM2iSIMConvertor;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.DocumentException;
import org.w3c.dom.Document;

import com.flightatm.ats.parser.Parser;

public class FLT_template {
	
	public FLT_template( String reformattedMessage ) {
		
		inputMessage = reformattedMessage;
		myATSparser = new Parser();
		try {
			myDoc = myATSparser.parseMessageNoHeader(reformattedMessage);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		myDoc.getDocumentElement().normalize();

		myPathHelper = new XPathHelper(); 		
		
	   setMessageTITLE();
	   setARCID();
	   setSEQPT();
	   setCEQPT();
	   setSSRCODE();	
	   setFLTRUL();
	   setFLTTYP();
	   setNBARC();
	   setACRTYP();
	   setWKTRC();
	   setADEP();	
	   setADES();
	   setROUTE();
	   setEOBT();
	   setRFL();
	   setSPEED();
	   setREMARKS();
	   setTTLEET();
	   setSTPOINT();
	   setSTLEVEL();
	   setSID();
	   setSTAR();
	   setCOP();
	   setROUSEND();
	   
	}
	
	String inputMessage;
	Parser myATSparser;
	Document myDoc;
	XPathHelper myPathHelper;
	
	// F3 ‘a’ message TITLE field
	public String messageTITLE = "";
	
	// F7 ‘a’ flight indicator. A minimum of 2 alphanumerical characters and a maximum of 8 alphanumerical characters, compulsory
	public String ARCID = "";
	
	// F10 ‘b’ Surveillance Equipment. The descriptor 'N' or, either one or more of the	descriptors ‘I’, ‘P’, ‘X’, ‘A’,	‘C’ with ‘I’, ‘P’, ‘X’ being mutually exclusive
	public String SEQPT = "";
	
	// F10 ‘a’ Equipment Descriptor: One or a combination of the following equipment descriptors: D, G, I, O, S  
	public String CEQPT = "";
	
	// F7 ‘c’ SSR code '0000' to '7777'
	public String SSRCODE = "    ";
	
	// F8 ‘a’ Flight Rules, “I”: Instrumental,”V”: Visual,”Y”: Change IFR to VFR,”Z”: Change VFR to IFR, by default "I", compulsory
	public String FLTRUL = "";
	
	// F8 ‘b’ forecast aircraft type. 4 alphanumerical characters, compulsory
	public String FLTTYP = "";
	
	// F9 ‘a’ Number of aircraft if more than one [0-9][0-9]
	public String NBARC = "01";
	
	// F9 ‘b’ An ICAO designator of an aircraft type. 
	public String ACRTYP = "";
	
	// F9 ‘c’ Wake turbulence category "J": Jumbo, "H": Heavy, "M": Medium, "L": Light, compulsory
	public String WKTRC = "";
	
	// F13 ‘a’  four letter ICAO designator for an aerodrome. compulsory
	public String ADEP = "";
	
	// F16 ‘a’  four letter ICAO designator for an aerodrome. compulsory
	public String ADES = "";
	
	// F15 original route, sequence of up to 400 characters, compulsory
	public String ROUTE = "";
	
	// F13 ‘b’ estimated off-block time, hhmm, optional in incoming flights, compulsory in take-off flights
	public String EOBT = "";
	
	// individually parsed.....mama mia , Cruise level. Up to 3 numerical characters in hundreds of feet, compulsory.
	public String RFL = "";
	
	// cruise speed in knots Nxxx or Mach Mxxx, optional, automatic completion if left blank 
	public String SPEED = "";
	
	//F18 ‘rmk’ plain text remarks
	public String REMARKS = "";
	
	// XXX starting point of the simulated flight, 11 alphanumerical characters, optional, automatic completion if left blank 
	public String STPOINT = "";
	
	// XXX starting level of the simulated flight, in hundreds of feet, optional, automatic completion.
	public String STLEVEL = "";
	
	// XXX starting time of the simulated flight, hhmm, optional, automatic completion.
	public String STTIME = "";
	
	// F16 ‘b’ Total estimated elapsed time in hours and minutes.
	public String TTLEET = "";
	
	// XXX Standard Instrument Departure, if it is selected an ADEP that requires SID, then compulsory. 
	public String SID = "";
	
	// XXX Standard Arrival Route, if it is selected an ADES that requires STAR, then compulsory.
	public String STAR = "";
	
	// Frequency, 000.000 to 999.999 MHz, optional, automatic completion.
	public String FREQ = "100000";
	
	//  coordination point 11 alphanumerical characters, compulsory. 
	public String COP = "";
	
	public String ROUSEND = "";	
	public String SMODE = "";
	public String TRACK = "";
	public String PV = "";
	
	
	public String getMessageTITLE() {
		return messageTITLE;
	}
	public String getARCID() {
		return ARCID;
	}
	public String getSEQPT() {
		return SEQPT;
	}
	public String getCEQPT() {
		return CEQPT;
	}
	public String getSSRCODE() {
		return SSRCODE;
	}
	public String getFLTRUL() {
		return FLTRUL;
	}
	public String getFLTTYP() {
		return FLTTYP;
	}
	public String getNBARC() {
		return NBARC;
	}
	public String getACRTYP() {
		return ACRTYP;
	}
	public String getWKTRC() {
		return WKTRC;
	}
	public String getADEP() {
		return ADEP;
	}
	public String getADES() {
		return ADES;
	}
	public String getROUTE() {
		return ROUTE;
	}
	public String getEOBT() {
		return EOBT;
	}
	public String getRFL() {
		return RFL;
	}
	public String getSPEED() {
		return SPEED;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public String getSTPOINT() {
		return STPOINT;
	}
	public String getSTLEVEL() {
		return STLEVEL;
	}
	public String getSTTIME() {
		return STTIME;
	}
	public String getTTLEET() {
		return TTLEET;
	}
	public String getSID() {
		return SID;
	}
	public String getSTAR() {
		return STAR;
	}
	public String getFREQ() {
		return FREQ;
	}
	public String getCOP() {
		return COP;
	}
	public String getROUSEND() {
		return ROUSEND;
	}
	public String getSMODE() {
		return SMODE;
	}
	public String getTRACK() {
		return TRACK;
	}
	public String getPV() {
		return PV;
	}
	
	
	
	public void setMessageTITLE(String messageTITLE) {
		this.messageTITLE = messageTITLE;
	}
	public void setMessageTITLE() {
		try {
			this.messageTITLE = myPathHelper.getSubFieldData( myDoc, "F3", "a");
		}catch (Exception  e) {
		}
	}
	public void setARCID(String aRCID) {
		ARCID = aRCID;
	}
	public void setARCID() {
		try {
			ARCID = myPathHelper.getSubFieldData( myDoc, "F7", "a");
		}catch (Exception  e) {
		}
	}	
	public void setSEQPT(String sEQPT) {
		SEQPT = sEQPT;
	}
	public void setSEQPT() {
		try {
			SEQPT = myPathHelper.getSubFieldData( myDoc, "F10", "b");
		}catch (Exception  e) {
		}
	}
	public void setCEQPT(String cEQPT) {
		CEQPT = cEQPT;
	}
	public void setCEQPT() {
		try {
			CEQPT = myPathHelper.getSubFieldData( myDoc, "F10", "a");
		}catch (Exception  e) {
		}
	}
	public void setSSRCODE(String sSRCODE) {
		SSRCODE = sSRCODE;
	}
	public void setSSRCODE() {
		try {
			SSRCODE = myPathHelper.getSubFieldData( myDoc, "F7", "c");
		}catch (Exception  e) {
		}
	}
	public void setFLTRUL(String fLTRUL) {
		FLTRUL = fLTRUL;
	}
	public void setFLTRUL() {
		try {
			FLTRUL = myPathHelper.getSubFieldData( myDoc, "F8", "a");
		}catch (Exception  e) {
		}
	}
	public void setFLTTYP(String fLTTYP) {
		FLTTYP = fLTTYP;
	}
	public void setFLTTYP() {
		try {
			FLTTYP = myPathHelper.getSubFieldData( myDoc, "F8", "b");
		}catch (Exception  e) {
		}
	}
	public void setNBARC(String nBARC) {
		NBARC = nBARC;
	}
	public void setNBARC() {
		try {
			NBARC = myPathHelper.getSubFieldData( myDoc, "F9", "a");
		}catch (Exception  e) {
		}
	}
	public void setACRTYP(String aCRTYP) {
		ACRTYP = aCRTYP;
	}
	public void setACRTYP() {
		try {
			ACRTYP = myPathHelper.getSubFieldData( myDoc, "F9", "b");
		}catch (Exception  e) {
		}
	}
	public void setWKTRC(String wKTRC) {
		WKTRC = wKTRC;
	}
	public void setWKTRC() {
		try {
			WKTRC = myPathHelper.getSubFieldData( myDoc, "F9", "c");
		}catch (Exception  e) {
		}
	}
	public void setADEP(String aDEP) {
		ADEP = aDEP;
	}
	public void setADEP() {
		try {
			ADEP = myPathHelper.getSubFieldData( myDoc, "F13", "a");
		}catch (Exception  e) {
		}
	}
	public void setADES(String aDES) {
		ADES = aDES;
	}
	public void setADES() {
		try {
			ADES = myPathHelper.getSubFieldData( myDoc, "F16", "a");
		}catch (Exception  e) {
		}
	}
	public void setROUTE(String rOUTE) {
		ROUTE = rOUTE;
	}
	public void setROUTE() {
		try {
			ROUTE = myPathHelper.getFieldData( myDoc, "F15");
			ROUTE = ROUTE.replaceAll("\\s+", " ").trim();
		}catch (Exception  e) {
		}
		//for NEWSIM export as .txt
		String foundRFLSPEED = "";  //-N0450F310 
		String firstStringinROUTE = ROUTE.substring(0,10);
		Pattern r = Pattern.compile("N(\\d{4})(F|A)(\\d{3})");
		Matcher m = r.matcher( firstStringinROUTE );
		while (m.find()) {	    	   
		  if ( m.group(0) != null) {
		    foundRFLSPEED =  m.group(0) ;
		  }
		}
		
		ROUTE = ROUTE.replace(foundRFLSPEED, "");
		ROUTE = ROUTE.replaceAll("\\s+", " ").trim();
		//check if the first point in the ROUTE is a SID
		String parts[] = ROUTE.split(" ");
		if (parts[0].contains(parts[1])){
			ROUTE = ROUTE.replace( parts[0] , "");
			ROUTE = ROUTE.replaceAll("\\s+", " ").trim();
			SID = parts[0]; 
		}
		//check if the last element in the ROUTE is a STAR
		parts = null;
		parts = ROUTE.split(" ");
		int lastIndex = parts.length - 1 ;
		if (parts[lastIndex].contains(parts[lastIndex-1])){
			STAR = parts[lastIndex];
			ROUTE = ROUTE.replace( parts[lastIndex] , "");
			ROUTE = ROUTE.replaceAll("\\s+", " ").trim();
			
		}
		

	}
	public void setEOBT(String eOBT) {
		EOBT = eOBT;
	}
	public void setEOBT() {
		try {
			EOBT = myPathHelper.getSubFieldData( myDoc, "F13", "b");
		}catch (Exception  e) {
		}
	}
	public void setRFL(String rFL) {
		RFL = rFL;
	}
	public void setRFL() {
		try {
			RFL = myPathHelper.getFieldData( myDoc, "RFL");
		}catch (Exception  e) {
		}
		if (RFL == "") { //for NEWSIM export as XML
			
			String foundRFL = "";       
			Pattern r = Pattern.compile("-RFL (F|A)(\\d{3})(\\n|\\s+-)");
			Matcher m = r.matcher( inputMessage );

			while (m.find()) {	    	   
	    	   if ( m.group(2) != null) {
	    		   foundRFL =  m.group(2) ;
	    	   }
	       }	
			RFL = foundRFL;			
		}
		
		//for NEWSIM export as .txt
		String myROUTEaux = "";
		try {
			myROUTEaux = myPathHelper.getFieldData( myDoc, "F15");
			myROUTEaux = myROUTEaux.replaceAll("\\s+", " ").trim();
		}catch (Exception  e) {
		}					
		String foundRFL = "";  //-N0450F310 
		String firstStringinROUTE = myROUTEaux.substring(0,10);
		Pattern r = Pattern.compile("N(\\d{4})(F|A)(\\d{3})");
		Matcher m = r.matcher( firstStringinROUTE );

		while (m.find()) {	    	   
    	   if ( m.group(3) != null) {
    		   foundRFL =  m.group(3) ;
    	   }
		}		
		RFL = foundRFL;		
	}
	public void setSPEED(String sPEED) {
		SPEED = sPEED;
	}
	public void setSPEED() {
		try {
			SPEED = myPathHelper.getFieldData( myDoc, "SPEED");
		}catch (Exception  e) {
		}
		if (SPEED == "") {
			
			String foundSPEED = "";       
			Pattern r = Pattern.compile("-SPEED (N|M)(\\d{4})(\\n|\\s+-)");
			Matcher m = r.matcher( inputMessage );

			while (m.find()) {	    	   
	    	   if ( m.group(1) != null) {
	    		   foundSPEED =  m.group(2) ;
	    	   }
	       }	
			SPEED = foundSPEED;			
		}
		//for NEWSIM export as .txt
		String myROUTEaux = "";
		try {
			myROUTEaux = myPathHelper.getFieldData( myDoc, "F15");
			myROUTEaux = myROUTEaux.replaceAll("\\s+", " ").trim();
		}catch (Exception  e) {
		}					
		String foundSPEED = "";  //-N0450F310 
		String firstStringinROUTE = myROUTEaux.substring(0,10);
		Pattern r = Pattern.compile("N(\\d{4})(F|A)(\\d{3})");
		Matcher m = r.matcher( firstStringinROUTE );

		while (m.find()) {	    	   
		  if ( m.group(1) != null) {
		    foundSPEED =  m.group(1) ;
		  }
		}		
		SPEED = foundSPEED;		

	}	
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public void setREMARKS() {
		try {
			REMARKS = myPathHelper.getSubFieldData( myDoc, "F18", "rmk");
		}catch (Exception  e) {
		}
	}
	
	public void setSTPOINT(String sTPOINT) {
		STPOINT = sTPOINT;
	}
	public void setSTPOINT() {
		
		String ROUTEaux = "";
		try {
			ROUTEaux = myPathHelper.getFieldData( myDoc, "F15");
			ROUTEaux = ROUTEaux.replaceAll("\\s+", " ").trim();
		}catch (Exception  e) {
		}
		//for NEWSIM export as .txt
		String foundRFLSPEED = "";  //-N0450F310 
		String firstStringinROUTE = ROUTEaux.substring(0,10);
		Pattern r = Pattern.compile("N(\\d{4})(F|A)(\\d{3})");
		Matcher m = r.matcher( firstStringinROUTE );
		while (m.find()) {	    	   
		  if ( m.group(0) != null) {
		    foundRFLSPEED =  m.group(0) ;
		  }
		}
		
		ROUTEaux = ROUTEaux.replace(foundRFLSPEED, "");
		ROUTEaux = ROUTEaux.replaceAll("\\s+", " ").trim();
		//check if the first point in the ROUTE is a SID
		String parts[] = ROUTEaux.split(" ");
		if (parts[0].contains(parts[1])){
			ROUTEaux = ROUTEaux.replace( parts[0] , "");
			ROUTEaux = ROUTEaux.replaceAll("\\s+", " ").trim();
		}
		String partsAux[] = ROUTEaux.split(" ");
		if ( partsAux[0].equals("T") ^ partsAux[0].equals("DCT") ) {
			STPOINT = partsAux[1];
		}else {
			STPOINT = partsAux[0];
		}
		
	}
	public void setSTLEVEL(String sTLEVEL) {
		STLEVEL = sTLEVEL;		
	}
	public void setSTLEVEL() {
		STLEVEL = this.getRFL();
		/*if ( SID != "") {
			STLEVEL = "080";			
		}*/
	}
	public void setSTTIME(String sTTIME) {
		STTIME = sTTIME;
	}
	public void setTTLEET(String tTLEET) {
		TTLEET = tTLEET;
	}
	public void setTTLEET() {
		try {
			TTLEET = myPathHelper.getSubFieldData( myDoc, "F16", "b");
		}catch (Exception  e) {
		}
	}
	public void setSID(String sID) {
		SID = sID;
	}
	public void setSID() {
		try {
			SID = myPathHelper.getFieldData( myDoc, "SID");
		}catch (Exception  e) {
		}
		if (SID == "") {
			
			String foundSID = "";       
			Pattern r = Pattern.compile("-SID (\\w{2,7})(\\n|\\s+-)");
			Matcher m = r.matcher( inputMessage );

			while (m.find()) {	    	   
	    	   if ( m.group(1) != null) {
	    		   foundSID =  m.group(1) ;
	    	   }
	       }	
			SID = foundSID;			
		}		
	}	
	public void setSTAR(String sTAR) {
		STAR = sTAR;
	}
	public void setSTAR() {
		try {
			STAR = myPathHelper.getFieldData( myDoc, "STAR");
		}catch (Exception  e) {
		}
		if (STAR == "") {
			
			String foundSTAR = "";       
			Pattern r = Pattern.compile("-STAR (\\w{2,7})(\\n|\\s+-)");
			Matcher m = r.matcher( inputMessage );

			while (m.find()) {	    	   
	    	   if ( m.group(1) != null) {
	    		   foundSTAR =  m.group(1) ;
	    	   }
	       }	
			STAR = foundSTAR;			
		}		
	}
	public void setFREQ(String fREQ) {
		FREQ = fREQ;
	}
	public void setCOP(String cOP) {
		COP = cOP;
	}
	public void setCOP() {

		String ROUTEaux = "";
		try {
			ROUTEaux = myPathHelper.getFieldData( myDoc, "F15");
			ROUTEaux = ROUTEaux.replaceAll("\\s+", " ").trim();
		}catch (Exception  e) {
		}
		//for NEWSIM export as .txt
		String foundRFLSPEED = "";  //-N0450F310 
		String firstStringinROUTE = ROUTEaux.substring(0,10);
		Pattern r = Pattern.compile("N(\\d{4})(F|A)(\\d{3})");
		Matcher m = r.matcher( firstStringinROUTE );
		while (m.find()) {	    	   
		  if ( m.group(0) != null) {
		    foundRFLSPEED =  m.group(0) ;
		  }
		}
		
		ROUTEaux = ROUTEaux.replace(foundRFLSPEED, "");
		ROUTEaux = ROUTEaux.replaceAll("\\s+", " ").trim();
		//check if the first point in the ROUTE is a SID
		String parts[] = ROUTEaux.split(" ");
		if (parts[0].contains(parts[1])){
			ROUTEaux = ROUTEaux.replace( parts[0] , "");
			ROUTEaux = ROUTEaux.replaceAll("\\s+", " ").trim();
		}
		String partsAux[] = ROUTEaux.split(" ");
		if ( partsAux[0].equals("T") ^ partsAux[0].equals("DCT") ) {
			COP = partsAux[1];
		}else {
			COP = partsAux[0];
		}
		
	}
	public void setROUSEND(String rOUSEND) {
		ROUSEND = rOUSEND;
	}
	public void setROUSEND() {
		//ROUSEND = rOUSEND;
		try {
			ROUSEND  = myPathHelper.getFieldData( myDoc, "F15");
			ROUSEND = ROUSEND .replaceAll("\\s+", " ").trim();
		}catch (Exception  e) {
		}
		//check if the first point in the ROUTE is a SID
		String parts[] = ROUSEND.split(" ");
		if (parts[1].contains(parts[2])){
			ROUSEND = ROUSEND.replace( parts[1] , "");
			ROUSEND = ROUSEND.replaceAll("\\s+", " ").trim();
		}
	}
	public void setSMODE(String sMODE) {
		SMODE = sMODE;
	}
	public void setTRACK(String tRACK) {
		TRACK = tRACK;
	}
	public void setPV(String pV) {
		PV = pV;
	}
	
	public String toString() {
		String stringifyObj = "";
		
		stringifyObj += "-ARCID:>" + this.getARCID() + "$\n";
		stringifyObj += "-SEQPT:>" + this.getSEQPT() + "$\n";
		stringifyObj += "-CEQPT:>" + this.getCEQPT() + "$\n";
		stringifyObj += "-SSRCODE:>" + this.getSSRCODE() + "$\n";
		stringifyObj += "-FLTRUL:>" + this.getFLTRUL() + "$\n";
		stringifyObj += "-FLTTYP:>" + this.getFLTTYP() + "$\n";
		stringifyObj += "-NBARC:>" + this.getNBARC() + "$\n";
		stringifyObj += "-ACRTYP:>" + this.getACRTYP() + "$\n";
		stringifyObj += "-ACRSUBTYP:> $\n";
		stringifyObj += "-WKTRC:>" + this.getWKTRC() + "$\n";
		stringifyObj += "-ADEP:>" + this.getADEP() + "$\n";
		stringifyObj += "-ADES:>" + this.getADES() + "$\n";
		stringifyObj += "-ROUTE:>" + this.getROUTE() + "$\n";
		stringifyObj += "-EOBT:>" + this.getEOBT()+ "$\n";
		stringifyObj += "-RFL:>" + this.getRFL() + "$\n";
		stringifyObj += "-SPEED:>" + this.getSPEED() + "$\n";
		stringifyObj += "-REMARKS:>" + this.getREMARKS() + "$\n";
		stringifyObj += "-TTLEET:>" + this.getTTLEET() + "$\n";
		stringifyObj += "-STPOINT:>"  + this.getSTPOINT() + "$\n";
		stringifyObj += "-STLEVEL:>" + this.getSTLEVEL() + "$\n";
		stringifyObj += "-STTIME:>$\n";		
		stringifyObj += "-SID:>" + this.getSID() + "$\n";
		stringifyObj += "-STAR:>" + this.getSTAR() + "$\n";
		stringifyObj += "-FREQ:>" + this.getFREQ() + "$\n";
		stringifyObj += "-COP:>" + this.getCOP() + "$\n";
		stringifyObj += "-ROUSEND:>" + this.getROUSEND() + "$\n";
		stringifyObj += "-SMODE:>Y$\n";
		stringifyObj += "-TRACK:>Y$\n";
		stringifyObj += "-PV:>Y$\n";
		
		return stringifyObj;
	}
	
	public String toCSV() {
		/* source: User Manual iSIM v1.A 15.12.2017, Document Number: 4021200209001MA00
		 * Edit the following fields:
			-	CALLSIGN: flight callsign. A minimum of 2 alphanumerical characters and a maximum of 8 alphanumerical characters, compulsory.
			-	SSRC (SSR code '0000' to '7777', optional).
			-	R (Flight rules: ”I”: Instrumental, ”V”: Visual, ”Y”: Change from IFR to VFR, ”Z”: Change from VFR to IFR, by default ”I”, compulsory, automatically completed).
			-	T (Flight type: ”S”: Regular, ”N”: Not regular, ”G”: General, ”M”: Military, ”X”: Any other, by default ”S”, compulsory, automatically completed).
			-	N (number of aircraft, 2 numerical characters or blanks, optional, if left blank it is automatically completed with 01).
			-	TYPE (Type of aircraft. 4 alphanumerical characters, compulsory).
			-	WK: Wake: "J": Super heavy, "H": Heavy, "M": Medium, "L": Light, compulsory.
			-	NCA. E (NCA equipment, maximum of 64 alphanumerical characters of which 25 are visible. If the content of the field exceeds 25, the ">>" symbol will be shown next to the title of the field to indicate that the content is greater than displayed. It is optional.). 
			-	SSR. E (SSR equipment, maximum of 20 alphanumerical characters of which 15 are visible. If the content of the field exceeds 15, the ">>" symbol will be shown next to the title of the field to indicate that the content is greater than displayed. It is optional.). 
			-	ADEP (Origin Airport, 4 alphanumerical characters, compulsory).
			-	EOBT (estimated off-block time, 4 numerical characters in hhmm format).
			-	ETOT (estimated take-off time, 4 numerical characters in hhmm format).
			-	ADES (Destination Airport, 4 alphanumerical characters, compulsory).
			-	ELDT (estimated landing time, 4 numerical characters in hhmm format).
			-	W (RVSM equipment "S": Equipped, "N": Not equipped, "X": Exempt, by default "S", compulsory, automatically completed).
			-	Y (833 equipment, "S": Equipped, "N": Not equipped, "X": Exempt, by default "S", compulsory, automatically completed).
			-	SPEED. (Cruise speed: it can be expressed in Knots Nxxx or Mach Mxxx, compulsory, if left blank it is completed with the standard for the type of aircraft).
			-	RFL (Cruise level. Up to 3 numerical characters in hundreds of feet, compulsory).
			-	SID (Take-off Procedure, 3 to 10 alphanumerical characters).
			-	STAR (Arrival Procedure, 3 to 10 alphanumerical characters).
			-	FIELD 18: (Other Information of the flight plan, maximum of 400 alphanumerical characters of which 120 are visible). If the content of the field exceeds 120, the ">>" symbol will be shown next to the title of the field to indicate that the content is greater than displayed, optional).
			-	ROUTE (Original route, sequence of up to 400 characters, compulsory).
			-	ROUTE COP: coordination route, sequence of up to 400 characters of which 80 are visible, If the content of the field exceeds 80, the ">>" symbol will be shown next to the title of the field to indicate that the content is greater than displayed.
			-	COP: coordination point 11 alphanumerical characters, compulsory. 
			-	COPFL: coordination flight level in hundreds of feet, compulsory.
			-	COPT: coordination time, hhmm, compulsory.
			-	S. POINT.: start point of the simulated flight, 11 alphanumerical characters, optional, automatic completion.
			-	S. TIME: start time of the simulated flight, hhmm, optional, automatic completion.
			-	S. LEVEL/AFL: start level of the simulated flight, in hundreds of feet, optional, automatic completion.
			-	PILOTFREQ: Frequency, 000.000 to 999.999 MHz, optional, automatic completion.
			-	A TYPE: actual aircraft type, 4 alphanumerical characters, compulsory.
			-	REGISTRATION: registration of the flight, 7 alphanumerical characters, optional, automatic completion.
			-	MODE S CODE: 24-bit flight direction ICAO identifier. Up to 8 digits in octal format. The entered value must be unique in each session, automatic completion.
			-	MODE S TYPE: identifier of the aircraft. It displays three selectable elements: Callsign (by default), Registration and Blank. The field is accessible when the Mode S indicator is activated.
			-	MODE S CALL: indicator of the Mode S. A minimum of 2 alphanumerical characters and a maximum of 9 alphanumerical characters, optional, automatic completion.
			Select or deselect the following fields (by default they are all selected, except overflight):
			-	FLIGHT (it indicates whether the associated flight is to be created).
			-	ACT AUT (it indicates whether the activation of the flight plan is sent to the FPD).
			-	OVERFLIGHT (it indicates whether the pass is with overflight of the fix or navigation by area).
			-	SDL (indicator of the activation status of the Data Link Communications of the aircraft). 
			-	SSR AUT (it indicates whether the SSR code is assigned to the flight automatically).
			-	EXT R (it indicates whether to respond to messages from an Adjacent center).
			-	FP (it indicates whether the creation of the associated flight plan is sent to the FDP).
			-	M S (indicator of the activation status of the Mode S of the aircraft). 

		 * */
		//CALLSIGN;SSRC;R;T;N;;;;NCA E.;SSR E.;ADEP;;EOBT;ADES;W;Y;SPEED;RFL;SID;STAR;ROUTE;FIELD 18;;;;;;;;PILOTFREQ;A. TYPE;;MODE S TYPE;;;;;;;;;;

		String stringifyObj = "";
		
		stringifyObj += this.getARCID() + ";";
		stringifyObj += this.getSSRCODE() + ";";
		stringifyObj += this.getFLTRUL() + ";";
		stringifyObj += this.getFLTTYP() + ";";
		stringifyObj += this.getNBARC() + ";";
		stringifyObj += this.getACRTYP() + ";";
		stringifyObj += this.getWKTRC() + ";";
		stringifyObj += ";";
		stringifyObj += this.getCEQPT() + ";";
		stringifyObj += this.getSEQPT() + ";";
		stringifyObj += this.getADEP() + ";";	
		stringifyObj += ";";
		stringifyObj += this.getEOBT()+ ";";
		stringifyObj += this.getADES() + ";";
		stringifyObj += "S;";
		stringifyObj += "S;";  
		stringifyObj += this.getSPEED() + ";";
		stringifyObj += this.getRFL()+ ";";
		stringifyObj += this.getSID() + ";";
		stringifyObj += this.getSTAR() + ";";
		stringifyObj += this.getROUTE() + ";";
		stringifyObj += this.getREMARKS() + ";";
		stringifyObj += ";;;;;;;";
		stringifyObj += this.getFREQ() + ";";
		stringifyObj += this.getACRTYP() + ";";
		stringifyObj += ";";
		stringifyObj += "C;";
		stringifyObj += ";;Y;Y;N;Y;Y;Y;Y;Y";
		
		return stringifyObj;
	}
	
	
	
}
