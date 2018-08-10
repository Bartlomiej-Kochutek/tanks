package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controller.CGameWindow;





public class VGameWindow
{
  CGameWindow controller;

  private static final int WINDOW_WIDTH = 1300;
  private static final int WINDOW_HIGHT = 760;

  private Display display;
  private Shell shell;
  private GC graphicContext;



  public VGameWindow(Display iDisplay)
  {
    display = iDisplay;
    shell = new Shell(display,
                      SWT.NO_BACKGROUND | SWT.NO_REDRAW_RESIZE | SWT.SHELL_TRIM);

    graphicContext = new GC(shell);
  }


  public void draw()
  {
  }

  public void prepareDisplay()
  {
    if (shell.isDisposed())
      shell = new Shell();
    if (shell.isDisposed())
      System.out.println("still disposed");

    shell.addPaintListener(listener -> controller.onRedraw());
    shell.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent iEvent)
      {
        controller.onKeyPressed(iEvent.keyCode);
      }
      @Override
      public void keyReleased(KeyEvent iEvent)
      {
        controller.onKeyReleased(iEvent.keyCode);
      }
    });
    Rectangle clientArea = shell.getClientArea();

    shell.setBounds(
        clientArea.x + 10,
        clientArea.y + 10,
        WINDOW_WIDTH,
        WINDOW_HIGHT);

    shell.open();
  }

  public boolean refresh()
  {
    if (shell.isDisposed())
    {
      return false;
    }
    else
    {
      if (!display.readAndDispatch())
      {
        shell.redraw();
      }
      return true;
    }
  }




  public void setDisplay(Display iDisplay)
  {
    display = iDisplay;
  }

  public void setShell(Shell iShell)
  {
    shell = iShell;
  }
  public Shell getShell()
  {
    return shell;
  }

  public GC getDraphicContext()
  {
    return graphicContext;
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




