package controller;

import java.util.Iterator;
import java.util.LinkedHashSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import model.MGameWindow;
import view.VGameWindow;





public class CGameWindow
{
  private MGameWindow model;
  private VGameWindow view;

  private CGameBoard childBoard;
  private LinkedHashSet<CTank> childTanks;

  private Display displayToGetDevice;

  private int elementSize;

  private static final int FIRST_TANK_MOVE_DOWN     = 's';
  private static final int FIRST_TANK_MOVE_LEFT     = 'a';
  private static final int FIRST_TANK_MOVE_RIGHT    = 'd';
  private static final int FIRST_TANK_MOVE_UP       = 'w';
  private static final int FIRST_TANK_SHOOT         = SWT.CONTROL;

  private static final int SECOND_TANK_MOVE_DOWN    = SWT.ARROW_DOWN;
  private static final int SECOND_TANK_MOVE_LEFT    = SWT.ARROW_LEFT;
  private static final int SECOND_TANK_MOVE_RIGHT   = SWT.ARROW_RIGHT;
  private static final int SECOND_TANK_MOVE_UP      = SWT.ARROW_UP;
  private static final int SECOND_TANK_SHOOT        = '.';



  public CGameWindow(
      MGameWindow iModel,
      VGameWindow iView,
      CGameBoard iChildBoard,
      Display iDisplayToGetDevice,
      int iElementSize)
  {
    model = iModel;
    model.setController(this);

    view = iView;
    view.setController(this);

    childBoard = iChildBoard;
    childBoard.setParentGameWindow(this);

    displayToGetDevice = iDisplayToGetDevice;
    elementSize = iElementSize;
  }


  public void start()
  {
    childBoard.prepare();

    for (CTank cTank : childTanks)
    {
      cTank.setParentGameWindow(this);
      cTank.prepare();
    }

    view.prepareDisplay();

    boolean keepRedrawing = true;
    while (keepRedrawing)
    {
      int deltaT = model.milisecsDelta();

      for (CTank cTank : childTanks)
      {
        cTank.move(deltaT);
        cTank.shoot(deltaT);
        cTank.moveMissiles(deltaT);
        cTank.checkMissilesCollision();
      }

      keepRedrawing = view.refresh();

      try
      {
        Thread.sleep(20);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  public void onRedraw()
  {
    childBoard.getModel().resetElements();

    for (CTank cTank : childTanks)
    {
      cTank.redrawWithMissiles();
    }

    Iterator<CTank> iterator = childTanks.iterator();
    CTank firstTank = iterator.next();
    CTank secondTank = iterator.next();

    childBoard.redraw(firstTank,  EPartOfScreen.LEFT);
    childBoard.redraw(secondTank, EPartOfScreen.RIGHT);
  }

  public void onKeyPressed(int iKeyCode)
  {
    Iterator<CTank> iterator = childTanks.iterator();
    CTank firstTank = iterator.next();
    CTank secondTank = iterator.next();

    switch (iKeyCode)
    {
    case SECOND_TANK_MOVE_DOWN:
      secondTank.setMoveDown(true);
      break;
    case SECOND_TANK_MOVE_LEFT:
      secondTank.setMoveLeft(true);
      break;
    case SECOND_TANK_MOVE_RIGHT:
      secondTank.setMoveRight(true);
      break;
    case SECOND_TANK_MOVE_UP:
      secondTank.setMoveUp(true);
      break;
    case SECOND_TANK_SHOOT:
      secondTank.setShooting(true);
      break;

    case FIRST_TANK_MOVE_DOWN:
      firstTank.setMoveDown(true);
      break;
    case FIRST_TANK_MOVE_LEFT:
      firstTank.setMoveLeft(true);
      break;
    case FIRST_TANK_MOVE_RIGHT:
      firstTank.setMoveRight(true);
      break;
    case FIRST_TANK_MOVE_UP:
      firstTank.setMoveUp(true);
      break;
    case FIRST_TANK_SHOOT:
      firstTank.setShooting(true);
      break;
    }
  }
  public void onKeyReleased(int iKeyCode)
  {
    Iterator<CTank> iterator = childTanks.iterator();
    CTank firstTank = iterator.next();
    CTank secondTank = iterator.next();

    switch (iKeyCode)
    {
    case SECOND_TANK_MOVE_DOWN:
      secondTank.setMoveDown(false);
      break;
    case SECOND_TANK_MOVE_LEFT:
      secondTank.setMoveLeft(false);
      break;
    case SECOND_TANK_MOVE_RIGHT:
      secondTank.setMoveRight(false);
      break;
    case SECOND_TANK_MOVE_UP:
      secondTank.setMoveUp(false);
      break;
    case SECOND_TANK_SHOOT:
      secondTank.setShooting(false);
      break;

    case FIRST_TANK_MOVE_DOWN:
      firstTank.setMoveDown(false);
      break;
    case FIRST_TANK_MOVE_LEFT:
      firstTank.setMoveLeft(false);
      break;
    case FIRST_TANK_MOVE_RIGHT:
      firstTank.setMoveRight(false);
      break;
    case FIRST_TANK_MOVE_UP:
      firstTank.setMoveUp(false);
      break;
    case FIRST_TANK_SHOOT:
      firstTank.setShooting(false);
      break;
    }
  }




  public MGameWindow getModel()
  {
    return model;
  }
  public void setModel(MGameWindow iModel)
  {
    model = iModel;
  }

  public VGameWindow getView()
  {
    return view;
  }
  public void setView(VGameWindow iView)
  {
    view = iView;
  }

  public CGameBoard getChildBoard()
  {
    return childBoard;
  }
  public void setChildBoard(CGameBoard iChildBoard)
  {
    childBoard = iChildBoard;
  }


  public LinkedHashSet<CTank> getChildTanks()
  {
    return childTanks;
  }
  public void setChildTanks(LinkedHashSet<CTank> iChildTanks)
  {
    childTanks = iChildTanks;
  }

  public Display getDisplayToGetDevice()
  {
    return displayToGetDevice;
  }
  public void setDisplayToGetDevice(Display iDisplayToGetDevice)
  {
    displayToGetDevice = iDisplayToGetDevice;
  }


  public int getElementSize()
  {
    return elementSize;
  }
  public void setElementSize(int iElementSize)
  {
    elementSize = iElementSize;
  }
}




