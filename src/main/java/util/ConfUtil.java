package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfUtil {

	private static Map<String, String> params = new HashMap<>();
	private static ConfUtil instance = null;

	private static final String CONF_FILE = "conf/app.properties";

	public static final String DEBUG = "DEBUG";

	private ConfUtil() {

	}

	private static void init() {
		if (instance == null) {
			instance = new ConfUtil();
			Properties props = new Properties();
			try (InputStream input = new FileInputStream(CONF_FILE)) {

				props.load(input);

			} catch (IOException ex) {
				ex.printStackTrace();
			}

			for (Map.Entry<Object, Object> entry : props.entrySet()) {
				params.put((String) entry.getKey(), (String) entry.getValue());
			}

		}
	}

	public static boolean getValue(String param) {
		init();
		String value = params.get(param);
		return value != null && value.equalsIgnoreCase("true");
	}

}
