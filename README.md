# Assignment
The goal is to create a small automation project to test the “Sign in” feature of Symphony. 
It consists in testing 3 pages: Sign in, Sign up, and ResetPassword.
URL: https://my.symphony.com

# Before you start
To build you will need Git and JDK 8 or later. Be sure that your JAVA_HOME environment variable points to the jdk1.8+ folder extracted from the JDK download.

# To execute test:
 - To execute one test: mvn -Dtest=SignInTest test
 - To execute a test with different browsers: mvn -Dbrowser=firefox test
		-Dbrowser=firefox
		-Dbrowser=chrome
		-Dbrowser=ie


# To generate test report:
 - To generate the report: mvn site surefire-report:report
 - The report directery is :\target\site\surefire-report.html
