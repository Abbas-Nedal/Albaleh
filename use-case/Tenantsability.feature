Feature: Some things that the tenant can do

  Scenario: Tenants need Watching AvailAble Housing
    Given  Tenants is logged into the system
    Then  show AvailAble Housing and return true

  Scenario: The ability to book accommodation
    Given  not booking
    When insert information for Hosue and book
    Then  succss  book accommodation and return true


  Scenario: The  Tenant  booked and need to other but this error
    Given  he has booking
    Then false and error and no book

    Scenario:  Tenants need Watching furniture
       Then show furniture succss

    Scenario: Show Some Info After Booking
      Given the tenant is book
      Then show info and return true

  Scenario: window for furniture through which the tenant can advertise his own used furniture for sale(Insert and Delete) sucssfly
    When the Tenant insert info for what he has saleing
    Then he advertise sucssfly and return ture



   Scenario: the value for  ID FURNITURE dose not exist or the Tenants  dont have this  FURNITURE
     When the Tenant insert info for what he has saleing( not sucssfly )
    Then Error  dose not exist  and return false

     Scenario: the last Tenant book and convert the APARTMENTS to full
       When last Tenant book
       Then convert the APARTMENTS to full

       Scenario: The tenant wants to know which of his neighbors is a students
         Then show students neighbors

