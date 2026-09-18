package ru.cororo.portfolio.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*
import ru.cororo.portfolio.components.*
import ru.cororo.portfolio.model.Profile
import ru.cororo.portfolio.theme.*

@Composable
fun Hero(profile: Profile) {
    Section(HeroStyle.toModifier().toAttrs { id("top") }) {
        Div {
            H1(NameStyle.toAttrs()) { Text(profile.name) }

            Div(TaglineStyle.toAttrs()) { Text(profile.tagline) }

            profile.location?.let { location ->
                Div(RowWrapStyle.toModifier().margin(top = 16.px).toAttrs()) {
                    IconChip("pin", location)
                }
            }

            profile.about.forEach { paragraph ->
                P(AboutStyle.toAttrs()) { Text(paragraph) }
            }

            if (profile.socials.isNotEmpty()) {
                Div(RowWrapStyle.toModifier().margin(top = 26.px).toAttrs()) {
                    profile.socials.forEach { social ->
                        if (social.primary) PrimaryLink(social) else GhostLink(social)
                    }
                }
            }
        }

        profile.avatar?.let { avatar ->
            Div(AvatarRingStyle.toAttrs()) {
                Img(src = avatar, alt = profile.name, attrs = AvatarStyle.toModifier().toAttrs())
            }
        }
    }
}

@Composable
fun SkillsSection(profile: Profile, index: String) {
    if (profile.skills.isEmpty()) return

    Section(SectionStyle.toModifier().toAttrs { id("stack") }) {
        SectionHeader(index, "Stack")
        Div(RowWrapStyle.toModifier().toAttrs()) {
            profile.skills.forEach { Chip(it) }
        }
    }
}
