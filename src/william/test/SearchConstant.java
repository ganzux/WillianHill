package william.test;

public class SearchConstant {

	public static final String PARAM_FIND = "-f";
	public static final String PARAM_TEXT = "-p";
	public static final String PARAM_HELP = "-?";
	
	public static boolean isParam( String chain ){
		return PARAM_FIND.equals( chain ) || PARAM_HELP.equals( chain ) || PARAM_TEXT.equals( chain );
	}

}
