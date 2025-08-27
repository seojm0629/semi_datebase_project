package kr.co.iei.question.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.question.model.service.QuestionService;
import kr.co.iei.question.model.vo.QuestionListData;

@Controller
@RequestMapping(value="/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping(value="/list")
	public String questionList(int reqPage, Model model) { // int reqPage로 페이지수와, Model 생성
		//QuestionListData라는 클래스를 따로 생성하여 List타입의 list와 String타입의 pageNavi의 객체를 생성함
		QuestionListData qld = questionService.selectQuestionList(reqPage);
		model.addAttribute("list", qld.getList());
		model.addAttribute("pageNavi", qld.getPageNavi());
		
		return "question/list";
	}
}
