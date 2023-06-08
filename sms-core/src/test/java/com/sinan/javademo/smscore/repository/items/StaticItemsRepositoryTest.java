package com.sinan.javademo.smscore.repository.items;

import com.sinan.javademo.smscore.exception.ItemNotFoundException;
import com.sinan.javademo.smscore.model.item.Item;
import com.sinan.javademo.smscore.model.item.UnitType;
import com.sinan.javademo.smscore.util.TestHelper;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
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

    @Test
    public void testGetItems() {
        Item item1 = new Item("Item1", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice());
        Item item2 = new Item("Item2", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice());
        staticItemsRepository.saveItem(item1);
        staticItemsRepository.saveItem(item2);
        assertTrue(staticItemsRepository.getItems().size() >= 2);

        Item item3 = new Item("Item3", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice());
        staticItemsRepository.saveItem(item3);
        assertTrue(staticItemsRepository.getItems().size() >= 3);
    }

    @Test
    public void testGetItem_existingItem() {
        Item item = new Item("Item", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice());
        staticItemsRepository.saveItem(item);
        Item retrievedItem = staticItemsRepository.getItem(item.getName());
        assertNotNull(retrievedItem);
        assertEquals(retrievedItem.getName(), item.getName());
    }

    @Test(expectedExceptions = ItemNotFoundException.class)
    public void testGetItem_itemNotExist() {
        String fakeItemId = UUID.randomUUID().toString();
        Item item = staticItemsRepository.getItem(fakeItemId);
    }

    @Test
    public void testSaveItem_newItem() {
        Item newItem = new Item("NewItem", TestHelper.getRandomItemType(), TestHelper.getRandomItemPrice());
        staticItemsRepository.saveItem(newItem);
        assertTrue(staticItemsRepository.getItems().size() > 0);
        assertNotNull(staticItemsRepository.getItem(newItem.getName()));
    }

    @Test
    public void testSaveItem_existingItem() {
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
        staticItemsRepository.saveItem(updatedItem);
        assertEquals(staticItemsRepository.getItem(updatedItem.getName()).getName(), "UpdatedItem");
        assertEquals(staticItemsRepository.getItem(updatedItem.getName()).getPrice(), 4.2d);
        assertEquals(staticItemsRepository.getItem(updatedItem.getName()).getUnit(), UnitType.BAG);
    }


}