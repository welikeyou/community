package com.java.servlets;
/*
功能: 审核创建社团申请的处理
创建者:刘诗滢
修改者:刘诗滢,黄友明
 */
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.java.control.Databaseco;
import com.java.model.Create_Apply;
import com.java.model.Database;


//审核页面上的操作
//对审核信息的同意拒绝删除
public class Update extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	public Databaseco databaseco = new Databaseco();
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
	{
		//System.out.println("修改");
		//get which method to be used
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String methodName = request.getParameter("method");
		//this.getServletContext().setAttribute("methodSelectByUpdate","reviewCreate_Apply");
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
	protected void approve(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		//System.out.print("pass");
		/*this.getServletContext().setAttribute("methodSelectByUpdate","reviewCreate_Apply");*/
		String studentNum=request.getParameter("studentNum");
		String associationName=request.getParameter("associationName");
		databaseco.addAssociations(studentNum,associationName);
		result(request,response);
		//response.sendRedirect("/mainpages/review.jsp");
		/*request.setAttribute("methodSelectByUpdate","reviewCreate_Apply");*/
		/*RequestDispatcher view=request.getRequestDispatcher("/mainpages/review.jsp");
		view.forward(request, response);*/


	}

	protected void refuse(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		String studentNum=request.getParameter("studentNum");
		String associationName=request.getParameter("associationName");
		databaseco.fail(studentNum,associationName);
		result(request,response);
		/*RequestDispatcher view=request.getRequestDispatcher("list");
		view.forward(request, response);*/
	}

	protected void delete(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		String studentNum=request.getParameter("studentNum");
		String associationName=request.getParameter("associationName");
		databaseco.delete(studentNum,associationName);
		result(request,response);
		/*RequestDispatcher view=request.getRequestDispatcher("list");
		view.forward(request, response);*/

	}

	//批量拒绝
	protected  void refuseForBox(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		String[] selected=request.getParameterValues("box1");
		String[] info;
		String studentNum;
		String associationName;
		if(selected==null){
			System.out.println("无选中的对象");
		}else{
			for(int i=0;i<selected.length;i++){
				info=request.getParameterValues(selected[i]);
				studentNum=info[0];
				associationName=info[1];
				databaseco.fail(studentNum,associationName);
			}
		}
		result(request,response);
		/*RequestDispatcher view=request.getRequestDispatcher("list");
		view.forward(request,response);*/
	}

	//批量同意
	protected void approveForBox(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		String[] selected=request.getParameterValues("box1");
		String[] info;
		String studentNum;
		String associationName;
		if(selected==null){
			System.out.println("无选中的对象");
		}else{
			for(int i=0;i<selected.length;i++){
				info=request.getParameterValues(selected[i]);
				studentNum=info[0];
				associationName=info[1];
				databaseco.addAssociations(studentNum,associationName);
			}
		}
		result(request,response);
		/*RequestDispatcher view=request.getRequestDispatcher("list");
		view.forward(request,response);*/
	}

	//批量拒绝
	protected void deleteForBox(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		String[] selected=request.getParameterValues("box2");
		String[] info;
		String studentNum;
		String associationName;
		if(selected==null){
			System.out.println("无选中的对象");
		}else{
			for(int i=0;i<selected.length;i++){
				info=request.getParameterValues(selected[i]);
				studentNum=info[0];
				associationName=info[1];
				databaseco.delete(studentNum,associationName);
			}
		}
		result(request,response);
		/*RequestDispatcher view=request.getRequestDispatcher("list");
		view.forward(request,response);*/
	}
	protected void result(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		//System.out.println("=====reviewCreate_Apply");
		List<Create_Apply> unchecked=databaseco.getUncheckedAssociations();
		List<Create_Apply>checked=databaseco.getCheckedAssociations();
		request.getSession().setAttribute("unchecked", unchecked);
		request.getSession().setAttribute("checked", checked);
        /*HttpSession session = request.getSession();
        session.setAttribute("list", unchecked);*/
		response.getWriter().println(" <script>\n" +
				"                location.href = \"/mainpages/review.jsp\";\n" +
				"            </script>");
	}
}
