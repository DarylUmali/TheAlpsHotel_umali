package ph.edu.comteq.thealpshotel_umali

import android.graphics.Bitmap

data class Hotel(
    val hotel_id: Int,
    val hotel_name: String,
    val hotel_rating: Int,
    val hotel_to_ski_distance: Int,
    val hotel_cover_image: Bitmap
)
