package model;

import java.util.Iterator;

import controller.CBoardElement;
import controller.CMissile;
import controller.CTank;
import controller.EDirection;
import view.VGameBoard;





public class MTank
{
  private CTank controller;

  private static final int DELTA_T_SCALE = 100;
  private static final int SHOOTING_INTERVAL = 100;

  private static final int DEFAULT_POS_X = 0;
  private static final int DEFAULT_POS_Y = 0;
  private float posX;
  private float posY;

  private int maxPos;

  private static final int DEFAULT_SPEED = 1;
  private int speed;

  private static final int DEFAULT_SIZE = 5;
  private int size;

  private EDirection direction;

  private boolean moveDown;
  private boolean moveLeft;
  private boolean moveRight;
  private boolean moveUp;

  private boolean shooting;
  private int lastShootDelta;

  private boolean defeated;



  public MTank()
  {
    size = DEFAULT_SIZE;

    posX = DEFAULT_POS_X;
    posY = DEFAULT_POS_Y;
    speed = DEFAULT_SPEED;

    direction = EDirection.UP;

    moveDown = false;
    moveLeft = false;
    moveRight = false;
    moveUp = false;

    shooting = false;

    lastShootDelta = 0;

    defeated = false;
  }


  public void prepare()
  {
    maxPos = controller.getParentGameWindow().getChildBoard().getSize() - size;
  }


  public void shoot(int iDeltaT)
  {
    if (!shooting)
      return;

    lastShootDelta += iDeltaT;
    if (lastShootDelta < SHOOTING_INTERVAL)
      return;

    lastShootDelta %= SHOOTING_INTERVAL;

    controller.getMissiles().add(new CMissile(
        controller,
        canonPositionX(),
        canonPositionY(),
        direction));
  }

  public void moveMissiles(float iDeltaT)
  {
    float deltaPos = iDeltaT / DELTA_T_SCALE;
    final float FASTER_THAN_TANK = (float)1.5;

    for (CMissile cMissile : controller.getMissiles())
    {
      cMissile.move(FASTER_THAN_TANK * deltaPos);
    }
  }

  public void checkMissilesCollision()
  {
    if (controller.getParentGameWindow().isAtLeastOneTankDeafeated())
    {
      controller.getMissiles().clear();
      return;
    }

    Iterator<CMissile> iterator = controller.getMissiles().iterator();
    while (iterator.hasNext())
    {
      boolean collisionOccured = iterator.next().collision();
      if (collisionOccured)
      {
        iterator.remove();
      }
    }
  }

  public void move(int iDeltaT)
  {
    float deltaPos = (float)iDeltaT / DELTA_T_SCALE;

    if (moveDown)
      moveDown(deltaPos);

    if (moveLeft)
      moveLeft(deltaPos);

    if (moveRight)
      moveRight(deltaPos);

    if (moveUp)
      moveUp(deltaPos);
  }

  private void moveDown(float iDeltaPos)
  {
    direction = EDirection.DOWN;

    if (verticalCollisionWithBaseWall(controller.getSize()))
      return;

    posY += iDeltaPos;
    correctPosY();
  }
  private void moveLeft(float iDeltaPos)
  {
    direction = EDirection.LEFT;

    if (horizontalCollisionWithBaseWall(-1))
      return;

    posX -= iDeltaPos;
    correctPosX();
  }
  private void moveRight(float iDeltaPos)
  {
    direction = EDirection.RIGHT;

    if (horizontalCollisionWithBaseWall(controller.getSize()))
      return;

    posX += iDeltaPos;
    correctPosX();
  }
  private void moveUp(float iDeltaPos)
  {
    direction = EDirection.UP;

    if (verticalCollisionWithBaseWall(-1))
      return;

    posY -= iDeltaPos;
    correctPosY();
  }

