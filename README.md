# Text Search Engine 
The exercise is to write a command line driven text search engine, usage being:

# Task
 -This should read all the text files in the given directory, building an in memory representation of the
  files and their contents, and then give a command prompt at which interactive searches can be
  performed.
  
-The search should take the words given on the command prompt and return a list of the top 10
 (max) matching filenames in rank order, giving the rank score against each match.  
 
 # Ranking
 1. The rank score must be 100% if a file contains all the words
 2. It must be 0% if it contains none of the words
 3. It should be between 0 and 100 if it contains only some of the words ­ but the exact ranking
 formula is up to you to choose and implement
 
# Tools Used

Spring Boot 2.1.4

Java 11.

Lombok.


## 

Import the project to your favorite IDE. Update the project dependencies by maven and find the SchibstedApplication.java 
then right click and run the project. It will start as a SpringBoot console application. 

or 

`java ­jar schibsted-0.0.1-SNAPSHOT.jar /..the path which will read files`   

# Documentation

The project has 2 different `.txt` files under the `/resources` path. The project test 
created due to these files.

I decided to do with Spring Boot Console Application because spring boot is so flexible to develop with. 
I have also created custom validation exception hadler but I did not put any specification to the custom exceptions.
 

# Usage of application 

when the app is running it reads the document directly from the path which is entered. Then the app
is expecting `search text` to find the percentage of including words and the name of the file. when the search parameter entered. The result will be the output. 


