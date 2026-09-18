package ru.cororo.portfolio.content

import androidx.compose.runtime.*
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import ru.cororo.portfolio.model.Portfolio

/**
 * Where the site content comes from.
 *
 * Today there is a single implementation reading a static JSON file that ships with the site. Once there is a
 * backend, add an implementation hitting it (see [ApiPortfolioSource]) and swap [PortfolioContent.source] - no
 * other file has to change.
 */
fun interface PortfolioSource {
    suspend fun load(): Portfolio
}

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

/** Reads the content bundled with the site, e.g. `/content/portfolio.json`. */
class StaticPortfolioSource(private val url: String = "/content/portfolio.json") : PortfolioSource {
    override suspend fun load(): Portfolio {
        val response = window.fetch(url).await()
        if (!response.ok) error("Failed to load $url: ${response.status} ${response.statusText}")
        return json.decodeFromString(response.text().await())
    }
}

/**
 * Reads the content from a backend returning the very same JSON shape. Not used yet - kept here so that moving
 * from a file to a server is a one line change in [PortfolioContent].
 */
class ApiPortfolioSource(private val endpoint: String = "/api/portfolio") : PortfolioSource {
    override suspend fun load(): Portfolio = StaticPortfolioSource(endpoint).load()
}

object PortfolioContent {
    /** Change this line (and nothing else) to serve the content from a backend. */
    var source: PortfolioSource = StaticPortfolioSource()
}

sealed interface PortfolioState {
    data object Loading : PortfolioState
    data class Ready(val portfolio: Portfolio) : PortfolioState
    data class Failed(val message: String) : PortfolioState
}

@Composable
fun rememberPortfolio(source: PortfolioSource = PortfolioContent.source): PortfolioState {
    var state by remember { mutableStateOf<PortfolioState>(PortfolioState.Loading) }

    LaunchedEffect(source) {
        state = try {
            PortfolioState.Ready(source.load())
        } catch (e: Throwable) {
            console.error("Unable to load the portfolio content", e)
            PortfolioState.Failed(e.message ?: "Unknown error")
        }
    }

    return state
}
