package tech.central.showcase.post_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import tech.central.showcase.base.SchedulersFacade
import tech.central.showcase.base.model.Post

class PostDetailViewModel(
        application: Application,
        private val post: Post,
        private val schedulersFacade: SchedulersFacade
) : AndroidViewModel(application) {
    companion object {
        private val TAG = PostDetailViewModel::class.java.simpleName
    }

    protected val disposables by lazy { CompositeDisposable() }

    private val postLiveData by lazy { MutableLiveData<Post>() }

    fun bindPostLiveData(): LiveData<Post> {
        if (postLiveData.value == null) {
            postLiveData.value = post
        }
        return postLiveData
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}