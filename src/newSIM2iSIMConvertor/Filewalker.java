package newSIM2iSIMConvertor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Filewalker {
	
	public List<File> xmlList ;	
	public List<File> txtList ;	
		
	public Filewalker( String path ) {				
		xmlList = new ArrayList<File>();
		txtList = new ArrayList<File>();
		this.walk(path);
	}
	
	public List<File> getXMLfilelist () {
		
		return xmlList;
	}
	
	public List<File> getTXTfilelist () {
		
		return txtList;
	}
	
    public void walk( String path ) {
    	
 	
        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
            	walk( f.getAbsolutePath() );
            }
            if ( f.isFile()  ) {
            	String extension = f.getName().substring(f.getName().lastIndexOf("."));
            	if ( extension.equals(".xml") ) {
            		xmlList.add(f);
            	}
            	if ( extension.equals(".ica") || extension.equals(".txt") ) {
            		txtList.add(f);
            	}
    
            }
        }
    }
}
