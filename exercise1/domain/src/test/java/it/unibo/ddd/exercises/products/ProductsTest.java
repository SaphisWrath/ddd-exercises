package it.unibo.ddd.exercises.products;

import it.unibo.ddd.exercises.money.Money;
import it.unibo.ddd.exercises.money.MoneyFactory;
import it.unibo.ddd.exercises.money.impl.MoneyFactoryImpl;
import it.unibo.ddd.exercises.products.impl.ProductFactoryImpl;
import it.unibo.ddd.exercises.products.impl.WarehouseImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class ProductsTest {
    private final ProductFactory pFac = new ProductFactoryImpl();
    private final MoneyFactory moneyFactory = new MoneyFactoryImpl();
    private final ProductID phID = pFac.makeProductID("name", "type");

    private Money priceEur(String amount) {
        return moneyFactory.makeMoney(
            new BigDecimal(amount),
            moneyFactory.makeCurrencyEUR()
        );
    }

    @Test
    void productsAreEqualIffTheirIdentifiersAreEqual() {
        Product product1 = pFac.makeProduct(
            phID,
            priceEur("100"),
            0
        );
        Product product2 = pFac.makeProduct(
            phID,
            priceEur("1"),
            25
        );
        assertEquals(product1, product2);
    }

    @Test
    void productsEnforceTheirInvariants() {
        assertThrows(IllegalArgumentException.class, () -> pFac.makeProduct(
            phID,
            priceEur("-100"),
            0
        ));
        assertThrows(IllegalArgumentException.class, () -> pFac.makeProduct(
            phID,
            priceEur("100"),
            -10
        ));
        Product product = pFac.makeProduct(phID, priceEur("100"), 0);
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(priceEur("-100")));
        assertThrows(IllegalArgumentException.class, () -> product.setQuantity(-100));
    }

    @Test
    void repositoryStoresAndRetrievesProducts() {
        ProductID deleteID = pFac.makeProductID("Pollo arrosto", "rosticceria");
        ProductID duplicateID = pFac.makeProductID("Penne Barilla", "alimentari");
        Product delete = pFac.makeProduct(deleteID, priceEur("10"), 10);
        Product duplicate = pFac.makeProduct(duplicateID, priceEur("10"), 10);

        Warehouse warehouse = new WarehouseImpl();
        warehouse.store(delete);
        warehouse.store(duplicate);
        warehouse.store(pFac.makeProduct(duplicateID, priceEur("10")));

        assertTrue(warehouse.getInventory().contains(delete));
        assertTrue(warehouse.getInventory().contains(duplicate));
        assertEquals(2, warehouse.getInventory().size());

        assertEquals(Optional.of(delete), warehouse.findProductByID(deleteID));
        assertEquals(Optional.empty(), warehouse.findProductByID(phID));

        warehouse.removeProduct(delete);
        assertEquals(Optional.empty(), warehouse.findProductByID(deleteID));
    }
}
