plugins {
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

allprojects {
	group = "com.github.sourcefranke"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "java")

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		testImplementation("org.springframework.boot:spring-boot-starter-test") {
			exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		}
	}
}
