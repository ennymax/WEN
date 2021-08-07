package Base;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import static utility.Utility.fetchProperty;

public class Email extends TestBase {

    public static void SendReportEndPoint(String RecipientEmail, String ProjectName, int f, int k) throws IOException {

        String from = fetchProperty(RecipientEmail).toString();
        String Projectname = fetchProperty(ProjectName).toString();

        final String username = fetchProperty("HostEmail").toString();
        final String password = fetchProperty("HostPassword").toString();

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ennymat25@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(from));

            message.setSubject("Test Report For :: " + Projectname);
            BodyPart messageBodyPart1 = new MimeBodyPart();
            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setContent("<h1>" + "Test Summary " + "</h1>", "text/html");
            messageBodyPart1.setContent("<h1>" + "Passed:" + f + " Failed:" + k + " "+ "</h1>", "text/html");

            BodyPart messa = new MimeBodyPart();

            messa.setText(fetchProperty("MessageBodyAPI").toString());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            File filename = getTheNewestFile("./Report/", "html");
            String name = "COM_API_PROD.html";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(name);
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(messa);

            message.setContent(multipart);

            System.out.println("*************************************Sending Report******************************************");
            Transport.send(message);
            System.out.println("****************************************Done***************************************************");

        } catch (MessagingException e) {
            System.out.println("*************************************Failed to Send Report******************************************");
            throw new RuntimeException(e);
        }
    }


    public static void SendReport(String RecipientEmail, String ProjectName, int f, int k, int t, String Percent) throws IOException {
        String Host = fetchProperty("HostEmail").toString();
        String from = fetchProperty(RecipientEmail).toString();
        String Projectname = fetchProperty(ProjectName).toString();

        final String username = fetchProperty("HostEmail").toString();
        final String password = fetchProperty("HostPassword").toString();

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ennymat25@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(from));

            message.setSubject("Test Report For :: " + Projectname);
            BodyPart messageBodyPart1 = new MimeBodyPart();
            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setContent("<h1>" + "Test Summary " + "</h1>", "text/html");
            messageBodyPart1.setContent("<h1>" + "Passed:" + f + " Failed:" + k + " Skipped:" + t + " "+ Percent + "</h1>", "text/html");

            BodyPart messa = new MimeBodyPart();

            messa.setText(fetchProperty("MessageBody").toString());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            File filename = getTheNewestFile("./Report/", "html");
            String name = "CicodReport.html";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(name);
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(messa);

            message.setContent(multipart);

            System.out.println("*************************************Sending Report******************************************");
            Transport.send(message);
            System.out.println("****************************************Done***************************************************");

        } catch (MessagingException e) {
            System.out.println("*************************************Failed to Send Report******************************************");
            throw new RuntimeException(e);
        }
    }

    public static void SendReportCCCCC(String RecipientEmail, String RecipientEmailC1, String RecipientEmailC2 , String RecipientEmailC3,String RecipientEmailC4, String ProjectName, int f, int k, int t, String Percent) throws IOException {
        String Projectname = fetchProperty(ProjectName).toString();

        String Host = fetchProperty("HostEmail").toString();
        String from = fetchProperty(RecipientEmail).toString();
        String from1 = fetchProperty(RecipientEmailC1).toString();
        String from2 = fetchProperty(RecipientEmailC2).toString();
        String from3 = fetchProperty(RecipientEmailC3).toString();
        String from4 = fetchProperty(RecipientEmailC3).toString();

        final String username = fetchProperty("HostEmail").toString();
        final String password = fetchProperty("HostPassword").toString();

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Host));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(from));
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(from1));
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(from2));
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(from3));
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(from3));

            message.setSubject("Test Report For :: " + Projectname);
            BodyPart messageBodyPart1 = new MimeBodyPart();
            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setContent("<h1>" + "Test Summary " + "</h1>", "text/html");
            messageBodyPart1.setContent("<h1>" + "Passed:" + f + " Failed:" + k + " Skipped:" + t + " "+ Percent + "</h1>", "text/html");

            BodyPart messa = new MimeBodyPart();

            messa.setText(fetchProperty("MessageBody").toString());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            File filename = getTheNewestFile("./Report/", "html");
            String name = "CicodReport";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(name);
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(messa);

            message.setContent(multipart);

            System.out.println("*************************************Sending Report******************************************");
            Transport.send(message);
            System.out.println("****************************************Done***************************************************");

        } catch (MessagingException e) {
            System.out.println("*************************************Failed to Send Report******************************************");
            throw new RuntimeException(e);
        }
    }

    public static File getTheNewestFile(String filePath, String ext) {
        File theNewestFile = null;
        File dir = new File(filePath);
        FileFilter fileFilter = new WildcardFileFilter("*." + ext);
        File[] files = dir.listFiles(fileFilter);

        assert files != null;
        if (files.length > 0) {

            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            theNewestFile = files[0];
        }

        return theNewestFile;
    }

    public static void DeleteNewestFile(String filePath, String ext) {
        File theNewestFile = null;
        File dir = new File(filePath);
        FileFilter fileFilter = new WildcardFileFilter("*." + ext);
        File[] files = dir.listFiles(fileFilter);

        assert files != null;
        if (files.length > 0) {

            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            theNewestFile = files[0];
        }

        if (theNewestFile != null) {
            theNewestFile.delete();
        }
        System.out.println("############################ FILE DELETED ############################");
    }

    public static void CheckFileDownloaded(String filePath, String ext) throws InterruptedException {
        Thread.sleep(5000);
        File theNewestFile = null;
        File dir = new File(filePath);
        FileFilter fileFilter = new WildcardFileFilter("*." + ext);
        File[] files = dir.listFiles(fileFilter);

        assert files != null;
        if (files.length > 0) {
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            theNewestFile = files[0];

          //  test.log(Status.PASS, " Downloaded File ::: " + theNewestFile.getName());
           // test.log(Status.INFO, " File Saved In ::: " + theNewestFile.getPath());
        }

        if (files.length==0) {
           // test.log(Status.FAIL, " Download Failed");
        }
    }
}
