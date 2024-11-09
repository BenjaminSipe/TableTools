Last Modified: 11/7/2024

Current version : 0.3.0

Update: I jumped the gun and made the block entity, the minecraft forge. 

Rather than work on on anything, it seems right to try to ground the work going on
and make it make sense. Make it all work together. 

I have the tool classes and tiers written down on paper. Not gonna add diagrams at the moment.


From: 9/23/2024

Two important questions:

What do I need before version 0.3.0? 
What do I need for version 1.0.0?
Generalizing this, my philosophy regarding version changes is thus:
There are major, minor, and bugfix versions. 
Bugfix versions are simply used for minor adjustments but no new features. 
Minor versions are for features and adjustments within a given feature pack.
Major versions should overhaul large feature sets....
With that in mind, I need to decide what I think is important to have in version 1.
It all depends on if I want to add enchanting in version 1? 
Probably the answer is No? But if that is true, then aren't I almost done? Like I wanna add tool bindings, and then the book system. And then I'm done.
Rethinking this. . . releases are versions for which I'm planning on fixing bugs and not just moving past. 
If the answer isn't just "screw you!" then probably it is a release.
Anyway. . . I really should be thinking about where I want this mod to go.
I have several major features I plan on adding.

1. Remove enchantments 
2. Add in modifiers
3. Add tool bindings 
4. Add new tool tiers
5. Adding a recipe book

I think I can't release a V1 without a recipe book. 
I hate vanilla enchantments. 
Yeah. Alloying:
Rose Gold,
Ender steel,
Netherite,
Current metals:
Iron, Copper, Gold, which makes Rose Gold the only thoughtful metal to exist.
What else? 
Long term where would I like this to live?
So for starters adding tool bindings is the simplest item on the list
Basically, I would like to have alloyable materials. . . as well as infusable materials...
Some ideas:
Scorched wood,
Blood Diamond,
Withered Bone,
Ender Steel,
Carbon Steel,
Blazed-wood
Resonant,
I would like to integrate it with other mods?
Probably not the primary focus.
I need to take some time and think about what else I want to add?
Minecraft has such a wide variety of obscure items and interactions, 
that there is a ton of room to slip into the cracks.
Going at this from the perspective of the 4 different upgrade paths...
I want there to be a Standard path, a Warrior's path a traveler's path,
and an excavator's path. 


#### Minecrafter
Diamond -> Netherite -> \
CRAFT -> SMITH ->
#### Excavator
Hardened Iron ( steel ) -> Ender-Steel -> \
CRAFT -> ALLOY(32) ->
#### Traveler
Obsidian -> Blood Diamond -> \
CRAFT -> SMITH -> 
#### Warrior
Gemstone -> Echoing -> Nether star -> \
CRAFT -> ALLOY(4) -> SMITH

OK... phases:
I want to add in the materials, and the abilities to craft them. 

If I'm thinking about things in phases. . . I should probably set my goal on tier one first.

Then tier 2. Then Tier 3. . . there is no point in getting worked up about the later tiers if 
I'm not getting to them for months regardless.



### NOTES
**The fact that I don't like the idea of imbuing a steel tool with ender pearls feels slightly indicative to me.**
**Do I actually want to do this?**
Let's think about it this way. . . I want to make ender pearls useful for tools. Setting up an enderman farm should be a good idea.
The truth is, I don't mind making ender-steel tools via imbuing, but I also like the idea of using the material for other things.

Warrior tools should revolve around the killing of great beasts. The Nether star is the **PERFECT** example of this.
It is literally the drop from a great beast.

Echo shards, to a lesser extend, meet that category. You know what, Guilded blackstone falls in that category. 
Magma cream and blaze rods actually also fit into that category. 
Thinking about the trial chambers ( we have those, breeze rods also fit that category. ), 
but are too common for this use case. Perhaps breeze charges would work as an ingredient? 
definitely feel  like blaze powder works  

Everything pre-diamond is considered agnostic.
These focus on ores of rising rarity.
So, following Netherite should be a harder ore with a harder application.
Etc. Etc.
Probably there should be an end ore. Probably another nether ore, and another overworld ore.

I think I will either need to use dragon's breath for some of these third tier items, or I will need to add an ore.

I can also add it to end city loot. Which would work well for the vanilla path as everyone goes there. 

No. . . I like the idea of an ore in the END. It just feels right. 
Obviously, anyone who adds another ore that they feel is as good or better can add their own material/ingredient.

### Other things: 
Rose gold will be used to add silk touch. The only issue is you will need a lot of it. I think. 

Basically, I want to add a post-endgame. But why? To be honest, I don't really care about the damage after 1-shotting basic mobs.
After that, it just seems fun. Excavator tools will generally be gemstone related. Perhaps after echoing should be blood diamond toolss,

Basically, Up till this point, this mod has been about setting the foundation for mod packs. But I think with the full plan in place, that will be secondary. 


Alright. . . so good news and bad news, I have some thoughts. For starters 

Materais and what I want them to do 

Alloy Forge:
This can be used to add one ingredient to another ingredient. 
This can be used to  add modifiers onto a tool, or forge new ingredients.

Steel can be forged from 4 coal ( or charcoal? )on an iron ingot.
Rose gold can be crafted with 4 copper onto a single gold ingot.

Steel is a hardened for of Iron that begins the base of the Excavator tool progression.

Another use for the alloy forge is to imbue properties of a material into a tool or weapon.
For instance: imbuing 48 nether quartz ( or prismarine shards ) will give a tool increased damage.

MOB DROPS:
Wither Skeleton drops Withered bone
Vexes drop phantom shard

FULL LIST OF ALLOYS: ( base, count-ingredient, result )
Iron, 4-coal, Steel
Gold, 4-copper, Rose Gold // FOR ENCHANTING
Diamond, 64-redstone, Blood Diamond
Black Emerald ( what's the point ? ) // NOT YET.
Steel ingot, 32-ender pearls, Ender Steel ingot


FULL LIST OF MODIFIERS: ( count-ingredient(s), effect )
48-nether quartz/prismarine shard, sharpness
32-rose gold, silk touch


## SCRATCHPAD (DATED)
In total, how many data folders will there be?
Material
Ingredient
Handle
Binding
Modifier ? Probably no more than that.

I don't think any of the others will need a "Material" esk folder
As tool heads will always be the primary motivator behind tool stats.

After those, it is time for the non-vanilla changes?
Remove item combos. . .

Getting right into the simplified enchantment systems. . . what enchantments do I want?

1. Decide what enchantments I actually want on tools...

Basic tool enchantments...
Silk touch, Fortune, Unbreaking, Efficiency, Mending

Additional weapon enchantments?
Sharpness, Sweeping edge?

I think I want it to be based on a single material for each:
// I think perhaps this is where I need to start thinking about alloying.
Lets think about it this way. . . I would like

Starting with this:
Alloying two materials together needs a special crafting block.

I would like the


Silk touch:
Fortune:
Unbreaking:
Efficiency:
Mending:
Sharpness:
