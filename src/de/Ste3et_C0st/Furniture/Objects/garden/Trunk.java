/*    */ package de.Ste3et_C0st.Furniture.Objects.garden;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Main.main;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class Trunk
/*    */   extends Furniture {
/*    */   public Trunk(ObjectID id) {
/* 18 */     super(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(Player player) {
/* 23 */     if (getObjID() == null)
/* 24 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 25 */       return;  if (player == null)
/* 26 */       return;  ItemStack stack = player.getInventory().getItemInMainHand();
/* 27 */     if (stack == null)
/* 28 */       return;  if (main.materialWhiteList.contains(stack.getType())) {
/* 29 */       if (stack.getType().equals(Material.AIR))
/* 30 */         return;  for (fEntity entity : getfAsList()) {
/* 31 */         if (entity instanceof fContainerEntity && entity.getName().startsWith("#TO")) {
/* 32 */           ((fContainerEntity)fContainerEntity.class.cast(entity)).setHelmet(stack);
/*    */         }
/*    */       } 
/* 35 */       update();
/*    */     } else {
/* 37 */       for (fEntity entity : getfAsList()) {
/* 38 */         if (entity.getName().startsWith("#SITZ:") && (
/* 39 */           entity.getPassenger() == null || entity.getPassenger().isEmpty())) {
/* 40 */           entity.setPassenger((Entity)player);
/*    */           return;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onBreak(Player player) {
/* 50 */     if (getObjID() == null)
/* 51 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 52 */       return;  if (player == null)
/* 53 */       return;  if (canBuild(player))
/* 54 */       destroy(player); 
/*    */   }
/*    */   
/*    */   public void spawn(Location location) {}
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\garden\Trunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */