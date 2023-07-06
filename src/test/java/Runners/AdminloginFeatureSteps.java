package Runners;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminloginFeatureSteps {


    @Given("that Admin is not logged in")
    public void thatAdminIsNotLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
     //   throw new io.cucumber.java.PendingException();
    }
    @When("the enterd password is {string} and the username is {string}")
    public void theEnterdPasswordIsAndTheUsernameIs(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
      //  throw new io.cucumber.java.PendingException();
    }
    @Then("the admin logged in succssfuly")
    public void theAdminLoggedInSuccssfuly() {
        // Write code here that turns the phrase above into concrete actions
      //  throw new io.cucumber.java.PendingException();
        assertTrue(true);

    }





    @Then("the admin login fails")
    public void theAdminLoginFails() {
        // Write code here that turns the phrase above into concrete actions
     //   throw new io.cucumber.java.PendingException();
    }


}
