plugins {
    id("java")
    id("application")
}

group = "xyz.devmeko"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "xyz.devmeko.ConwayLifeGame.Main"
    }
}

application {
    mainClass.set("xyz.devmeko.ConwayLifeGame.Main")
}
