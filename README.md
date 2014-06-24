assassins
=========

“Assassins” is a live-action "killer" game. Players try to eliminate each other from the game using mock weapons in an effort to become the last surviving player. Currently, players must eliminate their target in person and report their success to a webpage online or to a game administrator in person; this reporting system is tedious and relies heavily on an honor system. 

In our solution, each player will be assigned a target when the game begins. The assassin (a player with a target) will need to eliminate the target inconspicuously. The target’s phone will report its location randomly throughout the day. If the target notices the assassin first, he will be able to bump phones with the assassin to create a “timeout” period where the assassin will not be able to eliminate the target. However, if the assassin successfully locates the target without getting noticed, he will be able to bump phones to eliminate the target. These scenarios will be dependent on an honor system of who is noticed first.

Assassin
--------
When the game begins, the assassin (who is also a target, as everyone playing has an assigned assassin and an assigned target) receives a notification with the name of their target.  At random intervals throughout the day, they receive updates on the location of their target.  They also are notified of their target’s location whenever their target “kills” their own target.  When they successfully “kill” their target using whatever rules have been agreed upon, they then bump phones with the target, and the app registers the kill.  The assassin’s assigned killer is also notified of the assassin’s location, and the assassin is given a new target.


Target
--------
The target will use the application if he notices the assassin before getting eliminated. He will bump phones with the assassin, while the app is open, and initiate the transfer. This will create a “timeout” period where the assassin cannot eliminate the target for a set amount of time.

APIs
--------
We plan to use the Android SDK to build the game and NFC technology to share data between users. Location services such as Google Maps will also be used to display targets and other information. Google Cloud Messaging might be used if we need push notifications from servers to users.
