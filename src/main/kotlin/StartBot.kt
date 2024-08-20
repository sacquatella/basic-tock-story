package ai.tock.sample.webhook

import ai.tock.bot.api.webhook.webhook
import ai.tock.bot.api.client.*
import ai.tock.shared.property
import mu.KotlinLogging
import ai.tock.bot.api.websocket.start


val apiKey = property("tock_bot_api_key", System.getenv("TOCK_BOT_API_KEY") ?: "TOCKAPIKEY")
val isWebSocket = property("tock_bot_api_websocket", System.getenv("TOCK_BOT_API_WEBSOCKET") ?: "false").toBoolean()
val botApiHost = property("tock_bot_api_host", System.getenv("TOCK_BOT_API_HOST") ?: "localhost")
val botApiPort = property("tock_bot_api_port", System.getenv("TOCK_BOT_API_PORT") ?: "8080").toInt()
val botApiSsl = property("tock_bot_api_ssl", System.getenv("TOCK_BOT_API_SSL") ?: "false").toBoolean()
val logger = KotlinLogging.logger {}
/**
 * define Story for ChatBotIT with Rest API Call and OpenAI Call
 */
val basicbot = newBot(
    apiKey,
    newStory("debug") {
        logger.info("debug$message")
        send("Hello, small message for basic response")
        end( newCard(
            "source basic bot"
        ))
    },
    // Not known stories
    unknownStory {
        end("Sorry, I don't know how to answer that")
    }
    )

/**
 * Start the bot as a webhook or websocket if the environment variable is set
 */
fun main() {
    val logger = KotlinLogging.logger {}
    if (isWebSocket) {
        start(basicbot, botApiPort,botApiHost, botApiSsl)
    } else {
        webhook(basicbot)
    }
}