package william.test;

import java.io.File;
import java.util.List;

public class Search {

	//////////////////////////////////////////////////////////////////
	//                             MAIN                             //
	//////////////////////////////////////////////////////////////////
	public static void main( String[] args ) {

		boolean checked = false;
		try{
			checked = checkParams( args );
		} catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		
		if ( checked ) {
			if ( SearchConstant.PARAM_HELP.equals( args[0] ) )
				System.out.println( getHelp() );
			
			else{
				// TODO -> OK, maybe a Map <String,String>, for instance SearchConstan, value ???
				String fileToFind	= null;
				String textInFile	= null;
				String path			= null;
				
				
				try{
					int i = 0;
	
					while(i < args.length) {
						if ( args[i].equalsIgnoreCase( SearchConstant.PARAM_FIND) )
							fileToFind = args[++i];
						else if ( args[i].equalsIgnoreCase( SearchConstant.PARAM_TEXT) )
							textInFile = args[++i];
						else{
							path = args[i];
							i++;
						}
					}
				} catch ( Exception e){
					System.out.println("Argument error, Type " + SearchConstant.PARAM_HELP + " for help");
				}

				List<String> paths = null;
				try {
					if ( fileToFind != null && path != null){
						paths = getPaths(fileToFind, path, textInFile);
						
						if ( paths == null || paths.isEmpty() )
							System.out.println( "No result found" );
						else
							for ( String completedPath:paths )
								System.out.println( completedPath );
					}
						
				} catch (Exception e) {
					System.out.println( e.getMessage() );
				}
			}
		}
		
	}
	//////////////////////////////////////////////////////////////////
	//                         END OF MAIN                          //
	//////////////////////////////////////////////////////////////////



	//////////////////////////////////////////////////////////////////
	//                        PRIVATE METHODS                       //
	//////////////////////////////////////////////////////////////////
	private static boolean  checkParams( String[] args ) throws Exception{
		if ( args == null || args.length < 1 || args.length > 5 )
			throw new Exception("Type " + SearchConstant.PARAM_HELP + " for help");

		if ( ! SearchConstant.isParam( args[0] ) )
			throw new Exception( args[0] + " Is not a correct param. Type " + SearchConstant.PARAM_HELP + " for help");
		
		return true;
	}
	
	private static String getHelp(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nHELP");
		sb.append("\n\t-?");
		sb.append("\n\t\tThis HELP");
		sb.append("\n\t-f 'fileName' <directory>");
		sb.append("\n\t\tExample: -f pom.xml /test/will");
		sb.append("\n\t-p 'chain in the file'");
		sb.append("\n\t\tExample: -f pom.xml -p 'com.williamhill' /test/will");
		
		return sb.toString();
	}

	private static List<String> getPaths( String file, String path,  String textInFile ) throws Exception{

		FileSearch fileSearch = new FileSearch();
		fileSearch.searchDirectory(new File( path ), file, textInFile);

		return fileSearch.getResult();
	}
	//////////////////////////////////////////////////////////////////
	//                    END OF PRIVATE METHODS                    //
	//////////////////////////////////////////////////////////////////
}
