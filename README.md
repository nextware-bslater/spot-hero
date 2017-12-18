# spot-hero
repo conatins coding challenge for Spot Hero

curl commands
````
curl -X POST   http://localhost:8080/spot-hero/rates/upload   -H 'cache-control: no-cache'   -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'   -H 'mime-version: 1.0'   -H 'postman-token: d400c7b1-b0b4-96b0-df2c-5bb5b995a95b'   -F 'file=@/home/devii_b/fun_code/spot-hero/src/test/resources/rates.json'
````

DOCS
- contract: http://localhost:12346/docs/apidocs/index.html
- javadocs: http://localhost:12346/apidocs/index.html

Sample result:
Datetime ranges should be specified in isoformat.  A rate must completely encapsulate a datetime range for it to be available.
Rates will never overlap.
2015-07-01T07:00:00Z to 2015-07-01T12:00:00Z should yield 1500
json
curl -X POST   http://localhost:8080/spot-hero/api/rates/upload   -H 'cache-control: no-cache'   -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'   -H 'mime-version: 1.0'   -H 'postman-token: d400c7b1-b0b4-96b0-df2c-5bb5b995a95b'   -F 'file=@/home/devii_b/fun_code/spot-hero/src/test/resources/ratesTest.json' -F 'startInterval=2015-07-01T07:00:00Z' -F 'endInterval=2015-07-01T12:00:00Z'
xml
curl -X POST   http://localhost:8080/spot-hero/api/rates/upload   -H 'cache-control: no-cache'   -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'   -H 'mime-version: 1.0'   -H 'postman-token: d400c7b1-b0b4-96b0-df2c-5bb5b995a95b'   -F 'file=@/home/devii_b/fun_code/spot-hero/src/test/resources/ratesTest.xml' -F 'startInterval=2015-07-01T07:00:00Z' -F 'endInterval=2015-07-01T12:00:00Z'


2015-07-04T07:00:00Z to 2015-07-04T12:00:00Z should yield 2000
curl -X POST   http://localhost:8080/spot-hero/api/rates/upload   -H 'cache-control: no-cache'   -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'   -H 'mime-version: 1.0'   -H 'postman-token: d400c7b1-b0b4-96b0-df2c-5bb5b995a95b'   -F 'file=@/home/devii_b/fun_code/spot-hero/src/test/resources/ratesTest.json' -F 'startInterval=2015-07-04T07:00:00Z' -F 'endInterval=2015-07-04T12:00:00Z'

2015-07-04T07:00:00Z to 2015-07-04T20:00:00Z should yield unavailable

curl -X POST   http://localhost:8080/spot-hero/api/rates/upload   -H 'cache-control: no-cache'   -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'   -H 'mime-version: 1.0'   -H 'postman-token: d400c7b1-b0b4-96b0-df2c-5bb5b995a95b'   -F 'file=@/home/devii_b/fun_code/spot-hero/src/test/resources/ratesTest.json' -F 'startInterval=2015-07-04T07:00:00Z' -F 'endInterval=2015-07-04T20:00:00Z'