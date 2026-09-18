package ru.cororo.portfolio

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import org.jetbrains.compose.web.css.*
import ru.cororo.portfolio.theme.Css
import ru.cororo.portfolio.theme.Palette

@InitSilk
fun initStyles(ctx: InitSilkContext) {
    ctx.stylesheet.apply {
        registerStyleBase("html") { Modifier.scrollBehavior(ScrollBehavior.Smooth) }

        registerStyleBase("body") {
            Modifier.fillMaxWidth()
                .minHeight(100.vh)
                .margin(0.px)
                .fontFamily("Inter", "system-ui", "sans-serif")
                .color(Palette.Text)
                .backgroundColor(Palette.Background)
                .styleModifier {
                    property("background-image", Css.PAGE_GLOW)
                    property("background-repeat", "no-repeat")
                    property("-webkit-font-smoothing", "antialiased")
                }
        }

        // Links and headings are styled per component - this rule only drops the browser defaults.
        // Setting a color here would win over the component styles, so every link style sets its own.
        registerStyleBase("a") { Modifier.textDecorationLine(TextDecorationLine.None) }
        registerStyleBase("h1, h2, h3, p") { Modifier.margin(0.px) }
        registerStyleBase("::selection") {
            Modifier.color(Palette.Background).backgroundColor(Palette.Accent)
        }
    }
}

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        content()
    }
}
