package model;

import controller.CHitPoints;





public class MHitPoints
{
  private CHitPoints controller;

  private static final int DEFAULT_BAR_HEIGHT = 3;
  private int barHeight;

  private static final int MAX_HIT_POINTS = 1000;
  private int amount;



  public MHitPoints()
  {
    barHeight = DEFAULT_BAR_HEIGHT;
    amount = MAX_HIT_POINTS;
  }


  public void takeDamagedFromMissile(int iHitPointsDamage)
  {
    amount -= iHitPointsDamage;
  }

  public double getAmountAsPercentage()
  {
    return (double)amount / (double)MAX_HIT_POINTS;
  }




  public CHitPoints getController()
  {
    return controller;
  }
  public void setController(CHitPoints iController)
  {
    controller = iController;
  }

  public static int getMaxHitPoints()
  {
    return MAX_HIT_POINTS;
  }

  public int getBarHeight()
  {
    return barHeight;
  }

  public int getAmount()
  {
    return amount;
  }
  public void setAmount(int iAmount)
  {
    amount = iAmount;
  }
}
