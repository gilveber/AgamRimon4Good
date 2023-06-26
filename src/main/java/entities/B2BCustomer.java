package entities;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "b2b_customer")
public class B2BCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(columnDefinition = "integer", name = "customerCardCode", nullable = false)
    private int customerCardCode;

    @Column(columnDefinition = "text", name = "storeName", nullable = false)
    private String storeName;

    @Column(columnDefinition = "text", name = "storeOwnerName", nullable = false)
    private String storeOwnerName;

    @Column(columnDefinition = "text", name = "mobile", nullable = false)
    private String mobile;

    @Column(columnDefinition = "integer", name = "agentAssigned", nullable = false)
    private int agentAssigned;

    @Column(columnDefinition = "boolean default false", name = "smsWasSent")
    private boolean smsWasSent;

    @Column(columnDefinition = "boolean default false", name = "sendSms")
    private boolean sendSMS;

    @Column(columnDefinition = "text", name = "email")
    private String email;

    @Column(columnDefinition = "integer", name = "emailOpened")
    private int emailOpened;

    @Column(columnDefinition = "integer", name = "appInstalled")
    private int appInstalled;

    @Column(columnDefinition = "integer", name = "desktopAppLaunched")
    private int desktopAppLaunched;

    @Column(columnDefinition = "boolean default false", name = "emailWasSent")
    private boolean emailWasSent;

    @Column(columnDefinition = "text", name = "passwordOnAmodat")
    private String passwordOnAmodat;

    public B2BCustomer() {
    }

    @Override
    public String toString() {
        return "B2BCustomer{" +
                "id=" + id +
                ", customerCardCode=" + customerCardCode +
                ", storeName='" + storeName + '\'' +
                ", storeOwnerName='" + storeOwnerName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", agentAssigned=" + agentAssigned +
                ", smsWasSent=" + smsWasSent +
                ", sendSMS=" + sendSMS +
                ", email='" + email + '\'' +
                ", emailOpened=" + emailOpened +
                ", appInstalled=" + appInstalled +
                ", desktopAppLaunched=" + desktopAppLaunched +
                ", emailWasSent=" + emailWasSent +
                ", passwordOnAmodat='" + passwordOnAmodat + '\'' +
                '}';
    }

    public int getCustomerCardCode() {
        return customerCardCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public Integer getId() {
        return id;
    }

    public String getStoreOwnerName() {
        return storeOwnerName;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public void setCustomerCardCode(int customerCardCode) {
        this.customerCardCode = customerCardCode;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreOwnerName(String storeOwnerName) {
        this.storeOwnerName = storeOwnerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAgentAssigned() {
        return agentAssigned;
    }

    public void setAgentAssigned(int agentAssigned) {
        this.agentAssigned = agentAssigned;
    }

    public boolean isSmsWasSent() {
        return smsWasSent;
    }

    public void setSmsWasSent(boolean smsWasSent) {
        this.smsWasSent = smsWasSent;
    }

    public boolean isSendSMS() {
        return sendSMS;
    }

    public void setSendSMS(boolean tempBoolean) {
        this.sendSMS = tempBoolean;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmailOpened() {
        return emailOpened;
    }

    public void setEmailOpened(int emailOpened) {
        this.emailOpened = emailOpened;
    }

    public int getAppInstalled() {
        return appInstalled;
    }

    public void setAppInstalled(int appInstalled) {
        this.appInstalled = appInstalled;
    }

    public int getDesktopAppLaunched() {
        return desktopAppLaunched;
    }

    public void setDesktopAppLaunched(int desktopAppLaunched) {
        this.desktopAppLaunched = desktopAppLaunched;
    }

    public boolean isEmailWasSent() {
        return emailWasSent;
    }

    public void setEmailWasSent(boolean emailWasSent) {
        this.emailWasSent = emailWasSent;
    }

    public String getPasswordOnAmodat() {
        return passwordOnAmodat;
    }

    public void setPasswordOnAmodat(String passwordOnAmodat) {
        this.passwordOnAmodat = passwordOnAmodat;
    }


//_____________________________________________________________________________________________________________________________________________________________________________________

}