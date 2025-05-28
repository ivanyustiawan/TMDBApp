package navigator

import android.app.Activity
import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.FormattableFlags
import javax.inject.Inject

class ActivityNavigatorImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ActivityNavigator {
    override fun navigateTo(
        flags: Int,
        targetClassName: String,
        extras: Map<String, Any>?
    ) {

        try {
            val className = Class.forName(targetClassName)
            val activityClass = className as Class<out Activity>
            val intent = Intent(context, activityClass).apply {
                addFlags(flags)
                extras?.forEach { (key, value) ->
                    when (value) {
                        is String -> putExtra(key, value)
                        is Int -> putExtra(key, value)
                        is Boolean -> putExtra(key, value)
                    }
                }
            }
            context.startActivity(intent)
        } catch (e: ClassNotFoundException) {
            throw e
        }
    }
}