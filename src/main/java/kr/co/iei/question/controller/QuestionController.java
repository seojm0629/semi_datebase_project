package kr.co.iei.question.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.iei.model.member.vo.Member;
import kr.co.iei.question.model.service.QuestionService;
import kr.co.iei.question.model.vo.Question;
import kr.co.iei.question.model.vo.QuestionComment;
import kr.co.iei.question.model.vo.QuestionFile;
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
	
	@PostMapping(value="/write")
	public String questionWrite(Question q, MultipartFile[] upfile, Model model) {
		
		List<QuestionFile> fileList = new ArrayList<QuestionFile>();
		
		if(!upfile[0].isEmpty()) {
			String savepath = root+"/question/";
			
			for(MultipartFile file : upfile) {
				String filename = file.getOriginalFilename();
				String filepath = fileUtil.upload(savepath, file);
				QuestionFile questionFile = new QuestionFile();
				questionFile.setFilename(filename);
				questionFile.setFilepath(filepath);
				fileList.add(questionFile);
				
			}
		}
		int result = questionService.insertQuestion(q, fileList);
		model.addAttribute("title", "문의사항 작성 완료!!");
		model.addAttribute("text", "문의사항이 등록되었습니다. ");
		model.addAttribute("icon", "success");
		model.addAttribute("loc", "/question/list?reqPage=1" ); //reqPage 반드시 줘야 함.
		
		return "common/msg";
	}
	
	@GetMapping(value="/search")
	public String searchWriter(String search, Model model) {
		List writeList = questionService.selectWriter(search);
		model.addAttribute("list", writeList);
		return  "question/list";
	}
	
	@GetMapping(value="/detail")
	public String questionDetail(int questionNo, @SessionAttribute(required = false) Member member, Model model) {
		int memberNo = member==null ? 0 : member.getMemberNo();
		Question q = questionService.selectOneQuestion(questionNo, memberNo);
		System.out.println(q);
		if(q == null) {
			model.addAttribute("title", "문의사항 조회 실패");
			model.addAttribute("text", "이미 삭제된 게시글입니다.");
			model.addAttribute("icon", "info");
			model.addAttribute("loc", "/ question/list?reqPage=1" );
			return "common/msg";
		} else {
			model.addAttribute("q", q);
			return "question/detail";
		}
	}
	
	@GetMapping(value="/filedown")
	public void filedown(int questionFileNo, HttpServletResponse response) {
		QuestionFile questionFile = questionService.selectOneQuestionFile(questionFileNo);
		String savepath = root+"/question/";
		fileUtil.questionFile(savepath, questionFile.getFilepath(), questionFile.getFilename(), response);
	}
	
	
	@PostMapping(value="/insertComment")
	public String insertComment(QuestionComment qc) {
		System.out.println(qc);
		int result = questionService.insertQuestionComment(qc);
		System.out.println(result);
		return "redirect:/question/detail?questionNo=" + qc.getQuestionCommentRef();
	}
}

























