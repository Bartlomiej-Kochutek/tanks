package controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;





public final class _TanksApp
{

  public static void main(String[] iArgs)
  {
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    CMainMenu cMainMenu = (CMainMenu)context.getBean("cMainMenu");
    cMainMenu.execute();

    ((ConfigurableApplicationContext)context).close();
  }
}




