package app.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import app.model.Initiator;
import core.Parser;
import core.model.Interview;

@Controller
public class InterviewController {

	@GetMapping("/init")
	public String greetingForm(Model model) {
		model.addAttribute("initiator", new Initiator());
		return "initiator";
	}

	@PostMapping("/init")
	public String greetingSubmit(@ModelAttribute Initiator init) {
		if (StringUtils.containsIgnoreCase(init.getContent(), "machine learning", Locale.ENGLISH)) {
			String filePath = "data/ml_interview.yaml";
			Parser parser = new Parser();
			Interview interview = parser.parseInterview(filePath);
			init.setInterview(interview);
		}
		return "result";
	}

}
