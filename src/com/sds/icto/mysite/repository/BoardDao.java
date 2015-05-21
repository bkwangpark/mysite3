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
import com.sds.icto.mysite.exception.BoardDaoException;

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

	public void insert(BoardVo vo) {
		try {
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
		} catch (SQLException | ClassNotFoundException ex) {
			throw new BoardDaoException(ex.getMessage());
		}
	}

	public void update(BoardVo vo) {
		try {
			// 1. Connection 생성
			Connection conn = getConnection();

			// 2. Statement 준비, SQL문 날리기
			String sql = "update board set title=?, content=?"
					+ " where no=? and member_no=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 3. binding
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContext());
			pstmt.setLong(3, vo.getNo());
			pstmt.setLong(4, vo.getMem_no());

			// 4. query 실행
			pstmt.executeUpdate();
			// 5. 자원정리
			pstmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException ex) {
			throw new BoardDaoException(ex.getMessage());
		}
	}

	public void delete(BoardVo vo) {
		try {
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
		} catch (SQLException | ClassNotFoundException ex) {
			throw new BoardDaoException(ex.getMessage());
		}
	}

	public List<BoardVo> findList(String key) {
		List<BoardVo> findlist = new ArrayList<BoardVo>();
		try {
			// 1. Connection 생성
			Connection conn = getConnection();

			// 2. Statement 준비, SQL문 날리기
			String sql = "select * FROM BOARD where title like ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			String newtitle = "%" + key + "%";

			pstmt.setString(1, newtitle);

			ResultSet rs = pstmt.executeQuery();

			// 3. db가져오기
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String context = rs.getString(3);
				Long mem_no = rs.getLong(4);
				String mem_name = rs.getString(5);
				Long view_cnt = rs.getLong(6);
				String date = rs.getString(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContext(context);
				vo.setMem_no(mem_no);
				vo.setMem_name(mem_name);
				vo.setView_cnt(view_cnt);
				vo.setDate(date);

				findlist.add(vo);
			}

			// 4. 자원정리
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException ex) {
			throw new BoardDaoException(ex.getMessage());
		}
		return findlist;
	}

	public List<BoardVo> fetchList() {
		List<BoardVo> list = new ArrayList<BoardVo>();
		try {
			// 1. Connection 생성
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
				Long view_cnt = rs.getLong(6);
				String date = rs.getString(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContext(context);
				vo.setMem_no(mem_no);
				vo.setMem_name(mem_name);
				vo.setView_cnt(view_cnt);
				vo.setDate(date);

				list.add(vo);
			}

			// 4. 자원정리
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException ex) {
			throw new BoardDaoException(ex.getMessage());
		}
		return list;
	}

	public void updateView(BoardVo vo) {
		try {
			Connection conn = getConnection();

			String sql = "update board set view_cnt=view_cnt+1 where no=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());

			pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException ex) {
			throw new BoardDaoException(ex.getMessage());
		}
	}

	public BoardVo show(Long no) {
		BoardVo vo = new BoardVo();
		try {
			// 1. Connection 생성
			Connection conn = getConnection();
			// 2. Statement 준비, SQL문 날리기
			String sql = "select * FROM BOARD where no=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			ResultSet rs = pstmt.executeQuery();
			// 3. db가져오기
			rs.next();
			Long no1 = rs.getLong(1);
			String title = rs.getString(2);
			String context = rs.getString(3);
			Long mem_no = rs.getLong(4);
			String mem_name = rs.getString(5);
			Long view_cnt = rs.getLong(6);
			String date = rs.getString(7);
			
			vo.setNo(no1);
			vo.setTitle(title);
			vo.setContext(context);
			vo.setMem_no(mem_no);
			vo.setMem_name(mem_name);
			vo.setView_cnt(view_cnt);
			vo.setDate(date);

			// 4. 자원정리
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException ex) {
			throw new BoardDaoException(ex.getMessage());
		}
		return vo;
	}
}
