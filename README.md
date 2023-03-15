# api-rest-challenge
This repository contains Federico Piacentini's challenge.

For extra information on the implementation details, you can check the [Wiki](https://github.com/fpiacentini08/api-rest-challenge/wiki).

## Prerequisites

- Have docker running locally
- Having executed docker login


## Install
To generate the jar, execute the following:
```
mvn clean install
```

## Running locally
This project includes a docker composer file to start the following containers:

- database container
- external webserver container
- api-rest-challenge container


So you can start the whole project by executing the following in the root folder:

```
./scripts/start.sh
```

## How to consume this service?

A description of the exposed apis and example curls can be found [here](https://github.com/fpiacentini08/api-rest-challenge/wiki/Functioning-and-assumptions).

You can use the attached Postman Collection.
