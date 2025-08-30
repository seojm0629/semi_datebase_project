package kr.co.iei.party.model.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Alias(value = "party")
public class Party {

	private int partyNo;
	private String partyType;
	private String partyTitle;
	private String partyContent;
	private String partyPlace;
	private String partyThumb;
	private String partyDate;
	private int partyMale;
	private int partyFemale;
	private int partyPrice;

}
