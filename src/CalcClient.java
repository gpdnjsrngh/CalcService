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
	      BufferedReader in = null;//�Է¿�
	      BufferedWriter out = null;//��¿�
	      Scanner scanner = new Scanner(System.in);
	      try {
	         socket = new Socket("localhost", 9999); //(ip, ��Ʈ��ȣ)�� �ش�. 
	         System.out.println("������ �Ǿ����ϴ�!!");
	         
	         in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	         out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	         
	         while(true) {
	            System.out.print(">>");
	            
	            String outputMessage = scanner.nextLine();//��ĳ�ʾ� �Ѷ��� �о��
	            
	            out.write(outputMessage + "\n");//��뿡�� ������� 
	            out.flush(); //���ۿ� �ִ°� �� ����
	            
	            if(outputMessage.equalsIgnoreCase("bye")) {
	               break;
	            }
	            
	            String inputMessage = in.readLine();
	            System.out.println("��� : " + inputMessage);
	            
	         }
	         
	      }catch (IOException e) {
	         System.out.println(e.getMessage());
	      }finally {
	         scanner.close();
	         try {
	            socket.close();
	         } catch (IOException e) {
	            System.out.println("ä�� �� ������ �߻��Ͽ����ϴ�. ");
	         }
	      }
	      
	   }

}
