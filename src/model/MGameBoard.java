package model;

import controller.CBoardElement;
import controller.CGameBoard;





public class MGameBoard
{
  private CGameBoard controller;

  private int size;



  public MGameBoard()
  {
  }


  public void resetElements()
  {
    CBoardElement[][] boardElements = controller.getElements();
    int boardSize = boardElements.length;
    for (int i = 0; i < boardSize; i++)
    {
      for (int j = 0; j < boardSize; j++)
      {
        boardElements[i][j].setTank(false);
        boardElements[i][j].setCanon(false);
        boardElements[i][j].setMissile(false);
      }
    }
  }




  public CGameBoard getController()
  {
    return controller;
  }
  public void setController(CGameBoard iController)
  {
    controller = iController;
  }


  public int getSize()
  {
    return size;
  }
  public void setSize(int iSize)
  {
    size = iSize;
  }
}
