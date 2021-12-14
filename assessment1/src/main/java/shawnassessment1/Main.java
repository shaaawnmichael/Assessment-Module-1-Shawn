package shawnassessment1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> docRootList;
        int portArg;
        int poolCountArg;

        Map<String, String> commandLineArgs = new HashMap<>();
        String argumentName = "";
        String argumentValue = "";
        boolean argFlag = true;

        // Validate the command line arguments to check if they are passed correctly
        for (String argString: args) {
            if (argFlag) {
                if(argString.startsWith("--")) {
                    argumentName = argString.substring(2);
                } else {
                    System.out.println("ERROR: Argument name must start with -- @ " + argString);
                    System.exit(1);
                }
                argFlag = false;

            } else {
                if (argString.contains("-")) {
                    System.out.println("ERROR: Do not include dashes as part of option name! @" + argString);
                    System.exit(1);
                } else {
                    argumentValue = argString;
                    commandLineArgs.put(argumentName, argumentValue);
                }
                argFlag = true;
            }
        }

        // Get required values, else set default value
        if (!commandLineArgs.containsKey("port")){
            portArg = 3000;
        } else {
            portArg = Integer.parseInt(commandLineArgs.get("port"));
        }

        if (!commandLineArgs.containsKey("docRoot")){
            docRootList = new ArrayList<String>() {{add("./static");}};
        } else {
            docRootList = new ArrayList<>(Arrays.asList(commandLineArgs.get("docRoot").split(":")));
        }

        if (!commandLineArgs.containsKey("poolCount")){
            poolCountArg = 3;
        } else {
            poolCountArg = Integer.parseInt(commandLineArgs.get("poolCount"));
        }


        // Create a new HTTP server
        HttpServer server = new HttpServer(portArg,docRootList,poolCountArg);

//        // DEBUG Loop HASHMAP
//        for (Map.Entry me : commandLineArgs.entrySet()) {
//            System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue());
//        }
    }
}