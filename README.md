# My portfolio

Code of my personal site: <https://cororo.ru/>. Kotlin/JS on [Kobweb](https://kobweb.varabyte.com/),
deployed as a Docker image from GitHub Actions.

## Adding content

All the content of the page lives in one file:

```
site/src/jsMain/resources/public/content/portfolio.json
```

Nothing on the page is hardcoded in the UI, so a new job, award or project is one JSON object —
no Kotlin changes needed. Sections with an empty list simply disappear (including their nav link,
and the section numbers renumber themselves).

```jsonc
{
  "profile": {
    "name": "...",
    "handle": "...",          // shown in the header as @handle
    "tagline": "...",
    "location": "...",        // optional
    "avatar": "/my_photo.png",
    "about": ["paragraph", "paragraph"],
    "skills": ["Kotlin", "..."],
    "socials": [
      { "label": "Telegram", "url": "https://t.me/...", "icon": "telegram", "primary": true }
    ]
  },
  "experience": [
    {
      "title": "Backend developer",
      "org": "Company",         // optional
      "period": "2025 — now",   // optional, free text
      "description": "...",     // optional
      "url": "https://...",     // optional, makes `org` a link
      "tags": ["Kotlin"]
    }
  ],
  "education": [ /* same shape as experience */ ],
  "projects": [
    {
      "name": "YouTubeCounter",
      "description": "...",
      "year": "2026",           // optional
      "stars": 1,               // optional, hidden when 0
      "featured": true,         // pinned to the top of the grid
      "tags": ["Kotlin"],
      "links": [{ "label": "Source", "url": "https://...", "icon": "github" }]
    }
  ]
}
```

Available `icon` values: `github`, `telegram`, `star`, `pin`, `work`, `education`, `code`, `link`
(unknown values fall back to `link`). New ones are added in
`site/src/jsMain/kotlin/ru/cororo/portfolio/components/Icons.kt` as a single SVG path.

The JSON is fetched at runtime, so during development editing it and reloading the page is enough —
no recompilation.

## Moving the content to a server

`content/PortfolioSource.kt` is the only place that knows where the content comes from. When there is
a backend serving the same JSON shape, switch the source:

```kotlin
PortfolioContent.source = ApiPortfolioSource("/api/portfolio")
```

The rest of the site (model, sections, styles) stays untouched.

## Development

```shell
./gradlew :site:kobwebStart   # http://localhost:8080
./gradlew :site:kobwebStop
```

## Layout

```
site/src/jsMain/kotlin/ru/cororo/portfolio/
  model/       - serializable content model
  content/     - where the content is loaded from
  theme/       - colors, CSS tokens and all the styles
  components/  - icons, small widgets, page sections
  pages/       - the page itself, just composing the sections
```
