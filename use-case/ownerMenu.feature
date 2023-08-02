Feature: menuOwner
  Scenario: first Two Options
   Given the system starts
    When the Owners logged in
    Then it returns true and print the first menu




  Scenario: select the first option
    Given the Owners log in succssfuly
    When the owner choose number one 1
    Then return the chosen number and print enter the house id


  Scenario: select the second option
    Given the Owners log in succssfuly
    When the owner choose number two 2
    Then return the chosen number and print the list of own house


  Scenario: select the other option
    Given the Owners log in succssfuly
    When the owner choose any number expect one and two and three like 4
    Then return zero and print a alter msg

  Scenario: select the third option
    Given the Owners log in succssfuly
    When the owner choose number three 3
    Then return the chosen number and get out from the system








    Scenario: the owner enter the right house id
      Given the owner chose option number 1 (announcing)
      When the owner enter the right house id like 1
      Then it returns true and print which floor

    Scenario: the owner enter the wrong house id
      Given the owner chose option number 1 (announcing)
      When the owner enter the wrong house id like 4
      Then it returns false and alter msg






  Scenario: the owner enter the right floor id
    Given the owner enter the right house id
    When the owner enter the right floor id like 1
    Then it returns true and print announcing service list and it insert a new apartment

  Scenario: the owner enter the wrong floor id
    Given the owner enter the right house id
    When the owner enter the wrong floor id like 7
    Then it return false and alter msg







  Scenario: the owner choose number not in the list service
    Given the owner enter the right floor id
    When the owner chose number not in one-six range like 0
    Then it returns false and alter message
#
  Scenario: the owner chose number one in list services
    Given the owner enter the right floor id
    When the owner chose number one 1 in list services
    Then it returns true and print the choice


  Scenario: the owner chose number two in list services
    Given the owner enter the right floor id
    When the owner chose number two 2 in list services
    Then it returns true and print the choice two

  Scenario: the owner chose number three in list services
    Given the owner enter the right floor id
    When the owner chose number three 3 in list services
    Then it returns true and print the choice three

  Scenario: the owner chose number four in list services
    Given the owner enter the right floor id
    When the owner chose number four 4 in list services
    Then it returns true and print the choice four

  Scenario: the owner chose number five in list services
    Given the owner enter the right floor id
    When the owner chose number five 5 in list services
    Then it returns true and print the choice five

  Scenario: the owner chose number six in list services
    Given the owner enter the right floor id
    When the owner chose number six 6 in list services
    Then it returns true and print the choice six



  Scenario: Add Photo successfully
    Given the owner chose number 1 in list services
    When the owner insert the right path like "osama.jpg"
    Then The system should return true a


  Scenario: Add an empty photo
    Given the owner chose number 1 in list services
    When  the owner insert an empty path like ""
    Then The system should return falsea

  Scenario: Add null
    Given the owner chose number 1 in list services
    When  the owner insert nothing
    Then The system should return falseq


  Scenario: the owner chose number x two in list services
    Given the owner chose number  2 in list services
    When the owner fill all the info
    Then it returns true and

  Scenario: the owner chose numberx three in list services
    Given the owner chose numberx three  3 in list services
    When add the available service
    Then it returns truee




  Scenario: the owner chose number four in list services
    Given the owner chose numberx four  4 in list services
    When the owner chose number four in list services with value "No" and "300"
    Then it returns true and print what the monthly rent do






  Scenario: the owner already have a information to contact
    Given the owner chose numberx five  5 in list services
    When has a previos contact information and won't to change it insert 0
    Then it returns false and back to the list services menu

  Scenario: the owner don't have a information to contact
    Given the owner chose numberx five  5 in list services
    When don't has a previos and insert number like 569878
    Then it returns true and back to the list services menu





  Scenario: select a house number from the list that owned
    Given print the list of own house
    When the owner enter house number from the list like 1
    Then it returns true and print info about this house



  Scenario: select a house number not from the list that owned
    Given print the list of own house
    When the owner enter house number not from the list like 9
    Then it returns false and return to the menu

  Scenario: select a floor number from the list that owned
    Given print info about the house insert 1
    When the owner enter floor number from the list like 1
    Then it returns true and print info about this floor

  Scenario: select a floor number not from the list that owned
    Given print info about the house insert 1
    When the owner enter floor number not from the list like 9
    Then it returns false and return to the back menu

  Scenario: select a Apartment number from the list that owned
    Given insert a house id = 1 and floor id =1
    When the owner enter apartment number from the list like 1
    Then it returns true and print info about this apartment

  Scenario: select a Apartment number not from the list that owned
    Given insert a house id = 1 and floor id =1
    When the owner enter apartment number not from the list like 55
    Then it returns false and return to the menu main



#  Scenario:return to the main menu
#    Given print info about the apartment
#    When chose logout
#    Then it returns true and return to the main menu

