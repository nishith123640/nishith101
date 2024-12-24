import java.net.*;
import java.io.*;
public class tcps
{
public static void main(String args[]) throws IOException
{
ServerSocket sersock=new ServerSocket(5000);
System.out.println("server ready for connection");
Socket sock=sersock.accept();
System.out.println("connection is successful");
InputStream istream=sock.getInputStream();
BufferedReader fileRead=new BufferedReader(new InputStreamReader(istream));
String fname=fileRead.readLine();
BufferedReader contentRead=new BufferedReader(new FileReader(fname));
OutputStream ostream=sock.getOutputStream();
PrintWriter pwrite=new PrintWriter(ostream,true);
String str;
while((str=contentRead.readLine())!=null)
{
pwrite.println(str);
}
sock.close();
sersock.close();
pwrite.close();
fileRead.close();
contentRead.close();
}
}
Source Code: Client
import java.net.*;
import java.io.*;
public class tcpc
{
public static void main(String args[]) throws IOException
{
Socket sock=new Socket("127.0.0.1",5000);
System.out.println("Enter the File Name");
BufferedReader keyRead=new BufferedReader(new InputStreamReader(System.in));
String fname=keyRead.readLine();
OutputStream ostream=sock.getOutputStream();
PrintWriter pwrite=new PrintWriter(ostream,true);
System.out.println();
pwrite.println(fname);
InputStream istream=sock.getInputStream();
BufferedReader socketRead=new BufferedReader(new InputStreamReader(istream));
String str;
while((str=socketRead.readLine())!=null)
{
System.out.println(str);
}
sock.close();
pwrite.close();
keyRead.close();
socketRead.close();
}
}
output:
serverside
amc@amc-p2-1274il:~$ gedit abc.txt
amc@amc-p2-1274il:~$ gedit tcps.java
amc@amc-p2-1274il:~$ javac tcps.java
amc@amc-p2-1274il:~$ java tcps
server ready for connection
connection is successful
amc@amc-p2-1274il:~$


clientside
amc@amc-p2-1274il:~$ javac tcpc.java
amc@amc-p2-1274il:~$ java tcpc
Enter the File Name
abc.txt
computer network lab
Information Science and Engg
amc@amc-p2-1274il:~$ 
 
