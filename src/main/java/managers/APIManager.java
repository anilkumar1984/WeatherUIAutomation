package managers;

public class APIManager {
	
	public static String getAPIEndPoint() {
		 return FileReaderManager.getInstance().getConfigReader().getAPIEndPoint();
	}
	
	public static String getAPIResource() {
		return FileReaderManager.getInstance().getConfigReader().getAPIResource();
	}
}
