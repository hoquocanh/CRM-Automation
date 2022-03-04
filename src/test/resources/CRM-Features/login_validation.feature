#  Author: 
Feature: Login Validaiton
	Login button is enabled when User enters both username and password
	Error message is displayed when user user logs in with invalid username and password
	User is directed to Home page after logging in successfully

  Background: 
    Given User navigates to <server> Login page

  Scenario: Login button is enabled when user enters username and password
    When User enters username and password
    Then Login button should be disabled
    
  Scenario: Login button is enabled when user enters username and password
    When User enters username and password
    Then Login button should be enabled

  Scenario: Error message is displayed when user enters invalid username and password
    When User logins with invalid username and password
    Then Error message "Sorry, either the username or password seems to be invalid. Please try again or contact support@cogitocorp.com for help." should be displayed

  Scenario Outline: User Home page is displayed when User logged in
    When User logs in as <role>
    Then <role> Home page should be displayed

    Examples: 
      | role       |
      | Agent      |
      | Supervisor |
