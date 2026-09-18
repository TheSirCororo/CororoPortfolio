package ru.cororo.portfolio.theme

import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

private fun Modifier.transitionAll(duration: String = "0.25s") =
    styleModifier { property("transition", "all $duration ${Css.EASE}") }

/** `Modifier.fontFamily()` quotes every argument, so a ready-made font stack has to be set as a raw property. */
private fun Modifier.mono() = styleModifier { property("font-family", Css.MONO) }

// ---------------------------------------------------------------- layout

val ContainerStyle = CssStyle {
    base {
        Modifier.fillMaxWidth()
            .maxWidth(1040.px)
            .margin(leftRight = autoLength)
            .padding(leftRight = 20.px)
    }
    Breakpoint.MD { Modifier.padding(leftRight = 32.px) }
}

val SectionStyle = CssStyle {
    base { Modifier.fillMaxWidth().padding(topBottom = 44.px).styleModifier { property("scroll-margin-top", "84px") } }
    Breakpoint.MD { Modifier.padding(topBottom = 56.px) }
}

val SectionHeaderStyle = CssStyle.base {
    Modifier.fillMaxWidth()
        .display(DisplayStyle.Flex)
        .alignItems(AlignItems.Baseline)
        .gap(12.px)
        .margin(bottom = 26.px)
}

val SectionIndexStyle = CssStyle.base {
    Modifier.mono()
        .fontSize(13.px)
        .color(Palette.Accent)
        .styleModifier { property("letter-spacing", "0.08em") }
}

val SectionTitleStyle = CssStyle {
    base { Modifier.fontSize(26.px).fontWeight(FontWeight.SemiBold).color(Palette.Text).margin(0.px) }
    Breakpoint.MD { Modifier.fontSize(30.px) }
}

val SectionRuleStyle = CssStyle.base {
    Modifier.height(1.px)
        .styleModifier {
            property("flex", "1")
            property("background", "linear-gradient(90deg, ${Css.BORDER} 0%, rgba(255,255,255,0) 100%)")
        }
}

// ---------------------------------------------------------------- nav

val NavStyle = CssStyle.base {
    Modifier.fillMaxWidth()
        .position(Position.Sticky)
        .top(0.px)
        .zIndex(100)
        .padding(topBottom = 12.px)
        .styleModifier {
            property("background", "rgba(8, 9, 13, 0.72)")
            property("backdrop-filter", "blur(14px)")
            property("-webkit-backdrop-filter", "blur(14px)")
            property("border-bottom", "1px solid ${Css.BORDER}")
        }
}

val NavInnerStyle = CssStyle.base {
    Modifier.fillMaxWidth()
        .display(DisplayStyle.Flex)
        .alignItems(AlignItems.Center)
        .justifyContent(JustifyContent.SpaceBetween)
        .gap(16.px)
}

val BrandStyle = CssStyle {
    base {
        Modifier.mono()
            .fontSize(15.px)
            .fontWeight(FontWeight.Medium)
            .color(Palette.Text)
            .transitionAll()
    }
    hover { Modifier.color(Palette.Accent) }
}

val NavLinksStyle = CssStyle {
    base { Modifier.display(DisplayStyle.None) }
    Breakpoint.MD { Modifier.display(DisplayStyle.Flex).alignItems(AlignItems.Center).gap(26.px) }
}

val NavLinkStyle = CssStyle {
    base { Modifier.fontSize(14.px).color(Palette.TextMuted).transitionAll("0.2s") }
    hover { Modifier.color(Palette.Text) }
}

// ---------------------------------------------------------------- hero

val HeroStyle = CssStyle {
    base {
        Modifier.fillMaxWidth()
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.ColumnReverse)
            .alignItems(AlignItems.FlexStart)
            .gap(28.px)
            .padding(top = 48.px, bottom = 24.px)
    }
    Breakpoint.MD {
        Modifier.flexDirection(FlexDirection.Row)
            .alignItems(AlignItems.Center)
            .justifyContent(JustifyContent.SpaceBetween)
            .gap(48.px)
            .padding(top = 80.px, bottom = 48.px)
    }
}

val AvatarRingStyle = CssStyle {
    base {
        Modifier.size(124.px)
            .flexShrink(0)
            .borderRadius(50.percent)
            .padding(3.px)
            .styleModifier {
                property("background", Css.ACCENT_GRADIENT)
                property("box-shadow", "0 0 60px -12px rgba(123, 140, 255, 0.55)")
            }
    }
    Breakpoint.MD { Modifier.size(190.px) }
}

val AvatarStyle = CssStyle.base {
    Modifier.fillMaxSize()
        .borderRadius(50.percent)
        .objectFit(ObjectFit.Cover)
        .display(DisplayStyle.Block)
        .styleModifier { property("background", Palette.Background.toString()) }
}

