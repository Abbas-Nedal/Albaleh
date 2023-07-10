Feature: logout

  Scenario: Admin can logout
    Given that admin is not loged out
    When the admin enter the logout command
    Then the user will log out from the system successfully

  Scenario: Owners can logout
    Given that Owners is not loged out
    When the Owners enter the logout command
    Then the Owners will log out from the system successfully

  Scenario: tenants can logout
    Given that tenants is not loged out
    When the tenants enter the logout command
    Then the tenants will log out from the system successfully