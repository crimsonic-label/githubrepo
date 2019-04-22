# GitHub Repository Info Retriever

The REST service Spring Boot application retrieves information about Github repositories

The REST service url is [http://localhost:8080/repositories/{owner}/{repository-name}](http://localhost:8080/repositories/{owner}/{repository-name})

### The JSON returned includes
* full name of repository
* description of repository
* git clone url
* number of stargazers
* date of creation (ISO format)
