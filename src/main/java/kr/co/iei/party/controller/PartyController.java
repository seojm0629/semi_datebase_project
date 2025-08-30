package kr.co.iei.party.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.iei.party.model.service.PartyService;
import kr.co.iei.party.model.vo.Party;

@Controller
public class PartyController {
	@Autowired
	private PartyService partyService;

	@GetMapping("/party")
	public String partyPage(Model model) {
		List<Party> parties = partyService.getAllParties();
		model.addAttribute("parties", parties);
		return "party/party"; // party.html
	}

	@GetMapping(value = "/party/partyadmin")
	public String partyadmin() {
		return "party/partyadmin";
	}

	@GetMapping(value = "/party/partyuser")
	public String partyuser() {
		return "party/partyuser";
	}
	
}










































