package kr.co.iei.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.co.iei.question.model.service.QuestionService;
import kr.co.iei.question.model.vo.Question;
import kr.co.iei.question.model.vo.QuestionListData;
import kr.co.iei.util.FileUtil;

@Controller
@RequestMapping(value="/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@Value("${file.root}")
	private String root; //application.properties에 설정되어있는 file.root의 값을 가지고와서 문자열로 저장
	
	@Autowired
	private FileUtil fileUtil;
	
	@GetMapping(value="/list")
	public String questionList(int reqPage, Model model) { // int reqPage로 페이지수와, Model 생성
		//QuestionListData라는 클래스를 따로 생성하여 List타입의 list와 String타입의 pageNavi의 객체를 생성함
		QuestionListData qld = questionService.selectQuestionList(reqPage);
		model.addAttribute("pageNavi", qld.getPageNavi());
		model.addAttribute("list", qld.getList());
		return "question/list";
	}
	
	@GetMapping(value="/writeFrm")
	public String writeFrm() {
		return "question/writeFrm";
	}
	
	
	
	@GetMapping(value="/search")
	public String searchWriter(String search, Model model) {
		List writeList = questionService.selectWriter(search);
		model.addAttribute("list", writeList);
		return  "question/list";
	}
	
	@GetMapping(value="/detail")
	public String detail() {
		return "question/detail";
	}
	
	
	
}

























