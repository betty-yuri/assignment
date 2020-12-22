package com.selenium.assignment.utils;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class PhoneMessage {

    public static final String ACCOUNT_SID = "ACc9d05f3c9ed0396d9cbd61c52ea13f26";
    public static final String AUTH_TOKEN = "cf13902f6c7ec365c0224ff179774405";

    public  String retrieveCode(){
        String code = null;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ResourceSet<Message> messages = Message.reader()
                .setTo(new com.twilio.type.PhoneNumber("+12256382438"))
                .limit(1).read();
        for(Message record : messages) {
             code = record.getBody();
            System.out.println( "id "+record.getSid());
            System.out.println("body "+record.getBody());

        }
        code= code.substring(0,6);
        System.out.println( "id "+ code);
    return code;

    }
}

