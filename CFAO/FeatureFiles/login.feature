  Feature: OTM First Test Cases
  
  @FO_001
  Scenario: Access to domain
    Given Login into OTM as 'Admin'
    Then Wait for '10' seconds
    Then Attach ScreenShot
    Then Logout from OTM
    
      @FO_002
  Scenario: Access to domain
    Given Login into OTM as 'Admin'
    Then Wait for '10' seconds
    Then Logout from OTM