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

## Hacking/Contributing
:+1::tada: First off, thanks for taking the time to contribute! :tada::+1:
## Styleguides

### Git Commit Messages

* Use the present tense ("Add feature" not "Added feature")
* Use the imperative mood ("Move cursor to..." not "Moves cursor to...")
* Limit the first line to 72 characters or less
* Reference issues and pull requests liberally after the first line
* When only changing documentation, include `[ci skip]` in the commit title
* Consider starting the commit message with an applicable emoji:
    * :art: `:art:` when improving the format/structure of the code
    * :racehorse: `:racehorse:` when improving performance
    * :non-potable_water: `:non-potable_water:` when plugging memory leaks
    * :memo: `:memo:` when writing docs
    * :penguin: `:penguin:` when fixing something on Linux
    * :apple: `:apple:` when fixing something on macOS
    * :checkered_flag: `:checkered_flag:` when fixing something on Windows
    * :bug: `:bug:` when fixing a bug
    * :fire: `:fire:` when removing code or files
    * :green_heart: `:green_heart:` when fixing the CI build
    * :white_check_mark: `:white_check_mark:` when adding tests
    * :lock: `:lock:` when dealing with security
    * :arrow_up: `:arrow_up:` when upgrading dependencies
    * :arrow_down: `:arrow_down:` when downgrading dependencies
    * :shirt: `:shirt:` when removing linter warnings