Feature: ADVERTISEMENT

  Scenario: admin show  Accepted ADVERTISEMENT
    Given : the admin in login and the admin entered a request and see reservations in system

    Then :  Show ads previously approved by the admin

  Scenario: admin show  Show ADVERTISEMENT Waiting ِAcceptance
    Given : the admin in login and the admin entered a request and see reservations

    Then :  Show ads awaiting admin approval



  Scenario: admin  Accepted ADVERTISEMENT
    Given : the ADVERTISEMENT is processing
    When the Admin Accepted
    Then : The status of the ad becomes valid and is displayed

  Scenario: admin  Refuse ADVERTISEMENT
    Given : the ADVERTISEMENT a processing
    When the Admin Refused
    Then : The status of the ad becomes invalid and will not be shown


