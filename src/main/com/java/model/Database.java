package java.com.model;

import java.sql.*;

public class Database {

    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;
    public String url = null;
    public String user = null;
    public String password = null;
    public String sql = null;
    public void conn()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); //加载mysq驱动
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载错误");
            e.printStackTrace();//打印出错详细信息
        }
        try {
            url = "jdbc:oracle:thin:@localhost:1521:oracle";//简单写法：url = "jdbc:myqsl://localhost/test(数据库名)? user=root(用户)&password=yqs2602555(密码)";
            user = "user_01";
            password = "123";
            conn = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("数据库链接成功");
            stmt = (Statement) conn.createStatement();


        } catch (SQLException e) {
            System.out.println("数据库链接错误");
            e.printStackTrace();

        }

    }
   /*
   函数名 view
   参数 无
   功能: 获得社团图片,社团名称,社团招新标语,社团等级,并按照社团等级降序排列
   返回值: 先判断rs中除标题外是否有值,有值则返回rs,没有值则返回null
    */
    public ResultSet viewCommunities()
    {

         sql = "select picture,associationNum from association  order by levell DESC ";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    /*
    函数名 viewDetail
    参数 communityID是社团ID
    功能 根据社团id获得社团图片,社团名称,社团招新标语,社团等级,社长,社团资金,社团人数
    返回值: 先判断rs中除标题外是否有值,有值则返回rs,没有值则返回null
     */
    public ResultSet viewDetail(String communityID)
    {
        ResultSet rs = null;
        return rs;
    }
    /*
    函数名 applyCommunity
    参数 studentID是用户id, communityID是社团id
    功能 根据用户id和社团id将用户对该社团的申请信息写入数据库,其余未提供信息如时间请自行补充
    返回值 无
     */
    public void applyCommunity(String studentID, String communityID)
    {

    }
    /*
    函数名 viewapplies
    参数 studentID是用户ID
    功能 根据用户ID获得该用户所有申请信息
    返回值 先判断rs中除标题外是否有值,有值则返回rs,没有值则返回null
     */
    public ResultSet viewapplies(String studentID)
    {
        return null;
    }
    /*
    函数名 cancelapply
    参数 studentID是用户id,communityID是社团id
    功能 根据用户ID和社团id删除特定申请
    返回值 无
     */
    public void cancelapply(String studentID, String communityID)
    {

    }
}
