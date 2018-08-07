# json-comparator
Currently, the json comparators available over the web lack efficiency in the handling of differences in the hierarchy, data types and complex nesting. Also, there is a dire need for systematic logging of differences. This library will solve all the problems

In order to use the utility, just add the following dependency in your pom.xml

<dependencies>
   <dependency>
      <groupId>com.github.umeshsp</groupId>
      <artifactId>json-comparator</artifactId>
      <version>1.3</version>
   </dependency>
</dependencies>

And wherever you want to compare to JSON, just add the following code statement  

CompareJson.execute(jsonString1, jsonString2, true);
Here, jsonString1 and jsonString2 and the JSON Strings to be compared. true, signifies that the differences logs which are printed are to be updated to the existing file or other wise. 

Output:
A assertion_exceptions.log file with all the differences will be logged to your file system

Sample Result:
-----------------
Differences START
-----------------
map."query": expected: "what is your name", actual: "what is ur name"

-----------------
Differences   END
-----------------
