
build:
	mvn clean package

clean:
	mvn verify clean

tree:
	mvn dependency:tree

java-start:
	java -jar target/basicStory-23.9.2-SNAPSHOT-jar-with-dependencies.jar

start:
	mvn exec:java

exec:
	mvn exec:exec

docker:
	docker build -t basicstory:local .

run-docker:
	docker run -it --rm basicstory:local