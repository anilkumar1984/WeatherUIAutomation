# WeatherUIAutomation
Scenario: To Compare the Temperature of a City in UI with the Temperature from API
ProjectName: WeatherUIAutomation
FrameWork: Hybrid FrameWork
Components Used in FrameWork:
1.) Build Automation tool-POM
2.) Testing FrameWork-TestNg
3.) Design Pattern-POM
4.) Data Driven-TestData.xlsx
5.) Config.properties
6.) Data.json present under TestData folder(To pass the API key "appid" to the test)
7.) testng.xml
8.) Reports-Extent Report

Below are the Automation Tests which are placed under src/test/java folder
1.) WeatherInfoFromUITest-Get the Temperature from UI and stores it in TestData.xlsx
2.) WeatherInfoFromAPITest-Get the temerature from API which is in Kelvin and converts into Celsius  and stores it in TestData.xlsx
3.) CompareWeatherInfoTest- To compare the Temperature from UI and API 
The above test will pass only if there is an variation of (+ or - 2) degree celsius

All the pageObjects and genericTests like BaseTest are present under src/main/java
Once the execution gets completed ExtentReport will be present under Reports folder with current data and timestamp.

Currently 4 Reports are present under Reports folder
1.) Test which got passed while comparison of temperature between UI and API- ndtvWeather_06_04_2021_02_06_02
2.) Test which got failed while comparison of temperature between UI and API- 
ndtvWeather_06_04_2021_02_02_30
ndtvWeather_06_04_2021_02_20_20
3.) Test which got failed because the city is not present in the dropdown.- ndtvWeather_06_04_2021_02_07_25.

The mvn test takes two parameters which user as to pass from the cmd line
1. browser- Type of browser for execution, it supports chrome,edge and firefox
2. city- It can take any city

Command to execute the maven test is
mvn clean test -Dbrowser=<browser> -Dcity="<city>"

Example 
mvn clean test -Dbrowser=chrome -Dcity="bengaluru"
