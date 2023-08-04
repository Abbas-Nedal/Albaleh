package com.example.albaleh;


import java.sql.*;

public class Admin {
    private  static final  String textIDHOUSE="IDHOUSE";
    private static final String textIDFLOORSNUMBER="IDFLOORSNUMBER";
    private static final String textidadv="idadv";
    private static final String textIDOWNER="IDOWNERS";
    private static final  String textIDAPARTMENTS="IDAPARTMENTS";
    private static final  String TextCheck = "Check the values entered and the status of each apartment";
final  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

    private String password;
           String userName ;

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public Admin() throws SQLException {
        this.password = "admin";
        this.userName = "admin";
        this.status = false;
    }

    private boolean status;



    public boolean checkIfIsSTATES(int idowners, int idhouse, int idfloorsnumber, int idapartments, int idadv) {
        String sqlQuery = "SELECT ADVSTATES FROM advertisement WHERE idowners = ? AND idhouse = ? AND idfloorsnumber = ? AND idapartments = ? AND idadv = ? AND ADVSTATES = 1";

        try (PreparedStatement pstmt = con.prepareStatement(sqlQuery)) {
            pstmt.setInt(1, idowners);
            pstmt.setInt(2, idhouse);
            pstmt.setInt(3, idfloorsnumber);
            pstmt.setInt(4, idapartments);
            pstmt.setInt(5, idadv);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {e.printStackTrace();}return false;
    }




    public  boolean CheckIfIsProcceing (int idowners,int idhouse ,int idfloorsnumber ,int idapartments , int idadv) {



        PreparedStatement pstmt = null;
        try {
            // Create a prepared statement with a parameterized query
            String sqlQuery = "select ISPROCESS FROM advertisement WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ? and idadv = ? and ISPROCESS = 1 ";
            pstmt = con.prepareStatement(sqlQuery);

            // Execute the query
            pstmt.setInt(1, idowners);
            pstmt.setInt(2, idhouse);
            pstmt.setInt(3, idfloorsnumber);
            pstmt.setInt(4, idapartments);
            pstmt.setInt(5, idadv);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();
        } catch (Exception e) {e.printStackTrace();} finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {e.printStackTrace();}}}return false;
    }

    public boolean RefuseAds (int idowners,int idhouse ,int idfloorsnumber ,int idapartments , int idadv){
        PreparedStatement pstmtTenant = null ;
        try {



            String sqlTenantQuery = "UPDATE ADVERTISEMENT SET ISPROCESS = '0', ADVSTATES = '2' WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ? and idadv = ? and ISPROCESS = 1 ";

                 pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, idowners);
                pstmtTenant.setInt(2, idhouse);
                pstmtTenant.setInt(3, idfloorsnumber);
                pstmtTenant.setInt(4, idapartments);
                pstmtTenant.setInt(5, idadv);

            pstmtTenant.executeUpdate();

             sqlTenantQuery = "UPDATE APARTMENTS SET SERVICEAVAILABLE = '0'  WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ?";

             pstmtTenant = con.prepareStatement(sqlTenantQuery);

            pstmtTenant.setInt(1, idowners);
            pstmtTenant.setInt(2, idhouse);
            pstmtTenant.setInt(3, idfloorsnumber);
            pstmtTenant.setInt(4, idapartments);

                int x = pstmtTenant.executeUpdate();

                return x > 0;



        } catch (Exception e) {

            System.out.println(TextCheck);

        }finally {
            if (pstmtTenant != null) {
                try {
                    pstmtTenant.close();
                } catch (SQLException e) {e.printStackTrace();}}}return false;
    }







    public boolean AcceptAds(int idowners,int idhouse ,int idfloorsnumber ,int idapartments , int idadv){
        PreparedStatement pstmtTenant = null ;
         try {

             String sqlTenantQuery = "UPDATE ADVERTISEMENT SET ISPROCESS = '0', ADVSTATES = '1' WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ? and idadv = ? and ISPROCESS = 1 ";

                  pstmtTenant = con.prepareStatement(sqlTenantQuery);
                 pstmtTenant.setInt(1, idowners);
                 pstmtTenant.setInt(2, idhouse);
                 pstmtTenant.setInt(3, idfloorsnumber);
                 pstmtTenant.setInt(4, idapartments);
                 pstmtTenant.setInt(5, idadv);
                 pstmtTenant.executeUpdate();
                 pstmtTenant.close();


             sqlTenantQuery = "UPDATE APARTMENTS SET SERVICEAVAILABLE = '1' WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ?";

             pstmtTenant = con.prepareStatement(sqlTenantQuery);
             pstmtTenant.setInt(1, idowners);
             pstmtTenant.setInt(2, idhouse);
             pstmtTenant.setInt(3, idfloorsnumber);
             pstmtTenant.setInt(4, idapartments);

            int x =  pstmtTenant.executeUpdate();
if (x > 0){
    return true ;
}

         } catch (Exception e) {System.out.println(TextCheck);
         }finally {
             if (pstmtTenant != null) {
                 try {
                     pstmtTenant.close();
                 } catch (SQLException e) {e.printStackTrace();}}}return false;
     }


    public void SetIsProcessing(int idowners,int idhouse ,int idfloorsnumber ,int idapartments , int idadv){
        try {

            String sqlTenantQuery = "UPDATE ADVERTISEMENT SET ISPROCESS = '1', ADVSTATES = '0' WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ? and idadv = ?  ";

            PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
            pstmtTenant.setInt(1, idowners);
            pstmtTenant.setInt(2, idhouse);
            pstmtTenant.setInt(3, idfloorsnumber);
            pstmtTenant.setInt(4, idapartments);
            pstmtTenant.setInt(5, idadv);
            pstmtTenant.executeUpdate();



        } catch (Exception e) {System.out.println(TextCheck);}
    }

    public boolean ShowAcceptedAds (){

        try {

            // Create a prepared statement with a parameterized query
            String sqlQuery = "select idowners, idhouse , idfloorsnumber , idapartments , idadv  , advdescription from advertisement WHERE advstates = 1";
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);



            ResultSet rs = pstmt.executeQuery();



            while (rs.next()) {



                int idHouse = rs.getInt(textIDHOUSE);
                int idFloorsNumber = rs.getInt(textIDFLOORSNUMBER);
                int idOwner = rs.getInt(textIDOWNER);
                int idApartments = rs.getInt(textIDAPARTMENTS);
                int idAdv = rs.getInt(textidadv);
                String dec = rs.getString("advdescription");




                StringBuilder message = new StringBuilder();
                message.append(textIDHOUSE).append(idHouse).append("\n");
                message.append(textIDFLOORSNUMBER+":").append(idFloorsNumber).append("\n");
                message.append(textIDOWNER+": ").append(idOwner).append("\n");
                message.append(textIDAPARTMENTS+": ").append(idApartments).append("\n");
                message.append(textidadv+": ").append(idAdv).append("\n");
                message.append("description Adv : ").append(dec).append("\n");
                message.append("\n").append("--------------------------------------------------").append("\n");

                System.out.println(message.toString());
            }



            return true;

        } catch (Exception e) {e.printStackTrace();}return false;
    }









    public boolean ShowAdaWaitingِAcceptance (){

        try {

        // Create a prepared statement with a parameterized query
        String sqlQuery = "select idowners, idhouse , idfloorsnumber , idapartments , idadv  , advdescription from advertisement WHERE isprocess = 1";
        PreparedStatement pstmt = con.prepareStatement(sqlQuery);


        // Execute the query
        ResultSet rs = pstmt.executeQuery();



            while (rs.next()) {

                int idHouse = rs.getInt(textIDHOUSE);
                int idFloorsNumber = rs.getInt(textIDFLOORSNUMBER);
                int idOwner = rs.getInt(textIDOWNER);
                int idApartments = rs.getInt(textIDAPARTMENTS);
                int idAdv = rs.getInt(textidadv);
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


            return true ;

    } catch (Exception e) {e.printStackTrace();}return false ;
    }


    public boolean  WatchingReservations(){
        PreparedStatement pstmt = null;
        try {
            // Establish the database connection

            // Create a prepared statement with a parameterized query
            String sqlQuery = "select  DISTINCT idowner , idhouse , idfloorsnumber , idapartments FROM resident ";
             pstmt = con.prepareStatement(sqlQuery);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int idHouse = rs.getInt(textIDHOUSE);
                int idFloorsNumber = rs.getInt(textIDFLOORSNUMBER);
                int idOwner = rs.getInt("idowner");
                int idApartments = rs.getInt(textIDAPARTMENTS);


                // Display the information using JOptionPane
                StringBuilder message = new StringBuilder();
                message.append(textIDOWNER+": ").append(idOwner).append("\t");
                message.append(textIDHOUSE+": ").append(idHouse).append("\t");
                message.append(textIDFLOORSNUMBER+": ").append(idFloorsNumber).append("\t");
                message.append(textIDAPARTMENTS+": ").append(idApartments).append("\t");
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



            return true;
        } catch (Exception e) {e.printStackTrace();
        }finally {
            if (pstmt != null) {
                try {
                    pstmt.close();} catch (SQLException e) {e.printStackTrace();}}}return  false ;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
}
