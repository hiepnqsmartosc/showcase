package tech.central.showcase.post

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import tech.central.showcase.base.SchedulersFacade
import tech.central.showcase.base.model.Post
import tech.central.showcase.post.usecase.LoadPostUseCase

class PostViewModel(
        application: Application,
        private val loadPostUseCase: LoadPostUseCase,
        private val schedulersFacade: SchedulersFacade
) : AndroidViewModel(application) {
    companion object {
        private val TAG = PostViewModel::class.java.simpleName
    }

    protected val disposables by lazy { CompositeDisposable() }

    private val postListLiveData by lazy { MutableLiveData<List<Post>>() }

    fun bindPostListLiveData(): LiveData<List<Post>> {
        if (postListLiveData.value == null) {
            loadPosts()
        }

        return postListLiveData
    }

    private fun loadPosts() {
        disposables += loadPostUseCase.execute()
                .subscribeOn(schedulersFacade.io)
                .observeOn(schedulersFacade.ui)
                .doOnSubscribe {
                    postListLiveData.value = null
                }
                .subscribeBy(
                        onError = {
                            postListLiveData.value = emptyList()
                        },
                        onSuccess = {
                            postListLiveData.value = it
                        }
                )
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}