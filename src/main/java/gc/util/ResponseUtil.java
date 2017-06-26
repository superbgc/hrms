package gc.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	//用HttpServletReponse返回前台Json格式数据，同时减少controller层代码的沉余
	public static void write(HttpServletResponse response, Object o) throws IOException{
		response.setCharacterEncoding("text/html;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
