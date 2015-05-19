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

import com.sds.icto.mysite.domain.GuestBookVo;
import com.sds.icto.mysite.exception.GuestBookDaoException;

@Repository
public class GuestBookDao {
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

	public void insert(GuestBookVo vo) {
		try {
			// 1. Connection 생성
			Connection conn = getConnection();

			// 2. Statement 준비, SQL문 날리기
			String sql = " insert into guestbook values(guestbook_seq.nextval, ?, ?, ?, sysdate)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 3. binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContext());

			// 4. query 실행
			pstmt.executeUpdate();
			// 5. 자원정리
			pstmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException ex) {
			throw new GuestBookDaoException(ex.getMessage());
		}
	}

	public void delete(GuestBookVo vo) {
		try {
			// 1. Connection 생성
			Connection conn = getConnection();

			// 2. Statement 준비, SQL문 날리기
			String sql = " delete from guestbook where no=? and password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 3. binding
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());

			// 4. query 실행
			pstmt.executeUpdate();
			// 5. 자원정리
			pstmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException ex) {
			throw new GuestBookDaoException(ex.getMessage());
		}
	}

	public List<GuestBookVo> fetchList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		try {
			// 1. Connection 생성
			Connection conn = getConnection();

			// 2. Statement 준비, SQL문 날리기
			String sql = "select * FROM GUESTBOOK";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 4. db가져오기
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String context = rs.getString(4);

				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContext(context);

				list.add(vo);
			}

			// 5. 자원정리
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException ex) {
			throw new GuestBookDaoException(ex.getMessage());
		}

		return list;
	}
}
