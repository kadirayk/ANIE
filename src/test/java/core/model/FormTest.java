package core.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import core.Parser;

public class FormTest {
	Parser parser;

	@Before
	public void init() {
		parser = new Parser();
	}

	@Test
	public void parseTest() {
		String filePath = "test/data/ml_forms.yaml";
		FormCollection formCollection = parser.parseForm(filePath);
		String firstFormId = formCollection.getForms().get(0).getId();

		assertEquals("ml_form_1", firstFormId);

		String secondQuestions = formCollection.getForms().get(1).getFormItems().get(1).getQuestion();

		assertEquals("What is the benefit of your project?", secondQuestions);

	}

}
