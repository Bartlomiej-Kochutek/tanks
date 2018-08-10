package view;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controller.CMainMenu;





public class VMainMenu
{
  private CMainMenu controller;

  private Display display;
  private Shell shell;



  public VMainMenu()
  {
  }


  public void display()
  {
    Button playButton = new Button(shell, SWT.PUSH);
    playButton.setText("GRAJ");
    playButton.addSelectionListener(widgetSelectedAdapter(e ->
      {
        shell.close();//comment this line to have menu open while game starts
      }));
    playButton.addSelectionListener(widgetSelectedAdapter(e -> getController().onPlayButtonPushed()));

    Button exitButton = new Button(shell, SWT.PUSH);
    exitButton.setText ("WYJD");
    exitButton.addSelectionListener(widgetSelectedAdapter(e -> getController().onExitButtonPushed()));
    exitButton.addSelectionListener(widgetSelectedAdapter(e -> display.dispose()));

    shell.setDefaultButton(exitButton);
    shell.setLayout(new FillLayout(1));
    shell.pack();
    shell.open();
    while (!shell.isDisposed())
    {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }




  public void setDisplay(Display iDisplay)
  {
    display = iDisplay;
  }

  public void setShell(Shell iShell)
  {
    shell = iShell;
  }

  public CMainMenu getController()
  {
    return controller;
  }
  public void setController(CMainMenu iController)
  {
    controller = iController;
  }
}




