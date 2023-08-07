package Runners;

import com.example.albaleh.Owners;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class OwnerTest {

    private  Owners owners = new Owners(1);;
    int chioce =0;
    Boolean TF=false;
    @Given("the system starts")
    public void the_system_starts() {
    owners.setId(1);
    }

    @When("the Owners logged in")
    public void theOwnersLoggedIn() {
        owners.setStatus(true);
    }

    @Then("it returns true and print the first menu")
    public void it_returns_true_and_print_the_first_menu() {

        assertTrue(owners.menu());
    }

    @Given("the Owners log in succssfuly")
    public void theOwnersLogInSuccssfuly() {
        owners.setStatus(true);
    }


    @When("the owner choose number one {int}")
    public void the_owner_choose_number_one(int s) {

        TF= this.owners.menuChoice(s);

    }

    @Then("return the chosen number and print enter the house id")
    public void return_the_chosen_number_and_print_enter_the_house_id() {
     assertTrue(TF);
    }

    @When("the owner choose number two {int}")
    public void the_owner_choose_number_two(int e) {
        TF = owners.menuChoice(e);
    }

    @Then("return the chosen number and print the list of own house")
    public void return_the_chosen_number_and_print_the_list_of_own_house() {
        assertTrue(TF);
    }

    @When("the owner choose any number expect one and two and three like {int}")
    public void the_owner_choose_any_number_expect_one_and_two_and_three(int e) {

           TF=owners.menuChoice(e);

    }

    @Then("return zero and print a alter msg")
    public void return_zero_and_print_a_alter_msg() {

        assertFalse(TF);
    }

    @When("the owner choose number three {int}")
    public void theOwnerChooseNumberThree(int sd) {
        TF=owners.menuChoice(sd);
    }

    @Then("return the chosen number and get out from the system")
    public void return_the_chosen_number_and_get_out_from_the_system() {
        assertTrue(TF);
    }




    @Given("the owner chose option number {int} \\(announcing)")
    public void the_owner_chose_option_number_one(int one) {

        owners.menuChoice(one);

    }

    @When("the owner enter the right house id like {int}")
    public void the_owner_enter_the_right_house_id(int one) throws SQLException {
        TF = owners.witchhouse(one);
    }

    @Then("it returns true and print which floor")
    public void it_returns_true_and_print_which_floor() {
       assertTrue(TF);
    }

    @When("the owner enter the wrong house id like {int}")
    public void the_owner_enter_the_wrong_house_id(int four) throws SQLException {
        TF = owners.witchhouse(four);
    }

    @Then("it returns false and alter msg")
    public void it_returns_false_and_alter_msg() {
        assertFalse(TF);
    }
















    @Given("the owner enter the right house id")
    public void the_owner_enter_the_right_house_id1() throws SQLException {
            owners.witchhouse(1);
    }

    @When("the owner enter the right floor id like {int}")
    public void the_owner_enter_the_right_floor_id(int floor) throws SQLException {
            TF = owners.witchFloor(floor);
    }

    @Then("it returns true and print announcing service list and it insert a new apartment")
    public void it_returns_true_and_print_announcing_service_list() throws SQLException {
        assertTrue(TF);
        owners.announcingPrivateResidencesMenu();
        owners.insertApartment();

    }

    @When("the owner enter the wrong floor id like {int}")
    public void the_owner_enter_the_wrong_floor_id(int floor) throws SQLException {
        TF = owners.witchFloor(floor);
    }

    @Then("it return false and alter msg")
    public void it_return_false_and_alter_msg() {
        assertFalse(TF);
    }












    @Given("the owner enter the right floor id")
    public void the_owner_enter_the_right_floor_id1() throws SQLException {
     owners.witchFloor(1);
    }

    @When("the owner chose number not in one-six range like {int}")
    public void the_owner_chose_number_not_in_one_six_range(int choice) {
       TF = owners.announcingPrivateResidences(choice);
    }

    @Then("it returns false and alter message")
    public void it_returns_false_and_alter_message() {
        assertFalse(TF);
    }

    @When("the owner chose number one {int} in list services")
    public void the_owner_chose_number_one_in_list_services1(int coo) {
        TF = owners.announcingPrivateResidences(coo);

    }

    @Then("it returns true and print the choice")
    public void it_returns_the_chosen_number_and_print_what_the_add_photo_do() {
        assertTrue(TF);
    }



    @When("the owner chose number two {int} in list services")
    public void theOwnerChoseNumberTwoInListServices(int arg0) {
        TF = owners.announcingPrivateResidences(arg0);
    }

    @Then("it returns true and print the choice two")
    public void itReturnsTrueAndPrintTheChoiceTwo() {
        assertTrue(TF);
    }

    @When("the owner chose number three {int} in list services")
    public void theOwnerChoseNumberThreeInListServices(int arg0) {
        TF = owners.announcingPrivateResidences(arg0);
    }

    @Then("it returns true and print the choice three")
    public void itReturnsTrueAndPrintTheChoiceThree() {
        assertTrue(TF);
    }

    @When("the owner chose number four {int} in list services")
    public void theOwnerChoseNumberFourInListServices(int arg0) {
        TF = owners.announcingPrivateResidences(arg0);
    }

    @Then("it returns true and print the choice four")
    public void itReturnsTrueAndPrintTheChoiceFour() {
        assertTrue(TF);
    }

    @When("the owner chose number five {int} in list services")
    public void theOwnerChoseNumberFiveInListServices(int arg0) {
        TF = owners.announcingPrivateResidences(arg0);
    }

    @Then("it returns true and print the choice five")
    public void itReturnsTrueAndPrintTheChoiceFive() {
        assertTrue(TF);
    }

    @When("the owner chose number six {int} in list services")
    public void theOwnerChoseNumberSixInListServices(int arg0) {
        TF = owners.announcingPrivateResidences(arg0);
    }

    @Then("it returns true and print the choice six")
    public void itReturnsTrueAndPrintTheChoiceSix() {
        assertTrue(TF);
    }




    @Given("the owner chose number {int} in list services")
    public void the_owner_chose_number_one_in_list_services2(int i) {
        owners.announcingPrivateResidences(1);
    }

    @When("the owner insert the right path like {string}")
    public void theOwnerInsertTheRightPathLike(String arg0) throws SQLException {
        owners.setApartment(3);
        TF = owners.addPhoto(arg0);
    }

    @Then("The system should return true a")
    public void theSystemShouldReturnTrueA() {
        assertTrue(TF);
    }

    @When("the owner insert an empty path like {string}")
    public void theOwnerInsertAnEmptyPathLike(String arg0) throws SQLException {
        TF = owners.addPhoto("");
    }

    @Then("The system should return falsea")
    public void theSystemShouldReturnFalsea() {
        assertFalse(TF);
    }

    @When("the owner insert nothing")
    public void theOwnerInsertNothing() throws SQLException {

        TF = owners.addPhoto(null);
    }


    @Then("The system should return falseq")
    public void theSystemShouldReturnFalseq() {
        assertFalse(TF);
    }





    @Given("the owner chose number  {int} in list services")
    public void theOwnerChoseNumberInListServices(int arg0) {
        owners.announcingPrivateResidences(arg0);
    }


    @When("the owner fill all the info")
    public void theOwnerFillAllTheInfo() throws SQLException {
      TF=owners.RlocationInfo("hi this is desc","khalil","8");
    }
    @Then("it returns true and")
    public void it_returns_the_chosen_number_and_print_what_the_r_location_information_do() {
assertTrue(TF);
    }

    @Given("the owner chose numberx three  {int} in list services")
    public void theOwnerChoseNumberThreeInListServices1(int arg0) {
        owners.announcingPrivateResidences(arg0);
    }

    @When("add the available service")
    public void addTheAvailableService() throws SQLException {
    TF=owners.availableService(1);
    }

    @Then("it returns truee")
    public void itReturnsTruee() {
assertTrue(TF);
    }

    @Given("the owner chose numberx four  {int} in list services")
    public void theOwnerChoseNumberxFourInListServices(int arg0) {
        owners.announcingPrivateResidences(arg0);
    }




    @When("the owner chose number four in list services with value {string} and {string}")
    public void theOwnerChoseNumberFourInListServicesWithValueAnd(String arg0, String arg1) throws SQLException {
        TF= owners.monthlyRent(arg0,arg1);
    }
    @Then("it returns true and print what the monthly rent do")
    public void itReturnsTrueAndPrintWhatTheMonthlyRentDo() {
        assertTrue(TF);
    }

    @Given("the owner chose numberx five  {int} in list services")
    public void theOwnerChoseNumberxFiveInListServices(int arg0) {
        owners.announcingPrivateResidences(arg0);
    }

    @When("has a previos contact information and won't to change it insert {int}")
    public void hasAPreviosContactInformationAndWonTToChangeItInsert(int arg0) throws SQLException {
        TF = owners.contactInfo(arg0);
    }

    @Then("it returns false and back to the list services menu")
    public void itReturnsFalseAndBackToTheListServicesMenu() {
        assertFalse(TF);
    }



    @Then("it returns true and back to the list services menu")
    public void itReturnsTrueAndBackToTheListServicesMenu() {
        assertTrue(TF);
    }

    @When("don't has a previos and insert number like {int}")
    public void donTHasAPreviosAndInsertNumberLike(int arg0) throws SQLException {
        TF = owners.contactInfo(arg0);
    }




    @Given("print the list of own house")
    public void printTheListOfOwnHouse() throws SQLException {

    owners.ownerhousemenu();
    }

    @When("the owner enter house number from the list like {int}")
    public void theOwnerEnterHouseNumberFromTheList(int arg0) throws SQLException {

  TF =      owners.ControlPanelHouse(arg0);

    }

    @Then("it returns true and print info about this house")
    public void itReturnsTrueAndPrintInfoAboutThisHouse() {
        assertTrue(TF);
    }

    @When("the owner enter house number not from the list like {int}")
    public void theOwnerEnterHouseNumberNotFromTheListLike(int arg0) throws SQLException {
        TF=owners.ControlPanelHouse(arg0);
    }

    @Then("it returns false and return to the menu")
    public void itReturnsFalseAndReturnToTheMenu() {
        assertFalse(TF);
    }


    @Given("print info about the house insert {int}")
    public void printInfoAboutTheHouseInsert(int arg0) throws SQLException {
         chioce =arg0;
        owners.ownerhousemenu();
         owners.ControlPanelHouse(arg0);
    }

    @When("the owner enter floor number from the list like {int}")
    public void theOwnerEnterFloorNumberFromTheListLike(int arg0) throws SQLException {
        TF=owners.controlpanelFloor(chioce,arg0);
    }

    @Then("it returns true and print info about this floor")
    public void itReturnsTrueAndPrintInfoAboutThisFloor() {
        assertTrue(TF);
    }

    @When("the owner enter floor number not from the list like {int}")
    public void theOwnerEnterFloorNumberNotFromTheList(int a) throws SQLException {
        TF = owners.controlpanelFloor(chioce,a);
    }

    @Then("it returns false and return to the back menu")
    public void itReturnsFalseAndReturnToTheBackMenu() {
        assertFalse(TF);

    }
int ch;
    @Given("insert a house id = {int} and floor id ={int}")
    public void insertAHouseIdAndFloorId(int arg0, int arg1) throws SQLException {
        owners.ownerhousemenu();
        owners.ControlPanelHouse(arg0);
        owners.controlpanelFloor(arg0,arg1);
        chioce=arg0;
        ch=arg1;



    }

    @When("the owner enter apartment number from the list like {int}")
    public void theOwnerEnterApartmentNumberFromTheList(int w) throws SQLException {
       TF = owners.dashBoardControlPanel(chioce,ch,w);
    }

    @Then("it returns true and print info about this apartment")
    public void itReturnsTrueAndPrintInfoAboutThisApartment() {
        assertTrue(TF);
    }



    @Then("add the advertisement successfully")
    public void addTheAdvertisementSuccessfully() throws SQLException {
       assertTrue(owners.addAdv(1,1,3,"Testing"));


        owners.setHouse(owners.getHouse());
        owners.setFloor(owners.getFloor());
        owners.setApartment(owners.getApartment());
    }
}
