package com.example.albaleh;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin {


    private String Password, userName;

    public String getPassword() {
        return Password;
    }

    public String getUserName() {
        return userName;
    }

    public Admin() {
        this.Password = "admin";
        this.userName = "admin";
        this.status = false;
    }

    private boolean status;



    public  boolean CheckIfIsSTATES (int idowners,int idhouse ,int idfloorsnumber ,int idapartments , int idadv) {



        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            // Create a prepared statement with a parameterized query
            String sqlQuery = "select  ADVSTATES   FROM advertisement WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ? and idadv = ? and  ADVSTATES = 1  ";
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);

            pstmt.setInt(1, idowners);
            pstmt.setInt(2, idhouse);
            pstmt.setInt(3, idfloorsnumber);
            pstmt.setInt(4, idapartments);
            pstmt.setInt(5, idadv);

            ResultSet rs = pstmt.executeQuery();




            if (rs.next()){

                return true ;
            }

            else {
                return false ;


            }



        } catch (Exception e ){

            e.printStackTrace();

        }


        return false;
    }




    public  boolean CheckIfIsProcceing (int idowners,int idhouse ,int idfloorsnumber ,int idapartments , int idadv) {



        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            // Create a prepared statement with a parameterized query
            String sqlQuery = "select  ISPROCESS   FROM advertisement WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ? and idadv = ? and  ISPROCESS = 1  ";
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);


            // Execute the query
            pstmt.setInt(1, idowners);
            pstmt.setInt(2, idhouse);
            pstmt.setInt(3, idfloorsnumber);
            pstmt.setInt(4, idapartments);
            pstmt.setInt(5, idadv);
            ResultSet rs = pstmt.executeQuery();

         if (rs.next()){

             return true ;
         }

         else {

             return false ;
         }



          } catch (Exception e ){

            e.printStackTrace();

        }


        return false;
    }

    public void RefuseAds (int idowners,int idhouse ,int idfloorsnumber ,int idapartments , int idadv){
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            String sqlTenantQuery = "UPDATE ADVERTISEMENT SET ISPROCESS = '0', ADVSTATES = '2' WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ? and idadv = ? and ISPROCESS = 1 ";

                PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, idowners);
                pstmtTenant.setInt(2, idhouse);
                pstmtTenant.setInt(3, idfloorsnumber);
                pstmtTenant.setInt(4, idapartments);
                pstmtTenant.setInt(5, idadv);
                pstmtTenant.executeUpdate();



        } catch (Exception e) {

            System.out.println("Check the values entered and the status of each apartment");
        }
    }







    public void AcceptAds(int idowners,int idhouse ,int idfloorsnumber ,int idapartments , int idadv){
         try {
             Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

             String sqlTenantQuery = "UPDATE ADVERTISEMENT SET ISPROCESS = '0', ADVSTATES = '1' WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ? and idadv = ? and ISPROCESS = 1 ";

                 PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
                 pstmtTenant.setInt(1, idowners);
                 pstmtTenant.setInt(2, idhouse);
                 pstmtTenant.setInt(3, idfloorsnumber);
                 pstmtTenant.setInt(4, idapartments);
                 pstmtTenant.setInt(5, idadv);
                 pstmtTenant.executeUpdate();



         } catch (Exception e) {

             System.out.println("Check the values entered and the status of each apartment");
         }
     }

    public boolean ShowAcceptedAds (){

        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            // Create a prepared statement with a parameterized query
            String sqlQuery = "select idowners, idhouse , idfloorsnumber , idapartments , idadv  , advdescription from advertisement WHERE advstates = 1";
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);



            ResultSet rs = pstmt.executeQuery();



            while (rs.next()) {

                int idHouse = rs.getInt("IDHOUSE");
                int idFloorsNumber = rs.getInt("IDFLOORSNUMBER");
                int idOwner = rs.getInt("idowners");
                int idApartments = rs.getInt("IDAPARTMENTS");
                int idAdv = rs.getInt("idadv");
                String dec = rs.getString("advdescription");




                StringBuilder message = new StringBuilder();
                message.append("IDHOUSE: ").append(idHouse).append("\n");
                message.append("IDFLOORSNUMBER: ").append(idFloorsNumber).append("\n");
                message.append("IDOWNER: ").append(idOwner).append("\n");
                message.append("IDAPARTMENTS: ").append(idApartments).append("\n");
                message.append("idAdv: ").append(idAdv).append("\n");
                message.append("description Adv : ").append(dec).append("\n");
                message.append("\n").append("--------------------------------------------------").append("\n");

                System.out.println(message.toString());
            }

            rs.close();
            pstmt.close();
            con.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }








    public boolean ShowAdaWaitingِAcceptance (){

        try {
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

        // Create a prepared statement with a parameterized query
        String sqlQuery = "select idowners, idhouse , idfloorsnumber , idapartments , idadv  , advdescription from advertisement WHERE isprocess = 1";
        PreparedStatement pstmt = con.prepareStatement(sqlQuery);


        // Execute the query
        ResultSet rs = pstmt.executeQuery();



            while (rs.next()) {

                int idHouse = rs.getInt("IDHOUSE");
                int idFloorsNumber = rs.getInt("IDFLOORSNUMBER");
                int idOwner = rs.getInt("idowners");
                int idApartments = rs.getInt("IDAPARTMENTS");
                int idAdv = rs.getInt("idadv");
                String dec = rs.getString("advdescription");



                // Display the information using JOptionPane
                StringBuilder message = new StringBuilder();
                message.append("IDHOUSE: ").append(idHouse).append("\n");
                message.append("IDFLOORSNUMBER: ").append(idFloorsNumber).append("\n");
                message.append("IDOWNER: ").append(idOwner).append("\n");
                message.append("IDAPARTMENTS: ").append(idApartments).append("\n");
                message.append("idAdv: ").append(idAdv).append("\n");
                message.append("description Adv : ").append(dec).append("\n");
                message.append("\n").append("--------------------------------------------------------").append("\n");

                System.out.println(message.toString());
            }

            rs.close();
            pstmt.close();
            con.close();
            return true ;

    } catch (Exception e) {
        e.printStackTrace();
    }


        return false ;
    }


    public boolean  WatchingReservations(){

        try {
            // Establish the database connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            // Create a prepared statement with a parameterized query
            String sqlQuery = "select  DISTINCT idowner , idhouse , idfloorsnumber , idapartments FROM resident ";
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int idHouse = rs.getInt("IDHOUSE");
                int idFloorsNumber = rs.getInt("IDFLOORSNUMBER");
                int idOwner = rs.getInt("IDOWNER");
                int idApartments = rs.getInt("IDAPARTMENTS");


                // Display the information using JOptionPane
                StringBuilder message = new StringBuilder();
                message.append("IDOWNER: ").append(idOwner).append("\n");
                message.append("IDHOUSE: ").append(idHouse).append("\n");
                message.append("IDFLOORSNUMBER: ").append(idFloorsNumber).append("\n");
                message.append("IDAPARTMENTS: ").append(idApartments).append("\n");
                message.append("\nIDTENANTS:\n");

                // Retrieve the associated IDTENANTS
                String sqlTenantQuery = "SELECT IDTENANTS FROM RESIDENT WHERE IDHOUSE = ? and IDFLOORSNUMBER = ? and IDOWNER = ? and IDAPARTMENTS = ?  " ;
                PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, idHouse);
                pstmtTenant.setInt(2, idFloorsNumber);
                pstmtTenant.setInt(3, idOwner);
                pstmtTenant.setInt(4, idApartments);
                ResultSet rsTenant = pstmtTenant.executeQuery();
                int i = 1 ;
                while (rsTenant.next()) {

                    int idTenant = rsTenant.getInt("IDTENANTS");
                    message.append(i +"-").append(idTenant).append("\n");
                    i++;
                }

                rsTenant.close();
                pstmtTenant.close();

                System.out.println(message.toString());
            }

            rs.close();
            pstmt.close();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
        }

      return  false ;
    }

    public boolean TestWatchingReservationsIsEmpty() {



        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            // Create a prepared statement with a parameterized query
            String sqlQuery = "select * FROM resident";
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);


            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                rs.close(); // Close the result set
                pstmt.close(); // Close the prepared statement
                con.close(); // Close the connection
                return false;

            } else {

                rs.close(); // Close the result set
                pstmt.close(); // Close the prepared statement
                con.close(); // Close the connection
                return true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;



}

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
}
