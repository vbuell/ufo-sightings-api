## GraphQL Sample API in Kotlin

![](/images/intro_image_brwngrldev_graphql.png)

This is a sample app for demonstrating creating a GraphQL API written entirely in Kotlin. It uses the following primary dependencies:
* [Ktor - Server](http://ktor.io)
* [Koin - Dependency Injection](https://github.com/Ekito/koin)
* [KGraphQL - GraphQL Server](https://github.com/pgutkowski/KGraphQL) 
* [Squash - Database Access](https://github.com/orangy/squash)


It uses UFO Sighting data freely available from [Kaggle](https://www.kaggle.com/NUFORC/ufo-sightings).

## Useful GraphQL queries

All sightings limited to 5:
```
query {
  sightings(size: 5) {
    id
    date
    comments
  }
}
```

A particular sighting by ID:
```
query{
  sighting(id: 2344) {
    id
    date
    comments
  }
}
```

Sightings by country:
```
query{
  topCountrySightings {
    numOccurrences
    country
  }
}
```
