# Summary
Better Tools is a mod made by Benjamin Sipe.

This mod, inspired by Tinkers Construct and other similar mods, was made with
the goal of expanding and improving on the vanilla tool experience 
in a simple intuitive way.

While this mod adds literally thousands of possible tool combinations.
Tools should behave how you expect them to -- no strange mechanics or bizarre interactions.

Just mining and crafting.

If you hope to have wild abilities and paxels that break 7x7s with fortune 15!
This mod will never be that.

If you appreciate the vanilla tools but just wish there was more to it than just 
_"go find diamonds and a village and boom never care about tools again",_
then I hope you give this mod a look and see if it has what you are looking for.

# Features and Game-play

This mod is meant to replace the vanilla tools for all intents and purposes.

While I do not outright remove them, you should not ever need to craft or use a vanilla again with this mod installed.

The first step to making your new tool is getting the correct tool handle. 
Rather than all tools being made from sticks, Better tools adds 13 different tool handles by default.
They are the 11 vanilla wood types as well as bamboo ( the chute ) and bones. 

All wooden tool handles may be crafted by placing 2 of the corresponding plank into the crafting table in a shears pattern.
Afterward you may craft any tool using the standard recipe in a crafting table with your desired material. 
Bones and Bamboo plants may be placed directly into the crafting recipe.

And believe it or not, that is essentially it. You now have a custom tool with unique stats depending on the 
handle/ingredient combination. 

One last thing to know, better tools are divided into something called materials. 
This is done to avoid an annoyance I found while playing the "tetra" tool mod.

### But what is the best????
Netherite. Netherite, all around, is the best pickaxe.
At this point, none of the ( many ) tool types break what can be considered the "standard" tool progression.
Wood->Stone->Iron->Diamond->Netherite.

That being said, there are 2 additional end-tier tool sets added to the game. 

1. Echoing tools: these tools may be considered an intermediary between iron and diamonds.
For the time being they are crafted using a smithing table with a gemstone tool (such as emerald or quartz) and an echo shard.
2. Nether-star tools: made from smithing a nether star onto an obsidian pickaxe, these tools have more durability than 
any other tool (3000+)! However, these tools rock core makes them still slow to mine with. . . so while a fantastic 
travel tool, they will never beat the insta-mining of diamond and netherite.

# Making your own datapack
One of the most important features of this mod is the ability to add your own materials.
To this end, you can currently make datapack-resource pack combinations and either add new resources to existing material lists
( think more wood or stone types ), or add in completely new materials with unique stats. 

## Adding an ingredient
Below is the ingredient json for iron tools.
```
{
  "id": "btools:ingredient/iron",
  "path": "btools:item/tool_heads/iron/iron_",                                                                   
  "material_group": "btools:material/iron",
  "material": "minecraft:iron_ingot",
  "source": "CRAFTING"
}
```
In a datapack, it would need to be in a root/btools/ingredient directory ( or any nested directory within ).
Going down the list one by one:
* id -- This is how the ingredient is differentiated from other ingredients.
* path -- the asset location where the tool head textures are stored.
* material_group -- will explain in a bit
* material -- the item used as a crafting ingredient
* source -- is the item crafted or smithed.

The first thing to note is better tools assumes your pickaxe, axe, hoe, shovel, and sword are all stored in the same directory
and named pickaxe_head.png. For the above example, as the path ends with 'iron_', the five texture files for iron tools
are structures as follows:

* assets > btools > textures > items > tool_heads > iron >
  * iron_shovel_head.png
  * iron_sword_head.png
  * iron_pickaxe_head.png
  * iron_axe_head.png
  * iron_hoe_head.png

The second and equally important thing to note is the material_group.
Notice that this file doesn't contain durability, mining speed, or even mining teir.
Rather than copy that information out for each and every material, that info is broken into a separate object called a 
Material. If you are just adding a new wood type, then all you need to do is set the material group to "btools:material/wood".

If you want to add new tool materials, got to "Adding a Material".
Ingredients in the same material_group are considered only cosmetically different.
They may be combined in a crafting table or anvil, and their ingredients may repair each other.

