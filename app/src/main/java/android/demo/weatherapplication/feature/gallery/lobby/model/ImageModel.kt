package android.demo.weatherapplication.feature.gallery.lobby.model

import android.demo.weatherapplication.feature.gallery.lobby.GalleryLobbyViewModel


data class ImageModel(
    val imageUrl: String,
    val imageClickListener: GalleryLobbyViewModel.ImageClickListener
) {

}