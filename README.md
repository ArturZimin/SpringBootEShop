# E-SHOP Web Application
---
## Main opportunities:
- Viewing products, detailed view of characteristics
- Product filtering
- Add products to the bucket
- Formation of orders and payment by card with confirmation by sms
- Authentication and registration with activated by email
- Application protection
- Validation of input data at the front and back level
- Additional features for ADMIN and MANAGER account(addProduct(),addUser() and so on)
- Email notification of new discounts

### Tech stack:
+ JDKs\corretto-11
+ Java 11 SE
+ Spring (Web,Data,Security,Boot)
+ Spring Mail Sender
+ Thymeleaf
+ MySql db
+ Lombok
+ Flyway

### Application launch:
1. Use link for clone and launch in IDE:    [github](https://github.com/ArturZimin/SpringBootEShop.git)
2. Set up MySql: mysql.com
3. Create database:
   ```  cd C:\Program Files\MySQL\MySQL Server 8.0\bin  ```&#9166;
   ```  mysql -u name -p ```&#9166;
   ```   Enter password: ****```&#9166;
   ```    create database eshopdb;```&#9166;
4. Fix data in the file application.yaml:  
   ```  
   datasource:
    url: jdbc:mysql://localhost:3306/eshopdb
    username: ****
    password: ****
   jpa:
    hibernate:
      ddl-auto: create 
5. Run application from bash:   
    -  cd C:\Users\IdeaProjects\E-SHOP &#9166;  (directory of app)
    -  gradlew run &#9166; (run app)
6. Run test from bash:     
    -  cd C:\Users\IdeaProjects\E-SHOP &#9166;  (directory of app)
    -  gradlew test &#9166; (run test)
