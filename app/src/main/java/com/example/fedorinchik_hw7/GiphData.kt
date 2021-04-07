package com.example.fedorinchik_hw7

data class GifData(
    val data: List<Data>
)

data class Data(
    val content_url: String,
    val id: String,
    val images: Images,
    val title: String,
    val url: String,
    val username: String
)

data class Images(
    val original: Original
)

data class Original(
    val frames: String,
    val hash: String,
    val height: String,
    val size: String,
    val url: String,
    val webp: String,
    val webp_size: String,
    val width: String
)
