package core.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import core.Parser;

public class InterviewTest {
	Parser parser;

	@Before
	public void init() {
		parser = new Parser();
	}

	@Test
	public void nextStateTest() {
		String filePath = "data/ml_interview.yaml";
		Interview interview = parser.parseInterview(filePath);
		String currentState = interview.getCurrentState().getName();

		assertEquals("step1", currentState);

		interview.nextState();
		currentState = interview.getCurrentState().getName();
		assertEquals("step2", currentState);

	}

	@Test
	public void prevStateTest() {
		String filePath = "data/ml_interview.yaml";
		Interview interview = parser.parseInterview(filePath);
		String currentState = null;

		interview.nextState();
		currentState = interview.getCurrentState().getName();
		assertEquals("step2", currentState);

		interview.prevState();
		currentState = interview.getCurrentState().getName();
		assertEquals("step1", currentState);

	}

}
