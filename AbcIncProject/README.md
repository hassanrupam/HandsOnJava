# Spring Boot JPA MySQL - Building Rest CRUD API example

# ASsuming You are having Java & Maven Environment Ready for running
# You will require the following to Get This project Runnable->
# Mysql version - > 8.0.23 (Or any Mysql server would DO)
# Find the DB Script under "\AbcIncProject\DB_SCRIPT" directory!
# Run the Script to create DB with Data , Or You can just Create an empty 
# DB with Name "abc_inc_db_01_07_2021" 
# The Project Will create the Tables while running
# 
# The End point are specified below: 
# Project API
# GET http://localhost:8080/projects/getById/{Specify-Id} 
# DELETE http://localhost:8080/projects/delete/{specifyId}
# GET http://localhost:8080/projects/getList - To get Task List
# POST http://localhost:8080/pojects/save/ --JSON FORMAT{ "prjId": null,"prjName": "Project Y","prjDescription": "Project Y","prjStatus": "Created"}
# PUT http://localhost:8080/pojects/update/ --JSON FORMAT{ "prjId": "49ea34b3-740d-4d6e-b5a8-3f3bb5d305a7","prjName": "Project Y","prjDescription": "Project Y","prjStatus": "Created"}
# Task API
# GET http://localhost:8080/tasks/getById/{Specify-Id}
# DELETE http://localhost:8080/tasks/delete/{specifyId}
# GET http://localhost:8080/tasks/getList - To get Project List
# POST http://localhost:8080/tasks/save/ --JSON FORMAT {"tskId": null,"tskProject": {"prjId": "66eaa727-f720-462d-bdc6-58d8510795a1"},"tskName": "Task 1","tskStatus": "InProgress"}
# PUT http://localhost:8080/tasks/update/ --JSON FORMAT {"tskId": 3,"tskProject": {"prjId": "66eaa727-f720-462d-bdc6-58d8510795a1"},"tskName": "Task 1","tskStatus": "InProgress"}
# COMMON API
# http://localhost:8080/common/getStatusList - To get Status List fro Drop Down

# Other thas this Everything Is quit Self Explanatory If you read the Code Comments!

## Run Spring Boot application
```
mvn spring-boot:run
```


# @Author: Hassan Sakib Afrin
# @Created: 30-06-2021 11.59 PM
## For Any queries regaring Any Problem or ExplanationReach Me out
# @Email : hassanrupam@gmail.com
# @Phone : +880-01746-034-727
# @LinkedIn: https://www.linkedin.com/in/hassanrupam/