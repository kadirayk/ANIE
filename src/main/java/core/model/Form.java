package core.model;

import java.io.Serializable;
import java.util.List;

public class Form implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5697935240571388711L;
	private String id;
	private List<FormItem> formItems;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<FormItem> getFormItems() {
		return formItems;
	}

	public void setFormItems(List<FormItem> formItems) {
		this.formItems = formItems;
	}

}
