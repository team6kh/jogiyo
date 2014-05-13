package good.dto;

public class GoodCommonDTO
{
	/* Good Board need DTO*/
	
	//﻿ 자바빈의 게시판관련 프로퍼티(멤버변수) 선언 
	private	int	num;      //글번호
	private	String	writer;      //작성자
	private	String	Subject;      //글제목
	private	String	email;      //이메일
	private	String	content;      //내용
	private	String	pw;      //비밀번호
	private	int	reg_date;      //작성일
	private	int	readcount;      //조회수
	private	int	re_step;      //글순위
	private	int	ref;      //글의 그룹
	private	int	page_step;      //글 우선순위
	
	// 자바빈의 메소드 선언 (getter & setter)
	public int getNum()
	{
		return num;
	}
	public void setNum(int num)
	{
		this.num = num;
	}
	public String getWriter()
	{
		return writer;
	}
	public void setWriter(String writer)
	{
		this.writer = writer;
	}
	public String getSubject()
	{
		return Subject;
	}
	public void setSubject(String subject)
	{
		Subject = subject;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getPw()
	{
		return pw;
	}
	public void setPw(String pw)
	{
		this.pw = pw;
	}
	public int getReg_date()
	{
		return reg_date;
	}
	public void setReg_date(int reg_date)
	{
		this.reg_date = reg_date;
	}
	public int getReadcount()
	{
		return readcount;
	}
	public void setReadcount(int readcount)
	{
		this.readcount = readcount;
	}
	public int getRe_step()
	{
		return re_step;
	}
	public void setRe_step(int re_step)
	{
		this.re_step = re_step;
	}
	public int getRef()
	{
		return ref;
	}
	public void setRef(int ref)
	{
		this.ref = ref;
	}
	public int getPage_step()
	{
		return page_step;
	}
	public void setPage_step(int page_step)
	{
		this.page_step = page_step;
	}
	
}
