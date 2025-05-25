package navigator

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import javax.inject.Inject

class ActivityNavigatorImpl @Inject constructor() : ActivityNavigator {
    override fun navigateTo(
        context: Context,
        target: Class<out Activity>,
        extras: Bundle?
    ) {
        val intent = Intent(context, target).apply {
            extras?.let { putExtras(it) }
        }
        context.startActivity(intent)
    }
}