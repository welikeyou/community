import java.sql.ResultSet;

public class Databaseco {
public Database  database = new Database();
    public String[][] viewCommnuities()
    {
        database.conn();


        ResultSet rs = database.viewCommunities();
        String[][] strings = new String[10][4];
        for(int  i = 0;i<10;i++)
        {
            for(int j = 0;j<4;j++)
            strings[i][j] = String.valueOf(j);
        }

        database.off();
               return strings;
    }
    public String[] viewDetail()
    {
        database.conn();


        ResultSet rs = database.viewDetail();
        String[] strings = new String[8];//社团名称,社长,类型,迎新标语,社团成员人数,社团人数上限,社团级别,社团资金 ,
        for(int i = 0;i<8;i++)
        {
            strings[i] = String.valueOf(i);
        }
        database.off();
        return strings;
    }
    public void addCommunity(String studentID, String communityID)
    {
        database.conn();

        database.addCommunity(studentID,communityID);


        database.off();


    }
}
