# Description
A webpage that allows users to search for stock informa;on using the Alpha Vantage API and display the results on the same page below the form.<br>
The difference being, in this work we will create a Node.js script to return JSON forma]ed data to the front-end. The client will parse the JSON data and render it in a nicer-looking responsive UI, using the Bootstrap toolkit.
A user will first open a page as shown below, where he/she can enter the stock symbol. A quote on a matched stock symbol can be performed.<br>
Once the user has entered some characters in the edit box and selected a matching result from the autocomplete list, he would click on Get Quote, at which point validation must be done to check that the entered data is valid.
Once the valida;on is successful, the JQuery/Angular func)on ajax() is executed to start an asynchronous transaction with a Node.js script running on our AWS, and passing the search form data as parameters of the transaction.<br>

