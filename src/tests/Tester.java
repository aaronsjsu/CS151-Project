package tests;

import model.User;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Test driven development module.
 *
 * @since 1.0
 */
public final class Tester
{
    @Test public void userValidationTest()
    {
        final User a = new User("A", "username", "password");
        assertTrue(a.validatePassword("password"));
        assertFalse(a.validatePassword("PASSWORD"));
        assertFalse(a.validatePassword(""));
        assertFalse(a.validatePassword("123"));
    }
}
