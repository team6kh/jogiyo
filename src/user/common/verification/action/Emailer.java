package user.common.verification.action;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.opensymphony.xwork2.ActionSupport;
import common.RNG;

public class Emailer extends ActionSupport
{    
    private String ev_code; // email verification code
    
    /* emailer 액션으로 넘어오는 파라미터 */
    private String user_type;
    private String user_id;
    
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
        System.out.println("Emailer execute()");
        
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
            /* 파라미터 설정 */
            RNG rng = new RNG();            
            setEv_code(rng.sEv_code());
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
            /* StringBuffer를 사용해 body를 작성한다. */
            StringBuffer sbBody = new StringBuffer();
            sbBody.append("Taz'dingo, we want to verify that you are indeed \"");
            sbBody.append(getBuyer_name());
            sbBody.append("\". if that's the case, please type the following verification code: ");
            sbBody.append(getEv_code());
            sbBody.append(". if you're not ");
            sbBody.append(getBuyer_name());
            sbBody.append(" or didn't request verification, you can ignore this email.");
            /* END StringBuffer */
            setBody(sbBody.toString());
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
    public String getUser_type()
    {
        return user_type;
    }

    public void setUser_type(String user_type)
    {
        this.user_type = user_type;
    }
    
    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
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