package org.terasology.repl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.ai.SimpleAISystem;
import org.terasology.logic.inventory.InventoryComponent;
import org.terasology.logic.inventory.InventoryManager;
import org.terasology.logic.players.event.OnPlayerSpawnedEvent;
import org.terasology.registry.In;
import org.terasology.world.block.BlockManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



/**
 * Javascript (nashorn) TCP (telnet) Repl for advanced botting control & telemetry
 * http://blog.avisi.nl/2015/05/18/how-to-inspect-a-legacy-java-application-with-the-clojure-repl/
 */
@RegisterSystem(RegisterMode.ALWAYS)
public class Repl extends SimpleAISystem implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Repl.class);

    @In
    BlockManager blockManager;
    @In
    InventoryManager inventoryManager;
    @In
    EntityManager entityManager;

    final int port = 10002;
    private Thread thread;


    @Override
    public void preBegin() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void shutdown() {
        thread.stop(); //TODO safer shutdown
        thread = null;
    }

    @Override public void run() {
        logger.info("repl listening on port" + port);
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                logger.info("client connected {}", clientSocket);
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received message: " + inputLine + " from " + clientSocket.toString());
                    out.println(inputLine);
                }
                logger.info("client disconnected {}", clientSocket);
            } catch (IOException e) {
                logger.error("{}", e);
            }
        }

    }

    @ReceiveEvent(components = InventoryComponent.class)
    public void onPlayerSpawnedEvent(OnPlayerSpawnedEvent event, EntityRef player) {




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
