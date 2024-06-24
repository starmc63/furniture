/*    */ package de.Ste3et_C0st.Furniture.Main;
/*    */ 
/*    */ import de.Ste3et_C0st.Furniture.Objects.garden.config;
/*    */ import de.Ste3et_C0st.FurnitureLib.Utilitis.LocationUtil;
/*    */ import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class main
/*    */   extends JavaPlugin
/*    */ {
/*    */   private config c;
/*    */   private FileConfiguration file;
/* 21 */   public static double damage = 0.0D;
/*    */   
/*    */   public static main instance;
/*    */   static LocationUtil util;
/* 25 */   public static List<Material> materialWhiteList = new ArrayList<>();
/* 26 */   public static HashMap<String, Vector> catapultRange = new HashMap<>();
/*    */   
/*    */   public void onEnable() {
/* 29 */     if (!getServer().getPluginManager().isPluginEnabled("FurnitureLib")) {
/* 30 */       disablePlugin("[DiceFurniture] FurnitureLib is missing please install it!");
/* 31 */       getLogger().info("You can find the download here: https://www.spigotmc.org/resources/furniturelibary-protectionlib.9368/");
/*    */       
/*    */       return;
/*    */     } 
/* 35 */     instance = this;
/*    */     
/* 37 */     if (!FurnitureLib.getInstance().isEnabledPlugin()) {
/* 38 */       disablePlugin("[DiceFurniture] Plugin disabled because FurnitureLib is incorectly installed!");
/*    */       
/*    */       return;
/*    */     } 
/* 42 */     util = FurnitureLib.getInstance().getLocationUtil();
/* 43 */     if (FurnitureLib.getInstance().getDescription().getVersion().startsWith("3.")) {
/* 44 */       FurnitureHook furniturePlugin = new FurnitureHook(getInstance());
/* 45 */       furniturePlugin.saveResource("config.yml", "/fence/whiteList.yml");
/* 46 */       furniturePlugin.saveResource("damage.yml", "/bearTrap/damage.yml");
/* 47 */       furniturePlugin.saveResource("range.yml", "/catapult/range.yml");
/* 48 */       furniturePlugin.register();
/* 49 */       setDefaults();
/* 50 */       setDefaults_2();
/*    */     } else {
/* 52 */       FurnitureLib.getInstance().send("FurnitureLib Version > 3.x not found");
/* 53 */       FurnitureLib.getInstance().send("DiceFurniture deos not load");
/* 54 */       disablePlugin("");
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void disablePlugin(String string) {
/* 61 */     if (!string.isEmpty()) getLogger().warning(string); 
/* 62 */     Bukkit.getPluginManager().disablePlugin((Plugin)this);
/*    */   }
/*    */   
/*    */   private void setDefaults_2() {
/* 66 */     this.c = new config();
/* 67 */     this.file = this.c.getConfig("damage", "plugin/bearTrap/");
/* 68 */     damage = this.file.getDouble("damage");
/*    */     
/* 70 */     this.c = new config();
/* 71 */     this.file = this.c.getConfig("range", "plugin/catapult/");
/* 72 */     for (String str : this.file.getConfigurationSection("rangeOptions").getKeys(false)) {
/*    */ 
/*    */ 
/*    */       
/* 76 */       Vector v = new Vector(0.0D, this.file.getDouble("rangeOptions." + str + ".height"), this.file.getDouble("rangeOptions." + str + ".width"));
/* 77 */       catapultRange.put(str, v);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void setDefaults() {
/* 82 */     this.c = new config();
/* 83 */     this.file = this.c.getConfig("whiteList", "plugin/fence/");
/* 84 */     List<String> intList = this.file.getStringList("MaterialData");
/* 85 */     for (String i : intList) {
/* 86 */       if (Material.getMaterial(i) != null) {
/* 87 */         Material m = Material.getMaterial(i);
/* 88 */         materialWhiteList.add(m);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   public static LocationUtil getLocationUtil() {
/* 93 */     return util;
/*    */   }
/*    */   public static Plugin getInstance() {
/* 96 */     return (Plugin)instance;
/*    */   }
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Main\main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */