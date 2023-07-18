package me.greencat.commalization.action;

public class ActionNode {
    private final String name;
    private final ActionType type;
    private final ActionCode code;
    public ActionNode(String name,ActionType type,ActionCode code){
        this.name = name;
        this.type = type;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public ActionType getType() {
        return type;
    }

    public ActionCode getCode() {
        return code;
    }
}
