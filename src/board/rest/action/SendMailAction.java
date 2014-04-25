package board.rest.action;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.opensymphony.xwork2.Action;
import common.ConDAOAware;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.internet.*;
import java.util.*;
import javax.mail.*;


public class SendMailAction  implements Action, ConDAOAware {
	private SqlMapClient sqlMapper;
	private String rest_writer_email;
	private String email;
	private String content;
	private int currentPage;
	private int  rest_num;
	private String session_id;
	
	
	public void setConDAO(SqlMapClient sqlMapper) {
		this.sqlMapper = sqlMapper;
	}
	
	public String form() throws Exception {
		return SUCCESS; //mailer.jsp
	}
	
	public String execute() throws Exception {
		
		String host = "smtp.gmail.com";//smtp 서버
	    String subject = getSession_id()+"이 문의하신 글입니다.";
	    String from =  "team6kh@gmail.com";//문의하는 회원
	    String to1 =getRest_writer_email(); //받는 사람
	    String img_main = "http://blogfiles.naver.net/20140425_61/ljw7426_1398389790756PQfsf_PNG/%C1%A6%B8%F1.png";
	    String putter = "http://blogfiles.naver.net/20140425_268/ljw7426_1398389791006NHcfO_PNG/%C7%AA%C5%CD.png";
	    
	    String body = "<img src='"+img_main+"' /> <br/>"
								+"<br/><br/>"
								+ "<b>문의 내용</b><br/>"+getContent()+"<br/>"
								+ "<b>답변 메일</b> : "+getEmail()+"<br/>"
								+"<br/><hr><br/>"
								+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
								+"자주 묻는 질문 확인은 <a href = 'http://localhost:8000/jogiyo/listQna.action'>여기</a>를 클릭하세요. "
								+"빠른 시간내에 답변드리겠습니다. 감사합니다. <br/><hr><br/>"
								+"<img src='"+putter+"' /> <br/>";
	    try{
	           // 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
			   Properties props = new Properties();
			   
			   // G-Mail SMTP 사용시
			   props.put("mail.smtp.starttls.enable","true");
			   props.put("mail.transport.protocol", "smtp");
			   props.put("mail.smtp.host", host);
			   props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.port", "465");
			   // props.put("mail.smtp.user", from);
			   props.put("mail.smtp.auth", "true");
			   
			   Session mailSession = Session.getInstance(props,
			 		  new javax.mail.Authenticator() {
			 			protected PasswordAuthentication getPasswordAuthentication() {
			 				return new PasswordAuthentication("nathan2yo@gmail.com", "wodnr6435");
			 			}
			 		  });
			   
			   Message msg = new MimeMessage(mailSession);
			   msg.setFrom(new InternetAddress(from, MimeUtility.encodeText("팀6관리자","euc-kr","B")));//보내는 사람 설정
			   
			   InternetAddress[] address1 = {new InternetAddress(to1)};
			   
			   msg.setRecipients(Message.RecipientType.TO, address1);//받는 사람설정
			   msg.setSubject(subject);// 제목 설정
			   msg.setSentDate(new java.util.Date());// 보내는 날짜 설정
			   msg.setContent(body,"text/html; charset=euc-kr"); // 내용 설정 (HTML 형식)
			   Transport.send(msg); // 메일 보내기
	           
		} catch (MessagingException ex) {
			System.out.println("메일 발송에 실패 하였습니다.");
		} catch (Exception e) {
			System.out.println("메일 발송에 실패 하였습니다.");
		}
		
		return SUCCESS; //mailerPro.jsp
	}


	
	public String getRest_writer_email() {
		return rest_writer_email;
	}
	public void setRest_writer_email(String rest_writer_email) {
		this.rest_writer_email = rest_writer_email;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}


	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRest_num() {
		return rest_num;
	}
	public void setRest_num(int rest_num) {
		this.rest_num = rest_num;
	}
	
}
