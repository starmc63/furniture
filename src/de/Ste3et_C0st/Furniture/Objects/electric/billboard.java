/*    */ package de.Ste3et_C0st.Furniture.Objects.electric;
/*    */ 
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.entity.fArmorStand;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.ItemFrame;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.util.EulerAngle;
/*    */ 
/*    */ public class billboard
/*    */   extends Furniture
/*    */ {
/*    */   public billboard(ObjectID id) {
/* 23 */     super(id);
/* 24 */     if (isFinish()) {
/*    */       return;
/*    */     }
/* 27 */     spawn(id.getStartLocation());
/*    */   }
/*    */   
/*    */   public void spawn(Location location) {
/* 31 */     List<fArmorStand> asList = new ArrayList<>();
/* 32 */     Location center = getLutil().getCenter(location).add(0.0D, -1.2D, 0.0D);
/* 33 */     Location center2 = getLutil().getRelative(center, getBlockFace(), 0.0D, -4.0D);
/* 34 */     Location center3 = getLutil().getRelative(center, getBlockFace(), 0.0D, -3.3D);
/*    */     int i;
/* 36 */     for (i = 0; i <= 3; i++) {
/* 37 */       Location loc = getLutil().getRelative(center.clone(), getBlockFace(), -0.1D, -0.5D).add(0.0D, 0.88D * i, 0.0D);
/* 38 */       loc.setYaw(loc.getYaw() + 90.0F);
/* 39 */       fArmorStand packet = getManager().createArmorStand(getObjID(), loc);
/* 40 */       packet.getInventory().setItemInMainHand(new ItemStack(Material.STICK));
/* 41 */       packet.setPose(new EulerAngle(1.39D, 0.0D, 0.0D), Type.BodyPart.RIGHT_ARM);
/* 42 */       asList.add(packet);
/*    */     } 
/*    */     
/* 45 */     for (i = 0; i <= 3; i++) {
/* 46 */       Location loc = getLutil().getRelative(center2.clone(), getBlockFace(), -0.1D, -0.5D).add(0.0D, 0.88D * i, 0.0D);
/* 47 */       loc.setYaw(loc.getYaw() + 90.0F);
/* 48 */       fArmorStand packet = getManager().createArmorStand(getObjID(), loc);
/* 49 */       packet.getInventory().setItemInMainHand(new ItemStack(Material.STICK));
/* 50 */       packet.setPose(new EulerAngle(1.39D, 0.0D, 0.0D), Type.BodyPart.RIGHT_ARM);
/* 51 */       asList.add(packet);
/*    */     } 
/*    */     
/* 54 */     for (i = 0; i <= 4; i++) {
/* 55 */       Location loc = getLutil().getRelative(center3.clone(), getBlockFace(), -0.1D, 0.88D * i).add(0.0D, 0.7D, 0.0D);
/* 56 */       loc.setYaw(loc.getYaw() + 90.0F);
/* 57 */       fArmorStand packet = getManager().createArmorStand(getObjID(), loc);
/* 58 */       packet.getInventory().setItemInMainHand(new ItemStack(Material.STICK));
/* 59 */       packet.setPose(new EulerAngle(-0.17D, 0.0D, 0.0D), Type.BodyPart.RIGHT_ARM);
/* 60 */       asList.add(packet);
/*    */     } 
/*    */     
/* 63 */     for (i = 0; i <= 4; i++) {
/* 64 */       Location loc = getLutil().getRelative(center3.clone(), getBlockFace(), -0.1D, 0.88D * i).add(0.0D, 2.9D, 0.0D);
/* 65 */       loc.setYaw(loc.getYaw() + 90.0F);
/* 66 */       fArmorStand packet = getManager().createArmorStand(getObjID(), loc);
/* 67 */       packet.getInventory().setItemInMainHand(new ItemStack(Material.STICK));
/* 68 */       packet.setPose(new EulerAngle(-0.17D, 0.0D, 0.0D), Type.BodyPart.RIGHT_ARM);
/* 69 */       asList.add(packet);
/*    */     } 
/* 71 */     List<Block> blockLocation = new ArrayList<>();
/* 72 */     for (int x = 0; x <= 1; x++) {
/* 73 */       for (int y = 0; y <= 2; y++) {
/* 74 */         Location loc = getLutil().getRelative(location, getBlockFace(), 0.0D, -y - 1.0D).add(0.0D, (x + 1), 0.0D);
/* 75 */         Location loc2 = getLutil().getRelative(location, getBlockFace(), -1.0D, -y - 1.0D).add(0.0D, (x + 1), 0.0D);
/* 76 */         Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),loc,scheduledTask -> {loc.getBlock().setType(Material.BARRIER);});
/* 77 */         ItemFrame frame = (ItemFrame)getWorld().spawn(loc2, ItemFrame.class);
/* 78 */         frame.setFacingDirection(getBlockFace());
/* 79 */         blockLocation.add(loc.getBlock());
/*    */       } 
/*    */     } 
/* 82 */     getObjID().addBlock(blockLocation);
/* 83 */     for (fArmorStand pack : asList) {
/* 84 */       pack.setInvisible(true);
/* 85 */       pack.setBasePlate(false);
/*    */     } 
/* 87 */     send();
/* 88 */     Bukkit.getPluginManager().registerEvents((Listener)this, getPlugin());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBreak(Player player) {
/* 93 */     if (getObjID() == null)
/* 94 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 95 */       return;  if (player == null)
/* 96 */       return;  if (canBuild(player))
/* 97 */       destroy(player); 
/*    */   }
/*    */   
/*    */   public void onClick(Player p) {}
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\electric\billboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */