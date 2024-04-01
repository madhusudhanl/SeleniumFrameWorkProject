#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase Order Flow
  I want to use this template for my feature file

 Background: 
 Given I landed on application

  @tag2
  Scenario Outline: Add products in Cart and Verfiy
    Given I want to login with <UserName> and <Password>
    When I want select the <productName> and add to cart
    And Verify the cart page for <productName> and checkout
    Then I Complete the payment by selecting <country>
    And check for confirmation message THANKYOU FOR THE ORDER.

    Examples: 
      |UserName							|Password		|productName	|country |
      |madhus149@gmail.com	|Madhu@1991	|ZARA COAT 3	|India   |
      
