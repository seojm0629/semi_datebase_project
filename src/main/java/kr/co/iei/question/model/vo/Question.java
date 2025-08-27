package kr.co.iei.question.model.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Alias(value="question")
public class Question {
	private int questionNo;
	private String questionTitle;
	private String questionWriter;
	private String questionContent;
	private String regDate;
	private String questionYn;
	
}