val NameStyle = CssStyle.base {
    Modifier.fontWeight(FontWeight.SemiBold)
        .margin(0.px)
        .styleModifier {
            property("font-size", "clamp(2.3rem, 7vw, 3.5rem)")
            property("line-height", "1.05")
            property("letter-spacing", "-0.03em")
            property("background", Css.TEXT_GRADIENT)
            property("-webkit-background-clip", "text")
            property("background-clip", "text")
            property("color", "transparent")
        }
}

val TaglineStyle = CssStyle.base {
    Modifier.mono()
        .fontSize(15.px)
        .color(Palette.AccentSoft)
        .margin(top = 14.px)
}

val AboutStyle = CssStyle.base {
    Modifier.color(Palette.TextMuted)
        .fontSize(16.px)
        .margin(top = 14.px)
        .styleModifier {
            property("line-height", "1.75")
            property("max-width", "62ch")
        }
}

val RowWrapStyle = CssStyle.base {
    Modifier.display(DisplayStyle.Flex).flexWrap(FlexWrap.Wrap).alignItems(AlignItems.Center).gap(10.px)
}

val ChipStyle = CssStyle {
    base {
        Modifier.mono()
            .fontSize(12.px)
            .color(Palette.TextMuted)
            .padding(topBottom = 6.px, leftRight = 12.px)
            .borderRadius(999.px)
            .transitionAll("0.2s")
            .styleModifier {
                property("background", Css.SURFACE)
                property("border", "1px solid ${Css.BORDER}")
                property("white-space", "nowrap")
            }
    }
    hover {
        Modifier.color(Palette.Text).styleModifier { property("border-color", Css.BORDER_STRONG) }
    }
}

// ---------------------------------------------------------------- buttons

private val buttonBase = Modifier.display(DisplayStyle.LegacyInlineFlex)
    .alignItems(AlignItems.Center)
    .gap(9.px)
    .fontSize(14.px)
    .fontWeight(FontWeight.Medium)
    .padding(topBottom = 11.px, leftRight = 18.px)
    .borderRadius(12.px)
    .cursor(Cursor.Pointer)

val PrimaryButtonStyle = CssStyle {
    base {
        buttonBase.color(Palette.Background).transitionAll().styleModifier {
            property("background", Css.ACCENT_GRADIENT)
            property("font-weight", "600")
        }
    }
    hover {
        Modifier.styleModifier {
            property("transform", "translateY(-2px)")
            property("box-shadow", "0 16px 34px -18px rgba(123, 140, 255, 0.9)")
        }
    }
}

val GhostButtonStyle = CssStyle {
    base {
        buttonBase.color(Palette.Text).transitionAll().styleModifier {
            property("background", Css.SURFACE)
            property("border", "1px solid ${Css.BORDER}")
        }
    }
    hover {
        Modifier.styleModifier {
            property("background", Css.SURFACE_HOVER)
            property("border-color", Css.BORDER_STRONG)
            property("transform", "translateY(-2px)")
        }
    }
}

val IconButtonStyle = CssStyle {
    base {
        Modifier.size(38.px)
            .display(DisplayStyle.LegacyInlineFlex)
            .alignItems(AlignItems.Center)
            .justifyContent(JustifyContent.Center)
            .borderRadius(11.px)
            .color(Palette.TextMuted)
            .cursor(Cursor.Pointer)
            .transitionAll("0.2s")
            .styleModifier {
                property("background", Css.SURFACE)
                property("border", "1px solid ${Css.BORDER}")
            }
    }
    hover {
        Modifier.color(Palette.Text).styleModifier {
            property("background", Css.SURFACE_HOVER)
            property("border-color", Css.BORDER_STRONG)
            property("transform", "translateY(-2px)")
        }
    }
}

// ---------------------------------------------------------------- cards

val CardStyle = CssStyle {
    base {
        Modifier.fillMaxWidth()
            .padding(20.px)
            .borderRadius(16.px)
            .transitionAll()
            .styleModifier {
                property("background", Css.SURFACE)
                property("border", "1px solid ${Css.BORDER}")
                property("box-shadow", Css.CARD_SHADOW)
            }
    }
}

val CardGridStyle = CssStyle.base {
    Modifier.fillMaxWidth().display(DisplayStyle.Grid).gap(16.px).styleModifier {
        property("grid-template-columns", "repeat(auto-fit, minmax(290px, 1fr))")
    }
}

val ProjectCardStyle = CssStyle {
    base {
        Modifier.fillMaxWidth()
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .gap(12.px)
            .padding(20.px)
            .borderRadius(16.px)
            .transitionAll()
            .styleModifier {
                property("background", Css.SURFACE)
                property("border", "1px solid ${Css.BORDER}")
                property("box-shadow", Css.CARD_SHADOW)
                property("height", "100%")
            }
    }
    hover {
        Modifier.styleModifier {
            property("background", Css.SURFACE_HOVER)
            property("border-color", "rgba(123, 140, 255, 0.35)")
            property("box-shadow", Css.CARD_SHADOW_HOVER)
            property("transform", "translateY(-4px)")
        }
    }
}

