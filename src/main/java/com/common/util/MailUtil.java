package com.common.util;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class MailUtil {
	/**
	 * 单例模式
	 */
	private static MailUtil mailUtil; 
	public static synchronized MailUtil getInstance() { 
		if (mailUtil == null) {
			mailUtil = new MailUtil();
		}
		return mailUtil;
	}
	//360816061@qq.com    czjyyapppunacbbd
	//txvwjgcmaavzbged,hcmfxarnxdegbiga 

	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    public static String myEmailAccount = "26628450@qq.com";
    public static String myEmailPassword = "txvwjgcmaavzbged";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtp.exmail.qq.com";
    
    /**
     * 发送单个邮件
     * @param mail
     * @param title
     * @param content
     */
    public static void sendMail(String mail,String title,String content){ 
        try {
        	Properties props = new Properties(); 
            props.put("mail.smtp.auth", "true"); 
            props.put("mail.smtp.host", "smtp.qq.com"); 
            props.put("mail.smtp.port", "587"); 
            props.put("mail.user", myEmailAccount); 
            props.put("mail.password", myEmailPassword); 
            Authenticator authenticator = new Authenticator() { 
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myEmailAccount, myEmailPassword);
                }
            }; 
            Session mailSession = Session.getInstance(props, authenticator); 
            MimeMessage message = new MimeMessage(mailSession); 
            InternetAddress form = new InternetAddress(myEmailAccount);
            message.setFrom(form); 
            InternetAddress to = new InternetAddress(mail);
            message.setRecipient(RecipientType.TO, to); 
            message.setSubject(title); 
            message.setContent(content, "text/html;charset=UTF-8"); 
            Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 群发邮件
     * @param mails
     * @param title
     * @param content
     */
    public static void sendMailSomebody(String mails,String title,String content){ 
    	try {
    		if(mails == null || "".equals(mails)){
    			return;
    		}
    		String[] mailArr = mails.split(",");
        	for (int i = 0; i < mailArr.length; i++) {
        		sendMail(mailArr[i],title,content);
        		Thread.sleep(1000);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = "15024459450@139.com";

    public static void main(String[] args) throws Exception {
    	// 创建Properties 类用于记录邮箱的一些属性
        Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
        props.put("mail.smtp.port", "587");
        // 此处填写你的账号
        props.put("mail.user", myEmailAccount);
        // 此处的密码就是前面说的16位STMP口令
        props.put("mail.password", "czjyyapppunacbbd");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码 
                return new PasswordAuthentication(myEmailAccount, myEmailPassword);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        
//        boolean isEmail = checkEmail(receiveMailAccount);
//        if(!isEmail){
//        	System.out.println("邮箱验证不通过");
//        	return;
//        }
        // 设置发件人
        InternetAddress form = new InternetAddress(myEmailAccount);
        message.setFrom(form);

        // 设置收件人的邮箱
        InternetAddress to = new InternetAddress(receiveMailAccount);
        message.setRecipient(RecipientType.TO, to);

        // 设置邮件标题
        message.setSubject("测试邮件");

        // 设置邮件的内容体
        message.setContent("这是一封测试邮件", "text/html;charset=UTF-8");

        // 最后当然就是发送邮件啦
        Transport.send(message);
    }
}
