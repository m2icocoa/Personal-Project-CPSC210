# BTS Concert Tickets Purchase System

This is a system where you can purchase concert tickets for the most popular K-pop artist, BTS. 
The system lets you select seats from the *UBC Thunderbird Arena* seating chart.
The price of tickets varies on how close to the stage.

The users of this system will be anyone who wants to go to a BTS concert held at UBC.

I have been a huge fan of BTS.
However, as they gained world popularity in recent years, 
it has been extremely difficult to get tickets for their concert.
The ticket-selling website sometimes crushes in the middle of buying tickets 
because too many people access it at the same time.
Moreover, they no longer hold a concert in small arenas like the one at UBC, 
but only in huge stadiums.
Thus, I decided to create a system where we buy concert tickets hoping that BTS will come to UBC to perform one day.


# User Stories
- As a user, I want to be able to select a ticket.
- As a user, I want to be able to remove a ticket from my cart.
- As a user, I want to be able to know the price of the selected seat depending on how close it is to the stage.
- As a user, I want to be able to put a ticket to my cart.
- As a user, I want to be able to check the number of tickets in my cart.
- As a user, I want to be able to check the total price of tickets in my cart. 
- As a user, when I select the quit option from the application menu, 
I want to be reminded to save my cart to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my cart from file.


# Instructions for Grader
- I can generate the first required event by filling out the seat information I desire 
and clicking the button labelled "Add". 
- I can generate the second required event by selecting the ticket I want to remove and 
clicking the button labelled "Delete".
- I can locate my visual component by either running the application or adding a ticket. 
- I can save the state of my application by clicking the button labelled "Save". Moreover, when I click the button 
labelled "Quit" or the X button on the right corner, the message pops up to give me options to save or not.  
- I can reload the state of my application by clicking the button labelled "Load".


# Phase 4: Task 2
The events logged are "Add a ticket to cart", "Remove a ticket from a cart", and "Update a ticket".
An example of the events logged:

Saved Mii's cart to ./data/my-cart.json 
Fri Dec 02 17:47:48 PST 2022
Ticket added to cart.Fri Dec 02 17:47:54 PST 2022
Ticket added to cart.Fri Dec 02 17:47:58 PST 2022
Ticket added to cart.Fri Dec 02 17:49:13 PST 2022
Ticket updated.Fri Dec 02 17:49:44 PST 2022
Ticket updated.Fri Dec 02 17:49:52 PST 2022
Ticket removed from cart.Saved Mii's cart to ./data/my-cart.json


# Phase 4: Task 3
If I have time to do more I would make a restriction on the maximum number of tickets each customer can purchase.
In addition, I would reflect the seating chart attached to my system.
Since the actual seating chart does not have the constant number of rows in each section, 
it does not precisely correspond to what I implemented in this project (constant row and number of seats).
In this way, users would be able to easily select a seat while looking at the seating chart.
  
# Reference
Some methods in this project are cited from:
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
https://www.youtube.com/watch?v=22MBsRYuM4Q
https://1bestcsharp.blogspot.com/2015/05/java-jtable-add-delete-update-row.html
https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

