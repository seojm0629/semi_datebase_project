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
	private int member_no;
    private String member_edu;
    private String member_hobby;
    private int member_salary;
    private String member_work_type;
}
