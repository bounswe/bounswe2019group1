## Base URL = 35.166.169.167:8080

# Getting Articles
Filters by country or/and category and returns articles from last 10. If no filter is defined, returns last 10 articles.

**URL :** `/news&articles/articles`<br>
**METHOD :** `GET`<br>
**AUTHORIZATION :** `-`<br>
**PARAMETERS :** `?country={country name}&category={category name}`
### RESPONSE
Returned JSON Object from request `localhost:8080/news&articles/articles?country=Switzerland`

    {"status":200,"news":[{"title":"Swiss April Inflation Rate Matches Estimates","description":"Switzerland's annual inflation rate came in at 0.7 percent in April 2019, unchanged from the previous month and in line with market expectations. Health and housing prices increased at softer pace while transport inflation accelerated."}]}

# Getting News
Filters by country or/and category and returns news from last 10. If no filter is defined, returns last 10 news

**URL :** `/news&articles/news`<br>
**METHOD :** `GET`<br>
**AUTHORIZATION :** `-`<br>
**PARAMETERS :** `?country={country name}&category={category name}` 
### RESPONSE
Returned JSON Object from request `localhost:8080/news&articles/news?category=Inflation Rate&country=Tunisia`

    {"status":200,"news":[{"title":"Tunisia Inflation Rate at 14-Month Low of 6.9% in April","description":"The annual inflation rate in Tunisia declined to 6.9 percent in April 2019 from 7.1 percent in the previous month. It was the lowest inflation since February 2018, as inflation slowed mainly for food and non-alcoholic beverages (6.6% vs 7.5% in March), in particular (meat (9.8% vs 14.3%), vegetables (8.6% vs 10.2); transport (9.5% vs 9.7%), and recreation & culture (4.9% vs 5.2%). Inflation was steady for both housing & utilities (at 5.3%) and furnishings (at 9.1%). On the other hand, cost rose faster for clothing & footwear (9.0% vs 8.9%); restaurants & hotels (10.2% vs 8.8%). On a monthly basis, consumer prices went up 0.8 percent after increasing 0.4 percent in the previous month."}]}
    
# Getting Tickers
The ticker is a high level overview of the state of the market. It shows you the current best bid and ask, as well as the last trade price. It also includes information such as daily volume and how much the price has moved over the last day.

Parameter is the symbol of trading pair such as BTCUSD or ETHUSD or BTCEUR. If there is no parameter, it returns ALL tickers. <br>
**URL :** `/bitfinex/ticker`<br>
**METHOD :** `GET`<br>
**AUTHORIZATION :** `-`<br>
**PARAMETERS :** `?word={trading pair symbol}` 
### RESPONSE
Returned JSON Object from request `Base URL/bitfinex/ticker?word=BTCUSD` <br>

[["tBTCUSD",7024.7,16.88650808,7024.8,30.866369680000005,99.8,0.0144,7024.7,40437.54617372,7448,6858]]

# Getting Stats
Various statistics about the requested pair.

Parameter is the symbol of trading pair such as BTCUSD or ETHUSD or BTCEUR. If there is no parameter, it returns ALL stats. <br>
**URL :** `/bitfinex/stats`<br>
**METHOD :** `GET`<br>
**AUTHORIZATION :** `-`<br>
**PARAMETERS :** `?word={trading pair symbol}` 
### RESPONSE
Returned JSON Object from request `Base URL/bitfinex/stats?word=ETHEUR` <br>
[{"period":1,"volume":"16812.35195886"},{"period":7,"volume":"52765.72808059"},{"period":30,"volume":"256120.16530358"}]
