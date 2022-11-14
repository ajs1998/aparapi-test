plugins {
    id("java")
    id("application")
}

group = "dev.alexjs"
version = "1.0-SNAPSHOT"
application.mainClass.set("dev.alexjs.Main")

java.withJavadocJar()
java.withSourcesJar()
tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass
    }
    val paths = configurations.runtimeClasspath.get()
        .filter { it.exists() }
        .map { if(it.isDirectory) zipTree(it.toPath()) else it }
    from(paths)
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.aparapi:aparapi:3.0.0")
    implementation("com.aparapi:aparapi-jni:1.4.3")
//    implementation("org.apfloat:apfloat:1.10.1")
//    implementation("org.apfloat:apfloat-aparapi:1.10.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
