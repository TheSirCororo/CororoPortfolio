package ru.cororo.portfolio.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Main
import org.jetbrains.compose.web.dom.Text
import ru.cororo.portfolio.components.sections.*
import ru.cororo.portfolio.content.PortfolioState
import ru.cororo.portfolio.content.rememberPortfolio
import ru.cororo.portfolio.model.Portfolio
import ru.cororo.portfolio.theme.ContainerStyle
import ru.cororo.portfolio.theme.StatusStyle

@Page
@Composable
fun IndexPage() {
    when (val state = rememberPortfolio()) {
        is PortfolioState.Loading -> Status("loading…")
        is PortfolioState.Failed -> Status("Could not load the content: ${state.message}")
        is PortfolioState.Ready -> PortfolioPage(state.portfolio)
    }
}

@Composable
private fun PortfolioPage(portfolio: Portfolio) {
    document.title = "${portfolio.profile.name} — ${portfolio.profile.tagline}"

    NavHeader(portfolio)

    Main(ContainerStyle.toAttrs()) {
        Hero(portfolio.profile)

        // Section numbers are assigned here so that hiding a section in the JSON never leaves a gap.
        var number = 0
        fun next() = (++number).toString().padStart(2, '0')

        SkillsSection(portfolio.profile, next())
        TimelineSection("experience", next(), "Experience", portfolio.experience)
        TimelineSection("education", next(), "Education & awards", portfolio.education)
        ProjectsSection(next(), portfolio.projects)

        PageFooter(portfolio.profile)
    }
}

@Composable
private fun Status(message: String) {
    Div(ContainerStyle.toModifier().toAttrs()) {
        Div(StatusStyle.toAttrs()) { Text(message) }
    }
}
