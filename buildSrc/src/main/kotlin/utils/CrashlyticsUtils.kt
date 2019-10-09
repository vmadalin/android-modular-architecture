import org.gradle.api.Project
import java.io.File
import java.lang.Exception

private const val FABRIC_PROPERTIES_FILE_NAME = "fabric.properties"
private const val FABRIC_API_KEY_PROPERTY_NAME = "fabric.key"
private const val FABRIC_API_SECRET_PROPERTY_NAME = "fabric.secret"

fun createFabricProperties(project: Project) {
    val fabricPropertiesFile = File(FABRIC_PROPERTIES_FILE_NAME)
    val fabricApiKey = getLocalProperty(FABRIC_API_KEY_PROPERTY_NAME, project)
    val fabricApiSecret = getLocalProperty(FABRIC_API_SECRET_PROPERTY_NAME, project)

    if (!fabricPropertiesFile.exists()) {
        if (fabricApiKey.isEmpty() || fabricApiSecret.isEmpty()) {
            throw Exception()
        } else {
            fabricPropertiesFile.printWriter().use { output ->
                output.println("apiKey=$fabricApiKey")
                output.println("apiSecret=$fabricApiSecret")
            }
        }
    }
}
