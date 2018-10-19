package tech.central.showcase.post.usecase

import io.reactivex.Single
import tech.central.showcase.base.model.Post
import tech.central.showcase.base.provider.MockServiceProvider
import javax.inject.Inject

class LoadPostUseCase @Inject constructor(
        private val mockServiceProvider: MockServiceProvider
) {
    fun execute(): Single<List<Post>> {
        return mockServiceProvider.posts()
    }
}