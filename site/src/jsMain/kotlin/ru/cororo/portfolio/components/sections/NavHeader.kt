package ru.cororo.portfolio.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text
import ru.cororo.portfolio.components.IconLink
import ru.cororo.portfolio.components.SiteLink
import ru.cororo.portfolio.model.Portfolio
import ru.cororo.portfolio.theme.*

@Composable
fun NavHeader(portfolio: Portfolio) {
    val sections = buildList {
        if (portfolio.profile.skills.isNotEmpty()) add("stack" to "Stack")
        if (portfolio.experience.isNotEmpty()) add("experience" to "Experience")
        if (portfolio.education.isNotEmpty()) add("education" to "Education")
        if (portfolio.projects.isNotEmpty()) add("projects" to "Projects")
    }

    Header(NavStyle.toAttrs()) {
        Div(ContainerStyle.toModifier().toAttrs()) {
            Div(NavInnerStyle.toAttrs()) {
                SiteLink("#top", BrandStyle.toModifier()) {
                    Text(portfolio.profile.handle?.let { "@$it" } ?: portfolio.profile.name)
                }

                Nav(NavLinksStyle.toAttrs()) {
                    sections.forEach { (id, label) ->
                        SiteLink("#$id", NavLinkStyle.toModifier()) { Text(label) }
                    }
                }

                Div(RowWrapStyle.toModifier().toAttrs()) {
                    portfolio.profile.socials.forEach { IconLink(it) }
                }
            }
        }
    }
}
