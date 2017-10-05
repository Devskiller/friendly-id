[![Build Status](https://travis-ci.org/Devskiller/friendly-id.svg?branch=master)](https://travis-ci.org/Devskiller/friendly-id)

friendly ID
==

Library to convert uuid to url friendly IDs basing on base62
--

Dependency:

```xml
<dependency>
    <groupId>com.devskiller.friendly-id</groupId>
    <artifactId>friendly-id</artifactId>
    <version>${project.version}</version>
</dependency>
```

Usage
--
```java
Url62.create();
// 7NLCAyd6sKR7kDHxgAWFPG

Url62.decode("7NLCAyd6sKR7kDHxgAWFPG");
// c3587ec5-0976-497f-8374-61e0c2ea3da5

Url62.encode(UUID.fromString("c3587ec5-0976-497f-8374-61e0c2ea3da5"));
// 7NLCAyd6sKR7kDHxgAWFPG
```

Note
--
	
* *Id* `00cafe` is equal to `cafe` - leading zeros are ignored.
* *UUID* is a 128-bit number, so *id* also can store only 128-bit number


Spring Boot integration
---

Add starter dependency:

```xml
<dependency>
    <groupId>com.devskiller.friendly-id</groupId>
    <artifactId>friendly-id-spring-boot-starter</artifactId>
    <version>${project.version}</version>
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
    
Jackson integration    
---

Add jackson module dependency:

```xml
<dependency>
    <groupId>com.devskiller.friendly-id</groupId>
    <artifactId>friendly-id-jackson-datatype</artifactId>
    <version>${project.version}</version>
</dependency>
```

Register friendly_id module:

```java
ObjectMapper mapper = new ObjectMapper()
   .registerModule(new FriendlyIdModule());
```