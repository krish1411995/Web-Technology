# Description
Developed an Android Mobile application, which allows users to search for stock information, save some stock symbols as favorites, and post to the Facebook timeline. You should reuse the backend service (node.js script).<br>
The main “scene” of this app is like where the user can enter the stock ticker symbol and select from a list of matching stock symbols using “autocomplete.” A “stock quote” on a matched stock symbol can be retrieved.<br>
Once the user has entered some characters in the edit box and selected a matching result from the autocomplete list, he/she would click on Get Quote, at which point validation must be done to check that the entered data is not empty.<br>
Once the validation is successful, we would get the stock details using our node.js script hosted on Amazon Web Services, which would return the result in JSON format. We would display the stock details in a ListView component in the ‘Current’ tab.<br> Furthermore, our node.js script would be responsible for rendering the HighCharts in the ‘Current’ and ‘Historical’ tabs and also rending the news articles in the ‘News’ tab.<br>

# Implementation Details
5.1 Search Form<br>
You must replicate the Search Form.<br>
The interface consists of the following:<br>
• An ‘AutoCompleteTextView’ component allowing the user to enter the company
name or symbol.<br>
• Two TextViews implemented as buttons for interaction in the Search Form.<br>
• A button ‘Clear’ to clear the ‘AutoCompleteTextView’ component.<br>
• A button ‘Get Quote to get the quote, after validation.<br>
• A Switch implemented as an AutoRefresh element<br>
• Next to the switch, an Android icon to refresh on-click.<br>
• A Spinner listing options to sort the list.<br>
• A Spinner listing options to order the list.<br>
• The Favorite ListView showing the list of favorite stocks.<br>
• The Favorite List starts with an empty favorite list.<br>
The form has two buttons:<br>
a) Get Quote: Validations are first performed, when the button is clicked. If the validations are successful, then the stock details would be fetched from the server (either hosted on AWS). However, if the validations are unsuccessful, appropriate messages would be displayed and no further requests would be made to the server.<br>
b) Clear: This button would clear the ‘AutoCompleteTextView’ and clear any validations error, if present.<br>
5.1.1 AutoComplete<br>
The user can enter the stock name or symbol in the text view to get the stock information from our PHP script. Based on the user input, the AutoComplete would display the all the matching companies and symbols by making a HTTP request. The auto-complete dropdown is shown only when the user has typed in at least one character and the maximum number of results displayed in the auto-complete dropdown is 5. This needs to be implemented using AutoCompleteTextView.<br>
To get the data used for auto-complete suggestions, you need to make http requests to your Node.js script which is in AWS.<br>
If the user selects one of the results from the auto-complete dropdown, the content of the result (symbol with the company name) should be copied to the input field and the autocomplete dropdown then disappears.<br>
If the user taps on an area other than the auto-complete form, the dropdown should be hidden.<br>
![screen shot 2017-12-29 at 12 34 23 am](https://user-images.githubusercontent.com/20076221/34432867-14748d98-ec30-11e7-981a-d2437c266122.png)<br>
![screen shot 2017-12-29 at 12 34 10 am](https://user-images.githubusercontent.com/20076221/34432871-18ac37f8-ec30-11e7-9bd5-46179838d8b1.png)<br>
5.1.2 Validations<br>
The validation for empty symbol entry needs to be implemented. If the user does not enter anything in the ‘UITextField’ or just enters some empty spaces, when he presses the Get Quote button an appropriate message to indicate the error should be displayed.<br>
5.1.3 Get Quote Execution<br>
Once the validation is successful, you should execute an HTTP request to the Node.js script which is hosted on AWS, and then navigate to the details page about the requested stock.<br>
5.2 Favorite List<br>
The Favorites list interface consists of the following:<br>
• An Automatic Refresh switch, labeled AutoRefresh<br>
• A Refresh button<br>
• Two “Spinners” controlling the order of the list<br>
• The Favorite “Custom ListView” showing a list of favorite stocks<br>
The stocks in the user’s Favorites list would be displayed in a list.<br>
![screen shot 2017-12-29 at 12 36 38 am](https://user-images.githubusercontent.com/20076221/34432917-5eb5bb70-ec30-11e7-888f-b481dd08ab6d.png)<br>
Here are some important points about this feature:<br>
• Display symbol, stock price and change (change percent) in each row.<br>
• Sort by Default/Symbol/Price/Change/Change (%) in Ascending/Descending order<br>
• See Homework 8 about the behavior of the AutoRefresh switch and refresh button<br>
• Whenever the favorite list appears or re-appears, the price and change (change percent) data need to be updated. But the symbols should always be stored in “local device” storage.<br>
• Display an ‘activity indicator’ while loading data from server.<br>
• Display a proper error message if failing to update one or more stocks in the favorite list.<br>
• Select a row to search that stock and navigate to the stock detail page.<br>
• Long press a row and display a Context Menu to Delete list item. Then user can then remove that stock from the Favorites list.<br>
![screen shot 2017-12-29 at 1 05 16 am](https://user-images.githubusercontent.com/20076221/34433468-68e7dcb4-ec34-11e7-9a28-3f19775afd06.png)<br>
![screen shot 2017-12-29 at 1 05 24 am](https://user-images.githubusercontent.com/20076221/34433469-6bbedbcc-ec34-11e7-85cc-b04a31a0d1cc.png)<br>
5.3 Stock Details<br>
When the user clicks the Get Quote button, your app should display a loading image before you are ready to show the stock details view. The Stock Details section should be designed as per Figure 9.<br>
The stock detail section should have 3 views:<br> • Current Stock
• Historical Charts <br>• News Feeds<br>
You can use a “Tabbed Activity” to navigate between 3 views above. The back button in the header should navigate back to the Search Form.<br>
The Stock Details would be starting with the ‘Current’ view as loaded by default. Furthermore, the stock details would have a list showing all the stock values. The list of the items in the stock details would be implemented using a ‘ListView’.<br> The following stock values should be displayed: Stock Symbol, Last Price, Change, Timestamp, Open, Close, Day’s Range, Volume.<br> The meaning of these values is the same as in Homework 8.<br>
![screen shot 2017-12-29 at 1 08 45 am](https://user-images.githubusercontent.com/20076221/34433528-dc66ef0e-ec34-11e7-9f94-8b8ece7e4aa5.png)<br>
![screen shot 2017-12-29 at 1 08 30 am](https://user-images.githubusercontent.com/20076221/34433531-e1a9aae2-ec34-11e7-9899-b9a84e092dc8.png)<br>
Below the list of stock details, you need to show the indicator choices and the high chart image, as shown in Figure 10. The user will need to scroll down to see these areas. A Spinner and a TextView/Button labeled Change are used to choose another indicator and change the high chart image. Here are some points:<br>
• Include all the indicators and the chart is about the price/volume at the beginning.<br>
• The Change button is only enabled if a different indicator is selected.<br>
• You should use a “WebView” to display the chart and reuse some of your HTML and JavaScript code from previous homeworks. But you should figure out a way to communicate between your Android code and the JS code asynchronously. It’s NOT allowed to block the app while waiting for the chart to be shown in the WebView.<br>
• Whenever the chart in the WebView is in a loading state, you should hide the previous chart (if there’s any) and display a loading icon.<br>
• Display a proper message if there is any failure in retrieving a chart.<br>
![screen shot 2017-12-29 at 1 11 05 am](https://user-images.githubusercontent.com/20076221/34433575-30ad1034-ec35-11e7-8005-395f102a8dc6.png)<br>
![screen shot 2017-12-29 at 1 10 57 am](https://user-images.githubusercontent.com/20076221/34433578-33cc8dd0-ec35-11e7-8925-01a81cc4fe5b.png)<br>
5.4 Historical Charts<br>
![screen shot 2017-12-29 at 1 12 46 am](https://user-images.githubusercontent.com/20076221/34433634-74155f84-ec35-11e7-969a-7f35c3ec0e85.png)<br>
5.5 News Feed<br>
![screen shot 2017-12-29 at 1 12 53 am](https://user-images.githubusercontent.com/20076221/34433631-7143347a-ec35-11e7-8006-d6f851f2ba12.png)<br>
5.6 Facebook Share<br>
![screen shot 2017-12-29 at 1 14 08 am](https://user-images.githubusercontent.com/20076221/34433660-9ff1d3d0-ec35-11e7-8b83-73190e45da08.png)<br>
![screen shot 2017-12-29 at 1 13 59 am](https://user-images.githubusercontent.com/20076221/34433662-a24bd13a-ec35-11e7-8756-9f6700e6798f.png)<br>
5.7 Error Share<br>

![screen shot 2017-12-29 at 1 18 35 am](https://user-images.githubusercontent.com/20076221/34433763-3e27834c-ec36-11e7-9de6-5ffea8019b0c.png)
![screen shot 2017-12-29 at 1 18 27 am](https://user-images.githubusercontent.com/20076221/34433766-408d139a-ec36-11e7-8421-2e044ec69d89.png)
![screen shot 2017-12-29 at 1 18 41 am](https://user-images.githubusercontent.com/20076221/34433767-43107d8c-ec36-11e7-808f-ccf6ff6d44b0.png)


