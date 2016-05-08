package shop.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件的工具类
 * Created by near on 2016/3/1.
 *
 * @author Near
 * @version 1.0
 */
public class MailUtils {
    /**
     * 发送邮件的方法
     *
     * @param to   :收件人
     * @param code :激活码
     */
    public static void sendMail(String to, String code) {
        /**
         * 获得连接对象 Session
         */
        Properties props = new Properties();
        props.setProperty("mail.host", "localhost");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("service@near.com", "root");
            }
        });

        /**
         * 创建邮件对象
         */
        Message message = new MimeMessage(session);
        try {
            /**
             * 设置发件人
             */
            message.setFrom(new InternetAddress("service@near.com"));
            /**
             * 设置收件人
             *      RecipientType：抄送 CC   密送BCC
             */
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            /**
             * 设置标题:
             *
             */
            message.setSubject("来自Near购物商城官方激活邮件");
            /**
             * 设置邮件正文
             */
            message.setContent("<h1>Near购物商城官方激活邮件! <small>请点击下面链接完成激活操作!</small></h1><h3>" +
                            "<a href='http://localhost:8080/user_active.dao?code=" + code + "'>" +
                            "http://localhost:8080/user_active.dao?code=" + code + "</a></h3>",
                    "text/html;charset=UTF-8");
            /**
             * 发送邮件
             */
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
