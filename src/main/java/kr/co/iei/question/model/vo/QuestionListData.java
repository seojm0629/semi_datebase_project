package kr.co.iei.question.model.vo;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionListData {
	//page 넘버링하는 작업
		private List list;
		private String pageNavi;
}
