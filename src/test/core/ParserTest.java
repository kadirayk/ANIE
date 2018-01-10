package core;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import core.model.Interview;
import core.model.html.Input;
import core.model.html.Select;
import core.model.html.UIElement;

public class ParserTest {
	Parser parser;

	@Before
	public void init() {
		parser = new Parser();
	}

	@Test
	public void parseInterview() {
		String filePath = "data/interview.yaml";
		Interview interview = parser.parseInterview(filePath);
		assertEquals(interview.getContext(), "Machine Learning");

		assertEquals(interview.getStates()[0].getName(), "initial");

		// first ui element of first state is an html input element and its tpye
		// is text
		UIElement inputElement = interview.getStates()[0].getUiElements()[0];
		assertThat(inputElement, instanceOf(Input.class));

		Input inputField = (Input) inputElement;
		assertEquals(inputField.getAttributes().get("type"), "text");

		// first ui element of second state is an html select element and its
		// first option is option1
		UIElement selectElement = interview.getStates()[1].getUiElements()[0];
		assertThat(selectElement, instanceOf(Select.class));

		Select selectField = (Select) selectElement;
		assertEquals(selectField.getOptions().get(0).getAttributes().get("value"), "option1");

		System.out.println(selectField.getOptions().get(0).toHTML());

	}

}
