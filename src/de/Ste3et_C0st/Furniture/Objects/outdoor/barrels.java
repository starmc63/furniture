/*    */ package de.Ste3et_C0st.Furniture.Objects.outdoor;
/*    */ 
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*    */ import java.util.Objects;
/*    */ import org.bukkit.Bukkit;
import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class barrels
/*    */   extends Furniture
/*    */ {
/*    */   Integer id;
/*    */   Block block;
/*    */   
/*    */   public barrels(ObjectID id) {
/* 22 */     super(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(Player player) {
/* 27 */     if (getObjID() == null)
/* 28 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 29 */       return;  if (player == null)
/* 30 */       return;  if (canBuild(player)) {
/* 31 */       if (!player.getInventory().getItemInMainHand().getType().isBlock() && !player.getInventory().getItemInMainHand().getType().equals(Material.AIR))
/* 32 */         return;  Objects.requireNonNull(fContainerEntity.class); Objects.requireNonNull(fContainerEntity.class); fContainerEntity packet = getObjID().getPacketList().stream().filter(fContainerEntity.class::isInstance).map(fContainerEntity.class::cast).findFirst().orElse(null);
/* 33 */       if (Objects.nonNull(packet)) {
/* 34 */         if (packet.getInventory().getHelmet() != null && !packet.getInventory().getHelmet().getType().equals(Material.AIR)) {
/* 35 */           ItemStack is = packet.getInventory().getHelmet();
/* 36 */           is.setAmount(1);
/* 37 */           Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),task->getWorld().dropItem(getLocation(), is));
/*    */         } 
/* 39 */         packet.getInventory().setHelmet(player.getInventory().getItemInMainHand());
/*    */       } 
/* 41 */       update();
/* 42 */       consumeItem(player);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBreak(Player player) {
/* 48 */     if (getObjID() == null)
/* 49 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 50 */       return;  if (player == null)
/* 51 */       return;  if (canBuild(player)) {
/* 52 */       Objects.requireNonNull(fContainerEntity.class); Objects.requireNonNull(fContainerEntity.class); fContainerEntity packet = getObjID().getPacketList().stream().filter(fContainerEntity.class::isInstance).map(fContainerEntity.class::cast).findFirst().orElse(null);
/* 53 */       if (Objects.nonNull(packet) && 
/* 54 */         packet.getInventory().getHelmet() != null && !packet.getInventory().getHelmet().getType().equals(Material.AIR)) {
/* 55 */         ItemStack is = packet.getInventory().getHelmet();
/* 56 */         is.setAmount(1);
/* 57 */         Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(), task->getWorld().dropItem(getLocation(), is));
/*    */       } 
/*    */       
/* 60 */       destroy(player);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void spawn(Location location) {}
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\outdoor\barrels.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */