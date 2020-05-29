This is a small Spring Boot app that I'm using to learn aws cdk.
It was originally created with the Spring initializr: https://start.spring.io/

You can find the cdk code for running the dockerized version of the
app in AWS Fargate in this project: https://github.com/jorgen99/aws-cdk-demo

To build and test:

    mvn clean install

Edit dockerimage.config and push to your docker repo

    ./dockerBuildAndPush
