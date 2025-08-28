package kr.co.iei.question.model.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Alias(value="questionFile")
public class QuestionFile {
	private int questionFileNo;
	private int questionNo;
	private String filename;
	private String filepath;
}
