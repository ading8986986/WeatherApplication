package android.demo.weatherapplication.feature.gallery.detail

import android.os.Bundle
import android.view.View
import android.demo.weatherapplication.R
import android.demo.weatherapplication.arch.fragments.BaseFragment
import android.demo.weatherapplication.databinding.FragmentImageDetailBinding
import android.graphics.drawable.Drawable
import androidx.core.view.ViewCompat
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

private const val TRANSITION_NAMES = "transitionNames"
private const val IMAGE_URL = "imageUrl"


class GalleryImageDetailFragment :
    BaseFragment<GalleryImageDetailViewModel, FragmentImageDetailBinding>(
        GalleryImageDetailViewModel::class.java
    ) {
    private var imageUrl: String? = null
    private var transitionName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUrl = it.getString(IMAGE_URL)
            transitionName = it.getString(TRANSITION_NAMES)
        }
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun initViewBinding() {
        super.initViewBinding()
        ViewCompat.setTransitionName(viewBinding.imagePreview, transitionName)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        Glide.with(requireContext()).load(imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

            })
            .into(viewBinding.imagePreview)
        viewBinding.imagePreview.setOnClickListener {
            childFragmentManager.popBackStack()
        }
    }




    companion object {

        @JvmStatic
        fun newInstance(imageUrl: String, transitionName : String ) =
            GalleryImageDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(TRANSITION_NAMES, transitionName)
                    putString(IMAGE_URL, imageUrl)
                }
            }

    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_image_detail
    }

    override fun bindViewModelObservers() {
    }
}