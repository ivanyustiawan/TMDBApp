package di

import navigator.ActivityNavigator
import navigator.ActivityNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    fun provideActivityNavigator(): ActivityNavigator {
        return ActivityNavigatorImpl()
    }
}