package com.cinema.dao;

import com.cinema.model.User;
import com.cinema.model.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleDaoImpl extends UserRoleDao{
    @Override
    public void setUpdateParameters(PreparedStatement preparedStatement, UserRole entity) {

    }

    @Override
    public String getTableName() {
        return "user_roles";
    }

    @Override
    public UserRole convertToObject(ResultSet resultSet) throws SQLException {
        UserRole userRole = new UserRole();
        userRole.setId(resultSet.getInt("id"));
        userRole.setName(resultSet.getString("name"));
        userRole.setDescription(resultSet.getString("description"));
        return userRole;
    }

    @Override
    public String getInsertValues() {
        return "(name, description) values (?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "update "+this.getTableName()+" set name= ?, description = ? where id = ?";
    }

    @Override
    public void setParameters(PreparedStatement preparedStatement, UserRole entity) throws SQLException {

    }
}
