package managers;

import dataProviders.ConfigFileReader;

public class FileReaderManager {

	private static FileReaderManager fileReaderManager = new FileReaderManager();
	private static ConfigFileReader configReader;

	private FileReaderManager() {

	}

	public static FileReaderManager getInstance() {
		return fileReaderManager;

	}
	
	public ConfigFileReader getConfigReader() {
		return (configReader==null)?new ConfigFileReader():configReader;
	}
}
