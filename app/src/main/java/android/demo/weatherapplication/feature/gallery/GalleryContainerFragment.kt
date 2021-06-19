package android.demo.weatherapplication.feature.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.demo.weatherapplication.R
import android.demo.weatherapplication.arch.fragments.BaseFragment
import android.demo.weatherapplication.databinding.FragmentGalleryContainerBinding
import android.demo.weatherapplication.feature.gallery.lobby.GalleryLobbyFragment
import androidx.fragment.app.commit

class GalleryContainerFragment :
    BaseFragment<GalleryContainerViewModel, FragmentGalleryContainerBinding>(
        GalleryContainerViewModel::class.java
    ) {

    private lateinit var childFragment: Fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!this::childFragment.isInitialized) {
            childFragment = GalleryLobbyFragment()
            childFragmentManager.commit {
                add(R.id.child_fragment_container, childFragment)
            }
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_gallery_container
    }

    override fun bindViewModelObservers() {
    }
}