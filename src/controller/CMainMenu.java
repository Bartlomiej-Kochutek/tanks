package controller;

import view.VMainMenu;





public class CMainMenu
{
  private VMainMenu view;

  private CGameWindow childGameWindow;



  public CMainMenu(
      CGameWindow iChildGameWindow,
      VMainMenu iMainMenuView)
  {
    childGameWindow = iChildGameWindow;
    view = iMainMenuView;

    view.setController(this);
  }


  public void execute()
  {
    view.display();
  }

  public void onPlayButtonPushed()
  {
    childGameWindow.start();
  }

  public void onExitButtonPushed()
  {
  }




  public CGameWindow getChildGameWindow()
  {
    return childGameWindow;
  }
  public void setChildGameWindow(CGameWindow iChildGameWindow)
  {
    childGameWindow = iChildGameWindow;
  }

  public VMainMenu getView()
  {
    return view;
  }
  public void setView(VMainMenu iView)
  {
    view = iView;
  }
}




