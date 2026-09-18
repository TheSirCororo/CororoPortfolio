package ru.cororo.portfolio.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import ru.cororo.portfolio.model.Social
import ru.cororo.portfolio.theme.*

/** A plain `<a>`: external links open in a new tab, in-page anchors do not. */
@Composable
fun SiteLink(href: String, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val external = !href.startsWith("#") && !href.startsWith("/")
    A(
        href = href,
        attrs = modifier.toAttrs {
            if (external) {
                attr("target", "_blank")
                attr("rel", "noopener noreferrer")
            }
        }
    ) { content() }
}

@Composable
fun SectionHeader(index: String, title: String) {
    Div(SectionHeaderStyle.toAttrs()) {
        Span(SectionIndexStyle.toAttrs()) { Text(index) }
        H2(SectionTitleStyle.toAttrs()) { Text(title) }
        Div(SectionRuleStyle.toAttrs()) {}
    }
}

@Composable
fun Chip(text: String) {
    Span(ChipStyle.toAttrs()) { Text(text) }
}

@Composable
fun Tag(text: String) {
    Span(TagStyle.toAttrs()) { Text(text) }
}

@Composable
fun TagRow(tags: List<String>) {
    if (tags.isEmpty()) return
    Div(RowWrapStyle.toModifier().toAttrs()) {
        tags.forEach { Tag(it) }
    }
}

/** Square icon-only link, used for the social buttons in the header. */
@Composable
fun IconLink(social: Social) {
    SiteLink(social.url, IconButtonStyle.toModifier()) {
        Icon(social.icon, size = 18)
    }
}

@Composable
fun PrimaryLink(social: Social) {
    SiteLink(social.url, PrimaryButtonStyle.toModifier()) {
        Icon(social.icon, size = 17)
        Text(social.label)
    }
}

@Composable
fun GhostLink(social: Social) {
    SiteLink(social.url, GhostButtonStyle.toModifier()) {
        Icon(social.icon, size = 17)
        Text(social.label)
    }
}

/** A chip with a leading glyph, e.g. a location marker. */
@Composable
fun IconChip(icon: String, text: String) {
    Span(ChipStyle.toAttrs()) {
        Span(IconChipInnerStyle.toAttrs()) {
            Icon(icon, size = 13)
            Text(text)
        }
    }
}
