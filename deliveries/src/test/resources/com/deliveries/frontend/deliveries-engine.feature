Feature: Deliveries Engine

    Scenario: Consulting statistics
        Given an admin wants to check the revenues lately to check if the company is growing in "http://localhost:8080/"
        When he opens "dashboard" tab
        Then he sees how many "New Orders" there are

        Given an admin wants to check the revenues lately to check if the company is growing in "http://localhost:8080/"
        When he opens "dashboard" tab
        Then he sees how many "Clients Review" there are

        Given an admin wants to check the revenues lately to check if the company is growing in "http://localhost:8080/"
        When he opens "dashboard" tab
        Then he sees how many "User Registrations" there are

        Given an admin wants to check the revenues lately to check if the company is growing in "http://localhost:8080/"
        When he opens "dashboard" tab
        Then he sees how many "New Riders" there are

    Scenario: Search for a rider
        Given an admin that wants to consult information about a rider in "http://localhost:8080/"
        When he opens "Riders" tab
        And he searches for "Joan Weah"
        And clicks on the "plus" button
        Then he sees details of "Joan Weah"

    Scenario: Consulting information about a rider
        Given an admin that wants to consult information about a rider in "http://localhost:8080/"
        When he opens "Riders" tab
        And clicks on the "plus" button
        Then he sees details of the rider