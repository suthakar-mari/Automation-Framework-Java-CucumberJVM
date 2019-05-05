@regression
Feature: Adopt A Puppy
  As a brilliant and caring individual
  I want to be able to adopt a puppy
  So that I can do a lil' good in the world

  Background:
    Puppy adoption scenarios
    Given The order "Order A" exists
    And I am on the homepage

	@severity=blocker @tmsLink=007 @issue=008 @flaky
  Scenario: User adopts a puppy  
  	This is a scenario description in the report
    When I adopt a puppy providing "Order A"
    Then I should see the successful adoption message