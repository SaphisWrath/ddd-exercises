package it.unibo.ddd.exercises.orders;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {
    @Test
    void factoryAssignsFreshIdsAndCurrentTime() {
        // TODO: use Clock.fixed(...) to know which time to expect
        fail("TODO");
    }

    @Test
    void aggregateRootControlsItsItems() {
        // TODO: adding the same product twice sums amounts, non-positive amounts are rejected, items cannot be changed from outside the root
        fail("TODO");
    }

    @Test
    void deliveredOrdersCannotBeModified() {
        // TODO: any modification of a delivered order fails
        fail("TODO");
    }
}
