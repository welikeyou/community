package com.java.servlets;

import com.google.gson.Gson;
import com.java.control.Databaseco;
import com.java.model.Community;
import com.java.model.Fund;
import com.java.model.Join_Apply;
/*import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;*/

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


public class Fund_Manage extends HttpServlet {
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

    protected void addFund(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        //String associationNum=(String)request.getSession().getAttribute("associationNum");
        Community community = (Community)request.getSession().getAttribute("community");
        String person=request.getParameter("person");
        String cost=request.getParameter("cost");
        String income=request.getParameter("income");
        String pageMoney=request.getParameter("moneyDiv");
        String date=request.getParameter("date");
        String remark=request.getParameter("remark");
        if(income=="")income="0";
        if(cost=="")income="0";
        double calCurMoney=Double.valueOf(pageMoney).doubleValue()+Double.valueOf(income).doubleValue()-
                Double.valueOf(cost).doubleValue();
        String  currentMoney=""+calCurMoney;

        databaseco.addFunds(community.getComID(),person,cost,income,currentMoney,date,remark);

        result(request,response);
        /*Fund newInfo=new Fund();
        newInfo.setPerson(person);
        newInfo.setAssociationNum(associationNum);
        newInfo.setCost(cost);
        newInfo.setIncome(income);
        newInfo.setPreviousMoney(currentMoney);
        newInfo.setCurrentMoney(currentMoney);
        newInfo.setDate(date);
        newInfo.setRemark(remark);

        Gson gson=new Gson();
        String json=gson.toJson(newInfo);
        System.out.println(json);
        PrintWriter out=response.getWriter();
        out.write(json);
*/

    }
    //修改person和remark
   /* protected void updateFund2Info(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        String type=request.getParameter("type");
        String order=request.getParameter("order");
        String value=request.getParameter("value");
        double currentMoney;
        if(type=="1"){
            //修改person
            databaseco.updatePerson(order,value);
        }
        if(type=="5"){
            //修改remark
             databaseco.updateRemark(order,value);
        }
    }
    //修改cost
   protected  void updateCost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
       // String associationNum=(String)request.getSession().getAttribute("associationNum");
       Community community = (Community)request.getSession().getAttribute("community");
        String pageMoney=request.getParameter("currentMoneySer");
        String order=request.getParameter("orderSer");
        String value=request.getParameter("valueSer");
        double calMoney=Double.valueOf(pageMoney).doubleValue()-Double.valueOf(value).doubleValue();
        String currentMoney=""+calMoney;
        databaseco.updateCost(order,value,currentMoney,community.getComID());
        result(request,response);

    }
    protected  void updateIncome(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
       // String associationNum=(String)request.getSession().getAttribute("associationNum");
        Community community = (Community)request.getSession().getAttribute("community");
        String pageMoney=request.getParameter("currentMoneySer");
        String order=request.getParameter("orderSer");
        String value=request.getParameter("valueSer");
        double calMoney=Double.valueOf(pageMoney).doubleValue()+Double.valueOf(value).doubleValue();
        String currentMoney=""+calMoney;
        databaseco.updateIncome(order,value,currentMoney,community.getComID());
        result(request,response);
    }
    protected  void updateDate(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
       // String associationNum=(String)request.getSession().getAttribute("associationNum");
      //  Community community = (Community)request.getSession().getAttribute("community");
        String value=request.getParameter("valueSer");
        String order=request.getParameter("orderSer");
        databaseco.updateDate(order,value);
        result(request,response);
    }
*/
    protected void result(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
      // String associationNum=(String)request.getSession().getAttribute("associationNum");
        Community community = (Community)request.getSession().getAttribute("community");
        List<Fund> fundInfo=databaseco.getFundInfo(community.getComID());
        double money=databaseco.getAssociationMoney(community.getComID());//获得当前余额
        request.setAttribute("money",money);
        request.setAttribute("fundInfo",fundInfo);
        RequestDispatcher view = request.getRequestDispatcher("/money/funds.jsp");
        view.forward(request,response);
       /* response.getWriter().println(" <script>\n" +
                "                location.href = \"/zijin.jsp\";\n" +
                "            </script>");*/
    }

}
