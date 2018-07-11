# Mobiquity
## Cafe Town Web App Selenium Automation

##### Frameworks Used :
1. Java
2. Selenium
3. TestNG
4. Maven
5. Log4j

 
### Pre-requisites
##### 1. Java
```sh
1. Download JDK 1.8 and install from the below link :
[http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html]
2. Set Environment Variables if neccesary
```

##### 2. Maven
```sh
1. Download Maven(apache-maven-3.5.4-src.zip) from the following link :
[https://maven.apache.org/download.cgi]
2. Set the 'PATH' Environment variable to 'bin' folder of the downloaded maven folder
```

### Test Execution

```sh
1. Download/Clone this repository.
2. Open terminal window in the root folder of the project
3. Execute below command to run the tests from java class mentioned in CafeTownTestNGSuite.xml in chrome browser

    mvn clean verify -Dheadless=false -Dbrowser=chrome

#### Test Execution options
headless: set to true if you want to run the test headless
    mvn clean verify -Dheadless=true
browser: set the browser in which you want to run tests
    mvn clean verify -Dheadless=false -Dbrowser=firefox
suitexml: set to xml file containing  
    mvn clean verify -Dheadless=false -Dbrowser=chrome -Dsuitexml=testsuite.xml
    
```
