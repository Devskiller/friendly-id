[![Build Status](https://travis-ci.org/Devskiller/friendly-id.svg?branch=master)](https://travis-ci.org/Devskiller/friendly-id)  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.devskiller.friendly-id/friendly-id/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.devskiller.friendly-id/friendly-id)

friendly ID
==

Library to convert UUID to url friendly IDs basing on Base62
--

    UUID                                        Friendly ID
    
    c3587ec5-0976-497f-8374-61e0c2ea3da5   ->   7NLCAyd6sKR7kDHxgAWFPG
    |                                           |                              
    36 characters                               22 characters

                                  

Spring Boot integration
---

Just add friendly-id starter dependency:

```xml
<dependency>
    <groupId>com.devskiller.friendly-id</groupId>
    <artifactId>friendly-id-spring-boot-starter</artifactId>
    <version>1.1.0</version>
</dependency>
```
    
Sample application:

```java
@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/bars/{bar}")
    public Bar getBar(@PathVariable UUID bar) {
        return new Bar(UUID.randomUUID());
    }

    @Value
    class Bar {
        private final UUID id;
    }
}  
```   
    
Result:

    curl 'localhost:8080/bars/5fD1KwsxRcGhBqWNju0jzt' 
```json
{"id":"52OMXhWiAqUWwII0c97Svl"}
```    
Application uses internally UUID, but from external point of view only friendly id is visible.    
  
 ## Testing improvments 
  
This allows to write much simpler tests for our code, because you can use names instead of hard to remember UUIDs.

```java
mockMvc.perform(get("/bars/{bar}", "sampleBar"))
  .andExpect(status().isOk())
  .andExpect(jsonPath("$._links.self.href", is("http://localhost/bars/sampleBar")));
```
  
There is no need to define UUID value for `bar`, `sampleBar` is decoded to valid UUID identifier.

FriendlyID library 
---

Dependency:

```xml
<dependency>
    <groupId>com.devskiller.friendly-id</groupId>
    <artifactId>friendly-id</artifactId>
    <version>1.1.0</version>
</dependency>
```

Usage
--
```java
FriendlyId.createFriendlyId();
// 7NLCAyd6sKR7kDHxgAWFPG

FriendlyId.toUuid("7NLCAyd6sKR7kDHxgAWFPG");
// c3587ec5-0976-497f-8374-61e0c2ea3da5

FriendlyId.toFriendlyId(UUID.fromString("c3587ec5-0976-497f-8374-61e0c2ea3da5"));
// 7NLCAyd6sKR7kDHxgAWFPG
```

Note
--
	
* *Id* `00cafe` is equal to `cafe` - leading zeros are ignored.
* *UUID* is a 128-bit number, so *id* also can store only 128-bit number
  
    
Jackson integration    
---

Add jackson module dependency:

```xml
<dependency>
    <groupId>com.devskiller.friendly-id</groupId>
    <artifactId>friendly-id-jackson-datatype</artifactId>
    <version>1.1.0</version>
</dependency>
```

Register friendly_id module:

```java
ObjectMapper mapper = new ObjectMapper()
   .registerModule(new FriendlyIdModule());
```
