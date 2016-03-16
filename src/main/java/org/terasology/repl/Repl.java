package org.terasology.repl;



import clojure.lang.IFn;
import clojure.lang.RT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.inventory.InventoryComponent;
import org.terasology.logic.inventory.InventoryManager;
import org.terasology.logic.players.event.OnPlayerSpawnedEvent;
import org.terasology.registry.In;
import org.terasology.world.block.BlockManager;

import static clojure.lang.RT.var;


/**
 * http://blog.avisi.nl/2015/05/18/how-to-inspect-a-legacy-java-application-with-the-clojure-repl/
 */
@RegisterSystem(RegisterMode.ALWAYS)
public class Repl extends BaseComponentSystem {

    private static final Logger logger = LoggerFactory.getLogger(Repl.class);

    @In
    BlockManager blockManager;
    @In
    InventoryManager inventoryManager;
    @In
    EntityManager entityManager;

    private void replStart()  {
        //IFn plus = RT.var("clojure.core", "+");
        //Object object = plus.invoke(1, 2);
        //LOGGER.debug("plus invoked, result was: " + object);

        IFn require = RT.var("clojure.core", "require");
        require.invoke(RT.readString("clojure.tools.nrepl.server"));

        IFn server = var("clojure.tools.nrepl.server", "start-server");
        server.invoke();
    }

    @ReceiveEvent(components = InventoryComponent.class)
    public void onPlayerSpawnedEvent(OnPlayerSpawnedEvent event, EntityRef player) {

        new Thread(this::replStart).start();
        logger.info("started repl");


//        BlockItemFactory blockFactory = new BlockItemFactory(entityManager);
//        // Goodie chest
//        EntityRef chest = blockFactory.newInstance(blockManager.getBlockFamily("core:chest"));
//        chest.addComponent(new InventoryComponent(30));
//
//        inventoryManager.giveItem(chest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:companion"), 99));
//        inventoryManager.giveItem(chest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:brick:engine:stair"), 99));
//        inventoryManager.giveItem(chest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:Tnt"), 99));
//        inventoryManager.giveItem(chest, EntityRef.NULL, entityManager.create("core:fuseShort"));
//        inventoryManager.giveItem(chest, EntityRef.NULL, entityManager.create("core:fuseLong"));
//        inventoryManager.giveItem(chest, EntityRef.NULL, entityManager.create("core:shallowRailgunTool"));
//        inventoryManager.giveItem(chest, EntityRef.NULL, entityManager.create("core:thoroughRailgunTool"));
//
//        inventoryManager.giveItem(chest, EntityRef.NULL, entityManager.create("core:railgunTool"));
//
//        inventoryManager.giveItem(chest, EntityRef.NULL, entityManager.create("core:mrbarsack"));
//        inventoryManager.giveItem(chest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:Brick"), 99));
//        inventoryManager.giveItem(chest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:Ice"), 99));
//        inventoryManager.giveItem(chest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:Plank"), 99));
//
//        EntityRef doorItem = entityManager.create("core:door");
//        ItemComponent doorItemComp = doorItem.getComponent(ItemComponent.class);
//        doorItemComp.stackCount = 20;
//        doorItem.saveComponent(doorItemComp);
//        inventoryManager.giveItem(chest, EntityRef.NULL, doorItem);
//
//        // Inner goodie chest
//        EntityRef innerChest = blockFactory.newInstance(blockManager.getBlockFamily("core:Chest"));
//        innerChest.addComponent(new InventoryComponent(30));
//
//        inventoryManager.giveItem(innerChest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:lava"), 99));
//        inventoryManager.giveItem(innerChest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:water"), 99));
//
//        inventoryManager.giveItem(innerChest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:Iris"), 99));
//        inventoryManager.giveItem(innerChest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:Dandelion"), 99));
//        inventoryManager.giveItem(innerChest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:Tulip"), 99));
//        inventoryManager.giveItem(innerChest, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:YellowFlower"), 99));
//
//        // Place inner chest into outer chest
//        inventoryManager.giveItem(chest, EntityRef.NULL, innerChest);
//
//        inventoryManager.giveItem(player, EntityRef.NULL, entityManager.create("core:pickaxe"));
//        inventoryManager.giveItem(player, EntityRef.NULL, entityManager.create("core:axe"));
//        inventoryManager.giveItem(player, EntityRef.NULL, entityManager.create("core:shovel"));
//        inventoryManager.giveItem(player, EntityRef.NULL, blockFactory.newInstance(blockManager.getBlockFamily("core:Torch"), 99));
//        inventoryManager.giveItem(player, EntityRef.NULL, entityManager.create("core:explodeTool"));
//        inventoryManager.giveItem(player, EntityRef.NULL, entityManager.create("core:railgunTool"));
//        inventoryManager.giveItem(player, EntityRef.NULL, chest);

    }
}
