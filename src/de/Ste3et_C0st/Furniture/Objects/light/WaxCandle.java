/*    */ package de.Ste3et_C0st.Furniture.Objects.light;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*    */ import org.bukkit.Bukkit;
import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class WaxCandle
/*    */   extends Furniture
/*    */ {
/*    */   public WaxCandle(ObjectID id) {
/* 17 */     super(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(Player player) {
/* 22 */     if (getObjID() == null)
/* 23 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 24 */       return;  if (player == null)
/* 25 */       return;  if (canBuild(player)) {
/* 26 */       Block b = getWorld().getBlockAt(getObjID().getBlockList().toArray(new Location[getObjID().getBlockList().size()])[0]);
/* 27 */       ItemStack stack = player.getInventory().getItemInMainHand();
/* 28 */       if (stack == null)
/* 29 */         return;  if (stack.getType().equals(Material.AIR))
/* 30 */         return;  if (b != null) {
/* 31 */         if (b.getType().equals(Material.TORCH) && stack.getType().equals(Material.WATER_BUCKET)) {
/* 32 */
                Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),b.getLocation(),task->b.setType(Material.valueOf(FurnitureHook.isNewVersion() ? "REDSTONE_TORCH" : "REDSTONE_TORCH_OFF")));
/* 33 */         } else if (b.getType().equals(Material.valueOf(FurnitureHook.isNewVersion() ? "REDSTONE_TORCH" : "REDSTONE_TORCH_OFF")) && stack.getType().equals(Material.FLINT_AND_STEEL)) {
/* 34 */           Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),b.getLocation(),scheduledTask -> b.setType(Material.TORCH));
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBreak(Player player) {
/* 42 */     if (getObjID() == null)
/* 43 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 44 */       return;  if (player == null)
/* 45 */       return;  if (canBuild(player))
/* 46 */       destroy(player); 
/*    */   }
/*    */   
/*    */   public void spawn(Location location) {}
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\light\WaxCandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */