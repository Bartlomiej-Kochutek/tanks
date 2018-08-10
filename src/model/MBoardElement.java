package model;

import controller.CBoardElement;





public class MBoardElement
{
  private CBoardElement controller;

  private boolean isDestroyed;
  private boolean isTank;
  private boolean isCanon;
  private boolean isMissile;
  private boolean isBaseWall;



  public MBoardElement()
  {
    isDestroyed   = false;
    isTank        = false;
    isCanon       = false;
    isMissile     = false;
    isBaseWall    = false;
  }







  public CBoardElement getController()
  {
    return controller;
  }
  public void setController(CBoardElement iController)
  {
    controller = iController;
  }

  public boolean isDestroyed()
  {
    return isDestroyed;
  }
  public void setDestroyed(boolean iIsDestroyed)
  {
    isDestroyed = iIsDestroyed;
  }

  public boolean isTank()
  {
    return isTank;
  }
  public void setTank(boolean iIsTank)
  {
    isTank = iIsTank;
  }

  public boolean isCanon()
  {
    return isCanon;
  }
  public void setCanon(boolean iIsCanon)
  {
    isCanon = iIsCanon;
  }

  public boolean isMissile()
  {
    return isMissile;
  }
  public void setMissile(boolean iIsMissile)
  {
    isMissile = iIsMissile;
  }

  public boolean isBaseWall()
  {
    return isBaseWall;
  }
  public void setBaseWall(boolean iIsBaseWall)
  {
    isBaseWall = iIsBaseWall;
  }
}
