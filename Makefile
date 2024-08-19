
build:
	mvn clean package

tree:
	mvn dependency:tree

java-start:
	java -jar target/debugBot-23.9.2-SNAPSHOT-jar-with-dependencies.jar

start:
	mvn exec:java

exec:
	mvn exec:exec