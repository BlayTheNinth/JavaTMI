package net.blay09.javatmi;

public class GiftPaidUpgradeInfo {
    private String senderLogin;
    private String senderName;
    private boolean isSenderAnonymous;
    private String promoName;
    private int promoGiftTotal;
    private int months;
    private String systemMessage;

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public int getPromoGiftTotal() {
        return promoGiftTotal;
    }

    public void setPromoGiftTotal(int promoGiftTotal) {
        this.promoGiftTotal = promoGiftTotal;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    public boolean isSenderAnonymous() {
        return isSenderAnonymous;
    }

    public void setSenderAnonymous(boolean senderAnonymous) {
        isSenderAnonymous = senderAnonymous;
    }
}
