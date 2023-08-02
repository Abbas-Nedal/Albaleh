package Runners;

import com.example.albaleh.Admin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WatchingReservationsSteps  {

    Admin admin =new Admin();

    public WatchingReservationsSteps() throws SQLException {
    }


    @Given(": Admin is logged into the system")
    public void admin_is_logged_into_the_system() {

        admin.setStatus(true);

    }

    @When(": the admin entered a request, see reservations")
    public void the_admin_entered_a_request_see_reservations() {

 assertTrue(admin.isStatus());

    }

    @Then(": It shows the house number , the owner's number, the floor number, the apartment number, and the tenants' number")
    public void it_shows_the_house_number_the_owner_s_number_the_floor_number_the_apartment_number_and_the_tenants_number() {
        // Write code here that turns the phrase above into concrete actions

          assertTrue(admin.WatchingReservations());

    }


}
