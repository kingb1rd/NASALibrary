package com.infoechebo.nasalibrary.data.remote.dto

data class Item(
    val data: List<Data>,
    val href: String,
    val links: List<Link>
)