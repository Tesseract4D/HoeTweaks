package mods.tesseract.hoetweaks;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.tclproject.mysteriumlib.asm.common.CustomLoadingPlugin;
import net.tclproject.mysteriumlib.asm.common.FirstClassTransformer;

@Mod(modid = "hoetweaks")
public class HoeTweaks extends CustomLoadingPlugin {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.BreakEvent e) {
        EntityPlayer p = e.getPlayer();
        ItemStack k = p.getCurrentEquippedItem();
        if (p.isSneaking() && k.getItem() instanceof ItemHoe i) {
            e.setCanceled(i.onItemUse(k, e.getPlayer(), e.world, e.x, e.y, e.z, -1, 0, 0, 0));
        }
    }

    public String[] getASMTransformerClass() {
        return new String[]{FirstClassTransformer.class.getName()};
    }

    public void registerFixes() {
        registerClassWithFixes("mods.tesseract.hoetweaks.FixesItemHoe");
    }
}
