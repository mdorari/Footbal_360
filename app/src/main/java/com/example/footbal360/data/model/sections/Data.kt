package com.example.footbal360.data.model.sections

data class Data(
    val default_cover: Any,
    val id: String,
    val key: String,
    val mobile_order: Int,
    val order: Int,
    val page: String,
    val posts: List<Post>,
    val redirect_to: Any,
    val section_type: SectionType,
    val section_type_mobile: SectionTypeMobile,
    val slug: Any,
    val sort_order: String,
    val title: String
)