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
+ MySql db version:8
+ Lombok
+ Flyway
+ Docker
+ HTML,CSS,Java Script,jQuery,Bootstrap

### Application launch: WINDOWS
1. Use link for clone and launch in IDE:    [github](https://github.com/ArturZimin/SpringBootEShop.git)
2. Set up MySql: [MySql](https://www.mysql.com)
3. Create database:
   ```  cd C:\Program Files\MySQL\MySQL Server 8.0\bin  ```&#9166;
   ```  mysql -u name -p ```&#9166;
   ```   Enter password: ****```&#9166;
   ```    create database eshopdbd;```&#9166;

4. Fix data in the file application.yaml:  
   ``` 
   mail:
    ho
    st: smtp.gmail.com
    port: 587
    username: *****your email*****
    password: ******password******( settings example -> https://support.google.com/a/answer/176600?hl=en ) 
    
   datasource:
    url: jdbc:mysql://localhost:3306/eshopdbd
    username: root
    password: 7001
   
5. Run application from bash:   
    -  cd C:\Users\IdeaProjects\E-SHOP &#9166;  (directory of app)
    -  gradlew run &#9166; (run app)
6. Run test from bash:     
    -  cd C:\Users\IdeaProjects\E-SHOP &#9166;  (directory of app)
    -  gradlew test &#9166; (run test).

### Application launch: LINUX
1. Create folder :
```
mkdir EShop
cd EShop
```
2. Clone app:
```
git clone https://github.com/ArturZimin/SpringBootEShop.git
```
3. Go to the finished project directory:
```
cd /EShop/SpringBootEShop/ 
```
4. Run app:  
 *  docker-compose up -d --build  &#9166;   
5. Stop app : 
 * docker-compose down   &#9166;   