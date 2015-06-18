package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckOutRegisterTest {

    @Test
    public void shouldHandleCheckOut() {
        CheckOutRegister checkOutRegister = new CheckOutRegister();

        checkOutRegister.recordCheckOut("123 abcd");
        String actualResult = checkOutRegister.toString();

        assertEquals("123 abcd\n", actualResult);
    }

    @Test
    public void shouldHandleReturn() {
        CheckOutRegister checkOutRegister = new CheckOutRegister();

        checkOutRegister.recordCheckOut("123 abcd");
        checkOutRegister.recordCheckOut("321 abcd");
        checkOutRegister.recordReturn("123 abcd");
        String actualResult = checkOutRegister.toString();

        assertEquals("321 abcd\n", actualResult);
    }
}
