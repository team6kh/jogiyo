package board.review.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;



public class FileUpload {

	// 메서드를 위해 필요한 값들 (변수명 변경 가능, 메서드 사용할 때 각각의 매개변수에 맞게 잘 넣기만 하시면 됩니다.)
	private List<File> files = new ArrayList<File>(); // 첨부파일들
	private List<String> filesFileName = new ArrayList<String>(); // 첨부파일들의 파일명
	private String fileUploadPath; // 파일 업로드 경로
	private String fileRename; // 파일명 변경 시에 공통으로 붙여줄 이름 ex) review_0(review_ + review_num값)
	
	
	// uploadFiles메서드 리턴 값
	private String saveFileName; // 리턴값 ex) review_0_0.jpg review_0_1.png


	// 파일을 업로드경로에 저장하고, 변경한 파일명들을 " "(구분자)로 연결한 String 타입의 값으로 리턴
	public String uploadFiles(List<File> files, List<String> filesFileName,
			String fileUploadPath, String fileRename) throws Exception {
	
		saveFileName = "";
		
		// 각 파일을 순차적으로 꺼내는 반복문
		for (int i = 0; i < files.size(); i++) {
		
			String fileName = filesFileName.get(i);
		
			// 파일의 확장자 알아내기
			String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1,
					fileName.length());
		
			// 파일명 변경
			fileName = fileRename + "_" + i;

			// 새로운 파일 객체를 생성 
			File destFile = new File(fileUploadPath + fileName + "." + fileExt);
		
			// get(i)로 업로드한 파일을 순차적으로 가져와서 destFile객체에 저장한다.
			// 즉 destFile객체에 모든 정보를 복사해준다. (지정된 경로로 파일 저장)
			FileUtils.copyFile(files.get(i), destFile);

			// DB에 저장하기 위해 변경된 파일이름 연결: 
			saveFileName += fileName + "." + fileExt + " ";
		}
		return saveFileName;
	}
}