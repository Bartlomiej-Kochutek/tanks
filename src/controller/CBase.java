package controller;

import model.MBase;
import view.VBase;





public class CBase
{
  private CTank parentTank;

  private MBase model;
  private VBase view;



  public CBase(
      MBase iModel,
      VBase iView)
  {
    model = iModel;
    model.setController(this);

    view = iView;
    view.setController(this);
  }


  public void draw()
  {
    view.draw(parentTank.getParentGameWindow().getChildBoard().getElements());
  }

  public void prepare()
  {
    model.prepare();
  }




  public CTank getParentTank()
  {
    return parentTank;
  }
  public void setParentTank(CTank iParentTank)
  {
    parentTank = iParentTank;
  }

  public int getPosX()
  {
    return model.getPosX();
  }

  public int getPosY()
  {
    return model.getPosY();
  }

  public int getSize()
  {
    return model.getSize();
  }
}
