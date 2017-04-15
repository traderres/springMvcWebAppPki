package app1.utilities;


/**
 * To work, this line must be in the applicationContext.xml file
 *    <bean id="applicationContextProvider" class="app1.utilities.SpringAppContextUtils" />
 *
 *  MySpringBean mySpringBean = (MySpringBean) SpringApplicationContext.getBean("mySpringBean");
 *
 * This class implements ApplicationContextAware.
 * The method, â€œsetApplicationContext(â€¦)â€� gets called during the creation of this bean
 * providing the reference to the context.
 *
 */
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringAppContextUtils implements ApplicationContextAware
{
    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext aApplicationContext) throws BeansException
    {
        // Assign the ApplicationContext into a static variable
        applicationContext = aApplicationContext;
    }


    public static Object getBean(String aName)
    {
        if (applicationContext == null)
        {
            throw new RuntimeException("Error in SpringAppContextUtils.getBean().  The applicationContext is null");
        }

        return applicationContext.getBean(aName);
    }

}
