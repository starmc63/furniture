/*    */ package de.Ste3et_C0st.Furniture.Objects.outdoor;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Chest;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class tent_2
/*    */   extends Furniture
/*    */ {
/*    */   public tent_2(ObjectID id) {
/* 18 */     super(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawn(Location loc) {}
/*    */ 
/*    */   
/*    */   public void onBreak(Player player) {
/* 26 */     if (getObjID() == null)
/* 27 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 28 */       return;  if (player == null)
/* 29 */       return;  if (canBuild(player)) {
/* 30 */       destroy(player);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(Player player) {
/* 36 */     if (getObjID() == null)
/* 37 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 38 */       return;  if (player == null)
/* 39 */       return;  if (canBuild(player, false)) {
/* 40 */       if (FurnitureHook.isNewVersion()) {
/* 41 */         if (Type.DyeColor.getDyeColor(player.getInventory().getItemInMainHand().getType()) != null) {
/* 42 */           getLib().getColorManager().color(player, true, "_CARPET", getObjID(), Type.ColorType.BLOCK, 1);
/*    */           
/*    */           return;
/*    */         } 
/* 46 */       } else if (player.getInventory().getItemInMainHand().getType().name().equalsIgnoreCase("INK_SACK")) {
/* 47 */         getLib().getColorManager().color(player, true, "CARPET", getObjID(), Type.ColorType.BLOCK, 1);
/*    */         
/*    */         return;
/*    */       } 
/*    */       
/* 52 */       for (Location b : getObjID().getBlockList()) {
/* 53 */         if (b.getBlock().getType().equals(Material.CHEST)) {
/* 54 */           Chest c = (Chest)b.getBlock().getState();
/* 55 */           player.getPlayer().openInventory(c.getBlockInventory());
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\outdoor\tent_2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */