[![Build Status](https://travis-ci.org/Devskiller/friendly-id.svg?branch=master)](https://travis-ci.org/Devskiller/friendly-id)  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.devskiller.friendly-id/friendly-id/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.devskiller.friendly-id/friendly-id)  [![Coverage Status](https://coveralls.io/repos/github/Devskiller/friendly-id/badge.svg?branch=master)](https://coveralls.io/github/Devskiller/friendly-id?branch=master)

FriendlyID (Java, [Swift](https://github.com/kdubb/SwiftFriendlyId), [Rust](https://github.com/mariuszs/friendly_id)) 
==


What is the FriendlyID library?
--
The FriendlyID library converts a given UUID (with 36 characters) to a URL-friendly ID (a "FriendlyID") which is based on Base62 (with a maximum of 22 characters), as in the example below:


    UUID                                        Friendly ID
    
    c3587ec5-0976-497f-8374-61e0c2ea3da5   ->   5wbwf6yUxVBcr48AMbz9cb
    |                                           |                              
    36 characters                               22 characters or less

In addition, this library allows to:
 

* convert from a FriendlyID back to the original UUID; and
* create a new, random FriendlyID

Why use a FriendlyID?
--
Universal Unique IDs (UUIDs) provide a non-sequential and unique identifier that can be generated separately from the source database. As a result, it is not possible to guess either the previous or next identifier. That's great, but, to achieve this level of security, a UUID is long (128 bits long) and looks ugly (36 alphanumeric characters including four hyphens which are added to make it easier to read the UUID), as in this example: `123e4567-e89b-12d3-a456-426655440000`.

Such a format is:

* difficult to read (especially if it is part of a URL)
* difficult to remember
* cannot be copied with just two mouse-clicks (you have to select manually the start and end positions)
* can easily become broken across lines when it is copied, pasted, edited, or sent.


Our FriendlyID Java library solves these problems by converting a given UUID using Base62 with alphanumeric characters in the range [0-9A-Za-z] into a FriendlyId which consists of a maximum of 22 characters (but in fact often contains fewer characters).

Supported languages
--

Curently FriendlyId supports Java (this project) and [Swift](https://github.com/kdubb/SwiftFriendlyId) language (thanks to [Kevin Wooten](https://github.com/kdubb)) 

Tools
--

There is available CLI converters

* https://github.com/mariuszs/rust-friendlyid (also available in RPM and DEB format)
* https://github.com/kdubb/SwiftFriendlyId#command-line

## Use cases

### Basic (returning a user in a database)


Let us assume that a method in the controller for returning users requires the relevant UUID in order to find a given user in a database, as in this example:

```java
@GetMapping("/users/{userId}") 
public User getUser(@PathVariable UUID userId) {
        [implementation deleted]
}
```

Without using the Friendly ID library, you could access a given user as follows:

```bash
curl http://localhost:8080/users/c3587ec5-0976-497f-8374-61e0c2ea3da5
```


After adding the FriendlyID library, the controller method itself does not change, but you would be able to access a given user using the relevant FriendlyID as follows: 

```bash
curl http://localhost:8080/users/5wbwf6yUxVBcr48AMbz9cb
```



In addition, if a given document returned by such a method contains objects of type UUID, those IDs will also be shortened into FriendlyID format.


### Advanced (Optimizing testing)

 
 The FriendlyID library makes it possible to define for UUIDs values which are easy to read. By using names instead of hard-to-remember UUIDs, you can write much simpler tests for your code, for example:
 
```java
@Test 
public void shouldGetUser() { 
    mockMvc.perform(get("/users/{userId}", "John")) 
        .andExpect(status().isOk()) 
        .andExpect(content().contentType("application/json")) 
        .andExpect(jsonPath("$.uuid", is("John"))); 
} 
```

In the above example, the variable "John" is decoded by the library to the correct UUID, in this case, `00000000-0000-0000-0000-000000a69efb`. In this way, you can give a variable in a test class a truly meaningful value and, as a result, an assertion which refers to that variable becomes exceptionally easy to understand in your test program.


FriendlyID library 
--

Dependencies
---

```xml
<dependency>
    <groupId>com.devskiller.friendly-id</groupId>
    <artifactId>friendly-id</artifactId>
    <version>1.1.0</version>
</dependency>
```

Usage
---

```java
FriendlyId.createFriendlyId();
```

This creates a new, random FriendlyID, for example: `5wbwf6yUxVBcr48AMbz9cb`

```java
FriendlyId.toFriendlyId(UUID.fromString("c3587ec5-0976-497f-8374-61e0c2ea3da5"));
```

This converts a UUID in the form of a string to a FriendlyID, for example: `5wbwf6yUxVBcr48AMbz9cb`


 ```java
FriendlyId.toUuid("5wbwf6yUxVBcr48AMbz9cb");
```

This converts a FriendlyID to its UUID, for example: `c3587ec5-0976-497f-8374-61e0c2ea3da5`


Notes
--
	
* As every *UUID* is a 128-bit number, a *FriendlyID* can also store only a 128-bit number.
* If a FriendlyID has any leading zeros, those leading zeros are ignored - for example, `00cafe` is treated as `cafe`.


## Integrations


- [Spring Boot integration](#Spring-Boot-integration)
- [Jackson integration ](#Jackson-integration)

### Spring Boot integration

The FriendlyID library includes a Spring configuration to make it easy to add shorter IDs to an application. With a typical application based on Spring Boot, for your controllers to be able to use FriendlyIDs when communicating with the outside world, just add one new starter dependency as follows:

```xml
<dependency>
    <groupId>com.devskiller.friendly-id</groupId>
    <artifactId>friendly-id-spring-boot-starter</artifactId>
    <version>1.1.0</version>
</dependency>
```
    
Let us assume that you'll use this sample application:

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
    
This command: `curl http://localhost:8080/bars/5fD1KwsxRcGhBqWNju0jzt` 

will result in the following output: 
```json
{"id":"52OMXhWiAqUWwII0c97Svl"}
```    

In this case, `Bar` is a POJO class which is converted by Spring MVC to a JSON document. This `Bar` object has one field of type UUID, and this field is output to the JSON document as a FriendlyID instead of a UUID. Although the application uses the relevant UUID internally, from an external point of view, only the FriendlyID is visible.    

### Jackson integration    


First, add the following Jackson module dependency:
```xml
<dependency>
    <groupId>com.devskiller.friendly-id</groupId>
    <artifactId>friendly-id-jackson-datatype</artifactId>
    <version>1.1.0</version>
</dependency>
```
Then register the `FriendlyIdModule` module as follows:

```java
ObjectMapper mapper = new ObjectMapper()
   .registerModule(new FriendlyIdModule());
```

Contributing
----------

Thinking of helping us out? We invite you to take a look at:

- Source Code: [github.com/Devskiller/friendly-id/](github.com/Devskiller/friendly-id/)
- Issue Tracker: [github.com/Devskiller/friendly-id/issues](github.com/Devskiller/friendly-id/issues)


License
-------

The project is licensed under the Apache 2.0 license.
For further details, please see the [License](/LICENSE/) page. 
