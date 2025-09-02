package kr.co.iei.question.model.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Alias(value="questionComment")
public class QuestionComment {
	private int questionCommentNo;
	private String questionCommentWriter;
	private String questionCommentContent;
	private String questionCommentDate;
	private int questionRef;
	private int questionCommentRef;
	

}
