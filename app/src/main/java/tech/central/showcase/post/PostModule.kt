package tech.central.showcase.post

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyItemSpacingDecorator
import dagger.Module
import dagger.Provides
import tech.central.showcase.di.qualifier.ActivityContext
import tech.central.showcase.R

@Module
class PostModule {
    @Provides
    fun provideLayoutManager(@ActivityContext context: Context) = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

    @Provides
    fun provideItemDecoration(@ActivityContext context: Context) = EpoxyItemSpacingDecorator(context.resources.getDimensionPixelSize(R.dimen.spacing_photo))
}