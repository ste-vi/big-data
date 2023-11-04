
## Description
The folder contains simple spark program for counting words of txt files.

## How to run

1. **Install Spark locally**
- have docker installed
- pull spark docker image `docker pull apache/spark`
- run spark container `docker run -it -d -p 4040:4040 --name spark apache/spark`
- enter container `docker exec -it spark bash `

2. **Move sample text file into HDFS and run MapReducer jar**
- open another terminal
  - run `docker cp samples/text.txt spark:/opt/spark/bin`, this will copy sample file into the container
  - run `./gradlew clean jar`
  - run `docker cp build/libs/spark-example-1.0-SNAPSHOT.jar spark:/opt/spark/bin`, this will copy our mapReducer implementation into the container
- get back to the container bash terminal
  - run `./spark-submit --class org.stevi.spark.Main --master local spark-example-1.0-SNAPSHOT.jar text.txt`, this start spark jar
  - check logs in bash, you should see some custom logs