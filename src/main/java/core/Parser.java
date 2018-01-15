package core;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import core.model.FormCollection;
import core.model.Interview;

public class Parser {

	public Interview parseInterview(String filePath) {
		Interview interview = null;
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {
			interview = mapper.readValue(new File(filePath), Interview.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return interview;
	}

	public FormCollection parseForm(String filePath) {
		FormCollection forms = null;
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {
			forms = mapper.readValue(new File(filePath), FormCollection.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forms;

	}

}
