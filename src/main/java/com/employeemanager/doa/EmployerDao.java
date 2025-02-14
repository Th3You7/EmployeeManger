package com.employeemanager.doa;

import com.employeemanager.model.Employer;

import java.sql.*;
import java.util.ArrayList;

public class EmployerDao {
    private final String  jdbcUrl = "jdbc:mysql://localhost:3306/employeedb?useSSL=false";
    private final String jdbcDriver = "com.mysql.jdbc.Driver";
    private final String username = "root";
    private final String password = "th3you78";

    private static final String SELECT_EMPLOYEE = "SELECT * FROM EMPLOYEES WHERE id=?";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM EMPLOYEES";
    private static final String INSERT_EMPLOYEE = "INSERT INTO EMPLOYEES" + "  VALUES(?, ?, ?, ?, ?);";
    private static final String DELETE_EMPLOYEE = "DELETE FROM EMPLOYEES WHERE id=?";
    private static final String UPDATE_EMPLOYEE = "UPDATE EMPLOYEES SET name = ?, gender = ?, address = ?, phone = ? WHERE id = ?";

    public EmployerDao() {}

    // Establish JDBC Connection
    protected Connection getConnection() {
        Connection conn = null;
        try {
            // Load Type-4 Driver / MySQL Type-4 driver class
            Class.forName(jdbcDriver);
            // Establish a connection
            conn = DriverManager.getConnection(jdbcUrl, username, password);
        }catch (SQLException e){
            System.err.println("SQL Error: " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("JDBC Driver not found: " + e.getMessage());
        }
        return conn;
    }

    // Get an employee by id
    public Employer getEmployer(int id) {
        Employer employer = null;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_EMPLOYEE);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int _id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                employer = new Employer(_id, name, gender, address, phone, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return employer;
    }
    // get all employers
    public ArrayList<Employer> getAllEmployers() {
        ArrayList<Employer> employers = new ArrayList<>();

        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_EMPLOYEES);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int _id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                employers.add(new Employer(_id, name, gender, address, phone, email));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return employers;
    }
    // Create an employee
    public void insertEmployee(Employer user) {
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_EMPLOYEE);
            preparedStatement.setString(1, user.getName() );
            preparedStatement.setString(2, user.getGender() );
            preparedStatement.setString(3, user.getAddress() );
            preparedStatement.setString(4, user.getPhone() );
            preparedStatement.setString(5, user.getEmail() );

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    // Edit an Employee
    public void updateEmployee(Employer user) {
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_EMPLOYEE);
            preparedStatement.setString(1, user.getName() );
            preparedStatement.setString(2, user.getGender() );
            preparedStatement.setString(3, user.getAddress() );
            preparedStatement.setString(4, user.getPhone() );
            preparedStatement.setInt(5, user.get_id() );

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    // delete an Employee
    public void deleteEmployee(int id) {
        try {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(DELETE_EMPLOYEE);
        preparedStatement.setInt(1, id );
        preparedStatement.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



// get all employers;
// get an employer by id;
// get employers by gender;
// get employers by city;
// add an employer details;
// edit an employer;
// delete an employer by id;


