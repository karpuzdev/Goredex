package org.karpuzdev.goredex.bot

import me.koply.kcommando.KCommando
import net.dv8tion.jda.api.JDABuilder
import org.karpuzdev.goredex.bot.commands.PingCommand
import org.karpuzdev.goredex.bot.events.MessageReceived
import java.util.logging.Logger

class CodexiaBot {

    companion object val logger: Logger = Logger.getLogger("CodexiaBot")

    fun start(env : Map<String, String>) {
        if (!env.containsKey("token")) {
            logger.info("Environment variables are couldn't have 'token' variable. CodexiaBot is returning 0.")
            return
        }

        val jda = JDABuilder.createDefault(env["token"]).setAutoReconnect(true).build()
        jda.awaitReady()

        println(PingCommand::class.java.`package`.name)
        KCommando(jda)
                .setPrefix("€")
                .setPackage(PingCommand::class.java.`package`.name)
                .setOwners("269140308208517130", "291168238140653578").build()

        jda.addEventListener(MessageReceived())
    }

}