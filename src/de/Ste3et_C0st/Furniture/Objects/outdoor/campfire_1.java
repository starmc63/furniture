/*    */ package de.Ste3et_C0st.Furniture.Objects.outdoor;
/*    */ 
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*    */ import java.util.HashSet;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class campfire_1
/*    */   extends Furniture
/*    */ {
/*    */   public campfire_1(ObjectID id) {
/* 20 */     super(id);
/* 21 */     if (isFinish())
/* 22 */       return;  spawn(id.getStartLocation());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBreak(Player player) {
/* 27 */     if (getObjID() == null)
/* 28 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 29 */       return;  if (player == null)
/* 30 */       return;  if (canBuild(player)) {
/* 31 */       for (fEntity packet : getManager().getfArmorStandByObjectID(getObjID())) {
/* 32 */         packet.setFire(false);
/* 33 */         Location location = getLocation().clone();
/* 34 */         location.add(0.0D, 1.2D, 0.0D);
/* 35 */         getLib().getLightManager().removeLight(location);
/*    */       } 
/* 37 */       destroy(player);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(Player player) {
/* 43 */     if (getObjID() == null)
/* 44 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 45 */       return;  if (player == null)
/* 46 */       return;  if (canInteract(player)) {
/* 47 */       HashSet<fEntity> aspList = getObjID().getPacketList();
/* 48 */       ItemStack is = player.getInventory().getItemInMainHand();
/* 49 */       if (is.getType().equals(Material.WATER_BUCKET)) {
/* 50 */         for (fEntity packet : aspList) {
/* 51 */           packet.setFire(false);
/* 52 */           Location location = getLocation().clone();
/* 53 */           location.add(0.0D, 1.2D, 0.0D);
/* 54 */           getLib().getLightManager().removeLight(location);
/*    */         } 
/* 56 */         getManager().updateFurniture(getObjID());
/* 57 */       } else if (is.getType().equals(Material.FLINT_AND_STEEL)) {
/* 58 */         for (fEntity packet : aspList) {
/* 59 */           packet.setFire(true);
/* 60 */           Location location = getLocation().clone();
/* 61 */           location.add(0.0D, 1.2D, 0.0D);
/* 62 */           getLib().getLightManager().addLight(location, Integer.valueOf(15));
/*    */         } 
/* 64 */         getManager().updateFurniture(getObjID());
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void spawn(Location loc) {}
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\outdoor\campfire_1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */