package kr.co.iei.party.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartyController {

	@GetMapping(value = "/party")
	public String party() {
		return "party/party";
	}

	@GetMapping(value = "/party/partyadmin")
	public String partyadmin() {
		return "party/partyadmin";
	}
	
	@GetMapping(value ="/party/partyuser")
	public String partyuser() {
		return "party/partyuser";
	}
}
