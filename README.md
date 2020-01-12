# VIIVIIIIX
<h3>Project VIIVIIIIX(789)</h3>
This project is a PoC based on collecting user information on a webpage.

Following steps are followed:<br/>
-Collect user actions webpage.<br/>
-Send it to kafka microservice.<br/>
-Push data to an arbitrary kafka topic.<br/>
-Read the data from the kafka topic.<br/>
-Save it to MongoDB.

<h3>Project Contents</h3>
Project consists of 2 microservices and one script.<br/>
-Microservice which interacts with the kafka brokers to create, delete, filter topics.<br/>
-Microservice to produce, consume, and store events to MongoDB.<br/>
-Javascript script to capture user actions.<br/>
<br/> <br/>
<p>Input is always welcomed.</p>
