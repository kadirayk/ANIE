package core;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import core.model.Interview;

public class ParserTest {
	Parser parser;

	@Before
	public void init() {
		parser = new Parser();
	}

	@Test
	public void ParseFile() {
		String filePath = "data/interview.yaml";
		Interview interview = parser.parseInterview(filePath);
		assertEquals(interview.getContext(), "Machine Learning");

		assertEquals(interview.getStates()[0].getName(), "initial");

	}

}
