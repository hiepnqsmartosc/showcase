package tech.central.showcase.post_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_post_detail.*
import tech.central.showcase.base.BaseFragment
import tech.central.showcase.base.model.Post
import javax.inject.Inject
import tech.central.showcase.R

class PostDetailFragment : BaseFragment() {
    companion object {
        @JvmStatic
        private val TAG = PostDetailFragment::class.java.simpleName

        private const val ARG_POST = "ARG_POST"

        @JvmStatic
        fun newBundle(post: Post): Bundle {
            val bundle = Bundle()
            bundle.putParcelable(ARG_POST, post)
            return bundle
        }
    }

    //Injection
    @Inject
    lateinit var mPostDetailViewModelFactory: PostDetailViewModelFactory

    //Data Members
    private val mPostDetailViewModel by lazy { ViewModelProviders.of(this, mPostDetailViewModelFactory).get(PostDetailViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        arguments?.apply {
            mPostDetailViewModelFactory.post = getParcelable(ARG_POST) as Post
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_post_detail, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance(view, savedInstanceState)

        //Register ViewModel
        mPostDetailViewModel.bindPostLiveData()
                .observe(this, Observer {
                    it?.let {
                        textView.text = it.body
                    }
                })
    }

    private fun initInstance(rootView: View?, savedInstanceState: Bundle?) {
        //Init View instance

    }
}