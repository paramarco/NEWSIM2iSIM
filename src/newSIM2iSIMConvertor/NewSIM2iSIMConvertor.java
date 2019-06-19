/**
 * 
 */
package newSIM2iSIMConvertor;

/**
 * @author mvereda
 *
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.time.format.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.IOException;


public class NewSIM2iSIMConvertor {
	
	static int SEQNUM = 1;
	static int SEQNUMGlobal = 1;	
    static String outputPath = "scenario_in_iSIM_import_format";
    public ExternalInterfaces extInt = new ExternalInterfaces();
    
 	
	public static NodeList getXMLnodes (File inputFile) {
	    NodeList nList = null;
        try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName("message");			
        }catch (Exception e) {
        	e.printStackTrace();
		}
 
		return nList;
		
	}
	
	public static String replaceSender (String thisNodeContent, Element  eElement) {
		  
		Pattern r = Pattern.compile("!\\(%A\\)");
		Matcher m = r.matcher(thisNodeContent);
		if (m.find()) {
			thisNodeContent = thisNodeContent.replace(m.group(0) , eElement.getAttribute("sender") );
		}		
		return thisNodeContent;
	}
	
	public static String replaceReceiver (String thisNodeContent, Element  eElement) {
		  
		Pattern r = Pattern.compile("!\\(%B\\)");
	    Matcher  m = r.matcher(thisNodeContent);
	    if (m.find()) {
	    	thisNodeContent = thisNodeContent.replace(m.group(0) , eElement.getAttribute("receiver") );
	    }	
		return thisNodeContent;
	}
	
	public static String replaceSEQNUM (String thisNodeContent, Element  eElement) {

	   Pattern r = Pattern.compile("!\\(%#\\)");
	   Matcher m = r.matcher(thisNodeContent);
	   if (m.find()) {
		thisNodeContent = thisNodeContent.replace(m.group(0) , "00" + String.valueOf(SEQNUM) );
	   }	
	   return thisNodeContent;
	}
	
	public static String replaceTIMEwithVariable (String thisNodeContent, Element  eElement) {

	       Pattern r = Pattern.compile("!\\(%H%M((\\+|\\-)\\d+)*\\)");
	       Matcher m = r.matcher(thisNodeContent);
	       String time2set = "";       
       
	       while (m.find()) {
	    	   
	    	   if ( m.group(1) != null) {
	    		  time2set = m.group(1);
	    	   }
	    	   time2set = "*EOBT:>[CLOCK]" + time2set + "*";
	    	   thisNodeContent = thisNodeContent.replace( m.group(0) , time2set );
	       }	
		   return thisNodeContent;
	}
	
	public static String replaceTIMEwithConstant (String thisNodeContent, Element  eElement) {

		String time2set = "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
		String givenTimeOffset = eElement.getAttribute("time");
		LocalDateTime initialTime = LocalDateTime.of(2018, 1, 1, 0, 0);
		LocalDateTime	timeAdjusted = initialTime.plusHours( Long.parseLong( givenTimeOffset.substring(0, 2) ) );
       					timeAdjusted = timeAdjusted.plusMinutes( Long.parseLong( givenTimeOffset.substring(3, 5) ) );
       
	   Pattern r = Pattern.compile("!\\(%H%M((\\+|\\-)\\d+)*\\)");
       Matcher m = r.matcher(thisNodeContent);

       while (m.find()) {
    	   
    	   if ( m.group(1) != null) {
    		  timeAdjusted = timeAdjusted.plusMinutes( Long.parseLong( m.group(1) ) );
    	   }
    	   time2set =  dtf.format( timeAdjusted ); 
    	   thisNodeContent = thisNodeContent.replace( m.group(0) , time2set );
       }	
	   return thisNodeContent;
	}
	
	public static String replaceTXT_TIME_withConstants (String inputMsg) {

		String outputMsg = inputMsg;
		
		Pattern r = Pattern.compile("%TIME\\/HHMM\\/((-)*(\\d{4}))%");
		Matcher m = r.matcher(inputMsg);

		while (m.find()) {    	   
			if ( m.group(1) != null) {
				outputMsg = inputMsg.replace( m.group(0) , m.group(3));	
			}
		}
		return outputMsg;
	}

	
	
	public static String replaceDATEwithVariable (String thisNodeContent, Element  eElement) {

		String date2set = "";
		Pattern r = Pattern.compile("!\\(%Y%L%D((\\+|\\-)\\d+)*\\)");
		Matcher m = r.matcher(thisNodeContent);
	       			       
       while (m.find()) {
	       
    	   if ( m.group(1) != null) {
    		 date2set = m.group(1);
    	   }
    	   date2set = "*EOBD:>[DATE]" + date2set + "*";
    	   thisNodeContent = thisNodeContent.replace( m.group(0) , date2set );
       }
	       
       r = Pattern.compile("!\\(%Y\\)!\\(%L\\)!\\(%D((\\+|\\-)\\d+)*\\)");
       m = r.matcher(thisNodeContent);
	       			       
       while (m.find()) {
	       
    	   if ( m.group(1) != null) {
    		 date2set = m.group(1);
    	   }
    	   date2set = "*EOBD:>[DATE]" + date2set + "*";
    	   thisNodeContent = thisNodeContent.replace( m.group(0) , date2set );
       }    
	       
       return thisNodeContent;

	}
	
	public static String replaceDATEwithConstant (String thisNodeContent, Element  eElement) {
		
		String date2set = "";      
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");

       dtf = DateTimeFormatter.ofPattern("yyMMdd");
       LocalDateTime currentDate = LocalDateTime.now();
       
       Pattern r = Pattern.compile("!\\(%Y%L%D((\\+|\\-)\\d+)*\\)");
       Matcher m = r.matcher(thisNodeContent);
       			       
       while (m.find()) {
	       
    	   if ( m.group(1) != null) {
    		 currentDate = currentDate.plusDays( Long.parseLong( m.group(1) ) );
    	   }
    	   date2set = dtf.format( currentDate );
    	   thisNodeContent = thisNodeContent.replace( m.group(0) , date2set );
       }
       
       r = Pattern.compile("!\\(%Y\\)!\\(%L\\)!\\(%D((\\+|\\-)\\d+)*\\)");
       m = r.matcher(thisNodeContent);
       			       
       while (m.find()) {
	       
    	   if ( m.group(1) != null) {
    		 currentDate = currentDate.plusDays( Long.parseLong( m.group(1) ) );
    	   }
    	   date2set = dtf.format( currentDate );
    	   thisNodeContent = thisNodeContent.replace( m.group(0) , date2set );
       }    
       
	   return thisNodeContent;
	}

	
	public static File createfile ( File inputFile , String namePrefix , String fileExtension) {

		String parentsName = inputFile.getParentFile().getName();
		parentsName = outputPath + "/" + parentsName;

		new File(parentsName).mkdirs();
		
		File FileOutput = null;
		String fileSeqNum = String.valueOf(SEQNUM);
		String outputFileName ;
		while ( fileSeqNum.length() < 4 ) {
			fileSeqNum = "0" + fileSeqNum;
		}
		outputFileName = namePrefix + fileSeqNum + fileExtension;
	       
		try {
			FileOutput = new File( parentsName + "/" + outputFileName );
			if ( FileOutput.exists() ) {
				FileOutput.delete();
				FileOutput.createNewFile();
			}			
	    }catch (Exception e) {
        	e.printStackTrace();
		}
		return FileOutput;
	}
	
	public static void write2File ( File FileOutput, String text ) {
		PrintStream out;
		try {
			out = new PrintStream (new FileOutputStream( FileOutput.getAbsolutePath() , true ));
			out.println(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void copyfile2mainDirectory(File source, String namePrefix ) throws IOException {
		
		String fileSeqNum = String.valueOf(SEQNUMGlobal);
		String outputFileName ;
		while ( fileSeqNum.length() < 4 ) {
			fileSeqNum = "0" + fileSeqNum;
		}
		outputFileName = outputPath + "/" + namePrefix + fileSeqNum + ".txt";
		File dest = new File(outputFileName);
		Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
	}

	public static void createDESfile (File inputFile) {
		
		String parentsName = inputFile.getParentFile().getName();
		parentsName = outputPath + "/" + parentsName;

		new File(parentsName).mkdirs();
		
		PrintStream out = null;
		String outputFileName = "DES_TABLE.txt";
	       
		try {
			 out = new PrintStream (new FileOutputStream( parentsName + "/" + outputFileName ));
			 out.println("000000");
			 out.println("scenario: " + inputFile.getParentFile().getName());

	    }catch (Exception e) {
        	e.printStackTrace();
		}
		
	}
	
	public static String replaceXMLVariable2iSIMVariables ( Element eElement ) {
		
		String thisNodeContent = eElement.getTextContent();		
		thisNodeContent = replaceSender(thisNodeContent, eElement);
		thisNodeContent = replaceReceiver(thisNodeContent, eElement);
		thisNodeContent = replaceSEQNUM(thisNodeContent, eElement);
		thisNodeContent = replaceTIMEwithVariable(thisNodeContent, eElement);
		thisNodeContent = replaceDATEwithVariable(thisNodeContent, eElement);
		return thisNodeContent;
	}
	
	public static String replaceXMLVariables2Constants ( Element eElement ) {
		
		String thisNodeContent = eElement.getTextContent();		
		thisNodeContent = replaceSender(thisNodeContent, eElement);
		thisNodeContent = replaceReceiver(thisNodeContent, eElement);
		thisNodeContent = replaceSEQNUM(thisNodeContent, eElement);
		thisNodeContent = replaceTIMEwithConstant(thisNodeContent, eElement);
		thisNodeContent = replaceDATEwithConstant(thisNodeContent, eElement);
		return thisNodeContent;
	}
	
	public static String replaceTXTVariable2Constants ( String inputMsg ) {
		
		String outputMsg = replaceTXT_TIME_withConstants( inputMsg );
		return outputMsg;
	}
	
	public static void processXMLfile (File inputFile) {
		
        SEQNUM = 1;       
        try {
    	    NodeList nList = getXMLnodes(inputFile);
 
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);				
			    
			    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			       Element eElement = (Element) nNode;
			       
			       System.out.println("\n\n ############################################################# ");
			       System.out.println("file from NEWSIM: " + inputFile.getAbsolutePath() );        
			       			       
			       String reformattedMessage = replaceXMLVariable2iSIMVariables( eElement );	       
	       			       
			       File fileATS = createfile( inputFile, "ATS", ".txt" ); 
				   ATS_template myATStemplate =  new ATS_template( reformattedMessage , eElement );
				   write2File( fileATS, myATStemplate.toString() );
				   copyfile2mainDirectory( fileATS, "ATS");
				   
				   reformattedMessage = replaceXMLVariables2Constants( eElement );					   
				   
				   File fileFLT = createfile( inputFile, "FLT", ".txt"  );
				   FLT_template myFLTtemplate =  new FLT_template( reformattedMessage );
				   if ( myFLTtemplate.getMessageTITLE().contains("FPL") ) {
					   write2File( fileFLT, myFLTtemplate.toString() );
					   copyfile2mainDirectory( fileFLT, "FLT");
				   }
				   
				   System.out.println("------------------------------------------------------------------------------- ");
				   //System.out.println("FLT file :\n" + myFLTtemplate.toString() );
				   System.out.println("------------------------------------------------------------------------------- ");
				     
				   SEQNUM++;
				   SEQNUMGlobal++;				   
			    }
			}
		} catch (Exception e) {
		e.printStackTrace();
		}		
	}
	
	public static List<String> getTXTBlocks ( File inputFile ) {
		
		List<String> blockList = new ArrayList<String>();
        try {
        	
			String fileCharacters = new String(Files.readAllBytes(inputFile.toPath()));
			
			Pattern r = Pattern.compile("BLOCK_BEGIN((.|\\n)*?)BLOCK_END");
			Matcher m = r.matcher( fileCharacters );

			while (m.find()) {	    	   
	    	   if ( m.group(1) != null) {
	    		   String foundBlock =  m.group(1) ;
	    		   blockList.add( foundBlock );
	    		   System.out.println( foundBlock );
	    	   }
	       }			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        

		return blockList;
	}
	
	public static String getFPLfromBlock (String Block) {
		
		Pattern r = Pattern.compile("\\(FPL(.|\\n)*?\\)");
		Matcher m = r.matcher( Block );
		String foundFPL = "" ;
		
		while (m.find()) {	    	   
    	   if ( m.group(0) != null) {
    		   foundFPL =  m.group(0) ;
    	   }
       }			
		return foundFPL;
	}
	
	public static void processTXTfile (File inputFile) {
		
        SEQNUM = 1;
        File fileCSV = createfile( inputFile, "IMPORT" , ".csv");
        
        try {
        	List<String> blockList = getTXTBlocks(inputFile);
 
			for ( String block : blockList ) {
			       
			       System.out.println("\n\n ############################################################# ");
			       System.out.println("file from NEWSIM: " + inputFile.getAbsolutePath() );
			       System.out.println(" the FPL message:" +  getFPLfromBlock(block) );        
			       		        
			              
			       String reformattedMessage = replaceTXTVariable2Constants ( getFPLfromBlock(block) );	       

				   File fileFLT = createfile( inputFile, "FLT" , ".txt");
				   FLT_template myFLTtemplate =  new FLT_template( reformattedMessage );
				   write2File( fileFLT, myFLTtemplate.toString() );
				   write2File( fileCSV, myFLTtemplate.toCSV() );
				   copyfile2mainDirectory( fileFLT, "FLT");
				   				   
				   System.out.println("------------------------------------------------------------------------------- ");
				   System.out.println("FLT file :\n" + myFLTtemplate.toString() );
				   System.out.println("------------------------------------------------------------------------------- ");
				   
				   SEQNUM++;
				   SEQNUMGlobal++;				   
			   
			}
		} catch (Exception e) {
		e.printStackTrace();
		}		
	}
	
	public static void processXMLfilesFromNEWSIM ( String directory ) {
		
		Filewalker fw = new Filewalker( directory );
		List<File> xmlList = fw.getXMLfilelist();
		
		for ( File f : xmlList ) {		
			processXMLfile(f);
			createDESfile(f);
		}		
	}
	
	public static void processTXTfilesFromNEWSIM ( String directory ) {
		
		Filewalker fw = new Filewalker( directory );
		List<File> txtList = fw.getTXTfilelist();
		
		for ( File f : txtList ) {		
			processTXTfile(f);
			createDESfile(f);
		}		
	}
	
	
	public static File createFLTfile ( ) {

		File FileOutput = null;
		String fileSeqNum = String.valueOf(SEQNUM);
		String outputFileName ;
		while ( fileSeqNum.length() < 4 ) {
			fileSeqNum = "0" + fileSeqNum;
		}
		outputFileName = "FLT" + fileSeqNum + ".txt";
	       
		try {
			FileOutput = new File( outputPath + "/" + outputFileName );		
			 
	    }catch (Exception e) {
        	e.printStackTrace();
		}
		return FileOutput;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		processXMLfilesFromNEWSIM ( args[0] );
		processTXTfilesFromNEWSIM ( args[0] );
	}
}
