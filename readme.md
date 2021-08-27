# Best Sales Deals
Sample project created to find the best deals for hotels from csv file.

# Problems Found

- First, I am with a important deliver and was dificult work a lot hours in this solution, I focused on result and performance, dont focused too much in design.


- There is a folder called `build` inside the project with a package already compiled and 3 csv files created for test.
  

- About the csv file, I think that is important have a column to define a rebate type, for example percentage or currency. But to keep the proposal format, I will check in other way, but for me, is not safe.


- I think that could exist more than one best hotel deal, but I will consider just one;


- I think that the best hotel deal definition is not clear, but I implemented calculating the discount and analising the best final price after discount;


- Is not clear the understanding about PCT type, than I aplied the straight discount.


- I setup the csv file delimited by `";"` because I think is safer when work with currency;


- Analising the performance, this solution processed more than 1.000.000 records, in about 4 seconds. I think that is satisfactory.

```json
Code Type		Records				Time Mili	
Simple While		3				60				
Simple While		90				72
Simple While		5.040				205
Simple While		10.080				244
Simple While		503.901				2461
Simple While		1.007.801			4393
```
