package com.example.albaleh;


import javax.swing.*;
import java.sql.*;
import java.util.Scanner;


public class mainCode {
    static final String JDBC = "jdbc:sqlite:abbas";


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String sql = "insert into Tenants values ('1','t','234','2','wer','fsd','4','fs','fdf')";
        statement.executeUpdate(sql);
        connection.close();

//        Scanner scanner = new Scanner(System.in);
//        String command =  scanner.nextLine();
//        System.out.println(command );

 Admin admin = new Admin();







    }
}