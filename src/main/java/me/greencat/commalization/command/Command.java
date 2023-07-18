package me.greencat.commalization.command;

import java.util.LinkedList;

import me.greencat.commalization.action.ActionCode;
import me.greencat.commalization.action.ActionNode;
import me.greencat.commalization.action.ActionType;

public class Command {
    private final String command;
    private final LinkedList<ActionNode> actions = new LinkedList<>();
    public Command(String command){
        this.command = command;
    }
    public String getCommand(){
        return command;
    }
    public Command addNode(String name, ActionType type){
        actions.add(new ActionNode(name,type, ActionCode.NECESSARY));
        return this;
    }
    public Command addNotNecessaryNode(String name, ActionType type){
        actions.add(new ActionNode(name,type, ActionCode.NOT_NECESSARY));
        return this;
    }
    public Command addNoArgumentNode(String name){
        actions.add(new ActionNode(name,ActionType.NULL, ActionCode.NO_ARGUMENT));
        return this;
    }
    public LinkedList<ActionNode> getActions(){
        return actions;
    }

}
