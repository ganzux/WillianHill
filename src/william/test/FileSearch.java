package william.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {


	private String fileNameToSearch;
	private String packageName;
	private List<String> result = new ArrayList<String>();

	//////////////////////////////////////////////////////////////////
	//                          CONSTRUCTOR                         //
	//////////////////////////////////////////////////////////////////
	public void searchDirectory(File directory, String fileNameToSearch, String packageName) throws Exception {
		 
		setFileNameToSearch( fileNameToSearch );
		setPackageName( packageName );
	 
		if (directory.isDirectory()) {
		    search(directory, packageName);
		} else {
			throw new Exception(directory.getAbsoluteFile() + " is not a directory!");
		}
	 
	  }
	//////////////////////////////////////////////////////////////////
	//                      END OF CONSTRUCTOR                      //
	//////////////////////////////////////////////////////////////////



	//////////////////////////////////////////////////////////////////
	//                        PRIVATE METHODS                       //
	//////////////////////////////////////////////////////////////////
	private void search(File file, String packageName) throws Exception{
		if (file.isDirectory()) {
			
			if (file.canRead()) {

				for (File temp : file.listFiles()) {
					
					// Recursive search
					if ( temp.isDirectory() )
						search(temp, packageName);
				    else
				    	if ( getFileNameToSearch().equals( temp.getName().toLowerCase() ) ){
				    		// Only if the name is the same, we will look for the text (if there is text to find)

				    		if ( packageName == null || packageName.isEmpty() )
				    			result.add( temp.getAbsoluteFile().toString() );
				    		else if ( findTextInFile(temp,packageName ) )
				    			result.add( temp.getAbsoluteFile().toString() );

				    	}
			    }
				
			} else
				 throw new Exception( file.getAbsoluteFile() + "Permission Denied" );
		}
	}

	private boolean findTextInFile( File file, String text ){

		try (BufferedReader br = new BufferedReader(new FileReader( file ))) {
 			String currentLine;
 			while ( (currentLine = br.readLine()) != null )
				if (currentLine != null && currentLine.contains( text ) )
					return true;
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	//////////////////////////////////////////////////////////////////
	//                    END OF PRIVATE METHODS                    //
	//////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////
	//                      GETTERS AND SETTERS                     //
	//////////////////////////////////////////////////////////////////
	public String getFileNameToSearch() {
		return fileNameToSearch;
	}
	public void setFileNameToSearch(String fileNameToSearch) {
		this.fileNameToSearch = fileNameToSearch.toLowerCase();
	}
	public List<String> getResult() {
		return result;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public void setResult(List<String> result) {
		this.result = result;
	}
	//////////////////////////////////////////////////////////////////
	//                  END OF GETTERS AND SETTERS                  //
	//////////////////////////////////////////////////////////////////
}