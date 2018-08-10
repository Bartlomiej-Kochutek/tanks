package view;

import org.eclipse.swt.graphics.Color;

import controller.CBoardElement;





public class VBoardElement
{
  private CBoardElement controller;

  private Color color;



  public VBoardElement(Color iColor)
  {
    color = iColor;
  }






  public Color getColor()
  {
    return color;
  }
  public void setColor(Color iColor)
  {
    color = iColor;
  }

  public CBoardElement getController()
  {
    return controller;
  }
  public void setController(CBoardElement iController)
  {
    controller = iController;
  }
}
