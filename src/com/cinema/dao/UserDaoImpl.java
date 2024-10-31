package com.cinema.dao;

import com.cinema.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends UserDao {
    private UserRoleDaoImpl userRoleDao;

    public UserDaoImpl(){
        this.userRoleDao = new UserRoleDaoImpl();
    }

    @Override
    public void setUpdateParameters(PreparedStatement preparedStatement, User entity) {

    }

    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public User convertToObject(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("Id"));
        user.setUsername(resultSet.getString("name"));
        user.setUserRole(this.userRoleDao.findbyId(resultSet.getInt("role_id")));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

    @Override
    public String getInsertValues() {
        return "(name, email, password, role_id) values (?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "update "+this.getTableName()+" set name = ? , email = ?, password = ?, role_id = ? where id = ?";
    }

    @Override
    public void setParameters(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setString(1, entity.getUsername());
        preparedStatement.setString(2, entity.getEmail());
        preparedStatement.setString(3, entity.getPassword());
        preparedStatement.setInt(4, entity.getUserRole().getId());
    }
}
