package shawnassessment1;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

public class HttpClientConnection implements Runnable {
    private final Socket socket;
    private final int id;

    public HttpClientConnection(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    public void run() {
        // Read input
        String[] httpRequestHeader = null;
        try {
            httpRequestHeader = read_request();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String httpMethod = httpRequestHeader[0];
        String resourcePath = httpRequestHeader[1];

        if (!httpMethod.startsWith("GET")) {
            try {
                send_response(httpMethod + " not supported\r\n", "405 Method Not Allowed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            try {
                String httpBody = retrieve_resources(resourcePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String retrieve_resources(String resourcePath) throws IOException{
        System.out.println("Retrieving resource at path: " + resourcePath + " for connection id " + id);

        if (resourcePath.equalsIgnoreCase("/")) {
            resourcePath = "/index.html";
        }

        Path p = Paths.get(resourcePath);
        InputStream is= new FileInputStream(p.toFile());
        GZIPInputStream gis= new GZIPInputStream(is);
        InputStreamReader isr= new InputStreamReader(gis);
        LineNumberReader lnr= new LineNumberReader(isr);
        String line;
        while (null != (line = lnr.readLine()))
            System.out.printf("%d: %s\n", lnr.getLineNumber(), line);

        return "";
    }

    public String[] read_request() throws IOException {
        ArrayList<String> requestString = new ArrayList<>();
        String requestHeader;
        System.out.println("Reading HTTP GET request from Connection ID: " + id);
        BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        requestHeader=is.readLine();
        System.out.println("Response received from Connection ID: " + id);
        System.out.println(requestHeader);
        String[] requestHeaderParts = requestHeader.split(" ");

        return requestHeaderParts;
    }

    public void send_response(String responseBody, String responseHeaderCode) throws IOException {
        String responseHeader = "HTTP/1.1 " + responseHeaderCode + "\r\n\r\n";
        String httpResponse = responseHeader + responseBody;

        System.out.println("HTTP Header response: " + responseHeader);
        System.out.println("Writing HTTP response to Connection ID: " + id);
        OutputStream os= socket.getOutputStream();
        os.write(httpResponse.getBytes(StandardCharsets.UTF_8));
        os.close();
        socket.close();
        os.flush();
    }

}
