package com.example.albaleh;

import java.sql.*;
import java.time.LocalDate;

public class Tenants {




    private String Password, userName;
    private boolean status;

    public Tenants() {
        this.Password = "";
        this.userName = "";
        this.status = false;
    }


    public  boolean studentNeighbors(){

        try {


            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");


            // Create a prepared statement with a parameterized query
            String sqlQuery = "select  DISTINCT idowner , idhouse , idfloorsnumber , idapartments FROM resident ";
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            StringBuilder message = new StringBuilder();

            while (rs.next()) {

                int idHouse = rs.getInt("IDHOUSE");
                int idFloorsNumber = rs.getInt("IDFLOORSNUMBER");
                int idOwner = rs.getInt("IDOWNER");
                int idApartments = rs.getInt("IDAPARTMENTS");


                message.append("IDOWNER: ").append(idOwner).append("\t");
                message.append("IDHOUSE: ").append(idHouse).append("\t");
                message.append("IDFLOORSNUMBER: ").append(idFloorsNumber).append("\t");
                message.append("IDAPARTMENTS: ").append(idApartments).append("\t");
                message.append("\nTENANTS  :   \n");


                String sqlTenantQuery = "SELECT ID, universitiesspecialization, address, age, name, phone " +
                        "FROM RESIDENT " +
                        "JOIN TENANTS ON RESIDENT.IDTENANTS = TENANTS.ID " +
                        "WHERE RESIDENT.IDHOUSE = ? " +
                        "AND RESIDENT.IDFLOORSNUMBER = ? " +
                        "AND RESIDENT.IDOWNER = ? " +
                        "AND RESIDENT.IDAPARTMENTS = ? " +
                        "AND TENANTS.FISSTUDENT = 1";

                PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, idHouse);
                pstmtTenant.setInt(2, idFloorsNumber);
                pstmtTenant.setInt(3, idOwner);
                pstmtTenant.setInt(4, idApartments);


                ResultSet srs = pstmtTenant.executeQuery();;
int i = 1 ;

                while (srs.next()){

                    int ID = srs.getInt("ID");
                    String ADDRESS = srs.getString("ADDRESS");
                    String PHONE = srs.getString("PHONE");
                    String NAME = srs.getString("NAME");
                    String universitiesspecialization = srs.getString("universitiesspecialization");
                    String AGE = srs.getString("AGE");




                    message.append(i +"-").append("ID: ").append(ID).append("\t");
                    message.append("NAME: ").append(NAME).append("\t\t");
                    message.append("ADDRESS: ").append(ADDRESS).append("\t    ");
                    message.append("PHONE: ").append(PHONE).append("\t");
                    message.append("universitiesspecialization: ").append(universitiesspecialization).append("\t    ");
                    message.append("AGE: ").append(AGE).append("\n\n");


i++;

                }

                message.append("----------------------------------------------------------------------------------").append("\n");

            }

            System.out.println(message.toString());
            return true;

        } catch (Exception e){
e.printStackTrace();


        }

