



//黄友明  2018/7/3
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AddCommunity extends HttpServlet {
    public Databaseco databaseco = new Databaseco();
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
    protected void viewCommunities(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
        String[][] communities = databaseco.viewCommnuities();
        request.setAttribute("communities",communities);
        RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/viewCommunities.jsp");
        view.forward(request,response);
    }
    protected void viewDetail(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
        String id = request.getParameter("community");
       String[] viewDetail =  databaseco.viewDetail();
       request.setAttribute("viewDetail",viewDetail);
        RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/viewdetail.jsp");
        view.forward(request,response);

    }
    protected void addCommunity(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
        String stuID = (String)request.getSession().getAttribute("stuID");
        String commID = request.getParameter("community");
        databaseco.addCommunity(stuID,commID);
        RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/addResult.jsp");
        view.forward(request,response);
    }
}
