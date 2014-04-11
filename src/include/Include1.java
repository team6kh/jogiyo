package include;

public class Include1 {
    private String message;
    
    public String getMessage(){
        return message;
    }
    
    public String execute(){
        this.message = "include1";
        return "success";
    }
}

