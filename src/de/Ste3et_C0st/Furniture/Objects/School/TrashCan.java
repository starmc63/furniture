/*    */ package de.Ste3et_C0st.Furniture.Objects.School;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Main.main;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class TrashCan extends Furniture {
/*    */   public TrashCan(ObjectID id) {
/* 18 */     super(id);
/* 19 */     if (isFinish()) {
/* 20 */       Bukkit.getPluginManager().registerEvents((Listener)this, main.getInstance());
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBreak(Player player) {
/* 27 */     if (getObjID() == null)
/* 28 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 29 */       return;  if (player == null)
/* 30 */       return;  if (canBuild(player)) {
/* 31 */       destroy(player);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(Player player) {
/* 37 */     if (getObjID() == null)
/* 38 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 39 */       return;  if (player == null)
/* 40 */       return;  if (canInteract(player)) {
/* 41 */       ItemStack is = player.getInventory().getItemInMainHand();
/* 42 */       fContainerEntity stand = null;
/* 43 */       for (fEntity s : getfAsList()) {
/* 44 */         if (s.getName().equalsIgnoreCase("#TRASH#")) {
/* 45 */           if (s instanceof fContainerEntity) {
/* 46 */             stand = (fContainerEntity)s;
/*    */           }
/*    */           break;
/*    */         } 
/*    */       } 
/* 51 */       if (stand == null)
/* 52 */         return;  if ((is == null || is.getType() == null || is.getType().equals(Material.AIR)) && 
/* 53 */         stand.getItemInMainHand() != null && !stand.getItemInMainHand().getType().equals(Material.AIR)) {
/* 54 */
            fContainerEntity finalStand = stand;
            Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getCenter(), task->getWorld().dropItem(getCenter(), finalStand.getItemInMainHand()));
/* 55 */         stand.setItemInMainHand(new ItemStack(Material.AIR));
/* 56 */         update();
/*    */         
/*    */         return;
/*    */       } 
/* 60 */       stand.setItemInMainHand(is);
/* 61 */       player.getInventory().clear(player.getInventory().getHeldItemSlot());
/* 62 */       player.updateInventory();
/* 63 */       update();
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void spawn(Location loc) {}
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\School\TrashCan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */