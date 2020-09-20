import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class CalcClient {

	public static void main(String[] args) {
		  Socket socket = null;
	      BufferedReader in = null;//입력용
	      BufferedWriter out = null;//출력용
	      Scanner scanner = new Scanner(System.in);
	      try {
	         socket = new Socket("localhost", 9999); //(ip, 포트번호)를 준다. 
	         System.out.println("연결이 되었습니다!!");
	         
	         in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	         out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	         
	         while(true) {
	            System.out.print(">>");
	            
	            String outputMessage = scanner.nextLine();//스캐너야 한라인 읽어봐
	            
	            out.write(outputMessage + "\n");//상대에게 보내줘라 
	            out.flush(); //버퍼에 있는거 다 보내
	            
	            if(outputMessage.equalsIgnoreCase("bye")) {
	               break;
	            }
	            
	            String inputMessage = in.readLine();
	            System.out.println("결과 : " + inputMessage);
	            
	         }
	         
	      }catch (IOException e) {
	         System.out.println(e.getMessage());
	      }finally {
	         scanner.close();
	         try {
	            socket.close();
	         } catch (IOException e) {
	            System.out.println("채팅 중 오류가 발생하였습니다. ");
	         }
	      }
	      
	   }

}