If you would like to add a new tool type that is gained through smithing then you will need one additional field.
To see that, here is the entry for netherite tools.
```
{
  "id": "btools:ingredient/netherite",
  "path": "btools:item/tool_heads/netherite/netherite_",
  "material_group": "btools:material/netherite",
  "material": "minecraft:netherite_ingot",
  "source": "SMITHING",
  "base_material" : "btools:material/diamond"
}
```
Notice that the "source" is set to "SMITHING"...
Along with this, there is a "base_material".
This is how the game knows what tool you are crafting from . . .
Also note, the base_material is a _material_, not an _ingredient_. 
As an example, if you wanted to make a "charcoal" tool by using a piece of charcoal onto a wooden tool,
You would set the base material to "btools:material/wood", and that recipe would work with any wood tool.

## Adding a material
This is the material entry for stone tools.
In a datapack this json would go into a root/btools/material directory ( or any nested directory within ).
```
{
  "id" : "btools:material/stone",
  "durability": 131,
  "mining_speed": 4.0,
  "mining_level": "STONE"
}
```
Going through the entries, we have an id, durability, mining_speed, and mining_level.
To make things simple mining levels currently are limited to:

WOOD,STONE,IRON,DIAMOND, (and) NETHERITE.

As gold is identical to wood, the redundancy removed.

Note: there are no damage indicators here...
For the time being, damage is not controlled by the material. 
Instead, it is controlled by the tool type along with the mining_level, and mimics vanilla behavior.

The vanilla implementation of damage was extremely convoluted, and as a result was moved to simply being based on tool tier.
In the future this will be controlled by the material as well, once I come up with a suitable implementation for it. 

## Adding a tool handle

As before here is an example of a warped wood tool handle:
```
{
  "id": "btools:handle/warped",
  "prefix": "btools:item/warped_",
  "transform": "CRAFTED",
  "sprite": "btools:item/warped_tool_handle",
  "item": "minecraft:warped_planks",
  "modifiers": [
    {
      "property": "DURABILITY",
      "factor": 1.1,
      "operation": "MULTIPLY"
    },
    {
      "property": "MINING_SPEED",
      "factor": 1.1,
      "operation": "MULTIPLY"
    }
  ]
}
```

This is somewhat more complicated, but walking through it, 
We have an id.
As well as a prefix, which works identically to the ingredient prefix.
For dark oak tools, the texture files for the handles are located in:
* assets > btools > textures > items >
  * dark_oak_shovel_handle.png
  * dark_oak_sword_handle.png
  * dark_oak_tool_handle.png

Now, at their core, there are two kinds of tool handle ingredients, we will call them "raw" and "crafted".
If you want to make tools with a bone tool handle, then you will craft a shovel with a diamond at the top and two bones below. 
Bones are considered a "raw" crafting ingredient. In contrast, if you want to craft a tool with a cherry wood handle,
then you would first craft cherry_planks into a "cherry_tool_rod" then use the cherry_tool_rod to craft the shovel.
Cherry is then seen as a "crafted" tool ingredient.

"Transform" is the property that controls which of these this tool_handle is. 
The variables may be "IDENTITY" or "CRAFTED" at the moment. 

"sprite" is an optional parameter for "CRAFTED" tool handles and may be used to identify the sprite for the tool handle when it's crafted.

"sprite" doesn't do anything for "raw" ingredients, and if omitted from "CRAFTED" ones, 
the *_tool_handle texture is used.

"item" determines the crafting ingredient used to make the tool handle ( or tool itself in the case of "IDENTITY" transform ).

Finally, "modifiers" controls how the tool handle affects the stats of the tool you are making.
Modifiers contain 3 arguments, "property", "factor" and "operation",
* "property" controls what aspect should be changed. . . currently DURABILITY,MINING_SPEED, and DAMAGE are the only 3 implemented.
* "factor" controls by what number the property is changed.
* "operation" may be set to "ADD" or "MULTIPLY" depending on which you would like to do.
If you would like to lower a stat, multiply by a number between 0 and 1 or add a negative number.
So, in the above example, a multiplicative durability modifier of 1.1 increases the durability by 10%.

