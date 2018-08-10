package controller;

import model.MHitPoints;





public class CHitPoints
{
  private CTank parentTank;

  private MHitPoints model;



  public CHitPoints(MHitPoints iHitPointsModel)
  {
    model = iHitPointsModel;
    model.setController(this);
  }


  public void takeDamagedFromMissile(int iHitPointsDamage)
  {
    model.takeDamagedFromMissile(iHitPointsDamage);
  }

  public double getAmountAsPercentage()
  {
    return model.getAmountAsPercentage();
  }






  public CTank getParentTank()
  {
    return parentTank;
  }
  public void setParentTank(CTank iParentTank)
  {
    parentTank = iParentTank;
  }

  public int getBarHeight()
  {
    return model.getBarHeight();
  }

  public int getAmount()
  {
    return model.getAmount();
  }
  public void setAmount(int iAmount)
  {
    model.setAmount(iAmount);
  }
}
