/*    */ package de.Ste3et_C0st.Furniture.Objects.outdoor;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class tent_1
/*    */   extends Furniture
/*    */ {
/*    */   public tent_1(ObjectID id) {
/* 16 */     super(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(Player player) {
/* 21 */     if (getObjID() == null)
/* 22 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 23 */       return;  if (player == null)
/* 24 */       return;  if (canBuild(player, false)) {
/* 25 */       if (FurnitureHook.isNewVersion()) {
/* 26 */         if (Type.DyeColor.getDyeColor(player.getInventory().getItemInMainHand().getType()) != null) {
/* 27 */           getLib().getColorManager().color(player, true, "_CARPET", getObjID(), Type.ColorType.BLOCK, 1);
/*    */           
/*    */           return;
/*    */         } 
/* 31 */       } else if (player.getInventory().getItemInMainHand().getType().name().equalsIgnoreCase("INK_SACK")) {
/* 32 */         getLib().getColorManager().color(player, true, "CARPET", getObjID(), Type.ColorType.BLOCK, 1);
/*    */         
/*    */         return;
/*    */       } 
/*    */     }
/* 37 */     player.openWorkbench(null, true);
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


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\outdoor\tent_1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */