# cep-webservices

cep-webservices is an rest webservoce created to retrieve address
information from a cep and manage info from a cep database.

## tecnologies

this application was written in java and uses libraries and frameworks such as:
spring, spring-boot, spring-mvc, mockito and junit

## API

##### retrieveCEP
path: /cep/v1

*request*
```
{
    "cep": "11111111"
}
```

*response*
```
{
  "address": {
    "cep": "33333330",
    "street": "R. Da Consolação",
    "neighborhood": "Consolação",
    "city": "São Paulo",
    "state": "SP"
  }
}
```