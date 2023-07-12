package Runners;

import com.example.albaleh.Admin;
import com.example.albaleh.Tenants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.swing.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class loginFeatureSteps {

    Tenants tenants = new Tenants();


    Statement statement = null ;

    Admin admin = new Admin();

    public boolean connection(String idUser , String passwordUser ){

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
            e.printStackTrace();
        }




        return false;
    }

    @Given("the Admin is not logged in")
    public void the_admin_is_not_logged_in() {
        // Write code here that turns the phrase above into concrete actions

        admin.setStatus(false);


    }

    @When("enterd username  is {string} and the password is {string}")
    public void enterd_username_is_and_the_password_is(String username, String pass) {

        if (admin.getPassword().equals(pass) && admin.getUserName().equals(username)){
                            admin.setStatus(true);


        } else {

            admin.setStatus(false);

        }

    }

    @Then("the admin logged in succssfuly")
    public void theAdminLoggedInSuccssfuly() {

        assertTrue(admin.isStatus());
    }
    @When("enterd  username  is {string} and the password is {string}")
    public void enterdUsernameIsAndThePasswordIs(String username, String pass) {

        if (admin.getPassword().equals(pass) && admin.getUserName().equals(username)){
            admin.setStatus(true);


        } else {

            admin.setStatus(false);

        }
    }


    @Then("the admin login fails")
    public void theAdminLoginFails() {

        assertFalse(admin.isStatus());
    }



    @Given("the Owners is not logged in")
    public void the_owners_is_not_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("Owners enterd username  is {string} and the  password is {string}")
    public void owners_enterd_username_is_and_the_password_is(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the Owners logged in succssfuly")
    public void the_owners_logged_in_succssfuly() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



    @Then("the Owners login fails")
    public void the_owners_login_fails() {

    }

    @Given("the tenants is not logged in")
    public void the_tenants_is_not_logged_in() {
        assertFalse(tenants.isStatus());

        tenants.setStatus(false);


    }

    @When("tenants enterd username  is {string} and the  password is {string}")
    public void tenants_enterd_username_is_and_the_password_is(String username, String pass) throws SQLException {


        tenants.setStatus(connection(username, pass));





    }

    @Then("the tenants logged in succssfuly")
    public void the_tenants_logged_in_succssfuly() {

        assertTrue(tenants.isStatus());
    }





    @When("tenants enterd username  is {string} and the password is {string}")
    public void tenantsEnterdUsernameIsAndThePasswordIs(String username, String pass) {


        tenants.setStatus(connection(username, pass));


    }



    @Then("the tenants login fails")
    public void the_tenants_login_fails() {

        assertFalse(tenants.isStatus());

    }



    @When("Owners enterd username  is {string} and the password is {string}")
    public void ownersEnterdUsernameIsAndThePasswordIs(String arg0, String arg1) {


    }





}

