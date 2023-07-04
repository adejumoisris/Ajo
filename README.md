# Ajo
Ajo is a communal saving platform where you can save money with a group of like-minded, anytime any day

The application will be hosted on the AWS cloud and deployed with three-tier architecture. With this approach, we would achieve scalability, reliability, and maintainability. The three-tier architecture consists of three layers: 
* Presentation layer
* Application layer 
* Data layer

#### 1. Presentation Layer:
The presentation layer is responsible for user interaction and rendering the user interface. It includes web servers, load balancers (LB), and content delivery networks (CDNs). Both the LB and CDN are not set up at this time. Here's how we deploy this layer on AWS:
* Use Amazon EC2 instances as the web servers. Ubuntu is the base OS for all our servers and Apache2 serves the webpages. 
* Utilize an Application Load Balancer or Network Load Balancer to distribute incoming traffic across multiple instances for high availability and scalability. **(Future task)**
* Consider using Amazon CloudFront as a CDN to cache and deliver static content, reducing latency for users. **(Future task)**

#### 2. Application Layer:
The application layer contains the core business logic and handles user requests from the presentation layer. This layer  includes the application servers and JavaSpring boot core logic is deployed on the server. To deploy the application layer on AWS, the following services are put into consideration:
* We use Amazon EC2 instances to host the application servers.
* Integrate Amazon RDS (MySQL) for your relational database needs to the application server.
* Utilize AWS ElastiCache for caching frequently accessed data to improve application performance. **(Future task)**

#### 3. Data Layer:
The data layer stores and manages the application's data. The app uses MySQL as the relational database and would use Amazon S3 for object storage and file hosting
