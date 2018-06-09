package com.star.cloud;

import com.star.cloud.DBUtils.DBUtil;
import com.star.cloud.excelsax.ExampleEventUserModel;
import com.star.cloud.excelsax.FileUtils;
import com.star.cloud.excelsax.ValueUtils;
import com.star.cloud.thread.ClientThread;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.UUID;

public class App2 {
	public static void main(String args []){
		Connection conn = null;
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			CallableStatement cs = conn.prepareCall("{call getKeHuTj(?,?,?,?,?,?)}");
			cs.setString("ckName","河北");
			cs.setInt("nianfen",2018);
			cs.setInt("yuefen",4);
			cs.setInt("sfhz",1);
			cs.setInt("pageSize",5);
			cs.setInt("pageNo",0);
			
			
			ResultSet rs = cs.executeQuery();
			System.out.println(rs.toString());
			while (rs.next()) {
				String sfhz = rs.getString(1);
				String ckName = rs.getString(2);
				String flmzb = rs.getString(3);
				String zjzzcs = rs.getString(4);
				System.out.println(sfhz+"，"+ckName+"，"+flmzb+"，"+zjzzcs);
			}
			
			cs.close();
		}catch (Exception e  ){
			e.printStackTrace();
		}finally {
			if(null!=conn){
				DBUtil.closeConnection();
			}
			
		}
		
	}
	
	
}
