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
	    BufferedReader in = null;//입력용
	    BufferedWriter out = null;//출력용
	    
	    
	    try {
	        listener = new ServerSocket(9999);
	        System.out.println("연결을 기다리고 있습니다...");
	        
	        //accept는 blocking call로 이 함수가 일을 끝내기 전까지 리턴 안한다. = 동기화 콜
	        //nonblocking call이라는 말도 있다. 
	        socket = listener.accept(); //접속 대기
	        System.out.println("연결이 되었습니다!!");//accept끝나면 연결이 다 되었다는 뜻
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	        
	        while(true) {//무한루프를 돌면서 주고받는다. 
	           String inputMessage = in.readLine();//메세지를 읽었다.
	           
	           if(inputMessage.equalsIgnoreCase("bye")) {//대소문자 구분없이 bye를 보낸다면
	              System.out.println("연결을 종료합니다.");
	              break;
	           }
	           
	           System.out.println("받은 메세지 : " + inputMessage);//읽은거 출력해봐
	           //기다리고 계산하고 보내쥬고
	           String res = calc(inputMessage);
	           
	           out.write(res + "\n");//상대에게 보내줘라 
	           out.flush(); //버퍼에 있는거 다 보내
	           
	        }
	        
	     } catch (IOException e) {
	        System.out.println(e.getMessage());
	     }finally {
	        try {
	           socket.close();
	           listener.close();
	        } catch (IOException e) {
	           System.out.println("채팅 중 오류가 발생하였습니다. ");
	        }
	        
	     }
	}
	
    
}
