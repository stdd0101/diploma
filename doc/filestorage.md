# Project: filestorage
# 📁 Collection: auth 


## End-point: login
### Method: POST
>```
>localhost:8082/cloud/login
>```
### 🔑 Authentication noauth

|Param|value|Type|
|---|---|---|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: logout
### Method: GET
>```
>localhost:8082/cloud/logout
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: refresh
### Method: POST
>```
>localhost:8082/cloud/auth_token/refresh
>```
### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdGRkMDFAZ21haWwuY29tIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgyL2Nsb3VkL2xvZ2luIiwiZXhwIjoxNjM4OTgxNTExfQ.Z25ed7oBxCv1vPlW9Lic-niT5hRkdYPVxTpZVcQTDtc|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
# 📁 Collection: files 


## End-point: file
### Method: POST
>```
>localhost:8082/cloud/file
>```
### Body formdata

|Param|value|Type|
|---|---|---|
|filename|/C:/Users/user/Pictures/5.jpg|file|


### 🔑 Authentication noauth

|Param|value|Type|
|---|---|---|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: file rename
### Method: PUT
>```
>localhost:8082/cloud/file/1639089881302_240_F_378248025_94Yty6t31BKKtn1fJ8HMImxpgjWCKJRJ.jpg
>```
### Body formdata

|Param|value|Type|
|---|---|---|
|name|newName.jpg|text|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdGRkMDFAZ21haWwuY29tIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgyL2Nsb3VkL2xvZ2luIiwiZXhwIjoxNjM5MDkwNDE3fQ.EsrMO2LVByP__hyrRvmKsxWGMNUS1_jUAZ4hSKsBfM4|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: file delete
### Method: DELETE
>```
>localhost:8082/cloud/file/1639076249866_kfh5cuin_md.jpg
>```
### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdGRkMDFAZ21haWwuY29tIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgyL2Nsb3VkL2xvZ2luIiwiZXhwIjoxNjM5MDc3NzUwfQ.LTsvTOQoqb6mVvSCyC_I5OsWPUqJlhHNe7vgKTV5WXI|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: file download
### Method: GET
>```
>localhost:8082/cloud/file
>```
### Body formdata

|Param|value|Type|
|---|---|---|
|filename|/C:/Users/user/Pictures/5.jpg|file|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdGRkMDFAZ21haWwuY29tIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgyL2Nsb3VkL2F1dGhfdG9rZW4vcmVmcmVzaCIsImV4cCI6MTYzOTA3MTY0Nn0.9mkNIsR7-i_U-Nl7M0GU12ZGhkXLdLlY2VIwqRMUqvo |string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: list all files
### Method: GET
>```
>localhost:8082/cloud/list
>```
### Body (**raw**)

```json

```

### Query Params

|Param|value|
|---|---|
|limit|2|


### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdGRkMDFAZ21haWwuY29tIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgyL2Nsb3VkL2xvZ2luIiwiZXhwIjoxNjM5MDkwNDE3fQ.EsrMO2LVByP__hyrRvmKsxWGMNUS1_jUAZ4hSKsBfM4|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: users
### Method: GET
>```
>localhost:8082/cloud/users/
>```
### Body (**raw**)

```json

```

### 🔑 Authentication bearer

|Param|value|Type|
|---|---|---|
|token|eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdGRkMDFAZ21haWwuY29tIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgyL2Nsb3VkL2xvZ2luIiwiZXhwIjoxNjM4OTg0MzYzfQ.4e01tT1UrS4m1_SJZq1_lcoAIPaSJBwS-To3bGHURuo|string|



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
_________________________________________________
Powered By: [postman-to-markdown](https://github.com/bautistaj/postman-to-markdown/)
