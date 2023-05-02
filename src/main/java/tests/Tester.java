package tests;


import b2b.gettingNewCustomers.SMSSending;
import entities.B2BCustomer;
import jakarta.persistence.EntityManager;
import miscs.EntitiesSuite;
import miscs.SMSSender;
import miscs.Tools;

import java.util.ArrayList;


public class Tester {

    public static void main(String[] args) {

        sendSMSs();


    }

    public static void sendSMSs(){

        EntityManager entityManager = EntitiesSuite.AgamRimon___MainSchema___OnRemote___FromLocal.getEntityManager();

        ArrayList<B2BCustomer> allEntities = Tools.DBTools.getAllEntities(entityManager, new B2BCustomer());

        ArrayList<B2BCustomer>onlyGoodOnes=new ArrayList<>();

        for(B2BCustomer b2BCustomer:allEntities)
            if(b2BCustomer.isSendSMS()==true && b2BCustomer.isSmsWasSent()==false)
                onlyGoodOnes.add(b2BCustomer);


        for(B2BCustomer b2BCustomer:onlyGoodOnes){
            SMSSender.sendSMS("AgamRimon", b2BCustomer.getMobile(), SMSSending.getMessage_001(b2BCustomer.getCustomerCardCode()));
            b2BCustomer.setSmsWasSent(true);
            Tools.DBTools.persistEntity(b2BCustomer, entityManager);

        }







    }


    public static void insertOnlyNewest(){
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
