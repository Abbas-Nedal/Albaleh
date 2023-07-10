package Runners;

import com.example.albaleh.Admin;
import com.example.albaleh.Tenants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogOut {


 Admin admin = new Admin();
Tenants tenants = new Tenants();

    @Given("that admin is not loged out")
    public void that_admin_is_not_loged_out() {

        admin.setStatus(true);
    }

    @When("the admin enter the logout command")
    public void the_admin_enter_the_logout_command() {

        if (admin.isStatus() == true){
            admin.setStatus(false);
        }


    }

    @Then("the user will log out from the system successfully")
    public void the_user_will_log_out_from_the_system_successfully() {

        assertFalse(admin.isStatus());
    }

    @Given("that Owners is not loged out")
    public void that_owners_is_not_loged_out() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the Owners enter the logout command")
    public void the_owners_enter_the_logout_command() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the Owners will log out from the system successfully")
    public void the_owners_will_log_out_from_the_system_successfully() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("that tenants is not loged out")
    public void that_tenants_is_not_loged_out() {

        tenants.setStatus(true);

    }

    @When("the tenants enter the logout command")
    public void the_tenants_enter_the_logout_command() {

        if (tenants.isStatus() == true){
            tenants.setStatus(false);
        }
    }

    @Then("the tenants will log out from the system successfully")
    public void the_tenants_will_log_out_from_the_system_successfully() {

        assertFalse(tenants.isStatus());
    }


}
