package by.step.zimin.eshop.model;

public enum OperationSystem {
    WINDOWS("Operation system Windows."),
    ANDROID("Operation system Android."),
    LINUX("Operation system Linux."),
    IOS("Operation system IOS.");

    private String os;

    private OperationSystem(String os){
        this.os=os;
    }

    public String getOperationSystem(){
        return os;
    }
}
