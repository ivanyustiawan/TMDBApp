package navigator

import android.content.Intent

interface ActivityNavigator {
    fun navigateTo(
        flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK,
        targetClassName: String,
        extras: Map<String, Any>? = null
    )
}
