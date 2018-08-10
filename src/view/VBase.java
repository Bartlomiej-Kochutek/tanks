package view;

import controller.CBase;
import controller.CBoardElement;





public class VBase
{
  private CBase controller;



  public VBase()
  {
  }


  public void draw(CBoardElement[][] oElements)
  {
    int thisPosX = controller.getPosX();
    int thisPosY = controller.getPosY();
    int thisSize = controller.getSize();

    for (int xIndex = thisPosX; xIndex <= thisPosX + thisSize; xIndex++)
    {
      if (xIndex == thisPosX + 0.25 * thisSize)
      {
        xIndex = (int)(thisPosX + 0.75 * thisSize);
      }

      int yIndex = thisPosY;
      if (!VGameBoard.indicesOutsideWindow(xIndex, yIndex, oElements.length))
        oElements[xIndex][yIndex].setBaseWall(true);

      yIndex = thisPosY + thisSize;
      if (!VGameBoard.indicesOutsideWindow(xIndex, yIndex, oElements.length))
        oElements[xIndex][yIndex].setBaseWall(true);
    }
    for (int yIndex = thisPosY; yIndex <= thisPosY + thisSize; yIndex++)
    {
      if (yIndex == thisPosY + 0.25 * thisSize)
      {
        yIndex = (int)(thisPosY + 0.75 * thisSize);
      }

      int xIndex = thisPosX;
      if (!VGameBoard.indicesOutsideWindow(xIndex, yIndex, oElements.length))
        oElements[xIndex][yIndex].setBaseWall(true);

      xIndex = controller.getPosX() + thisSize;
      if (!VGameBoard.indicesOutsideWindow(xIndex, yIndex, oElements.length))
        oElements[xIndex][yIndex].setBaseWall(true);
    }
  }




  public CBase getController()
  {
    return controller;
  }
  public void setController(CBase iController)
  {
    controller = iController;
  }
}