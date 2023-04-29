# UpdateNotifier

Minecraft server plugin to add messages to display to players when they join. Only newer messages that the player hasn't seen before are shown. (Good for information about updates and stuff).

Messages are added through `config.yml`. The file's created when the plugin loads, but if you'd rather have them ready, it should look something like:

```
alert-msg: "Updates since you left:"

caught-up-msg:

messages:
  1: yadayadaya
  2: yadayadayadaya
  3: yadyayadaya
  4: bom bom ka ka
 ```
 
Additionally, `alert-msg` and `caught-up-msg` can be modified or left blank if wanted.

As the plugin is currently configured, messages don't display if it's the player's first time joining the server, but they are still marked as 'seen'. Additionally, if there are more than five unseen messages for a player, only the five "latest" (highest index in `config.yml`) messages are sent to chat, and the rest are marked as seen by the server.
