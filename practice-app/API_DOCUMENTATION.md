## Base URL = 35.166.169.167:8080

# Getting Articles
Filters by country or/and category and returns articles from last 10. If no filter is defined, returns last 10 articles.

**URL :** `/news&articles/articles`<br>
**METHOD :** `GET`<br>
**AUTHORIZATION :** `-`<br>
**PARAMETERS :** `?country={country name}&category={category name}`
### RESPONSE
Returned JSON Object from request `BASE_URL/news&articles/articles?country=Switzerland`

    {"status":200,"news":[{"title":"Swiss April Inflation Rate Matches Estimates","description":"Switzerland's annual inflation rate came in at 0.7 percent in April 2019, unchanged from the previous month and in line with market expectations. Health and housing prices increased at softer pace while transport inflation accelerated."}]}

# Getting News
Filters by country or/and category and returns news from last 10. If no filter is defined, returns last 10 news

**URL :** `/news&articles/news`<br>
**METHOD :** `GET`<br>
**AUTHORIZATION :** `-`<br>
**PARAMETERS :** `?country={country name}&category={category name}` 
### RESPONSE
Returned JSON Object from request `BASE_URL/news&articles/news?category=Inflation Rate&country=Tunisia`

    {"status":200,"news":[{"title":"Tunisia Inflation Rate at 14-Month Low of 6.9% in April","description":"The annual inflation rate in Tunisia declined to 6.9 percent in April 2019 from 7.1 percent in the previous month. It was the lowest inflation since February 2018, as inflation slowed mainly for food and non-alcoholic beverages (6.6% vs 7.5% in March), in particular (meat (9.8% vs 14.3%), vegetables (8.6% vs 10.2); transport (9.5% vs 9.7%), and recreation & culture (4.9% vs 5.2%). Inflation was steady for both housing & utilities (at 5.3%) and furnishings (at 9.1%). On the other hand, cost rose faster for clothing & footwear (9.0% vs 8.9%); restaurants & hotels (10.2% vs 8.8%). On a monthly basis, consumer prices went up 0.8 percent after increasing 0.4 percent in the previous month."}]}





## API THAT ALLOWS GETTING CURRENCY RATES

 
### GET REQUEST
It simply returns the html page that lists the most used 6 currency rates(TRY, USD, GBP, CAD, SEK, CHF).

**URL :** `/currencies`<br>
**METHOD :** `GET`<br>
**AUTHORIZATION :** `-`<br>


Returned HTML page of currencies  `35.166.169.167:8080/currencies`

    {<!DOCTYPE html>
     <html lang="en">
         <head>
             <meta charset="UTF-8">
             <link rel="stylesheet" type="text/css" href="./style3.css">
             <link rel="stylesheet" type="text/css" href="./style2.css">
             <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
             <title>Currencies</title>
         </head>
         <body>
             <!-- NAVIGATION BAR STARTS -->
             <ul>
                 <li>
                     <a href="homepage.html">Home</a>
                 </li>
                 <li>
                     <a href="article.html">Articles</a>
                 </li>
                 <li class="dropdown">
                     <a href="javascript:void(0)" class="dropbtn">Trading Equipment</a>
                     <div class="dropdown-content">
                         <a href="currencies.html">Currencies</a>
                         <a href="cryptocurrencies.html">Cryptocurrencies</a>
                     </div>
                     <li>
                         <a href="news.html">News</a>
                     </li>
                     <li style="float:right">
                         <a href="about.html">About</a>
                     </li>
                 </ul>
                 <!-- NAVIGATION BAR ENDS -->
                 <h1>
                     <span class="yellow">Currencies(Base: EUR)</span>
                 </h1>
                 <table class="container">
                     <thead>
                         <tr>
                             <th>
                                 <h1>Currency Name</h1>
                             </th>
                             <th>
                                 <h1>Exchange Rate</h1>
                             </th>
          \
                         </tr>
                     </thead>
                     <tbody>
                         <tr>
                             <td>TRY</td>
                             <td id="TRY_ER"></td>
                         </tr>
                         <tr>
                             <td>USD</td>
                             <td id="USD_ER"></td>
                         </tr>
                         <tr>
                             <td>GBP</td>
                             <td id="GBP_ER"></td>
                         </tr>
                         <tr>
                             <td>CAD</td>
                             <td id="CAD_ER"></td>
                         </tr>
                         <tr>
                             <td>SEK</td>
                             <td id="SEK_ER"></td>
                         </tr>
                         <tr>
                             <td>CHF</td>
                             <td id="CHF_ER"></td>
                         </tr>
                     </tbody>
                 </table>
                 <script>
         $(document).ready(function(){
             console.log("dsadas")
             $.post("/currencies",
                 {
                 },
                 function (data, status) {
                     var curr = JSON.parse(data);
                     console.log(curr)
                     $("#TRY_ER").text(curr.rates.TRY);
                     $("#USD_ER").text(curr.rates.USD);
                     $("#GBP_ER").text(curr.rates.GBP);
                     $("#CAD_ER").text(curr.rates.CAD);
                     $("#SEK_ER").text(curr.rates.SEK);
                     $("#CHF_ER").text(curr.rates.CHF);
                 });
         });
     </script>
             </body>
         </html>}

