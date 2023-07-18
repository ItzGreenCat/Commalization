# Commalization
A library provides formatting of commands
# Install
You only need to go to the Releases page to download the Jar file and add it to your project dependencies
# Usage
To use Commalization, create an instance of Commalization
```java
Commalization commalization = Commalization.getCommalization("Your_Instance_Name");
```
You can get a Commalization instance anywhere by calling the getCommalization method
The next step is to add a command, and you need to create a new Command instance
```java
Command command = new Command("Your_Command_Name");
```
You can then add parameters to your command
```java
//The addNode method is to add the necessary parameters that your command must include when it runs
command.addNode("string_args", ActionType.STRING);
//The addNotNecessaryNode method is to add unnecessary parameters that your command can choose not to include when it runs
command.addNotNecessaryNode("bool_args", ActionType.BOOLEAN);
command.addNode("number_args", ActionType.NUMBER);
//The addNoArgumentNode method is to add unnecessary flag parameters, which your command can choose not to include when running, and these parameters have no content, only appear as a flag in the command, and sometimes this parameter can replace the Boolean type parameter
command.addNoArgumentNode("mark");
```
It is worth mentioning that all three methods return their own Command instances, so they can be written as follows
```java
command.addNode("string_args", ActionType.STRING).addNotNecessaryNode("bool_args", ActionType.BOOLEAN).addNode("number_args", ActionType.NUMBER).addNoArgumentNode("mark");
```
Next is the registration command, which requires registering the Command instance with the Commalization instance
```java
commalization.registerCommand(command);
```
You can then use Commalization to parse your commands now
```java
commalization.format(/*your_command_here*/);
```
The format method returns a FormattedCommand object, similar to a HashMap, through which the parameters in the formatted command can be obtained

For the Command instance just now, this is a valid command
```
Your_Command_Name mark number_args:114514 string_args:"this is a string" bool_args:true
```
This is also a valid command
```
Your_Command_Name number_args:114514 string_args:"this is a string"
```
