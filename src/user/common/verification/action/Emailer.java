package user.common.verification.action;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.opensymphony.xwork2.ActionSupport;

public class Emailer extends ActionSupport
{    
    private String ev_code; // email verification code
    
    /* emailer 액션으로 넘어오는 파라미터 */
    private String userType;
    private String userId;
    
    private String buyer_name;
    private String buyer_email;
    
    private String seller_name;
    private String seller_email;
    /**/

    private String from; // The email address of the sender
    private String password; // The password of the above account
    private String to; // Who to send the email to?
    private String subject; // subject of the email
    private String body; // The actual email message   

    static Properties properties = new Properties();
    static
    {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
    }

    public String execute()
    {
        String ret = SUCCESS;
        try
        {
            Session session = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator()
                    {
                        protected PasswordAuthentication getPasswordAuthentication()
                        {
                            return new PasswordAuthentication(from, password);
                        }
                    });

            Message message = new MimeMessage(session);
            /* 파라미터 임시 설정 */
            setEv_code("1234");
            setFrom("team6kh@gmail.com");
            setPassword("dkagh1234.");
            if (getBuyer_email() != null)
            {
                setTo(getBuyer_email());
            } else if (getSeller_email() != null)
            {
                setTo(getSeller_email());
            }
            setSubject("[JOGIYO] Please verify your email '" + getBuyer_email() + "'");
            setBody("Taz'dingo, we want to verify that you are indeed \""
                    + getBuyer_name() 
                    + "\". if that's the case, please type the following verification code: "
                    + getEv_code()
                    + ". if you're not "
                    + getBuyer_name()
                    + " or didn't request verification, you can ignore this email."
                    );
            /* 파라미터 임시 설정 끝 */
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            //System.out.println("getUserType():"+getUserType());
            //System.out.println("getUserId():"+getUserId());
            Transport.send(message);
        } catch (Exception e)
        {
            ret = ERROR;
            e.printStackTrace();
        }
        return ret;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public static Properties getProperties()
    {
        return properties;
    }

    public static void setProperties(Properties properties)
    {
        Emailer.properties = properties;
    }

    /* emailer 액션으로 넘어오는 파라미터 */
    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }
    
    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getBuyer_name()
    {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name)
    {
        this.buyer_name = buyer_name;
    }
    
    public String getBuyer_email()
    {
        return buyer_email;
    }

    public void setBuyer_email(String buyer_email)
    {
        this.buyer_email = buyer_email;
    }
    
    public String getSeller_name()
    {
        return seller_name;
    }

    public void setSeller_name(String seller_name)
    {
        this.seller_name = seller_name;
    }

    public String getSeller_email()
    {
        return seller_email;
    }

    public void setSeller_email(String seller_email)
    {
        this.seller_email = seller_email;
    }        
    /**/    

    public String getEv_code()
    {
        return ev_code;
    }

    public void setEv_code(String ev_code)
    {
        this.ev_code = ev_code;
    }
}