package newSIM2iSIMConvertor;

import java.util.Hashtable;

public class ExternalInterfaces {

	public Hashtable<String, String> hashTable = new Hashtable<String, String>();
	
	public ExternalInterfaces() {
		hashTable.put("PR", "LKAA");
	    hashTable.put("VV", "LOVV");
	    hashTable.put("SZ", "LSAZ");
	    hashTable.put("WA", "EPWW");
	    hashTable.put("LP", "LIPP");
	    hashTable.put("MC", "EDYY");
	    hashTable.put("DW", "EDWW");
	    hashTable.put("R", "EDUU");
	    hashTable.put("EDGG", "EDGG");
	    //ADEXPs TOWERs 			       
	    hashTable.put("TDM", "EDDM");
	    hashTable.put("TDP", "EDDP");
	    hashTable.put("TDC", "EDDC");
	    hashTable.put("TDN", "EDDN");
	    hashTable.put("TDE", "EDDE");
	    
	    //ICAO IDENTIFIER
	    hashTable.put("EDGGZQZX", "EDGG");
	    hashTable.put("EDMMZQZX", "EDMM");
	    hashTable.put("EDUUZRFA", "EDUU");
	    hashTable.put("EDWWZQZX", "EDWW");
	    hashTable.put("EDYYZQZX", "EDYY");
	    hashTable.put("EPWAZQZX", "EPWW");			       
	    hashTable.put("LIPPZQZX", "LIPP");
	    hashTable.put("LKAAZQZX", "LKAA");
	    hashTable.put("LOVVZQZX", "LOVV");
	    hashTable.put("LSAZZQZX", "LSAZ");
	    //QUESTIONABLE ONES
	    hashTable.put("EUCHZMFP", "AFTN");
	    hashTable.put("TDH", "EDDH");
	}
}
