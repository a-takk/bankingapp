# bankingapp
This is just a simple project made with Swing, MySQL and simple Java concepts, made up of a few classes: 

AddAccount, 
DepositAccount, 
DisplayAccount, 
Menu, 
RemoveAccount, 
WithdrawAccount

It inherits the Connection from the getConnection method, and assigns the primitive types from the variable class,
assigns a random number from 1000000 to 9999999 which is the users ID, and uses the String first and last to 
display the first and last name as well as the ID and balance. 

I've also used Swing to create the frame, panel, button, text fields, then use action listeners which respond when 
the user makes an action, when the user enters data then submits it in the text fields, there is an action listener 
which calls the getConnection method and either inserts, deletes or updates the users information and then displays 
it. 
