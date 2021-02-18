# Senior Challenge

This is a REST API (Application Programming Interface) to manage orders and 
products/services.


## Techs

- Java
- Maven
- Spring Boot
- PostgreSQL
- JPA


## Requirements

- JDK
- PostgreSQL


## Installation

**I recommend using the IntelliJIDEA IDE (Integrated Development Environment) 
because it makes some tasks easier.**

- First, download the source code on [Github repository page](https://github.com/RuanScherer/senior-api).
Or if you prefer, use `$ git clone https://github.com/RuanScherer/senior-api`.
- After downloading, open the cloned project in the IDE.
- Make sure that you have PostgreSQL installed and available to connect, then go to file
`application.properties` at `src/main/resources/`.
- Fill `spring.datasource.url`, `spring.datasource.username` and `spring.datasource.password` 
  with your PosgtreSQL connection credentials
- In IntelliJIDEA, click the button to run the project, located in the upper right.
  If you use the IntelliJIDEA default settings, the project will be built and then it 
  will be executed.
- Okay, now the project must be running on your machine.


## How to use

All the examples below will assume that you use `http://localhost:8080` as the base 
URL and from there, add the corresponding endpoint to the end.

***All request bodies will be in JSON format.**

### Products / Services

#### Create a product or service

- Endpoint: `/products-services/`
- HTTP Method: `POST`

Request body example:
```
{
	"name": "Notebook",
	"description": "Lenovo IDEAPAD",
	"price": 2500,
	"type": "PRODUCT" or "SERVICE"
}
```

- `description` is optional.

If everything is right, the API will return the JSON object of the product/service 
created with its ID (which will be used later).
In response headers you can find "Location", the resource URL access.


#### Update a product or service

- Endpoint: `/products-services/{id}`
    - You need to replace `{id}` at the endpoint with the id of the product or
      service you created.
- HTTP Method: `PUT`

Request body example:
```
{
	"name": "Notebook",
	"description": "Lenovo IDEAPAD",
	"price": 2500,
	"type": "PRODUCT" or "SERVICE"
}
```

- All attributes in the body example are required.

If everything is right, the API will return the JSON object of the product/service
updated with its ID (which will be used later).


#### Find all products and services

- Endpoint: `/products-services/`
- HTTP Method: `GET`

If everything is right, the API will return the JSON containing all registered 
products and services.


#### Find product or service by ID

- Endpoint: `/products-services/{id}`
  - You need to replace `{id}` at the endpoint with the id of the product or 
    service you created.
- HTTP Method: `GET`

If everything is right, the API will return the JSON object of the product/service.


#### Remove product or service by ID

- Endpoint: `/products-services/{id}`
    - You need to replace `{id}` at the endpoint with the id of the product or
      service you created.
- HTTP Method: `DELETE`

If everything is right, the API will return the HTTP Status 204.<br/>
***Is not possible to remove a product or service that are linked to a solicitation.**


### Solicitations (Orders)

#### Create a solicitation

- Endpoint: `/solicitations/`
- HTTP Method: `POST`

Request body example:
```
{
	"requester": "Ruan",
	"discount": 10,
	"items": [
		{
			"productID": "9c29dd26-936f-43d1-bc95-9e3221b2ccab",
			"quantity": 1
		},
		{
			"productID": "1d0fa9f7-7791-4f1d-abc1-05f69ef3715a",
			"quantity": 2
		}
	]
}
```

- `items` and `discount` attributes are optional.
- `items` will allow you to create just one item per product, considering only the
  first ocurrence.

If everything is right, the API will return the JSON object of the solicitation
created with its ID (which will be used later), creation time (`solicitationTime`)
and status (`solicitationStatus`).

In response headers you can find "Location", the resource URL access.


#### Update a solicitation

- Endpoint: `/solicitations/{id}`
    - You need to replace `{id}` at the endpoint with the id of the solicitation 
      you created.
- HTTP Method: `PUT`

Request body example:
```
{
	"requester": "Nicolas",
	"discount": 10,
	"solicitationStatus": "WAITTING_PAYMENT"
}
```

- `items` is not allowed here.
- All attributes in the body example are required.

If everything is right, the API will return the JSON object of the solicitation
updated with its ID (which will be used later).


#### Find all solicitations

- Endpoint: `/solicitations/`
- HTTP Method: `GET`

If everything is right, the API will return the JSON containing all registered
solicitations.


#### Find solicitation by ID

- Endpoint: `/solicitations/{id}`
    - You need to replace `{id}` at the endpoint with the id of the solicitation
      you created.
- HTTP Method: `GET`

If everything is right, the API will return the JSON object of the solicitation.


#### Remove solicitation by ID

- Endpoint: `/solicitations/{id}`
    - You need to replace `{id}` at the endpoint with the id of the solicitation
      you created.
- HTTP Method: `DELETE`

If everything is right, the API will return the HTTP Status 204.

By removing a solicitation you will also be removing all items from it 
(section on items shortly thereafter).


### Solicitation Items

#### Create a solicitation item

- Endpoint: `/solicitations/{id}/items/}`
    - You need to replace `{id}` at the endpoint with the id of the solicitation
      you created.
- HTTP Method: `POST`

Request body example:
```
{
	"productID": "ff47be86-ecb0-494b-9bc0-faea68796da1",
	"quantity": 10
}
```

- `productID` is the ID of the product you created earlier.
- All attributes in the body example are required.

If everything is right, the API will return the JSON object of the item
created with the product data inside it.

In response headers you can find "Location", the resource URL access.


#### Update a solicitation item

- Endpoint: `/solicitations/{id}/items/{productId}`
    - You need to replace `{id}` at the endpoint with the id of the solicitation
      you created.
    - You need to replace `{productId}` at the endpoint with the id of the product
      you want to add to the order.
- HTTP Method: `PUT`

Request body example:
```
{
	"quantity": 10
}
```

- `productID` is not allowed here because it is used as item identifier.
- All attributes in the body example are required.

If everything is right, the API will return the JSON object of the item
updated with the product data inside it.


#### Find all solicitation items

- Endpoint: `/solicitations/{id}/items`
    - You need to replace `{id}` at the endpoint with the id of the solicitation
      you created.
- HTTP Method: `GET`

If everything is right, the API will return the JSON containing all items.


#### Find solicitation item by ID

- Endpoint: `/solicitations/{id}/items/{productId}`
    - You need to replace `{id}` at the endpoint with the id of the solicitation
      you created.
    - You need to replace `{productId}` at the endpoint with the id of the product 
      you added to the order and want to view.
- HTTP Method: `GET`

If everything is right, the API will return the JSON object of the item.


#### Remove solicitation item by ID

- Endpoint: `/solicitations/{id}/items/{productId}`
    - You need to replace `{id}` at the endpoint with the id of the solicitation
      you created.
    - You need to replace `{productId}` at the endpoint with the id of the product
      you want to remove from the order.
- HTTP Method: `DELETE`

If everything is right, the API will return the HTTP Status 204.