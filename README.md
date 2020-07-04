# Image9
Image9 is a full stack web application allowing a user to upload and download image files (similar to imgur). This application uploads/downloads images to and from a hosted AWS S3 bucket.

## Design Implementation
Front End: JavaScript and React Native for UI and Axios with webhooks to deal with http requests to back end http requests. Utilized VSCode for this portion.\
Back End: Java and Spring Boot for REST API design along with connection to S3 Bucket. Utilized Spring Tools Suite for this portion.

## Installation & Prerequisites 
This application requires two web servers to run concurrently for front end and back end.
### Java
User should have Java 8 installed with an appropriate IDE (preferrably IntelliJ, STS, Eclipse, etc).

### JavaScript
User should have node.js installed as a prerequisite.
Axios is required to make HTTP requests and communicate with the back end.
```
npm -S i axios
```

## Usage
To use the application, run both servers to start (localhost:3000 for front end, localhost:8080 for back end).
Next, select an image of your choice and drag it into the area below the circular border. Upon refreshing, page should reflect changes.
Below shows the implementation of this application after two image uploads.

## Improvement
This full stack application is a rough concept of how a user could upload and download images from a web server. To improve, everything should ideally run on one server, hosted on a dedicated platform. Cross origin should also be restricted to prevent malicious attacks. User data should also persist beyond the lifetime of the user session and should ideally be stored in a dedicated database. 

![alt text](https://i.imgur.com/hNJfUIa.png)
