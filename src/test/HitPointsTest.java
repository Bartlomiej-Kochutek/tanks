package test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import controller.CHitPoints;





@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(/*classes = {CHitPoints.class},*/ locations={"file://beansTest.xml"})
public class HitPointsTest
{

  @Test
  public void test()
  {
    fail("Not yet implemented");
  }

}
