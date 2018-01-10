package core.model.html;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class OptionTest {

	@Test
	public void toHTMLTest() {
		Map<String, String> attributes = new HashMap<>();
		attributes.put("value", "option1");
		String content = "Value1";
		Option option = new Option(content, attributes);

		String actual = option.toHTML();
		String expected = "<option value=\"option1\">Value1</option>";

		assertEquals(expected, actual);

	}

}
