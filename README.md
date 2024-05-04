# EventHub

## Introduction

EventHub is an event booking and management website. EventHub provides a one-stop
solution for customers to search and discover various kinds of events as well as for event
organizers to post their events to gain more audience and make events more successful.
EventHub aims to revolutionize the process of discovering, registering, and organizing
events. EventHub has a minimalistic and user-friendly interface which helps customers and
event organizers to connect easily for a wide range of events.

EventHub provides a wide variety of events, whether it's a small or large community event,
workshop, concert, or cultural event. Users can see various details of the events like date,
time, location, and description, and register for events directly through the platform with just
a few clicks. EventHub also provides a login and signup feature to ensure that only
authorized users can register for the event. It also provides an email notification feature
which notifies the user about new events in their city and also provides details for their
bookings. Also, users can access their bookings through the booking history page.

For event organizers, EventHub offers a powerful platform to showcase their events and
attract a larger audience. Organizers can create event listings by providing detailed
information about their events including description, time, date, ticket price and venue
details.

In summary, EventHub is more than just a booking platform—it's a dynamic community that
fosters connections, facilitates event discovery, and enhances the overall event experience.

## Features

1. User Authentication (Login & Signup)
2. Profile Management
3. Creating Event Post
4. Event Discovery
5. Booking System
6. Booking History
7. Email Notification
8. Payment Gateway
9. Event Organizer Analytics

## Built With

- [React](https://legacy.reactjs.org/docs/getting-started.html/) - The web framework used
- [npm](https://docs.npmjs.com//) - Dependency Management
- [Tailwind](https://tailwindcss.com/) - CSS Framework
- [FontAwesome](https://fontawesome.com/docs/web/use-with/react/) - Fontawesome for icons
- [Node.js](https://nodejs.org/en) - Javascript Runtime environment
- [Express](https://expressjs.com/) - Node.js web application framework
- [MySQL](https://www.mysql.com/) - MySQL used for database
- [Axios](https://www.npmjs.com/package/axios) - Axios
- [Java](https://www.java.com/en/) - Backend Programming Language.
- [SpringBoot](https://spring.io/projects/spring-boot) - Java framework used for developing rest applications
- [Docker](https://docs.docker.com/) - Platform designed to help developers build, share, and run container applications
- [AWS](https://aws.amazon.com/) - Cloud Computing service.
- [AWS EC2](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/concepts.html) - Used for hosting backend.
- [AWS Beanstalk](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/Welcome.html) - AWS Service for application deployment.
- [AWS SNS](https://docs.aws.amazon.com/sns/latest/dg/welcome.html) - AWS Service for sending email and other types of notifications.
- [AWS CloudFront](https://aws.amazon.com/cloudfront/) - Content Delivery Network.
- [AWS S3 Bucket](https://docs.aws.amazon.com/AmazonS3/latest/userguide/Welcome.html) - Cloud Object Storage

## Deployment

### Prerequisite

- Frontend - NodeJs and ReactJS
- Backend - Java and Java SpringBoot
- AWS Account - Cloud Computing Service
- Intellij IDEA - Suggested for efficient backend development.
- VS Code - Suggested for efficient frontend development.

### Installation

#### Frontend

- Open the root project and fire the below commands to install the project dependencies and start the application.

  ```
  cd frontend
  ```

  ```
  npm install
  ```

  ```
  npm start
  ```

#### Backend

- Open the backend project folder in Intellij IDEA.

- IntelliJ IDEA setup

  - In settings find Actions on Save and enable all options except build code.

- Installing dependencies

  - Run below commands

    ```
    mvn clean
    ```

    ```
    mvn install
    ```

- To run the project
  - Make sure Mysql is running in the local system.
  - Make sure Maven is installed in the system and environment variables are set
  - To run the project find a `BackendApplication` class and run that as a Java application
  - Visit `localhost:8080` and check logs
