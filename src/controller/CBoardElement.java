package controller;

import org.eclipse.swt.graphics.Color;

import model.MBoardElement;
import view.VBoardElement;





public class CBoardElement
{
  private MBoardElement model;
  private VBoardElement view;



  public CBoardElement(Color iColor)
  {
    model = new MBoardElement();
    model.setController(this);

    view = new VBoardElement(iColor);
    view.setController(this);
  }





  public MBoardElement getModel()
  {
    return model;
  }
  public void setModel(MBoardElement iModel)
  {
    model = iModel;
  }

  public VBoardElement getView()
  {
    return view;
  }
  public void setView(VBoardElement iView)
  {
    view = iView;
  }


  public boolean isDestroyed()
  {
    return model.isDestroyed();
  }
  public void setDestroyed(boolean iIsDestroyed)
  {
    model.setDestroyed(iIsDestroyed);
  }

  public boolean isTank()
  {
    return model.isTank();
  }
  public void setTank(boolean iIsTank)
  {
    model.setTank(iIsTank);
  }

  public boolean isCanon()
  {
    return model.isCanon();
  }
  public void setCanon(boolean iIsCanon)
  {
    model.setCanon(iIsCanon);
  }

  public boolean isMissile()
  {
    return model.isMissile();
  }
  public void setMissile(boolean iIsMissile)
  {
    model.setMissile(iIsMissile);
  }

  public boolean isBaseWall()
  {
    return model.isBaseWall();
  }
  public void setBaseWall(boolean iIsBaseWall)
  {
    model.setBaseWall(iIsBaseWall);
  }
}




