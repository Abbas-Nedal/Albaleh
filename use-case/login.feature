Feature: loginPage

  Scenario: Admin can login
    Given the Admin is not logged in
    When  enterd username  is "admin" and the password is "admin"
    Then the admin logged in succssfuly

   Scenario: Admin has the wrong password
    Given the Admin is not logged in
    When  enterd  username  is "admin" and the password is "123"
    Then the admin login fails

    Scenario:  Owners can login
      Given the Owners is not logged in
      When Owners enterd username  is "1" and the  password is "123456"
      Then the Owners logged in succssfuly


    Scenario: Owners has the wrong password
    Given the Owners is not logged in
    When Owners enterd username  is "1" and the password is "123"
    Then the Owners login fails


   Scenario:  tenants can login
    Given the tenants is not logged in
    When tenants enterd username  is "4000" and the  password is "123456"
    Then the tenants logged in succssfuly


   Scenario: tenants has the wrong password
    Given the tenants is not logged in
    When tenants enterd username  is "4000" and the password is "123"
    Then the tenants login fails

