
@tag
Feature: Error handling for login page
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: LoginPage
  	Given I landed on application
    When I want to login with <UserName> and <Password>
    Then I check for error message Incorrect email or password.
    
    Examples: 
      |UserName							|Password		|
      |madhus149@gmail.com	|Madhu@11		|
      ## providing wrong password to check 