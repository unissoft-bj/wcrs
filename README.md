# wcrs

for development.

1. go to the wcrs root directory. wcrs/
2. it needs java 8. set up JAVA_HOME to java 8) or run the set-env.bat in wms/. (you need edit it for your java 8 directory). 
3. then run gradlew.bat bootRun or gradlew bootRun (in Linux)
4. the server will be up ready

to build:
gradlew.bat clean build  or ./gradlew clean build

to build without test:
gradlew.bat clean build -x test

#For remote debugging: 
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar build/libs/wms.war 


#publish shared jar
spring boot rest service sample

./gradlew clean domainLibJar  --> build domainLibJar to be shared in other projects.

./gradlew clean domainLibJar publishToMavenLocal  --> publish domainLibJar to local maven repository.
