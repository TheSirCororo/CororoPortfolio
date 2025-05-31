package ru.cororo.portfolio

import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import org.jetbrains.compose.web.css.px

@InitSilk
fun initSilkStyles(ctx: InitSilkContext) {
    ctx.stylesheet.registerStyleBase("body") {
        Modifier.fontFamily("Inter")
    }
}

val HeadlineStyle = CssStyle.base {
    Modifier.fontWeight(FontWeight.SemiBold)
        .fontSize(52.px)
        .textAlign(TextAlign.Center)
        .color(Color.rgba(245, 246, 246, 255))
}

val SubheadlineStyle = CssStyle.base {
    Modifier.fontWeight(FontWeight.Medium)
        .fontSize(28.px)
        .color(Color.rgba(214, 214, 214, 255))
}