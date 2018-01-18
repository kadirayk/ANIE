package core.generator;

import org.junit.Before;
import org.junit.Test;

import core.Parser;
import core.model.Interview;

public class HtmlGeneratorTest {
	Parser parser;

	@Before
	public void init() {
		parser = new Parser();
	}

	@Test
	public void generatePageTest() {
		String filePath = "test/data/ml_interview.yaml";
		Interview interview = parser.parseInterview(filePath);

		new HtmlGenerator("test/data/").generatePage(interview);

	}

	@Test
	public void testException() {
		String filePath = "test/data/ml_interview.yaml";
		Interview interview = parser.parseInterview(filePath);

		new HtmlGenerator("test/data/nondir/").generatePage(interview);
	}

}
