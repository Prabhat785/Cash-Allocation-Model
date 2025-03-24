plugins {
    java
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Database
    implementation("org.xerial:sqlite-jdbc:3.42.0.0")

    // JSON Processing
    implementation("org.json:json:20231013")

    // Charting
    implementation("org.jfree:jfreechart:1.5.4")

    // Web scraping (if needed)
    implementation("org.jsoup:jsoup:1.16.1")

    // HTTP Client
    implementation("org.apache.httpcomponents:httpclient:4.5.14")

    // Date/Time
    implementation("joda-time:joda-time:2.12.5")

    // Logging
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("ch.qos.logback:logback-classic:1.4.8")

    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}



tasks.test {
    useJUnitPlatform()
}

tasks.register<Jar>("customFatJar") {
    manifest {
        attributes["Main-Class"] = "data.CashAllocationApp"
    }
    archiveBaseName.set("cash-allocation-model")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get())
}


