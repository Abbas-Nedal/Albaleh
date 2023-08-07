package Runners;

import com.example.albaleh.Tenants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tenantsability {


    Tenants tenants = new Tenants();




    @Given("Tenants is logged into the system")
    public void tenants_is_logged_into_the_system() {

        tenants.setStatus(true);
        assertTrue(tenants.isStatus());



    }

    @Then("show AvailAble Housing and return true")
    public void show_avail_able_housing_and_return_true() throws SQLException {

        assertTrue(tenants.ShowAvailableHouse());
    }



    @Given("not booking")
    public void notBooking() {

        tenants.setUserName("4002");
        assertFalse(tenants.DoIHaveAnApartmentReservation());

    }

    @When("insert information for Hosue and book")
    public void insertInformationForHosueAndBook() throws SQLException {
        tenants.bookAccommodation(1,1,1,1);


    }


    @Then("succss  book accommodation and return true")
    public void succssBookAccommodationAndReturnTrue() throws SQLException {

        assertTrue(tenants.DoIHaveAnApartmentReservation());
        tenants.DeleteBook();




    }

    @Then("show furniture succss")
    public void showFurnitureSuccss() {



        assertTrue(tenants.ShowFURNITUREAdvertise());
    }

    @Given("the tenant is book")
    public void theTenantIsBook() {

        tenants.setUserName("4000");
        assertTrue(tenants.DoIHaveAnApartmentReservation());
    }

    @Then("show info and return true")
    public void showInfoAndReturnTrue() throws SQLException {
        assertTrue(tenants.ShowSomeInfoAfterBooking());
    }

    @When("the Tenant insert info for what he has saleing")
    public void theTenantInsertInfoForWhatHeHasSaleing() {

        tenants.setUserName("4000");
        assertTrue(tenants.DeleteFURNITURE(4));

    }

    @Then("he advertise sucssfly and return ture")
    public void heAdvertiseSucssflyAndReturnTure() throws SQLException {
        assertTrue(tenants.InsertFURNITUREAdvertise(20,"absd"));

    }

    @When("the Tenant insert info for what he has saleing\\( not sucssfly )")
    public void theTenantInsertInfoForWhatHeHasSaleingNotSucssfly() {

        tenants.setUserName("4000");

    }

    @Then("Error  dose not exist  and return false")
    public void errorDoseNotExistAndReturnFalse() {
assertFalse(tenants.DeleteFURNITURE(3));
    }

    @Given("he has booking")
    public void heHasBooking() {
        tenants.setUserName("4000");
        assertTrue(tenants.DoIHaveAnApartmentReservation());
    }

    @Then("false and error and no book")
    public void falseAndErrorAndNoBook() throws SQLException {
        assertFalse(tenants.bookAccommodation(1,1,2,1));
    }

    @When("last Tenant book")
    public void lastTenantBook() throws SQLException {

        tenants.setUserName("4002");
        tenants.bookAccommodation(1,1,1,1);

    }

    @Then("convert the APARTMENTS to full")
    public void convertTheAPARTMENTSToFull() throws SQLException {
        assertTrue(tenants.changeSERVICEAVAILABLEToFull(1,1,1,1));
        tenants.DeleteBook();
    }

    @Then("show students neighbors")
    public void showStudentsNeighbors() throws SQLException {
        assertTrue(tenants.studentNeighbors());
    }
}
