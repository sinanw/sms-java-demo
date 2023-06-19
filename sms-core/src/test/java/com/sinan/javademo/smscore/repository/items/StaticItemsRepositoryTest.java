package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.item.UnitType;
import com.sinan.javademo.smscore.util.TestHelper;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.*;

public class StaticItemsRepositoryTest {

    @InjectMocks
    private StaticItemsRepository staticItemsRepository;


    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        staticItemsRepository = null;
    }

    @Test
    public void testGetItems() {
        //Arrange
        Item item1 = new Item("Item1", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice());
        Item item2 = new Item("Item2", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice());
        Item item3 = new Item("Item3", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice());
        staticItemsRepository.saveItem(item1);
        staticItemsRepository.saveItem(item2);
        staticItemsRepository.saveItem(item3);

        //Act
        var items = staticItemsRepository.getItems();

        //Assert
        assertEquals(items.size(), 3);
    }

    @Test
    public void testGetItem_existingItem() {
        //Arrange
        Item item = new Item("Item", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice());
        staticItemsRepository.saveItem(item);

        //Act
        Item retrievedItem = staticItemsRepository.getItem(item.getName());

        //Assert
        assertNotNull(retrievedItem);
        assertEquals(retrievedItem.getName(), item.getName());
    }

    @Test(expectedExceptions = ItemNotFoundException.class)
    public void testGetItem_itemNotExist() {
        //Arrange
        String fakeItemId = UUID.randomUUID().toString();

        //Act
        Item item = staticItemsRepository.getItem(fakeItemId);
    }

    @Test
    public void testSaveItem_newItem() {
        //Arrange
        Item newItem = new Item("NewItem", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice());

        //Act
        staticItemsRepository.saveItem(newItem);

        //Assert
        assertEquals(staticItemsRepository.getItems().size(), 1);
        assertNotNull(staticItemsRepository.getItem(newItem.getName()));
    }

    @Test
    public void testSaveItem_existingItem() {
        //Arrange
        Item newItem = new Item("NewItem", UnitType.TIN, 3.5d);
        staticItemsRepository.saveItem(newItem);
        assertNotNull(staticItemsRepository.getItem(newItem.getName()));
        assertEquals(staticItemsRepository.getItem(newItem.getName()).getName(), "NewItem");
        assertEquals(staticItemsRepository.getItem(newItem.getName()).getPrice(), 3.5d);
        assertEquals(staticItemsRepository.getItem(newItem.getName()).getUnit(), UnitType.TIN);
        Item updatedItem = staticItemsRepository.getItem(newItem.getName());
        updatedItem.setName("UpdatedItem");
        updatedItem.setPrice(4.2);
        updatedItem.setUnit(UnitType.BAG);

        //Act
        staticItemsRepository.saveItem(updatedItem);

        //Assert
        assertEquals(staticItemsRepository.getItem(updatedItem.getName()).getName(), "UpdatedItem");
        assertEquals(staticItemsRepository.getItem(updatedItem.getName()).getPrice(), 4.2d);
        assertEquals(staticItemsRepository.getItem(updatedItem.getName()).getUnit(), UnitType.BAG);
    }


}