package controller;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;

import model.MGameBoard;
import view.VGameBoard;





public class CGameBoard
{
  private CGameWindow parentGameWindow;

  private MGameBoard model;
  private VGameBoard view;

  private CBoardElement[][] elements;



  public CGameBoard(
      MGameBoard iModel,
      VGameBoard iView)
  {
    model = iModel;
    model.setController(this);

    view = iView;
    view.setController(this);
  }


  void prepare()
  {
    view.prepare();

    int thisSize = model.getSize();

    Device colorDevice = parentGameWindow.getDisplayToGetDevice().getSystemColor(SWT.COLOR_BLUE).getDevice();
    elements = new CBoardElement[thisSize][thisSize];
    for (int i = 0; i < thisSize; i++)
    {
      for (int j = 0; j < thisSize; j++)
      {
        elements[i][j] = new CBoardElement(new Color(
            colorDevice,
            (int)(Math.random() * 1000) % 70 + 130,
            (int)(Math.random() * 1000) % 70 + 60,
            (int)(Math.random() * 1000) % 30 + 20));
      }
    }
  }

  public void redraw(
      CTank iTank,
      EPartOfScreen iPartOfScreen)
  {
    view.redraw(
        parentGameWindow.getView().getDraphicContext(),
        iTank,
        iPartOfScreen);
  }

  public static boolean indicesOutsideWindow(
      int iXIndex,
      int iYIndex,
      int iBoardSize)
  {
    return (iXIndex < 0 || iXIndex >= iBoardSize ||
            iYIndex < 0 || iYIndex >= iBoardSize);
  }




  public controller.CGameWindow getParentGameWindow()
  {
    return parentGameWindow;
  }
  public void setParentGameWindow(CGameWindow iParent)
  {
    parentGameWindow = iParent;
  }

  public MGameBoard getModel()
  {
    return model;
  }
  public void setModel(MGameBoard iModel)
  {
    model = iModel;
  }

  public void setView(VGameBoard iView)
  {
    view = iView;
  }
  public VGameBoard getView()
  {
    return view;
  }

  public CBoardElement[][] getElements()
  {
    return elements;
  }
  public void setElements(CBoardElement[][] iElements)
  {
    elements = iElements;
  }


  public int getSize()
  {
    return model.getSize();
  }
  public void setSize(int iSize)
  {
    model.setSize(iSize);
  }
}




