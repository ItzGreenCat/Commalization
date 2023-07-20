package me.greencat.commalization.utils;

import me.greencat.commalization.action.ActionCode;
import me.greencat.commalization.action.ActionNode;
import me.greencat.commalization.command.*;

import java.util.LinkedList;

public class CommandFormatter {
    public static FormattedCommand format(Command command,String inputCommand){
        String[] commandArguments = inputCommand.split(" ");
        FormattedCommand formattedCommand = new FormattedCommand(commandArguments[0]);
        LinkedList<ActionNode> actions = command.getActions();
        actions.forEach(it -> {
            boolean containKey = false;
            int index = 0;
            for(int i = 0;i < commandArguments.length;i++){
                String node = commandArguments[i];
                String argumentName = node.split(":")[0];
                if(argumentName.equals(it.getName())){
                    containKey = true;
                    index = i;
                    break;
                }
            }
            if (it.getCode() == ActionCode.NECESSARY && !containKey) {
                throw new RuntimeException(String.format("The required parameter %s could not be found ",it.getName()));
            }
            if (containKey) {
                switch(it.getType()){
                    case NULL:
                        formattedCommand.put(it.getName(),null);
                        break;
                    case STRING:
                        String node = commandArguments[index];
                        String argument = node.split(":")[1];if(!commandArguments[index].contains(":") && it.getCode() != ActionCode.NO_ARGUMENT){
                        throw new RuntimeException(String.format("A colon could not be found in parameter %s of type string as a separation of parameter name from parameter",it.getName()));
                        }
                        if(!node.contains(":") && it.getCode() != ActionCode.NO_ARGUMENT){
                            throw new RuntimeException(String.format("A colon could not be found in parameter %s of type string as a separation of parameter name from parameter",it.getName()));
                        }
                        if(!node.contains("\"")){
                            throw new RuntimeException(String.format("The double quotation mark as the beginning of the parameter could not be found in the string type parameter %s",it.getName()));
                        }
                        String[] splitArgument = argument.split("\"");
                        String fullString;
                        int QuotationMarksCounter = 0;
                        for(char c : argument.toCharArray()){
                            if(c == '\"'){
                                QuotationMarksCounter++;
                            }
                        }
                        if(QuotationMarksCounter == 1) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(splitArgument[1]);
                            sb.append(" ");
                            boolean containStringEnd = false;
                            for (int j = index + 1; j < commandArguments.length; j++) {
                                String content = commandArguments[j];
                                if (!content.contains("\"")) {
                                    sb.append(content);
                                    sb.append(" ");
                                } else {
                                    sb.append(content.split("\"")[0]);
                                    containStringEnd = true;
                                    break;
                                }
                            }
                            if(!containStringEnd){
                                throw new RuntimeException(String.format("Unable to find a string ending for the parameter content of %s",it.getName()));
                            }
                            fullString = sb.toString();
                        } else {
                            fullString = splitArgument[1];
                        }
                        formattedCommand.put(it.getName(),fullString);
                        break;
                    case BOOLEAN:
                        boolean boolValue;
                        String stringValue = commandArguments[index].split(":")[1];
                        if(stringValue.equals("true")){
                            boolValue = true;
                        } else if(stringValue.equals("false")){
                            boolValue = false;
                        } else {
                            throw new RuntimeException(String.format("Incorrect Boolean value found at %s",it.getName()));
                        }
                        formattedCommand.put(it.getName(),boolValue);
                        break;
                    case NUMBER:
                        try {
                            double numberValue = Double.parseDouble(commandArguments[index].split(":")[1]);
                            formattedCommand.put(it.getName(),numberValue);
                        } catch (Exception e){
                            throw new RuntimeException(String.format("Incorrect Number value found at %s",it.getName()));
                        }
                }
            }
        });
        return formattedCommand;
    }
}
