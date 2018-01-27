package core.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import core.model.html.Option;
import core.model.html.Select;

public class StateTest {

	@Test
	public void toHTMLTest() {

		State state = new State();
		state.setName("step1");

		List<Question> questions = new ArrayList<>();

		state.setQuestions(questions);

		Question q = new Question();
		questions.add(q);

		Map<String, String> attributes = new HashMap<>();
		attributes.put("name", "selectName");

		List<Option> options = new ArrayList<>();

		Map<String, String> option1Attributes = new HashMap<>();
		option1Attributes.put("value", "option1");
		options.add(new Option("Value1", option1Attributes));

		Map<String, String> option2Attributes = new HashMap<>();
		option2Attributes.put("value", "option2");
		options.add(new Option("Value2", option2Attributes));

		Select select = new Select(null, attributes, options);

		q.setUiElement(select);

		String actual = state.toHTML();

		String expected = "<select name=\"response\">" + "\n\t<option value=\"option1\">Value1</option>"
				+ "\n\t<option value=\"option2\">Value2</option>" + "\n</select><br/>\n";

		assertEquals(expected, actual);

	}

}
