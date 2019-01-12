#RandomTeleport

### Plugin for Bukkit/Spigot core.

> Testing only on 1.13.2


## Features
* Only sign
* Black list blocks available


```yaml
settings:
  sign:
    name:
      pretty: §l§b[§4RandomTP§b] #Name after create sign for Random TP
      raw: '[rtp]' # What need write for create RandomTP sign
  teleport:
    max:
      x: 3000 # at -3000 to 3000 X Teleport. Point location sign
      z: 3000 # at -3000 to 3000 Z Teleport. Point location sign
    block:
      blackList:
      - LAVA
      - WATER
      - CACTUS
      - CACTUS_GREEN

```
