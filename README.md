# Product Microservices

__To compile and run:__

$ mvn spring-boot:run


__Possible calls:__

1. GET products
	- http://localhost:8092/product/all
	- http://localhost:8092/product/1
	- http://localhost:8092/product/2

2. POST a product (create)
	- http://localhost:8092/product/create

3. PUT a product (update)
    - http://localhost:8092/product/update/{id}
   	- http://localhost:8092/product/update/{id}/price/{price}

4. DELETE a product
   	- http://localhost:8092/product/delete/{id}
