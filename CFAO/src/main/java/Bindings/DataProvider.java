package Bindings;

import java.util.HashMap;
import java.util.Map;

public class DataProvider {
	private static Map<String, String> globalDataMap = new HashMap<>();

	/**
	 * Add global data in global data map
	 * 
	 * @param key
	 * @param value
	 */
	public static void addGlobalData(String key, String value) {
		globalDataMap.put(key, value);
	}
	
	/**
	 * Remove global data from global data map
	 * 
	 * @param key
	 */
	public static void removeGlobalData(String key) {
		globalDataMap.remove(key);
	}

	/**
	 * Fetch global data from global data map
	 * 
	 * @param key
	 */
	public static String getGlobalData(String key) {
		return globalDataMap.get(key);
	}


}
