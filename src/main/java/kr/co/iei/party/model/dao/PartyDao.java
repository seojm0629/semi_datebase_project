package kr.co.iei.party.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.party.model.vo.Party;

@Mapper
public interface PartyDao {

	List<Party> getAllParties();

}
