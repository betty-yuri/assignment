package com.selenium.assignment.utils;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail {

    protected static Session session;
    protected static URLName  urln;
    static Folder inbox;


    public Mail()
    {
        // Get a Properties object
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.pop3.socketFactory.fallback", "false");
        props.setProperty("mail.pop3.port", "995");
        props.setProperty("mail.pop3.socketFactory.port", "995");

        session = Session.getDefaultInstance(props,null);
        urln = new URLName("pop3","mailosaur.io",995,null,"1eov1bgh@mailosaur.io",
                "hUx9cTb3");
    }


    public ArrayList<String> checkInbox(String emailSubject)
        throws MessagingException, IOException, java.text.ParseException {
        Store store = session.getStore(urln);
        store.connect();
        inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
        FetchProfile profile = new FetchProfile();
        profile.add(FetchProfile.Item.ENVELOPE);
        Message[] messages = inbox.getMessages();
        String content = null;
        System.out.println("Email Number " + messages.length);
        ArrayList<String>  links = new ArrayList<String>();
        int n = messages.length;
        String subject = messages[n-1].getSubject();
        Date date = messages[n-1].getSentDate();

        if (subject.contains(emailSubject)  )
        {
            MimeMultipart multipart = (MimeMultipart) messages[n-1].getContent();
            int size = multipart.getCount();
            String desc = multipart.getBodyPart(1).getContent().toString();
            System.out.println("emailSubject: " + emailSubject);
            Pattern linkPattern = Pattern.compile("href=\"(.*?)\"",
                    Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
            Matcher pageMatcher = linkPattern.matcher(desc);
            while(pageMatcher.find()){
                links.add(pageMatcher.group(1));
            }
            links = links;
            System.out.println("---------------------------------");
            System.out.println("Email Number " + (n));
            System.out.println("Subject " + subject);
            System.out.println("links: " + links.get(1) );
        }
         System.out.println("return" + links);
        return links;

       // closeFolder(inbox);

    }

    public String retrieveURL( ArrayList<String> links)
    {
        String activation = links.get(1);
        System.out.println("retrieve" + activation);
        return activation;
    }

    private static void closeFolder(Folder inbox) {
        try {
            if (inbox != null)
                inbox.close(false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
