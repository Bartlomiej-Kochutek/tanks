package model;

import controller.CGameWindow;





public class MGameWindow
{
  private CGameWindow controller;

  private long lastMeasuredMilisecs;

  private boolean atLeastOneTankDeafeated;

  private int elementSize;



  public MGameWindow()
  {
    lastMeasuredMilisecs = System.currentTimeMillis();
    atLeastOneTankDeafeated = false;
  }


  public int milisecsDelta()
  {
    long currentMeasuredMilisecs = System.currentTimeMillis();
    int delta = (int)(currentMeasuredMilisecs - lastMeasuredMilisecs);

    lastMeasuredMilisecs = currentMeasuredMilisecs;

    return delta;
  }




  public CGameWindow getController()
  {
    return controller;
  }
  public void setController(CGameWindow iController)
  {
    controller = iController;
  }

  public int getElementSize()
  {
    return elementSize;
  }
  public void setElementSize(int iElementSize)
  {
    elementSize = iElementSize;
  }

  public boolean isAtLeastOneTankDeafeated()
  {
    return atLeastOneTankDeafeated;
  }
  public void setAtLeastOneTankDeafeated(boolean iAtLeastOneTankDeafeated)
  {
    atLeastOneTankDeafeated = iAtLeastOneTankDeafeated;
  }
}





