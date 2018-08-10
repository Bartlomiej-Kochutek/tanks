package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import controller.CBoardElement;
import controller.CGameBoard;
import controller.CTank;
import controller.EPartOfScreen;





public class VGameBoard
{
  private CGameBoard controller;

  Device colorDevice;
  private Color destroyedMapColor;
  private Color frameColor;
  private Color hitPointsPositiveColor;
  private Color hitPointsNegativeColor;
  private Color baseWallColor;



  public VGameBoard()
  {
  }


  public void prepare()
  {
    colorDevice = controller.getParentGameWindow().getDisplayToGetDevice().getSystemColor(SWT.COLOR_BLUE).getDevice();

    destroyedMapColor = new Color(
        colorDevice,
        255, 255, 255);

    frameColor = new Color(
        colorDevice,
        0, 0, 0);

    hitPointsPositiveColor = new Color(
        colorDevice,
        0, 255, 0);

    hitPointsNegativeColor = new Color(
        colorDevice,
        255, 0, 0);

    baseWallColor = new Color(
        colorDevice,
        255, 255, 0);
  }

  public void redraw(
      GC iGraphicContext,
      CTank iTank,
      EPartOfScreen iPartOfScreen)
  {
    Rectangle clientArea = controller.getParentGameWindow().getView().getShell().getClientArea();

    int halfTankSize = iTank.getSize() / 2;

    int tankPosX = iTank.getPosX();
    int amountOfWindowElementsX = (clientArea.width / controller.getParentGameWindow().getElementSize()) / 2;
    int elementsFirstXIndex = tankPosX - (amountOfWindowElementsX / 2 - halfTankSize);

    int firstWindowElementX = 0;
    if (iPartOfScreen == EPartOfScreen.RIGHT)
      firstWindowElementX = amountOfWindowElementsX + 1;

    int tankPosY = iTank.getPosY();
    int amountOfWindowElementsY = clientArea.height / controller.getParentGameWindow().getElementSize();
    int elementsFirstYIndex = tankPosY - (amountOfWindowElementsY / 2 - halfTankSize);

    CBoardElement[][] boardElements = controller.getElements();

    int boardSize = boardElements.length;
    int boardElementSize = controller.getParentGameWindow().getElementSize();

    redrawFrameAndBasicMapElements(
        iGraphicContext,
        iTank,
        boardElementSize,
        boardSize,
        elementsFirstXIndex,
        elementsFirstYIndex,
        firstWindowElementX,
        amountOfWindowElementsX,
        amountOfWindowElementsY);

    redrawTank(
        iGraphicContext,
        iTank,
        boardElementSize,
        boardSize,
        elementsFirstXIndex,
        elementsFirstYIndex,
        firstWindowElementX,
        amountOfWindowElementsX,
        amountOfWindowElementsY);

    redrawMissiles(
        iGraphicContext,
        iTank,
        boardElementSize,
        boardSize,
        elementsFirstXIndex,
        elementsFirstYIndex,
        firstWindowElementX,
        amountOfWindowElementsX,
        amountOfWindowElementsY);

    redrawHitPointsBar(
        iGraphicContext,
        iTank,
        boardElementSize,
        firstWindowElementX,
        amountOfWindowElementsX);

    redrawRightBorder(
        iGraphicContext,
        boardElementSize,
        firstWindowElementX,
        amountOfWindowElementsX,
        amountOfWindowElementsY);
  }
  private void redrawFrameAndBasicMapElements(
      GC iGraphicContext,
      CTank iTank,
      int iBoardElementSize,
      int iBoardSize,
      int iElemenstFirstXIndex,
      int iElemenstFirstYIndex,
      int iFirstWindowElementX,
      int iAmountOfWindowElementsX,
      int iAmountOfWindowElementsY)
  {
    int xIndex = iElemenstFirstXIndex;
    for (int currentWindowPartX = 0; currentWindowPartX < iAmountOfWindowElementsX; currentWindowPartX++)
    {
      CBoardElement[][] boardElements = controller.getElements();

      int yIndex = iElemenstFirstYIndex - 1;
      for (int currentWindowPartY = iTank.getHitPoints().getBarHeight();
            currentWindowPartY <= iAmountOfWindowElementsY; currentWindowPartY++)
      {
        yIndex++;
        if (indicesOutsideWindow(xIndex, yIndex, iBoardSize))
          iGraphicContext.setBackground(frameColor);
        else
        {
          if (boardElements[xIndex][yIndex].isTank() ||
              boardElements[xIndex][yIndex].isMissile())
            continue;

          iGraphicContext.setBackground(boardElements[xIndex][yIndex].getView().getColor());

          if (boardElements[xIndex][yIndex].isDestroyed())
            iGraphicContext.setBackground(destroyedMapColor);

          if (boardElements[xIndex][yIndex].isBaseWall())
            iGraphicContext.setBackground(baseWallColor);
        }
        iGraphicContext.fillRectangle(
            iBoardElementSize * (currentWindowPartX + iFirstWindowElementX),
            iBoardElementSize * currentWindowPartY,
            iBoardElementSize,
            iBoardElementSize);
      }
      xIndex++;
    }
  }
  private void redrawTank(
      GC iGraphicContext,
      CTank iTank,
      int iBoardElementSize,
      int iBoardSize,
      int iElemenstFirstXIndex,
      int iElemenstFirstYIndex,
      int iFirstWindowElementX,
      int iAmountOfWindowElementsX,
      int iAmountOfWindowElementsY)
  {
    CBoardElement[][] boardElements = controller.getElements();

    int xIndex = iElemenstFirstXIndex;
    for (int currentWindowPartX = 0; currentWindowPartX < iAmountOfWindowElementsX; currentWindowPartX++)
    {
      int yIndex = iElemenstFirstYIndex - 1;
      for (int currentWindowPartY = iTank.getHitPoints().getBarHeight();
            currentWindowPartY <= iAmountOfWindowElementsY; currentWindowPartY++)
      {
        yIndex++;
        if (indicesOutsideWindow(xIndex, yIndex, iBoardSize))
          continue;

        if (boardElements[xIndex][yIndex].isTank())
        {
          iGraphicContext.setBackground(iTank.getView().getTankColor());

          if (boardElements[xIndex][yIndex].isCanon())
          {
            iGraphicContext.setBackground(iTank.getView().getCanonColor());
          }
          iGraphicContext.fillRectangle(
              iBoardElementSize * (currentWindowPartX + iFirstWindowElementX),
              iBoardElementSize * currentWindowPartY,
              iBoardElementSize,
              iBoardElementSize);
        }
      }
      xIndex++;
    }
  }
  public static boolean indicesOutsideWindow(
      int xIndex,
      int yIndex,
      int iBoardSize)
  {
    return (xIndex < 0 || xIndex >= iBoardSize ||
            yIndex < 0 || yIndex >= iBoardSize);
  }
  private void redrawMissiles(
      GC iGraphicContext,
      CTank iTank,
      int iBoardElementSize,
      int iBoardSize,
      int iElemenstFirstXIndex,
      int iElemenstFirstYIndex,
      int iFirstWindowElementX,
      int iAmountOfWindowElementsX,
      int iAmountOfWindowElementsY)
  {
    Color missileColor = iTank.getView().getMissileColor();
    iGraphicContext.setBackground(missileColor);

    CBoardElement[][] boardElements = controller.getElements();

    int xIndex = iElemenstFirstXIndex;
    for (int currentWindowPartX = 0; currentWindowPartX < iAmountOfWindowElementsX; currentWindowPartX++)
    {
      int yIndex = iElemenstFirstYIndex - 1;
      for (int currentWindowPartY = iTank.getHitPoints().getBarHeight();
            currentWindowPartY <= iAmountOfWindowElementsY; currentWindowPartY++)
      {
        yIndex++;
        if (indicesOutsideWindow(xIndex, yIndex, iBoardSize))
          continue;

        if (boardElements[xIndex][yIndex].isMissile())
        {
          iGraphicContext.fillRectangle(
              iBoardElementSize * (currentWindowPartX + iFirstWindowElementX),
              iBoardElementSize * currentWindowPartY,
              iBoardElementSize,
              iBoardElementSize);
        }
      }
      xIndex++;
    }
  }
  private void redrawHitPointsBar(
      GC iGraphicContext,
      CTank iTank,
      int iBoardElementSize,
      int iFirstWindowElementX,
      int iAmountOfWindowElementsX)
  {
    double tankHitPointsPercentage = iTank.getHitPoints().getAmountAsPercentage();
    for (int currentWindowPartX = 0; currentWindowPartX < iAmountOfWindowElementsX; currentWindowPartX++)
    {
      double hitPointsBarPart = (double)currentWindowPartX / (double)iAmountOfWindowElementsX;
      boolean markHitPointsAsPositive = hitPointsBarPart < tankHitPointsPercentage;

      for (int currentWindowPartY = 0; currentWindowPartY < iTank.getHitPoints().getBarHeight();
            currentWindowPartY++)
      {
        if (markHitPointsAsPositive)
          iGraphicContext.setBackground(hitPointsPositiveColor);
        else
          iGraphicContext.setBackground(hitPointsNegativeColor);

        iGraphicContext.fillRectangle(
            iBoardElementSize * (currentWindowPartX + iFirstWindowElementX),
            iBoardElementSize * currentWindowPartY,
            iBoardElementSize,
            iBoardElementSize);
      }
    }
  }
  private void redrawRightBorder(
      GC iGraphicContext,
      int iBoardElementSize,
      int iFirstWindowElementX,
      int iAmountOfWindowElementsX,
      int iAmountOfWindowElementsY)
  {
    iGraphicContext.setBackground(frameColor);

    for (int currentWindowPartY = 0; currentWindowPartY <= iAmountOfWindowElementsY; currentWindowPartY++)
    {
      iGraphicContext.fillRectangle(
          iBoardElementSize * (iAmountOfWindowElementsX + iFirstWindowElementX),
          iBoardElementSize * currentWindowPartY,
          iBoardElementSize,
          iBoardElementSize);
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
}




