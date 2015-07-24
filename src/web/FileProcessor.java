package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
/*
 * 响应给客户端的信息的处理
 */
public class FileProcessor implements Processor {
	public String pathname = null;

	public byte[] bytes = null;

	public String root = "D:/webroot";

	public boolean flag = true;

	public boolean match(Request request) {
		return "close".equals(request.getHead("Connection"));
	}

	public void process(Request request, Response response) throws Exception {
		pathname = root + request.getUri();
		int status = getStatus();
		response.setStatus(status);
		response.setHeader("Content-Type: ", getType());
		response.setHeader("Content-Length: ", String.valueOf(bytes.length));
		response.setHeader("Connection: ", "keep-alive");
		response.addBody(bytes);
		response.flush();
	}

	public void setFlag(boolean f) {
		flag = f;
	}

	public void setBody(byte[] bt) {
		bytes = bt;
	}

	public String getType() {
		if (pathname.endsWith(".png")) {
			return "image/png";
		}
		else if (pathname.endsWith(".pdf")) {
			return "application/pdf";
		}
		else if (pathname.endsWith(".mp3")) {
			return "audio/mpeg";
		}else if(pathname.endsWith(".gif")){
			return "image/gif";
		}
		else {
			return "text/html";
		}
	}

	public int getStatus() throws Exception {
		if (!flag)
			return 200;
		File file = new File(pathname);
		if (!file.exists()) {
			String str = "<html>\r\n" + "<head>\r\n" + "<title>yangguang</title>\r\n" + "</head>\r\n" + "<body>\r\n"
					+ "<h1>404 File Not Found!</h1>\r\n" + "</body>\r\n" + "</html>\r\n";
			bytes = str.getBytes();
			return 404;
		}
		else {
			if (pathname.endsWith("/")) {
				sendDirectory();
			}
			else {
				sendFile();
			}
			return 200;
		}
	}

	public void sendDirectory() {
		File f = new File(pathname);
		String[] files = f.list();
		StringBuffer sb = new StringBuffer("");
		sb.append("<html><body><h1>" +"Files:"+ "</h1>");
		for (int i = 0; i < files.length; i++) {
			sb.append("<a href='" + files[i] + "'>");
			sb.append(files[i]+"<br>");
		}
		sb.append("</body></html>");
		bytes = sb.toString().getBytes();
	}

	public void sendFile() throws Exception {

		File f = new File(pathname);
		InputStream is = new FileInputStream(f);
		int len = (int) f.length();
		bytes = new byte[len];
		int offset = 0;
		while (offset < len) {
			offset += is.read(bytes, offset, len - offset);
		}
		is.close();
	}
}
