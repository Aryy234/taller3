plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val langchain4jVersion =  "1.23.0"

dependencies {
    implementation("dev.langchain4j:langchain4j:${langchain4jVersion}")
    implementation("dev.langchain4j:langchain4j-embeddings:${langchain4jVersion}-beta33")

}

tasks.test {
    useJUnitPlatform()
}