package com.example.albaleh;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;





public class Owners {
String sp ="Owner menu:-"+
        "\n1-announcing private residences"
        + "\n2-Dashboard owner control panel"
        + "\n3-An advertisement for an apartment\n4-Logout\n"
        + "Choose the Number that you want :  \n";
    static Logger log = Logger.getLogger(Owners.class.getName());
    private  static   Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Owners(int id) {
        this.id = id;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public Owners(int id, String name, String password, String userName, boolean status) {
        this.id = id;
        Name = name;
        Password = password;
        this.userName = userName;
        this.status = status;
    }

    private int id;
    private String Name;
    private String Password ;
    private String userName ;
    private boolean status;
    private int house;
    private int floor;
    private int apartment;

    Scanner scanner = new Scanner(System.in);

    public int getHouse() {
        return house;
    }

    public int getFloor() {
        return floor;
    }

    public int getApartment() {
        return apartment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
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


    public Owners() {
        this.status = false;
    }


    public boolean login(String idUser , String passwordUser ){

        try {

            // Create a prepared statement with a parameterized query
            String sqlQuery = "SELECT ID, PASSWORD FROM OWNERS WHERE ID = ? AND PASSWORD = ?";
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);
            pstmt.setString(1, idUser); // Set the value for the first parameter (ID)
            pstmt.setString(2, passwordUser); // Set the value for the second parameter (PASSWORD)

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                rs.close();
                pstmt.close();

                return  true;
            } else {

                rs.close();
                pstmt.close();

                return  false;
            }



        } catch (Exception e) {

            e.printStackTrace();


        }

        return false;
    }

    public Boolean menu(){

        log.info(sp);

        return true;
    }
    public boolean addAdv(int house , int floor , int apartment,String  DESCRIPTION ) throws SQLException {




        try {

            String sqlQuery = "SELECT MAX(IDADV) + 1 AS nextID FROM ADVERTISEMENT";
            PreparedStatement   pstmtTenant = con.prepareStatement(sqlQuery);
            ResultSet rs = pstmtTenant.executeQuery();
int IDAdv = 0 ;
if (rs.next()){

    IDAdv =rs.getInt("nextID");}


            String sqlInsertQuery = "INSERT INTO ADVERTISEMENT (IDOWNERS, IDHOUSE, IDFLOORSNUMBER,IDAPARTMENTS,IDADV,ISPROCESS,ADVDESCRIPTION,ADVSTATES) VALUES (?,?,?,?,?,?,?,?) ";

                PreparedStatement pstmtInsert = con.prepareStatement(sqlInsertQuery);
                pstmtInsert.setInt(1, this.id);
                pstmtInsert.setInt(2,house );
                pstmtInsert.setInt(3, floor);
                pstmtInsert.setInt(4, apartment);
                pstmtInsert.setInt(5, IDAdv);
                pstmtInsert.setInt(6, 1);
                pstmtInsert.setString(7, DESCRIPTION);
            pstmtInsert.setInt(8, 0);


                int rowsAffected = pstmtInsert.executeUpdate();
                if (rowsAffected > 0) {
                    return true;


                } else {


                   log.info("Failed to insert data.\n");


                    return false;

                }



            }catch (Exception e){
            e.printStackTrace();
            log.info("Error check your Enter or this adv is exist \n");
            return false;

        }

                   }
    public Boolean menuChoice(int chooseOption){

        if (chooseOption>4||chooseOption<1){
            log.info("sorry your chose wasn't in the menu please try again\n");
            return false;
        }


        if (chooseOption ==1){  log.info("Please enter the house number: \n");}
        else if (chooseOption ==2){   log.info("Choose a specific house number:-\n");}
        return true;
    }



    public Boolean witchhouse(int houseid) throws SQLException {

        Statement statement = con.createStatement();
        String sql = "select IDhouse from Housing  where IDowners='"+this.id+"' and IDhouse='"+houseid+"'";
        ResultSet rs =statement.executeQuery(sql);

        if (rs.next()){


            this.house=houseid;
            log.info("Please enter the floor number: \n");
            return true;
        }

        log.info("Sorry you don't have a house with this number please try again \n");
        return false;
    }

    public Boolean witchFloor(int floorid) throws SQLException {


        Statement statement = con.createStatement();
        String sql = "select IDfloorsnumber from Floors  where IDowners='"+this.id+"' and IDhouse='"+house+"' and IDfloorsnumber='"+floorid+"'";
        ResultSet rs =statement.executeQuery(sql);
        if (rs.next()){
            this.floor=floorid;
            return true;
        }
        log.info("Sorry you don't have floor with this number please try again \n");
        return false;
    }


    public Boolean announcingPrivateResidencesMenu(){
        log.info("What do you want to do:-\n" +
                "1- Add Photo\n" +
                "2-  Residence location and information about the apartment\n" +
                "3- Available services\n" +
                "4- The monthly rent\n" +
                "5- contact information\n" +
                "6- return to main menu");



        return true;

    }

    public Boolean announcingPrivateResidences(int choose){


        if (choose > 6 || choose<1)
        {    log.info("try again\n");
            return false;

        }


        if (choose ==1){log.info("** Add photos**");}
        else if (choose ==2){log.info("** Residence location and information**");}
        else if (choose ==3){log.info("** Available services**");}
        else if (choose ==4){log.info("** The monthly rent (is it inclusive of electricity and water or not)\n**");}
        else if (choose ==5){log.info("**contact information**");}
        else if (choose ==6){log.info("****");}

        return true;

    }


