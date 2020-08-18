package net.blay09.javatmi;

public class SubscriptionInfo {
    private boolean shouldShareStreak;
    private int cumulativeMonths;
    private int streakMonths;
    private String subPlan;
    private String subPlanName;
    private String systemMessage;
    private String message;

    public boolean isShouldShareStreak() {
        return shouldShareStreak;
    }

    public void setShouldShareStreak(boolean shouldShareStreak) {
        this.shouldShareStreak = shouldShareStreak;
    }

    public int getCumulativeMonths() {
        return cumulativeMonths;
    }

    public void setCumulativeMonths(int cumulativeMonths) {
        this.cumulativeMonths = cumulativeMonths;
    }

    public int getStreakMonths() {
        return streakMonths;
    }

    public void setStreakMonths(int streakMonths) {
        this.streakMonths = streakMonths;
    }

    public String getSubPlan() {
        return subPlan;
    }

    public void setSubPlan(String subPlan) {
        this.subPlan = subPlan;
    }

    public String getSubPlanName() {
        return subPlanName;
    }

    public void setSubPlanName(String subPlanName) {
        this.subPlanName = subPlanName;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
