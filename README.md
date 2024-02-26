# naturskolan

# Development database

`podman-compose up`

# Build

`./gradlew build`

## Rebuild on changes

`./gradlew -t build`


## Skip tests

For skipping tests when building a project, one can pass the -x option to the build task:

`./gradlew -t build -x test -i`

# Run

Run server with `./gradew run`