    public void returnmaxid() throws SQLException {
        int maxid = 0;
        Statement statement = con.createStatement();
        String sql = "select max(IDApartments) from Apartments";
        ResultSet rs =statement.executeQuery(sql);
        if (rs.next()) {
            maxid=rs.getInt("max(IDApartments)");
            this.apartment=maxid;
            this.apartment++;
        }

    }


    public Boolean insertApartment() throws SQLException {

        returnmaxid();
        Statement statement = con.createStatement();
        String sql = "insert into Apartments values ('"+this.house+"','"+this.floor+"','"+this.apartment+"','"+this.id+"','no Description','nothing','4','0','0','0')";

        statement.executeUpdate(sql);


        return true;
    }


    public Boolean addPhoto(String link) throws SQLException {
        if(link == null || link.equals(""))
        {
            log.info("Please insert a path :)\n");
            return false;
        }

        Statement statement = con.createStatement();
        String sql = "UPDATE Apartments set Image='" + link +"' where IDowners='"+ this.id+"' and IDApartments='"+this.apartment+"'";
        statement.executeUpdate(sql);


        return true;

    }

    public void desc(){
        log.info("Description:");
    }
    public void location(){
        log.info("\nLocation:");
    }
    public void limits(){
        log.info("\nLimits:");
    }
    public Boolean RlocationInfo(String desc ,String location ,String limits) throws SQLException {



        Statement statement = con.createStatement();
        String sql = "UPDATE Apartments set DESCRIPTION='"+desc+"', LIMIT='"+limits+"' WHERE IDAPARTMENTS='"+this.apartment+"'";
        statement.executeUpdate(sql);
        sql = "UPDATE Housing set Address='"+location+"'"+" WHERE IDhouse='"+this.house+"' and IDowners='"+this.id+"'";
        statement.executeUpdate(sql);


        return true;
    }


    public Boolean availableService(int avaservice) throws SQLException {
        Statement statement = con.createStatement();
        String sql = "UPDATE Apartments set ServiceAvailable='"+avaservice+"' WHERE IDApartments='"+this.apartment+"'";
        statement.executeUpdate(sql);
        return true;
    }

    public Boolean monthlyRent(String YN,String value) throws SQLException {



        Statement statement = con.createStatement();
        String sql = "UPDATE Apartments set MONTHLYRENT='"+value+"' where IDApartments='"+this.apartment+"'";
        statement.executeUpdate(sql);


        return true;
    }



    public Boolean contactInfo(int phonrnum) throws SQLException {



        if (phonrnum==0)
            return false;

        Statement statement = con.createStatement();
        String sql = "UPDATE Owners set Phone='"+phonrnum+"' where ID='"+this.id+"'";
        statement.executeUpdate(sql);

        return true;
    }



    private int chooseop;


    public Boolean ownerhousemenu() throws SQLException {

        Statement statement = con.createStatement();
        String sql = "select IDhouse , Address from Housing where IDowners='"+this.id+"'";
        ResultSet rs =statement.executeQuery(sql);
        log.info("Choose a specific house number:-\n");

        while (rs.next()){
            log.info("* "+rs.getInt("IDhouse")+" "+rs.getString("Address"));
            chooseop=rs.getInt("IDhouse");
        }

        return true;

    }

    public Boolean ControlPanelHouse(int choice) throws SQLException {

        if(choice<=0 || choice >chooseop)
            return false;

        this.house=choice;



        Statement statement = con.createStatement();
        String  sql = "select Floors from Housing where IDhouse='"+choice+"'";
        ResultSet rs =statement.executeQuery(sql);
        while (rs.next()){
        chooseop=rs.getInt("Floors");}
        sql = "select count(IDtenants) from Resident where IDhouse='"+choice+"'";
        rs =statement.executeQuery(sql);

        while (rs.next()){
            log.info("number of tenants in the floor ="+rs.getInt("count(IDtenants)") +
                    "\nnumber of floors is :"+chooseop+"\n if you want to get more information about the specific floor insert it's number\n");
        }


        log.info(()->chooseop+"\n");
        return true;

    }


    public Boolean controlpanelFloor(int choice,int choise2) throws SQLException {

        if (choise2 > chooseop || choise2 <= 0)return false;


        Statement statement = con.createStatement();
        String sql = "select IDApartments from Apartments where IDhouse='"+choice+"'and IDfloorsNumber='"+choise2+"'";
        ResultSet rs =statement.executeQuery(sql);
        log.info("apartment in the floor \n");
        while (rs.next()){
            log.info("Apartment number "+rs.getString("IDApartments")+"\n");
            chooseop=rs.getInt("IDApartments");
        }

        log.info("\n if you want to get more information about the specific Apartment insert it's number\n");


        return true;
    }


    public Boolean dashBoardControlPanel(int choice , int choise2,int choice3) throws SQLException {

        Statement statement = con.createStatement();

        if(choice3<=0||choice>chooseop){return false;}

        String sql =
                "SELECT IDTENANTS " +
                        "FROM Resident " +
                        "WHERE IDhouse='"+choice+"'" +
                        "AND IDfloorsnumber='"+choise2+"'" +
                        "AND IDApartments='"+choice3+"'"+
                        "AND IDowner='"+id +
                        "'";

        String sql2;

        ResultSet rs =statement.executeQuery(sql);
        log.info("apartment in the floor \n");
        while (rs.next()){

            sql2="select NAME From TENANTS Where ID='"+rs.getInt("IDTENANTS")+"'";
            ResultSet s=statement.executeQuery(sql2);
            while (s.next()){
                log.info("Tenant Name "+s.getString("Name") +"\n") ;}
        }
        sql ="select Description from Apartments where IDApartments='"+choice3+"'";
        rs =statement.executeQuery(sql);
        while (rs.next()){  log.info(rs.getString("Description")+"\n");
        }
        return true;

} }