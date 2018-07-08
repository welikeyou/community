package java.com.control;// 黄友明 2018/7/4
import java.com.model.Database;

import java.sql.ResultSet;

public class Databaseco {
public Database database = new Database();
    public String[][] show(ResultSet rs)
    {
        StringBuffer buffer=new StringBuffer();
        try{
            int colNum=rs.getMetaData().getColumnCount();//表格列数
            int i=1;
            while(true){
                String name=rs.getMetaData().getColumnName(i).toString();
                i++;
                if(i>colNum) break;
            }

            while(rs.next()){
                buffer.append(rs.getString("associationNum")+"/t");
                buffer.append(rs.getString("picture")+"/n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(rs!=null){
                    rs.close();


                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        String  result=new String(buffer);

        int n_length = 8;
        String[] res = result.split("/n");
        String[] rest = new String[2];
        String[][] re =  new String[n_length][2];
        for(int i = 0;i<n_length;i++)
        {
            rest = res[i].split("/t");
            re[i][0] = rest[0];
            re[i][1] = rest[1];
        }

        return re;
    }
    public String[][] viewCommnuities()
    {
database.conn();
        ResultSet rs = database.viewCommunities();
       String[][] communities = show(rs);


               return communities;
    }
    public String[] viewDetail(String communityID)
    {



        ResultSet rs = database.viewDetail( communityID);
        String[] strings = new String[8];//社团名称,社长,类型,迎新标语,社团成员人数,社团人数上限,社团级别,社团资金 ,
        for(int i = 0;i<8;i++)
        {
            strings[i] = String.valueOf(i);
        }

        return strings;
    }

    public void  applyCommunity(String studentID, String communityID)
    {
        database.applyCommunity(studentID,communityID);
    }
    public String[][] viewapplie(String studentID)
    {
        ResultSet rs = database.viewapplies(studentID);
        String[][] strings = new String[10][4];
        for(int  i = 0;i<10;i++)
        {
            for(int j = 0;j<4;j++)
                strings[i][j] = String.valueOf(j);
        }


        return strings;
    }
}
