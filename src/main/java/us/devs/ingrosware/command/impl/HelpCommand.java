package us.devs.ingrosware.command.impl;

import us.devs.ingrosware.IngrosWare;
import us.devs.ingrosware.command.Command;
import us.devs.ingrosware.command.annotation.CommandManifest;
import us.devs.ingrosware.util.other.Logger;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
@CommandManifest(label = "Help", description = "Shows all commands", handles = {"commands", "h"})
public class HelpCommand extends Command {

    @Override
    public void execute(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        IngrosWare.INSTANCE.getCommandManager().getValues().forEach(command ->
                stringBuilder.append(command.getLabel()).append(" - ").append(command.getDescription()).append("\n"));
        Logger.printMessage(stringBuilder.toString());
    }
}
