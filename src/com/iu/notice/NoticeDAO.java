package com.iu.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.iu.util.DBControl;

public class NoticeDAO {
	
	public static void main(String[] args) throws Exception{
		NoticeDAO noticeDAO = new NoticeDAO();
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setTitle("s");
		noticeDTO.setContents("s");
		noticeDTO.setWriter("s");
		
		int a = noticeDAO.insert(noticeDTO);
		System.out.println(a);
		
		
	}
	
	public void update() {
		
		
	}
	
	public int insert(NoticeDTO dto) throws Exception{
		
		Connection con = DBControl.getconnect();
		String sql = "insert into notice values(notice_seq.nextval,?,?,?,sysdate,0)";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, dto.getTitle());
		st.setString(2, dto.getContents());
		st.setString(3, dto.getWriter());
		
		int result = st.executeUpdate();
		
		DBControl.disconnect(st, con);
		
		return result;
	}
	
	public int delete(int num) throws Exception {
		
		Connection con = DBControl.getconnect();
		String sql = "delete notice where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		int result = st.executeUpdate();
		DBControl.disconnect(st, con);
		
		return result;
		
	}
	
	public NoticeDTO selectOne(int num) throws Exception {
		
		Connection con = DBControl.getconnect();
		String sql = "select * from notice where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		NoticeDTO nDto = null;
		if(rs.next()) {
			nDto=new NoticeDTO();
			nDto.setNum(rs.getInt("num"));
			nDto.setTitle(rs.getString("title"));
			nDto.setContents(rs.getString("contents"));
			nDto.setWriter(rs.getString("writer"));
			nDto.setReg_date(rs.getDate("reg_date"));
			nDto.setHit(rs.getInt("hit"));
			
		}
		DBControl.disconnect(rs, st, con);
		return nDto;
		
	}
	
	public ArrayList<NoticeDTO> selectAll() throws Exception {
		
		Connection con = DBControl.getconnect();
		String sql = "select num,title,writer,reg_date,hit from notice order by num desc";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		ArrayList<NoticeDTO> ar = new ArrayList<>();
		NoticeDTO nDto = null;
		while(rs.next()) {
			nDto = new NoticeDTO();
			nDto.setNum(rs.getInt("num"));
			nDto.setTitle(rs.getString("title"));
			nDto.setWriter(rs.getString("writer"));
			nDto.setReg_date(rs.getDate("reg_date"));
			nDto.setHit(rs.getInt("hit"));
			
			ar.add(nDto);
		}
		DBControl.disconnect(rs, st, con);
		return ar;
	}
	

}
