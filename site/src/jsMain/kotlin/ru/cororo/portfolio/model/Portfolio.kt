package ru.cororo.portfolio.model

import kotlinx.serialization.Serializable

/**
 * The whole content of the site. Everything rendered on the page comes from here, so adding a new job,
 * a diploma or a project never requires touching the UI code - see `resources/public/content/portfolio.json`.
 */
@Serializable
data class Portfolio(
    val profile: Profile,
    val experience: List<TimelineEntry> = emptyList(),
    val education: List<TimelineEntry> = emptyList(),
    val projects: List<Project> = emptyList(),
)

@Serializable
data class Profile(
    val name: String,
    val handle: String? = null,
    val tagline: String,
    val location: String? = null,
    val avatar: String? = null,
    val about: List<String> = emptyList(),
    val skills: List<String> = emptyList(),
    val socials: List<Social> = emptyList(),
)

/** [icon] is one of the ids known to `components/Icons.kt`; unknown ids fall back to a generic link glyph. */
@Serializable
data class Social(
    val label: String,
    val url: String,
    val icon: String = "link",
    val primary: Boolean = false,
)

/** A single row of the "Experience" / "Education" timelines. */
@Serializable
data class TimelineEntry(
    val title: String,
    val org: String? = null,
    val period: String? = null,
    val description: String? = null,
    val url: String? = null,
    val tags: List<String> = emptyList(),
)

@Serializable
data class Project(
    val name: String,
    val description: String,
    val year: String? = null,
    val tags: List<String> = emptyList(),
    val stars: Int = 0,
    val featured: Boolean = false,
    val links: List<Social> = emptyList(),
)
