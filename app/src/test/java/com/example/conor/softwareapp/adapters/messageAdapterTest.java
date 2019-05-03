package com.example.conor.softwareapp.adapters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class messageAdapterTest {
    @Mock
    messageAdapter messageAdapter;

    @Test
    public void getItemViewType() throws Exception {
        final int MSG_TYPE_RIGHT = 0;
        int pos = messageAdapter.getItemViewType(MSG_TYPE_RIGHT);
        assertEquals(MSG_TYPE_RIGHT, pos);
    }
}