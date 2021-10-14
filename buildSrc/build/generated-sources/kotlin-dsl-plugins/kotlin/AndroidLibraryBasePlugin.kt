/**
 * Precompiled [android-library-base.gradle.kts][Android_library_base_gradle] script plugin.
 *
 * @see Android_library_base_gradle
 */
class AndroidLibraryBasePlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Android_library_base_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
