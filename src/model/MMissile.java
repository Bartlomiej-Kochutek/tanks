package model;

import java.util.LinkedHashSet;

import controller.CBoardElement;
import controller.CMissile;
import controller.CTank;
import controller.EDirection;
import view.VGameBoard;





public class MMissile
{
  private CMissile controller;

  private float posX;
  private float posY;

  private EDirection direction;

  private static final int DEFAULT_DAMAGE = 8;
  private int damage;



  public MMissile()
  {
    damage = DEFAULT_DAMAGE;
  }


  public void move(float iDeltaPos)
  {
    switch (direction)
    {
      case DOWN:
        posY += iDeltaPos;
      break;
      case LEFT:
        posX -= iDeltaPos;
      break;
      case RIGHT:
        posX += iDeltaPos;
      break;
      case UP:
        posY -= iDeltaPos;
      break;
    }
  }

  public boolean collision()
  {
    CTank parentTank = controller.getParentTank();

    int boardSize = parentTank.getParentGameWindow().getChildBoard().getSize();

    if (VGameBoard.indicesOutsideWindow((int)posX, (int)posY, boardSize))
      return true;

    CBoardElement[][] boardElements = parentTank.getParentGameWindow().getChildBoard().getElements();

    int intPosX = (int)posX;
    int intPosY = (int)posY;

    if (collisionWithBoardElements(
          boardElements,
          intPosX,
          intPosY,
          boardSize))
      return true;

    if (collisionWithOtherTanks(
          intPosX,
          intPosY))
      return true;

    return false;
  }

  private boolean collisionWithBoardElements(
      CBoardElement[][] oBoardElements,
      int iPosXInt,
      int iPosYInt,
      int iBoardSize)
  { //keep functions sequence order
    if (oBoardElements[iPosXInt][iPosYInt].isBaseWall())
      return true;

    if (oBoardElements[iPosXInt][iPosYInt].isDestroyed())
      return false;

    oBoardElements[iPosXInt][iPosYInt].setDestroyed(true);
    if (direction == EDirection.UP || direction == EDirection.DOWN)
    {
      int neighbourIndex = iPosXInt - 1;
      if (neighbourIndex >= 0)
        oBoardElements[neighbourIndex][iPosYInt].setDestroyed(true);

      neighbourIndex = iPosXInt + 1;
      if (neighbourIndex < iBoardSize)
        oBoardElements[neighbourIndex][iPosYInt].setDestroyed(true);
    }
    else
    {
      int neighbourIndex = iPosYInt - 1;
      if (neighbourIndex >= 0)
        oBoardElements[iPosXInt][neighbourIndex].setDestroyed(true);

      neighbourIndex = iPosYInt + 1;
      if (neighbourIndex < iBoardSize)
        oBoardElements[iPosXInt][neighbourIndex].setDestroyed(true);
    }
    return true;
  }

  private boolean collisionWithOtherTanks(
      int iPosXInt,
      int iPosYInt)
  {
    CTank parentTank = controller.getParentTank();

    LinkedHashSet<CTank> tanks = parentTank.getParentGameWindow().getChildTanks();
    for (CTank tank : tanks)
    {
      if (tank == parentTank)
        continue;

      if (tank.getPosX() > iPosXInt || tank.getPosX() + tank.getSize() <= iPosXInt ||
          tank.getPosY() > iPosYInt || tank.getPosY() + tank.getSize() <= iPosYInt)
        continue;

      tank.getHitPoints().takeDamagedFromMissile(damage);

      return true;
    }
    return false;
  }




  public CMissile getController()
  {
    return controller;
  }
  public void setController(CMissile iController)
  {
    controller = iController;
  }

  public int getPosX()
  {
    return (int)posX;
  }
  public void setPosX(int iPosX)
  {
    posX = iPosX;
  }

  public int getPosY()
  {
    return (int)posY;
  }
  public void setPosY(int iPosY)
  {
    posY = iPosY;
  }

  public EDirection getDirection()
  {
    return direction;
  }
  public void setDirection(EDirection iDirection)
  {
    direction = iDirection;
  }

  public int getDamage()
  {
    return damage;
  }
  public void setDamage(int iDamage)
  {
    damage = iDamage;
    if (damage <= 0)
      damage = DEFAULT_DAMAGE;
  }
}
