/*     */ package de.Ste3et_C0st.Furniture.Objects.outdoor;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Main.FurnitureHook;
/*     */ import de.Ste3et_C0st.Furniture.Main.main;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.*;
/*     */
/*     */
/*     */
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fArmorStand;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.FurnaceRecipe;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.Recipe;
/*     */ import org.bukkit.util.EulerAngle;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ public class campfire_2
/*     */   extends Furniture
/*     */ {
/*  32 */   private List<Material> items = new ArrayList<>(Arrays.asList(new Material[] {
/*  33 */           Material.valueOf(FurnitureHook.isNewVersion() ? "BEEF" : "RAW_BEEF"), 
/*  34 */           Material.valueOf(FurnitureHook.isNewVersion() ? "CHICKEN" : "RAW_CHICKEN"), 
/*  35 */           Material.valueOf(FurnitureHook.isNewVersion() ? "COD" : "RAW_FISH"), Material.POTATO, 
/*  36 */           Material.valueOf(FurnitureHook.isNewVersion() ? "PORKCHOP" : "PORK"), Material.RABBIT, Material.MUTTON
/*     */         }));
/*  38 */   private EulerAngle[] angle = new EulerAngle[] { new EulerAngle(-1.5D, -0.5D, 0.0D), new EulerAngle(-1.9D, -0.3D, 0.7D), new EulerAngle(-1.5D, 0.3D, 1.9D), new EulerAngle(-0.7D, -0.5D, -1.2D) };
/*     */   
/*     */   private Location middle;
/*     */   
/*     */   private Location grill;
/*     */   
/*     */   private Integer timer;
/*     */   
/*     */   private fArmorStand armorS;
/*     */   private ItemStack is;
/*     */   
/*     */   public campfire_2(ObjectID id) {
/*  50 */     super(id);
/*  51 */     this.middle = getLutil().getCenter(getLocation());
/*  52 */     this.middle = getLutil().getRelative(this.middle, getBlockFace(), 0.5D, -0.5D);
/*  53 */     this.middle.add(0.0D, -1.2D, 0.0D);
/*     */     
/*  55 */     this.grill = getLutil().getRelative(this.middle, getBlockFace(), 0.0D, 0.5D);
/*  56 */     this.grill.setYaw((getLutil().FaceToYaw(getBlockFace()) + 90));
/*  57 */     if (id.isFinish()) {
/*     */       return;
/*     */     }
/*  60 */     spawn(id.getStartLocation());
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawn(Location loc) {}
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/*  68 */     if (getObjID() == null)
/*     */       return; 
/*  70 */     if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*     */       return; 
/*  72 */     if (player == null)
/*     */       return; 
/*  74 */     if (canInteract(player, false)) {
/*  75 */       HashSet<fEntity> aspList = getObjID().getPacketList();
/*  76 */       ItemStack itemStack = player.getInventory().getItemInMainHand();
/*  77 */       fArmorStand packet = null;
/*  78 */       for (fEntity pack : aspList) {
/*  79 */         if (pack instanceof fArmorStand) {
/*  80 */           fArmorStand stand = (fArmorStand)pack;
/*  81 */           if (stand.isSmall() && pack.isInvisible()) {
/*  82 */             packet = stand;
/*  83 */             if (packet.isFire()) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*  89 */       if (itemStack.getType().equals(Material.WATER_BUCKET) && packet.isFire()) {
/*  90 */         setfire(false);
/*  91 */       } else if (itemStack.getType().equals(Material.FLINT_AND_STEEL) && !packet.isFire()) {
/*  92 */         setfire(true);
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     if (canInteract(player, false)) {
/*  97 */       ItemStack itemStack = player.getInventory().getItemInMainHand();
/*     */       
/*  99 */       fArmorStand packet = null;
/* 100 */       HashSet<fEntity> aspList = getObjID().getPacketList();
/* 101 */       for (fEntity pack : aspList) {
/* 102 */         if (pack instanceof fArmorStand) {
/* 103 */           fArmorStand stand = (fArmorStand)pack;
/* 104 */           if (stand.isSmall() && pack.isInvisible()) {
/* 105 */             packet = stand;
/* 106 */             if (packet.isFire()) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 112 */       if (Objects.isNull(packet))
/* 113 */         return;  if (this.items.contains(itemStack.getType()) && packet.isFire() && this.armorS == null) {
/* 114 */         this.is = itemStack.clone();
/* 115 */         this.is.setAmount(1);
/*     */         
/* 117 */         setGrill();
/*     */         
/* 119 */         if (player.getGameMode().equals(GameMode.CREATIVE) && FurnitureConfig.getFurnitureConfig().useGamemode())
/* 120 */           return;  Integer i = Integer.valueOf(player.getInventory().getHeldItemSlot());
/* 121 */         ItemStack item = player.getInventory().getItemInMainHand();
/* 122 */         item.setAmount(item.getAmount() - 1);
/* 123 */         player.getInventory().setItem(i.intValue(), item);
/* 124 */         player.updateInventory();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setfire(boolean b) {
/* 130 */     Location loc = getCenter().clone();
/* 131 */     loc.add(0.0D, 1.3D, 0.0D);
/* 132 */     if (b) {
/* 133 */       getLib().getLightManager().addLight(loc, Integer.valueOf(15));
/*     */     } else {
/* 135 */       getLib().getLightManager().removeLight(loc);
/*     */     } 
/*     */     
/* 138 */     for (fEntity entity : getfAsList()) {
/* 139 */       if (entity instanceof fArmorStand) {
/* 140 */         fArmorStand stand = (fArmorStand)entity;
/* 141 */         if ((stand.getInventory().getHelmet() == null || stand
/* 142 */           .getInventory().getHelmet().getType().equals(Material.AIR)) && (stand
/* 143 */           .getInventory().getItemInMainHand() == null || stand
/* 144 */           .getInventory().getItemInMainHand().getType().equals(Material.AIR)) && 
/* 145 */           stand.isSmall() && entity.isInvisible()) {
/* 146 */           stand.setFire(b);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 151 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/* 156 */     if (getObjID() == null)
/*     */       return; 
/* 158 */     if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/*     */       return; 
/* 160 */     if (player == null)
/*     */       return; 
/* 162 */     if (canBuild(player)) {
/* 163 */       if (isRunning()) {
/* 164 */         Bukkit.getScheduler().cancelTask(this.timer.intValue());
/* 165 */         this.timer = null;
/* 166 */         Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),this.middle, task->getWorld().dropItemNaturally(this.middle.clone().add(0.0D, 1.0D, 0.0D), this.is).setVelocity((new Vector()).zero()));
/*     */       } 
/* 168 */       setfire(false);
/* 169 */       destroy(player);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeGrill() {
/* 174 */     if (isRunning()) {
/* 175 */       Bukkit.getScheduler().cancelTask(this.timer.intValue());
/* 176 */       this.timer = null;
/* 177 */       if (this.armorS != null && this.armorS.getInventory().getItemInMainHand() != null && 
/* 178 */         getItem(this.armorS.getInventory().getItemInMainHand()) != null) {
/* 179 */         Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),this.middle,task->getWorld().dropItemNaturally(this.middle.clone().add(0.0D, 1.0D, 0.0D), getCooked(this.is))
/* 180 */           .setVelocity((new Vector()).zero()));
/* 181 */         getObjID().getPacketList().remove(this.armorS);
/* 182 */         this.armorS.kill();
/* 183 */         this.armorS = null;
/*     */       } 
/*     */     } 
/* 186 */     if (this.armorS != null) {
/* 187 */       if (this.armorS.getInventory().getItemInMainHand() != null) {
/* 188 */         Bukkit.getRegionScheduler().run(FurnitureLib.getInstance(),this.middle,task->getWorld().dropItemNaturally(this.middle.clone().add(0.0D, 1.0D, 0.0D), getCooked(this.is))
/* 189 */           .setVelocity((new Vector()).zero()));
/*     */       }
/* 191 */       getObjID().getPacketList().remove(this.armorS);
/* 192 */       this.armorS.kill();
/* 193 */       this.armorS = null;
/*     */     } 
/* 195 */     update();
/*     */   }
/*     */   
/*     */   public ItemStack getItem(ItemStack is) {
/* 199 */     if (is == null) {
/* 200 */       return null;
/*     */     }
/* 202 */     if (is.getType() == null) {
/* 203 */       return null;
/*     */     }
/* 205 */     if (this.items.contains(is.getType())) {
/* 206 */       return is;
/*     */     }
/* 208 */     return null;
/*     */   }
/*     */   
/*     */   public ItemStack getCooked(ItemStack is) {
/* 212 */     if (is == null) {
/* 213 */       return null;
/*     */     }
/* 215 */     if (is.getType() == null) {
/* 216 */       return null;
/*     */     }
/* 218 */     if (this.items.contains(is.getType())) {
/* 219 */       Material mat = null;
/* 220 */       int rnd = (int)(Math.random() * 100.0D);
/* 221 */       if (rnd < 5) {
/* 222 */         mat = Material.COAL;
/*     */       } else {
/* 224 */         Iterator<Recipe> recipes = Bukkit.recipeIterator();
/* 225 */         while (recipes.hasNext()) {
/* 226 */           Recipe recipe = recipes.next();
/* 227 */           if (Objects.isNull(recipe) || 
/* 228 */             !(recipe instanceof FurnaceRecipe))
/* 229 */             continue;  FurnaceRecipe frecipe = (FurnaceRecipe)recipe;
/* 230 */           if (frecipe.getInput().getType().equals(is.getType())) {
/* 231 */             return frecipe.getResult();
/*     */           }
/*     */         } 
/*     */       } 
/* 235 */       return Objects.nonNull(mat) ? new ItemStack(mat) : is;
/*     */     } 
/* 237 */     return is;
/*     */   }
/*     */   
/*     */   public boolean isRunning() {
/* 241 */     if (this.timer == null) {
/* 242 */       return false;
/*     */     }
/* 244 */     return true;
/*     */   }
/*     */   
/*     */   public void setGrill() {
/* 248 */     HashSet<Player> playerSet = getObjID().getPlayerList();
/* 249 */     Player[] playerArray = (Player[])playerSet.stream().toArray(x$0 -> new Player[x$0]);
/* 250 */     this.armorS = getManager().createArmorStand(getObjID(), this.grill);
/* 251 */     this.armorS.setInvisible(true);
/* 252 */     this.armorS.getInventory().setItemInMainHand(this.is);
/* 253 */     this.armorS.send(playerArray);
/* 254 */     getObjID().addArmorStand((fEntity)this.armorS);
/* 255 */     this.timer = Integer.valueOf(main.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable()
/*     */ {
                /* 257 */ Integer rounds = Integer.valueOf(campfire_2.this.getLutil().randInt(15, 30));
                /* 258 */ Integer labs = Integer.valueOf(0);
                /* 259 */ Integer position = Integer.valueOf(0);

                /*     */
                /*     */
                /*     */
                public void run() {
                    /* 263 */
                    if (this.labs.intValue() >= this.rounds.intValue()) {
                        /* 264 */
                        campfire_2.this.removeGrill();
                        /*     */
                        return;
                        /*     */
                    }
                    /* 267 */
                    if (this.position.intValue() > 3) {
                        /* 268 */
                        this.position = Integer.valueOf(0);
                        /*     */
                    }
                    /* 270 */
                    if (campfire_2.this.armorS != null) {
                        /* 271 */
                        campfire_2.this.armorS.setPose(campfire_2.this.angle[this.position.intValue()], Type.BodyPart.RIGHT_ARM);
                        /* 272 */
                        campfire_2.this.update();
                        /*     */
                    }
                    /* 274 */
                    Integer integer = this.position;
                    this.position = Integer.valueOf(this.position.intValue() + 1);
                    /* 275 */
                    integer = this.labs;
                    this.labs = Integer.valueOf(this.labs.intValue() + 1);
                    /*     */
                }
    }, 0, 4));
}
}


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\outdoor\campfire_2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */