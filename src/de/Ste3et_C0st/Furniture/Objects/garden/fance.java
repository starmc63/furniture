/*    */ package de.Ste3et_C0st.Furniture.Objects.garden;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*    */ import de.Ste3et_C0st.Furniture.Main.main;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.entity.fContainerEntity;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import org.bukkit.Bukkit;
import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class fance
/*    */   extends Furniture
/*    */ {
/* 21 */   List<Material> matList = Arrays.asList(new Material[] { Material.SPRUCE_FENCE, Material.BIRCH_FENCE, Material.JUNGLE_FENCE, Material.DARK_OAK_FENCE, Material.ACACIA_FENCE, 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 27 */         Material.valueOf(FurnitureHook.isNewVersion() ? "COBBLESTONE_WALL" : "COBBLE_WALL"), 
/* 28 */         Material.valueOf(FurnitureHook.isNewVersion() ? "NETHER_BRICK_FENCE" : "NETHER_FENCE") });
/*    */   
/*    */   public fance(ObjectID id) {
/* 31 */     super(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(Player player) {
/* 36 */     if (getObjID() == null)
/* 37 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 38 */       return;  if (player == null)
/* 39 */       return;  if (canBuild(player)) {
/* 40 */       Block b = getWorld().getBlockAt(getObjID().getBlockList().toArray(new Location[getObjID().getBlockList().size()])[0]);
/* 41 */       ItemStack stack = player.getInventory().getItemInMainHand();
/* 42 */       if (stack == null)
/* 43 */         return;  if (stack.getType().equals(Material.AIR))
/* 44 */         return;  if (this.matList.contains(stack.getType())) {
            Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),b.getLocation(),task->{  b.setType(stack.getType());});
/* 45 */
/* 46 */         consumeItem(player); return;
/*    */       }
/* 48 */       if (main.materialWhiteList.contains(stack.getType())) {
/* 49 */         setTypes(stack);
/* 50 */         consumeItem(player);
/* 51 */         update();
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBreak(Player player) {
/* 59 */     if (getObjID() == null)
/* 60 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 61 */       return;  if (player == null)
/* 62 */       return;  if (canBuild(player)) {
/* 63 */       destroy(player);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private void setTypes(ItemStack is) {
/* 69 */     Objects.requireNonNull(fContainerEntity.class); Objects.requireNonNull(fContainerEntity.class); getManager().getfArmorStandByObjectID(getObjID()).stream().filter(fContainerEntity.class::isInstance).map(fContainerEntity.class::cast).forEach(packet -> packet.getInventory().setHelmet(is));
/*    */   }
/*    */   
/*    */   public void spawn(Location location) {}
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\garden\fance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */