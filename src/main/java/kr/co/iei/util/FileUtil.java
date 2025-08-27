package kr.co.iei.util;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {
	
	public String upload(String savepath, MultipartFile file) {
		//1. 원본 파일명 추출
		String filename = file.getOriginalFilename();
		//2. 원본 파일명에서 시작부터 가장 뒤에있는 . 앞까지 문자열로 가져옴 test
		String onlyFileName = filename.substring(0, filename.lastIndexOf("."));
		//3. 원본 파일명에서 가장 뒤에있는 .부터 끝까지를 문자열로 가져옴 .txt
		String extention = filename.substring(filename.lastIndexOf("."));
		//4. 실제로 업로드 할 파일명 변수를 선언
		String filepath = null;
		//5. 파일명이 중복되면 증가시키면서 뒤에 붙일 변수
		int count = 0;
		//6. 파일명이 겹치지 않을 때까지 반복해서 수행
		while(true) {
			if(count == 0) {
				filepath = onlyFileName + extention;
			} else {
				filepath= onlyFileName + "_" + count + extention;
			}
			
			//위에서 만든 파일명이 사용 중인지 체크
			File checkFile = new File(savepath, filepath);
			//해당 파일명으로 파일이 존재하지 않으면 반복문 종료
			if(!checkFile.exists()) {
				break;
			}
			//파일이 존재하면 카운트를 하나 올려서 다시 반복
			count++;
		}
		//파일명 중복체크 끝 -> 내가 업로드 할 파일명이 결정 -> 업로드 수행
		
		//중복체크가 끝난 파일명으로 업로드
		File uploadFile = new File(savepath+filepath);
		
		try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filepath;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
