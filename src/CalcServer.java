import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CalcServer {
	public static String calc(String exp) { //"2+23", "321-125"
		StringTokenizer st = new StringTokenizer(exp, " ");
		if(st.countTokens()!=3) return "error";
		int op1 = Integer.parseInt(st.nextToken());
		String opcode = st.nextToken();
		int op2 = Integer.parseInt(st.nextToken());
		
		String res = "";
		switch(opcode) {
		case "+" : res = Integer.toString(op1+op2); break;
		case "-" : res = Integer.toString(op1-op2); break;
		case "*" : res = Integer.toString(op1*op2); break;
		}
		
		return res;
		
	}
	public static void main(String[] args) {
		ServerSocket listener = null;
	    Socket socket = null;
	    BufferedReader in = null;//�Է¿�
	    BufferedWriter out = null;//��¿�
	    
	    
	    try {
	        listener = new ServerSocket(9999);
	        System.out.println("������ ��ٸ��� �ֽ��ϴ�...");
	        
	        //accept�� blocking call�� �� �Լ��� ���� ������ ������ ���� ���Ѵ�. = ����ȭ ��
	        //nonblocking call�̶�� ���� �ִ�. 
	        socket = listener.accept(); //���� ���
	        System.out.println("������ �Ǿ����ϴ�!!");//accept������ ������ �� �Ǿ��ٴ� ��
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	        
	        while(true) {//���ѷ����� ���鼭 �ְ�޴´�. 
	           String inputMessage = in.readLine();//�޼����� �о���.
	           
	           if(inputMessage.equalsIgnoreCase("bye")) {//��ҹ��� ���о��� bye�� �����ٸ�
	              System.out.println("������ �����մϴ�.");
	              break;
	           }
	           
	           System.out.println("���� �޼��� : " + inputMessage);//������ ����غ�
	           //��ٸ��� ����ϰ� �������
	           String res = calc(inputMessage);
	           
	           out.write(res + "\n");//��뿡�� ������� 
	           out.flush(); //���ۿ� �ִ°� �� ����
	           
	        }
	        
	     } catch (IOException e) {
	        System.out.println(e.getMessage());
	     }finally {
	        try {
	           socket.close();
	           listener.close();
	        } catch (IOException e) {
	           System.out.println("ä�� �� ������ �߻��Ͽ����ϴ�. ");
	        }
	        
	     }
	}
	
    
}
