package android.demo.weatherapplication.feature.gallery.lobby

import android.demo.weatherapplication.arch.view_model.BaseViewModel
import android.demo.weatherapplication.arch.view_model.LiveDataEventWrapper
import android.demo.weatherapplication.feature.gallery.lobby.model.ImageModel
import android.demo.weatherapplication.feature.gallery.lobby.model.NavigationParams
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GalleryLobbyViewModel : BaseViewModel() {

    // Due to time limits, urls are put in GalleryLobbyViewModel instead of being fetched from repository
    private val images = MutableLiveData<ArrayList<ImageModel>>()
    private val navigationEvent = MutableLiveData<LiveDataEventWrapper<NavigationParams>>()

    fun getImages(): LiveData<ArrayList<ImageModel>> {
        return images
    }

    fun getNavigationEvent(): LiveData<LiveDataEventWrapper<NavigationParams>> {
        return navigationEvent
    }

    override fun refreshData() {
        initData()
    }

    private fun initData() {
        val host = "https://homepages.cae.wisc.edu/~ece533/images/"

        val imageSuffixs = arrayOf(
            "airplane.png",
            "arctichare.png",
            "baboon.png",
            "serrano.png",
            "tulips.png",
            "watch.png",
            "zelda.png",
            "monarch.png",
            "peppers.png",
            "pool.png"
        )
        val imageList = ArrayList<ImageModel>()
        for (suffix in imageSuffixs) {
            val imageUrl = host + suffix
            imageList.add(ImageModel(imageUrl, object : ImageClickListener {
                override fun onImageClicked(
                    imageUrl: String,
                    transitionParams: Pair<View, String>
                ) {
                    navigationEvent.value = LiveDataEventWrapper(
                        NavigationParams(
                            imageUrl,
                            transitionParams.second,
                            transitionParams.first
                        )
                    )
                }
            }))
        }
        images.value = imageList
    }

    interface ImageClickListener {
        fun onImageClicked(imageUrl: String, transitionParams: Pair<View, String>)
    }
}