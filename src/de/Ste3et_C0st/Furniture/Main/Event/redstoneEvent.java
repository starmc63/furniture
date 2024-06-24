/*    */ package de.Ste3et_C0st.Furniture.Main.Event;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Objects.electric.streetlamp;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockRedstoneEvent;
/*    */ 
/*    */ 
/*    */ public class redstoneEvent
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   private void onBlockPowered(BlockRedstoneEvent e) {
/* 19 */     World world = e.getBlock().getWorld();
/* 20 */     Block block = e.getBlock();
/* 21 */     Location location = block.getLocation();
/*    */ 
/*    */     
/* 24 */     Map.Entry<Location, streetlamp> object = streetlamp.locationSet.entrySet().stream().filter(entry -> ((streetlamp)entry.getValue()).getObjID().getWorld().equals(world)).filter(entry -> (((Location)entry.getKey()).distance(location) <= 1.0D)).findFirst().orElse(null);
/* 25 */     if (Objects.nonNull(object))
/* 26 */       if (e.getNewCurrent() == 0) {
/* 27 */         ((streetlamp)object.getValue()).setLight(Boolean.valueOf(false));
/*    */       } else {
/* 29 */         ((streetlamp)object.getValue()).setLight(Boolean.valueOf(true));
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Main\Event\redstoneEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */