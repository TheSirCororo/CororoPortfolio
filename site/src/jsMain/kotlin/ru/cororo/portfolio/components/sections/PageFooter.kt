package ru.cororo.portfolio.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Footer
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import ru.cororo.portfolio.components.SiteLink
import ru.cororo.portfolio.model.Profile
import ru.cororo.portfolio.theme.FooterStyle
import ru.cororo.portfolio.theme.FooterLinkStyle
import ru.cororo.portfolio.theme.RowWrapStyle

private const val SOURCE_URL = "https://github.com/TheSirCororo/CororoPortfolio"

@Composable
fun PageFooter(profile: Profile) {
    Footer(FooterStyle.toAttrs()) {
        Span { Text("© 2026 ${profile.name}") }

        Div(RowWrapStyle.toModifier().toAttrs()) {
            Span { Text("Kotlin/JS · Kobweb") }
            SiteLink(SOURCE_URL, FooterLinkStyle.toModifier()) { Text("source") }
        }
    }
}
