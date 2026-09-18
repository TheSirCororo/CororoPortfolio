package ru.cororo.portfolio.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*
import ru.cororo.portfolio.components.*
import ru.cororo.portfolio.model.Project
import ru.cororo.portfolio.theme.*

@Composable
fun ProjectsSection(index: String, projects: List<Project>) {
    if (projects.isEmpty()) return

    // Pinned projects first, then the most starred ones - the JSON stays in whatever order is convenient to edit.
    val ordered = projects.sortedWith(compareByDescending<Project> { it.featured }.thenByDescending { it.stars })

    Section(SectionStyle.toModifier().toAttrs { id("projects") }) {
        SectionHeader(index, "Projects")

        Div(CardGridStyle.toAttrs()) {
            ordered.forEach { ProjectCard(it) }
        }
    }
}

@Composable
private fun ProjectCard(project: Project) {
    Div(ProjectCardStyle.toAttrs()) {
        Div(CardHeadStyle.toAttrs()) {
            H3(ProjectTitleStyle.toAttrs()) { Text(project.name) }

            Div(CardHeadMetaStyle.toAttrs()) {
                if (project.stars > 0) {
                    Span(StarBadgeStyle.toAttrs()) {
                        Icon("star", size = 12)
                        Text(project.stars.toString())
                    }
                }
                project.year?.let { Span(MetaTextStyle.toAttrs()) { Text(it) } }
            }
        }

        P(ProjectDescriptionStyle.toAttrs()) { Text(project.description) }

        TagRow(project.tags)

        if (project.links.isNotEmpty()) {
            Div(RowWrapStyle.toModifier().margin(top = 4.px).toAttrs()) {
                project.links.forEach { link ->
                    SiteLink(link.url, CardLinkStyle.toModifier()) {
                        Icon(link.icon, size = 14)
                        Text(link.label)
                    }
                }
            }
        }
    }
}
