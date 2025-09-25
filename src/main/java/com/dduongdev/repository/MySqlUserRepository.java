package com.dduongdev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dduongdev.entity.User;

@Repository
public class MySqlUserRepository implements UserRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MySqlUserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * RowMapper để map dữ liệu từ ResultSet sang User entity
	 */
	private static class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setToken(rs.getString("token"));
			return user;
		}
	}

	@Override
	public Optional<User> findByUsername(String username) {
		String sql = "SELECT id, username, password, token FROM `user` WHERE username = ?";
		return jdbcTemplate.query(sql, new UserRowMapper(), username).stream().findFirst();
	}

	@Override
	public Optional<User> findById(int id) {
		String sql = "SELECT id, username, password, token FROM `user` WHERE id = ?";
		return jdbcTemplate.query(sql, new UserRowMapper(), id).stream().findFirst();
	}

	@Override
	public void update(User user) {
		String sql = "UPDATE `user` SET username = ?, password = ?, token = ? WHERE id = ?";
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getToken(), user.getId());
	}
}
