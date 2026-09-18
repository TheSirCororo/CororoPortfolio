package ru.cororo.portfolio.theme

import com.varabyte.kobweb.compose.ui.graphics.Color

/** Every color of the site lives here, so restyling never means grepping through the components. */
object Palette {
    val Background = Color.rgb(0x08090D)
    val Text = Color.rgb(0xEEF0F4)
    val TextMuted = Color.rgb(0x98A0B0)
    val TextFaint = Color.rgb(0x6B7280)
    val Accent = Color.rgb(0x7B8CFF)
    val AccentSoft = Color.rgb(0x2DD4BF)
}

object Css {
    const val SURFACE = "rgba(255, 255, 255, 0.035)"
    const val SURFACE_HOVER = "rgba(255, 255, 255, 0.06)"
    const val BORDER = "rgba(255, 255, 255, 0.09)"
    const val BORDER_STRONG = "rgba(255, 255, 255, 0.18)"

    const val ACCENT_GRADIENT = "linear-gradient(135deg, #7B8CFF 0%, #2DD4BF 100%)"
    const val TEXT_GRADIENT = "linear-gradient(120deg, #FFFFFF 0%, #C3C9FF 45%, #2DD4BF 110%)"

    /** Two soft light spots behind the page - the whole "atmosphere" of the design. */
    const val PAGE_GLOW = "radial-gradient(60% 45% at 15% 0%, rgba(123, 140, 255, 0.18) 0%, rgba(8, 9, 13, 0) 100%), " +
        "radial-gradient(45% 40% at 90% 10%, rgba(45, 212, 191, 0.12) 0%, rgba(8, 9, 13, 0) 100%)"

    const val CARD_SHADOW = "0 1px 0 rgba(255, 255, 255, 0.04) inset, 0 18px 40px -24px rgba(0, 0, 0, 0.9)"
    const val CARD_SHADOW_HOVER =
        "0 1px 0 rgba(255, 255, 255, 0.07) inset, 0 28px 60px -28px rgba(123, 140, 255, 0.45)"

    const val MONO = "'JetBrains Mono', ui-monospace, SFMono-Regular, Menlo, monospace"

    const val EASE = "cubic-bezier(0.22, 1, 0.36, 1)"
}
