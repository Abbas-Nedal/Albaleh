package Runners;

import com.example.albaleh.Admin;
import com.example.albaleh.Owners;
import com.example.albaleh.Tenants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogOut {


    Owners owners = new Owners();
 Admin admin = new Admin();
Tenants tenants = new Tenants();

    public LogOut() throws SQLException {
    }

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

        owners.setId(owners.getId());
        owners.setPassword(owners.getPassword());


      Owners s =new Owners(1,"","","",false);
        owners.setStatus(true);
        owners.setUserName(owners.getUserName());
        owners.setHouse(3);
        owners.setFloor(2);
owners.desc();
owners.location();
owners.limits();
s.setName(s.getName());


    }

    @When("the Owners enter the logout command")
    public void the_owners_enter_the_logout_command() {


        if (owners.isStatus() == true){
            owners.setStatus(false);
        }
    }

    @Then("the Owners will log out from the system successfully")
    public void the_owners_will_log_out_from_the_system_successfully() {

        assertFalse(owners.isStatus());
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
