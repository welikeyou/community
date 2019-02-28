package com.java.servlets;

        import com.java.control.Databaseco;
        import com.java.model.Community;
        import com.java.model.Create_Apply;
        import com.java.model.Fund;
        import com.java.model.Join_Apply;

        import javax.servlet.RequestDispatcher;
        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.lang.reflect.InvocationTargetException;
        import java.lang.reflect.Method;
        import java.util.List;

public class Review_Join extends HttpServlet {
    Databaseco databaseco=new Databaseco();
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        //get which method to be used
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String methodName = request.getParameter("method");
        try
        {
            Method method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);//获得方法
            method.setAccessible(true);
            method.invoke(this,request,response);//执行方法
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //以上为通用代码
    }




}
