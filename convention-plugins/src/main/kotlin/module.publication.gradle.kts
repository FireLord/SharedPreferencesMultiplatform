import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // disabled https://github.com/vanniktech/gradle-maven-publish-plugin/issues/754
        // and configured at library build.gradle.kts using `JavadocJar.Dokka("dokkaHtml")`.
        /*
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })*/

        // Provide artifacts information required by Maven Central
        pom {
            name.set("Shared Preference Multiplatform library")
            description.set("Shared Preference Multiplatform library for Kotlin Multiplatform to store key-value pairs")
            url.set("https://github.com/FireLord/SharedPreferencesMultiplatform")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("FireLord")
                    name.set("FireLord Team")
                    organization.set("FireLord")
                    organizationUrl.set("https://firelord.github.io/")
                }
            }
            scm {
                url.set("https://github.com/FireLord/SharedPreferencesMultiplatform")
            }
        }
    }
}

signing {
    if (project.hasProperty("mavenCentralUsername") ||
        System.getenv("ORG_GRADLE_PROJECT_mavenCentralUsername") != null) {
        useGpgCmd()
        // It is not perfect (fails at some dependency assertions), better handled as
        // `signAllPublications()` (as in vanniktech maven publish plugin) at build.gradle.kts.
        //sign(publishing.publications)
    }
}
