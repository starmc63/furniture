/*    */ package de.Ste3et_C0st.Furniture.Objects.garden;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ public class config
/*    */ {
/*    */   public JavaPlugin plugin;
/*    */   public String fileName;
/* 14 */   public String path = "plugins/";
/*    */ 
/*    */   
/*    */   public config() {
/* 18 */     this.path = "plugins/FurnitureLib/";
/*    */   }
/*    */ 
/*    */   
/*    */   public FileConfiguration createConfig(String name, String Folder) {
/* 23 */     if (!name.endsWith(".yml")) {
/* 24 */       name = name + ".yml";
/*    */     }
/* 26 */     File f = new File(this.path + Folder + "/");
/* 27 */     if (!f.exists()) {
/* 28 */       f.mkdirs();
/*    */     }
/* 30 */     File arena = new File(this.path + Folder, name);
/* 31 */     if (!arena.exists()) {
/*    */       
/*    */       try {
/* 34 */         arena.createNewFile();
/*    */       }
/* 36 */       catch (IOException e) {
/*    */         
/* 38 */         e.printStackTrace();
/*    */       } 
/*    */     }
/* 41 */     return (FileConfiguration)YamlConfiguration.loadConfiguration(arena);
/*    */   }
/*    */ 
/*    */   
/*    */   public void saveConfig(String name, FileConfiguration fileConfiguration, String Folder) {
/* 46 */     if (!name.endsWith(".yml")) {
/* 47 */       name = name + ".yml";
/*    */     }
/* 49 */     File arena = new File(this.path + Folder, name);
/*    */     
/*    */     try {
/* 52 */       fileConfiguration.save(arena);
/*    */     }
/* 54 */     catch (IOException e) {
/*    */       
/* 56 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public FileConfiguration getConfig(String name, String Folder) {
/* 62 */     if (!name.endsWith(".yml")) {
/* 63 */       name = name + ".yml";
/*    */     }
/* 65 */     createConfig(name, Folder);
/* 66 */     File arena = new File(this.path + Folder, name);
/* 67 */     return (FileConfiguration)YamlConfiguration.loadConfiguration(arena);
/*    */   }
/*    */   
/*    */   public boolean ExistMaps(String folder) {
/* 71 */     if ((new File(this.path + folder)).exists()) {
/* 72 */       return true;
/*    */     }
/* 74 */     return false;
/*    */   }
/*    */   
/*    */   public boolean ExistConfig(String folder, String name) {
/* 78 */     if ((new File(this.path + folder, name)).exists()) {
/* 79 */       return true;
/*    */     }
/* 81 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void deleteFolder(File folder) {
/* 88 */     File[] files = folder.listFiles();
/* 89 */     if (files != null) {
/* 90 */       for (File f : files) {
/* 91 */         if (f.isDirectory()) {
/* 92 */           deleteFolder(f);
/*    */         } else {
/* 94 */           f.delete();
/*    */         } 
/*    */       } 
/*    */     }
/* 98 */     folder.delete();
/*    */   }
/*    */ }


/* Location:              C:\Users\starmc\Documents\DiceFurniture-3.9.1.jar!\de\Ste3et_C0st\Furniture\Objects\garden\config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */