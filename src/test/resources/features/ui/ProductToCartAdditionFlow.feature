@productAdditionValidation
Feature: Validate if the product is added multiples times in the cart
Description: This feature goal is validating the addition of multiple products on the website cart 

	Background: User is on the homepage
		Given I'm on the selling homepage
		When I click on the buying menu option
		Then A new page with a grid containing the products should appear      

  @successfulProductAddition
  Scenario: Add successfully products in the website cart
    Given I click on the following product with its details
    	|product 			|size    |colour |quantity 	|
    	|Abominable Hoodie 	|XS 	 |Blue   |2			|	
    When I add the product items to the cart 
    Then I'll be able to verify if the cart is updated with my products
	