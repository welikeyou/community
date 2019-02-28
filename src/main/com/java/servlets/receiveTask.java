package com.java.servlets;/*
日期：2018/7/8
执行人：李兰
 */
import com.java.control.Databaseco;
import com.java.model.Student;
import com.java.model.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;

public class receiveTask extends HttpServlet {

    public Databaseco databaseco = new Databaseco();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //get which method to be used
        String methodName = request.getParameter("method");
        //  System.out.println(methodName + "方法名");
        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);//获得方法
            method.setAccessible(true);
            method.invoke(this, request, response);//执行方法
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //get which method to be used
        String methodName = request.getParameter("method");
       System.out.println(methodName + "方法名");
        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);//获得方法
            method.setAccessible(true);
            method.invoke(this, request, response);//执行方法
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    //以上为通用代码
    protected  void showTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Student student=(Student)request.getSession().getAttribute("loginStudent");
     // databaseco.deleTask(student.getId());//删除超时或完成的大于等于30天的任务
        databaseco.chgState(student.getId());//先更改状态
        List<Task> urgentTask=databaseco.getUrgentTask(student.getId());
        List<Task> doingTask=databaseco.getDoingTask(student.getId());
        List<Task> overtimeTask=databaseco.getOvertimeTask(student.getId());
        List<Task> finishedTask=databaseco.getFinishedTask(student.getId());
      /*  List<Task> urgentTask=databaseco.getUrgentTask("123");
        List<Task> doingTask=databaseco.getDoingTask("123");
        List<Task> overtimeTask=databaseco.getOvertimeTask("123");
        List<Task> finishedTask=databaseco.getFinishedTask("123");
        System.out.println(urgentTask.get(0).getClubName()+"调用");*/
        request.setAttribute("urgentTask",urgentTask);
        request.setAttribute("doingTask",doingTask);
        request.setAttribute("overtimeTask",overtimeTask);
        request.setAttribute("finishedTask",finishedTask);
        request.getRequestDispatcher("/myImformation/woderenwu.jsp").forward(request,response);
    }

    protected  void toFinished(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student=(Student)request.getSession().getAttribute("loginStudent");
        String taskNum=request.getParameter("taskNum");
        databaseco.toFinishedBytaskNum(taskNum,student.getId());
        List<Task> urgentTask=databaseco.getUrgentTask(student.getId());
        List<Task> doingTask=databaseco.getDoingTask(student.getId());
        List<Task> overtimeTask=databaseco.getOvertimeTask(student.getId());
        List<Task> finishedTask=databaseco.getFinishedTask(student.getId());
        request.setAttribute("urgentTask",urgentTask);
        request.setAttribute("doingTask",doingTask);
        request.setAttribute("overtimeTask",overtimeTask);
        request.setAttribute("finishedTask",finishedTask);
        request.getRequestDispatcher("/myImformation/woderenwu.jsp").forward(request,response);
    }
    protected void load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到要下载的文件名
               String fileName = request.getParameter("filepath");  //23239283-92489-阿凡达.avi
               fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
               //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
             String fileSaveRootPath=this.getServletContext().getRealPath("");
             //通过文件名找出文件的所在目录
              String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
            //得到要下载的文件
              File file = new File(path + "\\" + fileName);
             //如果文件不存在
              if(!file.exists()){
                    //  request.setAttribute("message", "您要下载的资源已被删除！！");
                   // request.getRequestDispatcher("/message.jsp").forward(request, response);
                  response.getWriter().println("你要下的文件已不存在");
                      return;
                  }
       //处理文件名
             String realname = fileName.substring(fileName.indexOf("_")+1);
               //设置响应头，控制浏览器下载该文件
               response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
              //读取要下载的文件，保存到文件输入流
               FileInputStream in = new FileInputStream(path + "\\" + fileName);
               //创建输出流
              OutputStream out = response.getOutputStream();
              //创建缓冲区
               byte buffer[] = new byte[1024];
               int len = 0;
                //循环将输入流中的内容读取到缓冲区当中
               while((len=in.read(buffer))>0){
                      //输出缓冲区的内容到浏览器，实现文件下载
                      out.write(buffer, 0, len);
                  }
                       in.close();
              //关闭输出流
               out.close();
               response.getWriter().println("下载成功");

    }
    /**
     56     * @Method: findFileSavePathByFileName
     57     * @Description: 通过文件名和存储上传文件根目录找出要下载的文件的所在路径
     58     * @Anthor:孤傲苍狼
     59     * @param filename 要下载的文件名
     60     * @param saveRootPath 上传文件保存的根目录，也就是/WEB-INF/upload目录
     61     * @return 要下载的文件的存储目录
     62     */
    public String findFileSavePathByFileName(String filename,String saveRootPath){
            int hashcode = filename.hashCode();
          int dir1 = hashcode&0xf;  //0--15
            int dir2 = (hashcode&0xf0)>>4;  //0-15
              String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
                File file = new File(dir);
                if(!file.exists()){
                     //创建目录
                     file.mkdirs();
                    }
              return dir;
            }
}