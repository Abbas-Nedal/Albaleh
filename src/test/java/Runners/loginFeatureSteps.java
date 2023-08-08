package Runners;

import com.example.albaleh.Admin;
import com.example.albaleh.Owners;
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

      Owners owners = new Owners();
    Statement statement = null ;

    Admin admin = new Admin();

    public loginFeatureSteps() throws SQLException {
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

        owners.setStatus(false);

        assertFalse(owners.isStatus());
    }


    @When("Owners enterd username  is {string} and the  password is {string}")
    public void owners_enterd_username_is_and_the_password_is(String id, String pass) throws SQLException {

        owners.setStatus(owners.login(id, pass));



    }

    @Then("the Owners logged in succssfuly")
    public void the_owners_logged_in_succssfuly() {
        // Write code here that turns the phrase above into concrete actions

        assertTrue(owners.isStatus());
    }

    @When("Owners enterd username  is {string} and the password is {string}")
    public void ownersEnterdUsernameIsAndThePasswordIs(String id, String pass) throws SQLException {
        owners.setStatus(owners.login(id, pass));

    }


    @Then("the Owners login fails")
    public void the_owners_login_fails() {
        assertFalse(owners.isStatus());

    }

    @Given("the tenants is not logged in")
    public void the_tenants_is_not_logged_in() {
        assertFalse(tenants.isStatus());

        tenants.setStatus(false);


    }

    @When("tenants enterd username  is {string} and the  password is {string}")
    public void tenants_enterd_username_is_and_the_password_is(String username, String pass) throws SQLException {


        tenants.setStatus(tenants.login(username, pass));





    }

    @Then("the tenants logged in succssfuly")
    public void the_tenants_logged_in_succssfuly() {

        assertTrue(tenants.isStatus());
    }





    @When("tenants enterd username  is {string} and the password is {string}")
    public void tenantsEnterdUsernameIsAndThePasswordIs(String username, String pass) throws SQLException {


        tenants.setStatus(tenants.login(username, pass));


    }



    @Then("the tenants login fails")
    public void the_tenants_login_fails() {

        assertFalse(tenants.isStatus());

    }


    @Given("the userName and pass not null")
    public void theUserNameAndPassNotNull() {

 tenants.setUserName("4000");
 tenants.setPassword("123456");



    }

    @Then("return true value")
    public void returnTrueValue() {

        assertFalse(tenants.getUserName().equals(null));
        assertFalse(tenants.getPassword().equals(null));
    }
}

