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
	Interview interview;

	@GetMapping("/init")
	public String init(Model model) {
		model.addAttribute("initiator", new Initiator());
		return "initiator";
	}

	@PostMapping("/init")
	public String initSubmit(@ModelAttribute Initiator init) {
		if (StringUtils.containsIgnoreCase(init.getContent(), "machine learning", Locale.ENGLISH)
				|| StringUtils.containsIgnoreCase(init.getContent(), " ml", Locale.ENGLISH)) {
			String filePath = "data/ml_interview.yaml";
			Parser parser = new Parser();
			interview = parser.parseInterview(filePath);
			init.setInterview(interview);
		} else if (StringUtils.containsIgnoreCase(init.getContent(), "play a game", Locale.ENGLISH)) {
			String filePath = "data/game_interview.yaml";
			Parser parser = new Parser();
			interview = parser.parseInterview(filePath);
			init.setInterview(interview);

		} else if (StringUtils.containsIgnoreCase(init.getContent(), "play", Locale.ENGLISH)) {
			String filePath = "data/game_interview.yaml";
			Parser parser = new Parser();
			interview = parser.parseInterview(filePath);
			interview.nextState();
			init.setInterview(interview);

		}
		return "result";
	}

	@GetMapping("/next")
	public String next(@ModelAttribute Initiator init) {
		if (interview != null) {
			interview.nextState();
			init.setInterview(interview);
		}
		return "result";
	}

	@GetMapping("/prev")
	public String prev(@ModelAttribute Initiator init) {
		if (interview != null) {
			interview.prevState();
			init.setInterview(interview);
		}
		return "result";
	}

}
