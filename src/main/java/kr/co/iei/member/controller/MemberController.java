package kr.co.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.co.iei.model.member.service.MemberService;
import kr.co.iei.model.member.vo.Member;
//import kr.co.iei.util.FileUtil;
import kr.co.iei.util.FileUtil;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Value(value = "${file.root}")
	private String root;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value = "/joinagree")
	public String joinagree() {
		return "/member/joincheck";
	}
	
	@GetMapping(value = "/joinFrm")
	public String joinFrm() {
		return "member/joinFrm";
	}
	
	@PostMapping(value = "/join")
	public String join(MultipartFile memberImg, Member m, String year, String month, String date) {
		m.setBirthDate(year+"-"+month+"-"+date);
		String savepath = root+"/selfPhoto/";
		String filepath = fileUtil.upload(savepath, memberImg);
		
		m.setMemberImgPath(filepath);
		int result = memberService.insertMember(m);
		return "redirect:/";
	}
}
