import android.app.Activity
import android.content.Context
import android.os.Bundle

interface ActivityNavigator {
    fun navigateTo(
        context: Context,
        target: Class<out Activity>,
        extras: Bundle? = null
    )
}

//pemakaian
//@Inject lateinit var activityNavigator: ActivityNavigator
//val extras = bundleOf("userId" to "12345")
//navigator.navigateTo(requireContext(), LoginActivity::class.java, extras)