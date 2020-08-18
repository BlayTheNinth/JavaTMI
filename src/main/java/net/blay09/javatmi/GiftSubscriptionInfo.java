package net.blay09.javatmi;

public class GiftSubscriptionInfo {
    private String recipientId;
    private String recipientUserName;
    private String recipientDisplayName;
    private int giftMonths;
    private String subPlanName;
    private String subPlan;
    private String systemMessage;
    private boolean isSenderAnonymous;

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientUserName() {
        return recipientUserName;
    }

    public void setRecipientUserName(String recipientUserName) {
        this.recipientUserName = recipientUserName;
    }

    public String getRecipientDisplayName() {
        return recipientDisplayName;
    }

    public void setRecipientDisplayName(String recipientDisplayName) {
        this.recipientDisplayName = recipientDisplayName;
    }

    public int getGiftMonths() {
        return giftMonths;
    }

    public void setGiftMonths(int giftMonths) {
        this.giftMonths = giftMonths;
    }

    public String getSubPlanName() {
        return subPlanName;
    }

    public void setSubPlanName(String subPlanName) {
        this.subPlanName = subPlanName;
    }

    public String getSubPlan() {
        return subPlan;
    }

    public void setSubPlan(String subPlan) {
        this.subPlan = subPlan;
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
