package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LandingPage {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String openLanding() {
		return "welcome";
	}
}
