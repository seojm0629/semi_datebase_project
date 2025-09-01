package kr.co.iei.model.member.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.val;

@AllArgsConstructor
@NoArgsConstructor
@Alias(value = "member")
@Data
public class Member {
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberPhone;
	private String memberAddr;
	private String memberEmail;
	private String birthDate;
	private String memberGender;
	private String memberJob;
	private String memberMBTI;
	private int memberLevel;
	private String memberImgPath;
	private int myPassCount;
	private int myMatchingCount;
	private String memberGrade;
	private MemberMoreInfo memberMoreInfo;
}
