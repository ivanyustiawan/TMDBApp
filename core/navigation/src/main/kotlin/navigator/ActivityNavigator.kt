package navigator

interface ActivityNavigator {
    fun navigateTo(
        targetClassName: String,
        extras: Map<String, Any>? = null
    )
}
