# External service

This folder contains a Python 3 script to run a simple webserver.

This webserver will expose an api on port 8081.

Only one GET endpoint will be exposed.

This endpoint will respond a random integer number between 0 and 100.

This simple webserver has also been dockerized. For more info [here](https://github.com/fpiacentini08/api-rest-challenge/wiki/Docker).

## Prerequisites
- Python 3.8.2 or above


## Running locally
To run the webserver locally, execute the following:
```
python3 external-webserver.py
```

To stop the webserver, simply press CTRL + C.

## Example

```aidl

$ python3 external-webserver.py
Percentage Web server is running on http://localhost:8081
127.0.0.1 - - [12/Mar/2023 20:09:00] "GET / HTTP/1.1" 200 -
127.0.0.1 - - [12/Mar/2023 20:09:00] Response: {"value": 2}
127.0.0.1 - - [12/Mar/2023 20:11:28] "GET / HTTP/1.1" 200 -
127.0.0.1 - - [12/Mar/2023 20:11:28] Response: {"value": 58}
127.0.0.1 - - [12/Mar/2023 20:11:29] "GET / HTTP/1.1" 200 -
127.0.0.1 - - [12/Mar/2023 20:11:29] Response: {"value": 41}
127.0.0.1 - - [12/Mar/2023 20:11:29] "GET / HTTP/1.1" 200 -
127.0.0.1 - - [12/Mar/2023 20:11:29] Response: {"value": 55}
127.0.0.1 - - [12/Mar/2023 20:11:30] "GET / HTTP/1.1" 200 -
127.0.0.1 - - [12/Mar/2023 20:11:30] Response: {"value": 55}
127.0.0.1 - - [12/Mar/2023 20:11:30] "GET / HTTP/1.1" 200 -
127.0.0.1 - - [12/Mar/2023 20:11:30] Response: {"value": 44}
^C
Server stopped.
```