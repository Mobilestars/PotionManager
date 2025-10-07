# PotionManager

Version: 1.0.0  
Minecraft Version: 1.18+  
**Website:** [Modrinth](https://modrinth.com/plugin/potionsmanager)

---

## Description
**PotionManager** allows server admins to manage which potions cannot be brewed, which cannot be used, and which are completely blocked. The configuration supports three sections: `disabled-brewing`, `disabled-using`, and `disabled-both`.

---

## Installation
1. Place `PotionManager.jar` in your server's `plugins` folder.
2. Start the server to generate the default configuration.
3. Edit `config.yml` to set your disabled potions.
4. Restart the server to apply changes.

---

## Configuration
```yaml
disabled-brewing:
  - HARMING
  - STRONG_HARMING

disabled-using:
  - HEALING
  - STRONG_HEALING

disabled-both:
  - POISON
  - STRONG_POISON
