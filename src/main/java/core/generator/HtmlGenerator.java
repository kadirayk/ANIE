package core.generator;

import java.util.Map;

import core.model.html.UIElement;

public class HtmlGenerator {

	public String generateHtmlElement(UIElement uiElement) {
		StringBuilder htmlElement = null;

		if (uiElement != null) {

		}

		return htmlElement.toString();
	}

	public String toHTML(String tag, String value, Map<String, String> attributes) {
		StringBuilder html = new StringBuilder("<");
		html.append(tag);
		for (String key : attributes.keySet()) {
			html.append(" ").append(key).append("=\"").append(attributes.get(key)).append("\"");
		}
		html.append(">");
		html.append(value);
		html.append("<").append(tag).append(">");
		return html.toString();
	}

}
