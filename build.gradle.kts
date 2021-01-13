import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("fabric-loom") version "0.5-SNAPSHOT"
	id("maven-publish")
	kotlin("jvm") version "1.4.0"
	id("base")
}

fun property(propertyName: String): Any = project.property(propertyName)!!

base.archivesBaseName = property("archives_base_name").toString()
version = property("mod_version")
group = property("maven_group")

repositories {
	maven("http://maven.fabricmc.net/")
	maven("https://raw.githubusercontent.com/Devan-Kerman/Devan-Repo/master/")
}

dependencies {
	//to change the versions see the gradle.properties file
	minecraft("com.mojang:minecraft:${property("minecraft_version")}")
	mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
	modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")

	// Fabric API. This is technically optional, but you probably want it anyway.
	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")

	modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")

	// Cloth API - for data gen
	modApi("me.shedaniel.cloth.api:cloth-api:${property("cloth_api_version")}")
	include("me.shedaniel.cloth.api:cloth-api:${property("cloth_api_version")}")

	// ARRP API - for lang data gen
	modImplementation("net.devtech:arrp:${property("arrp_version")}")

	// PSA: Some older mods, compiled on Loom 0.2.1, might have outdated Maven POMs.
	// You may need to force-disable transitiveness on them.
}

// ensure that the encoding is set to UTF-8, no matter what the system default is
// this fixes some edge cases with special characters not displaying correctly
// see http://yodaconditions.net/blog/fix-for-java-file-encoding-problems-with-gradle.html
tasks {
	withType<JavaCompile> {
		options.encoding = "UTF-8"
	}

	withType<KotlinCompile> {
		kotlinOptions.jvmTarget = "1.8"
	}

	processResources {
		inputs.property("version", project.version)

		// filesMatching(sourceSets.main.resources.srcDirs) {
		//     filesMatching("fabric.mod.json")
		//     expand("version" to project.version)
		// }
		//
		// from(sourceSets.main.resources.srcDirs) {
		//     exclude("fabric.mod.json")
		// }
	}
}

// add generated models to resources
sourceSets["main"].resources {
	srcDir("src/generated/resources")
}

java { withSourcesJar() }

// configure the maven publication
publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			// add all the jars that should be included when publishing to maven
			artifact(tasks.remapJar.get()) {
				builtBy(tasks.remapJar.get())
			}
			artifact(tasks["sourcesJar"]) {
				builtBy(tasks.remapSourcesJar.get())
			}
		}
	}

	// Select the repositories you want to publish to
	// To publish to maven local, no extra repositories are necessary. Just use the task `publishToMavenLocal`.
	repositories {
		// See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
	}
}