        return false;
    }

    public boolean changeSERVICEAVAILABLEToFull(int idowners, int idhouse,  int idfloorsnumber,  int idapartments) throws SQLException {

        if ((this.CheckTheFullnessOfTheApartment(idowners, idhouse, idfloorsnumber, idapartments))) {
            try {


                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

                String sqlTenantQuery = "UPDATE APARTMENTS SET SERVICEAVAILABLE = '0' WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ?  ";

                PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, idowners);
                pstmtTenant.setInt(2, idhouse);
                pstmtTenant.setInt(3, idfloorsnumber);
                pstmtTenant.setInt(4, idapartments);

                int x = pstmtTenant.executeUpdate();
                return x > 0;

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return false;

    }
    public boolean ShowSomeInfoAfterBooking() throws SQLException {



        if (this.DoIHaveAnApartmentReservation()){


            try {
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");


                String sqlTenantQuery = "SELECT  id, address, phone,  name, universitiesspecialization, age from TENANTS WHERE ID = ?    ";
                PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, Integer.parseInt(this.userName));

                ResultSet rsTenant = pstmtTenant.executeQuery();


                if (rsTenant.next()) {

                    int id = rsTenant.getInt("id");
                    String address = rsTenant.getString("ADDRESS");
                    String phone = rsTenant.getString("phone");
                    String name = rsTenant.getString("name");
                    String universitiesspecialization = rsTenant.getString("universitiesspecialization");
                    String age = rsTenant.getString("age");


                    StringBuilder message = new StringBuilder();
                    message.append("id: ").append(id).append("\n");
                    message.append("ADDRESS : ").append(address).append("\n");
                    message.append("phone: ").append(phone).append("\n");
                    message.append("name: ").append(name).append("\n");
                    message.append("universitiesspecialization: ").append(universitiesspecialization).append("\n");
                    message.append("age: ").append(age).append("\n");





                    message.append("\n").append("--------------------------------------------------").append("\n");
                    System.out.println("the Personal data : \n"+message.toString());


                }

int id = 0 ;
                sqlTenantQuery = "SELECT  IDOWNER ,PAYMENTDUEDATE from RESIDENT WHERE IDTENANTS = ?    ";
                pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, Integer.parseInt(this.userName));

                rsTenant = pstmtTenant.executeQuery();
                if (rsTenant.next()) {





                     id = rsTenant.getInt("IDOWNER");
                    String PAYMENTDUEDATE = rsTenant.getString("PAYMENTDUEDATE");



                    StringBuilder message = new StringBuilder();
                    message.append("IDOWNER: ").append(id).append("\n");
                    message.append("PAYMENTDUEDATE : ").append(PAYMENTDUEDATE).append("\n");
                    message.append("\n").append("--------------------------------------------------").append("\n");
                    System.out.println("the PAYMENT DUE DATE data : \n"+message.toString());

                }





                 sqlTenantQuery = "SELECT ID ,  address, phone, age,  name from OWNERS where ID = ?  ";
                 pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, id);

                 rsTenant = pstmtTenant.executeQuery();


                if (rsTenant.next()) {

                     id = rsTenant.getInt("ID");
                    String address = rsTenant.getString("address");
                    String phone = rsTenant.getString("phone");
                    String name = rsTenant.getString("name");;
                    String age = rsTenant.getString("age");


                    StringBuilder message = new StringBuilder();
                    message.append("id: ").append(id).append("\n");
                    message.append("ADDRESS : ").append(address).append("\n");
                    message.append("phone: ").append(phone).append("\n");
                    message.append("name: ").append(name).append("\n");
                    message.append("age: ").append(age).append("\n");





                    message.append("\n").append("--------------------------------------------------").append("\n");
                    System.out.println("the Owner data : \n"+message.toString());

                      return true;
                }





            } catch (Exception e ){



            }




        }else { System.out.println(" you are  dont book"); return  false ; }


        return false;
    }


        public boolean DeleteBook() throws SQLException {

            try {



            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");


                int idowners =0 ,  idhouse =0 ,  idfloorsnumber =0,  idapartments =0 ;


            if ( this.DoIHaveAnApartmentReservation()) {



                String info = "SELECT IDOWNER, IDHOUSE, IDFLOORSNUMBER , IDAPARTMENTS FROM RESIDENT WHERE IDTENANTS = ?";

                PreparedStatement pstmt = con.prepareStatement(info);



                pstmt.setInt(1, Integer.parseInt(this.userName));
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                     idowners = rs.getInt("IDOWNER");
                     idhouse = rs.getInt("IDHOUSE");
                     idfloorsnumber = rs.getInt("IDFLOORSNUMBER");
                     idapartments = rs.getInt("IDAPARTMENTS");

                }






                String sqlInsertQuery = "DELETE FROM RESIDENT WHERE IDHOUSE = ? AND IDFLOORSNUMBER = ? AND IDOWNER = ? AND IDAPARTMENTS = ? AND IDTENANTS = ?";


                PreparedStatement ss = con.prepareStatement(sqlInsertQuery);

                    ss.setInt(1, idowners);
                    ss.setInt(2, idhouse);
                    ss.setInt(3, idfloorsnumber);
                    ss.setInt(4, idapartments);
                    ss.setInt(5, Integer.parseInt(this.userName));


                    int rowsAffected = ss.executeUpdate();
                    if (rowsAffected > 0) {

                        if ((this.CheckTheFullnessOfTheApartment(idowners, idhouse, idfloorsnumber, idapartments))) {

                            String sqlTenantQuery = "UPDATE APARTMENTS SET SERVICEAVAILABLE = '1' WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ?  ";

                            PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
                            pstmtTenant.setInt(1, idowners);
                            pstmtTenant.setInt(2, idhouse);
                            pstmtTenant.setInt(3, idfloorsnumber);
                            pstmtTenant.setInt(4, idapartments);

                            pstmtTenant.executeUpdate();

                        }




                        String sqlTenantQuery = "UPDATE APARTMENTS SET COUNT = COUNT - 1  WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ?";

                        PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
                        pstmtTenant.setInt(1, idowners);
                        pstmtTenant.setInt(2, idhouse);
                        pstmtTenant.setInt(3, idfloorsnumber);
                        pstmtTenant.setInt(4, idapartments);

                        pstmtTenant.executeUpdate();

                        sqlTenantQuery = "UPDATE TENANTS SET FAFTERBOOKING = 0  WHERE ID = ?";
                        pstmtTenant = con.prepareStatement(sqlTenantQuery);
                        pstmtTenant.setInt(1, Integer.parseInt(this.userName));
                        pstmtTenant.executeUpdate();
                        System.out.println("Data Delete .");

                        ss.close();
                        con.close();
                        return true;
                    }

            }

            } catch (SQLException e) {


                return false;

            }

        return  false;
        }

    public boolean ShowFURNITUREAdvertise(){



        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            // Create a prepared statement with a parameterized query
            String sqlQuery = "select  * from furniture";
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);


            ResultSet rs = pstmt.executeQuery();



            while (rs.next()) {

                int idtenants = rs.getInt("idtenants");
                int IDFURNITURE = rs.getInt("IDFURNITURE");
                int price = rs.getInt("price");

                String description = rs.getString("description");




                StringBuilder message = new StringBuilder();
                message.append("idtenants: ").append(idtenants).append("\n");
                message.append("IDFURNITURE: ").append(IDFURNITURE).append("\n");
                message.append("price: ").append(price).append("\n");
                message.append("description: ").append(description).append("\n");
                message.append("\n").append("--------------------------------------------------").append("\n");

                System.out.println(message.toString());
            }

            rs.close();
            pstmt.close();
            con.close();

            return true;

        } catch (Exception e) {




        }

        return false;
    }

    public boolean DoIHaveAnApartmentReservation(){


        try {


            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            // Create a prepared statement with a parameterized query
            String sqlQuery = "select  fafterbooking from tenants where id = ?  and fafterbooking = 1  ";
            PreparedStatement pstmtTenant = con.prepareStatement(sqlQuery);
            pstmtTenant.setInt(1, Integer.parseInt(this.userName));



            // Execute the query
            ResultSet rs = pstmtTenant.executeQuery();

            if (rs.next()) {
                return  true ;

            }
        } catch (Exception e){
            System.out.println("Error for ID");
            return false ;
        }

        return false;
    }



    public boolean DeleteFURNITURE(int IDFURNITURE){

        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            String sqlTenantQuery =  "DELETE FROM FURNITURE WHERE IDFURNITURE = ? and IDTENANTS =?  ";
            PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
            pstmtTenant.setInt(1, IDFURNITURE);
            pstmtTenant.setInt(2, Integer.parseInt(this.userName));
            int rsTenant = pstmtTenant.executeUpdate();
            if (rsTenant > 0) {

               System.out.println("successfully Delete FURNITURE");


                con.close();

               return true;
            } else {

                System.out.println("Check the value ID FURNITURE or the Tenants have this  FURNITURE ");

            }



        } catch (Exception e){

            System.out.println("Check the value ID FURNITURE or the Tenants have this  FURNITURE ");


        }

        return false;
    }
     public boolean InsertFURNITUREAdvertise ( int PRICE, String DESCRIPTION) throws SQLException {

        if(this.DoIHaveAnApartmentReservation()){
try {

    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

    String sqlQuery = "SELECT MAX(IDFURNITURE) + 1 AS nextID FROM furniture";
    PreparedStatement pstmtTenant = con.prepareStatement(sqlQuery);
    ResultSet rs = pstmtTenant.executeQuery();
    if (rs.next()) {
        int IDFURNITURE = rs.getInt("nextID");


        String sqlInsertQuery = "INSERT INTO FURNITURE (IDFURNITURE, PRICE, DESCRIPTION,IDTENANTS) VALUES (?, ?, ?,?) ";

        PreparedStatement pstmtInsert = con.prepareStatement(sqlInsertQuery);
        pstmtInsert.setInt(1, IDFURNITURE);
        pstmtInsert.setInt(2, PRICE);
        pstmtInsert.setString(3, DESCRIPTION);
        pstmtInsert.setInt(4, Integer.parseInt(this.userName));

        int rowsAffected = pstmtInsert.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Data inserted successfully.");

            pstmtInsert.close();
            con.close();
             return true;


        } else {


            System.out.println("Failed to insert data.");
            pstmtInsert.close();
            con.close();


            return false;

        }


        }

}catch (Exception e ){


 System.out.println("Check the values entered");
}} else {   System.out.println("There is no apartment reservation for this user");  }
        return  false;
     }

    public boolean CheckTheFullnessOfTheApartment(int idOwner, int idHouse, int idFloorsNumber, int idApartments) throws SQLException {

        try {


            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            // Create a prepared statement with a parameterized query
            String sqlQuery = "SELECT    count , limit   From APARTMENTS  WHERE  IDHOUSE = ? and IDFLOORSNUMBER = ? and IDOWNERS = ? and IDAPARTMENTS = ? ";
            PreparedStatement pstmtTenant = con.prepareStatement(sqlQuery);
            pstmtTenant.setInt(1, idHouse);
            pstmtTenant.setInt(2, idFloorsNumber);
            pstmtTenant.setInt(3, idOwner);
            pstmtTenant.setInt(4, idApartments);



            // Execute the query
            ResultSet rs = pstmtTenant.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                int limit = rs.getInt("limit");

                return limit <= count;

            }
        } catch (Exception e) {

            System.out.println("Check the values entered for Apartment ");
        }

        return false;
    }



    public boolean bookAccommodation(int idowners, int idhouse, int idfloorsnumber, int idapartments) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");



        if (!((this.CheckTheFullnessOfTheApartment(idowners, idhouse, idfloorsnumber, idapartments))  || this.DoIHaveAnApartmentReservation())) {

            try {
                LocalDate currentDate = LocalDate.now();

// Add 30 days to the current date
                LocalDate futureDate = currentDate.plusDays(30);

                String sqlInsertQuery = "INSERT INTO RESIDENT (IDHOUSE, IDFLOORSNUMBER, IDOWNER, IDAPARTMENTS, IDTENANTS,paymentduedate) VALUES (?, ?, ?, ?, ?,?) ";

                PreparedStatement pstmtInsert = con.prepareStatement(sqlInsertQuery);
                pstmtInsert.setInt(1, idhouse); // Set the ID parameter
                pstmtInsert.setInt(2, idfloorsnumber); // Set the name parameter
                pstmtInsert.setInt(3, idowners); // Set the age parameter
                pstmtInsert.setInt(4, idapartments); // Set the age parameter
                pstmtInsert.setInt(5, Integer.parseInt(this.userName)); // Set the age parameter
                pstmtInsert.setString(6 , (futureDate.toString())); // Set the age parameter





                int rowsAffected = pstmtInsert.executeUpdate();
                if (rowsAffected > 0) {


                    String sqlTenantQuery = "UPDATE APARTMENTS SET COUNT = COUNT + 1  WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ?";

                    PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
                    pstmtTenant.setInt(1, idowners);
                    pstmtTenant.setInt(2, idhouse);
                    pstmtTenant.setInt(3, idfloorsnumber);
                    pstmtTenant.setInt(4, idapartments);

                    pstmtTenant.executeUpdate();

                     sqlTenantQuery = "UPDATE TENANTS SET FAFTERBOOKING = 1  WHERE ID = ?";
                     pstmtTenant = con.prepareStatement(sqlTenantQuery);
                    pstmtTenant.setInt(1, Integer.parseInt(this.userName));
                    pstmtTenant.executeUpdate();
                    System.out.println("Data inserted successfully.");

                    pstmtInsert.close();
                    con.close();
                    return true;
                } else {


                    System.out.println("Failed to insert data.");


                    pstmtInsert.close();
                    con.close();
                    return false;

                }

            } catch (SQLException e) {

                System.out.println("Check the values entered for Apartment ");


            }
        } else {
            System.out.println(" Apartment full or book another apartment in advance ");





               this.changeSERVICEAVAILABLEToFull(idowners, idhouse, idfloorsnumber, idapartments);



            return false;
        }
        return false;
    }
    public boolean ShowAvailableHouse() throws SQLException {
 int i = 0 ;

        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            // Create a prepared statement with a parameterized query
            String sqlQuery = "SELECT   IMAGE ,DESCRIPTION, idowners , idhouse , idfloorsnumber , idapartments , count , limit , MONTHLYRENT  From APARTMENTS  WHERE serviceavailable = 1 ";
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                  i=1;
                int idHouse = rs.getInt("IDHOUSE");
                int idFloorsNumber = rs.getInt("IDFLOORSNUMBER");
                int idOwner = rs.getInt("idowners");
                int idApartments = rs.getInt("IDAPARTMENTS");
                int count = rs.getInt("count");
                int limit = rs.getInt("limit");
                int MONTHYRENT = rs.getInt("MONTHLYRENT");
                String DESCRIPTION = rs.getString("DESCRIPTION");
                String IMAGE = rs.getString("IMAGE");


                StringBuilder message = new StringBuilder();
                message.append("IDHOUSE: ").append(idHouse).append("\n");
                message.append("IDFLOORSNUMBER: ").append(idFloorsNumber).append("\n");
                message.append("IDOWNER: ").append(idOwner).append("\n");
                message.append("IDAPARTMENTS: ").append(idApartments).append("\n");
                message.append("count tenants in APARTMENTS: ").append(count).append("\n");
                message.append("limit  tenants in APARTMENTS:  ").append(limit).append("\n");
                message.append("MONTHLYRENT : ").append(MONTHYRENT).append("\n");
                message.append("DESCRIPTION : ").append(DESCRIPTION).append("\n");
                message.append("IMAGE : ").append(IMAGE).append("\n");

                String sqlTenantQuery = "SELECT ADDRESS FROM HOUSING WHERE IDHOUSE = ? and  IDOWNERS = ?   ";
                PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, idHouse);
                pstmtTenant.setInt(2, idOwner);
                ResultSet rsTenant = pstmtTenant.executeQuery();
                if (rsTenant.next()) {
                    String ADDRESS = rsTenant.getString("ADDRESS");
                    message.append("ADDRESS : ").append(ADDRESS).append("\n");
                }


                message.append("\n").append("--------------------------------------------------").append("\n");

                System.out.println(message.toString());



            }
                rs.close(); // Close the result set
                pstmt.close(); // Close the prepared statement
                con.close(); // Close the connection





        } catch (Exception e) {

            return false;
        }




        return i>0;

    }


    public boolean login(String idUser , String passwordUser ){

        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            // Create a prepared statement with a parameterized query
            String sqlQuery = "SELECT ID, PASSWORD FROM TENANTS WHERE ID = ? AND PASSWORD = ?";
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);
            pstmt.setString(1, idUser); // Set the value for the first parameter (ID)
            pstmt.setString(2, passwordUser); // Set the value for the second parameter (PASSWORD)

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                rs.close(); // Close the result set
                pstmt.close(); // Close the prepared statement
                con.close(); // Close the connection
                return  true;
            } else {

                rs.close(); // Close the result set
                pstmt.close(); // Close the prepared statement
                con.close(); // Close the connection
                return  false;
            }



        } catch (Exception e) {

        }




        return false;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
