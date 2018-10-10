package com.github.rtempleton.processors.UltraDirect.denormalizers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UDUtils {

	public static String flattenArray(List<String> list) {
		if(list.size()==0)
			return null;
		StringBuffer b = new StringBuffer();
		for(String s : list){
			b.append(s);
			b.append(",");
		}
		if(b.length()>0)
			//strip the trailing comma
			return(b.substring(0, b.length()));
		return "";
	}
	
	static void setString(PreparedStatement stmt, int pos, String val) throws SQLException{
		if(val==null){
			stmt.setNull(pos, java.sql.Types.VARCHAR);
		}else{
			stmt.setString(pos, val);
		}
	}
	
	static void setInteger(PreparedStatement stmt, int pos, Integer val) throws SQLException{
		if(val==null){
			stmt.setNull(pos, java.sql.Types.INTEGER);
		}else{
			stmt.setInt(pos, val);
		}
	}
	
	static void setFloat(PreparedStatement stmt, int pos, Float val) throws SQLException{
		if(val==null){
			stmt.setNull(pos, java.sql.Types.FLOAT);
		}else{
			stmt.setFloat(pos, val);
		}
			
	}

}
