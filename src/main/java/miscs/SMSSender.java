package miscs;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SMSSender {

    public static boolean sendSMS(String originNumber, String destinationNumber, String message){

        try {
            message= URLEncoder.encode(message, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        originNumber=removeHyphensFromPhoneNumber(originNumber);
        destinationNumber=removeHyphensFromPhoneNumber(destinationNumber);

        String url="https://sapi.itnewsletter.co.il/api/restApiSms/sendSmsToRecipients?ApiKey=7r4SL99diW22354990jNxM843q6Rp8B7&txtOriginator="+originNumber;
        url+="&destinations="+destinationNumber+"&txtSMSmessage="+message+"&dteToDeliver=&txtAddInf=local";

        String page = Tools.ServletTools.getSimplyWebPage(url);

        if(page==null || page.indexOf("success\":true")==-1)
            return false;

        return true;

    }
    private static String removeHyphensFromPhoneNumber(String phoneNumber){

        String str="";

        char[] charArray = phoneNumber.toCharArray();

        for(Character c:charArray)
            if(Character.isDigit(c) || Character.isLetter(c))
                str+=c;

        return str;
    }

}
