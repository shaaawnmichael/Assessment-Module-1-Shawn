package shawnassessment1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket;
        ServerSocket serverSocket;
        String port = "3000";
        String docRoot = "/static/";
    
        if (args != null && args.length >= 1)
            port = args[0];
        else {
            System.out.println("You didn't provide a file to" +
                    "read the cookies.Will try to read from default file.");
        }
    
    }
}
/* public static void main(String[] args) throws IOException {
    Socket socket;
    ServerSocket serverSocket;
    String inputFile = "cookie_file.txt";

    if (args != null && args.length >= 1)
        inputFile = args[0];
    else {
        System.out.println("You didn't provide a file to" +
                "read the cookies.Will try to read from default file.");
    }

    System.out.println("Server listening at port 12345...");
    serverSocket = new ServerSocket(12345);
    socket = serverSocket.accept();

    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
    String line = in.readLine();

    while (!"close".equals(line) && null != line) {

        System.out.println("Client: " + line);
        
        try {
            
            if ("get-cookie".equals(line)) {
                System.out.println("Sending a cookie..");
                out.println("cookie-text " + 
                        new Cookie().getCookie(inputFile));
                out.flush();
                line = in.readLine();
            } else {
                out.println("Server: you said " + line);
                out.flush();
                line = in.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
            break;
        } 
    }

    socket.close();
    serverSocket.close();
}
} */