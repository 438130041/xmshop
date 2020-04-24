package cn.ss.sa;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import net.sf.json.JSONArray;

public class RegeditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //ServletContext ser = getServletContext();
        //ApplicationContext ioc = (ApplicationContext) ser.getAttribute("app");
        doPost(request, response);
        //Regedit re=ioc.getBean(Regedit.class);
        //re.login();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String user=request.getParameter("name");
        String pass=request.getParameter("pass");
        System.out.println("用户名："+user);
        System.out.println("密码："+pass);
        //将List转化为JSON
        System.out.println("----------");
        String ty="java与apicloud的前后台交互！！";
        ArrayList<String> list=new ArrayList<String>();
        list.add(ty);
        JSONArray json=JSONArray.fromObject(list);
        //设置编码
        response.setCharacterEncoding("utf-8");
        //写入到前台
        response.getWriter().write(json.toString());

    }

}
