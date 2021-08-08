# Delivery and Campaign

## Tech Stack
* Java JDK 11
* Maven 3.8.1
* Spring Boot v2.9.2
* Kafka 2.8.0
* Postgresql 13
* Docker

## Running the Application
#### Build the app
`mvn clean install`

#### Start Docker, then build and up docker compose
`docker-compose up --build`

> This is only dockerize project :(

## Application Details

Once application is up, api documentation can be seen at `http://localhost:8082/swagger-ui.html`

### Sample Endpoint

#### [POST] /campaign/ (addCampaign)

```
Body: 
{
  "campaignType": "RATE",
  "categoryId": 1,
  "description": "Category Rate Campaign 1 Desc",
  "discountAmount": 0,
  "startDate": 20210101,
  "endDate": 20211010,
  "minPurchaseAmount": 150.0,
  "rate": 30,
  "title": "Category Rate Campaign 1"
}
```


