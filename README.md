# FakeInventories 

FakeInventories is a simple library plugin for Nukkit-MOT Minecraft Bedrock server software, that will help you to create
your custom virtual inventories with ease.

##### [Download plugin](https://github.com/LuminiaDev/FakeInventories-MOT/releases)

## Usage

```java
FakeInventory inventory = new FakeInventory(InventoryType.CHEST, "custom title");

inventory.setDefaultItemHandler((item, event) -> {
    event.setCancelled(true);

    Player target = event.getTransaction().getSource();

    target.sendMessage("is default item handler");
});

inventory.addItems((item, event) -> {
    event.setCancelled(true);

    Player target = event.getTransaction().getSource();
    
    target.sendMessage("is custom item handler in addItem method");
    
    target.removeWindow(inventory);
}, Item.get(Item.IRON_BLOCK), Item.get(Item.IRON_BAR))

inventory.setItem(5, Item.get(Item.DIAMOND), (item, event) -> {
    event.setCancelled(true);

    Player target = event.getTransaction().getSource();

    target.sendMessage("is custom item handler");

    target.removeWindow(inventory);
});

player.addWindow(inventory);
```

## Maven
Adding repo:
```xml
<repositories>
    <repository>
        <id>luminiadev-repository-snapshots</id>
        <url>https://repo.luminiadev.com/snapshots</url>
    </repository>
</repositories>
```

Adding dependency:
```xml
<dependency>
    <groupId>me.iwareq.fakeinventories</groupId>
    <artifactId>fakeinventories</artifactId>
    <version>1.1.9-MOT</version>
</dependency>
```

## Gradle
Adding repo:
```kts
maven {
    name = "luminiadevRepositorySnapshots"
    url = uri("https://repo.luminiadev.com/snapshots")
}
```

Adding dependency:
```kts
compileOnly("me.iwareq.fakeinventories:fakeinventories:1.1.9-MOT")
```