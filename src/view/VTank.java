package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;

import controller.CBoardElement;
import controller.CMissile;
import controller.CTank;





public class VTank
{
  private CTank controller;

  private Color tankColor;
  private Color canonColor;
  private Color missileColor;



  public VTank()
  {
  }


  public void prepare()
  {
    Device device = controller.getParentGameWindow().getDisplayToGetDevice().getSystemColor(SWT.COLOR_BLUE).getDevice();
    tankColor = new Color(
        device,
        50, 50, 50);

    canonColor = new Color(
        device,
        80, 10, 10);

    missileColor = new Color(
        device,
        255, 0, 144);
  }

  public void redraw(CBoardElement[][] oElements)
  {
    final int boardSize = oElements.length;

    int xIndex = controller.getPosX();
    if (xIndex < 0)
      xIndex = 0;

    int yIndex = controller.getPosY();
    if (yIndex < 0)
      yIndex = 0;
    int beginY = yIndex;

    int tankPosX = controller.getPosX();
    int tankEndX = tankPosX + controller.getSize();
    if (tankEndX > boardSize)
      tankEndX = boardSize;

    int tankPosY = controller.getPosY();
    int tankEndY = tankPosY + controller.getSize();
    if (tankEndY > boardSize)
      tankEndY = boardSize;

    while (xIndex < tankEndX)
    {
      yIndex = beginY;
      while (yIndex < tankEndY)
      {
        oElements[xIndex][yIndex].setDestroyed(true);
        oElements[xIndex][yIndex].setTank(true);

        if (canonOnPosition(
              xIndex,
              yIndex,
              tankPosX,
              tankPosY))
        {
          oElements[xIndex][yIndex].setCanon(true);
        }
        yIndex++;
      }
      xIndex++;
    }
  }
  private boolean canonOnPosition(
      int iXIndex,
      int iYIndex,
      int iTankPosX,
      int iTankPosY)
  {
    int halfTankSize = controller.getSize() / 2;

    switch (controller.getDirection())
    {
    case DOWN:
      if (iXIndex == iTankPosX + halfTankSize &&
          iYIndex >= iTankPosY + halfTankSize)
        return true;
      break;
    case LEFT:
      if (iXIndex <= iTankPosX + halfTankSize &&
          iYIndex == iTankPosY + halfTankSize)
        return true;
      break;
    case RIGHT:
      if (iXIndex >= iTankPosX + halfTankSize &&
          iYIndex == iTankPosY + halfTankSize)
        return true;
      break;
    case UP:
      if (iXIndex == iTankPosX + halfTankSize &&
          iYIndex <= iTankPosY + halfTankSize)
        return true;
      break;
    }
    return false;
  }

  public void redrawMissiles(CBoardElement[][] iElements)
  {
    for (CMissile cMissile : controller.getMissiles())
    {
      iElements[cMissile.getPosX()][cMissile.getPosY()].setMissile(true);
    }
  }




  public CTank getController()
  {
    return controller;
  }
  public void setController(CTank iController)
  {
    controller = iController;
  }

  public Color getTankColor()
  {
    return tankColor;
  }
  public void setTankColor(Color iTankColor)
  {
    tankColor = iTankColor;
  }

  public Color getCanonColor()
  {
    return canonColor;
  }
  public void setCanonColor(Color iCanonColor)
  {
    canonColor = iCanonColor;
  }

  public Color getMissileColor()
  {
    return missileColor;
  }
  public void setMissileColor(Color iMissileColor)
  {
    missileColor = iMissileColor;
  }
}




