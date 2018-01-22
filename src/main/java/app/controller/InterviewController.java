package app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import app.model.Initiator;
import core.NextStateNotFoundException;
import core.Parser;
import core.model.FormItem;
import core.model.Interview;
import util.ListUtil;
import util.SerializationUtil;

@Controller
public class InterviewController {
	Interview interview;
	private static final String INIT_TEMPLATE = "initiator";
	private static final String RESULT_TEMPLATE = "result";

	@GetMapping("/init")
	public String init(Model model) {
		model.addAttribute(INIT_TEMPLATE, new Initiator());
		interview = null;
		return INIT_TEMPLATE;
	}

	@PostMapping("/init")
	public String initSubmit(@ModelAttribute Initiator init) throws NextStateNotFoundException {
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
			interview.getCurrentState().getForm().getFormItems().get(0).setAnswer(init.getContent());
			interview.nextState();
			init.setInterview(interview);

		}
		SerializationUtil.write(interview);
		return RESULT_TEMPLATE;
	}

	@GetMapping("/interview/{id}")
	public String next(@ModelAttribute Initiator init) throws NextStateNotFoundException {
		interview = SerializationUtil.read(init.getId());
		if (interview != null) {
			init.setInterview(interview);
		}
		return RESULT_TEMPLATE;
	}

	@PostMapping("/interview/{id}")
	public String nextPost(@ModelAttribute Initiator init, @RequestParam String response)
			throws NextStateNotFoundException {
		if (interview != null) {
			List<String> answers = Arrays.asList(response.split(","));
			List<FormItem> formItems = interview.getCurrentState().getForm().getFormItems();
			if (formItems != null && ListUtil.isNotEmpty(formItems)) {
				int i = 0;
				for (FormItem f : formItems) {
					if (i < answers.size()) {
						f.setAnswer(answers.get(i));
					}
					i++;
				}
			}
			interview.nextState();
			init.setInterview(interview);

		}
		SerializationUtil.write(interview);
		return RESULT_TEMPLATE;
	}

	@GetMapping("/prev")
	public String prev(@ModelAttribute Initiator init) {
		if (interview != null) {
			interview.prevState();
			init.setInterview(interview);
		}
		return RESULT_TEMPLATE;
	}

}
