import java.util.ArrayList;
import java.util.Collections;

public class CommandLine {
    String[] Arguments;
    ArrayList<String> Argumentslist = new ArrayList<String>();

    public CommandLine(String[] arguments) {
        Arguments = arguments;
        Collections.addAll(Argumentslist, Arguments);
        if(arguments.length == 0){
            System.out.println("If you want to see cell in window please type -runwindow");
        }
    }

    public boolean doesArgumentExists(String argument) {
        for (String agr : Arguments) {
            if (agr.equals(argument)) {
                return true;
            }
        }
        return false;
    }

    public int getArgumentIndex(String argument) {
        for (int i = 0; i < Arguments.length; i++) {
            if (Arguments[i].equals(argument)) {
                return i;
            }
        }
        return -1;
    }
}
