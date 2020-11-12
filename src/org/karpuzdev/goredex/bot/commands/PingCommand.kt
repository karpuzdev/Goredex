package org.karpuzdev.goredex.bot.commands

import me.koply.kcommando.CommandUtils
import me.koply.kcommando.annotations.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

@Command(names=["ping"], description="Pong!")
class PingCommand : CommandUtils() {

    override fun handle(e: MessageReceivedEvent) {
        e.textChannel.sendMessage(basicEmbed("Pong!").build()).queue()
    }

}