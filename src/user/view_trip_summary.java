package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class view_trip_summary {
    //view trip_Summary
    public static void view_trip_summary(Connection conn, String u_idd)throws Exception
    {
        String view="SELECT * FROM trip_Summary where uid=?";
        PreparedStatement pst=conn.prepareStatement(view);
        pst.setInt(1,Integer.parseInt(u_idd));
        ResultSet rs=pst.executeQuery();
        while (rs.next())
        {
            System.out.println("DATE :"+rs.getDate("date")+" SOURCE :"+rs.getString("source")+" DESTINATION :"+rs.getString("destination")+" TRAVEL BY :"+rs.getString("trip_by"));
        }
    }
}