  private boolean verticalCollisionWithBaseWall(int iYDisplacement)
  {
    CBoardElement[][] boardElements = controller.getParentGameWindow().getChildBoard().getElements();

    for (int xDisplacement = 0; xDisplacement < controller.getSize(); xDisplacement++)
    {
      int xIndex = (int)posX + xDisplacement;
      int yIndex = (int)posY + iYDisplacement;

      if (VGameBoard.indicesOutsideWindow(xIndex, yIndex, boardElements.length))
        continue;

      if (boardElements[xIndex][yIndex].isBaseWall())
        return true;
    }
    return false;
  }
  private boolean horizontalCollisionWithBaseWall(int iXDisplacement)
  {
    CBoardElement[][] boardElements = controller.getParentGameWindow().getChildBoard().getElements();

    for (int yDisplacement = 0; yDisplacement < controller.getSize(); yDisplacement++)
    {
      int xIndex = (int)posX + iXDisplacement;
      int yIndex = (int)posY + yDisplacement;

      if (VGameBoard.indicesOutsideWindow(xIndex, yIndex, boardElements.length))
        continue;

      if (boardElements[xIndex][yIndex].isBaseWall())
        return true;
    }
    return false;
  }

  private void correctPosY()
  {
    if (posY < 0)
      posY = 0;

    if (posY > maxPos)
      posY = maxPos;
  }
  private void correctPosX()
  {
    if (posX < 0)
      posX = 0;

    if (posX > maxPos)
      posX = maxPos;
  }


  private int canonPositionX()
  {
    switch (direction)
    {
    case DOWN:
      return (int)posX + size / 2;
    case LEFT:
      return (int)posX;
    case RIGHT:
      return (int)posX + size;
    case UP:
      return (int)posX + size / 2;
    default:
      return 0;
    }
  }
  private int canonPositionY()
  {
    switch (direction)
    {
    case DOWN:
      return (int)posY + size;
    case LEFT:
      return (int)posY + size / 2;
    case RIGHT:
      return (int)posY + size / 2;
    case UP:
      return (int)posY;
    default:
      return 0;
    }
  }




  public void setController(CTank iController)
  {
    controller = iController;
  }
  public CTank getController()
  {
    return controller;
  }

  public int getPosX()
  {
    return (int)posX;
  }
  public void setPosX(int iPosX)
  {
    posX = iPosX;
    if (posX < 0)
      posX = DEFAULT_POS_X;
  }


  public int getPosY()
  {
    return (int)posY;
  }
  public void setPosY(int iPosY)
  {
    posY = iPosY;
    if (posY < 0)
      posY = DEFAULT_POS_Y;
  }

  public int getSize()
  {
    return size;
  }
  public void setSize(int iSize)
  {
    size = iSize;
    if (size <= 0)
      size = DEFAULT_SIZE;
  }

  public EDirection getDirection()
  {
    return direction;
  }
  public void setDirection(EDirection iDirection)
  {
    direction = iDirection;
  }

  public int getSpeed()
  {
    return speed;
  }
  public void setSpeed(int iSpeed)
  {
    speed = iSpeed;
    if (speed <= 0)
      speed = DEFAULT_SPEED;
  }


  public boolean isMoveDown()
  {
    return moveDown;
  }
  public void setMoveDown(boolean iMoveDown)
  {
    moveDown = iMoveDown;
    if (moveDown == true)
      moveUp = false;
  }

  public boolean isMoveLeft()
  {
    return moveLeft;
  }
  public void setMoveLeft(boolean iMoveLeft)
  {
    moveLeft = iMoveLeft;
    if (moveLeft == true)
      moveRight = false;
  }

  public boolean isMoveRight()
  {
    return moveRight;
  }
  public void setMoveRight(boolean iMoveRight)
  {
    moveRight = iMoveRight;
    if (moveRight == true)
      moveLeft = false;
  }

  public boolean isMoveUp()
  {
    return moveUp;
  }
  public void setMoveUp(boolean iMoveUp)
  {
    moveUp = iMoveUp;
    if (moveUp == true)
      moveDown = false;
  }


  public boolean isShooting()
  {
    return shooting;
  }
  public void setShooting(boolean iShooting)
  {
    shooting = iShooting;
  }

  public boolean isDefeated()
  {
    return defeated;
  }
  public void setDefeated(boolean iDefeated)
  {
    defeated = iDefeated;
  }
}
