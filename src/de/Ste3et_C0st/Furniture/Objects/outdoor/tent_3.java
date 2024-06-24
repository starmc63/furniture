/*    */ package de.Ste3et_C0st.Furniture.Objects.outdoor;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class tent_3
/*    */   extends Furniture
/*    */ {
/*    */   public tent_3(ObjectID id) {
/* 17 */     super(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawn(Location loc) {}
/*    */   
/*    */   public void onBreak(Player player) {
/* 24 */     if (getObjID() == null)
/* 25 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 26 */       return;  if (player == null)
/* 27 */       return;  if (canBuild(player)) {
/* 28 */       destroy(player);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(Player player) {
/* 34 */     if (getObjID() == null)
/* 35 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 36 */       return;  if (player == null)
/* 37 */       return;  if (canBuild(player, false)) {
/* 38 */       if (FurnitureHook.isNewVersion()) {
/* 39 */         if (Type.DyeColor.getDyeColor(player.getInventory().getItemInMainHand().getType()) != null) {
/* 40 */           getLib().getColorManager().color(player, true, "BANNER", getObjID(), Type.ColorType.BANNER, 1);
/*    */           
/*    */           return;
/*    */         } 
/* 44 */       } else if (player.getInventory().getItemInMainHand().getType().name().equalsIgnoreCase("INK_SACK")) {
/* 45 */         getLib().getColorManager().color(player, true, "BANNER", getObjID(), Type.ColorType.BANNER, 1);
/*    */         
/*    */         return;
/*    */       } 
/*    */     }
/*    */     
/* 51 */     if (canInteract(player, false))
/* 52 */       for (fEntity packet : getManager().getfArmorStandByObjectID(getObjID())) {
/* 53 */         if (packet.getName().equalsIgnoreCase("#SITZ#")) {
/* 54 */           packet.setPassenger((Entity)player);
/* 55 */           packet.update();
/*    */           return;
/*    */         } 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\outdoor\tent_3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */