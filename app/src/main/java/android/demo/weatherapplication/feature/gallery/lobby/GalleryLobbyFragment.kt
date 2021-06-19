package android.demo.weatherapplication.feature.gallery.lobby

import androidx.recyclerview.widget.GridLayoutManager
import android.demo.weatherapplication.R
import android.demo.weatherapplication.arch.fragments.BaseFragment
import android.demo.weatherapplication.feature.gallery.lobby.adapter.GalleryImageRecyclerViewAdapter
import android.demo.weatherapplication.databinding.FragmentGalleryLobbyBinding
import android.demo.weatherapplication.feature.gallery.detail.GalleryImageDetailFragment
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration

/**
 * A fragment representing a list of Items.
 */
class GalleryLobbyFragment :
    BaseFragment<GalleryLobbyViewModel, FragmentGalleryLobbyBinding>(GalleryLobbyViewModel::class.java) {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_gallery_lobby
    }


    override fun initViewBinding() {
        super.initViewBinding()
        postponeEnterTransition()
        viewBinding.apply {
            recyclerview.layoutManager = GridLayoutManager(context, 2)
            recyclerview.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.HORIZONTAL
                )
            )
            recyclerview.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            recyclerview.adapter = GalleryImageRecyclerViewAdapter()

        }
    }

    override fun bindViewModelObservers() {
        viewModel.getImages().observe(viewLifecycleOwner) {
            (viewBinding.recyclerview.adapter as GalleryImageRecyclerViewAdapter).setValues(it)
            (viewBinding.root as? ViewGroup)?.doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
        viewModel.getNavigationEvent().observe(viewLifecycleOwner) { paramsWrapper ->
            paramsWrapper.getContentIfNotHandled()?.let { params ->
                val fragment =
                    GalleryImageDetailFragment.newInstance(params.imageUrl, params.transitionNames)
                parentFragmentManager.commit {
                    replace(R.id.child_fragment_container, fragment)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                    addSharedElement(params.transitionedView, params.transitionNames)
                }
            }
        }
    }
}