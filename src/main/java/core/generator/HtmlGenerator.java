package core.generator;

import java.util.List;

import core.model.State;
import core.model.html.UIElement;

public class HtmlGenerator {

	public String generateComponent(State state) {
		StringBuilder htmlElement = new StringBuilder();

		if (state != null) {
			List<UIElement> uiElements = state.getUiElements();
			for (UIElement e : uiElements) {
				htmlElement.append(e.toHTML()).append("\n");
			}
		}

		return htmlElement.toString();
	}

}
