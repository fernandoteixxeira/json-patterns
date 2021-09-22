# json-patterns

## Tests

### Snake Case Request

```shell
curl --location --request GET 'http://localhost:8080//domain/9'
```

### Snake Case Response

```shell
curl --location --request POST 'http://localhost:8080/domain' \
--header 'Content-Type: application/json' \
--data-raw '{
    "domain_id": 9,
    "domain_description": "Mills Inc",
    "domain_color": "#0B179C",
    "domain_time": "2021-09-22T15:01:32.134564-03:00",
    "domainDate": "2021-09-22"
}'
```

### Camel Case Request

```shell
curl --location --request GET 'http://localhost:8080/camel-case//domain/9'
```

### Camel Case Response

```shell
curl --location --request POST 'http://localhost:8080/camel-case/domain' \
--header 'Content-Type: application/json' \
--data-raw '{
    "domainId": 9,
    "domainDescription": "Rath, Watsica and Quitzon",
    "domainColor": "#4CB217",
    "domainTime": "2021-09-22T15:01:09.150044",
    "domain_date": "2021-09-22"
}'
```
