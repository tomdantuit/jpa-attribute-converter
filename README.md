# Configurable AttributeConverter with JPA

## Description
This is an example project using an AttributeConverter to put a prefix on a database column's value. 
The prefix used is configurable in the application.yml. There are also REST endpoints for interacting
with the data.

## Dependencies
- [Docker-CE](https://docs.docker.com/install/)
- [Docker-Compose](https://docs.docker.com/compose/install/)

## Running
1. $` docker-compose up -d`
2. $` mvn clean package`
3. $` mvn spring-boot:run`

## Important Files
### CodeConverterConfig.java
The CodeConverterConfig class makes use of a value injected from the `application.yml` 
on a static property called `prefix`. The property needs to be static with the value injected on the setter 
because this configuration class cannot be `@Autowired` into an AttributeConverter class (and we don't want to
mess with the ApplicationContext directly).  This class is also supposed to be annotated as an `@Component`,
but I chose to use `@Configuration` because the intent would be for other converter configuration to exist in here.  

  
### CodeAttributeConverter.java
The CodeAttributeConverter class is where the rubber meets the road. For demonstration purposes, we are just taking
a prefix that can be specified via the application.yml and prepending it to the value being stored. You could just as 
easily inject a private key and change the logic at `Line 13` and `Line 18` to handle encrypting/decrypting data. 
  
### ItemRecord.java
The ItemRecord class is the entity that represents the database record for an item. The meaningful piece of info
here is on line 17, `@Convert(converter = CodeAttributeConverter.class)`. This line is telling Hibernate to call 
the `CodeAttributeConverter.convertToDatabaseColumn()` on the `code` field prior to writing to the db, as well as 
calling `CodeAttributeConverter.convertToEntityAttribute()` on the `code` field after reads, but before returning.  


