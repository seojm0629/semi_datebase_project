package kr.co.iei.api;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.iei.match.model.service.MatchService;
import kr.co.iei.model.member.service.MemberService;
import kr.co.iei.model.member.vo.Member;
import kr.co.iei.pay.model.vo.pay;
import kr.co.iei.util.EmailSender;

@Controller
@RequestMapping(value = "/api")
public class ApiController {
	
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private MatchService matchService;
	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@GetMapping(value = "/sendCode")
	public String sendCode(String emailFull) {
		//메일 타이틀
		String mailTitle = "설레는 즐거움 'DateBase'에서 인증문자드립니다. ";
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < 8 ; i++) {
			//대문자 : r.nextInt(26)+65
			//소문자 : r.nextInt(26)+97
			//숫자(0-9) : r.nextInt(10)
			int flag = r.nextInt(3); // 0 : 숫자, 1 : 대문자, 2 : 소문자
			if(flag == 0) {
				int randomCode = r.nextInt(10);
				sb.append(randomCode);
			} else if(flag == 1) {
				char randomCode = (char)(r.nextInt(26)+65);
				sb.append(randomCode);
			}else if(flag == 2) {
				char randomCode = (char)(r.nextInt(26)+97);
				sb.append(randomCode);
			}
		}//for
		String emailContent = "<h1>설레임을 드리는 DateBase입니다.</h1>";
		emailContent +="<h3> 인증번호는 [";
		emailContent +="<span style='color:red'>";
		emailContent += sb.toString();
		emailContent +="</span>";
		emailContent += "] 입니다. </h3>";
		
		emailSender.sendMail(mailTitle, emailFull, emailContent);
		
		return sb.toString();
	}
	@PostMapping(value="/pay")
	public String insertPayData(pay p , Model model) {
		int result = matchService.insertPayData(p);
		if(result==1) {
			int update = memberService.updateMemberPayment(p);
			model.addAttribute("title", "결제 완료");
			model.addAttribute("text", "돈 좀 더 써라.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/member/mypage?memberId="+p.getMemberId());
		}
		return "common/msg";
	}
	
}
