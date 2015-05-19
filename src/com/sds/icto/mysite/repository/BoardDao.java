package com.sds.icto.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.domain.BoardVo;
@Repository
public class BoardDao {
	private Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection conn = null;
		// 1. 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2. connection 생성
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(dbURL, "webdb", "webdb");

		return conn;
	}

	public void insert(BoardVo vo) throws SQLException, ClassNotFoundException {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. Statement 준비, SQL문 날리기
		String sql = "insert into board"
				+ " values(board_no_seq.nextval, ?, ?, ?, ?, 1, sysdate)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		// 3. binding
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContext());
		pstmt.setLong(3, vo.getMem_no());
		pstmt.setString(4, vo.getMem_name());
		
		// 4. query 실행
		pstmt.executeUpdate();
		// 5. 자원정리
		pstmt.close();
		conn.close();
	}

	public void delete(BoardVo vo) throws SQLException, ClassNotFoundException {
		// 1. Connection 생성
		Connection conn = getConnection();

		// 2. Statement 준비, SQL문 날리기
		String sql = "delete from board where no=? and member_no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		// 3. binding
		pstmt.setLong(1, vo.getNo());
		pstmt.setLong(2, vo.getMem_no());

		// 4. query 실행
		pstmt.executeUpdate();
		// 5. 자원정리
		pstmt.close();
		conn.close();
	}

	public List<BoardVo> fetchList() throws SQLException,
			ClassNotFoundException {
		// 1. Connection 생성
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = getConnection();

		// 2. Statement 준비, SQL문 날리기
		String sql = "select * FROM BOARD";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		// 3. db가져오기
		while (rs.next()) {
			Long no = rs.getLong(1);
			String title = rs.getString(2);
			String context = rs.getString(3);
			Long mem_no = rs.getLong(4);
			String mem_name = rs.getString(5);
			String date = rs.getString(7);

			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContext(context);
			vo.setMem_no(mem_no);
			vo.setMem_name(mem_name);
			vo.setDate(date);

			list.add(vo);
		}

		// 4. 자원정리
		rs.close();
		stmt.close();
		conn.close();

		return list;
	}

	public BoardVo show(BoardVo vo) throws SQLException, ClassNotFoundException {
		// 1. Connection 생성
		Connection conn = getConnection();
		// 2. Statement 준비, SQL문 날리기
		String sql = "select * FROM BOARD where no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, vo.getNo());

		ResultSet rs = pstmt.executeQuery();
		// 3. db가져오기
		rs.next();
		Long no = rs.getLong(1);
		String title = rs.getString(2);
		String context = rs.getString(3);
		Long mem_no = rs.getLong(4);
		String mem_name = rs.getString(5);
		String date = rs.getString(7);

		vo.setNo(no);
		vo.setTitle(title);
		vo.setContext(context);
		vo.setMem_no(mem_no);
		vo.setMem_name(mem_name);
		vo.setDate(date);

		// 4. 자원정리
		rs.close();
		pstmt.close();
		conn.close();

		return vo;
	}
}
