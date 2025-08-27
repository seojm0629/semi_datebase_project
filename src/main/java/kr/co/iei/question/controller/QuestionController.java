package kr.co.iei.question.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.question.model.service.QuestionService;

@Controller
@RequestMapping(value="/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping(value="/list") 
	public String list() {
		return "question/list";
	}
}
