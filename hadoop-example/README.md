
## Description
The folder contains simple hadoop MapReduce implementation for counting words of txt files.

## How to run

1. **Install Hadoop locally**
- have docker and docker-compose installed
- clone docker-hadoop repository `git clone https://github.com/big-data-europe/docker-hadoop.git`
- run `docker-compose up -d` inside pulled docker-hadoop repo folder. It will create all the needed containers (like namenode, datanode)
- open in browser `http://localhost:9870/` to check if hadoop server is up

2. **Move sample text file into HDFS and run MapReducer jar**
- run `docker exec -it namenode bash` and login into namenode
  - run `cd /tmp` and create folder `mkdir input`
- open another terminal and 
  - run `cp samples/text.txt e1020502778c:/tmp/input`, this will copy sample file into the container
  - run `./gradlew clean jar`
  - run `docker cp build/libs/hadoop-example-1.0-SNAPSHOT.jar e1020502778c:/tmp`, this will copy our mapReducer implementation into the container
-  get back to the container bash terminal
  - run `hdfs dfs -put .* input`, this will put sample file into HDFS
  - run `hadoop jar hadoop-example-1.0-SNAPSHOT.jar org.stevi.hadoop.Main input/input/text.txt output`, it will start map reduce jar and process the file and write response into the output folder
  - run `hdfs dfs -cat output/part-r-00000` to check results