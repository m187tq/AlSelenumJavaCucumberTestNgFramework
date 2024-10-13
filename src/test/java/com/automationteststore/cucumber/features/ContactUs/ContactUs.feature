@contactus
Feature: Verification of Contactus functionality

  Background: common steps
    Given I navigate to contactus page
    And I can see contact details "CONTACT US", "Address:" and "Telephone" are displayed

  @contactusPositive
  Scenario: user should be able to submit a message in contactus form with valid credentials
    When I inputted first name as "John" email Address as "email@email.com" and inquiry as "My message body"
    And clicks on the submit button
    Then I can see "Continue" button and "Your enquiry has been successfully sent to the store owner!"
    And I tap on the continue button
    And I landed on Ecommerce website

  @contactusNegative
  Scenario: should not be able to submit a message in contactus form with blank fields
    When clicks on the submit button
    Then I should get confirmation warning massage firstname "First name: is required field! Name must be between 3 and 32 characters!"
    Then I should get confirmation warning massage email "Email: is required field! E-Mail Address does not appear to be valid!"
    Then I should get confirmation warning massage inquiry "Enquiry: is required field! Enquiry must be between 10 and 3000 characters!"


  @contactusNegative
  Scenario: user should not be able to submit a message in contactus form with invalid email
    When I inputted first name as "John" email Address as "email" and inquiry as "My message body"
    And clicks on the submit button
    Then I should get confirmation warning massage email "E-Mail Address does not appear to be valid!"

  @contactusNegative
  Scenario: user should not be able to submit a message in contactus form with invalid message
    When I inputted first name as "John" email Address as "email@email.com" and inquiry as "Message"
    And clicks on the submit button
    #Then I should get confirmation warning massage inquiry "Enquiry: is required field! Enquiry must be between 10 and 3000 characters!"
    Then I can see "Continue" button and "Your enquiry has been successfully sent to the store owner!"
    And I tap on the continue button
    And I landed on Ecommerce website

  @contactusNegative
  Scenario: user should not be able to submit a message in contactus form with first name message
    When I inputted first name as "Jo" email Address as "email@email.com" and inquiry as "Message"
    And clicks on the submit button
    Then I should get confirmation warning massage firstname "Name must be between 3 and 32 characters!"
