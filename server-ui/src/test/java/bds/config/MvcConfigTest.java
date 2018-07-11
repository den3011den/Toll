package bds.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MvcConfigTest {


    @Mock
    ViewControllerRegistry registry;

    @Test
     public void addViewControllers() {

        MvcConfig mvcConfig = mock(MvcConfig.class);
        doNothing().when(mvcConfig).addViewControllers(isA(ViewControllerRegistry.class));
        mvcConfig.addViewControllers(registry);
        verify(mvcConfig, times(1)).addViewControllers(registry);

     }
}


