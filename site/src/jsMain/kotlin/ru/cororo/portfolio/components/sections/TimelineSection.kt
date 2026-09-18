package ru.cororo.portfolio.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*
import ru.cororo.portfolio.components.SectionHeader
import ru.cororo.portfolio.components.SiteLink
import ru.cororo.portfolio.components.TagRow
import ru.cororo.portfolio.model.TimelineEntry
import ru.cororo.portfolio.theme.*

/** Renders any list of dated entries - jobs, studies, awards - as a vertical timeline. */
@Composable
fun TimelineSection(id: String, index: String, title: String, entries: List<TimelineEntry>) {
    if (entries.isEmpty()) return

    Section(SectionStyle.toModifier().toAttrs { id(id) }) {
        SectionHeader(index, title)

        Div(TimelineStyle.toAttrs()) {
            entries.forEach { entry -> TimelineItem(entry) }
        }
    }
}

@Composable
private fun TimelineItem(entry: TimelineEntry) {
    Div(TimelineItemStyle.toAttrs()) {
        entry.period?.let { Div(MetaTextStyle.toModifier().margin(bottom = 6.px).toAttrs()) { Text(it) } }

        Div(TimelineTitleStyle.toAttrs()) { Text(entry.title) }

        entry.org?.let { org ->
            Div(Modifier.margin(top = 3.px).toAttrs()) {
                if (entry.url != null) {
                    SiteLink(entry.url, TimelineOrgStyle.toModifier()) { Text(org) }
                } else {
                    Span(TimelineOrgStyle.toAttrs()) { Text(org) }
                }
            }
        }

        entry.description?.let { Div(TimelineDescriptionStyle.toAttrs()) { Text(it) } }

        if (entry.tags.isNotEmpty()) {
            Div(RowWrapStyle.toModifier().margin(top = 12.px).toAttrs()) {
                TagRow(entry.tags)
            }
        }
    }
}