val ProjectTitleStyle = CssStyle.base {
    Modifier.fontSize(18.px).fontWeight(FontWeight.SemiBold).color(Palette.Text).margin(0.px)
}

val ProjectDescriptionStyle = CssStyle.base {
    Modifier.fontSize(14.px).color(Palette.TextMuted).styleModifier {
        property("line-height", "1.65")
        property("flex", "1")
    }
}

val TagStyle = CssStyle.base {
    Modifier.mono()
        .fontSize(11.px)
        .color(Palette.TextFaint)
        .padding(topBottom = 4.px, leftRight = 9.px)
        .borderRadius(7.px)
        .styleModifier {
            property("background", "rgba(255, 255, 255, 0.045)")
            property("white-space", "nowrap")
        }
}

val MetaTextStyle = CssStyle.base {
    Modifier.mono().fontSize(12.px).color(Palette.TextFaint)
}

// ---------------------------------------------------------------- timeline

val TimelineStyle = CssStyle.base {
    Modifier.fillMaxWidth().display(DisplayStyle.Flex).flexDirection(FlexDirection.Column).gap(4.px)
}

val TimelineItemStyle = CssStyle {
    base {
        Modifier.fillMaxWidth()
            .position(Position.Relative)
            .padding(left = 26.px, bottom = 26.px)
            .styleModifier { property("border-left", "1px solid ${Css.BORDER}") }
    }
    cssRule(":last-child") { Modifier.styleModifier { property("padding-bottom", "0") } }
    cssRule("::before") {
        Modifier.position(Position.Absolute)
            .left((-5).px)
            .top(6.px)
            .size(9.px)
            .borderRadius(50.percent)
            .styleModifier {
                property("content", "\"\"")
                property("background", Css.ACCENT_GRADIENT)
                property("box-shadow", "0 0 0 4px rgba(8, 9, 13, 1)")
            }
    }
    hover {
        Modifier.styleModifier { property("border-left-color", "rgba(123, 140, 255, 0.5)") }
    }
}

val TimelineTitleStyle = CssStyle.base {
    Modifier.fontSize(16.px).fontWeight(FontWeight.SemiBold).color(Palette.Text)
}

val TimelineOrgStyle = CssStyle {
    base { Modifier.fontSize(14.px).color(Palette.Accent).transitionAll("0.2s") }
    hover { Modifier.color(Palette.AccentSoft) }
}

val TimelineDescriptionStyle = CssStyle.base {
    Modifier.fontSize(14.px).color(Palette.TextMuted).margin(top = 8.px).styleModifier {
        property("line-height", "1.65")
        property("max-width", "68ch")
    }
}

// ---------------------------------------------------------------- misc

val FooterStyle = CssStyle.base {
    Modifier.fillMaxWidth()
        .padding(top = 28.px, bottom = 40.px)
        .margin(top = 32.px)
        .display(DisplayStyle.Flex)
        .flexWrap(FlexWrap.Wrap)
        .alignItems(AlignItems.Center)
        .justifyContent(JustifyContent.SpaceBetween)
        .gap(12.px)
        .mono()
        .fontSize(12.px)
        .color(Palette.TextFaint)
        .styleModifier { property("border-top", "1px solid ${Css.BORDER}") }
}

val StatusStyle = CssStyle.base {
    Modifier.fillMaxWidth()
        .padding(topBottom = 90.px)
        .mono()
        .fontSize(14.px)
        .color(Palette.TextFaint)
        .textAlign(TextAlign.Center)
}

val IconChipInnerStyle = CssStyle.base {
    Modifier.display(DisplayStyle.LegacyInlineFlex).alignItems(AlignItems.Center).gap(6.px)
}

val CardHeadStyle = CssStyle.base {
    Modifier.fillMaxWidth()
        .display(DisplayStyle.Flex)
        .alignItems(AlignItems.Center)
        .justifyContent(JustifyContent.SpaceBetween)
        .gap(12.px)
}

val CardHeadMetaStyle = CssStyle.base {
    Modifier.display(DisplayStyle.Flex).alignItems(AlignItems.Center).gap(10.px).flexShrink(0)
}

val StarBadgeStyle = CssStyle.base {
    Modifier.display(DisplayStyle.LegacyInlineFlex)
        .alignItems(AlignItems.Center)
        .gap(4.px)
        .mono()
        .fontSize(12.px)
        .color(Palette.AccentSoft)
}

val CardLinkStyle = CssStyle {
    base {
        Modifier.display(DisplayStyle.LegacyInlineFlex)
            .alignItems(AlignItems.Center)
            .gap(6.px)
            .fontSize(13.px)
            .fontWeight(FontWeight.Medium)
            .color(Palette.Accent)
            .transitionAll("0.2s")
    }
    hover { Modifier.color(Palette.AccentSoft) }
}

val FooterLinkStyle = CssStyle {
    base { Modifier.mono().fontSize(12.px).color(Palette.TextMuted).transitionAll("0.2s") }
    hover { Modifier.color(Palette.Accent) }
}
