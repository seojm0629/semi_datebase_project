package kr.co.iei.model.member.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Alias(value = "more")
@Data
public class MemberMoreInfo {
	private int memberNo;
    private String memberEdu;
    private String memberHobby;
    private int memberSalary;
    private String memberWorkType;
}
