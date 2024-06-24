/*    */ package de.Ste3et_C0st.Furniture.Objects.garden;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*    */ import de.Ste3et_C0st.FurnitureLib.Utilitis.LocationUtil;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.entity.fArmorStand;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Keyed;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Tag;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.util.EulerAngle;
/*    */ 
/*    */ public class TFlowerPot
/*    */   extends Furniture {
/*    */   public TFlowerPot(ObjectID id) {
/* 26 */     super(id);
/* 27 */     setPotState();
/* 28 */     if (isFinish()) {
/*    */       return;
/*    */     }
/* 31 */     spawn(id.getStartLocation());
/*    */   }
/*    */   private Block pot;
/*    */   private void setPotState() {
    Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),scheduledTask -> {
        this.pot = getLocation().getBlock();
        /*    */
        /* 37 */     if (FurnitureHook.isNewVersion())
            /* 38 */     { if(pot.getType()==null||!org.bukkit.Tag.FLOWER_POTS.isTagged(pot.getType())){pot.setType(Material.FLOWER_POT);}
            /*    */        }
        /* 40 */     else if (this.pot.getType() == null || !this.pot.getType().equals(Material.FLOWER_POT)) { this.pot.setType(Material.FLOWER_POT); }
        /*    */
        /*    */
        /*    */
        /* 44 */     getObjID().addBlock(Arrays.asList(new Block[] { this.pot }));
    });
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawn(Location paramLocation) {
/* 49 */     List<fArmorStand> packetList = new ArrayList<>();
/* 50 */     float yaw = 90.0F;
/* 51 */     for (int i = 0; i <= 3; i++) {
/* 52 */       Location location = getLutil().getRelative(getCenter(), LocationUtil.yawToFace(yaw), 0.53D, 0.08D);
/* 53 */       location.add(0.0D, -1.7D, 0.0D);
/* 54 */       location.setYaw(90.0F + yaw);
/*    */       
/* 56 */       fArmorStand asp = getManager().createArmorStand(getObjID(), location);
/* 57 */       asp.setRightArmPose(getLutil().degresstoRad(new EulerAngle(-115.0D, 45.0D, 0.0D)));
/* 58 */       asp.getInventory().setItemInMainHand(new ItemStack(Material.STICK));
/*    */       
/* 60 */       packetList.add(asp);
/* 61 */       yaw += 90.0F;
/*    */     } 
/*    */     
/* 64 */     for (fArmorStand asp : packetList) {
/* 65 */       asp.setInvisible(true);
/* 66 */       asp.setBasePlate(false);
/* 67 */       asp.setMarker(false);
/*    */     } 
/* 69 */     send();
/* 70 */     Bukkit.getPluginManager().registerEvents((Listener)this, getPlugin());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(Player p) {}
/*    */ 
/*    */   
/*    */   public void onBreak(Player player) {
/* 78 */     if (getObjID() == null)
/* 79 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 80 */       return;  if (player == null)
/* 81 */       return;  if (canBuild(player)) {
    Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),getLocation(),scheduledTask -> this.pot.setType(Material.AIR));
/* 82 */
/* 83 */       this.pot = null;
/* 84 */       destroy(player);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\garden\TFlowerPot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */