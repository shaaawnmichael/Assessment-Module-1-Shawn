package shawnassessment1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HttpServer {

    Socket socket;
    ServerSocket serverSocket;
        
        public static void main(String[] args) throws IOException {
            
            System.out.println("Please key in --port <port number> or --docRoot <colon delimiited list of directories>. If port is not specified default port will be 3000.");
            Scanner scan =new Scanner(System.in);
            String com = scan.next();

            while(true) {

            if (com.equals("--port".substring(6)));
                System.out.println("Listening on port" + com.substring(6));

            break;

            }
            

        }    
}


/* String opop=new String();
ServerSocket serv=new ServerSocket(12345);
System.out.println("listening at port " +serv.getLocalPort());
Socket sock=serv.accept();

try{
InputStream inps=sock.getInputStream();
OutputStream osos=sock.getOutputStream();
BufferedInputStream binps= new BufferedInputStream(inps);
DataInputStream dinps= new DataInputStream(binps);
BufferedOutputStream bos=new BufferedOutputStream(osos);
DataOutputStream dos=new DataOutputStream(bos);

String inputFromClient="Connected to Client";

System.out.println(inputFromClient);
Cookie newCookie= new Cookie();

while (!inputFromClient.equals("exit"))
{
inputFromClient=dinps.readUTF();
if (inputFromClient.equals("get-cookie"))
{
System.out.println(inputFromClient);
opop=newCookie.returnCookie();
System.out.println(opop); 
dos.writeUTF(opop);

}
else if (inputFromClient.equals("exit"))
System.out.println("Disconnected");

else
{System.out.println("wrong: "+inputFromClient);
dos.writeUTF("Wrong input");  

}
dos.flush();
}
sock.close();
serv.close();
}
catch(Exception e)
{
e.printStackTrace();
} */