package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.model.Initiator;

@Controller
public class InterviewController {

	@GetMapping("/init")
	public String greetingForm(Model model) {
		model.addAttribute("initiator", new Initiator());
		return "initiator";
	}

	@GetMapping("/test")
	public String test(Model model) {
		model.addAttribute("initiator", new Initiator());
		return "initiator";
	}

	@PostMapping("/init")
	public String greetingSubmit(@ModelAttribute Initiator greeting) {
		return "result";
	}

}
