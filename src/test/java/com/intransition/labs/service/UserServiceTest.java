package com.intransition.labs.service;


import com.intransition.labs.domain.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {
    @Mock
    private UserService userService;

    @Before
    public void beforeEachTest() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(userService.findByEmail(Mockito.eq("vladimir"))).thenReturn(new User());
    }

    @Test
    public void testFindByEmail() {
        User user = userService.findByEmail("vladimir");
        Assert.assertNotNull(user);

        user = userService.findByEmail("itransition");
        Assert.assertNull(user);
    }

}
