package kr.co.iei.party.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartyController {

	@GetMapping(value = "/party")
	public String partyPage() {
		return "party/party";
	}
}
