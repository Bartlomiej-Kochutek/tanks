package controller;

import java.util.LinkedList;

import model.MTank;
import view.VTank;





public class CTank
{
  private CGameWindow parentGameWindow;

  private MTank model;
  private VTank view;

  private LinkedList<CMissile> missiles;
  private CHitPoints hitPoints;

  private CBase base;



  public CTank(
      MTank iTankModel,
      VTank iTankView,
      CHitPoints iHitPointsController,
      CBase iBaseController)
  {
    model = iTankModel;
    model.setController(this);

    view = iTankView;
    view.setController(this);

    hitPoints = iHitPointsController;
    hitPoints.setParentTank(this);

    base = iBaseController;
    base.setParentTank(this);

    missiles = new LinkedList<CMissile>();
  }


  void prepare()
  {
    model.prepare();
    view.prepare();

    base.prepare();
    base.draw();
  }

  public void redrawWithMissiles()
  {
    if (model.isDefeated())
      return;

    view.redraw(parentGameWindow.getChildBoard().getElements());
    view.redrawMissiles(parentGameWindow.getChildBoard().getElements());
  }


  public void shoot(int iDeltaT)
  {
    model.shoot(iDeltaT);
  }

  public void moveMissiles(float iDeltaT)
  {
    model.moveMissiles(iDeltaT);
  }

  public void checkMissilesCollision()
  {
    model.checkMissilesCollision();
  }

  public void move(int iDeltaT)
  {
    model.move(iDeltaT);
  }




  public VTank getView()
  {
    return view;
  }
  public void setView(VTank iView)
  {
    view = iView;
  }

  public controller.CGameWindow getParentGameWindow()
  {
    return parentGameWindow;
  }
  public void setParentGameWindow(CGameWindow iParent)
  {
    parentGameWindow = iParent;
  }

  public int getPosX()
  {
    return model.getPosX();
  }
  public void setPosX(int iPosX)
  {
    model.setPosX(iPosX);
  }

  public int getPosY()
  {
    return model.getPosY();
  }
  public void setPosY(int iPosY)
  {
    model.setPosY(iPosY);
  }

  public int getSize()
  {
    return model.getSize();
  }
  public void setSize(int iSize)
  {
    model.setSize(iSize);
  }

  public EDirection getDirection()
  {
    return model.getDirection();
  }
  public void setDirection(EDirection iDirection)
  {
    model.setDirection(iDirection);
  }

  public int getSpeed()
  {
    return model.getSpeed();
  }
  public void setSpeed(int iSpeed)
  {
    model.setSpeed(iSpeed);
  }


  public boolean isMoveDown()
  {
    return model.isMoveDown();
  }
  public void setMoveDown(boolean iMoveDown)
  {
    model.setMoveDown(iMoveDown);
  }

  public boolean isMoveLeft()
  {
    return model.isMoveLeft();
  }
  public void setMoveLeft(boolean iMoveLeft)
  {
    model.setMoveLeft(iMoveLeft);
  }

  public boolean isMoveRight()
  {
    return model.isMoveRight();
  }
  public void setMoveRight(boolean iMoveRight)
  {
    model.setMoveRight(iMoveRight);
  }

  public boolean isMoveUp()
  {
    return model.isMoveUp();
  }
  public void setMoveUp(boolean iMoveUp)
  {
    model.setMoveUp(iMoveUp);
  }


  public boolean isShooting()
  {
    return model.isShooting();
  }
  public void setShooting(boolean iShooting)
  {
    model.setShooting(iShooting);
  }


  public LinkedList<CMissile> getMissiles()
  {
    return missiles;
  }
  public void setMissiles(LinkedList<CMissile> iMissiles)
  {
    missiles = iMissiles;
  }

  public CHitPoints getHitPoints()
  {
    return hitPoints;
  }
  public void setHitPoints(CHitPoints iHitPoints)
  {
    hitPoints = iHitPoints;
  }

  public boolean isDefeated()
  {
    return model.isDefeated();
  }
  public void setDefeated(boolean iDefeated)
  {
    model.setDefeated(iDefeated);
  }
}





