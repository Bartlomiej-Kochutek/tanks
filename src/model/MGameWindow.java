package model;

import controller.CGameWindow;





public class MGameWindow
{
  private CGameWindow controller;

  private long lastMeasuredMilisecs;



  public MGameWindow()
  {
    lastMeasuredMilisecs = System.currentTimeMillis();
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
}





