package core;

import java.io.File;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import core.model.Form;
import core.model.FormCollection;
import core.model.Interview;
import core.model.State;

public class Parser {

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

	public Interview parseInterview(String filePath) {
		Interview interview = null;
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {
			interview = mapper.readValue(new File(filePath), Interview.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (interview != null) {
			String formPath = interview.getFormRepo();

			FormCollection forms = parseForm(formPath);

			if (forms != null) {

				for (State s : interview.getStates()) {
					String formId = s.getFormId();
					Form form = forms.getFormById(formId);
					s.setForm(form);
				}

			}
			String id = UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
			interview.setId(id);

		}

		return interview;
	}

}
