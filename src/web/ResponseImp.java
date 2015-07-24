package web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
/*
 * 响应类
 */
public class ResponseImp implements Response{
	public PrintStream output;
	public ResponseImp(OutputStream os){
		output = new PrintStream(os);
	}
	
	public void setStatus(int status) {
		switch (status) {
			case STATUS_200:
				output.println("HTTP/1.1 200 OK!");
				break;
			case STATUS_404:
				output.println("HTTP/1.1 404 File Not Found!");
				break;
			case STATUS_503:
				output.println("HTTP/1.1 503 server cannot use!");
				break;
		}
	}
	
	public void setHeader(String key, String value){
		output.println(key+value);
	}

	public void addBody(byte[] body){
		output.println();
		try{
			output.write(body);
			output.println();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void flush(){
		output.flush();
	}
}
