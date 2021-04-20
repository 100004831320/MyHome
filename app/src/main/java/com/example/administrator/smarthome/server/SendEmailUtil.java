package com.example.administrator.smarthome.server;



import java.util.Date;
import java.util.Properties;
import java.util.Random;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Administrator on 2021/4/20 0020.
 */

/*public class SendEmailUtil {
    public SendEmailUtil(){}
    public int sendEmail(String emailaddress) throws MessagingException {
        try {
            Random random = new Random();
            int code = random.nextInt(9000)+1000;
            //创建连接对象 连接到邮件服务器
            Properties properties = new Properties();
                    //设置发送邮件的基本参数
                    //发送邮件服务器(注意，此处根据你的服务器来决定，如果使用的是QQ服务器，请填写smtp.qq.com)
                    properties.put("mail.smtp.host", "smtp.qq.com");
                    //发送端口（根据实际情况填写，一般均为25）
                    properties.put("mail.smtp.port", "25");
                    properties.put("mail.smtp.auth", "true");
                    //设置发送邮件的账号和密码
                    Session session = Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            //两个参数分别是发送邮件的账户和密码(注意，如果配置后不生效，请检测是否开启了 POP3/SMTP 服务，QQ邮箱对应设置位置在: [设置-账户-POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务])
                            return new PasswordAuthentication("412473895@qq.com","lyx19990321.");
                        }
                    });
                    //创建邮件对象
                    MimeMessage message = new MimeMessage(session);
                    //设置发件人
                    message.setFrom(new InternetAddress("admin@huic188.com"));
                    //设置收件人
                    message.setRecipient(Message.RecipientType.TO,new InternetAddress(emailaddress));
                    //设置主题
                    message.setSubject("这是一份测试邮件");
                    //设置邮件正文  第二个参数是邮件发送的类型
                    message.setContent(code,"text/html;charset=UTF-8");
                    //发送一封邮件
                    Transport.send(message);
            /*HtmlEmail email = new HtmlEmail();// 不用更改
            email.setHostName("smtp.qq.com");// 需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.addTo(emailaddress);// 收件地址

            email.setFrom("412473895@qq.com", "智能家居");// 此处填邮箱地址和用户名,用户名可以任意填写
            //hhhjkkkhhjklj（IMAP/SMTP服务）---这里需要改为你自己
            email.setAuthentication("412473895@qq.com", "gkikktsnpjagcagc");// 此处填写邮箱地址和客户端授权码

            email.setSubject("智能家居");// 此处填写邮件名，邮件名可任意填写
            email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + code);// 此处填写邮件内容

            email.send();*/
/*            return code;
        } catch (Exception e) {
            return 0;
        }
    }
}*/

public class SendEmailUtil {

        // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
        // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
        //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
        public static String myEmailAccount = "412473895@qq.com";
        public static String myEmailPassword = "gkikktsnpjagcagc";

        // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
        // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
        public static String myEmailSMTPHost = "smtp.qq.com";

        // 收件人邮箱（替换为自己知道的有效邮箱）
        public static String receiveMailAccount = "2129243562.com";

        public  void sendEmail(String emailaddress) throws Exception {

                receiveMailAccount = emailaddress;

                // 1. 创建参数配置, 用于连接邮件服务器的参数配置
                Properties props = new Properties();                    // 参数配置
                props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
                props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
                props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

                // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
                //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
                //     打开下面 /* ... */ //之间的注释代码, 开启 SSL 安全连接。

                // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
                //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
                //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
                final String smtpPort = "465";
                props.setProperty("mail.smtp.port", smtpPort);
                props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.setProperty("mail.smtp.socketFactory.fallback", "false");
                props.setProperty("mail.smtp.socketFactory.port", smtpPort);


                // 2. 根据配置创建会话对象, 用于和邮件服务器交互
                Session session = Session.getDefaultInstance(props);
                session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

                // 3. 创建一封邮件
                MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

                // 4. 根据 Session 获取邮件传输对象
                Transport transport = session.getTransport();

                // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
                //
                //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
                //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
                //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
                //
                //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
                //           (1) 邮箱没有开启 SMTP 服务;
                //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
                //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
                //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
                //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
                //
                //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
                transport.connect(myEmailAccount, myEmailPassword);

                // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
                transport.sendMessage(message, message.getAllRecipients());

                // 7. 关闭连接
                transport.close();
        }

        /*public static void main(String[] args) throws Exception {
            // 1. 创建参数配置, 用于连接邮件服务器的参数配置
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

            // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
            //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
            //     打开下面 /* ... */ //之间的注释代码, 开启 SSL 安全连接。

                // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
                //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
                 //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
                /*final String smtpPort = "465";
                props.setProperty("mail.smtp.port", smtpPort);
                props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.setProperty("mail.smtp.socketFactory.fallback", "false");
                props.setProperty("mail.smtp.socketFactory.port", smtpPort);


                // 2. 根据配置创建会话对象, 用于和邮件服务器交互
                Session session = Session.getDefaultInstance(props);
                session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

                // 3. 创建一封邮件
                MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

                // 4. 根据 Session 获取邮件传输对象
                Transport transport = session.getTransport();

                // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
                //
                //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
                //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
                //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
                //
                //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
                //           (1) 邮箱没有开启 SMTP 服务;
                //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
                //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
                //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
                //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
                //
                //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
                transport.connect(myEmailAccount, myEmailPassword);

                // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
                transport.sendMessage(message, message.getAllRecipients());

                // 7. 关闭连接
                transport.close();
        }*/

/**
 * 创建一封只包含文本的简单邮件
 *
 * @param session 和服务器交互的会话
 * @param sendMail 发件人邮箱
 * @param receiveMail 收件人邮箱
 * @return
 * @throws Exception
 */
public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "某宝网", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("打折钜惠", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("XX用户你好, 今天全场5折, 快来抢购, 错过今天再等一年。。。", "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
        }


}