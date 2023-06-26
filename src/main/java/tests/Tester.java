package tests;


import b2b.gettingNewCustomers.SMSSending;
import entities.B2BCustomer;
import jakarta.persistence.EntityManager;
import miscs.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;


public class Tester {

    public static void main(String[] args) {

//        get70EmailFromFile();
////        sendSMSs();
        sendEmails();
//
//
////        printGotMailButNeedToSendEmailCount();
//       printTotalEmailsCount();
////        printNeedToSendSmsCount();
////        printSmsThatWasSentCount();
//
//
    }

    public static void get70EmailFromFile() {
        EntityManager em = EntitiesSuite.AgamRimon___MainSchema___OnRemote___FromLocal.getEntityManager();

        ArrayList<B2BCustomer> allEntities = Tools.DBTools.getAllEntities(em, new B2BCustomer());

        File file = R.Rs.fd.get2WayFile().file;

        try {
            Workbook workbook = new HSSFWorkbook(Files.newInputStream(file.toPath()));

            Sheet missingSheet = workbook.getSheetAt(0);

int iii=0;
int sameEmail=0;
            for (int i = 1; i < 118; i++) {
                Row row = missingSheet.getRow(i);
                String cNum = row.getCell(1).getStringCellValue();
                String cName = row.getCell(2).getStringCellValue();
                String cEmail = row.getCell(3).getStringCellValue();

                B2BCustomer customer = getCustomerByNum(allEntities, Integer.parseInt(cNum));

                if (customer == null) {
                    System.out.println("should not be null");
                    iii++;
                }else{
                    String email=customer.getEmail();
                    if(email==null){

                        customer.setEmail(cEmail);
                        Tools.DBTools.persistEntity(customer, em);

                    }







                }

            }

            System.out.println("done"+iii+"  "+sameEmail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static B2BCustomer getCustomerByNum(ArrayList<B2BCustomer> allEntities, int num) {

        for (B2BCustomer b2 : allEntities)
            if (b2.getCustomerCardCode() == num)
                return b2;

        return null;
    }


    public static void printSmsThatWasSentCount() {
        EntityManager entityManager = EntitiesSuite.AgamRimon___MainSchema___OnRemote___FromLocal.getEntityManager();

        ArrayList<B2BCustomer> allEntities = Tools.DBTools.getAllEntities(entityManager, new B2BCustomer());

        int count = 0;

        for (B2BCustomer b2bCustomer : allEntities) {
            if (b2bCustomer.isSendSMS() && b2bCustomer.isSmsWasSent())
                count++;

        }

        System.out.println(count);
    }

    public static void printNeedToSendSmsCount() {
        EntityManager entityManager = EntitiesSuite.AgamRimon___MainSchema___OnRemote___FromLocal.getEntityManager();

        ArrayList<B2BCustomer> allEntities = Tools.DBTools.getAllEntities(entityManager, new B2BCustomer());

        int count = 0;

        for (B2BCustomer b2bCustomer : allEntities) {
            if (b2bCustomer.isSendSMS() && !b2bCustomer.isSmsWasSent())
                count++;

        }

        System.out.println(count);
    }

    public static void printGotMailButNeedToSendEmailCount() {
        EntityManager entityManager = EntitiesSuite.AgamRimon___MainSchema___OnRemote___FromLocal.getEntityManager();

        ArrayList<B2BCustomer> allEntities = Tools.DBTools.getAllEntities(entityManager, new B2BCustomer());

        int count = 0;

        for (B2BCustomer b2bCustomer : allEntities) {
            if (b2bCustomer.getEmail() != null && !b2bCustomer.isEmailWasSent())
                count++;

        }

        System.out.println(count);
    }

    public static void printTotalEmailsCount() {
        EntityManager entityManager = EntitiesSuite.AgamRimon___MainSchema___OnRemote___FromLocal.getEntityManager();

        ArrayList<B2BCustomer> allEntities = Tools.DBTools.getAllEntities(entityManager, new B2BCustomer());

        int gotEmailCount = 0;

        for (B2BCustomer b2bCustomer : allEntities) {
            if (b2bCustomer.getEmail() != null)
                gotEmailCount++;

        }

        System.out.println(gotEmailCount);
    }

    public static void sendEmails() {
        EntityManager entityManager = EntitiesSuite.AgamRimon___MainSchema___OnRemote___FromLocal.getEntityManager();

        ArrayList<B2BCustomer> allEntities = Tools.DBTools.getAllEntities(entityManager, new B2BCustomer());

        ArrayList<B2BCustomer> onlyGoodOnes = new ArrayList<>();

        for (B2BCustomer b2BCustomer : allEntities)
            if (b2BCustomer.isEmailWasSent() == false && b2BCustomer.getEmail() != null && b2BCustomer.getPasswordOnAmodat() != null)
                onlyGoodOnes.add(b2BCustomer);


        for (B2BCustomer b2BCustomer : onlyGoodOnes) {
            File file = R.Rs._Email_001.get2WayFile().file;
            String s = Tools.MiscTools.readTextFileAString(file);

            s = s.replace("logoLink", "http://agamrimon.com/ImageServer?imageName=Agam-Logo.png&customerCardCode=" + b2BCustomer.getCustomerCardCode());
            s = s.replace("desktopLink", "http://agamrimon.com/DesktopAppLaunched?customerCardCode=" + b2BCustomer.getCustomerCardCode());
            s = s.replace("storeLink", "http://agamrimon.com/AppInstall?customerCardCode=" + b2BCustomer.getCustomerCardCode());
            s = s.replace("***UserName***", b2BCustomer.getEmail().trim());
            s = s.replace("***UserPassword***", b2BCustomer.getPasswordOnAmodat());

            AgamMailer.send(b2BCustomer.getEmail().trim(), "פרטי התחברות - אגם רימון", s);
            System.out.println("Email was sent to " + b2BCustomer.getEmail().trim());

            b2BCustomer.setEmailWasSent(true);
            Tools.DBTools.persistEntity(b2BCustomer, entityManager);

        }


    }


    public static void sendSMSs() {

        EntityManager entityManager = EntitiesSuite.AgamRimon___MainSchema___OnRemote___FromLocal.getEntityManager();

        ArrayList<B2BCustomer> allEntities = Tools.DBTools.getAllEntities(entityManager, new B2BCustomer());

        ArrayList<B2BCustomer> onlyGoodOnes = new ArrayList<>();

        for (B2BCustomer b2BCustomer : allEntities)
            if (b2BCustomer.isSendSMS() == true && b2BCustomer.isSmsWasSent() == false)
                onlyGoodOnes.add(b2BCustomer);


        for (B2BCustomer b2BCustomer : onlyGoodOnes) {
            SMSSender.sendSMS("AgamRimon", b2BCustomer.getMobile(), SMSSending.getMessage_001(b2BCustomer.getCustomerCardCode()));
            b2BCustomer.setSmsWasSent(true);
            Tools.DBTools.persistEntity(b2BCustomer, entityManager);

        }


    }


    public static void insertOnlyNewest() {
//        try {
//            EntityManager em = EntitiesSuite.AgamRimon___MainSchema___OnRemote___FromLocal.getEntityManager();
//            em.getTransaction().begin();
//            Workbook missingRecords = new HSSFWorkbook(Files.newInputStream(R.Rs._30New.get2WayFile().file.toPath()));
//            Sheet missingSheet = missingRecords.getSheetAt(0);
////
//            for (int i = 0; i < 30; i++) {
//                Row row = missingSheet.getRow(i);
//                String a=row.getCell(0).getStringCellValue();
//                String b=row.getCell(1).getStringCellValue();
//                String c=row.getCell(2).getStringCellValue();
//                String d=row.getCell(3).getStringCellValue();
//
//
//                B2BCustomer customer=new B2BCustomer();
//
//                customer.setCustomerCardCode(Integer.parseInt(a));
//                customer.setStoreName(b);
//                customer.setStoreOwnerName("");
//                customer.setAgentAssigned(Integer.parseInt(c));
//                customer.setMobile(d);
//
//                em.persist(customer);
//            }
//
//            em.getTransaction().commit();
//
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//

    }


}
