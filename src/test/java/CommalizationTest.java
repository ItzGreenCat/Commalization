import me.greencat.commalization.Commalization;
import me.greencat.commalization.action.ActionType;
import me.greencat.commalization.command.Command;
import me.greencat.commalization.utils.CommandFormatter;
import me.greencat.commalization.utils.FormattedCommand;

import java.util.Scanner;

public class CommalizationTest {
    public static Commalization commalization = Commalization.getCommalization("Test");
    public static Command testCommand = new Command("test");
    public static void main(String[] args) {
        testCommand.addNode("string_args", ActionType.STRING);
        testCommand.addNotNecessaryNode("bool_args", ActionType.BOOLEAN);
        testCommand.addNode("number_args", ActionType.NUMBER);
        testCommand.addNoArgumentNode("mark");
        commalization.registerCommand(testCommand);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            FormattedCommand formattedCommand = commalization.format(scanner.nextLine());
            System.out.println(formattedCommand);
        }
    }
}
