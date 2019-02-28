package com.java.servlets;

import com.java.control.Databaseco;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import com.java.model.Community;
import com.java.model.Recruitment;
import com.java.model.Student;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*
功能:实现堆分配任务的处理
创建者:黄友明
修改者
 */
public class assiginment extends HttpServlet {
    public Databaseco databaseco = new Databaseco();
    private Recruitment recruitment = new Recruitment();
    private String filePath;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       /* request.getSession().setAttribute("stu",student);
        response.sendRedirect("/mainpages/afterlogin.jsp");*/
        //get which method to be used
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String methodName = request.getParameter("method");
        // methodName = "reportnew";
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
        //以上为通用代码
    }

    private void upload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filename = null;
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath1 = this.getServletContext().getRealPath("/upload");
        String savePath2 = "D:\\community\\src\\main\\webapp\\upload";
        File file1 = new File(savePath1);
        File file2 = new File(savePath2);
        //判断上传文件的保存目录是否存在
        if (!file1.exists() && !file1.isDirectory()) {
            System.out.println(savePath1 + "目录不存在，需要创建");
            //创建目录
            file1.mkdir();
        }
        if (!file2.exists() && !file2.isDirectory()) {
            System.out.println(savePath1 + "目录不存在，需要创建");
            //创建目录
            file2.mkdir();
        }
        //消息提示
        String message = "";
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
           /* upload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);

                }
            });*/
            //3、判断提交上来的数据是否是上传表单的数据
           /* if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                // return;
            }*/
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            //  StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                   /* String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);*/
                } else {//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    filename = item.getName();
                    // System.out.println(filename);
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    filename = makeFileName(filename);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out1 = new FileOutputStream(savePath1 + "\\" + filename, true);

                    //  System.out.println(savePath + "\\" + filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while ((len = in.read(buffer)) > 0) {
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out1.write(buffer, 0, len);

                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out1.close();
                    InputStream in2 = item.getInputStream();
                    FileOutputStream out2 = new FileOutputStream(savePath2 + "\\" + filename, true);
                    while ((len = in2.read(buffer)) > 0) {
                        out2.write(buffer, 0, len);
                    }
                    in2.close();;
                    out2.close();
                    //删除处理文件上传时生成的临时文件
                    //  item.delete();
                    message = "文件上传成功！";
                }
            }
        } catch (Exception e) {
            message = "文件上传失败！";
            e.printStackTrace();

        }
        //System.out.println(message);
        //  response.getWriter().println(filename);
        PrintWriter out = response.getWriter();
        filePath = "/upload/" + filename;
        out.println(filePath);

    }
    private String makeFileName(String filename){
                //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
               return UUID.randomUUID().toString() + "_" + filename;
            }
    protected void reportnew(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // request.setAttribute("message",message);
        Community community = (Community) request.getSession().getAttribute("community") ;
        recruitment.setTittle(request.getParameter("title"));
        recruitment.setIntroduction(request.getParameter("introduction"));
        recruitment.setTime(request.getParameter("time"));
        recruitment.setExamine(request.getParameter("examine"));
        recruitment.setPropaganda(request.getParameter("propaganda"));
        recruitment.setPublisher(request.getParameter("publisher"));
        recruitment.setContact(request.getParameter("contact"));
        recruitment.setDemand(request.getParameter("demand"));
        recruitment.setPostcard(filePath);
        recruitment.setAssociationNum(community.getComID());
        filePath = null;
        //recruitment.setPostcard(upload(request,response));
        databaseco.releaseRecruitment(recruitment);
        response.sendRedirect("/assignment/reportnew.jsp");

        // request.getRequestDispatcher("/mainpages/login.jsp").forward(request, response);
    }

    protected void reportwork(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Community community = (Community) request.getSession().getAttribute("community") ;
        databaseco.releaseActivity(request.getParameter("title"), community.getComName(), request.getParameter("time"), request.getParameter("address"), request.getParameter("introduction"), request.getParameter("demand"), request.getParameter("registrationTime"), request.getParameter("publisher"), request.getParameter("contact"),filePath);
        filePath = null;
        response.sendRedirect("/assignment/reportwork.jsp");
    }

    protected void assiginmentTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Community community = (Community) request.getSession().getAttribute("community") ;
        Student student = (Student)request.getSession().getAttribute("loginStudent") ;
        String title =request.getParameter("title");
        String content = request.getParameter("content");
        String DDL = request.getParameter("DDL");
        String[] receviers = request.getParameterValues("box");
        String studentNum = student.getId();
        String sender = student.getId();
        String comID = community.getComID();
        databaseco.addAssignment(comID,title,content,sender,DDL,filePath,receviers);
        filePath = null;
        response.sendRedirect("/mainpages/shouye.jsp");
    }
}
