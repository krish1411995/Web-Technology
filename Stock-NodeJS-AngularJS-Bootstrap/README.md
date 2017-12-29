# Description
A webpage that allows users to search for stock informa;on using the Alpha Vantage API and display the results on the same page below the form.<br>
The difference being, in this work we will create a Node.js script to return JSON forma]ed data to the front-end. The client will parse the JSON data and render it in a nicer-looking responsive UI, using the Bootstrap toolkit.
A user will first open a page as shown below, where he/she can enter the stock symbol. A quote on a matched stock symbol can be performed.<br>
Once the user has entered some characters in the edit box and selected a matching result from the autocomplete list, he would click on Get Quote, at which point validation must be done to check that the entered data is valid.
Once the valida;on is successful, the JQuery/Angular func)on ajax() is executed to start an asynchronous transaction with a Node.js script running on our AWS, and passing the search form data as parameters of the transaction.<br>

# SreenShots
![screen shot 2017-12-28 at 11 30 40 pm](https://user-images.githubusercontent.com/20076221/34431993-4954d822-ec28-11e7-8f63-07f6b8312f99.png)<br>
![screen shot 2017-12-28 at 11 31 20 pm](https://user-images.githubusercontent.com/20076221/34431998-514c3836-ec28-11e7-958f-6cc7bdd06dc8.png)<br>
![screen shot 2017-12-28 at 11 31 48 pm](https://user-images.githubusercontent.com/20076221/34432002-5ce4273a-ec28-11e7-8775-543eb7e3bf2c.png)<br>
![screen shot 2017-12-28 at 11 32 15 pm](https://user-images.githubusercontent.com/20076221/34432006-6290ae06-ec28-11e7-9992-2f13c879e4be.png)<br>
![screen shot 2017-12-28 at 11 33 33 pm](https://user-images.githubusercontent.com/20076221/34432013-6a84df42-ec28-11e7-899c-aabb832f6641.png)<br>
![screen shot 2017-12-28 at 11 34 22 pm](https://user-images.githubusercontent.com/20076221/34432015-6e6e19fc-ec28-11e7-8048-4c2d4c1fb244.png)<br>

