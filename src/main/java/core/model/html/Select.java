package core.model.html;

import java.util.List;
import java.util.Map;

public class Select extends UIElement {
	List<Option> options;
	private static final String TAG = "select";

	public Select() {
		setTag(TAG);
	}

	public Select(String content, Map<String, String> attributes, List<Option> options) {
		setTag(TAG);
		setContent(content);
		setAttributes(attributes);
		setOptions(options);
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	public String toHTML() {
		StringBuilder html = new StringBuilder("<");
		html.append(getTag());
		if (getAttributes() != null) {
			for (String key : getAttributes().keySet()) {
				html.append(" ").append(key).append("=\"").append(getAttributes().get(key)).append("\"");
			}
		}
		html.append(">");
		if (options != null) {
			for (Option o : options) {
				html.append("\n\t").append(o.toHTML());
			}
		}
		html.append("\n</").append(getTag()).append(">");
		return html.toString();
	}

}
