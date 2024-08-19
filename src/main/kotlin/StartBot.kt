package ai.tock.sample.webhook

import ai.tock.bot.api.webhook.webhook
import ai.tock.bot.api.client.*
import ai.tock.shared.property
import mu.KotlinLogging
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ObjectMapper




val apiKey = property("tock_bot_api_key", System.getenv("TOCK_BOT_API_KEY") ?: "TOCKAPIKEY")
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
 * Start the bot as a webhook
 */
fun main() {
    val logger = KotlinLogging.logger {}
    webhook(basicbot)
}