package me.greencat.commalization;

import me.greencat.commalization.command.Command;
import me.greencat.commalization.utils.CommandFormatter;
import me.greencat.commalization.utils.FormattedCommand;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

public class Commalization {
    private static final HashMap<String,Commalization> instances = new HashMap<>();
    public static Commalization getCommalization(String name){
        return instances.containsKey(name) ? instances.get(name) : new Commalization(name);
    }

    private final String name;
    private final HashSet<Command> commands;
    private Commalization(String name){
        this.name = name;
        commands = new HashSet<>();
        instances.put(name,this);
    }
    public String getName(){
        return name;
    }
    public void registerCommand(Command command){
        commands.add(command);
    }
    public FormattedCommand format(String command){
        FormattedCommand formattedCommand;
        String commandNodeBase = command.split(" ")[0];
        Optional<Command> option = commands.stream().filter(it -> it.getCommand().equals(commandNodeBase)).findFirst();
        if (option.isPresent()) {
            formattedCommand = CommandFormatter.format(option.get(),command);
        } else {
            throw new RuntimeException(String.format("Couldn't match command %s in this Commalization instance",commandNodeBase));
        }
        return formattedCommand;
    }
}
