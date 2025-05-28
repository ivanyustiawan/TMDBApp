package di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import navigator.ActivityNavigator
import navigator.ActivityNavigatorImpl

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    fun provideActivityNavigator(context: Context): ActivityNavigator {
        return ActivityNavigatorImpl(context = context)
    }
}