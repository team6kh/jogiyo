package test.action;

import com.opensymphony.xwork2.ActionSupport;

public class QuizAction extends ActionSupport {
    private String name;
    private int age;
    private String answer;
    
    public String execute() throws Exception {
    	return SUCCESS;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
