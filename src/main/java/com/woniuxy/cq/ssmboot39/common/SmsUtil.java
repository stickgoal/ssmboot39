package com.woniuxy.cq.ssmboot39.common;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * http://uuxn.com/twilio-toll-free-sms
 * 使用twilio发送短信
 */
public class SmsUtil {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "{}";
    public static final String AUTH_TOKEN = "{}";
    //在twilio的个人页面上获取的trail number
    public static final String TRIAL_PHONE_NUMBER="{}";

    public void send(String to,String text){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(to),
                new PhoneNumber(TRIAL_PHONE_NUMBER),
                text).create();
    }





    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+8615023135322"),
                new PhoneNumber("+12077460991"),
                "您好，这是测试短信，收到请勿惊慌").create();

        System.out.println(message.getSid());
    }




}
