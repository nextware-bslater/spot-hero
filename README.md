# SpotHero Parking Rate Microservice
This repo contains a coding challenge that is described by this [prompt](/src/main/resources/Sr_Engineer_(Services).pdf)

I made some key assumptions based on the prompt:
1) Intervals at which a valid price can occur never span mulitple days and as stated never overlap
2) Start and end times must be monotonically increasing

## Prereqs
1) Maven 3.3.3 + installed on machine
2) Docker version 17.03.1-ce, build c6d412e
3) docker-compose version 1.11.2, build dfed245

## Deployment
Git clone source to environment on which you wish to deploy
````
cd ~/spot-hero
docker-compose up -d
````
You should now be able to access API Documentation and API

## API Documentation
- Contract: http://\<hostname\>/docs/apidocs/index.html
- Javadocs: http://\<hostname\>/apidocs/index.html

Contract describes how to access API and API Metrics
and Javadocs are documentation for the Java Classes not represented in the API contract

# cURL Commands
1) Command to upload file
````
curl -X POST   'http://localhost:8080/spot-hero/api/rates/upload?startInterval=2015-07-01T07%3A00%3A00Z&endInterval=2015-07-01T12%3A00%3A00Z'   -H 'cache-control: no-cache'   -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'   -H 'mime-version: 1.0'   -H 'postman-token: d400c7b1-b0b4-96b0-df2c-5bb5b995a95b'   -F 'file=@/home/devii_b/fun_code/spot-hero/src/test/resources/ratesTest.json'
````
2) Command for JSON body
````
curl -X POST \
  'http://localhost:8080/spot-hero/api/rates?startInterval=2015-07-01T07%3A00%3A00Z&endInterval=2015-07-01T12%3A00%3A00Z' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
  "rates": [
    {
      "days": "mon,tues,wed,thurs,fri",
      "times": "0600-1800",
      "price": 1500
    },
    {
      "days": "sat,sun",
      "times": "0600-2000",
      "price": 2000
    }
  ]
}'
````
3) Command for XML body
````
curl -X POST \
  'http://localhost:8080/spot-hero/api/rates?startInterval=2015-07-01T07%3A00%3A00Z&endInterval=2015-07-01T12%3A00%3A00Z' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
  "rates": [
    {
      "days": "mon,tues,wed,thurs,fri",
      "times": "0600-1800",
      "price": 1500
    },
    {
      "days": "sat,sun",
      "times": "0600-2000",
      "price": 2000
    }
  ]
}'
````
