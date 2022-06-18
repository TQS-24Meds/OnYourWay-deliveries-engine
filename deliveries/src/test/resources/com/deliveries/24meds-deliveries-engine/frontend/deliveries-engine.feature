Feature: Deliveries Engine

Scenario: Consulting statistics
    When an admin wants to check the revenues lately to check if the company is growing
    Then she opens the dashboard 
    And opens New Orders and see the "recent incoming"


Scenario: Consulting information about a rider
    When an admin wants to check on a specific rider
     she opens "Riders" and searches for "Joan Weah"
        And clicks on the "plus" button
        And see how many rides he has made, his previous rides and his details
        
    