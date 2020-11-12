package org.karpuzdev.goredex.bot.events

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class MessageReceived : ListenerAdapter() {

    companion object val map = HashMap<String, String>()
    val plus = arrayOf("java-noman-mega-kopli-koply-recbe-kins-spring-kotlin-scss-js", "\uD83E\uDD0C")
    val negative = arrayOf("php-skript-html-react-receb", "\uD83E\uDD0F")

    init {
        for (s in plus[0].split("-")) {
            map[s] = plus[1]
        }
        for (s in negative[0].split("-")) {
            map[s] = negative[1]
        }
    }

    override fun onMessageReceived(e: MessageReceivedEvent) {
        if (e.author == e.jda.selfUser) {
            return
        }

        val msg = e.message.contentRaw.toLowerCase()
        for (entry in map.entries) {
            if (msg.contains(entry.key)) {
                e.message.addReaction(entry.value).queue()
            }
        }

    }

}
