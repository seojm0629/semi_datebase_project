package kr.co.iei.party.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.party.model.dao.PartyDao;

@Service
public class PartyService {

	@Autowired
	private PartyDao partyDao;
}
