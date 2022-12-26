# Spring Web
Practice making web application by Spring Framework.

## Requirements
### Member
1. Create
2. Read

### Goods
1. Create
2. Update
3. Read

### Order
1. Create
2. Read \
**Support dynamic query using JPA.** \
ex) Search by `OrderStatus`, `userName`
3. Update \
cancel the order (order status)
 
### etc
1. Goods require inventory management.
2. Type of goods consist fo book, audio, movie
3. Goods should be categorized.
4. Input delivery information when ordering.

## Application Architecture
![Web-Application-Architecture](/src/main/resources/assets/Web-Application-Architecture.gif)

| Layer      | Description                        |
|------------|------------------------------------|
| Controller | API routing                        |
| Service    | Business Logic                     |
| Repository | Handle domain (entities) using JPA |
| Domain     | Define entities                    |

