# Mortgage

This GitHub repository contains the Mortgage app as a code test for Landbay

One of the biggest assumptions is that I don't know much about the domain, about the relation between mortgage and investments, so I created what I think it is right for this case, the Mortgage has a total value that is it's principal and each lender can invest into it some of it's money.

There is no lender repository saying the amount of money each Lender has, as this service is focused into the mortgage itself.

## Steps completed

Steps 1 and 4 were completed successfully using the spring boot framework and the requisites in the pdf provided.

Only the endpoints provided in this file were implemented, generic endpoints like /mortgage to list all mortgages were not implemented.

### /mortgage - POST
To create a new mortgage using POST verb
Example of body:

```json

	{
		"name": "Third Mortgage from json",
		"principal": 345678,
		"interest": 0.46,
		"portfolio": {
			"investments": [
				{
					"lender": {
						"name": "Lender 1"
					},
					"ammount": 154234
				},
				{
					"lender": {
						"name": "Lender 2"
					},
					"ammount": 145634
				}
			]
		}
	}

```


### /mortgage/{id} - GET
To retrieve a specific mortgage

### /mortgage/{id}/protfolio - POST
To add a new investment into the mortgage
Example of body:

```json

	{
		"lender": {
			"name": "Lender 1"	
		},
		"ammount": 154234
	}

```


## Steps not completed
Steps 2 and 3 not completed due to lack of time.

I've decided to prioritize 1 and 2 as it would give a more completed solution in the required time.

## To compile and run the application

Maven is used to compile, test and package the application into a docker image, it is straight forward to do so, symple follow this steps:


| *Command* | *description* |
| ------------- | ------------- |
| mvn clean install | build, compile and install the artifact |
| mvn package docker:build | package the artifact into a docker image |
| docker run -p 8080:8080 -t pasquotto/mortgage | run the newly created image |

# Bonus

There is a HAL browser installed, just go to http://[domain]:[port]/ and it is possible to play with the endpoints

