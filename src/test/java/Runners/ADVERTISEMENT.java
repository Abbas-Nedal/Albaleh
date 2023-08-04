package Runners;

import com.example.albaleh.Admin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.swing.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ADVERTISEMENT {

    Admin admin = new Admin();

    public ADVERTISEMENT() throws SQLException {
    }


    @Given(": the admin in login and the admin entered a request and see reservations in system")
    public void the_admin_in_login_and_the_admin_entered_a_request_and_see_reservations_in_system() {

        admin.setStatus(true);
        assertTrue(admin.isStatus());
    }

    @Then(":  Show ads previously approved by the admin")
    public void show_ads_previously_approved_by_the_admin() {

        assertTrue(admin.ShowAcceptedAds());

    }


    @Given(": the admin in login and the admin entered a request and see reservations")
    public void theAdminInLoginAndTheAdminEnteredARequestAndSeeReservations() {

        admin.setStatus(true);
        assertTrue(admin.isStatus());
    }

    @Then(":  Show ads awaiting admin approval")
    public void showAdsAwaitingAdminApproval() {

        assertTrue(admin.ShowAdaWaitingِAcceptance());
    }

    @Given(": the ADVERTISEMENT is processing")
    public void theADVERTISEMENTIsProcessing() {

       assertTrue(admin.CheckIfIsProcceing(1,1,1,2,1));
    }

    @When("the Admin Accepted")
    public void theAdminAccepted() {
        admin.AcceptAds(1,1,1,2,1);

    }

    @Then(": The status of the ad becomes valid and is displayed")
    public void theStatusOfTheAdBecomesValidAndIsDisplayed() {
        assertTrue(admin.checkIfIsSTATES(1,1,1,2,1));
         admin.SetIsProcessing(1,1,1,2,1);
    }

    @Given(": the ADVERTISEMENT a processing")
    public void theADVERTISEMENTAProcessing() {

        assertTrue(admin.CheckIfIsProcceing(1,1,1,2,1));


    }

    @When("the Admin Refused")
    public void theAdminRefused() {
       admin.RefuseAds(1,1,1,2,1);
    }

    @Then(": The status of the ad becomes invalid and will not be shown")
    public void theStatusOfTheAdBecomesInvalidAndWillNotBeShown() {
        assertFalse(admin.checkIfIsSTATES(1,1,1,2,1) || admin.CheckIfIsProcceing(1,1,1,2,1) );
        admin.SetIsProcessing(1,1,1,2,1);
    }
}
