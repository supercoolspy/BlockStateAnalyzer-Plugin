package dev.cosmics.blockstateanalyzer

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minecraft.world.level.block.Block.BLOCK_STATE_REGISTRY
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class BlockStateAnalyzer : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        if (!dataFolder.exists()) {
            dataFolder.mkdirs()
        }
        loadStateIDs()
        Bukkit.getConsoleSender().sendMessage(Component.text("Loaded ${BLOCK_STATE_REGISTRY.size()} block states to blockstates.json", NamedTextColor.GREEN))
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }


    private fun loadStateIDs() {
        val json = JsonObject()
        BLOCK_STATE_REGISTRY.forEach {
            val id = BLOCK_STATE_REGISTRY.getId(it)
            json.addProperty(id.toString(), it.toString())
        }
        val file = dataFolder.resolve("blockstates.json")
        file.writeText(json.toString())
    }
}
