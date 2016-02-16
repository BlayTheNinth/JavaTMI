package net.blay09.javatmi;

public enum UserType {
    Staff("staff"),
    Admin("admin"),
    GlobalMod("global_mod"),
    Mod("mod");

    private static UserType[] values = values();

    private String tagValue;

    UserType(String tagValue) {
        this.tagValue = tagValue;
    }

    public static UserType fromTag(String value) {
        if(value == null || value.isEmpty()) {
            return null;
        }
        for(UserType userType : values) {
            if(userType.tagValue.equals(value)) {
                return userType;
            }
        }
        return null;
    }
}
