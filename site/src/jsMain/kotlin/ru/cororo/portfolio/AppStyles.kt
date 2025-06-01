package ru.cororo.portfolio

import com.varabyte.kobweb.compose.css.AlignItems
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.JustifyItems
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.CssStyleScope
import org.jetbrains.compose.web.css.CSSMediaQuery
import org.jetbrains.compose.web.css.StylePropertyValue
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.px

@InitSilk
fun initSilkStyles(ctx: InitSilkContext) {
    ctx.stylesheet.registerStyleBase("body") {
        Modifier.fontFamily("Inter")
    }
}

val HeadlineStyle = CssStyle {
    base {
        Modifier.fontWeight(FontWeight.SemiBold)
            .fontSize(52.px)
            .textAlign(TextAlign.Center)
            .color(Color.rgba(245, 246, 246, 255))
    }

    media600px {
        Modifier.fontSize(40.px)
            .margin(top = 20.px)
    }
}

val SubheadlineStyle = CssStyle {
    base {
        Modifier.fontWeight(FontWeight.Medium)
            .fontSize(28.px)
            .color(Color.rgba(214, 214, 214, 255))
    }

    media600px {
        Modifier.fontSize(20.px)
    }
}

val SocialMediaGridStyle = CssStyle {
    base {
        Modifier
            .fillMaxWidth()
            .gap(50.px)
            .grid {
                columns { repeat(4) { size(1.fr) } }
                rows { repeat(1) { size(1.fr) } }
            }
            .justifyItems(JustifyItems.Center)
            .alignItems(AlignItems.Center)
            .margin(top = 5.px)
    }

    media600px {
        Modifier
            .grid {
                columns { repeat(2) { size(1.fr) } }
                rows { repeat(2) { size(1.fr) } }
            }
            .gap(0.px)
            .margin(top = 30.px)
    }
}

private fun CssStyleScope.media600px(createModifier: () -> Modifier) =
    cssRule(CSSMediaQuery.MediaFeature("max-width", StylePropertyValue("600px"))) {
        createModifier()
    }
