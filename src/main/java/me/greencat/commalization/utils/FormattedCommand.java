package me.greencat.commalization.utils;

import java.util.HashMap;
import java.util.HashSet;

public class FormattedCommand {
    private final String command;
    private final HashMap<String,String> stringMap = new HashMap<>();
    private final HashMap<String,Boolean> boolMap = new HashMap<>();
    private final HashMap<String,Double> numberMap = new HashMap<>();
    private final HashSet<String> non_argument_map = new HashSet<>();
    public FormattedCommand(String command){
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
    public void put(String key,Object value){
        if(value == null){
            non_argument_map.add(key);
            return;
        }
        if(value instanceof String){
            stringMap.put(key,(String)value);
            return;
        }
        if(value instanceof Boolean){
            boolMap.put(key, (Boolean) value);
            return;
        }
        if(value instanceof Double){
            numberMap.put(key,(Double)value);
        }
    }
    public String getString(String key){
        return stringMap.get(key);
    }
    public Boolean getBool(String key){
        return boolMap.get(key);
    }
    public Double getNumber(String key){
        return numberMap.get(key);
    }
    public Boolean contains(String key){
        return non_argument_map.contains(key) || stringMap.containsKey(key) || boolMap.containsKey(key) || numberMap.containsKey(key);
    }

    public String toString(){
        return "commandName:" + command + ",values:" + stringMap + " " + boolMap + " " + numberMap + " " + non_argument_map;
    }

}
