package b2b.gettingNewCustomers;

public class SMSSending {

    public static String getMessage_001(int id){
        String message="";

        message+="לקוחותינו היקרים!!!"+"\n";
        message+="אגם רימון משתדרגת במודל הזמנות אינטרנטי..."+"\n";
        message+="מעתה תוכלו לעשות הזמנות גם דרך אתר החברה ו/או דרך דפדפן"+"\n";
        message+="מכל מחשב או מכל טאבלט"+"\n";
        message+="הסוכנים ישארו זמינים כבעבר לכל בקשה ועניין..."+"\n";
        message+="על מנת להתחיל את התהליך אנא לחצ/י על הלינק הבא..."+"\n";
        message+= "http://AgamRimon.com/NewB2BCustomers/New_B2B_User.html?ID="+id;

        return message;
    }

}
