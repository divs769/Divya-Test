It is an maven project. Its maven version 4.0
Uses Java 8

Steps to run

Install maven add M2_HOME and MAVEN_HOME

Go to the folder where it is unzipped
Run
mvn compile
mvn test
mvn exec:java -Dexec.mainClass="com.zopa.LowRateQuoteSystem" -Dexec.args="C:\Users\lmer569\divyatest\src\main\resources\MarketDataForExercise.csv 1000"