package shawnassessment1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private ServerSocket server;
    private final int portNumber;
    private final int threadPoolCount;
    private ArrayList<String> docRootList;

    // Overloaded constructor when both arguments are supplied
    public HttpServer(int portNumber, ArrayList<String> docRootList, int threadPoolCount) throws IOException {
        this.portNumber = portNumber;
        this.docRootList = docRootList;
        this.threadPoolCount = threadPoolCount;
        this.start_server();
    }

    private void start_server() throws IOException {
        int i = 1;
        // Validate docRootList argument
//        if (!validate_doc_root(this.docRootList)){
//            System.exit(1);
//        }

        // Start the server
        ExecutorService threadPool = Executors.newFixedThreadPool(threadPoolCount);
        server= new ServerSocket(portNumber);

        // Listen for requests
        System.out.println("Listening on port " + Integer.toString(portNumber));
        while(true) {
            Socket socket= server.accept();
            HttpClientConnection httpWorker= new HttpClientConnection(socket, i);
            threadPool.submit(httpWorker);
            i++;
        }
    }

    private static boolean validate_doc_root(ArrayList<String> arg_docRootList) {
        Path tmpPath;

        // Check every path supplied and return false if any of them does not exist
        for (String rootPath: arg_docRootList) {
            tmpPath = Paths.get(rootPath);
            if (Files.notExists(tmpPath)) {
                System.out.println("ERROR: Directory at " + rootPath + " does not exist");
                return false;
            }
        }
        // Return true if all paths are valid
        return true;
        
    }
}
