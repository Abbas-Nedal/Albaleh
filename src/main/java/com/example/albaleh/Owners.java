package com.example.albaleh;

import java.sql.*;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;


public class Owners {

    static final String JDBC = "jdbc:sqlite:abbas";


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
    private String Password , userName ;
    private boolean status;
    private int house,floor,apartment;

    Scanner scanner = new Scanner(System.in);


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
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "abbas", "abbas");

            // Create a prepared statement with a parameterized query
            String sqlQuery = "SELECT ID, PASSWORD FROM OWNERS WHERE ID = ? AND PASSWORD = ?";
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
            e.printStackTrace();
        }

        return false;
    }

    public Boolean menu(){

        out.println("Owner menu:-"+
                "\n1-announcing private residences"
                + "\n2-Dashboard owner control panel"
                + "\n3-Logout"
                + "Choose the Number that you want");

        return true;
    }


    public Boolean menuChoice(int chooseOption){

        if (chooseOption>3||chooseOption<1){
            out.println("sorry your chose wasn't in the menu please try again");
            return false;
        }

        switch (chooseOption){
            case 1: out.print("Please enter the house number: "); break;
            case 2:  out.println("Choose a specific house number:-");break;
        }
        return true;
    }



    public Boolean witchhouse(int houseid) throws SQLException {

        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String sql = "select IDhouse from Housing  where IDowners='"+this.id+"' and IDhouse='"+houseid+"'";
        ResultSet rs =statement.executeQuery(sql);

        while (rs.next()){


            this.house=houseid;
            out.println("Please enter the floor number: ");
            return true;
        }
        out.println("Sorry you don't have a house with this number please try again ");
        return false;
    }

    public Boolean witchFloor(int floorid) throws SQLException {

        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String sql = "select IDfloorsnumber from Floors  where IDowners='"+this.id+"' and IDhouse='"+house+"' and IDfloorsnumber='"+floorid+"'";
        ResultSet rs =statement.executeQuery(sql);
        while (rs.next()){
            this.floor=floorid;
            return true;
        }
        out.println("Sorry you don't have floor with this number please try again ");
        return false;
    }


    public Boolean announcingPrivateResidencesMenu(){
        out.println("What do you want to do:-\n" +
                "1- Add Photo\n" +
                "2-  Residence location and information about the apartment\n" +
                "3- Available services\n" +
                "4- The monthly rent\n" +
                "5- contact information" +
                "6- return to main menu");



        return true;

    }

    public Boolean announcingPrivateResidences(int choose){


        if (choose > 6 || choose<1)
        {  out.println("try again");
            return false;

        }

        switch (choose){
            case 1:out.print("** Add photos**");break;
            case 2:out.print("** Residence location and information**");break;
            case 3:out.print("** Available services**");break;
            case 4:out.print("** The monthly rent (is it inclusive of electricity and water or not)\n**");break;
            case 5:out.print("**contact information**");break;
            case 6:out.print("****");break;
        }

        return true;

    }


    public void returnmaxid() throws SQLException {
        int maxid = 0;
        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String sql = "select max(IDApartments) from Apartments";
        ResultSet rs =statement.executeQuery(sql);
        if (rs.next()) {
            maxid=rs.getInt("max(IDApartments)");
            this.apartment=maxid;
            this.apartment++;
        }
        connection.close();
    }


    public Boolean insertApartment() throws SQLException {

        returnmaxid();
        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String sql = "insert into Apartments values ('"+this.house+"','"+this.floor+"','"+this.apartment+"','"+this.id+"','no Description','nothing','4','0','no','0','0')";

        statement.executeUpdate(sql);

        connection.close();
        return true;
    }


    public Boolean addPhoto(String link) throws SQLException {
        if(link == null || link.equals(""))
        {
            out.println("Please insert a path :)");
            return false;
        }

        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String sql = "UPDATE Apartments set Image='" + link +"' where IDowners='"+ this.id+"' and IDApartments='"+this.apartment+"'";
        statement.executeUpdate(sql);
        connection.close();

        return true;

    }

    public void desc(){
        out.print("Description:");
    }
    public void location(){
        out.print("\nLocation:");
    }
    public void limits(){
        out.print("\nLimits:");
    }
    public Boolean RlocationInfo(String desc ,String location ,String limits) throws SQLException {



        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String sql = "UPDATE Apartments set Description='"+desc+"'and Limits='"+limits+"' WHERE IDApartments='"+this.apartment+"'";
        statement.executeUpdate(sql);
        sql = "UPDATE Housing set Address='"+location+"'"+" WHERE IDhouse='"+this.house+"' and IDowners='"+this.id+"'";
        statement.executeUpdate(sql);
        connection.close();

        return true;
    }


    public Boolean availableService(int avaservice) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String sql = "UPDATE Apartments set ServiceAvailable='"+avaservice+"' WHERE IDApartments='"+this.apartment+"'";
        statement.executeUpdate(sql);
        return true;
    }

    public Boolean monthlyRent(String YN,String value) throws SQLException {

//
//        out.println("Inclusive water and electricity or not?Yes/No");

        //out.print("\nenter the value of mounthly rent :");

        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String sql = "UPDATE Apartments set MonthlyRent='"+value+"' where IDApartments='"+this.apartment+"'";
        statement.executeUpdate(sql);
        connection.close();

        return true;
    }



    public Boolean contactInfo(int phonrnum) throws SQLException {

        //out.print("enter your phone number (if your number exist Print 0 ):");


        if (phonrnum==0)
            return false;

        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String sql = "UPDATE Owners set Phone='"+phonrnum+"' where ID='"+this.id+"'";
        statement.executeUpdate(sql);
        connection.close();
        return true;
    }



    private int chooseop;


    public Boolean ownerhousemenu() throws SQLException {
        int i=0;
        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String sql = "select IDhouse , Address from Housing where IDowners='"+this.id+"'";
        ResultSet rs =statement.executeQuery(sql);
        out.println("Choose a specific house number:-");
        while (rs.next()){
            out.println("* "+rs.getInt("IDhouse")+" "+rs.getString("Address"));
            chooseop=rs.getInt("IDhouse");
        }

        return true;

    }

    public Boolean ControlPanelHouse(int choice) throws SQLException {

        if(choice<=0 || choice >chooseop)
            return false;

        this.house=choice;



        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String  sql = "select Floors from Housing where IDhouse='"+choice+"'";
        ResultSet rs =statement.executeQuery(sql);
        chooseop=rs.getInt("Floors");
        sql = "select count(IDtenants) from Resident where IDhouse='"+choice+"'";
        rs =statement.executeQuery(sql);

        while (rs.next()){
            out.println("number of tenants in the floor ="+rs.getInt("count(IDtenants)") +
                    "\nnumber of floors is :"+chooseop+"\n if you want to get more information about the specific floor insert it's number");
        }


        out.println(chooseop);
        return true;

    }


    public Boolean controlpanelFloor(int choice,int choise2) throws SQLException {
        out.println(chooseop);
        out.println(choice);
        out.println(choise2);
        if (choise2 > chooseop || choise2 <= 0)return false;


        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();
        String sql = "select IDApartments from Apartments where IDhouse='"+choice+"'and IDfloorsNumber='"+choise2+"'";
        ResultSet rs =statement.executeQuery(sql);
        out.println("apartment in the floor ");
        while (rs.next()){
            out.println("Apartment number "+rs.getString("IDApartments"));
            chooseop=rs.getInt("IDApartments");
        }

        out.println("\n if you want to get more information about the specific Apartment insert it's number");


        return true;
    }


    public Boolean dashBoardControlPanel(int choice , int choise2,int choice3) throws SQLException {

        Connection connection = DriverManager.getConnection(JDBC);
        Statement statement = connection.createStatement();

        if(choice3<=0||choice3>chooseop)return false;

        String sql ="SELECT Name " +
                "FROM Tenants " +
                "WHERE ID=(" +
                "SELECT IDtenants " +
                "FROM Resident " +
                "WHERE IDhouse='"+choice+"'" +
                "AND IDfloorsnumber='"+choise2+"'" +
                "AND IDApartments='"+choice3+"'"+
                "AND IDowner='"+id +
                "')";

        ResultSet rs =statement.executeQuery(sql);
        out.println("apartment in the floor ");
        while (rs.next()){
            out.println("Tenant Name "+rs.getString("Name"));
        }
        sql ="select Description from Apartments where IDApartments='"+choice3+"'";
        rs =statement.executeQuery(sql);
        while (rs.next()){out.println(rs.getString("Description"));
        }
        return true;
    }




}