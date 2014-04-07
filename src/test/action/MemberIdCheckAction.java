package test.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

// @WebServlet("/member/member_id_check_action.controller")
public class MemberIdCheckAction extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               String id = request.getParameter("id");
               int checkNum = 0;
               if (id.equals("admin")){
            	   checkNum = 1;
               }
               // int checkNum = new MemberBo().idCheck(id);   //아이디가 존재하는지 체크 1:중복 0:중복아님
               boolean checkResult = false;  //체크결과 기본설정 : false
               if(checkNum == 0){     //중복아닐때 실행
                       checkResult=true;      //체크결과 : true
               }
               request.setAttribute("checkResult", checkResult);
               RequestDispatcher rd = request.getRequestDispatcher("/test/testForm.jsp");
               rd.forward(request, response); //포워딩
        }
}
