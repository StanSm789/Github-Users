# Springboot Application for Searching GitHub Users

This is a Springboot application that integrates with the GitHub API to search for and return information about GitHub users. It provides an API endpoint to search and return users based on search query, order and pagination parameters.

## API Endpoint

To retrieve information about GitHub users, you can use the following API endpoint:

`http://localhost:8070/users?q={query}&order={order}&per_page={perPage}`

**Parameters:**

- `q`: The search query string.
- `order`: The order in which to return results (`asc` or `desc`).
- `per_page`: The number of results per page.

For example: http://localhost:8070/users?q=postman&order=desc&per_page=5

## Prerequisites

To run this application, you need to have the following prerequisites installed on your machine:

- JDK 19.
- Apache Maven 3.8.7 or higher.
- IntelliJ IDEA.

## Running the Application

To run this application in IntelliJ IDEA, follow these steps:

1. Download and unzip the project.
2. Open the project in IntelliJ IDEA.
3. Navigate to the `com.smirnov.technicaltest` package in the Project window.
4. Right-click on `TechnicalTestApplication` and select `Run TechnicalTestApplication`.


## Testing

This application includes integration tests to cover the service and controller. To run the tests:

3. Navigate to the `test` folder `com.smirnov.technicaltest` package in the Project window.
4. Right-click on `com.smirnov.technicaltest` and select `Run Tests in technicaltest`.

## Acknowledgements

This application was created by Stanislav Smirnov as a technical test for Microba.