### Getting Currency Rates (POST REQUEST)
Returns real-time exchange rate data for all available currencies. 

**URL :** `/currencies`<br>
**METHOD :** `POST`<br>
**AUTHORIZATION :** `-`<br>

### RESPONSE
Returned JSON Object from request POST`localhost:8080/currencies

    {"success":true,"timestamp":1557682325,"base":"EUR","date":"2019-05-12","rates":{"AED":4.127029,"AFN":87.917805,"ALL":123.372846,"AMD":541.742872,"ANG":2.106548,"AOA":365.305877,"ARS":50.349688,"AUD":1.604883,"AWG":2.022392,"AZN":1.915665,"BAM":1.955994,"BBD":2.248788,"BDT":94.785016,"BGN":1.955656,"BHD":0.42368,"BIF":2062.840225,"BMD":1.123551,"BND":1.517637,"BOB":7.763347,"BRL":4.446117,"BSD":1.123383,"BTC":0.000177,"BTN":78.672405,"BWP":11.977191,"BYN":2.358728,"BYR":22021.605892,"BZD":2.264631,"CAD":1.507975,"CDF":1837.00591,"CHF":1.136697,"CLF":0.027933,"CLP":770.756637,"CNY":7.667225,"COP":3686.147174,"CRC":668.490242,"CUC":1.123551,"CUP":29.77411,"CVE":110.595088,"CZK":25.725501,"DJF":199.677422,"DKK":7.467235,"DOP":56.947252,"DZD":134.196812,"EGP":19.234754,"ERN":16.853376,"ETB":32.588607,"EUR":1,"FJD":2.422882,"FKP":0.862781,"GBP":0.864539,"GEL":3.084144,"GGP":0.864496,"GHS":5.80318,"GIP":0.862781,"GMD":55.733467,"GNF":10364.761224,"GTQ":8.606066,"GYD":234.777425,"HKD":8.818474,"HNL":27.752098,"HRK":7.390694,"HTG":98.260744,"HUF":323.144615,"IDR":16088.693141,"ILS":3.999787,"IMP":0.864496,"INR":78.572758,"IQD":1337.026072,"IRR":47307.12856,"ISK":136.803852,"JEP":0.864496,"JMD":153.42106,"JOD":0.796646,"JPY":123.528872,"KES":113.6019,"KGS":78.47849,"KHR":4556.000426,"KMF":492.230006,"KPW":1011.777925,"KRW":1320.397132,"KWD":0.341676,"KYD":0.936283,"KZT":427.017232,"LAK":9690.629918,"LBP":1694.97717,"LKR":198.126674,"LRD":198.138251,"LSL":16.13442,"LTL":3.317555,"LVL":0.679625,"LYD":1.561816,"MAD":10.825082,"MDL":20.131809,"MGA":4044.784449,"MKD":61.716113,"MMK":1721.73394,"MNT":2955.537626,"MOP":9.081496,"MRO":401.107126,"MUR":39.219244,"MVR":17.302643,"MWK":811.883772,"MXN":21.46354,"MYR":4.672854,"MZN":71.766844,"NAD":16.134177,"NGN":404.478343,"NIO":37.245937,"NOK":9.788944,"NPR":125.56248,"NZD":1.703864,"OMR":0.43264,"PAB":1.123495,"PEN":3.724599,"PGK":3.776184,"PHP":58.643757,"PKR":159.122986,"PLN":4.296795,"PYG":7150.83174,"QAR":4.090871,"RON":4.760147,"RSD":117.972904,"RUB":73.163078,"RWF":1016.813946,"SAR":4.213711,"SBD":9.197671,"SCR":15.367905,"SDG":50.692952,"SEK":10.816895,"SGD":1.531288,"SHP":1.484103,"SLL":9943.429442,"SOS":654.466189,"SRD":8.379442,"STD":23651.429,"SVC":9.830906,"SYP":578.629233,"SZL":16.134204,"THB":35.453098,"TJS":10.597111,"TMT":3.943665,"TND":3.372119,"TOP":2.568495,"TRY":6.723223,"TTD":7.614476,"TWD":34.774996,"TZS":2584.63099,"UAH":29.417961,"UGX":4241.237147,"USD":1.123551,"UYU":39.39194,"UZS":9499.626577,"VEF":11.221469,"VND":26232.114467,"VUV":127.760523,"WST":3.001761,"XAF":656.164825,"XAG":0.076008,"XAU":0.000874,"XCD":3.036453,"XDR":0.810655,"XOF":656.154055,"XPF":119.938978,"YER":281.227461,"ZAR":15.909268,"ZMK":10113.309813,"ZMW":14.438015,"ZWL":362.182398}}