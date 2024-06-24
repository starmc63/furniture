/*    */ package de.Ste3et_C0st.Furniture.Objects.outdoor;
/*    */ 
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
/*    */ public class hammock
/*    */   extends Furniture {
/*    */   public hammock(ObjectID id) {
/* 17 */     super(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(Player player) {
/* 22 */     if (getObjID() == null)
/* 23 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 24 */       return;  if (player == null)
/* 25 */       return;  ItemStack stack = player.getInventory().getItemInMainHand();
/*    */     
/* 27 */     if (stack != null && 
/* 28 */       isMaterial(stack.getType())) {
/* 29 */       if (stack.getType().name().contains("BANNER")) {
/* 30 */         if (canBuild(player)) {
/* 31 */           for (fEntity entity : getfAsList()) {
/* 32 */             if (entity instanceof fContainerEntity) {
/* 33 */               fContainerEntity containerEntity = fContainerEntity.class.cast(entity);
/* 34 */               if (containerEntity.getHelmet() != null && 
/* 35 */                 containerEntity.getHelmet().getType().name().contains("BANNER")) {
/* 36 */                 containerEntity.setHelmet(stack.clone());
/*    */               }
/*    */             } 
/*    */           } 
/*    */           
/* 41 */           consumeItem(player);
/* 42 */           update();
/*    */           
/*    */           return;
/*    */         } 
/* 46 */       } else if (canBuild(player)) {
/* 47 */         for (fEntity entity : getfAsList()) {
/* 48 */           if (entity instanceof fContainerEntity) {
/* 49 */             fContainerEntity containerEntity = fContainerEntity.class.cast(entity);
/* 50 */             if (containerEntity.getHelmet() != null && 
/* 51 */               containerEntity.getHelmet().getType().toString().toLowerCase().endsWith("log")) {
/* 52 */               containerEntity.setHelmet(stack.clone());
/*    */             }
/*    */           } 
/*    */         } 
/*    */ 
/*    */         
/* 58 */         consumeItem(player);
/* 59 */         update();
/*    */ 
/*    */         
/*    */         return;
/*    */       } 
/*    */     }
/*    */     
/* 66 */     for (fEntity entity : getfAsList()) {
/* 67 */       if (entity.getName().startsWith("#SITZ#")) {
/* 68 */         if (entity.getPassenger() == null || entity.getPassenger().isEmpty()) {
/* 69 */           entity.setPassenger((Entity)player);
/*    */         }
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBreak(Player player) {
/* 78 */     if (getObjID() == null)
/* 79 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 80 */       return;  if (player == null)
/* 81 */       return;  if (canBuild(player)) {
/* 82 */       destroy(player);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean isMaterial(Material m) {
/* 87 */     if (m == null) return false; 
/* 88 */     boolean b = false;
/* 89 */     if (m.toString().toLowerCase().endsWith("log")) {
/* 90 */       b = true;
/* 91 */     } else if (m.toString().toLowerCase().endsWith("banner")) {
/* 92 */       b = true;
/*    */     } 
/*    */     
/* 95 */     return b;
/*    */   }
/*    */   
/*    */   public void spawn(Location location) {}
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\outdoor\hammock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */