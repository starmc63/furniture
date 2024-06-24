/*     */ package de.Ste3et_C0st.Furniture.Objects.RPG;
/*     */ 
/*     */ import de.Ste3et_C0st.Furniture.Main.main;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Furniture;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureConfig;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.ObjectID;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.Type;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fArmorStand;
/*     */ import de.Ste3et_C0st.FurnitureLib.main.entity.fEntity;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.DyeColor;
/*     */ import org.bukkit.EntityEffect;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Creeper;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.FallingBlock;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Sheep;
/*     */ import org.bukkit.entity.TNTPrimed;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityChangeBlockEvent;
/*     */ import org.bukkit.event.entity.EntityExplodeEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.persistence.PersistentDataType;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.util.EulerAngle;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Catapult
/*     */   extends Furniture
/*     */   implements Listener
/*     */ {
/*     */   private fArmorStand stand1;
/*  52 */   private HashMap<Entity, Player> fallingSandList = new HashMap<>();
/*  53 */   private List<EntityType> entityList = Arrays.asList(new EntityType[] { EntityType.PIG, EntityType.CREEPER, EntityType.COW });
/*     */   
/*     */   public Catapult(ObjectID id) {
/*  56 */     super(id);
/*  57 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)main.instance);
/*     */     
/*  59 */     for (fEntity stand : id.getPacketList()) {
/*  60 */       if (stand.getCustomName().toLowerCase().startsWith("#range")) {
/*  61 */         this.stand1 = (fArmorStand)stand;
/*  62 */         this.stand1.setNameVasibility(false);
/*  63 */         this.stand1.update();
/*  64 */         if (this.stand1.getCustomName().equalsIgnoreCase("#range")) {
/*  65 */           this.stand1.setName("#range:1");
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPrimedTnt(EntityExplodeEvent e) {
/*  73 */     if (e.getEntity() == null)
/*  74 */       return;  if (e.getEntity() instanceof TNTPrimed && 
/*  75 */       this.fallingSandList.containsKey(e.getEntity())) {
/*  76 */       Entity entity = e.getEntity();
/*  77 */       Player p = this.fallingSandList.get(e.getEntity());
/*  78 */       this.fallingSandList.remove(e.getEntity());
/*  79 */       Block b = entity.getLocation().getBlock().getRelative(BlockFace.DOWN);
/*  80 */       if (!FurnitureLib.getInstance().getPermManager().canBuild(p, b.getLocation())) {
/*  81 */         e.setCancelled(true);
/*  82 */         e.blockList().clear();
/*  83 */         e.getEntity().remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onFallingSand(EntityChangeBlockEvent e) {
/*  91 */     if (e.getEntity() == null)
/*  92 */       return;  if (e.getEntity() instanceof FallingBlock && 
/*  93 */       this.fallingSandList.containsKey(e.getEntity())) {
/*  94 */       Entity entity = e.getEntity();
/*  95 */       Player p = this.fallingSandList.get(e.getEntity());
/*  96 */       this.fallingSandList.remove(e.getEntity());
/*  97 */       Block b = entity.getLocation().getBlock().getRelative(BlockFace.DOWN);
/*  98 */       if (!FurnitureLib.getInstance().getPermManager().canBuild(p, b.getLocation())) {
/*  99 */         e.setCancelled(true);
/* 100 */         e.getEntity().remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(Player player) {
/* 108 */     if (getObjID() == null)
/* 109 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 110 */       return;  if (player == null)
/* 111 */       return;  if (canInteract(player)) {
/* 112 */       Location loc = getRelative(getCenter(), getBlockFace(), -0.8D, -1.03D).add(0.0D, -0.2D, 0.0D);
/* 113 */       loc.setYaw(getYaw());
/* 114 */       ItemStack stack = player.getInventory().getItemInMainHand();
/* 115 */       if (stack.getType().equals(Material.TNT))
/* 116 */       { TNTPrimed tnt = (TNTPrimed)getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
/* 117 */         if (tnt == null)
/* 118 */           return;  Vector v = getLaunchVector(getBlockFace());
/* 119 */         if (v == null)
/* 120 */           return;  tnt.playEffect(EntityEffect.WITCH_MAGIC);
/* 121 */         tnt.setVelocity(v.multiply(1));
/* 122 */         this.fallingSandList.put(tnt, player); }
/* 123 */       else { if (stack.getType().equals(Material.AIR)) {
/* 124 */           setRange(player);
/*     */           return;
/*     */         } 
/* 127 */         if (FurnitureLib.isNewVersion()) {
/* 128 */           if (player.getGameMode().equals(GameMode.CREATIVE) && FurnitureConfig.getFurnitureConfig().useGamemode())
/* 129 */             return;  if (stack.getType().isBlock()) {
/* 130 */             FallingBlock block = getWorld().spawnFallingBlock(loc, stack.getType().createBlockData());
/* 131 */             if (block == null)
/* 132 */               return;  Vector v = getLaunchVector(getBlockFace());
/* 133 */             if (v == null)
/* 134 */               return;  block.setDropItem(false);
/* 135 */             block.setVelocity(v.multiply(1));
/* 136 */             this.fallingSandList.put(block, player);
/* 137 */             player.playSound(getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0F, (float)getPitch());
/* 138 */             stack.setAmount(stack.getAmount() - 1);
/* 139 */             player.getInventory().setItemInMainHand(stack);
/* 140 */             block.getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "fallingsand"), PersistentDataType.STRING, player.getUniqueId().toString()); return;
/*     */           } 
/* 142 */           if (stack.getType().name().contains("SPAWN_EGG")) {
/*     */             try {
/* 144 */               String name = stack.getType().name().replace("_SPAWN_EGG", "");
/* 145 */               EntityType type = EntityType.valueOf(name);
/* 146 */               if (!this.entityList.contains(type)) { setRange(player); return; }
/* 147 */                Vector v = getLaunchVector(getBlockFace());
/* 148 */               Entity entity = getWorld().spawnEntity(loc, type);
/* 149 */               entity.setVelocity(v);
/* 150 */               LivingEntity entiLivingEntity = (LivingEntity)entity;
/* 151 */               entiLivingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2147483647, 4));
/* 152 */               consumeItem(player);
/* 153 */               if (type.equals(EntityType.CREEPER)) {
/* 154 */                 if (randInt(0, 25) == 7) {
/* 155 */                   Creeper creeper = (Creeper)entity;
/* 156 */                   creeper.setPowered(true);
/*     */                 } 
/* 158 */               } else if (type.equals(EntityType.SHEEP)) {
/* 159 */                 Sheep mob = (Sheep)entity;
/* 160 */                 int i = randInt(0, (DyeColor.values()).length);
/* 161 */                 mob.setColor(DyeColor.values()[i]);
/* 162 */                 if (randInt(0, 25) == 7) {
/* 163 */                   mob.setCustomName("jeb_");
/* 164 */                   mob.setCustomNameVisible(false);
/*     */                 } 
/*     */               } 
/* 167 */               player.playSound(getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0F, (float)getPitch());
/*     */               return;
/* 169 */             } catch (Exception ex) {
/* 170 */               ex.printStackTrace();
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/* 175 */         setRange(player);
/*     */         
/*     */         return; }
/*     */       
/* 179 */       consumeItem(player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBreak(Player player) {
/* 185 */     if (getObjID() == null)
/* 186 */       return;  if (getObjID().getSQLAction().equals(Type.SQLAction.REMOVE))
/* 187 */       return;  if (player == null)
/* 188 */       return;  if (canBuild(player)) {
/* 189 */       destroy(player);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRange(Player p) {
/* 194 */     if (this.stand1 != null) {
/* 195 */       int x = -59;
/* 196 */       String name = "#range:1";
/* 197 */       switch (this.stand1.getName().substring(this.stand1.getName().length() - 1, this.stand1.getName().length())) {
/*     */         case "1":
/* 199 */           name = "#range:2";
/* 200 */           x -= 4;
/*     */           break;
/*     */         case "2":
/* 203 */           name = "#range:3";
/* 204 */           x -= 8;
/*     */           break;
/*     */         case "3":
/* 207 */           name = "#range:4";
/* 208 */           x -= 12;
/*     */           break;
/*     */         case "4":
/* 211 */           name = "#range:5";
/* 212 */           x -= 16;
/*     */           break;
/*     */       } 
/*     */       
/* 216 */       this.stand1.setName(name);
/* 217 */       EulerAngle angle = this.stand1.getLeftArmPose();
/* 218 */       angle = getLutil().Radtodegress(angle);
/* 219 */       angle = angle.setX(x);
/* 220 */       this.stand1.setLeftArmPose(getLutil().degresstoRad(angle));
/* 221 */       update();
/* 222 */       p.playSound(getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, (float)getPitch());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int randInt(int min, int max) {
/* 227 */     Random rand = new Random();
/* 228 */     int randomNum = rand.nextInt(max - min + 1) + min;
/* 229 */     return randomNum;
/*     */   }
/*     */   
/*     */   public double getPitch() {
/* 233 */     String str = "#range:1";
/* 234 */     if (this.stand1 != null) str = this.stand1.getName().toLowerCase(); 
/* 235 */     str = str.replace("#range:", "");
/* 236 */     if (str.equalsIgnoreCase("1"))
/* 237 */       return 0.1D; 
/* 238 */     if (str.equalsIgnoreCase("2"))
/* 239 */       return 0.4D; 
/* 240 */     if (str.equalsIgnoreCase("3"))
/* 241 */       return 0.6D; 
/* 242 */     if (str.equalsIgnoreCase("4"))
/* 243 */       return 0.7D; 
/* 244 */     if (str.equalsIgnoreCase("5")) {
/* 245 */       return 0.8D;
/*     */     }
/* 247 */     return 0.1D;
/*     */   }
/*     */   
/*     */   public Vector getLaunchVector(BlockFace face) {
/* 251 */     String str = "#range:1";
/* 252 */     if (this.stand1 != null) str = this.stand1.getName().toLowerCase(); 
/* 253 */     str = str.replace("#range:", "");
/* 254 */     String level = "Range" + str;
/* 255 */     Vector v = new Vector(0.0D, 0.5D, 1.2D);
/* 256 */     if (main.catapultRange.containsKey(level)) v = (Vector)main.catapultRange.get(level); 
/* 257 */     switch (face) { case NORTH:
/* 258 */         v = new Vector(0.0D, v.getY(), v.getZ()); break;
/* 259 */       case SOUTH: v = new Vector(0.0D, v.getY(), -v.getZ()); break;
/* 260 */       case EAST: v = new Vector(-v.getZ(), v.getY(), 0.0D); break;
/* 261 */       case WEST: v = new Vector(v.getZ(), v.getY(), 0.0D);
/*     */         break; }
/*     */     
/* 264 */     return v;
/*     */   }
/*     */   
/*     */   public void spawn(Location location) {}
/*     */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\RPG\Catapult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */