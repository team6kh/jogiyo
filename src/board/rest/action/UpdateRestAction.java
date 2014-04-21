package board.rest.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import board.rest.dto.RestDTO;
import board.restopt.dto.RestoptDTO;
import board.review.action.FileUpload;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.ActionSupport;

import common.ConDAOAware;
import common.Constants;

public class UpdateRestAction extends ActionSupport implements ConDAOAware {
	public static SqlMapClient sqlMapper;
	private List<RestoptDTO> list = new ArrayList<RestoptDTO>();
	
	private int rest_num;
	private int currentPage;
	private String session_id;
	
	
	
	
	//상품글 update
	private RestDTO paramClass = new RestDTO();
	private RestDTO resultClass = new RestDTO();
	
	//상품글 일반항목
	private String rest_subject;
	private String  rest_localcategory;
	private String  rest_typecategory;
	//매인사진
	private File rest_destFile1; //dto
	private String rest_main_orgname; // dto
	private String rest_main_savname; // dto
	private File upload1;
	private String upload1ContentType;
	private String upload1FileName;
	private String fileUploadPath1 = Constants.COMMON_FILE_PATH + Constants.REST_MAIN_FILE_PATH;
	//컨텐트사진
	private File rest_destFile2;
	private String rest_content_orgname;
	private String rest_content_savname;
	private File upload2;
	private String upload2ContentType;
	private String upload2FileName;
	private String fileUploadPath2 = Constants.COMMON_FILE_PATH + Constants.REST_CONTENT_FILE_PATH;
	//상품글 update 끝
	
	
	
	
	
	
	//옵션레코드 update
	private RestoptDTO paramClass1 = new RestoptDTO();
	private RestoptDTO resultClass1 = new RestoptDTO();
	
	//insertRest.jsp에서 사용자가 입력한 옵션명 파라미터
	private String restopt_subject1;
	private String restopt_subject2;
	private String restopt_subject3;
	private String restopt_subject4;
	private String restopt_subject5;
	private String restopt_subject6;
	private String restopt_subject7;
	private String restopt_subject8;
	private String restopt_subject9;
	private String restopt_subject10;
	private String restopt_subject11;
	private String restopt_subject12;
	private String restopt_subject13;
	private String restopt_subject14;
	private String restopt_subject15;

	//insertRest.jsp에서 사용자가 입력한 옵션가 파라미터
	private int restopt_priceplus1;
	private int restopt_priceplus2;
	private int restopt_priceplus3;
	private int restopt_priceplus4;
	private int restopt_priceplus5;
	private int restopt_priceplus6;
	private int restopt_priceplus7;
	private int restopt_priceplus8;
	private int restopt_priceplus9;
	private int restopt_priceplus10;
	private int restopt_priceplus11;
	private int restopt_priceplus12;
	private int restopt_priceplus13;
	private int restopt_priceplus14;
	private int restopt_priceplus15;

	//옵션사진
	private String restopt_orgname; //set get //dto
	private String restopt_savname; //set get //dto

	//이하 set get
	private File optupload1;
	private String optupload1ContentType;
	private String optupload1FileName;
	private String optfileUploadPath1 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload2;
	private String optupload2ContentType;
	private String optupload2FileName;
	private String optfileUploadPath2 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload3;
	private String optupload3ContentType;
	private String optupload3FileName;
	private String optfileUploadPath3 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload4;
	private String optupload4ContentType;
	private String optupload4FileName;
	private String optfileUploadPath4 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload5;
	private String optupload5ContentType;
	private String optupload5FileName;
	private String optfileUploadPath5 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload6;
	private String optupload6ContentType;
	private String optupload6FileName;
	private String optfileUploadPath6 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload7;
	private String optupload7ContentType;
	private String optupload7FileName;
	private String optfileUploadPath7 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload8;
	private String optupload8ContentType;
	private String optupload8FileName;
	private String optfileUploadPath8 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload9;
	private String optupload9ContentType;
	private String optupload9FileName;
	private String optfileUploadPath9 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload10;
	private String optupload10ContentType;
	private String optupload10FileName;
	private String optfileUploadPath10 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload11;
	private String optupload11ContentType;
	private String optupload11FileName;
	private String optfileUploadPath11 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload12;
	private String optupload12ContentType;
	private String optupload12FileName;
	private String optfileUploadPath12 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload13;
	private String optupload13ContentType;
	private String optupload13FileName;
	private String optfileUploadPath13 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload14;
	private String optupload14ContentType;
	private String optupload14FileName;
	private String optfileUploadPath14 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;

	private File optupload15;
	private String optupload15ContentType;
	private String optupload15FileName;
	private String optfileUploadPath15 = Constants.COMMON_FILE_PATH + Constants.REST_MENU_FILE_PATH;
	//옵션레코드 update 끝
	
	
	
	
	
	
	
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}	
	
	public String form() throws Exception {
		//수정에 뿌려줄 레코드1개 select
		resultClass = (RestDTO)sqlMapper.queryForObject("Rest.selectRestOne", getRest_num());
		//옵션에 뿌려줄 레코드들 select
		list = (List<RestoptDTO>) sqlMapper.queryForList("Rest.selectRestoptOne", getRest_num());
	
		return SUCCESS; //updateRest.jsp
	}
	
	
	public String execute() throws Exception {
		
		//상품글 업데이트 시작
			//상품글 일반항목 업데이트
			paramClass.setRest_num(getRest_num());
			paramClass.setRest_subject(getRest_subject());
			paramClass.setRest_localcategory(getRest_localcategory());
			paramClass.setRest_typecategory(getRest_typecategory());
			sqlMapper.update("Rest.updateRestone", paramClass);
			
			
			//상품글 파일 업데이트
			if (getUpload1() != null && getUpload2() != null) {
				resultClass = (RestDTO) sqlMapper.queryForObject("Rest.selectLastNo");
	
				//매인사진 파일 이름과 확장자 설정.
				String file_name1 = "main_" + resultClass.getRest_num();
				String file_ext1 = getUpload1FileName().substring(getUpload1FileName().lastIndexOf('.') + 1, getUpload1FileName().length());
				//컨텐트사진 파일 이름과 확장자 설정.
				String file_name2 = "content_" + resultClass.getRest_num();
				String file_ext2 = getUpload2FileName().substring(getUpload2FileName().lastIndexOf('.') + 1, getUpload2FileName().length());
	
				//매인사진파일 저장.
				rest_destFile1 = new File(fileUploadPath1 + file_name1 + "."+ file_ext1); 
				FileUtils.copyFile(getUpload1(), rest_destFile1);
				//컨텐트사진파일 저장.
				rest_destFile2 = new File(fileUploadPath2 + file_name2 + "."+ file_ext2); 
				FileUtils.copyFile(getUpload2(), rest_destFile2); 
	
				//글넘버
				paramClass.setRest_num(resultClass.getRest_num());
				//매인사진파일 DTO에 set
				paramClass.setRest_destFile1(rest_destFile1.getPath().replace('\\', '/').substring(27));
				paramClass.setRest_main_orgname(getUpload1FileName());
				paramClass.setRest_main_savname(file_name1 + "." + file_ext1);
				//컨텐트사진파일 DTO에 set
				paramClass.setRest_destFile2(rest_destFile2.getPath().replace('\\', '/').substring(27));
				paramClass.setRest_content_orgname(getUpload2FileName()); 
				paramClass.setRest_content_savname(file_name2 + "." + file_ext2);
	
				//파일 정보 업데이트.
				sqlMapper.update("Rest.updateFile", paramClass);
			}
		//상품글 업데이트 완료
		
		
		
		
		//옵션글 업데이트 시작
			//업데이트 전 기존옵션을 불러옴
			list = (List<RestoptDTO>) sqlMapper.queryForList("Rest.selectRestoptOne", getRest_num());
			int listsize = list.size(); //기존 옵션의 개수 (index 0 to size)
			
			
			//옵션사진 삭제용
			FileUpload fileUpload = new FileUpload();

			
			//이전 옵션의 개수 이내의 인덱스를 판단.
			if(0<listsize){
				//사용자가 입력한 옵션이 없으면, 
				if(getRestopt_subject1() == null && getRestopt_priceplus1() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
					//수정시에 사용자가 옵션을 감소시킨것으로 판단
					//즉, 있을 필요가 없는 레코드이니 해당 레코드 삭제처리
					if(list.get(0).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(0).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			//이하 동일함
			if(1<listsize){
				if(getRestopt_subject2() == null && getRestopt_priceplus2() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
					
					if(list.get(1).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(1).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(2<listsize){
				if(getRestopt_subject3() == null && getRestopt_priceplus3() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(2).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(2).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(3<listsize){
				if(getRestopt_subject4() == null && getRestopt_priceplus4() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(3).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(3).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(4<listsize){
				if(getRestopt_subject5() == null && getRestopt_priceplus5() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(4).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(4).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(5<listsize){
				if(getRestopt_subject6() == null && getRestopt_priceplus6() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(5).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(5).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(6<listsize){
				if(getRestopt_subject7() == null && getRestopt_priceplus7() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(6).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(6).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(7<listsize){
				if(getRestopt_subject8() == null && getRestopt_priceplus8() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(7).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(7).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(8<listsize){
				if(getRestopt_subject9() == null && getRestopt_priceplus9() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(8).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(8).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(9<listsize){
				if(getRestopt_subject10() == null && getRestopt_priceplus10() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(9).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(9).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(10<listsize){
				if(getRestopt_subject11() == null && getRestopt_priceplus11() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(10).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(10).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(11<listsize){
				if(getRestopt_subject12() == null && getRestopt_priceplus12() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(11).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(11).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(12<listsize){
				if(getRestopt_subject13() == null && getRestopt_priceplus13() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(12).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(12).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(13<listsize){
				if(getRestopt_subject14() == null && getRestopt_priceplus14() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(13).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(13).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			if(14<listsize){
				if(getRestopt_subject15() == null && getRestopt_priceplus15() == 0){
					paramClass1.setRestopt_rest_num(getRest_num());
	
					if(list.get(14).getRestopt_subject() != null){
						paramClass1.setRestopt_subject(list.get(14).getRestopt_subject());
						sqlMapper.delete("Rest.deleteRestoptOne", paramClass1);
					}
				}
			}
			
			//위에서 필요없는 레코드는 삭제하고,
			//필요한 레코드는 그대로임
			//아래에서 사용자가 수정한 정보들을 update 함
			
			
			///
			if(getRestopt_subject1() != null && getRestopt_priceplus1() != 0){
				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject1);
				paramClass1.setRestopt_priceplus(restopt_priceplus1);

				if (getOptupload1() != null){ //수정시에 파일 업로드 했을경우
					if(0<listsize){//기존파일 삭제
						if (list.get(0).getRestopt_savname() != null) {
							String filesName = list.get(0).getRestopt_savname(); //기존 파일명을 가져옴
							fileUpload.deleteFiles(filesName, optfileUploadPath1); //해당경로, 기존파일명을 삭제함.
							
							//새롭게 업로드된 것 저장
							//옵션 사진 파일 이름과 확장자 설정.
							String file_name1 = "menu1_" + getRest_num();
							String file_ext1 = getOptupload1FileName().substring(getOptupload1FileName().lastIndexOf('.') + 1, getOptupload1FileName().length());
							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath1 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload1(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload1FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}
				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(0<listsize){
						paramClass1.setRestopt_destFile1(list.get(0).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(0).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(0).getRestopt_savname());
					}
				}

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			///
			
			
			
			
			if(getRestopt_subject2() != null && getRestopt_priceplus2() != 0){
				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject2);
				paramClass1.setRestopt_priceplus(restopt_priceplus2);

				if (getOptupload2() != null){
					if(1<listsize){
						if (list.get(1).getRestopt_savname() != null) {
							String filesName = list.get(1).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath2);
							
							String file_name1 = "menu2_" + getRest_num();
							String file_ext1 = getOptupload2FileName().substring(getOptupload2FileName().lastIndexOf('.') + 1, getOptupload2FileName().length());

							File restopt_destFile1 = new File(optfileUploadPath2 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload2(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload2FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}
				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(1<listsize){
						paramClass1.setRestopt_destFile1(list.get(1).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(1).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(1).getRestopt_savname());
					}
				}

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject3() != null && getRestopt_priceplus3() != 0){
				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject3);
				paramClass1.setRestopt_priceplus(restopt_priceplus3);

				if (getOptupload3() != null){
					if(2<listsize){
						if (list.get(2).getRestopt_savname() != null) {
							String filesName = list.get(2).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath3);
							
							String file_name1 = "menu3_" + getRest_num();
							String file_ext1 = getOptupload3FileName().substring(getOptupload3FileName().lastIndexOf('.') + 1, getOptupload3FileName().length());

							File restopt_destFile1 = new File(optfileUploadPath3 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload3(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload3FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}
				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(2<listsize){
						paramClass1.setRestopt_destFile1(list.get(2).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(2).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(2).getRestopt_savname());
					}
				}
				
				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject4() != null && getRestopt_priceplus4() != 0){
				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject4);
				paramClass1.setRestopt_priceplus(restopt_priceplus4);

				if (getOptupload4() != null){
					if(3<listsize){
						if (list.get(3).getRestopt_savname() != null) {
							String filesName = list.get(3).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath4);
							
							String file_name1 = "menu4_" + getRest_num();
							String file_ext1 = getOptupload4FileName().substring(getOptupload4FileName().lastIndexOf('.') + 1, getOptupload4FileName().length());

							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath4 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload4(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload4FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}
					
				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(3<listsize){
						paramClass1.setRestopt_destFile1(list.get(3).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(3).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(3).getRestopt_savname());
					}
				}

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject5() != null && getRestopt_priceplus5() != 0){
				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject5);
				paramClass1.setRestopt_priceplus(restopt_priceplus5);

				//1개
				if (getOptupload5() != null){
					if(4<listsize){
						if (list.get(4).getRestopt_savname() != null) {
							String filesName = list.get(4).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath5);
							
							//옵션 사진 파일 이름과 확장자 설정.
							String file_name1 = "menu5_" + getRest_num();
							String file_ext1 = getOptupload5FileName().substring(getOptupload5FileName().lastIndexOf('.') + 1, getOptupload5FileName().length());

							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath5 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload5(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload5FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}

				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(4<listsize){
						paramClass1.setRestopt_destFile1(list.get(4).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(4).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(4).getRestopt_savname());
					}
				}

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject6() != null && getRestopt_priceplus6() != 0){
				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject6);
				paramClass1.setRestopt_priceplus(restopt_priceplus6);

				//1개
				if (getOptupload6() != null){
					if(5<listsize){
						if (list.get(5).getRestopt_savname() != null) {
							String filesName = list.get(5).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath6);
							
							//옵션 사진 파일 이름과 확장자 설정.
							String file_name1 = "menu6_" + getRest_num();
							String file_ext1 = getOptupload6FileName().substring(getOptupload6FileName().lastIndexOf('.') + 1, getOptupload6FileName().length());

							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath6 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload6(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload6FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}

				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(5<listsize){
						paramClass1.setRestopt_destFile1(list.get(5).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(5).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(5).getRestopt_savname());
					}
				}

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject7() != null && getRestopt_priceplus7() != 0){

				if (getOptupload7() != null){
					if(6<listsize){
						if (list.get(6).getRestopt_savname() != null) {
							String filesName = list.get(6).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath7);
							
							//옵션 사진 파일 이름과 확장자 설정.
							String file_name1 = "menu7_" + getRest_num();
							String file_ext1 = getOptupload7FileName().substring(getOptupload7FileName().lastIndexOf('.') + 1, getOptupload7FileName().length());

							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath7 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload7(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload7FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}

				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(6<listsize){
						paramClass1.setRestopt_destFile1(list.get(6).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(6).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(6).getRestopt_savname());
					}
				}

				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject7);
				paramClass1.setRestopt_priceplus(restopt_priceplus7);

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject8() != null && getRestopt_priceplus8() != 0){
				if (getOptupload8() != null){
					if(7<listsize){
						if (list.get(7).getRestopt_savname() != null) {
							String filesName = list.get(7).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath8);
							
							//옵션 사진 파일 이름과 확장자 설정.
							String file_name1 = "menu8_" + getRest_num();
							String file_ext1 = getOptupload8FileName().substring(getOptupload8FileName().lastIndexOf('.') + 1, getOptupload8FileName().length());

							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath8 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload8(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload8FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}

				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(7<listsize){
						paramClass1.setRestopt_destFile1(list.get(7).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(7).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(7).getRestopt_savname());
					}
				}

				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject8);
				paramClass1.setRestopt_priceplus(restopt_priceplus8);

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject9() != null && getRestopt_priceplus9() != 0){
				if (getOptupload9() != null){
					if(8<listsize){
						if (list.get(8).getRestopt_savname() != null) {
							String filesName = list.get(8).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath9);
							
							//옵션 사진 파일 이름과 확장자 설정.
							String file_name1 = "menu9_" + getRest_num();
							String file_ext1 = getOptupload9FileName().substring(getOptupload9FileName().lastIndexOf('.') + 1, getOptupload9FileName().length());

							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath9 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload9(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload9FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}

				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(8<listsize){
						paramClass1.setRestopt_destFile1(list.get(8).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(8).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(8).getRestopt_savname());
					}
				}


				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject9);
				paramClass1.setRestopt_priceplus(restopt_priceplus9);

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject10() != null && getRestopt_priceplus10() != 0){
				if (getOptupload10() != null){
					if(9<listsize){
						if (list.get(9).getRestopt_savname() != null) {
							String filesName = list.get(9).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath10);
							
							//옵션 사진 파일 이름과 확장자 설정.
							String file_name1 = "menu10_" + getRest_num();
							String file_ext1 = getOptupload10FileName().substring(getOptupload10FileName().lastIndexOf('.') + 1, getOptupload10FileName().length());

							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath10 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload10(), restopt_destFile1);


							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload10FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}

				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(9<listsize){
						paramClass1.setRestopt_destFile1(list.get(9).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(9).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(9).getRestopt_savname());
					}
				}

				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject10);
				paramClass1.setRestopt_priceplus(restopt_priceplus10);

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject11() != null && getRestopt_priceplus11() != 0){
				if (getOptupload11() != null){
					if(10<listsize){
						if (list.get(10).getRestopt_savname() != null) {
							String filesName = list.get(10).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath11);
							
							//옵션 사진 파일 이름과 확장자 설정.
							String file_name1 = "menu11_" + getRest_num();
							String file_ext1 = getOptupload11FileName().substring(getOptupload11FileName().lastIndexOf('.') + 1, getOptupload11FileName().length());

							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath11 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload11(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload11FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}

				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(10<listsize){
						paramClass1.setRestopt_destFile1(list.get(10).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(10).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(10).getRestopt_savname());
					}
				}

				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject11);
				paramClass1.setRestopt_priceplus(restopt_priceplus11);

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject12() != null && getRestopt_priceplus12() != 0){
				if (getOptupload12() != null){
					if(11<listsize){
						if (list.get(11).getRestopt_savname() != null) {
							String filesName = list.get(11).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath12);
							
							//옵션 사진 파일 이름과 확장자 설정.
							String file_name1 = "menu12_" + getRest_num();
							String file_ext1 = getOptupload12FileName().substring(getOptupload12FileName().lastIndexOf('.') + 1, getOptupload12FileName().length());

							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath12 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload12(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload12FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}

				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(11<listsize){
						paramClass1.setRestopt_destFile1(list.get(11).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(11).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(11).getRestopt_savname());
					}
				}

				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject12);
				paramClass1.setRestopt_priceplus(restopt_priceplus12);

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject13() != null && getRestopt_priceplus13() != 0){
				if (getOptupload13() != null){
					if(12<listsize){
						if (list.get(12).getRestopt_savname() != null) {
							String filesName = list.get(12).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath13);
							
							//옵션 사진 파일 이름과 확장자 설정.
							String file_name1 = "menu13_" + getRest_num();
							String file_ext1 = getOptupload13FileName().substring(getOptupload13FileName().lastIndexOf('.') + 1, getOptupload13FileName().length());

							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath13 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload13(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload13FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}

				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(12<listsize){
						paramClass1.setRestopt_destFile1(list.get(12).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(12).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(12).getRestopt_savname());
					}
				}

				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject13);
				paramClass1.setRestopt_priceplus(restopt_priceplus13);

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject14() != null && getRestopt_priceplus14() != 0){
				if (getOptupload14() != null){
					if(13<listsize){
						if (list.get(13).getRestopt_savname() != null) {
							String filesName = list.get(13).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath14);
							
							//옵션 사진 파일 이름과 확장자 설정.
							String file_name1 = "menu14_" + getRest_num();
							String file_ext1 = getOptupload14FileName().substring(getOptupload14FileName().lastIndexOf('.') + 1, getOptupload14FileName().length());

							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath14 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload14(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload14FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}

				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(13<listsize){
						paramClass1.setRestopt_destFile1(list.get(13).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(13).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(13).getRestopt_savname());
					}
				}


				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject14);
				paramClass1.setRestopt_priceplus(restopt_priceplus14);

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
			if(getRestopt_subject15() != null && getRestopt_priceplus15() != 0){
				if (getOptupload15() != null){
					if(14<listsize){
						if (list.get(14).getRestopt_savname() != null) {
							String filesName = list.get(14).getRestopt_savname();
							fileUpload.deleteFiles(filesName, optfileUploadPath15);
							
							//옵션 사진 파일 이름과 확장자 설정.
							String file_name1 = "menu15_" + getRest_num();
							String file_ext1 = getOptupload15FileName().substring(getOptupload15FileName().lastIndexOf('.') + 1, getOptupload15FileName().length());

							//옵션 사진 파일 저장.
							File restopt_destFile1 = new File(optfileUploadPath15 + file_name1 + "."+ file_ext1); 
							FileUtils.copyFile(getOptupload15(), restopt_destFile1);

							//매인사진파일 DTO에 set
							paramClass1.setRestopt_destFile1(restopt_destFile1.getPath().replace('\\', '/').substring(27));
							paramClass1.setRestopt_orgname(getOptupload15FileName());
							paramClass1.setRestopt_savname(file_name1 + "." + file_ext1);
						}
					}

				}else{//수정시에 파일 업로드 하지 않았을 경우
					if(14<listsize){
						paramClass1.setRestopt_destFile1(list.get(14).getRestopt_destFile1());
						paramClass1.setRestopt_orgname(list.get(14).getRestopt_orgname());
						paramClass1.setRestopt_savname(list.get(14).getRestopt_savname());
					}
				}

				paramClass1.setRestopt_rest_num(getRest_num());
				paramClass1.setRestopt_subject(restopt_subject15);
				paramClass1.setRestopt_priceplus(restopt_priceplus15);

				sqlMapper.update("Rest.updateRestopt", paramClass1);
			}
		//옵션글 업데이트 완료
		
		return SUCCESS;  // preRead.jsp
	}
	
	

	
	
	
	public RestDTO getParamClass() {
		return paramClass;
	}

	public void setParamClass(RestDTO paramClass) {
		this.paramClass = paramClass;
	}

	public String getRest_subject() {
		return rest_subject;
	}

	public void setRest_subject(String rest_subject) {
		this.rest_subject = rest_subject;
	}

	public String getRest_localcategory() {
		return rest_localcategory;
	}

	public void setRest_localcategory(String rest_localcategory) {
		this.rest_localcategory = rest_localcategory;
	}

	public String getRest_typecategory() {
		return rest_typecategory;
	}

	public void setRest_typecategory(String rest_typecategory) {
		this.rest_typecategory = rest_typecategory;
	}

	public File getRest_destFile1() {
		return rest_destFile1;
	}

	public void setRest_destFile1(File rest_destFile1) {
		this.rest_destFile1 = rest_destFile1;
	}

	public String getRest_main_orgname() {
		return rest_main_orgname;
	}

	public void setRest_main_orgname(String rest_main_orgname) {
		this.rest_main_orgname = rest_main_orgname;
	}

	public String getRest_main_savname() {
		return rest_main_savname;
	}

	public void setRest_main_savname(String rest_main_savname) {
		this.rest_main_savname = rest_main_savname;
	}

	public File getUpload1() {
		return upload1;
	}

	public void setUpload1(File upload1) {
		this.upload1 = upload1;
	}

	public String getUpload1ContentType() {
		return upload1ContentType;
	}

	public void setUpload1ContentType(String upload1ContentType) {
		this.upload1ContentType = upload1ContentType;
	}

	public String getUpload1FileName() {
		return upload1FileName;
	}

	public void setUpload1FileName(String upload1FileName) {
		this.upload1FileName = upload1FileName;
	}

	public String getFileUploadPath1() {
		return fileUploadPath1;
	}

	public void setFileUploadPath1(String fileUploadPath1) {
		this.fileUploadPath1 = fileUploadPath1;
	}

	public File getRest_destFile2() {
		return rest_destFile2;
	}

	public void setRest_destFile2(File rest_destFile2) {
		this.rest_destFile2 = rest_destFile2;
	}

	public String getRest_content_orgname() {
		return rest_content_orgname;
	}

	public void setRest_content_orgname(String rest_content_orgname) {
		this.rest_content_orgname = rest_content_orgname;
	}

	public String getRest_content_savname() {
		return rest_content_savname;
	}

	public void setRest_content_savname(String rest_content_savname) {
		this.rest_content_savname = rest_content_savname;
	}

	public File getUpload2() {
		return upload2;
	}

	public void setUpload2(File upload2) {
		this.upload2 = upload2;
	}

	public String getUpload2ContentType() {
		return upload2ContentType;
	}

	public void setUpload2ContentType(String upload2ContentType) {
		this.upload2ContentType = upload2ContentType;
	}

	public String getUpload2FileName() {
		return upload2FileName;
	}

	public void setUpload2FileName(String upload2FileName) {
		this.upload2FileName = upload2FileName;
	}

	public String getFileUploadPath2() {
		return fileUploadPath2;
	}

	public void setFileUploadPath2(String fileUploadPath2) {
		this.fileUploadPath2 = fileUploadPath2;
	}

	public int getRest_num() {
		return rest_num;
	}
	public void setRest_num(int rest_num) {
		this.rest_num = rest_num;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public RestDTO getResultClass() {
		return resultClass;
	}
	public void setResultClass(RestDTO resultClass) {
		this.resultClass = resultClass;
	}
	public List<RestoptDTO> getList() {
		return list;
	}
	public void setList(List<RestoptDTO> list) {
		this.list = list;
	}

	public RestoptDTO getParamClass1() {
		return paramClass1;
	}

	public void setParamClass1(RestoptDTO paramClass1) {
		this.paramClass1 = paramClass1;
	}

	public RestoptDTO getResultClass1() {
		return resultClass1;
	}

	public void setResultClass1(RestoptDTO resultClass1) {
		this.resultClass1 = resultClass1;
	}

	public String getRestopt_subject1() {
		return restopt_subject1;
	}

	public void setRestopt_subject1(String restopt_subject1) {
		this.restopt_subject1 = restopt_subject1;
	}

	public String getRestopt_subject2() {
		return restopt_subject2;
	}

	public void setRestopt_subject2(String restopt_subject2) {
		this.restopt_subject2 = restopt_subject2;
	}

	public String getRestopt_subject3() {
		return restopt_subject3;
	}

	public void setRestopt_subject3(String restopt_subject3) {
		this.restopt_subject3 = restopt_subject3;
	}

	public String getRestopt_subject4() {
		return restopt_subject4;
	}

	public void setRestopt_subject4(String restopt_subject4) {
		this.restopt_subject4 = restopt_subject4;
	}

	public String getRestopt_subject5() {
		return restopt_subject5;
	}

	public void setRestopt_subject5(String restopt_subject5) {
		this.restopt_subject5 = restopt_subject5;
	}

	public String getRestopt_subject6() {
		return restopt_subject6;
	}

	public void setRestopt_subject6(String restopt_subject6) {
		this.restopt_subject6 = restopt_subject6;
	}

	public String getRestopt_subject7() {
		return restopt_subject7;
	}

	public void setRestopt_subject7(String restopt_subject7) {
		this.restopt_subject7 = restopt_subject7;
	}

	public String getRestopt_subject8() {
		return restopt_subject8;
	}

	public void setRestopt_subject8(String restopt_subject8) {
		this.restopt_subject8 = restopt_subject8;
	}

	public String getRestopt_subject9() {
		return restopt_subject9;
	}

	public void setRestopt_subject9(String restopt_subject9) {
		this.restopt_subject9 = restopt_subject9;
	}

	public String getRestopt_subject10() {
		return restopt_subject10;
	}

	public void setRestopt_subject10(String restopt_subject10) {
		this.restopt_subject10 = restopt_subject10;
	}

	public String getRestopt_subject11() {
		return restopt_subject11;
	}

	public void setRestopt_subject11(String restopt_subject11) {
		this.restopt_subject11 = restopt_subject11;
	}

	public String getRestopt_subject12() {
		return restopt_subject12;
	}

	public void setRestopt_subject12(String restopt_subject12) {
		this.restopt_subject12 = restopt_subject12;
	}

	public String getRestopt_subject13() {
		return restopt_subject13;
	}

	public void setRestopt_subject13(String restopt_subject13) {
		this.restopt_subject13 = restopt_subject13;
	}

	public String getRestopt_subject14() {
		return restopt_subject14;
	}

	public void setRestopt_subject14(String restopt_subject14) {
		this.restopt_subject14 = restopt_subject14;
	}

	public String getRestopt_subject15() {
		return restopt_subject15;
	}

	public void setRestopt_subject15(String restopt_subject15) {
		this.restopt_subject15 = restopt_subject15;
	}

	public int getRestopt_priceplus1() {
		return restopt_priceplus1;
	}

	public void setRestopt_priceplus1(int restopt_priceplus1) {
		this.restopt_priceplus1 = restopt_priceplus1;
	}

	public int getRestopt_priceplus2() {
		return restopt_priceplus2;
	}

	public void setRestopt_priceplus2(int restopt_priceplus2) {
		this.restopt_priceplus2 = restopt_priceplus2;
	}

	public int getRestopt_priceplus3() {
		return restopt_priceplus3;
	}

	public void setRestopt_priceplus3(int restopt_priceplus3) {
		this.restopt_priceplus3 = restopt_priceplus3;
	}

	public int getRestopt_priceplus4() {
		return restopt_priceplus4;
	}

	public void setRestopt_priceplus4(int restopt_priceplus4) {
		this.restopt_priceplus4 = restopt_priceplus4;
	}

	public int getRestopt_priceplus5() {
		return restopt_priceplus5;
	}

	public void setRestopt_priceplus5(int restopt_priceplus5) {
		this.restopt_priceplus5 = restopt_priceplus5;
	}

	public int getRestopt_priceplus6() {
		return restopt_priceplus6;
	}

	public void setRestopt_priceplus6(int restopt_priceplus6) {
		this.restopt_priceplus6 = restopt_priceplus6;
	}

	public int getRestopt_priceplus7() {
		return restopt_priceplus7;
	}

	public void setRestopt_priceplus7(int restopt_priceplus7) {
		this.restopt_priceplus7 = restopt_priceplus7;
	}

	public int getRestopt_priceplus8() {
		return restopt_priceplus8;
	}

	public void setRestopt_priceplus8(int restopt_priceplus8) {
		this.restopt_priceplus8 = restopt_priceplus8;
	}

	public int getRestopt_priceplus9() {
		return restopt_priceplus9;
	}

	public void setRestopt_priceplus9(int restopt_priceplus9) {
		this.restopt_priceplus9 = restopt_priceplus9;
	}

	public int getRestopt_priceplus10() {
		return restopt_priceplus10;
	}

	public void setRestopt_priceplus10(int restopt_priceplus10) {
		this.restopt_priceplus10 = restopt_priceplus10;
	}

	public int getRestopt_priceplus11() {
		return restopt_priceplus11;
	}

	public void setRestopt_priceplus11(int restopt_priceplus11) {
		this.restopt_priceplus11 = restopt_priceplus11;
	}

	public int getRestopt_priceplus12() {
		return restopt_priceplus12;
	}

	public void setRestopt_priceplus12(int restopt_priceplus12) {
		this.restopt_priceplus12 = restopt_priceplus12;
	}

	public int getRestopt_priceplus13() {
		return restopt_priceplus13;
	}

	public void setRestopt_priceplus13(int restopt_priceplus13) {
		this.restopt_priceplus13 = restopt_priceplus13;
	}

	public int getRestopt_priceplus14() {
		return restopt_priceplus14;
	}

	public void setRestopt_priceplus14(int restopt_priceplus14) {
		this.restopt_priceplus14 = restopt_priceplus14;
	}

	public int getRestopt_priceplus15() {
		return restopt_priceplus15;
	}

	public void setRestopt_priceplus15(int restopt_priceplus15) {
		this.restopt_priceplus15 = restopt_priceplus15;
	}

	public String getRestopt_orgname() {
		return restopt_orgname;
	}

	public void setRestopt_orgname(String restopt_orgname) {
		this.restopt_orgname = restopt_orgname;
	}

	public String getRestopt_savname() {
		return restopt_savname;
	}

	public void setRestopt_savname(String restopt_savname) {
		this.restopt_savname = restopt_savname;
	}

	public File getOptupload1() {
		return optupload1;
	}

	public void setOptupload1(File optupload1) {
		this.optupload1 = optupload1;
	}

	public String getOptupload1ContentType() {
		return optupload1ContentType;
	}

	public void setOptupload1ContentType(String optupload1ContentType) {
		this.optupload1ContentType = optupload1ContentType;
	}

	public String getOptupload1FileName() {
		return optupload1FileName;
	}

	public void setOptupload1FileName(String optupload1FileName) {
		this.optupload1FileName = optupload1FileName;
	}

	public String getOptfileUploadPath1() {
		return optfileUploadPath1;
	}

	public void setOptfileUploadPath1(String optfileUploadPath1) {
		this.optfileUploadPath1 = optfileUploadPath1;
	}

	public File getOptupload2() {
		return optupload2;
	}

	public void setOptupload2(File optupload2) {
		this.optupload2 = optupload2;
	}

	public String getOptupload2ContentType() {
		return optupload2ContentType;
	}

	public void setOptupload2ContentType(String optupload2ContentType) {
		this.optupload2ContentType = optupload2ContentType;
	}

	public String getOptupload2FileName() {
		return optupload2FileName;
	}

	public void setOptupload2FileName(String optupload2FileName) {
		this.optupload2FileName = optupload2FileName;
	}

	public String getOptfileUploadPath2() {
		return optfileUploadPath2;
	}

	public void setOptfileUploadPath2(String optfileUploadPath2) {
		this.optfileUploadPath2 = optfileUploadPath2;
	}

	public File getOptupload3() {
		return optupload3;
	}

	public void setOptupload3(File optupload3) {
		this.optupload3 = optupload3;
	}

	public String getOptupload3ContentType() {
		return optupload3ContentType;
	}

	public void setOptupload3ContentType(String optupload3ContentType) {
		this.optupload3ContentType = optupload3ContentType;
	}

	public String getOptupload3FileName() {
		return optupload3FileName;
	}

	public void setOptupload3FileName(String optupload3FileName) {
		this.optupload3FileName = optupload3FileName;
	}

	public String getOptfileUploadPath3() {
		return optfileUploadPath3;
	}

	public void setOptfileUploadPath3(String optfileUploadPath3) {
		this.optfileUploadPath3 = optfileUploadPath3;
	}

	public File getOptupload4() {
		return optupload4;
	}

	public void setOptupload4(File optupload4) {
		this.optupload4 = optupload4;
	}

	public String getOptupload4ContentType() {
		return optupload4ContentType;
	}

	public void setOptupload4ContentType(String optupload4ContentType) {
		this.optupload4ContentType = optupload4ContentType;
	}

	public String getOptupload4FileName() {
		return optupload4FileName;
	}

	public void setOptupload4FileName(String optupload4FileName) {
		this.optupload4FileName = optupload4FileName;
	}

	public String getOptfileUploadPath4() {
		return optfileUploadPath4;
	}

	public void setOptfileUploadPath4(String optfileUploadPath4) {
		this.optfileUploadPath4 = optfileUploadPath4;
	}

	public File getOptupload5() {
		return optupload5;
	}

	public void setOptupload5(File optupload5) {
		this.optupload5 = optupload5;
	}

	public String getOptupload5ContentType() {
		return optupload5ContentType;
	}

	public void setOptupload5ContentType(String optupload5ContentType) {
		this.optupload5ContentType = optupload5ContentType;
	}

	public String getOptupload5FileName() {
		return optupload5FileName;
	}

	public void setOptupload5FileName(String optupload5FileName) {
		this.optupload5FileName = optupload5FileName;
	}

	public String getOptfileUploadPath5() {
		return optfileUploadPath5;
	}

	public void setOptfileUploadPath5(String optfileUploadPath5) {
		this.optfileUploadPath5 = optfileUploadPath5;
	}

	public File getOptupload6() {
		return optupload6;
	}

	public void setOptupload6(File optupload6) {
		this.optupload6 = optupload6;
	}

	public String getOptupload6ContentType() {
		return optupload6ContentType;
	}

	public void setOptupload6ContentType(String optupload6ContentType) {
		this.optupload6ContentType = optupload6ContentType;
	}

	public String getOptupload6FileName() {
		return optupload6FileName;
	}

	public void setOptupload6FileName(String optupload6FileName) {
		this.optupload6FileName = optupload6FileName;
	}

	public String getOptfileUploadPath6() {
		return optfileUploadPath6;
	}

	public void setOptfileUploadPath6(String optfileUploadPath6) {
		this.optfileUploadPath6 = optfileUploadPath6;
	}

	public File getOptupload7() {
		return optupload7;
	}

	public void setOptupload7(File optupload7) {
		this.optupload7 = optupload7;
	}

	public String getOptupload7ContentType() {
		return optupload7ContentType;
	}

	public void setOptupload7ContentType(String optupload7ContentType) {
		this.optupload7ContentType = optupload7ContentType;
	}

	public String getOptupload7FileName() {
		return optupload7FileName;
	}

	public void setOptupload7FileName(String optupload7FileName) {
		this.optupload7FileName = optupload7FileName;
	}

	public String getOptfileUploadPath7() {
		return optfileUploadPath7;
	}

	public void setOptfileUploadPath7(String optfileUploadPath7) {
		this.optfileUploadPath7 = optfileUploadPath7;
	}

	public File getOptupload8() {
		return optupload8;
	}

	public void setOptupload8(File optupload8) {
		this.optupload8 = optupload8;
	}

	public String getOptupload8ContentType() {
		return optupload8ContentType;
	}

	public void setOptupload8ContentType(String optupload8ContentType) {
		this.optupload8ContentType = optupload8ContentType;
	}

	public String getOptupload8FileName() {
		return optupload8FileName;
	}

	public void setOptupload8FileName(String optupload8FileName) {
		this.optupload8FileName = optupload8FileName;
	}

	public String getOptfileUploadPath8() {
		return optfileUploadPath8;
	}

	public void setOptfileUploadPath8(String optfileUploadPath8) {
		this.optfileUploadPath8 = optfileUploadPath8;
	}

	public File getOptupload9() {
		return optupload9;
	}

	public void setOptupload9(File optupload9) {
		this.optupload9 = optupload9;
	}

	public String getOptupload9ContentType() {
		return optupload9ContentType;
	}

	public void setOptupload9ContentType(String optupload9ContentType) {
		this.optupload9ContentType = optupload9ContentType;
	}

	public String getOptupload9FileName() {
		return optupload9FileName;
	}

	public void setOptupload9FileName(String optupload9FileName) {
		this.optupload9FileName = optupload9FileName;
	}

	public String getOptfileUploadPath9() {
		return optfileUploadPath9;
	}

	public void setOptfileUploadPath9(String optfileUploadPath9) {
		this.optfileUploadPath9 = optfileUploadPath9;
	}

	public File getOptupload10() {
		return optupload10;
	}

	public void setOptupload10(File optupload10) {
		this.optupload10 = optupload10;
	}

	public String getOptupload10ContentType() {
		return optupload10ContentType;
	}

	public void setOptupload10ContentType(String optupload10ContentType) {
		this.optupload10ContentType = optupload10ContentType;
	}

	public String getOptupload10FileName() {
		return optupload10FileName;
	}

	public void setOptupload10FileName(String optupload10FileName) {
		this.optupload10FileName = optupload10FileName;
	}

	public String getOptfileUploadPath10() {
		return optfileUploadPath10;
	}

	public void setOptfileUploadPath10(String optfileUploadPath10) {
		this.optfileUploadPath10 = optfileUploadPath10;
	}

	public File getOptupload11() {
		return optupload11;
	}

	public void setOptupload11(File optupload11) {
		this.optupload11 = optupload11;
	}

	public String getOptupload11ContentType() {
		return optupload11ContentType;
	}

	public void setOptupload11ContentType(String optupload11ContentType) {
		this.optupload11ContentType = optupload11ContentType;
	}

	public String getOptupload11FileName() {
		return optupload11FileName;
	}

	public void setOptupload11FileName(String optupload11FileName) {
		this.optupload11FileName = optupload11FileName;
	}

	public String getOptfileUploadPath11() {
		return optfileUploadPath11;
	}

	public void setOptfileUploadPath11(String optfileUploadPath11) {
		this.optfileUploadPath11 = optfileUploadPath11;
	}

	public File getOptupload12() {
		return optupload12;
	}

	public void setOptupload12(File optupload12) {
		this.optupload12 = optupload12;
	}

	public String getOptupload12ContentType() {
		return optupload12ContentType;
	}

	public void setOptupload12ContentType(String optupload12ContentType) {
		this.optupload12ContentType = optupload12ContentType;
	}

	public String getOptupload12FileName() {
		return optupload12FileName;
	}

	public void setOptupload12FileName(String optupload12FileName) {
		this.optupload12FileName = optupload12FileName;
	}

	public String getOptfileUploadPath12() {
		return optfileUploadPath12;
	}

	public void setOptfileUploadPath12(String optfileUploadPath12) {
		this.optfileUploadPath12 = optfileUploadPath12;
	}

	public File getOptupload13() {
		return optupload13;
	}

	public void setOptupload13(File optupload13) {
		this.optupload13 = optupload13;
	}

	public String getOptupload13ContentType() {
		return optupload13ContentType;
	}

	public void setOptupload13ContentType(String optupload13ContentType) {
		this.optupload13ContentType = optupload13ContentType;
	}

	public String getOptupload13FileName() {
		return optupload13FileName;
	}

	public void setOptupload13FileName(String optupload13FileName) {
		this.optupload13FileName = optupload13FileName;
	}

	public String getOptfileUploadPath13() {
		return optfileUploadPath13;
	}

	public void setOptfileUploadPath13(String optfileUploadPath13) {
		this.optfileUploadPath13 = optfileUploadPath13;
	}

	public File getOptupload14() {
		return optupload14;
	}

	public void setOptupload14(File optupload14) {
		this.optupload14 = optupload14;
	}

	public String getOptupload14ContentType() {
		return optupload14ContentType;
	}

	public void setOptupload14ContentType(String optupload14ContentType) {
		this.optupload14ContentType = optupload14ContentType;
	}

	public String getOptupload14FileName() {
		return optupload14FileName;
	}

	public void setOptupload14FileName(String optupload14FileName) {
		this.optupload14FileName = optupload14FileName;
	}

	public String getOptfileUploadPath14() {
		return optfileUploadPath14;
	}

	public void setOptfileUploadPath14(String optfileUploadPath14) {
		this.optfileUploadPath14 = optfileUploadPath14;
	}

	public File getOptupload15() {
		return optupload15;
	}

	public void setOptupload15(File optupload15) {
		this.optupload15 = optupload15;
	}

	public String getOptupload15ContentType() {
		return optupload15ContentType;
	}

	public void setOptupload15ContentType(String optupload15ContentType) {
		this.optupload15ContentType = optupload15ContentType;
	}

	public String getOptupload15FileName() {
		return optupload15FileName;
	}

	public void setOptupload15FileName(String optupload15FileName) {
		this.optupload15FileName = optupload15FileName;
	}

	public String getOptfileUploadPath15() {
		return optfileUploadPath15;
	}

	public void setOptfileUploadPath15(String optfileUploadPath15) {
		this.optfileUploadPath15 = optfileUploadPath15;
	}
	
	
}
