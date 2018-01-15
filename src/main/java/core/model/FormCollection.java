package core.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.ListUtil;

public class FormCollection {
	private List<Form> forms;
	private Map<String, Form> formMap;

	public List<Form> getForms() {
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
		if (ListUtil.isNotEmpty(forms)) {
			formMap = new HashMap<>();
			for (Form f : forms) {
				formMap.put(f.getId(), f);
			}
		}
	}

	public Form getFormById(String id) {
		return formMap.get(id);
	}

}
