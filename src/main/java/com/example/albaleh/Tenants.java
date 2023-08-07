package com.example.albaleh;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Logger;

public class Tenants {
    static Logger log = Logger.getLogger(Tenants.class.getName());

    private  static   Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");
        } catch (SQLException e) {e.printStackTrace();}
    }

  private static final String  IDOWNER = "IDOWNER";
    private  static final  String textADDRESS = "ADDRESS";
    private  static final    String dash = "--------------------------------------------------";
    private  static final  String textIDHOUSE="IDHOUSE";
    private static final String textIDFLOORSNUMBER="IDFLOORSNUMBER";

    private static final String textIDOWNER="IDOWNERS";
    private static final  String textIDAPARTMENTS="IDAPARTMENTS";



    private String password;
    private String userName;
    private boolean status;

    public Tenants() {
        this.password = "";
        this.userName = "";
        this.status = false;
    }


    public  boolean studentNeighbors() throws SQLException {
        PreparedStatement pstmt= null;
        PreparedStatement pstmtTenant =null ;
        try {




            // Create a prepared statement with a parameterized query
            String sqlQuery = "select  DISTINCT idowner , idhouse , idfloorsnumber , idapartments FROM resident ";
             pstmt = con.prepareStatement(sqlQuery);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            StringBuilder message = new StringBuilder();

            while (rs.next()) {

                int idHouse = rs.getInt(textIDHOUSE);
                int idFloorsNumber = rs.getInt(textIDFLOORSNUMBER);
                int idOwner = rs.getInt(IDOWNER);
                int idApartments = rs.getInt(textIDAPARTMENTS);


                message.append(textIDOWNER+": ").append(idOwner).append("\t");
                message.append(textIDHOUSE+": ").append(idHouse).append("\t");
                message.append(textIDFLOORSNUMBER+": ").append(idFloorsNumber).append("\t");
                message.append(textIDAPARTMENTS+": ").append(idApartments).append("\t\n");
                message.append("\nTENANTS  :   \n");


                String sqlTenantQuery = "SELECT ID, universitiesspecialization, address, age, name, phone " +
                        "FROM RESIDENT " +
                        "JOIN TENANTS ON RESIDENT.IDTENANTS = TENANTS.ID " +
                        "WHERE RESIDENT.IDHOUSE = ? " +
                        "AND RESIDENT.IDFLOORSNUMBER = ? " +
                        "AND RESIDENT.IDOWNER = ? " +
                        "AND RESIDENT.IDAPARTMENTS = ? " +
                        "AND TENANTS.FISSTUDENT = 1";

                 pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, idHouse);
                pstmtTenant.setInt(2, idFloorsNumber);
                pstmtTenant.setInt(3, idOwner);
                pstmtTenant.setInt(4, idApartments);


                ResultSet srs = pstmtTenant.executeQuery();
int i = 1 ;

                while (srs.next()){

                    int ID = srs.getInt("ID");
                    String ADDRESS = srs.getString(textADDRESS);
                    String PHONE = srs.getString("PHONE");
                    String NAME = srs.getString("NAME");
                    String universitiesspecialization = srs.getString("universitiesspecialization");
                    String AGE = srs.getString("AGE");




                    message.append(i +"-").append("ID: ").append(ID).append("\t");
                    message.append("NAME: ").append(NAME).append("\t\t");
                    message.append("ADDRESS: ").append(ADDRESS).append("\t\t");
                    message.append("PHONE: ").append(PHONE).append("\t");
                    message.append("universitiesspecialization: ").append(universitiesspecialization).append("\t    ");
                    message.append("AGE: ").append(AGE).append("\n\n");


i++;

                }

                message.append(dash).append("\n");

            }

            log.info(()->message+"\n");

            return true;

        } catch (Exception e){e.printStackTrace();} finally {
            if (pstmt != null){
                pstmt.close();
            }
            if (pstmtTenant != null){
                pstmtTenant.close();
            }
        }
        return false;}

    public boolean changeSERVICEAVAILABLEToFull(int idowners, int idhouse,  int idfloorsnumber,  int idapartments) throws SQLException {
        PreparedStatement pstmtTenant =null;
        if ((this.CheckTheFullnessOfTheApartment(idowners, idhouse, idfloorsnumber, idapartments))) {
            try {



                String sqlTenantQuery = "UPDATE APARTMENTS SET SERVICEAVAILABLE = '0' WHERE idowners = ? and idhouse = ? and idfloorsnumber = ? and idapartments = ?  ";

                 pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, idowners);
                pstmtTenant.setInt(2, idhouse);
                pstmtTenant.setInt(3, idfloorsnumber);
                pstmtTenant.setInt(4, idapartments);

                int x = pstmtTenant.executeUpdate();
                return x > 0;

            } catch (Exception e) {e.printStackTrace();}
            finally {
                if (pstmtTenant != null){
                    pstmtTenant.close();
                }
            }
        }
        return false;

    }
    public boolean ShowSomeInfoAfterBooking() throws SQLException {

        PreparedStatement pstmtTenant =null;

        if (this.DoIHaveAnApartmentReservation()){


            try {


                String sqlTenantQuery = "SELECT  id, address, phone,  name, universitiesspecialization, age from TENANTS WHERE ID = ?    ";
                 pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, Integer.parseInt(this.userName));

                ResultSet rsTenant = pstmtTenant.executeQuery();


                if (rsTenant.next()) {

                    int id = rsTenant.getInt("id");
                    String address = rsTenant.getString(textADDRESS);
                    String phone = rsTenant.getString("phone");
                    String name = rsTenant.getString("name");
                    String universitiesspecialization = rsTenant.getString("universitiesspecialization");
                    String age = rsTenant.getString("age");


                    StringBuilder message = new StringBuilder();
                    message.append("\nid: ").append(id).append("\n");
                    message.append(textADDRESS+": ").append(address).append("\n");
                    message.append("phone: ").append(phone).append("\n");
                    message.append("name: ").append(name).append("\n");
                    message.append("universitiesspecialization: ").append(universitiesspecialization).append("\n");
                    message.append("age: ").append(age).append("\n");





                    message.append("\n").append(dash).append("\n");

                    log.info("the Personal data : \n"+  message +"\n");


                }

int id = 0 ;
                sqlTenantQuery = "SELECT  IDOWNER ,PAYMENTDUEDATE from RESIDENT WHERE IDTENANTS = ?    ";
                pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, Integer.parseInt(this.userName));

                rsTenant = pstmtTenant.executeQuery();
                if (rsTenant.next()) {





                     id = rsTenant.getInt(IDOWNER);
                    String PAYMENTDUEDATE = rsTenant.getString("PAYMENTDUEDATE");



                    StringBuilder message = new StringBuilder();
                    message.append(IDOWNER+": ").append(id).append("\n");
                    message.append("PAYMENTDUEDATE : ").append(PAYMENTDUEDATE).append("\n");
                    message.append("\n").append(dash).append("\n");
                    log.info("the PAYMENT DUE DATE data : \n"+message+"\n");

                }





                 sqlTenantQuery = "SELECT ID ,  address, phone, age,  name from OWNERS where ID = ?  ";
                 pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, id);

                 rsTenant = pstmtTenant.executeQuery();


                if (rsTenant.next()) {

                     id = rsTenant.getInt("ID");
                    String address = rsTenant.getString("address");
                    String phone = rsTenant.getString("phone");
                    String name = rsTenant.getString("name");
                    String age = rsTenant.getString("age");


                    StringBuilder message = new StringBuilder();
                    message.append("id: ").append(id).append("\n");
                    message.append("ADDRESS : ").append(address).append("\n");
                    message.append("phone: ").append(phone).append("\n");
                    message.append("name: ").append(name).append("\n");
                    message.append("age: ").append(age).append("\n");





                    message.append("\n").append(dash).append("\n");
                    log.info("the Owner data : \n"+  message);

                      return true;
                }





            } catch (Exception e ){e.printStackTrace();}finally {
                if (pstmtTenant != null){
                    pstmtTenant.close();
                }


            }




        }else { log.info(" you are  dont book\n"); return  false ; }return false;}


    public boolean DeleteBook() throws SQLException {
        PreparedStatement pstmt =null;
        PreparedStatement deleteStmt =null;
        try {
            int idowners = 0;
            int idhouse = 0;
            int idfloorsnumber = 0;
            int idapartments = 0;

            if ( this.DoIHaveAnApartmentReservation()) {
                String info = "SELECT IDOWNER, IDHOUSE, IDFLOORSNUMBER, IDAPARTMENTS FROM RESIDENT WHERE IDTENANTS = ?";
                 pstmt = con.prepareStatement(info);
                pstmt.setInt(1, Integer.parseInt(this.userName));
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    idowners = rs.getInt("IDOWNER");
                    idhouse = rs.getInt(textIDHOUSE);
                    idfloorsnumber = rs.getInt(textIDFLOORSNUMBER);
                    idapartments = rs.getInt(textIDAPARTMENTS);
                } else {
                    // No reservation found for the user
                    return false;
                }

                String sqlDeleteQuery = "DELETE FROM RESIDENT WHERE IDHOUSE = ? AND IDFLOORSNUMBER = ? AND IDOWNER = ? AND IDAPARTMENTS = ? AND IDTENANTS = ?";
                 deleteStmt = con.prepareStatement(sqlDeleteQuery);
                deleteStmt.setInt(1, idhouse);
                deleteStmt.setInt(2, idfloorsnumber);
                deleteStmt.setInt(3, idowners);
                deleteStmt.setInt(4, idapartments);
                deleteStmt.setInt(5, Integer.parseInt(this.userName));

                int rowsAffected = deleteStmt.executeUpdate();
                if (rowsAffected > 0) {
                    if (this.CheckTheFullnessOfTheApartment(idowners, idhouse, idfloorsnumber, idapartments)) {
                        String sqlUpdateQuery = "UPDATE APARTMENTS SET SERVICEAVAILABLE = '1' WHERE idowners = ? AND idhouse = ? AND idfloorsnumber = ? AND idapartments = ?";
                        PreparedStatement updateStmt = con.prepareStatement(sqlUpdateQuery);
                        updateStmt.setInt(1, idowners);
                        updateStmt.setInt(2, idhouse);
                        updateStmt.setInt(3, idfloorsnumber);
                        updateStmt.setInt(4, idapartments);
                        updateStmt.executeUpdate();
                    }

                    String sqlUpdateCountQuery = "UPDATE APARTMENTS SET COUNT = COUNT - 1 WHERE idowners = ? AND idhouse = ? AND idfloorsnumber = ? AND idapartments = ?";
                    PreparedStatement updateCountStmt = con.prepareStatement(sqlUpdateCountQuery);
                    updateCountStmt.setInt(1, idowners);
                    updateCountStmt.setInt(2, idhouse);
                    updateCountStmt.setInt(3, idfloorsnumber);
                    updateCountStmt.setInt(4, idapartments);
                    updateCountStmt.executeUpdate();

                    String sqlUpdateTenantQuery = "UPDATE TENANTS SET FAFTERBOOKING = 0 WHERE ID = ?";
                    PreparedStatement updateTenantStmt = con.prepareStatement(sqlUpdateTenantQuery);
                    updateTenantStmt.setInt(1, Integer.parseInt(this.userName));
                    updateTenantStmt.executeUpdate();

                    log.info("Data Deleted.\n");
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(pstmt !=null){
                pstmt.close();
            }
            if(deleteStmt !=null){
                deleteStmt.close();
            }
        }
        return false;
    }


    public boolean ShowFURNITUREAdvertise(){



        try {

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
                message.append("idtenants: ").append(idtenants).append("\t       ");
                message.append("IDFURNITURE: ").append(IDFURNITURE).append("\n");
                message.append("price: ").append(price).append("\n");
                message.append("description: ").append(description).append("\n");
                message.append("\n").append(dash).append("\n");

                log.info(()->message+"\n");
            }

            rs.close();
            pstmt.close();

            return true;

        } catch (Exception e) { e.printStackTrace();}return false;}

    public boolean DoIHaveAnApartmentReservation(){


        try {



            // Create a prepared statement with a parameterized query
            String sqlQuery = "select  fafterbooking from tenants where id = ?  and fafterbooking = 1  ";
            PreparedStatement pstmtTenant = con.prepareStatement(sqlQuery);
            pstmtTenant.setInt(1, Integer.parseInt(this.userName));



            // Execute the query
            ResultSet rs = pstmtTenant.executeQuery();

            if (rs.next()) {
                return  true ;

            }
        } catch (Exception e){ log.info("Error for ID\n"); return false ; }

        return false;
    }



    public boolean DeleteFURNITURE(int IDFURNITURE){

        try {

            String sqlTenantQuery =  "DELETE FROM FURNITURE WHERE IDFURNITURE = ? and IDTENANTS =?  ";
            PreparedStatement pstmtTenant = con.prepareStatement(sqlTenantQuery);
            pstmtTenant.setInt(1, IDFURNITURE);
            pstmtTenant.setInt(2, Integer.parseInt(this.userName));
            int rsTenant = pstmtTenant.executeUpdate();
            if (rsTenant > 0) {

                log.info("successfully Delete FURNITURE\n");



               return true;
            } else {

                log.info("Check the value ID FURNITURE or the Tenants have this  FURNITURE \n");

            }



        } catch (Exception e){log.info("Check the value ID FURNITURE or the Tenants have this  FURNITURE \n");


        }

        return false;
    }
     public boolean InsertFURNITUREAdvertise ( int PRICE, String DESCRIPTION) throws SQLException {

        if(this.DoIHaveAnApartmentReservation()){
try {


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
            log.info("Data inserted successfully.\n");


             return true;


        } else {log.info("Failed to insert data.\n"); return false;}


        }

}catch (Exception e ){log.info("Check the values entered\n"); }} else {   log.info("There is no apartment reservation for this user\n");  }return  false;}

    public boolean CheckTheFullnessOfTheApartment(int idOwner, int idHouse, int idFloorsNumber, int idApartments) throws SQLException {

        try {



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
        } catch (Exception e) {log.info("Check the values entered for Apartment \n");}

        return false;
    }



    public boolean bookAccommodation(int idowners, int idhouse, int idfloorsnumber, int idapartments) throws SQLException {




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
                    log.info("Data inserted successfully.\n");



                    return true;
                } else {log.info("Failed to insert data.\n"); return false;

                }

            } catch (SQLException e) {log.info("Check the values entered for Apartment \n");}}
        else {log.info(" Apartment full or book another apartment in advance \n");





               this.changeSERVICEAVAILABLEToFull(idowners, idhouse, idfloorsnumber, idapartments);



            return false;
        }
        return false;
    }
    public boolean ShowAvailableHouse() throws SQLException {
 int i = 0 ;
        PreparedStatement pstmtTenant = null ;
        PreparedStatement pstmt =null;
        try {

            // Create a prepared statement with a parameterized query
            String sqlQuery = "SELECT   IMAGE ,DESCRIPTION, idowners , idhouse , idfloorsnumber , idapartments , count , limit , MONTHLYRENT  From APARTMENTS  WHERE serviceavailable = 1 ";
             pstmt = con.prepareStatement(sqlQuery);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                  i=1;
                int idHouse = rs.getInt(textIDHOUSE);
                int idFloorsNumber = rs.getInt(textIDFLOORSNUMBER);
                int idOwner = rs.getInt(textIDOWNER);
                int idApartments = rs.getInt(textIDAPARTMENTS);
                int count = rs.getInt("count");
                int limit = rs.getInt("limit");
                int MONTHYRENT = rs.getInt("MONTHLYRENT");
                String DESCRIPTION = rs.getString("DESCRIPTION");
                String IMAGE = rs.getString("IMAGE");


                StringBuilder message = new StringBuilder();
                message.append(textIDHOUSE+": ").append(idHouse).append("\t  ");
                message.append(textIDFLOORSNUMBER+" ").append(idFloorsNumber).append("\t    ");
                message.append(textIDOWNER+": ").append(idOwner).append("\t       ");
                message.append(textIDAPARTMENTS+ ": ").append(idApartments).append("\n");
                message.append("count tenants in APARTMENTS: ").append(count).append("\n");
                message.append("limit  tenants in APARTMENTS:  ").append(limit).append("\n");
                message.append("MONTHLYRENT : ").append(MONTHYRENT).append("\n");
                message.append("DESCRIPTION : ").append(DESCRIPTION).append("\n");
                message.append("IMAGE : ").append(IMAGE).append("\n");

                String sqlTenantQuery = "SELECT ADDRESS FROM HOUSING WHERE IDHOUSE = ? and  IDOWNERS = ?   ";
                 pstmtTenant = con.prepareStatement(sqlTenantQuery);
                pstmtTenant.setInt(1, idHouse);
                pstmtTenant.setInt(2, idOwner);
                ResultSet rsTenant = pstmtTenant.executeQuery();
                if (rsTenant.next()) {
                    String ADDRESS = rsTenant.getString(textADDRESS);
                    message.append(textADDRESS+" : ").append(ADDRESS).append("\n");
                }


                message.append("\n").append(dash).append("\n");

                log.info(()->message+"\n");



            }






        } catch (Exception e) {return false;}finally {
            if ( pstmt != null){
                pstmt.close();
            }
            if ( pstmtTenant != null){
pstmtTenant.close();
            }
        }




        return i>0;

    }


    public boolean login(String idUser , String passwordUser ) throws SQLException {
        PreparedStatement pstmt =null;
        try {

            // Create a prepared statement with a parameterized query
            String sqlQuery = "SELECT ID, PASSWORD FROM TENANTS WHERE ID = ? AND PASSWORD = ?";
             pstmt = con.prepareStatement(sqlQuery);
            pstmt.setString(1, idUser); // Set the value for the first parameter (ID)
            pstmt.setString(2, passwordUser); // Set the value for the second parameter (PASSWORD)

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                rs.close(); // Close the result set
                pstmt.close(); // Close the prepared statement

                return  true;
            } else {



                return  false;
            }



        } catch (Exception e) {e.printStackTrace();}finally {
            if (pstmt != null){

                pstmt.close(); // Close the prepared statement
            }
        }
        return false;}


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
