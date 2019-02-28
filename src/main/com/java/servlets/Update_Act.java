package com.java.servlets;

import com.java.control.Databaseco;
import com.java.model.Activity_Apply;
import com.java.model.Community;
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

public class Update_Act extends HttpServlet {
    Databaseco databaseco=new Databaseco();
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        //get which method to be used
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

    //approve state->passed
    protected void approveAct(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        System.out.print("pass1");
        String studentNum=request.getParameter("studentNum");
        String title=request.getParameter("title");
        String associationName=request.getParameter("associationName");
        databaseco.approveAct(studentNum,title,associationName);
        result(request,response);


    }

    //refuse state->failed
    protected void refuseAct(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        System.out.println("refuse1");
        String studentNum=request.getParameter("studentNum");
        String title=request.getParameter("title");
        String associationName=request.getParameter("associationName");
        databaseco.failAct(studentNum,title,associationName);
       /* RequestDispatcher view=request.getRequestDispatcher("list");
        view.forward(request, response);*/
        result(request,response);
    }

    protected void deleteAct(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        System.out.println("delete1");
        String studentNum=request.getParameter("studentNum");
        String title=request.getParameter("title");
        String associationName=request.getParameter("associationName");
        databaseco.deleteAct(studentNum,title,associationName);
       /* RequestDispatcher view=request.getRequestDispatcher("list");
        view.forward(request, response);*/
        result(request,response);

    }

    //批量拒绝
    protected  void refuseActForBox(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        System.out.println("批量refuse1");
        String[] selected=request.getParameterValues("1box");
        String[] info;
        String studentNum;
        String title;
        String associationName;
        if(selected==null){
            System.out.println("无选中的对象");
        }else{
            for(int i=0;i<selected.length;i++){
                info=request.getParameterValues(selected[i]);
                studentNum=info[0];
               title=info[1];
               associationName=info[2];
                databaseco.failAct(studentNum,title,associationName);
            }
        }
       /* RequestDispatcher view=request.getRequestDispatcher("list");
        view.forward(request,response);*/
        result(request,response);
    }

    //批量同意
    protected void approveActForBox(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        System.out.println("批量approve1");
        String[] selected=request.getParameterValues("1box");
        String[] info;
        String studentNum;
        String title;
        String associationName;
        if(selected==null){
            System.out.println("无选中的对象");
        }else{
            for(int i=0;i<selected.length;i++){
                info=request.getParameterValues(selected[i]);
                studentNum=info[0];
                title=info[1];
                associationName=info[2];
                databaseco.approveAct(studentNum,title,associationName);
            }
        }
       /* RequestDispatcher view=request.getRequestDispatcher("list");
        view.forward(request,response);*/
        result(request,response);
    }

    //批量删除
    protected void deleteActForBox(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        System.out.println("批量delete1");
        String[] selected=request.getParameterValues("box");
        String[] info;
        String studentNum;
        String title;
        String associationName;
        if(selected==null){
            System.out.println("无选中的对象");
        }else{
            for(int i=0;i<selected.length;i++){
                info=request.getParameterValues(selected[i]);
                studentNum=info[0];
                title=info[1];
                associationName=info[2];
                databaseco.deleteAct(studentNum,title,associationName);
            }
        }
        result(request,response);
      /*  RequestDispatcher view=request.getRequestDispatcher("list");
        view.forward(request,response);*/
    }

    protected void result(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        Community community = (Community)request.getSession().getAttribute("community");
        String associaitonNum= community.getComID();
        List<Activity_Apply>checked=databaseco.getCheckedAc(associaitonNum) ;
        List<Activity_Apply>unchecked=databaseco.getUncheckedAc(associaitonNum);
        request.setAttribute("checkedAcJoin",checked);
        request.setAttribute("uncheckedAcJoin",unchecked);
   /* response.getWriter().println(" <script>\n" +
            "                location.href = \"/checkJoin.jsp\";\n" +
            "            </script>");*/
        RequestDispatcher view=request.getRequestDispatcher("/checkapply/checkJoin.jsp");
        view.forward(request,response);
    }
}
