package ru.cororo.portfolio.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.JustifyItems
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toAttrs
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import ru.cororo.portfolio.HeadlineStyle
import ru.cororo.portfolio.SubheadlineStyle

@Composable
fun SocialMediaButton(imageUrl: String, mediaUrl: String) {
    Link(mediaUrl, Modifier.gridArea("auto")) {
        Image(imageUrl)
    }
}

@Page
@Composable
fun IndexPage() {
    Box(
        Modifier.fillMaxSize().alignItems(AlignItems.Center).justifyItems(JustifyItems.Center)
    ) {
        Column(Modifier.maxWidth(600.px).alignItems(AlignItems.Center)) {
            Div(
                Modifier.borderRadius(1000.px)
                    .maxWidth(286.px)
                    .maxHeight(286.px)
                    .toAttrs()
            ) {
                Image("/my_photo.png")
            }

            Div(HeadlineStyle.toAttrs()) {
                SpanText("Artem Musatenko")
            }

            Div(SubheadlineStyle.toAttrs()) {
                SpanText("17 y.o. software engineer")
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .gap(50.px)
                    .grid {
                        columns { repeat(4) { size(1.fr) } }
                        rows { repeat(1) { size(1.fr) } }
                    }
                    .margin(top = 20.px)
            ) {
                SocialMediaButton("telegram_logo.svg", "https://t.me/cororo")
                SocialMediaButton("github_logo.svg", "https://github.com/TheSirCororo")
                SocialMediaButton("vk_logo.svg", "https://vk.com/cororo2021")
                SocialMediaButton("mail_logo.svg", "mailto:artem.cororo@gmail.com")
            }
        }
    }
}