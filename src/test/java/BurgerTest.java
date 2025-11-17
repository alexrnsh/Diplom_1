import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {


        @Mock
        Bun bunMock;

        @Mock
        Ingredient ingredientMock1;

        @Mock
        Ingredient ingredientMock2;

        Burger burger;

        @Before
        public void init() {
            MockitoAnnotations.initMocks(this);
            burger = new Burger();
        }

        @Test
        public void testSetBuns() {
            burger.setBuns(bunMock);
            assertEquals(bunMock, burger.bun);
        }

        @Test
        public void testAddIngredient() {
            burger.addIngredient(ingredientMock1);
            assertEquals(1, burger.ingredients.size());
            assertEquals(ingredientMock1, burger.ingredients.get(0));
        }

    @Test
    public void testRemoveIngredient() {
        System.out.println("Step 1: ingredients = " + burger.ingredients);

        burger.addIngredient(ingredientMock1);
        System.out.println("Step 2 (added ingredientMock1): ingredients = " + burger.ingredients);

        burger.addIngredient(ingredientMock2);
        System.out.println("Step 3 (added ingredientMock2): ingredients = " + burger.ingredients);

        burger.removeIngredient(0);
        System.out.println("Step 4 (after remove index 0): ingredients = " + burger.ingredients);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(0));
    }

        @Test
        public void testMoveIngredient() {
            burger.addIngredient(ingredientMock1);
            burger.addIngredient(ingredientMock2);

            burger.moveIngredient(0, 1);

            assertEquals(ingredientMock2, burger.ingredients.get(0));
            assertEquals(ingredientMock1, burger.ingredients.get(1));
        }

        @Test
        public void testGetReceiptFormatsCorrectly() {

            when(bunMock.getName()).thenReturn("black bun");
            when(bunMock.getPrice()).thenReturn(100f);

            when(ingredientMock1.getName()).thenReturn("hot sauce");
            when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
            when(ingredientMock1.getPrice()).thenReturn(50f);

            burger.setBuns(bunMock);
            burger.addIngredient(ingredientMock1);

            String receipt = burger.getReceipt();
            System.out.println("RECEIPT:\n" + receipt);

            assertTrue(receipt.contains("(==== black bun ====)"));
            assertTrue(receipt.contains("= sauce hot sauce ="));
            assertTrue(receipt.contains("Price: 250,000000"));
        }

}
